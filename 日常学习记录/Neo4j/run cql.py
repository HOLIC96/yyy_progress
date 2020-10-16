from py2neo import Node,Relationship,Graph

graph = Graph(password='123456')
data = graph.run('MATCH (p:person) return p LIMIT 5')
print(list(data))
