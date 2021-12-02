import java.util.*;

class Program {

	  public int nonAttackingQueens(int n)
		{
			// The base case
			if(n == 1)
					return 1;
			char[][] initConfig = new char[n][n]; // default initialized to '' : we start here
			return recurse(n,0,initConfig);
		}
		
		private int recurse(int queensAvail, int row, char[][] matrix) 
		{
			int n = matrix.length;
			int sum = 0;
			// recursive case
			for(int k = 0; k < n; ++k)
			{
				int childR = row;
				int childC = k;
				matrix[childR][childC] = 'Q';
				int childRow = row + 1;
				if(hasAttackingQueens(childR,childC,matrix) == false)
				{
					if(queensAvail-1 == 0)
					{
						++sum;
					}
					else if(childRow < n)
					{
						sum += recurse(queensAvail - 1, childRow, matrix);
						// <---- IP = instruction pointer returns here
					}
				}
				matrix[childR][childC] = '\0';
			}
			return sum;
		}
		
		// Try to refactor
		private boolean hasAttackingQueens(int qI, int qJ, char[][] matrix)
		{
			int n = matrix.length;
			boolean hasAttackingQueen = false;
			// Check verticially
			for(int i = 0; i < n; ++i)
			{
				if(matrix[i][qJ] == 'Q' && i != qI)
				{
					return true;
				}
			}
			
			// Check horiz
			for(int j = 0; j < n; ++j)
			{
				if(matrix[qI][j] == 'Q' && j != qJ)
				{
					return true;
				}
			}
			
			// UPPER DIAGONAL
			int sI = qI-1;
			int sJ = qJ+1;
			// Go northeast
			while(sI >= 0 && sJ < n )
			{
				if(matrix[sI--][sJ++] == 'Q')
						return true;
			}
			// Go southwest	
			sI = qI+1;
			sJ = qJ-1;
			while(sI < n && sJ > 0 )
			{
				if(matrix[sI++][sJ--] == 'Q')
						return true;
			}
			
			// LOWER DIAGONAL
			// Go northwest
			sI = qI-1;
			sJ = qJ-1;
			while(sI >= 0 && sJ >= 0)
			{
				if(matrix[sI--][sJ--] == 'Q')
						return true;
			}
			// Go southeast	
			sI = qI + 1;
			sJ = qJ + 1;
			while(sI < n && sJ < n )
			{
				if(matrix[sI++][sJ++] == 'Q')
						return true;
			}			
			
			return hasAttackingQueen;
		}
}

Standard N-queens problem

Write a func that takes in a pos int n, and returns a number of non-attacking placements
of n queens on a (n*n) chessboard
#-placements where no queens can attack another queen
Queens can attack hoz, vert, diag in a single turn
We have only two non-attacking placements

4*O(N) = O(N) iteration for each queen check
{ \, /, |, - }

+--+--+--+--+  
|  |Q |  |  |
+--+--+--+--+
|  |  |  |Q |
+--+--+--+--+
|Q |  |  |  |
+--+--+--+--+
|  |  |Q |  |
+--+--+--+--+

PSEUDOCODE : 

	  public int nonAttackingQueens(int n)
		{
			int[] nonAttackPlacements = new int[1];
			char[][] initConfig = new char[n][n]; // default initialized to ''
			return nonAttackPlacements[0];
		}
		
		private void recurse(int n, int row, char[][] matrix, int[] numPlacements)
		{
			// base case/terminating
			if(n == 0)
			{
					numPlacements[0]++;
			}
			// recursive case
			for(int j = 0; j < n; ++j)
			{
				int rowW = row;
				int colW = j;
				
				// Deep-copy step configs for each new state : can we avoid this?
				char[] childMatrix = new char[n][n];
				for(int i = 0; i < n; ++i)
				{
					for(int j = 0; j < n; ++j)
					{
						childMatrix[i][j] = matrix[i][j];
					}
				}
				
				childMatrix[rowW][colW] = 'Q';
				if(!hasAttackingQueens(rowW,colW,childMatrix))
				{
					recurse(n, row + 1, childMatrix, numPlacements);
				}
			}
		}
		
		private void hasAttackingQueen(int qI, int qJ, char[][] matrix)
		{
			// Check verticially
			
			// Check horiz
			
			// Check upper diag
			
			// Check low diag
		}

INITIALLY ( r = 0) : 

(S)
[Q,_,_]
[_,_,_]
[_,_,_]

NEXT CONFIGS ( r = 1 ): 

(S_1)								Write_indices
[Q,_,_]
[Q,_,_]			X				(1,0)
[_,_,_]

[Q,_,_]
[_,Q,_]			X				(1,1)
[_,_,_]	

[Q,_,_]
[_,_,Q]			VALID		(1,2)
[_,_,_]

Recursion - DFS tree ( configs could overlap - no)?
	


Clarification/Constraints : 
	n > 1
	n = 1 : 1 configuration - 1 queen
	n can get huge ( 100 ) 
	Only pieces are queens
	No oonstraints
	
Thought process : 
	- backtracking,recursive, problems decrease
	- combinatorial object
	- To copy matrix over each iteration
	- prune the search space
	
Complexity 
Let N := #-queens/dims
Time = O(N!) ( EXP? ) 
Space = O(N^3) ( explicit ) O(N) ( implicit ) 
	n*(n-1)*(n-2)...1
	#-calls * space/call
	#-valid_combinations
	
	for_loop
		recursive();
	
	N^2 for the matrix
	N for #-calls on the call stack
	
-> go only if the state is valid

Their optimal
Time = O(N!)
Space = O(N)

TEST CASES : 
