from py2neo import Graph,Node,Relationship

graph = Graph(password='123456')
tx = graph.begin()
a = Node('person', name='Jack')
tx.create(a)
b = graph.nodes[234]
ab = Relationship(a, 'KNOWS', b)
tx.create(ab)
tx.commit()
print(graph.exists(ab))
