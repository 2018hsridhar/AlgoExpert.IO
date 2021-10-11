/*
Remove Islands
- Given a 2D matrix : unequal dimensions ( M x N ) , not, ( N X N ) 
- Filled with 0s or 1s ( black-and-white img ) 
- island = any number of 1s hoz/vert adj, but not diagonally adjacent
- island can NOT touch the border of the matrix/img. If a 1 touches a border .... not an island

Goal : 
	Remove all the islands
	Return a new matrix ( no in-place modification of the input ) 

matrix = 
[
  [1, 0, 0, 0, 0, 0],
  [0, 1, 0, 1, 1, 1],
  [0, 0, 1, 0, 1, 0],
  [1, 1, 0, 0, 1, 0],
  [1, 0, 1, 1, 0, 0],
  [1, 0, 0, 0, 0, 1],
]


matrix = 
[
  [1, 0, 0, 0, 0, 0],
  [0, 1, 0, 1, 1, 1],
  [0, 0, 1, 0, 1, 0],
  [1, 1, 0, 0, 1, 0],
  [1, 0, 1, 1, 0, 0],
  [1, 0, 0, 0, 0, 1],
]

matrix = 
[
  [1, 0, 0, 0, 0, 0],
  [0, _, _, _, _, 1],
  [0, _, _, _, _, 0],
  [1, _, _, _, _, 0],
  [1, _, _, _, _, 0],
  [1, 0, 0, 0, 0, 1],
]

Complements of sets : 
	Boundary set
		
	Non-boundary portion

bfs() on the boudnary itself
	A boudnary element = 1
	This is an island but on the boundary -> go eliminate it
	Start was a boundary : induction from root ( all other elems must be a boudnary ) 
	Change the value


bfs(1,3, false)
	bfs(1,4, false)
		bfs(1,5, false)
			return true
		bfs(2,4, false)
	

visited = matrix in global scope, all false initialized

// If point never a boundary -> modif is never made

removeIslands
	for(int i)
		for(int j)
			bfs(mat,i,j,false)
	
	for(int i)
		for(int j)
			if(mat[i][j] == 1)
				mat[i][j] = 0;
				
	for(int i)
		for(int j)
			if(mat[i][j] == 2)
				mat[i][j] = 1;
	
	return mat;
			
	2 := islands on the boudnary

void boolean bfs(int[][] mat, int i, int j)
	if(visited[i][j])
		return;
	else
		visited[i][j] = T;
		mat[i][j] = 2;
		// check children
		if(i-1 >= 0 && mat[i-1][j] == 1)
			bfs(mat, i-1, j, isBoundaryPoint)
		if(i+1 < matrix.length && mat[i+1][j] == 1)
			bfs(mat. i+1, j, isBoundaryPoint)
		if(j-1 >= 0 && mat[i][j-1] == 1)
			bfs(mat, i, j-1, isBoundaryPoint)
		if(j+1 < matrix[0].length && mat[i][j+1] == 1)
			bfs(mat, i, j+1, isBoundaryPoint)
		return;
		
boolean isOnBoundary(int i, int j, int M, int N)
	if ( i == 0 || j ==0 )
		return true;
	if( i == M-1 || j == N-1 )
		return true;
	return false;

		
		
		

matrix = 
[
  [1, 0, 0, 0, 0, 0],
  [0, X, 0, 1, 1, 1],
  [0, 0, X, 0, 1, 0],
  [1, 1, 0, 0, 1, 0],
  [1, 0, X, X, 0, 0],
  [1, 0, 0, 0, 0, 1],
]

// The islands that were removed can be clearly seen here:
// [
//   [ ,  ,  ,  ,  , ],
//   [ , 1,  ,  ,  , ],
//   [ ,  , 1,  ,  , ],
//   [ ,  ,  ,  ,  , ],
//   [ ,  , 1, 1,  , ],
//   [ ,  ,  ,  ,  , ], 
// ]

matrix = 
[
  [1, 0, 0, 0, 0, 0],
  [0, 0, 0, 1, 1, 1],
  [0, 0, 0, 0, 1, 0],
  [1, 1, 0, 0, 1, 0],
  [1, 0, 0, 0, 0, 0],
  [1, 0, 0, 0, 0, 1],
]

Matrix - can it be null/singleton ( 1x1 ). Assume not empty

// Head recursive approach
	-> market elements / islands already visited
	-> perform boudns check in N,S,E,W directions
	-> ask if current element is next to a boundary point

BFS traversal ( flood fill algorithms ) 
	Each matrix element
	

bool BFS(isABoundaryPoint)
{
	BFS(child)
	if any child = T
		=> was on boundary
	else
		=> was not on boundary
}

Minesweeper : 

[
  [2, 0, 2 ],
  [0, 2, 0 ], 
  [2, 2, 2 ]
]
 
(1,1)

[
  [F, F, T ],
  [F, T, F ], 
  [T, T, T ]
]

@ end : for all points equal to 2 -> change that back to 1
@ end : for all islands equal to 1 -> change those to 0
Return the new matrix

Visited set : avoid inf loops in recursion



Complextity 
M := #-rows
N := #-cols
Time = O(MN)		-> quadratic
Space = O(MN)		-> linear

O(MN) + O(M+N)
	Drop O(M+N) << O(MN)

Explicit : O(MN)
Implicit : O(M+N)

bfs(matrix,0,0)
	bfs(matrix,0,1)
		bfs(matrix,0,2)
			bfs(matrix,0,3)


Edge Case Testing
(A) All ones
(B) All zeroes

BFS on the function call stack -> recursive


matrix = 
[
  [1, 1, 1, 1, 1, 1],
  [0, X, 0, 0, 0, 1],
  [0, 0, X, 0, 0, 1],
  [1, 1, 0, 0, 0, 1],
  [1, 0, X, X, 0, 1],
  [1, 0, 0, 0, 0, 1],
]


*/


