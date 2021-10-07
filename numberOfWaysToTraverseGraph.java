/*

Given two positive integers denoting {width,height} of a grid-shaped, RECTANGULAR graph
Return #-ways to reach bottom right corner of graph, when starting @ top left corner
Moves restricted in cardinality : can traverse down or right only

Is definitely known to be the classic DP problem with grids
Can we get the recursive approach here first, and then break it into subproblems?

Never get a 1x1 grid, null grid, or empty ( 0x0 ) grid as well

Edge Case Testing : 
(A) (w,h) = (4,1)
	=> 4
(B) (w,h) = (1,6)
	=> 6
(C) (w,h) = (2,4)
	=> 4
(C) (w,h) = (4,4)
	=> 20


Pseudocode : 
	int count = 0;
	void recurse(int sx, int sy, int tx, int ty):
		if(sx==yx && sy==ty)
			count++;
			return;
		if(sx+1 < r)
			recurse(sx+1,sy,tx,ty)
		if(sy+1 < r)
			recurse(sx,sy+1,tx,ty)
	
Recursion Complexity : 
Let M := #-rows, N := #-cols
But what is H, is what will trip folks up
Time = O(2^H)
Space = Explicit : O(1)
				Implicit Call stack space : O(H)
				
DP Complexity : 
Let M := #-rows, N := #-cols
Time = 
Space = Explicit : O(1)
				Implicit Call stack space : O()				

*/


import java.util.*;

class Program {

	int numWays = 0;
	public int numberOfWaysToTraverseGraph(int width, int height) 
	{
		numWays = 0; // initialize static/global vars as well
		recurse(0,0, width, height);
		return numWays;
  }
	
	private void recurse(int sx, int sy, int tx, int ty)
	{
		// System.out.printf("sx,sy = (%d,%d)\n", sx, sy);
		if(sx == tx-1 && sy==ty-1)
		{
			System.out.printf("here\n");
			numWays++;
			return;
		}
		if(sx + 1 < tx)
		{
			recurse(sx + 1, sy, tx, ty);
		}
		if(sy + 1 < ty)
		{
			recurse(sx, sy + 1, tx, ty);
		}
		return;
	}
	
}