Problem statement : 
Given a 2D array - a matrix - containing 0s and 1s
0s :- parts of land
1s :- parts of a river

River consists of ANY number of 1s hoz/vert adjacent ( NOT diagonally ) 
#-adj ones determines size

Returns an array of sizes of ALL rivers represented in the 2D matrix
Order of sizes does NOT matter
 
matrix = [
	[1,0,0,1,0],
	[1,0,1,0,0],
	[0,0,1,0,1],
	[1,0,1,0,1],
	[1,0,1,1,0]
]

{2,1,5,2,2}
Count size of array all vertically or horizontally
Do NOT count diagonally


Input clarifications : 
1. can Matrix be null or empty?
	Is never empty/null
2. Height,Width can differ and each can be equal one
3. Can get a matrix of all 1's/all 0's  MxN

Island counting
STRATEGIES : BFS/DFS, Recursive / Iterative

COMPLEXITY
Let M,N := width,height of the matrix
Time = O(MN*O(dfs)) -> O(MN*2) = O(MN) ( POLY ) 
	-> a cell is visited twice @ max in a matrix
Space = O(MN) ( implicit func call stack space ) O(1) explicit

PSEUDOCODE : 


	List<Integer> riverSizes(int[][] matrix):
		List<Integer> riverSizes;
		Visit each cell in the matrix	// O(MN)
			If zero or two -> skip it
			Else if cell (i,j) = 1
				int riverSize = dfs(i,j)
				riverSizes.addLast(riverSize)
		return riverSizes
		
	int dfs(matrix, i, j):
		
		int size = 0;
		int m = matrix.length
		int n = matrix[0].length;
		
		if(i < 0 || i >= m)
		{
			return 0;
		}
		if(j < 0 || j >= n)
		{
			return 0;
		}
		
		// Parent
		if(matrix[i][j] != 1)
		{
			return 0;
		}
		size += 1;
		matrix[i][j] = 2;
		
		// Adjacent to parent ( in cardinal dirs ) : children
		size += dfs(matrix, i-1,j); 
		size += dfs(matrix, i+1,j);
		size += dfs(matrix, i,j-1);
		size += dfs(matrix, i,j+1);
		
		return size;
		



TEST CASES
(A) 
matrix = [
	[0,0,1,0,0],
	[0,0,1,0,0],
	[1,1,1,1,1],
	[0,0,1,0,0],
	[0,0,1,0,0]
]
{9} := expected return

Mark the visited cells : mark as "2" int value or "0" ( in-place modif ) 
Do boundary checks in cardinal dirs - { N, E, S, W } 

Summation : backtrack values as well
Set parent before exploring any child as well

dfs is called only once per cell : not more than once
We mark cell as visited - via "2" - no dfs calls can be made again

(B) 
matrix = [
	[0,0],
	[0,0]
]
{}

(C) 
matrix = 
[
	[1,1,1],
	[1,1,1]
]
{6}

(D) 
matrix = [
	[1,0,0],
	[0,1,0],
	[0,0,1]
]
{1,1,1} 

(E)
matrix = [
	[1,1,0,1],
	[0,1,0,0],
	[0,0,1,0]
	[0,0,,01]
]
{1,1,1} 


import java.util.*;

class Program 
{
	
	static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	
  public static List<Integer> riverSizes(int[][] matrix) 
	{
		
		// List<Integer> riverSizes = new ArrayList<Integer>();
		List<Integer> riverSizes = new LinkedList<Integer>();
		int m = matrix.length;
		int n = matrix[0].length;
		for(int i = 0; i < m; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				if(matrix[i][j] == 1)
				{
					int riverSize = dfs(matrix, i,j);
					riverSizes.add(riverSize);
				}
			}
		}
		return riverSizes;
  }
	
	// Incorporate a seperate visited matrix ( set booleans there ) ; ask if we visited an element
	// O(MN) explicit 
  private static int dfs(int[][] matrix, int i, int j)
	{
		int size = 0;
		int m = matrix.length;
		int n = matrix[0].length;
		
		if(i < 0 || i >= m || j < 0 || j >= n)
		{
			return 0;
		}
		
		// Parent
		if(matrix[i][j] != 1)
		{
			return 0;
		}
		
		size += 1;
		matrix[i][j] = 2;
		
		// Adjacent to parent ( in cardinal dirs ) : children
		for(int k = 0; k < dirs.length; ++k)
		{
			int[] step = dirs[k];
			size += dfs(matrix, i + step[0], j + step[1]);
		}
		
		// size += dfs(matrix, i-1,j); 
		// size += dfs(matrix, i+1,j);
		// size += dfs(matrix, i,j-1);
		// size += dfs(matrix, i,j+1);
		
		return size;
	}
}

