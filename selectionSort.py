import sys
# Is a O(N^2) Time, O(1) Space Complexity algo
# Where N := # elements in the input array
# Select for the min and max at each step, and just perform a single SWP operation of elements
# There is no need for elements shifting, luckily - > (N-1) # of swaps
def selectionSort(array):
	for wIdx in range(len(array)):	# Range does not take in increments though
	    rIdx = wIdx
		minIdx = -1
		# minEl = float('inf') # We lack an INT_MAX : hence use of float here, but may lead to precission errs
		minEl = sys.maxsize		# Python 3 : sys.maxsize instead 
		while(rIdx < len(array)):
			if(array[rIdx] < minEl):
				minEl = array[rIdx]
				minIdx = rIdx
			rIdx += 1
		# Exec swp operation here
		swap(array,wIdx,minIdx) # Exactly (n-1) swp operations :-O
	return array
	
# Swaps takes in index -> not value -> parameters
# You can write it like this. Like ... woah!
def swap(array, i, j):
	array[i], array[j] = array[j],array[i]
	# temp = array[i]
	# array[i] = array[j]
	# array[j] = temp
