def LoadDataSet():
    f = open('fashion_cat.txt', 'r')
    line = f.readlines()
    dataset = []
    for each in line:
        each = each.rstrip(' \n').split(' ')
        dataset.append(each)
    return print(dataset)


if __name__ == '__main__':
    LoadDataSet()

