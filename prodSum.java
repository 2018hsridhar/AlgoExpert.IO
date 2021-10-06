import java.util.*;

class Program {
  // Tip: You can use `element instanceof ArrayList` to check whether an item
  // is an array or an integer.
  public static int productSum(List<Object> array) 
	{
		int prodSum = recurse(array, 1);
		return prodSum;
	}
	
	private static int recurse(List<Object> array, int depth) 
	{
		int prodSum = 0;
		int n = array.size();
		prodSum = unroll(0,n,array,depth);
		return prodSum;
  }

	// Head recursive method
	// Terminating condition : i == n
	private static int unroll(int i, int n, List<Object> array, int depth)
	{
			// [1] Work in parent
			if(i == n)
				return 0;
			int prodSum = 0;
			Object element = array.get(i);
			if(element instanceof ArrayList)
				prodSum +=  ( depth * recurse((List<Object>)element, depth+1) ); 
			else
				prodSum +=  ( depth * (Integer)element );
		
			// [2] Work in rest of the recursive subproblem
			prodSum += unroll(i+1,n,array,depth);
			return prodSum;
	}
	
}


Write up a function titled productSum
Special Array : non-empty ( always has some element )
	* do a NULL check or a check if array.size() = 0
	[]=1
Contains either Integer or Special Arrays nested within
<x,y,*,+,-,...>
productSum = sum(els*depth_level)

[1,[2,3],4] -> 1+(2+3)*2 + 4, correct?
First depth level = 1

[1,[2,[3]]
	1 -> depth = 1
	2 -> depth = 2
	3 -> depth = 3

Test Cases : 
(A) [1,[2,[3]]
	=> 14
(B) [1,[2,[1,1],1],4]
	=> 15
(C) [1,2,3,4]
	=> 10
(D) [[[1]]]
	=> 3
(E) 
(F)

Recursive approach since this is nested
Iterative approach

Complexity : 
Let N := len(array), #-elements
Let H := deepest level we nest
Time = O(N)
Space = O(H) [ implicit function call stack ] 
				O(1)
				
Recursive-Recursive
	O(HN) implicit call stack space -> quadratic time complexity
Iterative-Recursion

pass in 2 parameters for the recursion

[1,[2,[1,1],1],4]
productSum([1,[2,[1,1],1],4])
	recurse([1,[2,[1,1],1],4],1)
		1*(1 + recurse([2,[1,1],1], 2) + 4)
						2*(2 + recurse([1,1], 3) + 2)
								3*(1 + 1)
								========================
								6
						2*(2+6+2)  = 20
				1*(1+20+4) = 25
		25
	25

Avoid type changing?

	

