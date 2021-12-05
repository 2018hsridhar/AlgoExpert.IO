/*

Is a sudoku puzzle
In problem : Provided an input to a 2D array ( represented as list<list<Int>> )
Some present vals already [0-9]
Idea : return the solution to Sudoku
Already has pre-loaded values here
There can be multiple valid solutions ( not just one ) 
	=> only one valid solution
Can board be all zero-ed out?
	- Is pre-loaded with values
	- Follows sudoku's 3 constraints
	- no repeated numbers in the 9 3x3 subgrids as well
Can mutate the original input as well ( if desired ) 
Valid #'s : 1-9
	- always a valid solution!

The objective is to fill the grid such that each row, column, and 3x3 subgrid contains the numbers 1-9 exactly once.
In other words, no row may contain the same digit more than once, no column may contain the same digit more than once, 
and none of the 9 3x3 subgrids may contain the same digit more than once.

Sample Input
board = 
[
  [7, 8, 0, 4, 0, 0, 1, 2, 0],
  [6, 0, 0, 0, 7, 5, 0, 0, 9],
  [0, 0, 0, 6, 0, 1, 0, 7, 8],
  [0, 0, 7, 0, _, 0, 2, 6, 0],
  [^, 0, 1, 0, 5, 0, 9, 3, 0],
  [9, 0, 4, 0, 6, 0, 0, 0, 5],
  [0, 7, 0, 3, 0, 0, 0, 1, 2],
  [1, 2, 0, 0, 0, 7, 4, 0, 0],
  [0, 4, 9, 2, 0, 6, 0, 0, &],
]

<- start the 0-cell search from a specific index pair
	<-(3,4) : can search all rows & cols beneath, and all cols to the right
	<- read left to right

  0	1		2		3	4		5	6		7		8 = col indices
	
Output
[
  [7, 8, 5, 4, 3, 9, 1, 2, 6],
  [6, 1, 2, 8, 7, 5, 3, 4, 9],
  [4, 9, 3, 6, 2, 1, 5, 7, 8],
  [8, 5, 7, 9, 4, 3, 2, 6, 1],
  [2, 6, 1, 7, 5, 8, 9, 3, 4],
  [9, 3, 4, 1, 6, 2, 7, 8, 5],
  [5, 7, 8, 3, 9, 4, 6, 1, 2],
  [1, 2, 6, 5, 8, 7, 4, 9, 3],
  [3, 4, 9, 2, 1, 6, 8, 5, 7],
]

Strategies : Backtracking,Recursive Problem
- test out all configurations
- if config invalid : do not recurse on it
- combinatorial object ( combinatorial problem ) 
- Code to search for empty slot as well
		-> once these indices hit the end ( 9x9 ) : test validity [ terminating condition ] 
- For each empty slot [ 0 ] -> test out the 9 numbers and then proceed

Func : Test validity of a 9x9 board
	O(1) : bound to #-operations here	
	
COMPLEXITY : 
Let N := size of board
9*9 slots total = 81 slots
Total combinations ( to test ) = (9^81) = a const
TIME = O(1)
SPACE = O(81 stack frames*O(1)) = O(1) ( EXP ) O(1) ( IMP ) 
	- 81 stack frames @ max in a call stack
	- If I deep-copy the matrix at each recursive call : we multiply by (9*9) INT cells
	- If no deep-copy : we multiply by 1 
	
Caller & callee stack frames
-> if you are running out of allocatable stack frames, consider pruning the search space ahead of time
in your recursive approach as well.
	


*/



import java.util.*;

class Program {

	// You still get TLE here ... dang it! Memory is leaking somewhere too?
  public ArrayList<ArrayList<Integer>> solveSudoku(ArrayList<ArrayList<Integer>> board) 
	{
		int[][] finalArr = new int[9][9];
		
		// Add a special pair as well for the final cell case of [9,0] too!
		int zeroIdx = 0;
		ArrayList<int[]> zeroCells = new ArrayList<int[]>();	// we need new for HEAP initialization ( do NOT just declare )
		getZeroCells(board, zeroCells);
		int[] parentCell = zeroCells.get(0);
		ArrayList<ArrayList<Integer>> finalRes = new ArrayList<ArrayList<Integer>>(); // seperate heap-alloced mem ( does dim matter )
		// We didn't set parent Cell in caller
		// System.out.printf("Parent cell = [%d,%d]\n", parentCell[0], parentCell[1]);
		boolean status = recurseGenerateConfigs( board, parentCell[0],parentCell[1], finalArr, zeroCells, zeroIdx);
		// System.out.printf("Status = %s\n", status);
		if(status == false)
		{
			return null;
		}
		for(int i = 0; i < 9; ++i)
		{
			ArrayList<Integer> finalRow = new ArrayList<Integer>();
			for(int j = 0; j < 9; ++j)
			{
				finalRow.add(finalArr[i][j]);
			}
			finalRes.add(finalRow);
		}
		
		
    return finalRes;
  }
	
