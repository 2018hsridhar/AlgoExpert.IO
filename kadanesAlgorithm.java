/*

THOUGHT PROCESS : 

COMPLEXITY
Let N := len(array)
Time = 
Space =

TEST BENCH
(A) [1,2,3,4,5]
(B) [-1,-2,-3,-4,-5]
(C) [-1,-2,-3,-4,0]
(D) [1,2,3,-2,5,6,-7,8]
(E) [1,2,4,-8,1]
(F) [1,2,4,-8,1,2,100]
(G) [1,2,4,-5,1,2,10,-10,20]

*/
import java.util.*;

class Program 
{
  public static int kadanesAlgorithm(int[] array) 
	{
		if(array == null || array.length == 0)
			return 0;
		
		int maxSum = Integer.MIN_VALUE;
		int runningSum = 0;
		int n = array.length;
		for(int i = 0; i < n; ++i)
		{
			runningSum += array[i];
			// Single standalone element test anyways ( seems taken care of by initial running sum )?
			if(array[i] > maxSum)
				maxSum = array[i];
			if(runningSum > maxSum)
				maxSum = runningSum;
			if(runningSum < 0)
				runningSum = 0;
		}
		
		
		return maxSum;
  }
}
