'''
Find the largest number
Array size guaranteed at least three integers

Let N := #-elements in the array
TIME = O(3*N) = O(N)
SPACE = O(3) = O(1) ( constant )

TEST CASES :
(A) [1,2,3]
	[1,2,3]
(B) [1,2,2]
	[1,2]
(C) [1,1,1]
	[1]
(D)
(E)

https://stackoverflow.com/questions/7604966/maximum-and-minimum-values-for-ints

'''

import sys

def findThreeLargestNumbers(array):
	maxes = [] # Dynamic arrays everywhere : not static contiguous blocks :-O
	if(array is None):
		print("Array is none")
	# Indices are range-based iteration
	elif(len(array) == 0 ):
		print("Array length equals zero")
	else:
		n = len(array)
		minVal = -1*sys.maxsize - 1
		maxOne = minVal # no INT_MIN : must go by max negated, minus 1 ( storage ) 
		maxTwo = minVal
		maxThree = minVal
		for i in range(0,n):
			if(array[i] > maxOne):
				maxOne = array[i]
		# for in loop
		for elem in array:
			if(elem > maxTwo and elem < maxOne):
				maxTwo = elem
		for val in array:
			if(val > maxThree and val < maxTwo):
				maxThree = val
	if(maxThree != minVal):
		maxes.append(maxThree)
	else:
		if(maxTwo != minVal):
			maxes.append(maxTwo)
		else:
			maxes.append(maxOne)
	if(maxTwo != minVal):
		maxes.append(maxTwo)
	else:
		maxes.append(maxOne)
	maxes.append(maxOne)
	return maxes			
