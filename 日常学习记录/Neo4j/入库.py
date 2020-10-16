from py2neo import Graph, Node, Relationship

graph = Graph('http://localhost:7474', username='neo4j', password='123456')

a = Node('person', name='俞逸洋')
graph.create(a)
b = Node('person',name='蔚')
graph.create(b)
ab = Relationship(a, 'KNOWS', b)
graph.create(ab)

a = Node('person', name='yy',age=23)
b = Node('person', name='ww',age=21)
ab = Relationship(a, 'KNOWS', b)
graph.create(a)
graph.create(b)
graph.create(ab)