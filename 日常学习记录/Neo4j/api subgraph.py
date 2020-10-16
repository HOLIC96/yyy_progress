from py2neo import Node,Relationship,Graph

a = Node('Person', name='Alice')
b = Node('Person', name='Bob')
r = Relationship(a, 'KNOWS', b)
s = a | b | r
graph = Graph(password='123456')
graph.create(s)
