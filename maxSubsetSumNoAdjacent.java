/*
Strategies
	- DP / Recursive
	- Greedy				X ( loose adjacency info after in-place mod ) 

Write whatever you want here.
Non-negataive numbers can have duplicates too
Array len >= 1 ( handle 0 / nulls too ) 
Let's perform a sort ahead of time
array = [75, 105, 120, 75, 90, 135]
array = [75, 105, 120, 75, 90, 135]

your goal is to find the maximum sum on non adjacent elements 

output = 330 but why? 75 + 120 + 135 = 210+120 = 330
Subsequence (s1,s2,..sk) such that sum(i)s_i is maximized
 
 0 1 2  3  4
[1 10 3 20 5]
len(array) = 4
{1},{10},{3}, {20}
{1 3} = 4
{1,20} = 21
{10,20} = 30
{3,20} = 23
MAX(subsets) = 30

 0 1 
[1 10 ]
 0
[1]

2 choices @ a given index
	consider the element OR not select for it
subset({1,10,3,20,5})
	1+subset({3,20,5})
			1+3+subset({5})
			1+20+subset() *****
	10+subset({20,5})
			10+5+subset()
			10+20+subset() ****
	<- back track up the value and consider the maximal
	
 0 1 2  3  4
[1 10 3 20 5]
[a b  c  d e]
	a = {1,10,3,20,5}
	b = 	{10,3,20,5}
	c = 		 {3,20,5}
	d = 		   {20,5}
	e = 					{5}
	
		(C) 3+max{5}, 			max({20,5})
		(B) 10+max({20,5}), max{3,20,5})
		(A) 1+max({10,3,20,5}), max{10,3,20,5})

	(C) two_plus = e, one_plus = d
	(B) two_plus = d, one_plus = c
	(A) two_plus = c, one_plus = b
	
	two_plus will be the former "1 plus" : go an index back
	one plus = current answer
	
Optimal Substructure Property & Overlapping Subproblems 
return max across the DP row
DP[i] = 
{
	1, in the base case ( last index always prefilled )
	max(DP[i],DP[i+1]), if 2 elements only
	MAX(A[i] + DP[i+2], DP[i+1]) provided i+2 is in bounds as well, for i 
}

 0 1 2 
MAX([3 20 5]) = MAX(3 + DP[5]), DP[20,5])


Complexity
Let N := len(array)
Let K := len(subset). 1 <= k < n
Time (Recursive ) = EXP = O(2^n) (n%2)
Space = (implicit ) O(2^n), (explicit) O(1)
Time (DP ) = LINEAR = O(N)
Space = (DP ) = O(N) ( better = O(1))

Edge Case Testing : 
(A) [1 10 3 20 5]

n = 5; n-3 = 2
		 [1  10 3  20 5]
DP = [30, 30, 20, 20, 5]
			0  1  2  3  4
i = 2	max(a[2] + DP[4], DP[3]) = max(3+5, 20)
i = 1 max(a[1] + DP[3], DP[2]) = max(10+20, 20)
i = 0 max(a[0] + DP[2], DP[1]) = max(1+20, 30)
			
(B) [75, 105, 120, 75, 90, 135]

	[_, _, _, 210, 135, 135]

	120+135 = 255
	
	Do it in constant space
		-> keep two subset sums as we traverse.
	*/
	

import java.util.*;

class Program {
  public static int maxSubsetSumNoAdjacent(int[] array) 
	{
		if(array == null || array.length == 0)
			return 0;
		int n = array.length;
		int[] DP = new int[n];
		
		// Quick handling of base cases
		if(array.length == 1)
			return array[0];
		if(array.length == 2)
			return Math.max(array[0], array[1]);
		
		// 1. Fill out the base cases
		int DP_one_plus = Math.max(array[n-1],array[n-2]);
		int DP_two_plus = array[n-1];
		int mss = 0;
		for(int i = n-3; i >= 0; --i)
		{
			mss = Math.max(array[i] + DP_two_plus, DP_one_plus);
			DP_two_plus = DP_one_plus;
			DP_one_plus = mss;
		}
    
    return mss;
  }
}

	
