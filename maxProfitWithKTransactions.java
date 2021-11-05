import java.util.*;

class Program 
{
	public static int maxProfitWithKTransactions(int[] prices, int k)
	{
		int n = prices.length;
		if(k > prices.length) 
			return helper(prices, 0, n-1,prices.length/2);
		return helper(prices, 0, n-1, k);
	}

	// k = 0; return 0
  private static int helper(int[] prices, int lowIdx, int highIdx, int k)
	{
		if(k == 0)
		{
			return 0;	
		}
		if ( lowIdx >= highIdx)
		{
			return 0;
		}
		int n = prices.length;
		int maxProfit = 0;	// don't allows negatives : strict cap
		for(int i = lowIdx; i <= highIdx; ++i)
		{
			int buy = prices[i];
			for(int j = i+1; j <= highIdx; ++j)
			{
				int sale = prices[j];
				int difference = sale - buy;
				// Concern : do we do it from 0 to k-1, or just k-1?
				for(int a = 0; a < k; ++a)
				{
					int remainingProfit = helper(prices, j+1, highIdx, a);
					// System.out.printf("Remaining profit for (%d,%d) = %d\n", j+1, highIdx, remainingProfit);
					int currProfit = difference + remainingProfit;
					if(currProfit > maxProfit)
					{
						maxProfit = currProfit;
					}
				}
			}
		}
		return maxProfit;
	}
}



Input clarification
1. Can have duplicate prices
2. k >= 1
3. n >= 2

Given an array of positive integer denoting price of a single stk on diff days
each idx = own day
Given k \in Int = #-transaction to make
	buy on a date
	sell on another LATER date
	
Returns max profit generatble by buying and selling the stock, given k transactions
One share of stock at a time per day
Can't buy if a share is held

Don't need to use ALL transactions


******
Note that you can only hold one share of the stock at a time;
in other words, you can't buy more than one share of the stock on any given day,
and you can't buy a share of the stock if you're still holding another share. 
Also, you don't need to use all k transactions that you're allowed.
******

( Recursive ) Complexity
Let N := len(prices), K := #-transactions
Time = O(2^N) ( EXPONENTIAL ) 
Space = O(N) ( IMPLICIT ) O(1) Excpliti

( DP ) Complexity
Time = O(N) or O(NK) ( POLY )  { generalize form : O(N^{const} & K^{const}) : O(N^3) = O(NNN) }
Space = O(1) ( IMPLICIT ) O(N) or O(NK) Excplit


Strategies : Recursive, Backtracking, DP, Greedy
----------------
Sample input 
prices = [5, 11, 3, 50, 60, 90]
k = 2

sample output
93 // Buy: 5, Sell: 11; Buy: 3, Sell: 90
-----------------

Problem simplifies when k = 1
Work on a subarray ( [....][k=1])?
		([k=5][k=4]...[k=1])
Given two choices per day 
	Make a purchase ( don't hold ) : sell ( do hold )
	Don't make a purchase	( no hold ) : don't make sale ( even if holding ) 

					0		1		2	 3		4		5
prices = [5, 11, 	3, 50, 60, 90]
					_____________________
						 __________________
						 			_____________
											_________
													_____
Nested Intervals ( pairwise testing ) : O(N^2) pair testing

k = 3 
	buy_0,sell_1 ( solve for k=2, [2,5])
	buy_0,sell_2 ( solve for k=2, [3,5])
	buy_0,sell_3 ( solve for k=2, [4,5])

					
						 ----------------->
prices = [5, 11, 3, 50, 60, 90]
right_most_max
running_difference ( from cur elem )
			=  [90, 90, 90, 90, 90, 0]
diff	=  [85, 79, 87, 40, 30, 0]]


TEST BENCH
(A) [1,2,3,4,5], k = 1
	4 { 5 - 4 = 1 }
(B) [5,4,3,2,1], k = 1
	0
(C) [5,5,5,5,5], k = 1
	0
(D) ( provided ) [5, 11, 3, 50, 60, 90]
	k = 2
