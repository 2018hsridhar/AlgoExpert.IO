/*

Is surprisingly harder than it looks
Is definitely a boundary thing here for sure ( check when they all converge ) 

COMPLEXITY
LET N,M := DIMS(MATRIX)
TIME = O(MN)
SPACE = O(1) ( EXP & IMP ) 

TEST CASES
(A) [1]
(B) [1,2,3,4]
(C) [[1],[2],[3],[4]]
(D) [[1,2,3],[4,5,6,],[7,8,9]]
(E)

Converge on the perimeter of the matrix here

*/
import java.util.*;

class Program 
{
	// Heap allocated memory keeps track of pointers/memory regions ( stk mem cleared ) 
  public static List<Integer> spiralTraverse(int[][] array) 
	{
		if(array == null)
		{
			return null;
		}
		ArrayList<Integer> spiral = new ArrayList<Integer>();
		int tR = 0;
		int bR = array.length - 1;	// wait a second ( harken to double indirection access pattern ) 
		int lC = 0;
		int rC = array[0].length - 1;
		
		// Ptr must commence at (0,0) here too!
		// Check when the 4 boundaries converge too
		int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};	// Right, Bottom, Left, Top
		int i = 0;
		int j = 0;
		int curDir = 0; // we go left->right first
		
		// Note when to change the directions as well, and when to terminate
		while(tR <= bR && lC <= rC )
		{
			// ALways add what is being traversed, BEFORE, what is being checked here
			// If-else-if conditional logic too
			// System.out.printf("(i,j) = (%d,%d)\n", i, j);
			int curElem = array[i][j];
			spiral.add(curElem);
			int nextI = i + dirs[curDir][0];
			int nextJ = j + dirs[curDir][1];
			if(nextJ > rC)	// can't go further right - go bottom now
			{
				curDir = (curDir + 1 ) % dirs.length;
				nextI = i + 1;
				nextJ = j;	// preserve the j here ( no change ) 
				tR++;
			}
			else if ( nextI > bR )  // can't go bottom -. go left now
			{
				curDir = (curDir + 1 ) % dirs.length;
				nextI = i;
				nextJ = j - 1;	// preserve the j here ( no change ) 
				rC--;				
			}
			else if ( nextJ < lC ) 	// can't go left -> go up now
			{
				curDir = (curDir + 1 ) % dirs.length;
				nextI = i - 1;
				nextJ = lC;	// preserve the j here ( no change ) 
				bR--;				
			}
			else if (nextI < tR ) 	// can't go top now -> go right now
			{
				curDir = (curDir + 1 ) % dirs.length;
				nextI = tR;
				nextJ = j + 1;	// preserve the j here ( no change ) 
				lC++;				
			}
			// Update current pointers to index pairings
			i = nextI;
			j = nextJ;
			// System.out.printf("(tR,bR,lC,rC) = (%d,%d,%d,%d)\n", tR, bR, lC, rC);
		}
		// Your ending is right here ... but you have repeated elements
		// System.out.printf("At end of while loop (i,j) = (%d,%d)\n", i, j);
		
		// Special case handling now
    
    return spiral;
  }
}
