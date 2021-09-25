/*

Brute Force Solution : 
Let M := number of rows in the matrix
Let N := number of cols in the matrix

Evaluate each element and perform element-by-element comparison
if target value is in the matrix OR not
If in matrix, return that pair
Otherwise, return {-1,-1}

Complexity : 
RunTime = O(MN) [Polynomial - Quadratic ]
Space = O(1)

Denoting a column matrix as if it were typed as a row matrix, via {} closing brackets
Edge Case Testing : 
(A) [1] target = 1, target = 0
(B) [1,3,6,8,9]  targets = {-1,1,3,5,9,10} : search within bounds is a good way!
(C) {}
(D) [2,3
		6,7]		Square Matrix
(E) [2,3,6
		6,7,10]		Rectangular Matrix
(F)
(G)


*/

// Solution 1 - The Brute Force Case
import java.util.*;

class Program
{
  public static int[] searchInSortedMatrix(int[][] matrix, int target) 
  {
	int m = matrix.length;
	int n = matrix[0].length;
	for(int i = 0; i < m; ++i)
	{
		for(int j = 0; j < n; ++j)
		{
			if(matrix[i][j] == target)
			{
				return new int[]{i,j};
			}
		}
	}
	return new int[] {-1, -1};
  }
}
