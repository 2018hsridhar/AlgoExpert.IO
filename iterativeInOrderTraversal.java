Write a func that takes in a binary tree, which had an additional pointer
to its parent node

Traverse it iteratively using IN-ORDER traversal
Do NOT use recursion or the func call stack

During traversal, the callback func should be called upon each node
Printing the call back  : do not use console.log() here

Each binary tree node has an integer value, left, right, parent pointer


CLARIFICATION
	1. Binary tree : always a valid btree ( NOT A BST ) 
	2. Can be duplicate values in nodes
	3. If null -> just return
	4. Values can be negative ( don't assume MSB signage toggleable ) 
	

tree =    1
       /     \
      2        3
    /  \     /   \
   4    5   6     7
     \
      9
			 \
			 	10
					\
					 11
			
	9->4->2 ( start at 2 )
	4->9 and 4.right = 9 ( 9's parent's right = 9 ) 
	
	
	
PSEUDOCODE : 

	  public static void iterativeInOrderTraversal(
      BinaryTree tree, Function<BinaryTree, Void> callback) 
		{
			BinaryTree curr = tree;
			if(tree == null)
			{
				return;
			}
			while(curr != null)
			{
				if(curr.left != null)	// potential trip up : don't explore this again
				{
					curr = curr.left;
				}
				else if(curr.left == null)
				{
					// Leaf node case
					if(curr.right == null)
					{
						callback.apply(curr);
						// Potentially go back up : parent testing
						while(curr != null)
						{
							BinaryTree par = curr.parent;
							if(par == null)
							{
								// You are at the root node : printed right most child then
								// Return ( vs. break ) gets us out of many nested loops
								return;
							}
							else if(par.right == curr)
							{
								cur = par;
							}
							else if ( parent.left == curr)
							{
								cur = par;
								cur.left = null; // do not explore left again
								break;
							}
						}
					}
					else if ( curr.right != null)	// has to be else if()
					{
						callback.apply(curr);
						curr = curr.right;
					}
				}
			}
		}

	
May need nested iterations	
callback = someCallback

// The input callback will have been called in the following order:
callback(4)
callback(9)
callback(2)
callback(1)
callback(6)
callback(3)
callback(7)

Constance space


COMPLEXITY
Let N := number nodes in the binary tree
Let H := height of the tree ( log_2(n) balanced, (n) skewed ) 
Time = O(2*N) = O(N)
Is not O(NH)
Space = O(1) ( explicit/implicit ) 
Visit each element at most twice
	- once per going down
	- once per going back up

In-order : left->root->right
	Backtracking : 

Visited set of nodes here
Can't use a HM






import java.util.function.Function;

class Program {
  public static void iterativeInOrderTraversal(
      BinaryTree tree, Function<BinaryTree, Void> callback) 
		{
			BinaryTree curr = tree;
			if(tree == null)
			{
				return;
			}
			while(curr != null)
			{
				if(curr.left != null)	// potential trip up : don't explore this again
				{
					curr = curr.left;
				}
				else if(curr.left == null)
				{
					// Leaf node case
					if(curr.right == null)
					{
						callback.apply(curr);
						// Potentially go back up : parent testing
						while(curr != null)
						{
							BinaryTree par = curr.parent;
							if(par == null)
							{
								// You are at the root node : printed right most child then
								// Return ( vs. break ) gets us out of many nested loops
								return;
							}
							else if(par.right == curr)
							{
								curr = par;
							}
							else if ( par.left == curr)
							{
								curr = par;
								curr.left = null; // do not explore left again
								break;
							}
						}
					}
					else if ( curr.right != null)	// has to be else if()
					{
						callback.apply(curr);
						curr = curr.right;
					}
				}
			}
		}


  static class BinaryTree {
    public int value;
    public BinaryTree left;
    public BinaryTree right;
    public BinaryTree parent;

    public BinaryTree(int value) {
      this.value = value;
    }

    public BinaryTree(int value, BinaryTree parent) {
      this.value = value;
      this.parent = parent;
    }
  }
}
