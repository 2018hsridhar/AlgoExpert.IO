import java.util.*;

class Program {

	  public int nonAttackingQueens(int n)
		{
			// The base case
			if(n == 1)
					return 1;
			char[][] initConfig = new char[n][n]; // default initialized to '' : we start here
			return recurse(n,n,0,initConfig);
		}
		
		private int recurse(int queensAvail, int n, int row, char[][] matrix) 
		{
			int sum = 0;
			// recursive case
			for(int k = 0; k < n; ++k)
			{
				int childR = row;
				int childC = k;
				matrix[childR][childC] = 'Q';
				int childRow = row + 1;
				if(hasAttackingQueens(n, childR,childC,matrix) == false)
				{
					if(queensAvail-1 == 0)
						++sum;
					if(childRow < n)
					{
						sum += recurse(queensAvail - 1, n, childRow, matrix);
						// <---- IP = instruction pointer returns here
					}
				}
				matrix[childR][childC] = '\0';
			}
			return sum;
		}
		
		// Try to refactor
		private boolean hasAttackingQueens(int n, int qI, int qJ, char[][] matrix)
		{
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
