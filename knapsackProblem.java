Each subarray holds two integer values and maps to an item
	int_1 : item's value
	int_2 : item's weight
	
Given an int that represents the maximum capacity of the knapsack
Fit as many items as possible without EXCEEDING the capacity, while maximizing combined value
Only have one item of each at thy disposal
List<List<Integer>> can generalize to List<Integer, List<Integer>>
	Kinda artificially impose a tupel structure here as well too
Can return ANY combination as well too!

Can be a bit difficult to assess if a problem is greedy or bottom-up DP, as they both leverage
the optimal substrcture property underneath. But greedy does it locally -> bottom-up DP is exhaustive

Yes - each level is a n-ary tree ( # choices that can be made ) -> BUT, those nodes for which we can NOT go further
themselves are terminating nodes/leaves. We still have to contemplate worst case trees though
such as {1} for capacity=5. Thus, we make the worst case arguement for tree level being O(H) 
where H := (capacity/min_choice) + 1


JAVA8 has a "var" keyword now ( auto ) ?

Base case handling as well

Recursive Computational Complexity : 
Let C := capacity level
Let H := (capacity/min_choice) + 1
Let M := number of choices we can make ( from the set ) ( M = 4 = a constant though )
Time = O(4^H)
Space = O(H)

Top-Down Memo Computational Complexity : 
Time = O()
Space = O(C)

Bottom-up DP Computational Complexity : 
Time = O(C)
Space = O(C)

fib(5) = fib(4) + fib(3)
but fib(4) and fib(3) calculated
So fib(5) was a single recrusive call ( non-memoized ) 
but we do array lookups for the sequence in const time
so fib(5) takes O(1) time ( for non-memoized call)

Now in the future, fib(6) = fib(5) + fib(4)
but fib(5) is a memoized call, so it's O(1) anyways
But fib memoized seems inductive : we knows it's 2 calls per each level

[1,2],[4,3],[5,6],[6,7] => <value,weight>
Given capacity ( weight ) : knapsack is a weighted problem!
Pseudocode for recursvie
For TD conversion later, have a return value ( vs. a global)
Return the current values as well
Sure - indies don't have to be ordered, BUT, still not intuitive to cache
	helper(items, capacity, value, indices)
Take out a parameter, and in lieu, model it as a head recursive equation instead
Might have to return a List<List<Integer>> : ok this is just getting annoying

	
	helper(items, 10,{})
	Max{
		1 + helper(items,8.{0})
		4 + helper(items,7,{1})
		5 + helper(items,4,{2})
		6 + helper(items,3,{3})
					MAX
					{
						4+helper(items,1,{3,0})
						1+helper(items,0,{3,1})
					}
		}
Issue of deep copying these sets as well!

Do it head recursive -> check parent value at this point as well, BEFORE executing the children

Edge case Testing : 
(A) [1,2],[4,3],[5,6],[6,7] , 10 => {10, [3,7]}
(B) [1,2],[6,7]]  => {9, [6,7], [1,2]} VS {5, [1,2],[1,2],[1,2],[1,2],[1,2]}
	> in this case, max value is 9, for capacity = 10. 



So DP needs following memory requirements : 
(a) An array of List<Integers> for indices optimzed per value
(b) An array of ints for indices corresponding to their target capacities

Also remember - this is [0/1] knapsack : not an infinite number of each (value,weight) pairing!
Consider a set -> key removal/key insertion be quick here


import java.util.*;

class Program {
	public static List<Integer> maxValList;
	public static int maxValue;
	public static List<Integer> maxIndices;
	
  public static List<List<Integer>> knapsackProblem(int[][] items, int capacity) 
	{
		// [1] initialize globals to update -> max value and its indices
		maxIndices = new ArrayList<Integer>();
		maxValue = 0;
		maxValList = new ArrayList<Integer>();
		
		// [2] Set up initial used Items ( all zeroes ) and initial index set ( an empty list ) 
		// Invoke root recursive call here
		int n = items.length;
		int[] usedItems = new int[n];
		for(int i = 0; i < n; ++i)
			usedItems[i] = 0;
		List<Integer> indices = new ArrayList<Integer>();
		helper(items, usedItems, capacity, 0, indices);

		// [3] Add information to list ending here
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		maxValList.add(maxValue);
		result.add(maxValList);
		result.add(maxIndices);
    return result;
  }
	
	public static void helper(int[][] items, int[] usedItems, int capacity, int value, List<Integer> indices)
	{
		if(capacity < 0)
		{
			return;
		}
		else if ( capacity == 0)
		{
			if(value > maxValue)
			{
				maxValue = value;
				maxIndices = indices;
				return;
			}
		}
		else
		{
			int numItems = items.length;
			for(int i = 0; i < numItems; ++i)
			{
				int[] item = items[i];
				int curValue = item[0];
				int curWeight = item[1];
				if(usedItems[i] == 0)
				{
					int reducedCapacity = capacity - curWeight;
					if(reducedCapacity < 0)
					{
						// check current state ( as it might be locally good enough ) 
						if(value > maxValue)
						{
							maxValue = value;
							maxIndices = indices;
							return;
						}
					}
					else
					{
						int nextValue = value + curValue;
						// Iterate over children -> engender another used items list, per child ( ref to figure ) 
						int[] reducedItems = new int[numItems];
						for(int j = 0; j < numItems; ++j)
							reducedItems[j] = usedItems[j];
						reducedItems[i] = 1;
						List<Integer> nextIndices = new LinkedList<Integer>();
						for(Integer el : indices)
							nextIndices.add(el);
						nextIndices.add(i);
						helper(items, reducedItems, reducedCapacity, nextValue, nextIndices);
					}
				}
			}	
		}
		return;
	}
	
}


