Goal : write a function that takes in a (nxn) 2 dimensional square matrix containing only 1's and 0's
Return a boolean representing whether input matrix contains a square of border made up of ONLY zeroes.

Caveat : 1x1 matrix is an INVALID matrix. Single {0} is not valid
Not an optimization problem : existence problem 

Input clarification : 
1. Any null, empty, or single elem matrices -> n >= 3 here
Within the border : can have 0's or 1's -> does not matter
Border must be a square too!
2. No tight upper bound in inputs 
3. Not an array : is a list<list<>> ... use .get()() OR convert to a 2D array

Problem solving strategies : 
	Iteration, BFS/DFS
	
	Don't want to overwrite, but need a visited set
	Take min of #-consecutive 0's to your right and to your bottom
	Take min of #-consecutive 0's to your left and to your top
	At {1}, do not eval a count at all
	
Border size : (3,n)

TEST CASES : 

(A) an invalid case

	matrix = [
		[0,0],
		[0,0]
	]
	
(B) 

	matrix = [
		[0,0,0],
		[0,0,0],
		[0,0,0]		
	]

matrix = [
		[0,0,0],
		[0,_,0],
		[0,0,0]		
	]

(C)


matrix = [
  [1, 1, 1, 0, 1, 0],
  [0, 0, 0, 0, 0, 1],
  [0, 1, 1, 1, 0, 1],
  [0, 0, 0, 1, 0, 1],
  [0, 1, 1, 1, 0, 1],
  [0, 0, 0, 0, 0, 1],
]

Answer = true


(D)
matrix = [
  [1, 1, 1, 0, 0, 0],
  [_, 0, 0, R, R, 1],
  [0, 1, 0, 0, 0, 1],
  [0, B, 0, BR,0, 1],
  [0, 1, 1, 0, 0, 1],
  [0, 0, 0, 0, 0, 1],
]

															offsets
  [_, 0, 0, 0, R, 1], => [_,1,2,3,4,5]
															-----
															
At the "_" element, we have two zeroes to right, 4 zeroes to bottom
Longest border formable from the "_" elem is of length = 2 , at best ( len = 1, or another length )
If we do get the bottom and right -> we now the bottom-right corner
count(to_left) >= diff(BR.x - B.x) : we can form a bottom border
count(to_top) >= diff(BR.y - R.y) : we can form a right border

Iterate from 2 to min(count(R),count(B))
	Check each index for it's validity as well






COMPLEXITY 
Let N be dimensions of the square matrix
Precomputation : O(4*n^2) = O(n^2)
Time = O(N^2*(K)) where K = min(0's to right, 0's to bottom ) but K in worst case can = N
	O(N^3)
	O(N^3) + O(N^2) = O(N^3) ( POLY - Cubic ) 
Space = O(4*N^2) ( explicit ) O(1) ( implicit ) 


Better : O(N^2) or O(N^2*logN) or O(N^2 * sqrt(N))

PSEUDOCODE : 

	// 1 Precomputation
	n = len(matrix)
	int[n][n] zeroesLeft;
	int[n][n] zeroesRight;
	int[n][n] zeroesBottom;
	int[n][n] zeroesTop;
	
	int leftCounter = 0;
	int rightCounter = 0;
	int topCounter = 0;
	int bottomCounter = 0;
	
	// Left counters 
	for i in [0,n-1]
		zeroesLeft[i][0] = 0
		for j in [1,n-1]
			if(matrix[i][j-1] == 0)
				leftCounter++;
			if(matrix[i][j] == 1)
				leftCounter = 0;
			zeroesLeft[i][j] = leftCounter;
	
	// Right counters 
	for i in [0,n-1]
		zeroesLeft[i][n-1] = 0
		for j in [n-2,0]
			if(matrix[i][j-1] == 0)
				rightCounter++;
			if(matrix[i][j] == 1)
				rightCounter = 0;
			zeroesRight[i][j] = rightCounter;
			
	// Top counters 
	for j in [0,n]
		zeroesTop[0][j] = 0
		for i in [1,n-1]
			if(matrix[i-1][j] == 0)
				topCounter++;
			if(matrix[i][j] == 1)
				topCounter = 0;
			zeroesTop[i][j] = topCounter;
			
	// Bottom counters 
	for j in [0,n]
		zeroesTop[0][j] = 0
		for i in [1,n-1]
			if(matrix[i-1][j] == 0)
				topCounter++;
			if(matrix[i][j] == 1)
				topCounter = 0;
			zeroesTop[i][j] = topCounter;			
	
	
