/*
Input : binary tree
Output : sum of all it's subtree's node depths
nodes can point to null/0-addr or not

Distance is zero indexed as well 
Break down into each subtree too


Strategies : Recursion, DP, Traversal, Top-Down, Bottom-Up

Complexity
Let N := #-trees in the input
Let H := height of the tree ( log(n) balanced, n worst-skew case ) 
Time = O(N)
Space = O(1) ( explicit ) O(H) ( implicit ) 
Further argue : each vertex visited exactly, and only exactly, once
Interestingly is a top-down algorithmic approach!

Ask if candidate can arrive at a single pass solution as well

*/

import java.util.*;

class Program {
  public static int allKindsOfNodeDepths(BinaryTree root)
	{
		if(root == null)
		{
			return 0;
		}
		return dfs(root, 0);
	}
	
	private static int dfs(BinaryTree root, int depth)
	{
		int initSum = 0;
		if(root == null)
		{
			return 0;
		}
		else
		{
			initSum += (depth*(depth+1))/2;
			if(root.left != null)
			{
				initSum += dfs(root.left, depth + 1);
			}
			if(root.right != null)
			{
				initSum += dfs(root.right, depth + 1);
			}
		}
		return initSum;
  }

  static class BinaryTree {
    int value;
    BinaryTree left;
    BinaryTree right;

		// Always notice how in any class, constructor method accessibility remains "public"
    public BinaryTree(int value) {
      this.value = value;
      left = null;
      right = null;
    }
  }
}
