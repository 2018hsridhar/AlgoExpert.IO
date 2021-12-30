# In Python, we need the ':' symbol to denote the end of conditional statements ( and for loops, which are conditional statements within ) 
def getNthFib(n):
	x1 = 0
	x2 = 1
	f = 0
	if ( n <= 1 ):
		return 0
	elif ( n == 2):
		return 1
	else:
		for i in range(2,n):	
			f = x1 + x2
			# Most functions do take parameterized arg lists in their parantheses
			# Space delimited args passed in here
			x1 = x2
			x2 = f
	return f
