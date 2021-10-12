/*

Provided a pre-order traversal of a BST
Also given a NON-EMPTY array of integers ( has at least one int though ) 
Creates the relevant BST -> returns root node
	Seems recursive in nature for sure
	
Input array := visitation order
Follows BST strictness property : ( LHS, > ) and ( RHS, <= )
Children are either valid BST nodes themselves or None / null

Edge Case Testing
(A) [1] 
(B) [1, 2]
	1
   \
    2
(C)  [2, 1]
	2
 / 
1
(D) [1, 2, 3, 4]
(E) [4, 3, 2, 1]
(F) [1, 1, 1 ]
(G) [2, 1, 3]
	2
 / \
1   3
(H)
(I)

Yes in BST initialization, both leftPtr = rightPtr = null, and we just have
the value constructor here

*/

import java.util.*;

class Program {
  // This is an input class. Do not edit.
  static class BST {
    public int value;
    public BST left = null;
    public BST right = null;

    public BST(int value) {
      this.value = value;
    }
  }

	// Is given an an array list, but let us treat as if it were an array instead
  public BST reconstructBst(ArrayList<Integer> preOrderTraversalValues) 
	{
		int n = preOrderTraversalValues.size() - 1;
		BST root = reconstructBst(preOrderTraversalValues, 0, n);
		return root;
  }
	
	
	// BST algos tend to be Divide-and-Conquer/Recursive with Optimal Substructure Property as well
	// No overlapping subproblems, however
	// Shortening the ranges entails convergence here
	// Indices could be messing up here ( WRT convergence of subproblems ) . Close in tight bounds correctly!
	
	public BST reconstructBst(ArrayList<Integer> preOrderTraversalValues, int low, int high) 
	{
		System.out.printf("low = %d \t high = %d \n", low, high);
		BST root = null;
		// [1] Perform search for the next greater element in the BST input
		if(low > high)
			return null;
		else if ( low == high)
		{
			int value = preOrderTraversalValues.get(low);
			root = new BST(value);
			return root;
		}
		else
		{
			int value = preOrderTraversalValues.get(low);
			root = new BST(value);
			
			// locate next maximal element : if idx = -1, then now maximal here
			int nextMaximalIdx = -1;
			for(int i = low + 1; i <= high; ++i) // change inequality
			{
				if(preOrderTraversalValues.get(i) >= value)	// other inequality
				{
					nextMaximalIdx = i;
					break;
				}
			}
			
			if(nextMaximalIdx == -1)
			{
				root.left = reconstructBst(preOrderTraversalValues, low + 1, high);
				root.right = null;
			}
			else
			{
				root.left = reconstructBst(preOrderTraversalValues, low + 1, nextMaximalIdx - 1);
				root.right = reconstructBst(preOrderTraversalValues, nextMaximalIdx, high);
			}
		}
		return root;
  }
}
