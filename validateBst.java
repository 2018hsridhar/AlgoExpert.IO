Validate BST
Write a function that takes in a potentially invalid Binary Search Tree (BST) 
and returns a boolean representing whether the BST is valid.

Each BST node has an integer value, a left child node, and a right child node. 
A node is said to be a valid BST node if and only if it satisfies the BST
property: its value is strictly greater than the values of every node to its 
left; its value is less than or equal to the values of every node to its right; 
and its children nodes are either valid BST nodes themselves or None / null.

A BST is valid if and only if all of its nodes are valid BST nodes.

Sample Input
tree =   10									(leftmost of the left, root), ( root, rightmost of the right )
       /     \
      5      10						
    /   \   /   \
   2     5 13   22
 /           \
1            14


				a
			 / \
			b   c
		 / \ / \
    d  e f  g
	 /     \
	h       i
	
	b : (-inf, 10]
	c : [10,+inf)
	
	f : [10,15]
	g : [24,+inf)
	
Range-based way : 

	(-inf,inf)
	go to left -> new range = ( root.left, root.val )
	go to right -> new range = (root.val,root.right)
	



Sample Output
true

- strategies : recursive, iterative ( stk/queue - DFS/BFS )
- tree travesal problem : top-down OR bottom-up
- all nodes must be valid BSTs
- Given tree may be a BST or not a BST

Left subtree and right subtree must be a BST
curr node : comparison to the left and right, if they exist, and check I am "sandwhiched" in between them
	node.left.val < node.val <= node.right.val
	node val > rightmost child left subtree AND <= leftmost child right subtree
	Return this on the callback
	
COMPLEXITY
Let N := number of nodes in the BST
Let H := height of the BST ( log_2(N) balanced, (N) skew )
TIME = O(N)
SPACE = O(H) ( call stack -> recursive ) O(1) ( AUX ) 

TEST CASES
(A) Can get single node by itself ( no left/right child ) 








import java.util.*;

class Program {
  public static boolean validateBst(BST tree) 
	{
		int lower = Integer.MIN_VALUE;	// -inf
		int upper = Integer.MAX_VALUE;	// inf
		boolean res = checkNodesAreBST(tree, lower, upper);
		return res;
  }
	
	public static boolean checkNodesAreBST(BST tree, int lower, int upper)
	{
		if(tree == null)
		{
			return true;
		}
		boolean res = true;	// the default value
		if(lower <= tree.value && tree.value < upper)	// EXERT CAUTION
		{
				res = res && checkNodesAreBST(tree.left, lower, tree.value) &&
					checkNodesAreBST(tree.right, tree.value, upper);
		 }
		 else
			res = false;
		 return res;
	}
	

  static class BST {
    public int value;
    public BST left;
    public BST right;

    public BST(int value) {
      this.value = value;
    }
  }
}








