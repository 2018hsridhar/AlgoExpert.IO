
Sort this : 
	[2,	3, 5, 6, 7, 8, 9]
     0  1  2  3  4  5  6
  
Sample Input
array = [8, 5, 2, 9, 7, 6, 3]
k = 3 ( would be at index 2 = (k-1) if sorted )

Sample Output
5

8 = pivot
What if a pivot NOT in array was selected for?
pivot = 5.5 :            idx = 2 ( pivot ) : kth-smallest
	partitioning => {2,3,5,6,7,8,9}
						 ~
	
k = 2
[8, 5, 2, 9, 7, 6, 3]
[5, 2, 7, 6, 3, 8,9 ]
[2, 3, 5, 7, 6, 8,9 ] HALT

k = 5
[6, 5, 2, 9, 7, 8, 3]
[5, 2, 3, 6, 9, 7, 8]	6 @ k = 4
[5, 2, 3, 6, 7, 8, 9]	8 @ k = 6		Partition in a divide-and-conquer way
[5,5] : but done	=> single Element case


PSEUDOCODE : 

	quickSelect(array,k):
		n = len(array)
		low = 0
		high = n-1
		while(low <= high)
			pivotIndex = partition(A,low,high,pivot)
			if pivotIndex == k-1 :
				ret array[pivotIndex]
			elif pivotIndex > k-1 :
				high = pivotIndex - 1
			else
				low = pivotIndex + 1
				
				
	[4,1,3,8,6,2,5]
	 0 1 2 3 4 5 6 = indices
	 i = 0, j = 1, pivot = a[0] = 4
	 [4,1,3,8,6,2,5]		is a NOP
	 [4,1,3,2,6,8,5]		< relative ordering does not matter
	 [2,1,3,4,6,8,5]		< relative ordering does not matter
	 
	
	# pivot = A[low] here
	# Elements from (i,j] >= pivot and [low+1,i] < pivot
	partition(A,low,high,pivot):
		pivot = array[low]
		i = low+1	# where pivot is # A[i] = pivot
		for j in range(low+1,high,1):
			if A[j] < pivot
				swp(A,j,i)
				i++
		swp(A,low,i-1)
		ret i-1
				
	swp(A,src,dst):
		temp = A[src]
		A[src] = A[dst]
		A[dst] = temp
	




Strategy : 
Quicksort : pivot selected at random index
	Pivot @ exact location

	Pivot = 8
	[5,2,7,6,3,8,9]
     ---------
	 6 elems <= 8
	 1 elem > 8




Summations / Math
Binary Search 		X
Min/Max - 			O(N)
Divide-and-Conquer/Recursive strategy

Clarification
n := integers ( not guaranteed distinctness )
Find kth smallest element
Given a k, where 1 <= k <= n
Always find a smallest element


Complexity
Let N := len(array)

Worst : 
	O(N^2) time, O(1) space ( auxillary and call stack ) -> iterative
	Randomize => average
	
Average :
	Time = O(N) + O(lgN) + O(lg(lg(N)))?
	O(N)
	1      2         3
	O(N) + O(N//2) + O(N//4) = O(N) : drop smaller terms
	1+0.5+0.25+.... ~= 2
	O(2*N) = O(N)
	
Best :
	O(N) ( 1 step partitioning ) Time, O(1) space


Do it linearly : O(N)
	- multiple passes : no O(NK) either
Std sort : O(NlgN)



Test Cases
(A) [1,2,3,4,5,6],3
	=> 3
(B) [6,5,4,3,2,1],3
	=> 3 ( getting 5 : remedy )
(C)
(D)
(E)

Provided : 


[8, 5, 2, 9, 7, 6, 3]
3


def quickselect(array, k):
	n = len(array)
	low = 0
	high = n-1
	while low <= high:
		pivotIndex = partition(array,low,high)
		# print(array, pivotIndex, array[pivotIndex])
		if pivotIndex == k-1 :
			return array[pivotIndex]
		elif pivotIndex > k-1 :
			high = pivotIndex - 1
		else:
			low = pivotIndex + 1

# O(N) Time, O(1) Space
def partition(A,low,high):
	pivot = A[low]
	i = low+1	# where pivot is # A[i] = pivot
	for j in range(low+1,high+1,1):
		if A[j] < pivot:
			swp(A,j,i)
			i += 1
	swp(A,low,i-1)
	return i-1

def swp(A,src,dst):
	temp = A[src]
	A[src] = A[dst]
	A[dst] = temp
