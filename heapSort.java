
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
	

Maxheap remains on the left-hand side here
[9,8,6,2,1,5,3]
							|
[3,8,6,2,1,5,9] 
	          |




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
		
		/*
		  [1] Max Heap construction : based on child->parent swaps EVERYWHERE!
		  [2] Like heap sort to making smaller and smaller heaps each time, after extraction of the topmost element too
		  [3] Incurs implicit rebalancing steps as well
		*/
		int n = A.length;
		for(int i = 1; i < n; ++i)
		{
			int j = i;
			while(j > 0)	// Special case handle for j = 1 though? 
			{
				if(j % 2 == 0 && A[(j-2)/2] < A[j])	// comparison in the while loop
				{
					swp(A,j,(j-2)/2);
					j = (j-2)/2;	// same loop var use OMG
				}
				else if(j % 2 == 1 && A[(j-1)/2] < A[j])	// comparison in the while loop
				{
					swp(A,j,(j-1)/2);
					j = (j-1)/2;	// same loop var use OMG
				}
				else
				{
					break;
				}
			}
		}
		
		
		
		//		     4             10 ( indices 4, 4*2 + 2 :: off ) 
		// 9,8,5,4,1,4,1,0,-1,-2,3,-3,-3,-3,-7,-4,-9,-9,-7,-4,-9,-7,
		// Heap construction here : DEFINITELY BUGGY -> please fix again!
			
		// for(int x : A)
		// 	System.out.printf("%d,", x);
		// System.out.println();
		
		int len = n-1;
		for(int i = 0; i < n; ++i)			// Possible bug too
		{

			swp(A,0,len);	// Wait is this correct
			
				
			// Bounds ref can not be right here either
			// 0 -> (1,2) : 2*k+1, 2*k + 2  ( exception case ) 
			// 1 -> (3,4) : 
			// 2 -> (5,6) :
			len -= 1;
			int k = 0;
			// Case : if left than both left and right -> must take the max of both here!!!! 
			// Recursive swapping ( level-by-level makes most sense ) 
			while(k < len)
			{
				// I see -> not evaluating a max properly here
				// You need to swap with the right children ( the maximal child -> greedily ) : 2->{3,5} on {left,right} here!
				if((2*k)+1 <= len && (2*k)+2 <= len && A[k] < A[(2*k)+1] && A[k] < A[(2*k)+2])
				{
					// check which child lower : the left or the right
					int lower = 2*k + 1;
					if(A[(2*k)+2] > A[lower])
					{
						lower = 2*k + 2;
					}
					swp(A,k,lower);
					k = lower;
				}
				// Handle one-child cases as well too!
				else if((2*k)+1 <= len && A[k] < A[(2*k)+1])
				{
					swp(A,k,(2*k)+1);
					k = (2*k)+1;
				}
				else if((2*k)+2 <= len && A[k] < A[(2*k)+2])
				{
					swp(A,k,(2*k)+2);
					k = (2*k)+2;
				}
				else
				{
					break;
				}
			}
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
