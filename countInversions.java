Goal : Count the number of inversions in an array
Solve for indices (i,j) s.t. A[i] > A[j]

Clarify input : 
	- integer array
	- never null/empty : well-formed => at least one element
	- can have duplicate elements too
	- original ordering of elements matters ( or not ) 
	- sort the array ( nlgn - merge/quick sort )  
	- dupl case : must abide by strict inequality of '<' here
	- sorted property of the dynamic list may be of assistance
	
 0	1	 2	3
[3, 4, 1, 2] -> unsorted
 
 	  ^
 {3} -> dynamic list is to the left here
 Exploration @ idx = 0 : idx = {1,2,3} := { 4, 1, 2 }
 {1, 2, 4} -> 3 is > two elements ( 1,2 ) here
 	a[mid] to a[mid-1] comparison
	Boundary cases : mid = 0, or mid = final idx too
	
 
 
[1, 2, 3, 4] -> sorted			( see a number of values less than you ) 
	second array?

First elem : #-elements < you guaranteed to be right of you

						
						
						
[3, 2, 1, 0]								( see number indices greater than me ) 
	
	
 ---> iterate and go left-to-right?
 ---> storing additional data?
 

{2,3}
[2, 3, 0, _]
{1,2,3} = elements
{0,2,3} = indices



Come to 4 ( at index = 1 )
	1 is inbetween (0,2) 
	2 valus to my right here
	
Strategies : 2 ptrs, DP, Recursion, bsearch ( target inbetween two elements -> not 
in actual dynamic listing ) , divide-and-conquer

Maybe use a heap ( sort property ) 
Binary search in a dynamic array ( of indices ) 
	- sorted dynamic list
Use a hashmap ( map values -> indices OR indices -> values ) 
{
	3=>{0}
	1=>2,
	4=>1
	2=>3,
}

i < j and array[i] > array[j]
[0, 2], [0, 3], [1, 2], [1, 3]
Output: 4

Naive brute-force algorithm
	Pairwise elemnet comparison : (i,j) where j in range (i+1,n)

COMPLEXITY ( Naive ) 
Let N := #-elements in array/length
TIME = O(N^2)
SPACE = O(1)

BETTER
TIME = O(N) OR O(NLGN)
SPACE = O(1) or O(N) 

Data structure : 


TEST CASES
(A) [1,1,1,1,1]
	=> 0
(B) [1]
	=> 0
(C) [1,2,3,4]
	=> 0
(D) [4,3,2,1]
	=> 6
(E) [4,3,1,2,3,5,2,4,1]
							 -------
				 =============			 
(F)
(G)


import java.util.*;

class Program {

  public int countInversions(int[] array) 
	{
		int numInversions = 0;
		if(array.length == 1)
		{
			return 0;
		}
		ArrayList<Integer> dynamic = new ArrayList<Integer>();
		int n = array.length;
		dynamic.add(array[n-1]);
		for(int i = n-2; i >= 0; --i )
		{
			int elem = array[i];
			int numInverts = bSearch(elem, dynamic);
			System.out.printf("For elem = [%d] \t numInverts = [%d]\n", elem, numInverts);
			System.out.printf("%s\n", dynamic.toString());
			numInversions += numInverts;
		}
		return numInversions;
  }
	
	// {1,3,4} = dynamic list and insert(2) => @ index 0, val = 1
	public int bSearch(int elem, ArrayList<Integer> dynamic)
	{
		// Check 0-indexing as well ( for #-elements less than you ) 
		int numLess = 0;
		int low = 0;
		int high = dynamic.size() - 1;
		while(low <= high)
		{
			int mid = (low + high)/2;
			if ( dynamic.get(mid) == elem)	// duplicates case ( top of the range here -> special handling )
			{
				// amortize to O(nlgn)
				while(mid+1 < dynamic.size() && dynamic.get(mid+1) == elem)
				{
					mid += 1;
				}
				dynamic.add(mid + 1,elem);	// IOOB exception
				return mid + 1;	// be a bug too?
			}
			else if ( dynamic.get(mid) < elem )
			{
				// System.out.printf("Getting mid < elem\n");
				if(mid <= high-1 && elem < dynamic.get(mid+1))	// adjancey check ( idx, idx + 1)
				{
					// set(int index, E element)
					dynamic.add(mid + 1,elem);
					return mid + 1;
				}
				else if ( mid == high)	// doable by default
				{
					dynamic.add(elem);	// special case
					return mid + 1;
				}
				else
				{
					low = mid + 1;
				}
			}
			else if ( dynamic.get(mid) > elem)
			{
				high = mid - 1;
			}
		}
		return numLess;
	}
	
	
	
	
	
}



