# coding=utf8
"""
    已完成
    将商品推荐表转化成对应商品类目推荐表
    5:00 3000
    6:27 10500
    7:19 15000
"""
import time
def item_cat():
    fashion_cat = []
    fashion_cat_file = open('fashion_cat.txt', 'a+')
    fashoion_matchsets = open('dim_fashion_matchset.txt')
    fashion_match = fashoion_matchsets.readlines()
    i = 1
    for each in fashion_match:
        items_list_d = []
        new_cat = []

        fashoion_match_line = each.rstrip('\n').split(' ')
        items_list = fashoion_match_line[1].split(';')
        for items in items_list:
            if ',' in items:
                items_list_d.append(items.split(',')[0])
                items_list.remove(items)
            else:
                items_list_d.append(items)

        for new in items_list_d:
            new_cat.append(cat_id(new))

        for out in new_cat:
            fashion_cat_file.write(out + ' ')
        fashion_cat_file.write('\n')
        print(i)
        i = i + 1


def cat_id(item_id):
    dim_items = open('dim_items.txt', 'r')
    dim_items_line = dim_items.readlines()

    for each in dim_items_line:
        items_list = each.rstrip('\n').split(' ')
        if item_id == items_list[0]:
            return items_list[1]


if __name__ == '__main__':
    time1 = time.time()
    item_cat()
    time2 = time.time()
    print(time2 - time1)
