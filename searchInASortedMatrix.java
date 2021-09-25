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

/*

Brute Force Solution : 
Let M := number of rows in the matrix
Let N := number of cols in the matrix

Evaluate each element and perform element-by-element comparison
if target value is in the matrix OR not
If in matrix, return that pair
Otherwise, return {-1,-1}

Complexity : 
RunTime = O(MlogN) + O(NlogM) [Polynomial - Logarithmic as well ]
Space = O(1)

Denoting a column matrix as if it were typed as a row matrix, via {} closing brackets
Edge Case Testing : 
(A) [1] target = 1, target = 0
(B) [1,3,6,8,9]  targets = {-1,1,3,5,9,10} : search within bounds is a good way!
(C) {}
(D) [2,3
		6,7]		Square Matrix
(E) [
			[2,3,6],
			[6,7,10]		Rectangular Matrix
		]
(F)
(G)

Pass in indices for the binary search as well
We know the row information and column bounds already -> will be quick here
Iterative bSearch preffered to avoid implicit recursive call stack space

*/


import java.util.*;

class Program 
{
	/*
	 Getting individual columns IS easy -> not individual rows -> in JAVA.
		Modularize this design : seperate binary search from global sorting
	 1. rowBSearch and colBSearch
	 	- argue for this as we want EXTENSIBILITY in our functions/API
		- and make it easier to NOT break any future deltas/changes to the codebase
	 2. We construct these array objects, and just pass those in
   https://stackoverflow.com/questions/11076338/get-the-rows-and-columns-from-a-2d-array-matrix-in-java	
	 3. BRING UP CYCLOMATIC CONSISTENCY with following interviee!
	 
	*/	
	public static int bSearchRow(int[][] A, int target, int row)
	{
		int low = 0;
		// int high = A.length - 1;
		int high = A[0].length - 1;
		while(low <= high)
		{
			int mid = low + (high-low)/2;
			if(A[row][mid] == target)
				return mid;
			else if ( A[row][mid] > target ) 
			{
				high = mid - 1;
			}
			else
			{
				low = mid + 1;				
			}
		}
		return -1;
	}
	
	// You bsearch each col, by bounded by max # rows
	public static int bSearchCol(int[][] A, int target, int col)
	{
		int low = 0;
		// int high = A[0].length - 1;
		int high = A.length - 1;
		while(low <= high)
		{
			int mid = low + (high-low)/2;
			if(A[mid][col] == target)
				return mid;
			else if ( A[mid][col] > target ) 
			{
				high = mid - 1;
			}
			else
			{
				low = mid + 1;				
			}
		}
		return -1;
	}
	
	
  public static int[] searchInSortedMatrix(int[][] matrix, int target) 
	{
		int m = matrix.length;
		int n = matrix[0].length;
		int[] pairHit = new int[]{-1,-1};

		// Binary search each row
		for(int i = 0; i < m; ++i)
		{
			int targetIdx = bSearchRow(matrix, target, i);			
			if(targetIdx != -1)
			{
				pairHit[0] = i;
				pairHit[1] = targetIdx;
				break;
			}
		}
	
		// Binary search each column
		for(int j = 0; j < n; ++j)
		{
			int targetIdx = bSearchCol(matrix, target, j);
			if(targetIdx != -1)
			{
				pairHit[0] = targetIdx;
				pairHit[1] = j;
				break;
			}
		}

		return pairHit;
  }
}

