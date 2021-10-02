Given two inputs - height, max_num_steps
Given a height = 3, maxSteps = any_stsep @ any point ( here, 2 )
Given total number of ways that you can reach said height from bottom of staircase

Height traversable per step -> 

height = 3
maxsteps = 2

output = 5

height = 5,maxSteps = 2
Steps that can be taken 
Different combinatinos => return # of combinations
{1}
{2}
To get to 5, we can do the following : 
{1,1,1,1,1}
{1,2,2}
{1,1,1,2}
{2,2,1}
{2,1,2}

Subset generation problem -> recursive in nature
We may have DP involved becuase of overlapping smaller recursive subproblems ( brute force ) 
	Bottom-up DP or top-down memoization
	
{3} -> goes over maxSteps = 2
{4}
Can use UP TO the max number of steps given

5-1 = 4
5 - 1 - 1 = {3}
5 - 2 = {3}

A way to cache the computation based on index/stair height [1,...,height]
Height -> assume lower bound = 1, and assume maxSteps is bounded by [1,height]

Complexity ( brute force ) 
step = 1 : {1+1+...+1} = H number of nimes ( 1*H = H)
Let H := height, M := max_steps
Time = O(num_choices^H) = O(max_steps^H)	-> exponential 
Space = O(H)

Pseudo code : 
global numCombos : scope it out of recurisve helper
2 terminating condition
recurse(height,maxSteps)
	if(height == 0)
		++numCombos;
		return
	else if ( height < 0)
		return
	else
		for(int i = 1; i <= maxSteps ++i)
			recurse(height-i, maxSteps);
	
height(5,2)
	height(4,2)
		height(3,2)
			height(2,2)
				height(0,2)
					++cnt;
				height(1,2)
					height(-1,2)
					height(0,2)
						++cnt
			height(1,2)
		height(2,2)
	height(3,2)
		height(2,2)
		height(1,2)
		
Edge case testing : 
(4,2) => 5
(4,1) => 1
(0,2) => 1
(0,0) => 1
(2,0) => 0

numsteps = 2, height = 4
possible steps takeable : {1,2}
height = 4 
	4-2 = 2 : height(2)
	4-1 = 3 : height(3)

Top-Down Memoization:
Let H := maxHeight, M := max Steps
Time = O(maxSteps * H)			-> POLY ( quadratic ) 
Space = O(H)		-> for the cache
	-> Implicit call stack space taken

Iterative Bottom-Up DP 
Let H := maxHeight, M := max Steps
Time = O(HM)			-> POLY ( linear ) 
Space = O(H)		-> for the cache
	-> No call stack space take

Fibonacci sequence problem
Fib(i) = Fib(i-1) + fib(i-2)
memo(height) = memo(height-1) + memo(height-2 ) _ ... memo(height-maxSteps)
Validity check -> can expect a property sequence as well

Math.max(maxSteps,height)
// hey if height=5,maxSteps = 8
// {8,7,6} -> all reach
// perform a loop from height to maxSteps to account for those valid combinations


Math.max(maxSteps,height)
// hey if height=5,maxSteps = 8
// {8,7,6} -> all reach
// perform a loop from height to maxSteps to account for those valid combinations

height=4, maxStep = 2

1=1 = 2
1+2 = 3
2 +3 = 5
3 + 5 = 8

maxSteps = 3
h		maxSteps 			rep				sequence
0		{}						1					1		
1   {1}						1				mem[0]
2   {1,2}					2				mem[0] + mem[1]
3		{1,2,3}					3				mem[1] + mem[2] = mem[0] + mem[0] + mem[1]
4		{1,2,3}					5				mem[2] + mem[3]	= mem[0] + mem[1] + mem[2] + mem[1]
																= mem[0] + mem[0] + mem[1] + mem[0] + mem[0]
																= mem[0] + mem[0] + mem[0] + mem[0] + mem[0]
5		{1,2m3}					8				mem[4] = mem[3]
													mem[2] + mem[3]	= mem[0] + mem[1] + mem[2] + mem[1]
																= mem[0] + mem[0] + mem[1] + mem[0] + mem[0]
																= mem[0] + mem[0] + mem[0] + mem[0] + mem[0]
nth Fibonacii?
Fib[i] = Fib[i-1] + Fib[i-2] + Fib[i-3]






import java.util.*;

class Program {

	int[] memo;
  public int staircaseTraversal(int height, int maxSteps) 
	{
		memo = new int[height+1];
		memo[0] = 1; // correct if needed to 1
		for(int i = 1; i < height+1; ++i)
			memo[i] = -1;
		if(height == 0)
			return 1;
		if(maxSteps == 0)
			return 0;
		topDownMemo(height,maxSteps);
		return memo[height];
  }
	
	public int topDownMemo(int height, int maxSteps)
	{
		int numCombos = 0;
		if ( height == 0)
		{
			memo[0] = 1;
			return 1;
		}
		else
		{
			// O(maxSteps) time here
			for(int step = 1; step <= Math.min(height,maxSteps); ++step)
			// for(int i = 1; i <= maxSteps; ++i)
			{
				// 1. hit the cache
				if(height - step >= 0)
				{
					if(memo[height-step] != -1 )
						numCombos += memo[height-step];
					else
					{
						// 2. execute recursion if not in cache
						memo[height-i] = topDownMemo(height-step, maxSteps);
						numCombos += memo[height-step];
					}
				}
			}
		}
		memo[height] = numCombos;
		return numCombos;
	}
	
}

















import java.util.*;

class Program {

  public int staircaseTraversal(int height, int maxSteps) 
	{
		if(height == 0)
			return 1;
		if(maxSteps == 0)
			return 0;
		return bottomUpDP(height,maxSteps);
  }
	
	public int bottomUpDP(int height, int maxSteps)
	{
		int[] memo;
		memo = new int[height+1];
		memo[0] = 1; // correct if needed to 1
		for(int curH = 1; curH <= height; ++curH)
		{
				for(int step = 1; step <= maxSteps; ++step)
				{
					if(curH - step >= 0)
					{
						memo[curH] += memo[curH-step];
					}	
				}
		}
		return memo[height];
	}
	
}

