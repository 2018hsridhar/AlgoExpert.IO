import java.util.*;

class Program {
  public static boolean isMonotonic(int[] array) 
	{
		int n = array.length;
		int index = 0;
		boolean[] status = new boolean[]{true,true};
		boolean isMonotonic = recurse(array, status, index);
		return isMonotonic;
	}
	
	// Pass in index parameter for recursion
	// private scope : not exposed in the API
	// stauts[0] = nonIncr, status[1] = nonDecr
	// tail recursion :=> call-stack optimizable
	// parmaeter passing and func call overhead
	private static boolean recurse(int[] array, boolean[] status, int i)
	{
		int n = array.length;
		if(i >= n)			// stopping case ( >= for safety )
			return true;
		// [1] Work of parent
		if(i < n-1)	// boudns check 
		{
			if(array[i+1] > array[i])	// comparison operation
			{
				status[0] = false;				// update to boolean vars
			}
			if(array[i+1] < array[i])
			{
				status[1] = false;
			}
		}
		boolean isMonotonic = (status[0] || status[1]);
		if(isMonotonic == false)
		{
			return false;
		}
		return recurse(array, status, i + 1);
  }
}


Given an array of integers
Return if array if monotonic : strictly non-increasing order or non-decreasing ordered
Return true if either of the first two -> else, return false



array = [-1, -5, -10, -1100, -1100, -1101, -1102, -9001] -> true

Duplicates - yes : breaks strict property anyways
non-increasing : elements should not increase ( can be a plateau or can decrease )
non-decreasing : elements should not decrease ( can plateau or they can increase)

Adjacent element comparisons 
PSEUDOCODE : 

N length array : N-1 comparisons taking place between two adjacent elements

	// O(N) = T, O(1) = S
	bool nonIncr = true
	bool nonDecr = true
	for each ith_elem in the array
		check bounds and then compare ith to ith+1 elem
			if(a[ith+1] > a[ith])
				nonIncr = false
			if(a[ith+1] < a[ith])
				nonDecr = false
	
	isMonotonic = nonIncr || nonDecr
	ret isMonotonic
	
Truth table logic

nonIncr	nonDecr	isMonotonic
T					T				T
T					F				T							
F					T				T
F					F				F

COMPLEXITY
Let N := #-elements in the array input
TIME = O()
SPACE = O() ( EXP ) O() ( IMP ) 

TEST CASES :
(A) array = [-1] : TRUE
(B) array = [-1,0,-1]
	=> FALSE
(C) array = []
	TRUE
(D) [0,0,0,0,0,0,0]
	TRUE
(E) [1,2,3,4,5,6]
	TRUE
(F) [6,5,4,3,2]
	TRUE
(G) [1,0,1,0,1] 
	Alternating sequence of elements
	FALSE
(H) [1,2,3,4,3,2,1] ( *** peak *** ) 
	FALSE
(I) [4,3,2,1,2,3,4] ( *** valley *** ) 
	FALSE
(J) [1,2,3,4,5,6,5,4,3,2,1,1,2,3,4,5,6,5,4,3,2,1,1,2,3,4,5,6,5,4,3,2,1,1,2,3,4,5,6,5,4,3,2,1,1,2,3,4,5,6,5,4,3,2,1,1,2,3,4,5,6,5,4,3,2,1,1,2,3,4,5,6,5,4,3,2,1,1,2,3,4,5,6,5,4,3,2,1,1,2,3,4,5,6,5,4,3,2,1,1,2,3,4,5,6,5,4,3,2,1]
	large input case
Does array fit in RAM/memory? Assume it



