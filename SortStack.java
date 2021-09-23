/*
Function name = sortStack
Input = ArrayList<Integer>
Output = 

Array itself is a "stack"
Sort in-place ( pass object each time in recursive call -> not extra AUX space ) 
return said object at the end too
Perhaps a depth pointer/index to object

Can access final element
Can not move elements?? 
NOT ALLOWED OTHER DATA STRUCTURES!

Head recursion OR tail recursion here?
Build inductively : partition into set<met_condition> and set<to_explore>

Is this a comparative sort algo? Need to compare two elements to DENOTE which is preceding or not.



COMPLEXITY 
Let N := number of elements in input SLL
Time = O(N^2)
Space = O(N)

EDGE CASES : 
(A)
(B)
(C)
(D)
(E)
(F)
(G)
(H)

BASE/TERMINATING CONDITION
(A) <= 1 element in the ArrayList :: just return
(B) 2 elements in the ArrayList :: perform swap, based on the peek() operation value here

Resembles insertion sort done recursively.
O(N^2) 
	Outer loop for insertion at correct place
	Inner loop for sorting the subarray / smaller "nested" stack
Very large recursive stack space here for sure!

Elements in this stack are NOT guaranteed to be unique ( can have duplicates ) 
Careful with the inequaities here

*/

import java.util.*;

class Program {

  public ArrayList<Integer> sortStack(ArrayList<Integer> stack) 
	{
		// BASE/TERMINATING CASE
		int n = stack.size();
		if(n <= 1)
			return stack;
		
		// Start outer helper at size = n here
		outerHelper(stack, n);
		return stack;
	}
	
	// Guaranteed O(N) calls in outer loop
	public void outerHelper(ArrayList<Integer> stack, int i)
	{
		// Singleton stack here
		if(i <= 0)
			return;
		
		// ( i >= 2 ) At least more than 2 elements in this stack
		int topMost = stack.get(i-1);
		// no overwrite : must strictly remove and add here -> dammit ( in JAVA API ) 
		stack.remove(i-1);
		outerHelper(stack, i-1);
		stack.add(topMost);
		insert(stack,i);
  }
	
	// Let idx be bounded by [0,size-1] here
	public void insert(ArrayList<Integer> stack, int i)
	{
		if(i <= 1) // stack size <= 1 here ( there's no -1 indx => run into exception )
			return;
		int topMost = stack.get(i-1);
		stack.remove(i-1);
		int secondMost = stack.get(i-2);
		stack.remove(i-2);
		if(topMost < secondMost)
		{
			stack.add(topMost);
			insert(stack,i-1);
			stack.add(secondMost);
		}
		else
		{
			stack.add(secondMost);
			stack.add(topMost);
		}
			return;
	}
	
}
