# coding=gbk
"""
�����
�ú�����Ҫ�Ƕ��Ա����´����е�txt�ļ����д�����Ҫ������ȡ�����У�������䴦���������ı�
"""
import codecs

f = codecs.open('dim_items.txt', mode='r', encoding='utf-8')
line = f.readline()
list1 = []
list2 = []
list3 = []
while line:
    a = line.split()
    b = a[0:2]  # ��ƷID
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
