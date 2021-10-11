/*
Topics : Divide-and-conquer, tree, depth-first-search, breadth-first-search, recursion

Given three nodes contained in the same Binary Search Tree
Return if either nodeOne or nodeThree is an ancestor of nodeTwo, as well as a descendant of nodeTwo
	-> [node_three, node_one] OR [node_one, node_three] = [ancestor, descendant]
Guaranteed the uniqueness of their nodes, as well as them never pointing to NULL
Each BST node has an integer value
Nodes are valid ;= assume they lie in the BST as well
Nodes satisfy the BST property

Nodes themselves are addresses/pointers = compare addresses
We do have BST property - expect a O(logn) search as well
	- ordering of (node_1, node_2, node_3 ) does NOT matter here
	
In the pseudocode, exec the following : 

boolean dfs(Node src, Node dst)
	if(src == null)
		return false;
	if(src == dst)
		return true;
	else
		if(src.val < dst.val)
			dfs(src.right,dst)
		else
			dfs(src.left,dst)
			
Goal now : how to do in O(1) constant space?
	iterative dfs ( over recursive dfs ) ?
	remember that other guy's proposal when you coded with him on iteration?
	
			
Complexity 
Let N := num_nodes(BST)
Let H := height(BST) ( log_2(n) avg case, n worst case ) 
Time = O(N)
Space = O(H)

Case Testing : 
(A)
(B)
(C)
(D)
(E)
(F)


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

  public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) 
	{
		boolean firstOrder = dfs(nodeOne,nodeTwo) && dfs(nodeTwo, nodeThree);
		boolean secondOrder = dfs(nodeThree,nodeTwo) && dfs(nodeTwo, nodeOne);
		boolean satisfiedOrderProperty = (firstOrder || secondOrder);
		return satisfiedOrderProperty;
  }
	
	private boolean dfs(BST src, BST dst)
	{
		boolean res = false;
		if(src == dst)
			return true;
		if(src == null)
			return false;
		else
		{
			if(src.value < dst.value)
				res = dfs(src.right, dst);
			else
				res = dfs(src.left,dst);
		}
		return res;
	}
	
}
