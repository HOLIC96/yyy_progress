from py2neo import Node, Relationship

a = Node('Person', name="Alice")
b = Node('Person', name="Bob")
r = Relationship(a, 'KNOWS', b)
a['age'] = 20
a.setdefault('location', '北京')
b['age'] = 21
r['time'] = '2019/12/09'
data ={
    'name':'Amy',
    'age' : 21
}
a.update(data)
print(a, b, r)
