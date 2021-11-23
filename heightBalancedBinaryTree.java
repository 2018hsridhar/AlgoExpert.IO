tree = 1
     /   \
    2     3
  /   \     \
 4     5     6
     /   \
    7     8
	
height diff <= 1 -> True 
	
Give a binary tree
Return tree if it is a height balanced
Is height balanced if each node in the tree 
if diff of height(lst), 
height(rst) = 1
Each binary tree has a left node, or a right node

INPUT :
1. Can tree be null or empty?
	Yes can be null or empty
2. 2 children, @ max, per each node
3. Node vals are all unique ( no duplicates ) 

PSEUDOCODE : 

Traversal orders ( pre/post/level ), BFS/DFS ( recursive ) , top-down or bottom-up
Must check each node for its two subtres for balance
Desire a function to solve for height of trees
Must solve both children before comparison operation takes place
Height(node ) = 1 + Math.max(height(left), height(right))


boolean[] result = new boolean[1];
result[0] = true;

boolean heightBalancedBinaryTree(BinaryTree tree) 
{
	if(tree == null || tree.left == null && tree.right == null)
		return true;
	helper(tree);
	return result[0];
}

int recurse(BinaryTree tree)
{
	if(tree == null)
		return 0
	int heightLeft = recurse(tree.left)
	int heightRight = recurse(tree.right)
	int diff = (int)Math.abs(heightLeft - heightRight)
	if(diff > 1)
		result[0] = false
	int curHeight = 1 + Math.max(heightLeft,heightReight)
	return curHeight
}


COMPLEXITY
Let N := #-nodes in the tree
Let H := height of the tree ( log_2(n) balanced, n worst ) 
Time = O(N)
	worst case : all nodes except for the root are height balanced
Space = O(1) ( explicit ) 
				O(H) ( func call stack space / implicit space ) 

parent
|-left
|-right

TEST CASES
(A) null, single node tree
(B) 1
	  |-2
		|-3
		TRUE
(C) 1
		|-2
		  |-3
			  |-4
					|-5
		FALSE
(D) 1
		|-2
			|-4
			|-5
		|-3
			|-6
			|-7
		TRUE
		( perfect tree : every level filled up )  
(E)  1
		 |-2
			|-4
				|-8
					|-10
				|-9
			|-5
		 |-3
			|-6
			|-7
		FALSE







import java.util.*;

class Program {
  // This is an input class. Do not edit.
  static class BinaryTree {
    public int value;
    public BinaryTree left = null;
    public BinaryTree right = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

	// C/C++ instead of JAVA : passing in a pointer to an address instead of an array
	boolean[] result;
  public boolean heightBalancedBinaryTree(BinaryTree tree) 
	{
		result = new boolean[1];
		result[0] = true;
		if(tree == null || tree.left == null && tree.right == null)
		{
			return true;
		}
		recurse(tree);
		return result[0];
	}
	
	public int recurse(BinaryTree tree)
	{
		if(tree == null)
		{
			return 0;
		}
		// Post order traversal : left->right->root
		int heightLeft = recurse(tree.left);
		int heightRight = recurse(tree.right);
		int diff = (int)Math.abs(heightLeft - heightRight);
		if(diff > 1)
		{
			result[0] = false;
		}
		int curHeight = 1 + Math.max(heightLeft,heightRight);
		return curHeight;
	}
	
}

