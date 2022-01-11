Clarifications
- Input fits in RAM
- All integers
- Can have duplicates too

Goal : Sort using the heap sort algorithm
Strategy : Heap ( min|max ) , Iterative ( recursion ), Two pointers
In-place OR create auxillary space

Naive : Initialize heap, add everything to the minheap, and output back starting at first index
	- n-# elements, but time for the heapify
	- tree rotate/shifts : log_n time ( rearrange roots )
	- O(N) for search, O(logn) for deletion
	
		2
	 / \
	4	  3
 / \ / 
6  5 7  				<- minHeap data structure
								Order reltation by level, not by children order ( leftVal >= rightVal )

Last elem : reduce size
[1,2,3,4,5,7,6] <- array representing the heap
[6,2,3,4,5,7] <- array representing the heap
- sift
[2,6,3,4,5,7]
[2,4,3,6,7,7]

Log_n() time : O(1) time

--- fixed size = 7
[1,2,3,4,5,7,6]
             ^
				     p2

- deletion 
[6,2,3,4,5,7] [1]
 ^         ^
p1				 p2


Delete(1) / removeMin()

					 2
				 /   \
				4	  	3
				 \	 / \
				 5	7   6
					 

				 0	1	 2	3	 4	5	 6
INPUT : [8, 5, 2, 9, 1, 6, 3]
				 ^
				 P
				 --
				 ----
				 ------
				 ---------- every step
				[2,8,5,9,1,6,3]
				         ^
					  	   P
				
				2
			 / \
			8   5
		 /
		9
			At pointer for val = 1 ( index 4 ) 
			Inductively, assuming up to kth index, A[1...k-1] is a heap
				
				1
			 / \
			2   5					MINHEAP
		 / \  / \
		9   8 6  3
		
				9
			 / \
			8		6					MAXHEAP
		 / \  / \
		2  1 5   3

	[2,8,5,9,1,6,3]
	
	minHeap = []
	maxHeap = [9,8,6,2,1,5,3] ( in place)
						
						[8,3,6,2,1,5,9] and sift again
						 -----------
						[6,3,5,2,1,8,9] and sift again
						 ---------
						 
(index / 2) OR (index-1)/2 : child -> parent

Heap ops : 
	~ extract_min/max : poll -> removes top element  (wrapper func )
	~ heapify  : Rebalance heap
		=> swap operation underneath
	peek : read the top Element
	size : 
  isEmpty : "use size underneath "
	add : 
	
	
PSEUDOCODE : 

	n = len(array)
	ptr1 = 0
	ptr2 = n-1
	
	# [1] construct the maxHeap
	for each index in range(1,n,1):
		compare A[i] to A[i/2]
		while A[i] > A[i/2] and i > 0
			swp(A,i,i/2)
			i = i/2
		
	# [2] Place max on RHS and sort subarrays
	len = n-1
	for i in range(1,n,1):
		max = A[0]
		swp(A,0,len)
		i = 0 		# 2 posssible children
		while(i*2 < n) :				# Be buggy here ( can go to left child ) 
			if A[i] < A[i*2]
				swp(A,i,i*2)
				i = i*2
			if A[i] < A[i*2 + 1]
				swp(A,i,i*2 + 1)
				i = i*2 + 1
		
	
	ret A
			
			
		

Sample Input
array = [8, 5, 2, 9, 5, 6, 3]

Sample Output
[2, 3, 5, 5, 6, 8, 9]

COMPLEXITY
Let N := #-elements in the array
N^2(insertion/selection),NlgN(merge), N*K(radix)
TIME = O(NlgN)							
SPACE = O(1) ( imp ) O(N) ( exp ) 

TEST CASES : 
(A) random
	[3,1,-2,5,4,9,7,0,99,5,4]
		( *** not passing ) 
(B) sorted
	[1,2,3,4,5]
(C) sorted rev order
	[5,4,3,2,1]
(D) [5,4,2,1,8,1]
	[1,1,2,4,5,8]			<= stability testing ( radix ) 
(E) [6,6,6,6,6]
	[6,6,6,6,6]
(F)  [8, 5, 2, 9, 5, 6, 3]
	( *** test case *** ) 
(G) [1]
(H)


Investigate #-swp,#-cmp operations





import java.util.*;

class Program {
  public static int[] heapSort(int[] A) 
	{
		if(A == null || A.length == 0)
			return new int[]{};
		
		int n = A.length;
		for(int i = 1; i < n; ++i)
		{
			while(A[i] > A[i/2] && i > 0)	// comparison in the while loop
			{
				swp(A,i,i/2);
				i = i/2;
			}
		}
		
		// for(int x : A)
			// System.out.printf("%d,", x);
		// System.out.println();
		
		int len = n-1;
		for(int i = 0; i < n; ++i)			// Possible bug too
		{
			swp(A,0,len);
			len -= 1;
			int k = 0;
			while(k*2 < len)
			{
				if(A[k] < A[k*2])
				{
					swp(A,k,k*2);
					k = k*2;
				}
				if(A[k] < A[k*2 + 1])
				{
					swp(A,k,k*2 + 1);
					k = k*2 + 1;
				}
				else
				{
					break;
				}
			}
			for(int x : A)
				System.out.printf("%d,", x);
			System.out.println();
		}		
		
		return A;
  }
	
	private static void swp(int[] A, int i, int j)
	{
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
	
}

