Given a non empty array of integers
Return the max sum obtainable by summing up all integers in a non-empty subarray of the input array

array = [3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4]

19 // [1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1]

PSEUDOCODE : 

	maxSum = MIN_VALUE;
	runSum = 0
	for each element in the array
			runSum += elem
			maxSum = max(maxSum, runSum)
			if(runSum <= 0)
				runSum = 0
	return maxSum

COMPLEXITY
Let N := # elements in the input array
TIME = O(N)
SPACE = O(1)
Greedy algorithm : solves for smaller subproblems at each step

Can have duplicate input

TEST CASES
Array always non-empty
(A) [1,4,7,11]
	=> take entire sum up to 23
(B) [1,4,7,-1]
	=> 12
(C) [1,2,5,9,12,-1]	
	Negative as the last : but all else is good here
(D) [1,4,7,-2,1000]
	1+4+7 = 12 - 2 = 10 + 1000	( have a dip : not zeroed out here or negative )
(E) [1,4,7,-100,1000]
	1+4+7 = 12 - 100 = -88 ( +1000 ) : brings us too low ( have a dip : massive decrement )
(F) [1,2,3,-6,5,7]
		 --------
		 0
		 1+2+3+-6+5+7 = 5 + 7 = 12
(G) [1,2,3,-6,0,0,5,7]
(H) [-6,-4,-7,-1,-2]
	-1 ( subarray at idx = 3 )
(I) [1]
(J) [-1]

import java.util.*;

class Program {
  public static int kadanesAlgorithm(int[] array) 
	{
		int maxSum = Integer.MIN_VALUE;
		int runSum = 0;
		for(int i = 0; i < array.length; ++i)
		{
			runSum += array[i];
			maxSum = Math.max(maxSum, runSum);
			runSum = Math.max(0, runSum);
		}			
		return maxSum;
  }
}