// [0,0,0,0,1,1,0,1,0,0] => [0,1,2,3,0,0,0,0,0,1]	
	
	// 2 Actual algorithm
	// May be able to halve amount of storage 
	// Check for Index OOB run-time exceptions
	
		for i in [0,n]
			for j in [0,n]
				int offsetRB = Math.min(zeroesBottom[i][j], zeroesRight[i][j]);
				int rb_i = i + offsetRB
				int rb_j = j + offsetRB
				for(int k = 2; k <= offsetRB; k++ )
					int offsetTL = Math.min(zeroesLeft[rb_i][rb_j], zeroesTop[rb_i][rb_j])
					if(offsetTL >= offsetRB)
					{
						return true
					}
					
					offSetTL < offsetRB
					
		return false
	
	
	

import java.util.*;

class Program {

	private static void convertListToArray(List<List<Integer>> matrix, int[][] input)
	{
		int n = matrix.size();
		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				input[i][j] = matrix.get(i).get(j);
			}
		}
	}
	
	public static boolean squareOfZeroes(List<List<Integer>> matrix) 
	{
		// [1] Precomputation steps
		int n = matrix.size();
		int[][] input = new int[n][n];
		convertListToArray(matrix, input);
		
		int[][] zeroesLeft = new int[n][n];
		int[][] zeroesRight = new int[n][n];
		int[][] zeroesBottom = new int[n][n];
		int[][] zeroesTop = new int[n][n];

		fillLeftZeroes(input, zeroesLeft);
		fillRightZeroes(input, zeroesRight);
		fillBottomZeroes(input, zeroesBottom);
		fillTopZeroes(input, zeroesTop);
		
		boolean res = checkForZeroBorder(zeroesLeft, zeroesRight, zeroesTop, zeroesBottom);
		return res;
		
	}
	
	private static boolean checkForZeroBorder(int[][] left, int[][] right, int[][] top, int[][] bottom)
	{
		int n = left.length;
		for(int i = 0; i < n-1; ++i)
		{
			for(int j = 0; j < n-1; ++j)
			{
				int offsetRB = Math.min(bottom[i][j], right[i][j]);
				for(int k = 1; k <= offsetRB; k++)
				{
					int rbi = i + offsetRB;
					int rbj = j + offsetRB;
					// int offsetTL = Math.min(left[rb_i][rb_j], top[rb_i][rb_j]);
					int leftVal = left[rbi][rbj];
					int topVal = top[rbi][rbj];
					int offsetTL = Math.min(leftVal, topVal);
					if(offsetTL >= offsetRB && offsetTL != 0 && offsetRB != 0)
					{
						// System.out.printf(" k = %d \t Left val = %d \n Top val = %d\n", k, leftVal, topVal);
						// System.out.printf("Offset TL = %d \t offset RB = %d \n", offsetTL, offsetRB);
						// System.out.printf("Returning true at indices = [%d,%d]\n", i, j);
						return true;
					}
				}
			}					
		}
		return false;
	}
	
	// Conditinoal logic order eval here too!
	private static void fillLeftZeroes(int[][] matrix, int[][] counts)
	{
		int n = matrix.length;
		for(int i = 0; i < n; ++i)
		{
			int leftCounter = 0;
			counts[i][0] = 0;
			for(int j = 1; j < n; ++j)
			{
				if(matrix[i][j] == 1)
				{
					leftCounter = 0;
				}
				else if(matrix[i][j-1] == 0)
				{
					leftCounter++;
				}
				counts[i][j] = leftCounter;
			}
		}
	}
	
		
	private static void fillRightZeroes(int[][] matrix, int[][] counts)
	{
		int n = matrix.length;
		for(int i = 0; i < n; ++i)
		{
			int rightCounter = 0;
			counts[i][n-1] = 0;
			for(int j = n-2; j >= 0; --j)
			{
				if(matrix[i][j] == 1)
				{
					rightCounter = 0;
				}
				else if(matrix[i][j+1] == 0)
				{
					rightCounter++;
				}
				counts[i][j] = rightCounter;
			}
		}
	}
	
		
	private static void fillTopZeroes(int[][] matrix, int[][] counts)
	{
		int n = matrix.length;
		for(int j = 0; j < n; ++j)
		{
			int topCounter = 0;
			matrix[0][j] = 0;
			for(int i = 1; i < n; ++i)
			{
				if(matrix[i][j] == 1)
				{
					topCounter = 0;
				}
				else if(matrix[i-1][j] == 0)
				{
					topCounter++;
				}
				counts[i][j] = topCounter;
			}
		}
	}
	
		
	private static void fillBottomZeroes(int[][] matrix, int[][] counts)
	{
		int n = matrix.length;
		for(int j = 0; j < n; ++j)
		{
			int bottomCounter = 0;
			counts[n-1][j] = 0;
			for(int i = n-2; i >= 0; --i)
			{
				if(matrix[i][j] == 1)
				{
					bottomCounter = 0;
				}
				else if(matrix[i+1][j] == 0)
				{
					bottomCounter++;
				}
				counts[i][j] = bottomCounter;			
			}
		}
	}		
		
		
		
}


