from py2neo import Graph,Node,Relationship

graph = Graph(password='123456')
node = graph.nodes.match('person', name='Jack').first()
node['age'] = 88
graph.push(node)

node = graph.nodes.match('person', name='Jack', age = 8).first()
node['age'] = 44
graph.push(node)