/*
Note : if size exceeds either dimension -> what to do there as well!
If it exceeds both -> we just sum up the entire input anyways!

Yes we can assume safely that size <= min(height, width) here, and is >= 1 as well!
Let us reduce a 2D problem to a 1D problem instead
Let us get a solution avoidant of priority queues here
Account for case where all inputs are negatives, or worst, close to INT_MINs ( gotcha ) 


COMPLEXITY
Let M,N := #-rows, #-cols in the matrix
Let S := size dimension
TIME = O(MN)
SPACE = O(M)
Your approach beats Clement's :-) Yeah prefix summing code!

Better : O(MN) time, O(MN) space. Take note -> more space for less time here!
You did get brute force being O(MN*S^2) as well

TEST CASES
(A)
(B)
(C)
(D)
(E)
(F)

*/
import java.util.*;

class Program 
{

  public int maximumSumSubmatrix(int[][] matrix, int size) 
	{
    int maxSum = Integer.MIN_VALUE;
		int m = matrix.length;
		int n = matrix[0].length;
		int k = size;
		// [1] PRECOMPUTATION STAGE
		int[] rowSums = new int[m];
		// O(M*N) risk, but O(M*k)
		for(int i = 0; i < m; ++i)
		{
			int rowSum = 0;
			// O(k)
			for(int j = 0; j < size; ++j)
			{
				rowSum += matrix[i][j];
			}
			rowSums[i] = rowSum;
		}
		
		// [2] SLIDING WINDOW STAGE
		int curCol = size; // if size = 2, curCol = 2 ( 1 indexing here ) 
		// O(N)
		while(curCol != n+1)
		{
			// compute current (kxk) matrices
			// O(M) twice
			int kSum = 0;
			for(int i = 0; i < k; ++i)
			{
				kSum += rowSums[i];
			}
			maxSum = Math.max(maxSum, kSum);
			for(int i = k; i < m; ++i)
			{
				kSum += rowSums[i];
				kSum -= rowSums[i-k];
				maxSum = Math.max(maxSum, kSum);
			}
			
			// perform 1D shift on the rowSums array
			// Check we do not read past array bounds as well
			int shiftCol = curCol;
			if(shiftCol == n)
			{
				break;	// early terminate here
			}
			for(int i = 0; i < m; ++i)
			{
				rowSums[i] += matrix[i][shiftCol];
				rowSums[i] -= matrix[i][curCol - size];
			}
			
			++curCol; // incr only after computations wrap up
		}
		
    return maxSum;
  }
}
