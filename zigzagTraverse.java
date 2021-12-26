/*
Is there a better way to tackle this problem as well?
Instead of the possible index+index arguement, and checking the boundaries as well?
We know alternations are needed, and we also know the bounds to keep checking as well here


COMPLEXITY
Let N, M := dimensionsof the two-dimensional array
TIME = O(NM)
SPACE = O(1) [ with exception to append to the results here ]

n := #-rows
m := #-cols

*/
import java.util.*;

class Program 
{
  public static List<Integer> zigzagTraverse(List<List<Integer>> array) 
	{
    List<Integer> traversal = new ArrayList<Integer>();
		int[][] dirs = new int[][]{{-1,1},{1,-1}};	// NE, SW
		int m = array.size();
		int n = array.get(0).size();	// It is a size of a list here
		int rI = 0;
		int cI = 0;
		int diag = 1;	// go northeast ( 1 :=> southwest )
		while(rI <= m-1 && cI <= n-1 ) // 0 indexing here
		{
			// Always add the current elem under reference BEFORE shifting indices
			int elem = array.get(rI).get(cI);
			traversal.add(elem);
			int nxtRI = rI + dirs[diag][0];
			int nxtCI = cI + dirs[diag][1];
			if(diag == 0)
			{
				if(nxtRI < 0 || nxtCI >= n )
				{
					diag = 1;
					if(nxtCI >= n)
					{
						nxtRI = rI + 1;
						nxtCI = n-1;
					}
					else if(nxtRI < 0 && nxtCI < n)
					{
						nxtRI = 0;
					}
				}
			}
			else if ( diag == 1)
			{
				if(nxtRI >= m || nxtCI < 0)
				{
					diag = 0;
					if(nxtRI >= m)
					{
						nxtRI = m-1; // what the row formerly was here ( n - 1 )  
						nxtCI = cI + 1;
					}
					else if(nxtRI < m && nxtCI < 0)
					{
						nxtCI = 0;
					}
				}
			}
			rI = nxtRI;
			cI = nxtCI;
		}
		
		return traversal;
  }
}
