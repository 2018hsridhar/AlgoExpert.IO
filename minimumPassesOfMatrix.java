import java.util.*;

class Program {

	/*
	
	Strategies : Binary Search, Level-Order BFS 
	
	Level-order BFS too ( utilize queue iteration to avoid call stack space ) 
	Input matrix always guaranteed one element
	Return {-1} if negative ints CAN NOT be converted to positives
	Seems recursive in nature now : akin to Uber - > multiple starting nodes can start the BFS level-order traversal ( many root nodes problem ) 
	ADJACENT ELEMENT => Cardinal directions restricted { N,S,E,W } 
	
	But how to pass the depth information as well?

	Let us suppose that (m,n) can get very huge, BUT, fit in-memory
	Can we check if we even have a decrement or not here ( over the queue ) ?
	
	COMPLEXITY : 
	TIME = O(MN)
	SPACE = O(1)
	
	Using a queue for recursion : 
	WORST CASE TIME-SPACE
	TIME = O((MN)^2)
	SPACE = O(MN), supposing each element is a negative value
	
	Think : The depth of a queue, OR, the depth of a stack, does have a strict upper bound
	
	
	TEST BENCH : 
	(A) 
			[
 			 	[-1, 3],
				[2, -4]
			]
	(B) [
 			 [-1, -3],
  			[-2, -4]
			]
	(C) [
 				[1, 3],
  			[2, 4]
			]
	(D)
			
			
	*/
	
	public class tuple
	{
		public int i;
		public int j;
		
		public tuple(){};
		public tuple(int i, int j)
		{
			this.i = i;
			this.j = j;
		}
	}

	// See the benefit of the implicit call stack is preserving level/depth info anyways :-)
  public int minimumPassesOfMatrix(int[][] matrix) {
    // QUICKLY HANDLE THE BASE CASE HERE
		if(matrix.length == 1 && matrix[0].length == 1)
		{
			if(matrix[0][0] < 0) 
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
		
		// [0] Initialize the queue with your given roots here
		// But do check if there are any negatives as well ( say, we have all positives only ! ) 
		// The initial parent set here, though, does differ!
		boolean encountNeg = false;
		List<tuple> parentSet = new ArrayList<tuple>();
		for(int i = 0; i < matrix.length; ++i)
		{
			for(int j = 0; j < matrix[0].length; ++j)
			{
				if(matrix[i][j] < 0)	// but careful here : is is NOT all the negative elements we commence at!
				{
					tuple newEl = new tuple(i,j);	// by default, we always get one pass here
					if(matrix[newEl.i][newEl.j] < 0 )
					{
						if(hasPositiveNeighbor(newEl, matrix))	// has a design flaw though
						{
							parentSet.add(newEl);
						}
						encountNeg = true;
					}	
				}
			}
		}
		
		// [2] Iterate over the queue
		if(parentSet.size() == 0 && encountNeg == true)
			return -1;
		else if(parentSet.size() == 0 && encountNeg == false)
				return 0;
		int numPasses = bfs(matrix, parentSet);
		
		// check i any neg roots remain
		for(int i = 0; i < matrix.length; ++i)
		{
			for(int j = 0; j < matrix[0].length; ++j)
			{
				if(matrix[i][j] < 0)
					return -1;
			}
		}
		
    return numPasses;
  }
	
	public int bfs(int[][] matrix, List<tuple> parentSet)
	{
		// We do NOT operate on a null set anyways
		if(parentSet.size() == 0)
		{
			return 0;
		}
		
		// Generate the child set here
		List<tuple> childSet = new ArrayList<tuple>();
		for(int i = 0; i < parentSet.size(); ++i)
		{
			tuple parent = parentSet.get(i);
			if(matrix[parent.i][parent.j] < 0 )
			{
				getNeighbors(parent, matrix, childSet);
			}
		}

		// Then set the parents as expected to their values
		// But do another count here ( as parents may have been set to positive earlier as well )
		boolean encountNeg = false;
		for(int i = 0; i < parentSet.size(); ++i)
		{
			tuple parent = parentSet.get(i);
			int curX = parent.i;
			int curY = parent.j;
			if(matrix[curX][curY] < 0 )
			{
				encountNeg = true;
				boolean canBecomePos = hasPositiveNeighbor(parent, matrix);
				if(canBecomePos == true)
				{
					matrix[curX][curY] *= -1;
				}
				else
				{
					return -1; // not doable here
				}
			}
		}
		
		if(encountNeg == false)
			return 0;
		
		// Now iterate over the children
		int childProblem = bfs(matrix, childSet);
		if(childProblem == -1) return -1;
		return 1 + childProblem;
	}
	
		// Checks Adjacency in 2,3,4 dir case
	public void getNeighbors(tuple root, int[][] matrix, List<tuple> childSet)
	{
		int i = root.i;
		int j = root.j;
		if((i-1) >= 0 && matrix[i-1][j] < 0)
		{
			childSet.add(new tuple(i-1,j));
		}
		if((i+1) < matrix.length && matrix[i+1][j] < 0)
		{
			childSet.add(new tuple(i+1,j));
		}
		if((j-1) >= 0 && matrix[i][j-1] < 0 )
		{
			childSet.add(new tuple(i,j-1));
		}
		if((j+1) < matrix[0].length && matrix[i][j+1] < 0)
		{
			childSet.add(new tuple(i,j+1));
		}
		return;
	}
	
	// Checks Adjacency in 2,3,4 dir case
	public boolean hasPositiveNeighbor(tuple root, int[][] matrix)
	{
		int i = root.i;
		int j = root.j;
		if((i-1) >= 0 && matrix[i-1][j] > 0)
		{
			return true;
		}
		if((i+1) < matrix.length && matrix[i+1][j] > 0)
		{
			return true;
		}
		if((j-1) >= 0 && matrix[i][j-1] > 0)
		{
			return true;
		}
		if((j+1) < matrix[0].length && matrix[i][j+1] > 0)
		{
			return true;
		}
		return false;
	}
	
}
