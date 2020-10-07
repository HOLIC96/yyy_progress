# coding=gbk
"""
已完成
该函数主要是对淘宝穿衣搭配中的txt文件进行处理，主要用来提取所需行，按需搭配处理并输入新文本
"""
import codecs

f = codecs.open('dim_items.txt', mode='r', encoding='utf-8')
line = f.readline()
list1 = []
list2 = []
list3 = []
while line:
    a = line.split()
    b = a[0:2]  # 商品ID
    list2 = [int(x) for x in b]
    list2 = int()
    list1.append(list2)
    # list2.append(c[0])
    line = f.readline()
f.close()

for i in list1:
    list3.append(i)
    with open('cat_item.txt', 'a+', encoding='utf-8') as w:
        w.write((str(i)) + '\n')

print(b[0][0])
