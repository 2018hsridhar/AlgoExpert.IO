/*												 
													 (        ) : len = 3 here
													 
Maybe O(N) storage of the encountered (min,max) also needed as well?
Dynamic data structures to incorporate
Element to look forward to : to the right OR to the left
Can also contemplate a stack as well
Can we induct / be greedy here as well?
Can we also try divide-and-conquer as well?
																		|
 0	1		2		3		4		5		6		7		8		9		10	11	12
[1,	2,	4,	7,	10,	11,	7,	12,	6,	7,	16,	18,	19]
 -----------------------^ 
 ---------  <=======================>  ----------

Arguement : if there is an subarray/subinterval to be sorted on the RHS -> we must include it's indices as well
We can not exclude it, as we perform one GLOBAL operation/update to the entirety of the array 
Do we have a strategy to grow on the left as well as the right?
														
Strategies : Nested Intervals, Recursion, DP, Two Pointers ( left,right meet-in-the-middle ) 
Can also grab the global minima and the global maxima
Can also consider the sliding window technique as well

Subarray to sort : NOT to reverse

Notice ; you are foretting negatives case here, as well as base case
TEST BENCH : 
(A) [5,4,3,2,1]
	[0,4]
(B)	[1,2,3,4,5]
	[-1,-1]
(C) [5,5,5,5,5]
	[-1,-1]
(D)	[1,10,2,9,3,8]
	[1,5]
(E) [1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19]	( *** provided *** )
	[3,9]
(F) [1,2]
	[-1,-1]
(G) [2,1]
	[0,1]
(H) [1, 2, 17, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19]
		 0	1		2	 3	4		5		6	 7	8		9	 10	11	12	13
	[2,11]
		Returning [2,10] here. Needs amending!
(I) [1,2,3,6,5,4,7,8,9]
	[3,5]
(J) [1,2,3,6,5,4,7,8,9,12,11,10,13,14,15]
	[3,11]
(K) [5,4,3,2,1,0,1,2,3,4,5]
	[0,9]
	
 0 1
[5,6] 
[6,5]

Complexity
Let N := len(array)
Time = O(N)
Space = O(1)


*/

import java.util.*;

class Program 
{
  public static int[] subarraySort(int[] array) 
	{
		int left_idx = -1;
		int right_idx = -1;
		int n = array.length;
		int initPtr = n-1;
		boolean foundPlusJump = false;
		while(initPtr > 0)
		{
			if(array[initPtr] >= array[initPtr-1])
			{
				--initPtr;
			}
			else
			{
				foundPlusJump = true;
				break;
			}
		}
		
		// You went thru the entirety case
		if(initPtr == 0)
		{
			return new int[]{-1,-1};
		}
		
		// Your initial jump discontinuity logic is a bit off : plz adjust right ptr here now
		// min diff of 1 here, at least ( the minimal guaranteed, if is exists ) 
		int right_ptr = initPtr;
		int left_ptr = initPtr-1;
		int rangeMinima = array[left_ptr];
		while(array[left_ptr] > array[right_ptr] && right_ptr < n-1)
		{
			rangeMinima = Math.min(rangeMinima, array[right_ptr]);
			++right_ptr;
		}
		rangeMinima = Math.min(rangeMinima, array[right_ptr]);
		if(array[right_ptr] > array[left_ptr])
		{
			--right_ptr;
		}
		int maxRight = array[right_ptr];
		
		// [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 2]	
		right_idx = right_ptr;
		left_idx = left_ptr;
		initPtr = left_ptr - 1;
		while(initPtr >= 0)
		{
			int nextLeftElem = array[initPtr];
			if(nextLeftElem <= rangeMinima)
			{
				rangeMinima = nextLeftElem;
				--initPtr;
			}
			else
			{
				// now ask if that new element is within the range of subinterval, or above range
				// It should bea  while loop though
				if(nextLeftElem > maxRight)
				{
					while(right_idx != n-1 && array[right_idx+1] < maxRight)
					{
						++right_idx;
						maxRight = array[right_idx];
					}
					if(left_idx > 0)
					{
						left_idx = initPtr;
					}
				}
				else if ( nextLeftElem > rangeMinima)
				{
					if(left_idx > 0)
					{
						left_idx = initPtr;
					}
				}
				--initPtr;
			}
			
		}
		
    return new int[] {left_idx,right_idx};
  }
}
