/*

arrayOne = [10, 15, 8, 12, 94, 81, 5, 2, 11]
					                                ^
arrayTwo = [10, 8, 5, 15, 2, 12, 11, 94, 81]
							                            ^
Iter_1 : 
				 arr_1 10 [8,5,2],[15,12,94,81,11]
				 arr_2 10 [8,5,2],[15,12,94,81,11]
						arr_1 8 [5,2],[]
				 		arr_2 8 [5,2],[]
								arr_1 5 [2],[]
				 				arr_2 5 [2],[]
						arr_1 15 [12,11],[94,81]
						arr_2 15 [12,11],[94,81]
								arr_1 12 [11],[]
				 				arr_2 12 [11],[]
								arr_1 94 [81],[]
				 				arr_2 94 [81],[]
										 			
						
true


arrayOne = [10, 12, 8, 15, 94, 81, 5, 2, 11]
					          ^
		    r
		   [8, 5, 2] [12,15,94,81,11]
arrayTwo = [10, 8, 5, 15, 2, 12, 11, 94, 81] 
							     ^
						[8,5,2],[15,12,11,94,81]
						roots are NOT equal
FALSE
         10
       /     \
      8      15
    /       /   \
   5      12    94
 /       /     /
2       11    81   

									 
FALSE

true // both arrays represent the BST below
isBST(root) IF isBST(lst) & isBST(rst)
Strategies : Recursive, D-&-C, DP, Tree Traversal Order, Stack, top-down approach
Base case : single value ( both sublists empty ) -> return true

2 recursive subcalls per recursive call

N := len(array)
Time = (2^N func calls ) * (N time per sublists) = O(N*2^N)
			(N func calls ) * ( N time per sublist creation ) = O(N^2)
	O(N^2)
Space = N ( implicit ) , 2 N space arrays allocated = O(N^2)

BST_3
				 10
						\
             15
            /   \
          12    94
         /     /
        11    81

BST_1
				 10
       /     \
      8      15
    /       /   \
   5      12    94
 /       /     /
2       11    81

 BST_2
 				 10
       /     \
      8      15
    /       /   \
   5      12    81
 /       /    		\ 
2       11    		 94

Check whether these two arrays form the same BST
Do NOT create a BST directly ( not the data structure ) 
Follow the BST property
	vals to left : strictly lower ( < ) root node val 
	vals to right of a node : >= root node val
Order of addition is always left to right

No guarantee that arr lens are same
Arrays can have duplicate values
Arrays can be empty or null

Sort on the inputs : pairwise comparison by index
Self-balancing tree : challenge -> order matters

Assumption in-place merge sort

Complexity : 
Let M := len(arrayOne)
Let N := len(arrayTwo). 
M = N
Time = O(NLogN) + O(NLogN) + O(N) = O(NLogN)
Space = O(N) + O(1) = O(N)



TEST BENCH
(A) arrayOne = [], arrayTwo = []
	TRUE
(B) arrayOne = [], arrayTwo = [10,...]
	FALSE
(C) arrayOne = [10], arrayTwo = [10]
	TRUE
(D) arrayOne = [10], arrayTwo = [15]
	FALSE
	
	
	****
	
(E) arrayOne = [10,15,20], arrayTwo = [15,20]
	FALSE
(H) [10,15,20],[10,20,15]
(F) arrayOne = [10, 15, 8, 12, 94, 81, 5, 8, 8], arrayTwo = [10, 15, 8, 12, 94, 81, 5, 8, 8]
	TRUE
(G)
(H)

// Creating new sublists at each stage
Pseudocode : 
	boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo)	
	{
		if(arrayOne == null || arrayTwo == null)
		{
			return (arrayOne == null && arrayTwo == null);		
		}
		int sz_one = arrayOne.size();
		int sz_two = arrayTwo.size();
		if(sz_one == 0 || sz_two == 0)
		{
			return (sz_one == 0 && sz_two == 0);
		}
		if(sz_one != sz_two)
		{
			return false;
		}
		else 
		{
			int rootOne = arrayOne.get(0);
			int rootTwo = arrayOne.get(0);
			if(sz_one == 1 && sz_two == 1)
			{
				return (rootOne == returnTwo);
			}
			if(rootOne != rootTwo)
			{
				return false;
			}
			else
			{
				List<Integer> lowerVals_one = new LinkedList<Integer>();
				List<Integer> lowerVals_two = new LinkedList<Integer>();
				List<Integer> upperVals_one = new LinkedList<Integer>();
				List<Integer> upperVals_two = new LinkedList<Integer>();
				createSubLists(arrayOne, lowerVals_one, upperVals_one);
				createSubLists(arrayTwo, lowerVals_two, upperVals_two);
				boolean lstSameBST = sameBsts(lowerVals_one, lowerVals_two);
				boolean rstSameBST = sameBsts(upperVals_one, upperVals_two);
				return ( lstSameBST && rstSameBST);
			}
		}
	}
	
	
	public static void createSubLists(List<Integer> input, List<Integer> lowerVals, List<Integer> upperVals)
	{
		int sz = input.size();
		int root = input.get(0);
		for(int i = 1; i < sz; ++i)
		{
			if(input.get(i) < root)
			{
				lowerVals.add(input.get(i));
			}
			else
			{
				upperVals.add(input.get(i));
			}
		}
	}

*/

import java.util.*;

class Program {
  public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo)	
	{
		if(arrayOne == null || arrayTwo == null)
		{
			return (arrayOne == null && arrayTwo == null);		
		}
		int sz_one = arrayOne.size();
		int sz_two = arrayTwo.size();
		if(sz_one == 0 || sz_two == 0)
		{
			return (sz_one == 0 && sz_two == 0);
		}
		if(sz_one != sz_two)
		{
			return false;
		}
		else 
		{
			int rootOne = arrayOne.get(0);
			int rootTwo = arrayTwo.get(0);
			if(sz_one == 1 && sz_two == 1)
			{
				return (rootOne == rootTwo);
			}
			if(rootOne != rootTwo)
			{
				return false;
			}
			else
			{
				List<Integer> lowerVals_one = new LinkedList<Integer>();
				List<Integer> lowerVals_two = new LinkedList<Integer>();
				List<Integer> upperVals_one = new LinkedList<Integer>();
				List<Integer> upperVals_two = new LinkedList<Integer>();
				createSubLists(arrayOne, lowerVals_one, upperVals_one);
				createSubLists(arrayTwo, lowerVals_two, upperVals_two);
				boolean lstSameBST = sameBsts(lowerVals_one, lowerVals_two);
				boolean rstSameBST = sameBsts(upperVals_one, upperVals_two);
				return ( lstSameBST && rstSameBST);
			}
		}
	}
	
	// O(N) time, O(1) space ( explicit and implicit ) 
	public static void createSubLists(List<Integer> input, List<Integer> lowerVals, List<Integer> upperVals)
	{
		int sz = input.size();
		int root = input.get(0);
		for(int i = 1; i < sz; ++i)
		{
			if(input.get(i) < root)
			{
				lowerVals.add(input.get(i));
			}
			else
			{
				upperVals.add(input.get(i));
			}
		}
	}
}