(E) [1,10,2,9,3,8,4,7,5,6], k = 5
	25
(F) [1,9,3,5] k = 1
	8
(G) [1,9,3,5] k = 2
	8+2 = 10
(H) [1,10,2,9,3,8,4,7,5,6], k = 3
	21
(H) [1,10,2,9,3,8,4,7,5,6], k = 8
	25
	
(____) the k = 1 case

bottom-up approach : ( right->left) and then by the increasing "k" value

O(N^2*K)
Do not go recalculating the max values for a sale at a given date!  ( greedy strategy ) 
	Update those maxes as we progress

					B		S
prices = [5, 11, (_,_,_,_)]
					B							S			
prices = [5, 11, 3, 50, 60, (__)]
						 B	 S	
prices = [5, 11, 3 (_,_,_)]

Pseducode : 
	public static int maxProfitWithKTransactions(int[] prices, int k)
	{
		int n = prices.length;
		return helper(prices, 0, n, k);
	}

	// k = 0; return 0
  private static int helper(int[] prices, int lowIdx, int highIndx, int k)
	{
		if(k == 0)
		{
			return 0;	
		}
		else if ( lowIdx >= highIdx)
		{
			return 0;
		}
		int n = prices.length;
		int maxProfit = 0;	// don't allows negatives : strict cap
		for(int i = 0; i < n; ++i)
		{
			int buy = prices[i];
			for(int j = i+1; j < n; ++j)
			{
				int sale = prices[j];
				int difference = sale - buy;
				// Concern : do we do it from 0 to k-1, or just k-1?
				for(int a = 0; a < k; ++a)
				{
					int remainingProfit = helper(prices, j+1, n, a);
					int currProfit = difference + remainingProfit;
					if(currProfit > maxProfit)
					{
						maxProfit = currProfit;
					}
				}
			}
		}
		return maxProfit;
	}


	
helper(prices, 0, 6, 5)
	helper(prices, 2, 6, 3)	// drops #-available transactions quickly

[_,_,_,_] : even len : /2
[_,_,_] : odd len / 2













import java.util.*;

class Program 
{
	static int[][][] memo;
	
	public static int maxProfitWithKTransactions(int[] prices, int k)
	{
		if(prices.length == 0)
			return 0;
		int n = prices.length;
		// account for really huge k 
		memo = new int[n][n][k+1]; // 5 transactions : {0,1,2,3,4,5}
		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				for(int a = 0; a <= k; ++a)
				{
						memo[i][j][k] = -1;	// unfilled here
				}
			}
		}
		
		if(k > prices.length) 
			return helper(prices, 0, n-1,prices.length/2);
		return helper(prices, 0, n-1, k);
	}

	// k = 0; return 0
  private static int helper(int[] prices, int lowIdx, int highIdx, int k)
	{
		// Check the memoized cache
		if(memo[lowIdx][highIdx][k] != -1)
		{
			return memo[lowIdx][highIdx][k];
		}
		// Then exec loop
		if(k == 0)
		{
			memo[lowIdx][highIdx][k] = 0;	
			return 0;
		}
		int n = prices.length;
		int maxProfit = 0;	// don't allows negatives : strict cap
		for(int i = lowIdx; i <= highIdx; ++i)
		{
			int buy = prices[i];
			for(int j = i+1; j <= highIdx; ++j)
			{
				int sale = prices[j];
				int difference = sale - buy;
				// Concern : do we do it from 0 to k-1, or just k-1?
				for(int a = 0; a < k; ++a)
				{
					if ( j+1 < highIdx)	// this style preferred : don't go to invalid children in the first place
					{
						int remainingProfit = helper(prices, j+1, highIdx, a);
						// System.out.printf("Remaining profit for (%d,%d) = %d\n", j+1, highIdx, remainingProfit);
						int currProfit = difference + remainingProfit;
						if(currProfit > maxProfit)
						{
							maxProfit = currProfit;
						}
					}
				}
			}
		}
		memo[lowIdx][highIdx][k] = maxProfit;
		return maxProfit;
	}
}

