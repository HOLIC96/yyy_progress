# coding=gbk

"""
���
��Ҫ��cat_items���в���
"""
import codecs

f = codecs.open('cat_item.txt', mode='r', encoding='utf-8')
line = f.readline()
list1 = []
list2 = []
list3 = []
while line:
    a = line.split()
    b = int(a[0:2])  # ��ƷID
    list1.append(b)
    # list2.append(c[0])
    line = f.readline()
f.close()
print(list1[0][0])