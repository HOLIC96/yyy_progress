# coding=gbk
"""
�ú�����dim_items�������ݽ��д�������һ����Ʒ��Ŀ��ڶ�����ƷID�����ֵ䲢����
"""
import codecs
import csv
import pandas as pd
import time


def main():
    f = codecs.open('dim_items.txt', mode='r', encoding='utf-8')
    line = f.readline()
    global list1
    global list2
    list1 = []
    list2 = []
    # list3 = []
    while line:
        a = line.split()
        b = a[0:1]  # ��ƷID
        c = a[1:2]  # ��Ʒ��ĿID
        list1.append(b[0])
        list2.append(c[0])
        line = f.readline()
    f.close()
    keys = list2
    values = list1

    for i in range(len(keys)):
        dictionary.setdefault(keys[i], []).append(values[i])

    # print('Dictionary: %s' % dictionary)

'''
def save():  # ���ֵ����ݽ��д�������CSV
    df = pd.DataFrame.from_dict(dictionary, orient='index')
    pd.DataFrame(df).to_csv('test.csv')
'''

if __name__ == '__main__':
    start = time.time()
    dictionary = {}
    main()
    # save()
    end = time.time()
    print(dictionary[list2[0]])
    r = dictionary[list2[0]]
    print(len(r))
    print("�����ʱ��%s" % float(end - start))
