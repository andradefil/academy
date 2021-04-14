import random
l1=10000000
l2=99999999
listrandom=[]
for i in range (l1):
    value=random.randint(l1,l2)
    listrandom.append(value)

for item in listrandom:
    print(item)
