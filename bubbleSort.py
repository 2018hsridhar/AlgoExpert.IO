'''
https://www.youtube.com/watch?v=6Gv8vg0kcHc

Complexity
Let N := #-elements in the array
Time = O(N^2)
Space = O(1)

Test Cases : 
(A)
(B)
(C)

PSEUDOCODE 

	n = len(arr)
	lastElemIdx = n-1
	sorted is true
	while array is unsorted
		sorted is true
		for each elem in array till lastElemIdx
			if(adjacent elements are out of order)
				swp(adj elems)
				sorted is false
		decr lastElemIdx by 1
	
	ret array
'''
def bubbleSort(array):
	n = len(array)
	lastElemIdx = n-1
	sorted = False
	while(sorted is False):
		sorted = True
		for i in range(0,lastElemIdx,1):
			if(array[i] > array[i+1]):
				array[i],array[i+1] = array[i+1],array[i]
				sorted = False
		lastElemIdx -= 1
	
	return array
	
	
	
