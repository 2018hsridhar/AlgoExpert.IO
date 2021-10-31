Given an array of integers, and it is a sorted array
All values are guaranteed uniqueness ( no duplicates exist ) 
Return an index where index = value ( that is, A[index] = index )
Also return the FIRST case ( not later cases : 3 & 3, 4 & 4 ) 

Input clarifications
(1) No lower/upper bound on inputs : can be (-) or (+)
(2) Can have an empty len ( return -1 ) 
(3) If no A[i] = i : return -1

  0		1	 2	3	 4	5	 6
[-5, -3, 0, 3, 4, 5, 9]

  0	 1	2  3	4  5	6
[ 0, 1, 2, 3, 4, 5, 6 ]

Brute Force Solution : linear search
Let N := length(array)
Time = O(N)
Space = O(1)

TEST BENCH : 
(A) [-5, -3, 0, 3, 4, 5, 9]
(B) [ 0, 1, 2, 3, 4, 5, 6 ]	-> stop at first element
(C) [-1, 0, 1, 2, 3, 4, 6]	-> go all the way to the end for our assertion
	*** example worst case for binary search ***
(D) [1], [0] 
(E) []
(F) [ 0, 1, 2, 4, 5, 6, 7 ]	-> chuck out RHS
				0
(G) [ -1, 0, 1, 2, 4, 5, 6 ]	-> chuck out LHS
				4
(H) [0,2] [1,2]
				
				
				
Do better : 
Binary Search ( divide-and-conquer strategy )
Time = O(LogN)
Space = O(1) ( explicit ) O(H) ( implicit) ( best case - logN, worst case - N )

public int indexEqualsValue(int[] array)
{
	int targetIdx = -1;
	int n = array.length;
	for(int i = 0; i < n; ++i)
	{
		if(array[i] == i)
		{
			targetIdx = i;
			break;
		}
	}
	return targetIdx;
}

  0	 1	2  3	4  5	6
[ 0, 1, 2, 3, 4, 5, 6 ]
					 |
		 |			 --->
		 
  0	 1	2  3	4  5	6
[ -1, 0, 1, 3, 4, 5, 6 ]
				 ^  &
				 
				 
Recursive ( backtracking ) 					 
	mid = low + (high-low)/2;
					 
	low		high	mid		a[mid]
	0			6			3			3
	0			2			1			1
	0			1			0			0
	
	Gap of 1 ( are unique )
	  0	 1	2  3	4  5	6
	[ 0, 1, 2, 4, _, _, _ ]	-> chuck out RHS
	[ _, _, _, 2, 4, 5, 6 ]	-> chuck out LHS
	
	
private int binarySearch(int[] array, int low, int high)
{
	if(low > high)
	{
		return -1;
	}
	else
	{
		int mid = low + (high-low)/2;
		int val = array[mid];
		if(mid == val)
		{
			int initIndex = mid;
			int leftIndex = binarySearch(array, low, mid - 1);
			if(leftIndex != -1)
			{
				initIndex = leftIndex;
			}
			return initIndex;
		}
		else if ( mid < val)
		{
			return binarySearch(array, low, mid - 1);
		}
		else
		{
			return binarySearch(array, mid + 1, high);
		}
	}
}
	
https://www.linkedin.com/in/harisrid/

Reason about space : explicit space - implicit / function call stack space ( recursive algorithms )


import java.util.*;

class Program {
  public int indexEqualsValue(int[] array) 
	{
		int low = 0;
		int high = array.length - 1;
		// int index = binarySearch(array,low,high);
		int index = iterativeBinarySearch(array);
		return index;
  }
	
	private int iterativeBinarySearch(int[] array)
	{
		int low = 0;
		int high = array.length - 1;
		int initIndex = -1;
		while(low <= high)
		{
			int mid = low + (high-low)/2;
			int val = array[mid];
			if(mid <= val)
			{
				if(mid == val && initIndex != mid)
				{
					if(mid > 0 && array[mid-1] < mid-1)
						return mid;
					initIndex = mid;
				}
				high = mid - 1;
			}
			else
			{
				low = mid + 1;
			}
		}
		return initIndex;
	}
	
	private int binarySearch(int[] array, int low, int high)
	{
		if(low > high)
		{
			return -1;
		}
		else
		{
			int mid = low + (high-low)/2;
			int val = array[mid];
			if(mid == val)
			{
				int initIndex = mid;
				int leftIndex = binarySearch(array, low, mid - 1);
				if(leftIndex != -1)
				{
					initIndex = leftIndex;
				}
				return initIndex;
			}
			else if ( mid < val)
			{
				return binarySearch(array, low, mid - 1);
			}
			else
			{
				return binarySearch(array, mid + 1, high);
			}
		}
	}
}


	
