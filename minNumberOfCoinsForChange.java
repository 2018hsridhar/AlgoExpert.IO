Given a nonneg int n
Given an array denoting coin denominations

Returns the SMALLEST number of coins needed to make change for said target using denominations
Coin supply be infinite : unlimited access


sample input: [1, 5, 10], n = 7
k = 3 coins { 1 + 1 + 5 = 7 }

If no ways to reach the target 'n' : return -1
>= 1 or -1 ( unreachable )

Problem solving strategies : Brute Force Recursion, backtracking, Greedy, DP

n = 7 :
{1,2}
coinChange(7)-2=5
coinChange(7)-1-1=5
coinChange(5) overlaps for coinChange(7)

Doing a minimum : evaluate each child state AND take the minimum across those children

CLARIFICATION
n >= 1 ( could be zero ) , but denoms are nonNEG ( >= 1)
Can array be null ? Yes

COMPUTATIONAL COMPLEXITY 
Let K := #-unique denominations
( naive recursion/backtracking ) 
Time = O(K^N) 						EXP
Space = O(N) ( implicit ) O(1) explicit
( bottom-up DP )
Time = O(NK) 						POLY
Space = O(1) ( implicit ) O(N) explicit


TEST BENCH 
(A) [], 7 
	0
(B) [], 0
	1
(C) [1], 10
	10
(D) [2,3]	5
	2
	*** not passing this case ***
(E) [4,6] 2
	-1
(F) [2,3,4] 10
(G) 

Denoms {1,2} not the most helpful



PSEUDOCODE ::

	// Gotcha : if minNumWays in all cases is invalid!
	helper(n,denominations)
	
		if n < 0
			return -1

		if n is 0
			return 1

		canSolve = false
		minNumWays = Integer.MAX_VALUE;					// minimum is if a denom = 1 ( val ) 
		for each coin in denominations
			solve k = n - coin
			solve for childNumWays = helper(k,denominations)
			if(childNumWays >= 1)
				canSolve = true
				if ( childNumWays + 1 ) < minNumWays
					minNumWays = childNumWays + 1
				
		if(canSolve is false)
			minNumWays = -1
			
		return -1

Akin to a Fibonnaci-Sequence
Bottom-Up DP

numWays[n] = Min
			{ 1 + numWays[n-{ith_denom}] }
			
numWay array is pre-initialized to values = -1
With exception of the zero case set to zero by default
			
			
import java.util.*;

class Program 
{

	public static int minNumberOfCoinsForChange(int n, int[] denoms) 
	{
		return budp(n,denoms);
  }
	
	private static int budp(int n, int[] denominations)
	{
		// bottom-up : solve all subproblems of size < n, b4 solving the subproblem of size n
		int[] memo = new int[n+1];
		for(int i = 0; i < n+1; ++i)
		{
			memo[i] = -1;
		}
		memo[0] = 0; // default value
		for(int i = 1; i <= n; ++i)
		{
			int change = i;
			boolean canSolve = false;
			int minNumWays = Integer.MAX_VALUE;					// minimum is if a denom = 1 ( val ) 
			for(int j = 0; j < denominations.length; ++j)
			{
				int childChange = change - denominations[j];
				if(childChange >= 0)	// valid index here
				{
					int childNumWays = memo[childChange];
					if(childNumWays != -1 && ( childNumWays + 1 ) < minNumWays)
					{
						canSolve = true;
						minNumWays = childNumWays + 1;
					}
				}
			}
			if(canSolve == false)
			{
				minNumWays = -1;
			}
			memo[i] = minNumWays;
		}
		return memo[n];
	}	
}
