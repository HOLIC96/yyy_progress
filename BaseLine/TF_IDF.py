# coding=gbk
import math
import time
import pickle
import os

from pandas import np
from sklearn.cluster import KMeans

"""
    进行中……
    
    当前问题：
        调试中，当前tf-idf值存入文件后占用空间过大，整个文件可能占用90G空间，硬盘内存不够。
    
    TF-IDF主要完成dim_items表中商品分词数据，比较不同商品的相似度得出匹配相似度
    build_dict()
    cal_dict_times()
        cal_tf()
        cal_idf()
        cal_tf_idf()
    single_id_tf()
    load_idf()
    judge_init()
    single_tf_idf()
    single_tf()       
    similarity()
        dot_product()
        magnitude()
    sim()
    get_tf_idf()
                get_cluster() 该函数测试中
"""


class tfIdf(object):
    i = 0

    def __init__(self):
        # dictory : documents' number that have this term
        # {term1:2,term:10}=>term1词出现在两个文档中
        self.term_dict = {}
        self.dict_index = {}
        # 对应商品id号的标题分词
        self.cloth_items = []
        # 商品的id号
        self.items_id = []
        # 保存的tf次数
        self.tf_times = 0
        # 保存的idf次数
        self.idf_times = 0
        self.idf = []
        self.have_idf = 0
        self.have_dict = 0

    # 建立词表
    def build_dict(self):
        with open('dim_items.txt', 'r') as f:
            lines = f.readlines()
            for line in lines:
                fc = line.split()
                # item_id:480073缺少分词数据，NAN补全
                if len(fc) == 2:
                    print(line)
                i = i + 1
                print(i)
                fenci = fc[2]
                self.items_id.append(fc[0])
                items = fenci.split(',')
                self.cloth_items.append(items)
                flag = []
                for item in items:
                    if item not in self.term_dict:
                        self.term_dict[item] = 1
                    elif item not in flag:
                        self.term_dict[item] += 1
                    flag.append(item)

        new_dict = {}
        index = 0
        for term in self.term_dict:
            # 将分词结果小于4的数据剔除
            if self.term_dict[term] > 4:
                new_dict[term] = self.term_dict[term]
                self.dict_index[term] = index
                index += 1

        self.term_dict = new_dict

    # 统计词典只出现一次的词的个数
    def cal_dict_times(self, times):
        num = 0
        for item in self.term_dict:
            if self.term_dict[item] == times:
                num += 1

        return num

    """
    计算词频并存入tf_save供后续使用
    每隔500次存储显示一次保存状态
    """

    def cal_tf(self):
        terms_vector = []
        with open('tf_save.txt', 'wb') as f:
            for cloth in range(len(self.cloth_items)):
                term_vector = self.single_tf(cloth)
                # if term_vector != 0:
                terms_vector.append(term_vector)
                # 运行时间太长，以此标记存储时间和运行状态
                if cloth and (cloth + 1) % 500 == 0 or cloth == (len(self.cloth_items) - 1):
                    # print(terms_vector)
                    self.tf_times += 1
                    if self.tf_times % 10 == 0:
                        print("已经保存{0}次".format(self.tf_times))
                    # pickle解决存储问题
                    pickle.dump(terms_vector, f)
                    terms_vector = []

    """计算IDF并存入idf_save供后续使用"""

    def cal_idf(self):
        N = len(self.cloth_items)
        terms_vector = []
        with open('idf_save.txt', 'wb') as f:
            for term in self.term_dict:
                # print(self.term_dict[term])
                idf = math.log((N / (self.term_dict[term] + 1)) + 1)
                terms_vector.append(idf)
            pickle.dump(terms_vector, f)
        # print(terms_vector)

    # 调用先前存储的tf、idf值，计算各个商品的TF-IDF值，存入tf_idf_save中
    def cal_tf_idf(self):
        tf_idf = {}
        items_index = 0
        if os.path.getsize('tf_save.txt') > 0:
            with open('idf_save.txt', 'rb') as idf_File:
                with open('tf_save.txt', 'rb') as tf_File:
                    with open('tf_idf_save.txt', 'wb') as tf_idf_File:  # 因为系统内存不足，在260次停止运行，总文档大致有90G
                        idf = pickle.load(idf_File)
                        for i in range(self.tf_times):
                            tf = pickle.load(tf_File)
                            for tf_item in range(len(tf)):
                                # 计算TF-IDF,存入temp
                                temp = [a * b for a, b in zip(idf, tf[tf_item])]
                                tf_idf[self.items_id[items_index]] = temp
                                items_index += 1
                                # 记录，每500个数据存入一次数据
                                if tf_item and (tf_item + 1) % 500 == 0 or tf_item == (len(tf) - 1):
                                    pickle.dump(tf_idf, tf_idf_File)
                                    tf_idf = {}

    # 计算单个商品tf
    def single_id_tf(self, cloth_id):
        index = self.items_id.index(cloth_id)
        term_vector = self.single_tf(index)
        return term_vector

    def load_idf(self):
        with open('idf_save.txt', 'rb') as idf_File:
            self.idf = pickle.load(idf_File)

    def judge_init(self):
        if self.have_dict == 0:
            self.build_dict()
            self.cal_tf()
            self.cal_idf()
            self.cal_tf_idf()
            self.cal_dict_times()
            print("创建词典并计算tf-idf...")
            self.have_dict = 1
            self.have_idf = 1
        if self.have_idf == 1:
            self.load_idf()
            print("载入idf...")
            self.have_idf = 1

    def single_tf_idf(self, cloth_id):
        self.judge_init()
        term_tf = self.single_id_tf(cloth_id)
        term_tf_idf = [a * b for a, b in zip(self.idf, term_tf)]
        return term_tf_idf

    def single_tf(self, index):
        term_vector = [0 for i in range(len(self.term_dict))]
        for item in self.cloth_items[index]:
            if item in self.term_dict:
                index = self.dict_index[item]
                term_vector[index] += 1
        N = sum(term_vector)
        if N == 0:
            print("序号为{0}的商品标题与其他商品都没有大的相似性".format(index))
        # 处理，不要没有太大相关性的商品？
        # self.items_id[index] = -1
        # term_vector = 0
        else:
            term_vector = [term / N for term in term_vector]
        return term_vector

    # 点积
    def dot_product(self, v1, v2):
        return sum(a * b for a, b in zip(v1, v2))

    def magnitude(self, v1):
        return math.sqrt(self.dot_product(v1, v1))

    # 计算余弦相似度
    def similarity(self, v1, v2):
        return self.dot_product(v1, v2) / (self.magnitude(v1) * self.magnitude(v2) + 1)

    # 排序,前100个
    def sort(self, sim):
        sim = sorted(sim.items(), key=lambda item: item[1], reverse=True)
        return sim[:100]

    # 相似度计算
    def sim(self, term_id):
        # 匹配所对应的商品ID
        index = self.items_id.index(term_id)
        # 获取tf值
        search_tf = self.single_tf(index)

        self.judge_init()

        search_tf_idf = [a * b for a, b in zip(search_tf, self.idf)]

        sim = {}
        with open('tf_idf_save.txt', 'rb') as tf_idf_File:
            for time in range(self.tf_times):
                tf_idf = pickle.load(tf_idf_File)
                # print(len(tf_idf))
                for items in range(len(tf_idf)):
                    ii = time * 500 + items
                    if time != 0:
                        ii += 1
                    if ii != index:
                        # print(tf_idf[self.items_id[ii]])
                        if self.items_id[ii] not in tf_idf:
                            print(self.items_id[ii])
                            print(ii)
                        sim[self.items_id[ii]] = self.similarity(search_tf_idf, tf_idf[self.items_id[ii]])
                sim = self.sort(sim)
                sim = dict(sim)
        return sim

    # 获取tf_idf
    def get_tf_idf(self, item_id):
        tf_idf = self.single_tf_idf(item_id)
        return tf_idf

    '''
    def get_cluster(self, tf_idf):
        # 待定
        km = KMeans(max_iter=1000, n_clusters=16, n_jobs=1, precompute_distances='auto')
        x = np.array(tf_idf)
        y = km.predict([x])
        return y[0]
    '''

    # 计算两个商品的相似度
    def get_sim(self, id1, id2):
        tf_idf1 = self.get_tf_idf(id1)
        tf_idf2 = self.get_tf_idf(id2)
        sim = self.similarity(tf_idf1, tf_idf2)
        return sim


def main():
    s = tfIdf()
    # test
    id1 = input('请输入第一个商品ID： ')
    id2 = input('请输入第二个商品ID： ')
    sim = s.get_sim(id1, id2)
    print("the sim between two items is: ", sim)


if __name__ == '__main__':
    main()
