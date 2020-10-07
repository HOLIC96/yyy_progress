import codecs
import os
import pandas as pd

f = codecs.open('user_id.txt', mode='r', encoding='utf-8')
line = f.readline()
list1 = []
list3 = []
while line:
    a = line.split(',')
    # item_id
    b = a[1:2]
    # cat_id
    # c = a[1:2]
    list1.append(int(b[0].strip()))
    # list2.append(c[0])
    line = f.readline()
f.close()

# for i in list1:
#     with open('user_id_KG.txt', 'a+', encoding='utf-8') as w:
#         w.write((str(i)) + '\n')



'''
list3 = []


for i in list1:
    if i not in list3:
        list3.append(i)
        with open('item_id.txt', 'a+', encoding='utf-8') as w:
            w.write((str(i)) + '\n')
'''
