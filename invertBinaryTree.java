
Input : Is a Binary Tree root
	Composed of integer values

Clarifications
- Swap left and right childrens
- Invert as a whole
- Integers can be duplicate values
- Handle null AND empty tree cases
- ONLY binary tree
- NO full tree guarantee : 1 child cases possible for intermediary nodes
- Fits into RAM
- value swap or address swap?
- can modify in-place

Address swapping : 
				PARENT
				/   \
	  Addr1		Addr2
		
		
				PARENT
				/   \
	  Addr2		Addr1

tree =    1
       /     \
      2       3
    /   \   /   \
   4     5 6     7
 /   \
8     9


Sample Output:
      1
    /     \
   3       2
 /   \   /   \
7     6 5     4
            /   \
           9     8
	
						
Strategies : Recursive ( Head/Tail ) , Top-Down OR Bottom-Up ( Head ) 
Call Stack optimization

PSEUDOCODE : 
	
	invertBinaryTree(BinaryTree tree)
		if tree is a leaf node 
			return
		else if tree.left is not null and tree.right is not null
			invertBinaryTree(tree.left)
			invertBinaryTree(tree.right)
			BinaryTree temp = tree.right
			tree.right = tree.left
			tree.left = temp
		else if tree.left is not null and tree.right is null
			invertBinaryTree(tree.left)
			tree.right = tree.left
			tree.left = null
		else if tree.left is null and tree.right is not null
			invertBinaryTree(tree.right)
			tree.left = tree.right
			tree.right = null
	
COMPLEXITY
Let N := #-nodes in the tree
Let H := height of the tree ( log_2(n) balanced, (n) worst ) 
TIME = O(N)
SPACE = O(H) ( call stack ) O(1) ( explicit/stack frame ) 
					 

[parent,left,right,....]
TEST CASES : 
(A) []
(B) [1]
(C) [1,2]
(D) [1,null,2]
(E) [1,2,3]
(F) [1,2,null,3,4]
(G) [1,2,null,3,4,null,null,5,6,7,8]

{
  "nodes": [
    {"id": "1", "left": "2", "right": "3", "value": 1},
    {"id": "2", "left": "4", "right": "5", "value": 2},
    {"id": "3", "left": "6", "right": "7", "value": 3},
    {"id": "4", "left": "8", "right": "9", "value": 4},
    {"id": "5", "left": null, "right": null, "value": 5},
    {"id": "6", "left": null, "right": null, "value": 6},
    {"id": "7", "left": null, "right": null, "value": 7},
    {"id": "8", "left": null, "right": null, "value": 8},
    {"id": "9", "left": null, "right": null, "value": 9}
  ],
  "root": "1"
}


Iterative Approach
-> Use a stack or queue explicitly


tree =    1
       /     \
      2       3
    /   \   /   \
   4     5 6     7
 /   \
8     9

after first iteration

tree =    1
       /     \
			3				2    
		 / \		/   \  
		6		7	 4     5
				 /   \
				8     9


Sample Output:
      1
    /     \
   3       2
 /   \   /   \
7     6 5     4
            /   \
           9     8
					 
Top-down?
[1,2,3]
1 : swap it's children -> chuck 1 out : continue on

PSEUDOCODE : 

	Initialize a queue with root node
iterativeInvertBinaryTree(BinaryTree tree):	
	while queue is not empty :
		curNode = queue.pop
		if left not null
			queue.append(curNode.left) 
		if right not null
			queue.append(curNode.right)
		// Perform swap
		BinaryTree temp = curNode.right
		curNode.right = curNode.left
		curNode.left = temp
			
	return	

COMPLEXITY
Let N, H stay as defined
TIME = O(N)
SPACE = O(N) ( explicit ) O(1) ( call stack ) 

Stack versus queue?

				1
			 /	\
			2		 3
		 / \	/ \
		4	 5	6	 7	final level = (n+1/2) #-leaves, for n nodes, in a complete binary tree


DFS manner VS. level order
TIME = O(N)
SPACE = O(H)

pop -> push(left), push(right)
STK : [1,2,4,6,7] : n/2 number of nodes : height

			1
		 / \
		2	  3
		   / \
			4   5
			   / \
				6	  7






import java.util.*;

class Program {
  public static void invertBinaryTree(BinaryTree tree) 
	{
		if(tree == null || (tree.left == null && tree.right == null))
		{
			return;
		}
		if(tree.left != null)
			invertBinaryTree(tree.left);
		if(tree.right != null)
			invertBinaryTree(tree.right);
		BinaryTree temp = tree.right;
		tree.right = tree.left;
		tree.left = temp;
		return;
  }

  static class BinaryTree {
    public int value;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int value) {
      this.value = value;
    }
  }
}

 	public static void stackTraverasl(BinaryTree tree)
	{
		if(tree == null)
		{
			return;
		}
		Stack<BinaryTree> traversal = new Stack<BinaryTree>();
		traversal.push(tree);
		while(traversal.size() > 0)
		{
			BinaryTree curNode = traversal.pop(); // poll safer than remove
			if(curNode.left != null)
			{
				traversal.push(curNode.left);
			}
			if(curNode.right != null)
			{
				traversal.push(curNode.right);
			}
			// Perform swap
			BinaryTree temp = curNode.right;
			curNode.right = curNode.left;
			curNode.left = temp;
		}
		return;
  }
	
	
	public static void queueTraverasl(BinaryTree tree)
	{
		if(tree == null)
		{
			return;
		}
		Queue<BinaryTree> traversal = new LinkedList<BinaryTree>();
		traversal.add(tree);
		while(traversal.size() > 0)
		{
			BinaryTree curNode = traversal.poll(); // poll safer than remove
			if(curNode.left != null)
			{
				traversal.add(curNode.left);
			}
			if(curNode.right != null)
			{
				traversal.add(curNode.right);
			}
			// Perform swap
			BinaryTree temp = curNode.right;
			curNode.right = curNode.left;
			curNode.left = temp;
		}
		return;
  }
