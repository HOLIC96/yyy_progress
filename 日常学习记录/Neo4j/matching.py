from py2neo import Graph,NodeMatcher,RelationshipMatcher

graph = Graph(password='123456')
matcher = NodeMatcher(graph)
print(matcher.match('老师', name='Mr.冯').first())
print(list(matcher.match('学生').where("_.name =~ '二.*'")))
print(list(matcher.match('学生').where("_.name =~ '二.*'").order_by("_.age").limit(3)))

rMatch = RelationshipMatcher(graph)
print(list(rMatch.match(r_type='师生lq')))