import java.util.*;

class Program {

	boolean[][] visited;
  public int[][] removeIslands(int[][] matrix) 
	{
		int M = matrix.length;
		int N = matrix[0].length;
		int[][] islandsRem = new int[M][N];
		visited = new boolean[M][N];
		for(int i = 0; i < M; ++i)
		{
			for(int j = 0; j < N; ++j)
			{
				visited[i][j] = false;
				islandsRem[i][j] = matrix[i][j];
			}
		}
		
		// [1] BFS test on each boudnary point
		// Initial parent must be 1 as well
		
		// Linear time iteration over boundary ( 4 times ) 
		for(int i = 0; i < M; ++i)
		{
			if(islandsRem[i][0] == 1)
				bfs(islandsRem, i, 0);
			if(islandsRem[i][N-1] == 1)
				bfs(islandsRem, i, N - 1);
		}
		for(int j = 0; j < N; ++j)
		{
			if(islandsRem[0][j] == 1)
				bfs(islandsRem, 0,j);
			if(islandsRem[M-1][j] == 1)
				bfs(islandsRem, M - 1, j);
		}		
		
		// O(MN) computation
		for(int i = 0; i < M; ++i)
		{
			for(int j = 0; j < N; ++j)
			{
				if(islandsRem[i][j] == 1)
					islandsRem[i][j] = 0;
				if(islandsRem[i][j] == 2)
					islandsRem[i][j] = 1;
			}
		}
		
		return islandsRem;
  }
	
	// O(MN)
	private void bfs(int[][] matrix, int i, int j)
	{
		if(visited[i][j])
			return;
		else
		{
			visited[i][j] = true;
			matrix[i][j] = 2;
			if(i-1 >= 0 && matrix[i-1][j] == 1)
				bfs(matrix, i-1, j);
			if(i+1 < matrix.length && matrix[i+1][j] == 1)
				bfs(matrix, i+1, j);
			if(j-1 >= 0 && matrix[i][j-1] == 1)
				bfs(matrix, i, j-1);
			if(j+1 < matrix[0].length && matrix[i][j+1] == 1)
				bfs(matrix, i, j+1);		
		}
		return;
	}
	
	
}

