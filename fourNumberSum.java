/*
THOUGHT PROCESS
- Ordering of the quadruplets results should NOT matter here
- Array is non-empty and integers are guaranteed DISTINCTNESS
- Return a 2D array as well
- Indices must differ as well ( as they are also distinct integers ) 
-
-


COMPLEXITY
Let N := len(array input)
Due to a lucky O(NLogN), O(1) in-place merge sort earlier
Item			Brute-Force			Better											Current
Time = 		O(N^4)					O(N^3) or O(N^2) or O(N)		O(N^2)
Space = 	O(1)						O(N) or O(N^2) or more			O(1)



TEST BENCH : 
(A) [], [2,7], [1,2,3] -> always return an empty array
(B) [7, 6, 4, -1, 1, 2] targetSum = 16
	[[7,6,4,-1],[7,6,1,2]]
(C) []
(D) []
E() []

We luck out when return type is values, and NOT, indices here as well
It entails an ability to sort as well

*/
import java.util.*;

class Program 
{
  public static List<Integer[]> fourNumberSum(int[] array, int targetSum) 
	{
		List<Integer[]> quadruplets = new ArrayList<Integer[]>();
		// Apply an existing Arrays.sort operation already
		Arrays.sort(array);
		if(array == null)
			return null;
		quadruplets = optimalSol(array, targetSum);
		return quadruplets;
  }
	
	private static List<Integer[]> optimalSol(int[] A, int targetSum)
	{
		List<Integer[]> quadruplets = new ArrayList<Integer[]>();
		int n = A.length;
		for(int i = 0; i < n; ++i)
		{
			// O(N) from here
			for(int j = i+1; j < n; ++j)
			{
				int leftSum = A[i] + A[j];
				int subSum = targetSum - leftSum;
				// Execute the 2SUM algorithm here
				int low = j+1;
				int high = n-1;
				int leftPtr = low;
				int rightPtr = high;
				while(leftPtr < rightPtr)	// halt at equality or >, due to two distinct values property
				{
					int curSum = A[leftPtr] + A[rightPtr];
					if(curSum == subSum)
					{
						Integer[] quad = new Integer[]{ A[i],A[j], A[leftPtr], A[rightPtr] };
						quadruplets.add(quad);
						++leftPtr;
						--rightPtr;
					}
					else if ( curSum < subSum )
					{
						++leftPtr;
					}
					else
					{
						--rightPtr;
					}
				}
			}
		}
		return quadruplets;
	}
}

import java.util.*;

class Program {
  public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
    // Write your code here.
    return new ArrayList<Integer[]>();
  }
	
		// Check this about nesting : is it really starting as a {0,1,2,3} sequence here>
	private static void bruteForceSol(int[] A, int targetSum, List<Integer[]> quadruplets)
	{
		int n = A.length;
		for(int i = 0; i < n; ++i)
		{
			for(int j = i+1; j < n; ++j)
			{
				for(int k = j+1; k < n; ++k)
				{
					for(int l = k+1; l < n; ++l)
					{
						int curSum = A[i] + A[j] + A[k] + A[l];
						if(curSum == targetSum)
						{
							Integer[] nextQuad = new Integer[4];
							nextQuad[0] = A[i];
							nextQuad[1] = A[j];
							nextQuad[2] = A[k];
							nextQuad[3] = A[l];
							quadruplets.add(nextQuad);
						}
					}
				}
			}
		}
	}
	