	// Return address to that board
	// Allocating too much heap memory in the stack frames?
	// NOt setting parent cell here as well!
	// Parent case is not getting filled ... ahhh I see
	private boolean recurseGenerateConfigs(ArrayList<ArrayList<Integer>> board, int pI, int pJ, int[][] finalArr, ArrayList<int[]> zeroCells, int zeroIdx)
	{
		// System.out.printf("Testing combos for current index pair = (%d,%d)\n", pI, pJ);
		// Terminating case
		if(pI == 9 && pJ == 0)
		{
			if(isValidCombination(board))
			{
				// System.out.printf("At deep copy step\n");
				// Deep-copy board to finalRes
				for(int i = 0; i < 9; ++i)
				{
					for(int j = 0; j < 9; ++j)
					{
						finalArr[i][j] = board.get(i).get(j);
					}
				}
				return true;
			}
			else
			{
				return false;
			}
		}
		// Recursive case
		else
		{
			// Assumes cell (i,j) = 0
			// Iterate over child configs
			// Slight gotcha at final cell ( 8,8) : not cells at this point! -> ask if nextCell = [9,0]
			int[] nextCell = zeroCells.get(zeroIdx + 1);
			// Store in parent caller stack frame
			int cI = nextCell[0];
			int cJ = nextCell[1];
			// System.out.printf("Generating stack frame for cell = [%d,%d]\n", cI, cJ);
			for(int k = 1; k <= 9; ++k)
			{
				if(pI != 9)
				{
					board.get(pI).set(pJ,k);
					// assert if the digit insertion here is valid as well
					if(insertValid(board, pI, pJ))
					{
						// System.out.printf("Insert of val k = [%d] was valid\n", k);
						// We can always increment the zero cell index ... it gets passed in anyways here
						// But set parent child cells correctly. 
						boolean childState = recurseGenerateConfigs(board, cI,cJ, finalArr, zeroCells, zeroIdx + 1);
						if(childState == true)
						{
							return true;
						}
					}
					board.get(pI).set(pJ,0);
				}
				else
				{
					// Immediately shift IP to the terminating case in this function
					return recurseGenerateConfigs(board, cI,cJ, finalArr, zeroCells, zeroIdx + 1);
				}
				// set back for next exploration ( changing on way back from backtrack)
				// backtrack won't result in that final board
			}
		}
		return false;
	}
	
	private boolean insertValid(ArrayList<ArrayList<Integer>> board, int wI, int wJ)
	{
			// [1] Find where it's (9*9) is and analyzed it ( leverage modulus operation ) 

			// Check the vertical ( row-by-row )
			int[] vertFreqMap = new int[10];
			for(int i = 0; i < 9; ++i)
			{
				int val = board.get(i).get(wJ);
				vertFreqMap[val]++;
				if(val != 0 && vertFreqMap[val] == 2)
				{
					return false;
				}
			}

			// Check the horizontal ( col-by-col )
			int[] hozFreqMap = new int[10];
			for(int j = 0; j < 9; ++j)
			{
				int val = board.get(wI).get(j);
				hozFreqMap[val]++;
				if(val != 0 && hozFreqMap[val] == 2)
				{
					return false;
				}
			}
		
			// Check the submatrices now
			int[] subMatMap = new int[10];
			int offI = wI / 3;
			int offJ = wJ / 3;
			for(int i = 0; i < 3; ++i)
			{
				for(int j = 0; j < 3; ++j)
				{
					int val = board.get(offI*3 + i).get(offJ*3 + j);
					subMatMap[val]++;
					if(val != 0 && subMatMap[val] == 2)
					{
						return false;
					}
				}
			}
			
			return true;
		
	}
	
	
	// Given the index pair to start search from
	// Could refactor
	// Could we expedite this as well ( just store the list of the 0-cells as we traverse )? We know ahead of time
	// What to fill, and what not to fill anyways!
	private void getZeroCells(ArrayList<ArrayList<Integer>> board, ArrayList<int[]> zeroCells)
	{
		int n = board.size();
		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
					if(board.get(i).get(j) == 0)
					{
						int[] newPair = new int[]{i,j};	// non-dimensional initializatino syntax
						zeroCells.add(newPair);
					}
			}
		}
		zeroCells.add(new int[]{9,0});
	}

	// Use a frequency map : check per row, per col, so on
	// If any entry 0 -> is not valid 
	private boolean isValidCombination(ArrayList<ArrayList<Integer>> board)
	{
		// Check row-wise
		for(int i = 0; i < 9; ++i)
		{
			// Drawback : alloc new heap memo each iteration here
			int[] numFreqMapRow = new int[10]; // zero-initialized
			// Per col
			for(int j = 0; j < 9; ++j)
			{
				int elem = board.get(i).get(j);
				numFreqMapRow[elem]++;
				if(numFreqMapRow[elem] == 2)
				{
					return false;
				}
			}
		}
		
			// Check col-wise
		for(int j = 0; j < 9; ++j)		
		{
				int[] numFreqMapCol = new int[10]; // zero-initialized
				// Per col
				for(int i = 0; i < 9; ++i)
				{
				int elem = board.get(i).get(j);
				numFreqMapCol[elem]++;
				if(numFreqMapCol[elem] == 2)
				{
					return false;
				}
			}
		}
		
		// (3x3) checks
		int[][] startSubs = new int[][]{{0,0},{0,3},{0,6},{3,0},{3,3},{3,6},{6,0},{6,3},{6,6}};
		for(int k = 0; k < startSubs.length; ++k)
		{
			int sR = startSubs[k][0];
			int sC = startSubs[k][1];
			int[] numFreqMap = new int[10];
			for(int i = 0; i < 3; ++i)
			{
					for(int j = 0; j < 3; ++j)		
					{
						int elem = board.get(sR + i).get(sC + j);
						numFreqMap[elem]++;
						if(numFreqMap[elem] == 2)
						{
							return false;
						}
				}
			}
		}		
		return true;
	}
	
	
}
