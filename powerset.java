/*
Write whatever you want here.

array = [1, 2, 3]

[[], [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]] 

array = [100,200,300] len = 3

Goal : find the powerset of a given array/list
Powset := Will be different kindas of combinations by elements of the array

array = [1,1,1] => 
Guaranteed uniqueness of element
Can have a null array OR an empty array

n is guaranteed to be [1,6]

Recursive approach -> generating combinations
Null subset is a valid element of the powerset

// remaining = n ( 3) 
// remaining == 1 :  we are done

dfs(pow, {1,2,3},3)
	dfs(pow, {2,3},2)
			dfs(pow, {2},1)
			dfs(pow, {3},1)
	dfs(pow, {1,3},2)
			dfs(pow, {1},1)
			dfs(pow, {3},1)
	dfs(pow, {1,2},2)
			dfs(pow, {1},1)
			dfs(pow, {2},1)


Nested intervals : Bound for (i,j) here
{}
{1},{2},{3},{4}
{1,2},{1,3},{1,4}
			{2,3},{2,4}
						{3,4}
{1,2,3},{1,2,4}
				{2,3,4}
{1,2,3,4}

											1											2							3
										/	 |	\								 /  \						|
								(1,2)	 (1,3) (1,4)			(2,3) (2,4)			(3,4)
								/		\		|
					(1,2,3) (1,2,4)	
						 |				(1,3,4)
						(1,2,3,4)
						
						
// Assume each elem can start a powset
// Establish bound = len ( len - 1)

len	len-1
1		0
2		1
3		2
4		3
	
Pseudocode 
Head recursive : generate the powerset @ a given level, and then generate for the next level

	powSet.add(new ArrayList<Integer>());
	helper(powSet,array,0,len(array))
	return powSet;

	private void helper(List<List<Integer>> powSet, List<Integer> array, int leftIdx, int n) :
		if(level > n)
			return;
		for(int i = leftIdx; i < n; ++i)
			List<Integer> subset = new List<Integer>();
			subset.add(array.get(i));
			powSet.add(subset)
			helper(powSet,subset,leftIdx + 1, n)
	
	
Testing
(A) [100,200,300]
	[[], [100], [200], [100,200], [100,300], [200,300], [100,200,300]]
(B) [1,2,3]
	[[], [1], [2], [1,2], [1,3], [2,3], [1,2,3]]
(C) [1]
	[[], [1]]
(D) [1,2,3,4,5,6]
(E) [1,2]
	[[], [1], [2], [1,2]]


Complexity : 
Let N := # elements in input array
Time = O(2^N) for EXP
			O(N*2^N) : N for the deep-copying
Space = O(N) ( implicit ) 
				O(1) ( explicit ) 
				O(N*(2^N))	: 2^N is for the deep-copying ( making the new subsets ) 
				
				
				
[1,2, 3] : depth = 3			
helper(pS, input, {}, 0, 0)
	helper(pS, input, {1}, 1, 1)
		helper(pS, input, {1,2}, 2, 2)
			helper(pS, input, {1,2,3}, 2, 3)
		helper(pS, input, {1,3}, 2, 3)
	helper(pS, input, {2}, 1, 2)
		helper(pS, input, {2,3}, 2, 3)
	helper(pS, input, {3}, 1, 3)
	

*/

import java.util.*;

class Program {
  public static List<List<Integer>> powerset(List<Integer> array) 
	{
		List<List<Integer>> powSet = new ArrayList<List<Integer>>();
		List<Integer> parentSet = new ArrayList<Integer>();
		powSet.add(parentSet);
		helper(powSet, array, parentSet, 0);
		return powSet;
  }
	
	private static void helper(List<List<Integer>> powSet, List<Integer> array, List<Integer> parentSet, int leftIdx)
	{
		int n = array.size();
		if(leftIdx >= n)
			return;
		for(int i = leftIdx; i < n; ++i)
		{
			List<Integer> childSubset = new ArrayList<Integer>();
			for(Integer x : parentSet)
				childSubset.add(x);
			childSubset.add(array.get(i));
			powSet.add(childSubset);
			helper(powSet,array, childSubset, i + 1);
		}
	}
	
	
	
	
}

