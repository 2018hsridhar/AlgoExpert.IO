/*
Given a list of contiguous blocks on the street.
Each block := an apartment you can move into as well.

Pick an apartment block that minimizes distance to travel elsewhere

PROBLEM-SOLVING STRATEGIES : Combinations/DP/Recursion
Can have multiple valid solutions : can return ANY of them as well
A block has 3 seperate instutitions : { gym, school, store } 

COMPLEXITY : 
Let N := #-blocks
Let K := length(requirements) / #-unique requirements
Time = O(NK)
Space = O(NK)

We can try to precompute a bunch of storage and distances as well, if need be
	Solve for each of them in parallel from one another?
	Can BFS from valid locations as well too ( to fill in our arrays ) 

TEST BENCH : 
(A)
(B)
(C)
(D)
(E)

What if we get a case where we have fewer reqs, or more reqs, than a given block has as well?
We can run into a case where #-reqs is less than #-total buildings per block
*/
import java.util.*;

class Program 
{
  public static int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) 
	{
		int n = blocks.size();
		int k = reqs.length;
		int[][] minimalDists = new int[n][k];
		// Default initialize to Integer.MAX_VALUE here
		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < k; ++j)
			{
				minimalDists[i][j] = Integer.MAX_VALUE;
			}
		}
		
		
		for(int i = 0; i < n; ++i)
		{
			Map<String,Boolean> blockInfo = blocks.get(i);
			int j = 0;
			for(String key : reqs)
			{
					// System.out.printf("key = %s\n", key);				
					if(blockInfo.get(key) == true)
					{
						minimalDists[i][j] = 0;
					}
					++j;
			}
		}
		
		// Scope for each building req as well ( having precomuted info ahead of time ) 
		for(int j = 0; j < k; ++j)
		{
			boolean encounteredLoc = false;
			int stepsAway = 0;
			
			// IN THE FORWARDS DIRECTION
			for(int i = 0; i < n; ++i)
			{
				// Always check if we hit a location for first time :-)
				if(minimalDists[i][j] == 0)
				{
					encounteredLoc = true;
				}
				
				if(encounteredLoc)
				{
					if(minimalDists[i][j] == 0)
					{
						stepsAway = 0;
					}
					else
					{
						stepsAway++;
					}
					minimalDists[i][j] = Math.min(minimalDists[i][j], stepsAway);
				}
			}
			
			// IN THE REVERSE DIRECTION
			// Reset our local vars here
			encounteredLoc = false; 
			stepsAway = 0;
			for(int i = n-1; i >= 0; --i)
			{
				// Always check if we hit a location for first time :-)
				if(minimalDists[i][j] == 0)
				{
					encounteredLoc = true;
				}
				
				if(encounteredLoc)
				{
					if(minimalDists[i][j] == 0)
					{
						stepsAway = 0;
					}
					else
					{
						stepsAway++;
					}
					minimalDists[i][j] = Math.min(minimalDists[i][j], stepsAway);
				}
			}
			
		}
		
		int globalMaxBlocksToWalk = Integer.MAX_VALUE;
		int minIndex = -1;
		for(int i = 0; i < n; ++i)
		{
			int localMaxBlocksToWalk = 0;
			for(int j = 0; j < k; ++j)
			{
				localMaxBlocksToWalk = Math.max(minimalDists[i][j], localMaxBlocksToWalk);
			}
			if(localMaxBlocksToWalk < globalMaxBlocksToWalk)
			{
				globalMaxBlocksToWalk = localMaxBlocksToWalk;
				minIndex = i;
			}
		}
		
		return minIndex;
  }
}
