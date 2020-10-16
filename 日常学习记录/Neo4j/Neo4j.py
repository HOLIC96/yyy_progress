from py2neo import Graph, Node, Relationship

graph = Graph('http://localhost:7474', username='neo4j', password='123456')

test_node_1 = Node(label='ryz', name='皇帝')
test_node_2 = Node(label='ryz', name='皇后')
test_node_3 = Node(label='ryz', name='公主')
graph.create(test_node_1)
graph.create(test_node_2)
graph.create(test_node_3)

node_1_zf_node_1 = Relationship(test_node_1, '丈夫', test_node_2)
node_1_zf_node_1['count'] = 1
node_2_qz_node_1 = Relationship(test_node_2, '妻子', test_node_1)
node_2_mv_node_3 = Relationship(test_node_2, '妻子', test_node_3)
node_2_qz_node_1['count'] = 1
node_2_mv_node_3['count'] = 1

graph.create(node_1_zf_node_1)
graph.create(node_2_qz_node_1)
graph.create(node_2_mv_node_3)

print(graph)
print(test_node_1)
print(test_node_2)
print(node_1_zf_node_1)
print(node_2_qz_node_1)
print(node_2_mv_node_3)
