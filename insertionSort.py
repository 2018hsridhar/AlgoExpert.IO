'''
Complexity : O(N^2) Time, O(1) Space
Let N := #-elements in the input array
'''

'''
Python lacks a NULL : has a NONE instead
If a function does not return any value -> it returns NONE instead
The 'pass' statement : omit implementation

Insert elements into their correct locations/indices
Does insertion sort require selecting for the min el or for the max el? 
Please review rederivation of insertion sort
	- You know that it requires more comparison and swap ops than selection sort, typically
	with exception of well-sorted inputs ( none here : no swps/cmps ) 
	
'''

def insertionSort(array):
	if( array is None ):
		print("Array equals None")
		return array
	elif ( len(array) == 0 ):
		print("Array length equals zero")
		return array
	else:
		n = len(array)
		for i in range(len(array)):
			elemIdx = i
			elem = array[i]
			j = i-1
			while(j >= 0 and array[j] > elem):
				swap(array, j, elemIdx)
				elemIdx = j # Keeps reducing each time here
				j -= 1
		return array

def swap(A, i, j):
	A[i],A[j] = A[j],A[i]
