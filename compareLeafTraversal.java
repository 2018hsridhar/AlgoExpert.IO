Takes in the root node of two binary node trees
Boolean -> are their leaf traversals the same


Traversal is ONLY the leaf nodes from left to right
Any node with no ( left,right ) children


   4
 /   \
1     5
    /   \
   3     2  
	 
	 
tree1 = 1
      /   \
     2     3
   /   \     \
  4     5     6
      /   \
     7     8
		 
		 7 is @ depth 4 : on the right of 5, left of 2
		 
tree2 = 1
      /   \
     2     3
   /   \    \
  4     7    5
            /  \
           8    6
					 
	Stack or two stacks?

	7 is @ depth 3 : on the right of 2
	Running two traversals concurrently -> how to preserve context and backtrack?
	
PSEUDOCODE : 
	
	list = ()
	// O(sz(tree)) time : O(M) + O(N), Space = O(G) + O(H) ( recursively ) O(1) ( iteratively )
	// O(1) to apped to end of lists
	void generateLeafTraversal(BinaryTree tree, list)
		traverses tree in preoder
		if node is a leaf -> append to list
	
	// O(min(one,two)) time, O(1)
	boolean compareLeafLists(List one, List two)
		//if(one.size() != two.size())
		//	ret false
		ptr1, ptr2 = 0
		n = sz(one)
		while(ptr1 < one.size() && ptr2 < two.size())
			if(one[ptr1] != two[ptr2])
				ret false
			++ptr1
			++ptr2
		// We stop at the tinier list
		if(ptr2 < two.size() || ptr1 < one.size())
			ret false
		ret true

Strategy : PreOrder traversal, Two Pointers Algo

Leaves = (4,7,8,6) for both of them

Leaf Traversal = (1,3,2)
Are the leaves of tree 1 = leaves of tree 2 ( based on their orderings ) 

Clarifications : 
	- Binary trees ( not n-ary ) 
	- Can they be null/empty : yes
	- Can the values be duplicate or all unique? They CAN be duplicates
	
	
COMPLEXITY : 
Let M, N := number nodes in tree1, tree2 respectively
Let G, H : = height of tree1, tree2 respectively
	ideally, G = log_2(M), H = log_2(N) ( balanced ) 
	worst cases, G = M, H = N
TIME = O(M) + O(N) + O(min(M,N))
SPACE = Space = O(G) + O(H) ( recursively ) O(1) ( iteratively ) 

TEST CASES
(A) [[],[]] ( two trees point to null ) 
	=> [],[] => TRUE
(B) [[1],[1]]
	=> [1],[1] => TRUE
(C) [[1],[10]]
	=> [1],[10] => FALSE



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

  public boolean compareLeafTraversal(BinaryTree tree1, BinaryTree tree2) 
	{
		ArrayList<Integer> leavesOne = new ArrayList<Integer>();
		ArrayList<Integer> leavesTwo = new ArrayList<Integer>();
		generateLeafTraversal(tree1, leavesOne);
		generateLeafTraversal(tree2, leavesTwo);
		// boolean equalLeafTraversals = leafTraversalsAreEqual(leavesOne, leavesTwo);
    // return equalLeafTraversals;
		boolean equalLeafTraversals = leavesOne.equals(leavesTwo);
		return equalLeafTraversals;
  }
	
	private boolean leafTraversalsAreEqual(ArrayList<Integer> leavesOne, ArrayList<Integer> leavesTwo)
	{
		int ptr1 = 0;
		int ptr2 = 0;
		int m = leavesOne.size();
		int n = leavesTwo.size();
		if(m != n)
		{
			return false;
		}
		while(ptr1 < m && ptr2 < n)
		{
			if(leavesOne.get(ptr1) != leavesTwo.get(ptr2))
			{
				return false;
			}
			else
			{
				++ptr1;
				++ptr2;
			}
		}
		return true;
	}
	
	
	// Do a inorder : left -> root->right : traverasl
	// Traversal order : left -> right ( top-down ordering ) 
	private void generateLeafTraversal(BinaryTree tree, ArrayList<Integer> leaves)
	{
		if(tree != null)
		{
			// BASE CASE : node is a leaf
			if(tree.left == null && tree.right == null)
			{
				leaves.add(tree.value);
			}
			else
			{
				generateLeafTraversal(tree.left, leaves);
				generateLeafTraversal(tree.right, leaves);
			}
		}
		else if ( tree == null ) 
		{
			return;
		}
	}
	
	
	
	
	
}



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

  public boolean compareLeafTraversal(BinaryTree tree1, BinaryTree tree2) 
	{
		boolean equalLeafTraversals = compareLeaves(tree1, tree2);
		return equalLeafTraversals;
  }
	
	
	// Do a inorder : left -> root->right : traverasl
	// Traversal order : left -> right ( top-down ordering ) 
	// Recursive approach -> save context for concurrent traversal ( or use an explicit stack : iterative )
	// Iterative two stack approach?
	
	private boolean compareLeaves(BinaryTree tree1, BinaryTree tree2)
	{
		Stack<BinaryTree> stk1 = new Stack<BinaryTree>(); 
		Stack<BinaryTree> stk2 = new Stack<BinaryTree>(); 
		stk1.push(tree1);
		stk2.push(tree2);
		// Order of pushing onto stack matters here
		// Might need to check empty case too in iteration!
		// Push if a new subtree or a new intermediary node must be visited
		// Goal is not to produce a stack resembling : [4,7,8,6,...]
		// Goal is to do it "on-the-fly"
		// NO question - > operate if tree1's and tree 2's current nodes are NOT leaves
		while(!stk1.isEmpty() && !stk2.isEmpty())
		{
			// Pop operation : as we iterate -> stacks might have been empty!
			BinaryTree stk1_top = stk1.pop();	// Check if to be pop of peek as well!
			BinaryTree stk2_top = stk2.pop();
			boolean tree1IsLeaf = ( stk1_top.left == null && stk1_top.right == null);
			boolean tree2IsLeaf = ( stk2_top.left == null && stk2_top.right == null);
			if(tree1IsLeaf == true && tree2IsLeaf == true)
			{
				return (stk1_top.value == stk2_top.value);
			}
			else if ( tree1IsLeaf == false)	// this would execute accordingly
			{
				if(stk1_top.right != null)
				{
					stk1.push(stk1_top.right);
				}
				if(stk1_top.left != null)
				{
					stk1.push(stk1_top.left);
				}
			}
			else if ( tree2IsLeaf == false)	// never executes if tree2 is a leaf
			{
				if(stk2_top.right != null)
				{
					stk2.push(stk2_top.right);
				}
				if(stk2_top.left != null)
				{
					stk2.push(stk2_top.left);
				}
			}
		}
		return (stk1.isEmpty() && stk2.isEmpty());
	}

	
	
}
