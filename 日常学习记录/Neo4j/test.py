from py2neo import Graph, Node, Relationship

graph = Graph(password='123456')
# 10个学生
a1 = Node('学生', name='赵', age=21, sex='男', gradClass='1908')
a2 = Node('学生', name='钱', age=22, sex='男', gradClass='1908')
a3 = Node('学生', name='孙', age=21, sex='男', gradClass='1908')
a4 = Node('学生', name='李', age=21, sex='男', gradClass='1908')
a5 = Node('学生', name='周', age=21, sex='男', gradClass='1908')
a6 = Node('学生', name='吴', age=20, sex='男', gradClass='1908')

b1 = Node('学生', name='郑', age=20, sex='女', gradClass='1908')
b2 = Node('学生', name='王', age=20, sex='女', gradClass='1908')

allStuNodes = [a1, a2, a3, a4, a5, a6, b1, b2]
boyStuNodes = [a1, a2, a3, a4, a5, a6]
girlStuNodes = [b1, b2]
c1 = Node('老师', name='冯', age=50, sex='男', gradClass='1908')

# 所有学生建立同学关系网
for stu in allStuNodes:
    for stu_ in allStuNodes:
        if stu != stu_:
            r = Relationship(stu, '同学', stu_)
            graph.create(r)

# 所有学生与老师建立师生关系
for stu in allStuNodes:
    r = Relationship(stu, '师生', c1)
    graph.create(r)



