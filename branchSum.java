
/*

Write a function that takes in a Binary Tree and returns a list of 
its branch sums ordered from leftmost branch sum to rightmost branch sum.

// Have that summation be locally scoped and passed along method signature




dfs(1, 0)
	dfs(2, 1)
		dfs(4, 3)
			dfs(8, 7)	
				dfs(15) : done here
			dfs(9)
		dfs(5)
			dfs(10)
	dfs(3)
		dfs(6)
		dfs(7)


tree =     1
        /     \
       3       4
     /   \    /  \
    7     8  10   11
  /  \   /
 15  16  18

Sample Input
tree =     1
        /     \
       2       3
     /   \    /  \
    4     5  6    7
  /   \  /
 8    9 10
Sample Output
[15, 16, 18, 10, 11]
// 15 == 1 + 2 + 4 + 8
// 16 == 1 + 2 + 4 + 9
// 18 == 1 + 2 + 5 + 10
// 10 == 1 + 3 + 6
// 11 == 1 + 3 + 7 of all values in a Binary Tree branch. 
A Binary Tree branch is a path of nodes in a tree that starts at the root node and ends at any leaf node.

5 total branches for the tree

1 -> childless root
Assume binary tree won't be null : can be a single node tree
Do NOT know a maximum number of nodes here
ordered from leftmost branch sum to rightmost

DFS : recurisvely build the summations
Explore the left before exploring the right
Stack based approach ( recurisvely or iteratively ) 

Terminating case : check if the node is a leaf node ( left and right child = NULLPTRs ) .

Complexity : 
Let N := number of nodes in the binary tree
Let H = log(N) := height of the tree
	> balanced : H = log(N)
	> worst case ( SLL ) : H = N
Time = O(N)
Space = O(H)
	Stack in iterative version
	
Avoiding the stack frames/activation records for the recursive approach
Try to avoid overwriting the existing tree here as well


*/

import java.util.*;

class Program {
  // This is the class of the input root. Do not edit it.
  public static class BinaryTree {
    int value;
    BinaryTree left;
    BinaryTree right;

    BinaryTree(int value) {
      this.value = value;
      this.left = null;
      this.right = null;
    }
  }
	
	// Write up another encapsulating object?
	public static class encap {
		BinaryTree root;
		int sum;
		
		encap(BinaryTree root, int sum)
		{
			this.root = root;
			this.sum = sum;
		}
	}

	
  public static List<Integer> branchSums(BinaryTree root) 
	{
		if(root == null)
			return new LinkedList<Integer>();
		List<Integer> sums = dfs_iterative(root);
    return sums;
  }
	
	public static List<Integer> dfs_iterative(BinaryTree root)
	{
			List<Integer> sums = new LinkedList<Integer>();
			Stack<encap> stk = new Stack<encap>();
			stk.add(new encap(root,0));
			while(!stk.isEmpty())
			{
				encap cur = stk.pop();
				BinaryTree myRoot = cur.root;
				int myBranchSum = (cur.sum + myRoot.value);
				if(myRoot.left == null && myRoot.right == null)
				{
					sums.add(myBranchSum);
				}
				else if ( myRoot.left == null && myRoot.right != null)
				{
					stk.push(new encap(myRoot.right, myBranchSum));
				}
				else if ( myRoot.left != null && myRoot.right == null)
				{
					stk.push(new encap(myRoot.left, myBranchSum));
				}
				else
				{
					stk.push(new encap(myRoot.right, myBranchSum));
					stk.push(new encap(myRoot.left, myBranchSum));
				}
			}
			return sums;
	}
}
