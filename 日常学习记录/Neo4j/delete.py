from py2neo import Graph,Node,Relationship

graph = Graph(password='123456')
nodes = graph.nodes.match('老师')
for node in nodes:
    graph.delete(node)
