# generate random integer values
from random import seed
from random import randint
# seed random number generator
seed(1)
# generate some integers
max = 1000000
for _ in range(max):
	usetime = randint(0, 5)
	value = randint(0, 5)
	print("{0},{1}".format(usetime, value))