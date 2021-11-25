Right sibling tree :

Input is a binary tree
Write a func to transform it into a right sibling tree

Make every node have it's right pointer, point to the right sibling, instead of the right child

Clarifications
- Tree is never null/empty : always well-formed with a single node
- Node values could be duplicates ( but it should not matter )
- Transformation done "in-place" : bottom-up approach
	-> left never modified : only the right modified ( left-right-root OR right-left-root )

tree =     1
      /         \
     2  				 3
   /   \       /   \
  4     5     6     7
 / \     \   /     / \
8   9    10 11    12 13
           /
          14

Recursive In-order traversal for update op
	Pass in access to a reference to the parent ( as a parameterized value ) 
	Save left,right subtrees in your stack frame before invoking recursive calls on the subtrees
	Recurse ( LHS ) and update that
	
	Parent update came from a valid case as well!
	
	parent = 5
	right = 10
	
INVARIANT : If any ancestors on your path have a null right sibling node ... then all descendant nodes 
on the right subtree of said ancestor will also point to null.

PERFECT TREE 
  				1
      /         \
     2           			3
   /   \       			/   \
  4     5     			6     7
 / \   / \   			/     / \
8   9  10 11	 12 13    14 15
           
Pre-order manner : 

levels	0		1		2		3
				1		2		4		9
refs		[							]

dfs(4, level)
	dfs(8, level * 2)
	dfs(9, level * 2 + 1)
	
Diff greater than one : set the pre-existing reference to point to null now
Overwrite
Exception for case of (2^level - 1) : set to null [ 15->16, 7->8 ]

if(log_2(level + 1) is not decimal ... weird )
if(onesCount(level+1) == 1) : binary trick ( 1, 2, 4, 8 -> 0001, 0010, 0100, 1000 ) ( unsigned int ) 

	
O(H)					 
					 
Is a store of addresses here 
(8,9,_,10,11,_,12,13)	-> fill to the right ( store the levels in list ) 
Rightmost children : go to null
Func call stack space : save state in the parent call before calling the recursion
DFS : independent paths


Mapping to power of 2
	left : add 1
	right : add 2

HINT : referencing the sibling -> if parent has a pointer to it's sibling, then right should be easy to get to the left
	


tree =     1
      /         \
     2 					 	3
   /   	     	 	/   \
  4          		6     7
 / \        	/     / \
8   9   	 	11    12 13
     \     	/ 
      10   12 
					
           1 // the root node with value 1
      /
     2-----------3
   /           /
  4-----5-----6-----7
 /           /     /
8---9    10-11    12-13 // the node with value 10 no longer has a node pointing to it
           /
          14					

5 is a right of 2, 6 is as left of 3 - sibling relationship ( next to eah other in level order )
11 is left of 6, 12 is left of 7 : no right of 6 here



Strategies : Bottom-up, recursive, 2 pointers, Stack, Level-Order traversal ( BFS ) 
	Left child sibling : pretty easy
	



COMPLEXITY
Let N := #-nodes in the tree
Let H := height of the tree ( log_2(n) balanced, n skewed ) 
Time = 
Space = 

TEST CASES


import java.util.*;

class Program {
	
	// root lacks directionality too! ( it's not a left : is a right ) 
  public static BinaryTree rightSiblingTree(BinaryTree root) 
	{
		BinaryTree initParent = null;
		char dir = 'R';
		dfs(root, dir, initParent);
		return root;
  }
	
	private static void dfs(BinaryTree root, char dir, BinaryTree parent)
	{
		if(root == null)
		{
			return;
		}
		BinaryTree lst = root.left;
		BinaryTree rst = root.right;
		dfs(lst, 'L', root);
		
		if(parent == null)
		{
			root.right = null;
		}
		else
		{
			if(dir == 'L')
			{
				root.right = parent.right;
			}
			else if(parent.right != null)
			{
				root.right = parent.right.left; // right always goes to a left ( right->right is a gap ) 
			}
		}
		dfs(rst, 'R', root);
		
	}
	
	

  // This is the class of the input root. Do not edit it.
  static class BinaryTree {
    int value;
    BinaryTree left = null;
    BinaryTree right = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }
}
