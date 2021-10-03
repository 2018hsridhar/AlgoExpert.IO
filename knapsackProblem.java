/*

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



*/

import java.util.*;

class Program {
  public static List<List<Integer>> knapsackProblem(int[][] items, int capacity) {
    // Write your code here.
    // Replace the code below.
    List<Integer> totalValue = Arrays.asList(10);
    List<Integer> finalItems = Arrays.asList(1, 2);
    var result = new ArrayList<List<Integer>>();
    result.add(totalValue);
    result.add(finalItems);
    return result;
  }
}
