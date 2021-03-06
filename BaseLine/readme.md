# 天池BaseLine进程  
## 赛题简介
淘宝网是中国深受欢迎的网购零售平台，其中服饰鞋包行业占据市场的绝大部分份额，围绕着淘宝诞生了一大批优秀的服饰鞋包导购类的产品。穿衣搭配是服饰鞋包导购中非常重要的课题，它所延伸出的技术、算法能广泛应用到大数据营销几乎所有场景中，如搜索、推荐和营销服务。淘宝穿衣搭配算法竞赛将为参赛者提供搭配专家和达人生成的搭配组合数据，百万级别的淘宝商品的文本和图像数据，同时还将提供用户的脱敏行为数据。期待参赛者能从以上行为、文本和图像数据中挖掘穿衣搭配模型，为用户提供个性化、优质的、专业的穿衣搭配方案。

## 文本处理思路及过程
1.对dim_items.txt来说，里面包含了“商品ID——类目ID——分词结果”三个部分，先使用字典将商品类目与商品ID一一进行对应，一是为了对user_brought_history进行处理使，对购买的同类商品进行区分，二是为了处理dim_fashion_matchset表，将商品匹配信息转化成类目匹配信息。再就是对分词结果进行处理，处理思路是用TF-IDF，以商品ID作为索引，计算每个商品的tf-idf值，假设其相似度高的商品是互为匹配的关系。  

2.对dim_fashion_matchset表来说，里面包含了专家推荐的商品匹配推荐，不同类商品由“;”隔开，同类由“，”隔开，最初的想法是直接用apriori算法直接对原数据集进行关联算法处理，但这样处理商品之间的支持度并不高，所以转而对表数据进行处理，将商品匹配转化成商品类目匹配，再进行apriori算法对数据进行处理。    

3.对user_brought_history来说，里面存储了“用户ID——购买商品ID——购买日期”的信息，处理思路是将同一用户在同一天购买的不同类商品提取出来，假设他们之间存在着关联关系。  

## 知识图谱建立思路及过程
1.通过对文本进行处理与理解，暂时定义服装推荐BaseLine知识图谱分为商品和用户两大类，商品大类下再细分为item_id和商品类目两小类，用户大类下细分为用户ID和行为日期两小类（行为日期暂定，因为想在图谱里表示 用户--> has brought --> item_id --> on 20201007，但暂时没有想到比较好的构建思路）。  

2.通过对数据进行处理，共有281类商品，商品总数为499983个，用户1103702个，数据已加入知识图谱中，并将商品ID存入item_id并将其存入对应的商品类目下，方便后期建立推理规则。  

3.后期根据文本处理后得到的相似度进行融合处理，将关联规则输入知识图谱。


## 现今存在的问题
1.apriori算法计算出的关联规则支持度和置信度不高。  

2.因为知识图谱中加载的数据过多（应该），现在我的电脑暂时打不开owl文件，并不能清楚地知道知识图谱的构件图的样子。

3.原本的计划是在构建只是图谱的代码中实现知识图谱的固化，将知识图谱存储到TDB中。但根据官网给的资料进行尝试后暂时存在问题实现不了。替代做法是利用Jena fuseki可视化工具，现将知识图谱构建后的数据存储为owl格式，在利用fuseki手动存入TDB2中，可通过SparQL进行查询，检查是否存在问题。该问题可能对后期为知识图谱添加推理规则产生一定影响。
