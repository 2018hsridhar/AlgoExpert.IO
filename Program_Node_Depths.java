import java.util.*;

class Program_Node_Depths {

	
	// This is the recursive approach : typically either head-recursion or tail-recursion
	// Go go preorder, in-order, or post-order as well too
	// Sum up each level here : 
	// Can try to be head recursive as well 
	// Aim to avoid a global recursive variable as well too
	// May be able to do an modification in-place, if we want to do this querying often as well!
	// Provide helper method to pass in depth too, over the state
	
  public static int nodeDepths(BinaryTree root) {
		int nodeDepth = 0;
		if(root == null)
			return 0;
		return helper(root,0);
	}
	
	private static int helper(BinaryTree root, int depth)
	{
		if(root == null)
			return depth;
		int sum = depth;
		if(root.left != null)
		{
			sum += helper(root.left, depth + 1);
		}
		if(root.right != null)
		{
			sum += helper(root.right, depth + 1);
		}
    return sum;
  }
  
  
  
  
  import java.util.*;


/*
Let N := total number of nodes in the binary tree here
Worst case = fully unabalcned tree
balanced tree = o(H) where H = log(N) here
Time Complexity = O(N)
Space = O(N) for recursive helper call stack, but O(1) in-memory
But due to in-place modification, we can run this as a query at a large amount for sure, given the root of any tree OR subtree as well
*/

class Program {

	// Can we reduce the amount of state passed here
	// Can we do an 'in-place' modification as well here?

  public static int nodeDepths(BinaryTree root) 
	{
		if(root == null)
			return 0;
		replaceValues(root, 0);
		return root.value;
  }
	
	// Recursion lends itself to some really good inductive arguements as well!
	private static int replaceValues(BinaryTree root, int height)
	{
		if(root == null)
			return 0;
		
		// Terminating at leaf node
		if(root.left == null && root.right == null)
		{
			root.value = height;	
			return root.value;
		}
		root.value = height;
		if(root.left != null)
		{
			root.value += replaceValues(root.left, height + 1);
		}
		if(root.right != null)
		{
			root.value += replaceValues(root.right, height + 1);		
		}
		return root.value;
	}
	

  static class BinaryTree {
    int value;
    BinaryTree left;
    BinaryTree right;

    public BinaryTree(int value) {
      this.value = value;
      left = null;
      right = null;
    }
  }
}


  static class BinaryTree {
    int value;
    BinaryTree left;
    BinaryTree right;

    public BinaryTree(int value) {
      this.value = value;
      left = null;
      right = null;
    }
  }
}
