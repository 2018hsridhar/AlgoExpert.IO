/*
Function takes in two non-empty arrays of integers
	-> presume ints can be duplicated AND any value as well
Find the unique pair ( or at least, a pair ) with ABSOLUTE difference closest to zero
	-> Potential for a priority queue approach
Return array containing these two numbers
	[first_arr_val, second_arr_val]

Expect a trip up with dist function : (x-y) can go two ways if y < 0 OR y > 0
Assume we are always provided a solution as well ( it exists ) 

Complexity : 
Let M := len(arrayOne), N := len(arrayTwo)
Time = O(MN) for each MN pairs here
Space = O(1)

More efficient
Consider a sort function here?
{} = performance for both merge sort operations
Time = {O(NlogN) +O(MlogM)} + O(N+M)
Space = O(N+M) + {O(M) + O(N)}

Time = MAX{O(NlogN), O(MlogM)} + O(N+M)
Space = O(1) [ assume merge sort is in-place here! ] 

Edge cases : 
(A)
(B)
(C)
(D)
(E)
(F)

A = [-1,3,5,10,20,28]
B =			 [4,17,   26,134,135]

{4,5}
{26,28}
{20,17}
are incredibly close pairs here. 
What if we combined both outputs ( e.g. a merge sort operation )?
C = merge(A,B) = [-1,3,4,5,10,17,20,26,28,134,135] => merge via a 2 ptr based approach and allocate space of O(M+N) here
And then we got the min difference between a monotonically non-decreasing array!
We can kinda be "greedy" here I guess. We know that given (a,b,c), min_diff(a,b,c) = min(diff(a,b),diff(b,c))


Can we pair it up in this manner ( performs binary searching here ) ?

What if given a case such as the following instead:
A = [1,2,3,4,5]
B =				[7,8,9,10,11]

Since max(A) < min(B), min_diff = min(B) - max(A) = 7 - 5 = 2
Same goes for a case such as this : 
A = [1,2,3,4,5]
B =				[-5,-3,-2,0,1]
Since max(B) <= min(A), min_diff = min(B) - max(A) = 0


*/

import java.util.*;

class Program {
  public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) 
	{
		Arrays.sort(arrayOne);
		Arrays.sort(arrayTwo);
		if(arrayOne == null || arrayTwo == null)
    	return new int[] {};
		int[] minDiffPair = new int[2];
		int minDist = Integer.MAX_VALUE;
		int m = arrayOne.length;
		int n = arrayTwo.length;
		int ptr1 = 0;
		int ptr2 = 0;
		int wIdx = 0;
		int[] merged = new int[m+n];
		boolean[] inArrOne = new boolean[m+n]; 
		while(ptr1 < m && ptr2 < n)
		{
			int val_one = arrayOne[ptr1];
			int val_two = arrayTwo[ptr2];
			if(val_one <= val_two)
			{
				merged[wIdx++] = arrayOne[ptr1++];
				inArrOne[wIdx-1] = true;
			}
			else			
			{
				merged[wIdx++] = arrayTwo[ptr2++];
				inArrOne[wIdx-1] = false;
			}
			}
		while(ptr1 < m)
		{
			merged[wIdx++] = arrayOne[ptr1++];
			inArrOne[wIdx-1] = true;
		}
		while(ptr2 < n)
		{
			merged[wIdx++] = arrayTwo[ptr2++];
			inArrOne[wIdx-1] = false;
		}
		
		// Numbers must match up to their ORIGINAL positinos in the array as well ... DANG!
		
		for(int i = 0; i < merged.length-1; ++i)
		{
			int absDist = getAbsoluteDist(merged[i], merged[i+1]);
			// System.out.printf("For (%d,%d), absDist = %d \t minDist = %d\n", merged[i], merged[i+1], absDist, minDist);
			// System.out.printf("For (%d,%d), inArrOne = (%s,%s)\n", merged[i], merged[i+1], inArrOne[i], inArrOne[i+1]);
			if(absDist < minDist)
			{
				if(inArrOne[i] == true && inArrOne[i+1] == false)
				{
					minDiffPair[0] = merged[i];
					minDiffPair[1] = merged[i+1];
					minDist = absDist;
				}
				else if (inArrOne[i+1] && !inArrOne[i])
				{
					minDiffPair[0] = merged[i+1];
					minDiffPair[1] = merged[i];
					minDist = absDist;
				}
			}
		}

		return minDiffPair;
  }
	
	public static int getAbsoluteDist(int i, int j)
	{
		int absDist = 0;
		if((i < 0 && j < 0) || (i > 0 && j > 0))
			absDist = (int)Math.abs((int)Math.abs(i) - (int)Math.abs(j));
		else if ( i == 0 )
			absDist = (int)Math.abs(j);
		else if ( j == 0)
			absDist = (int)Math.abs(i);
		else
			absDist = (int)Math.abs((int)Math.abs(i) + (int)Math.abs(j));
		return absDist;
	}
	
}
