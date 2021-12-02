Binary Tree class typically put below
Input is a binary tree
Return it with left most node as the HEAD
Convert it into a DLL - Doubly-Linked-List

binary tree node : convert (left,right) to (prev,next) here
@ end, if input is a BST, the output will be a list of sorted numbers ( liken BT to a BST )
Flattening is in-place : mutate it as we go.
The flattening should be done in place, meaning that the original data structure should be mutated (no new structure should be created)

Clarifications
- can BT be null or a single element?
	can get a single elem case
-
-
Strategies : 
	Maintain a pointer to current node under consideration
	Return the leftmost child always
	Use a SLL add addresses as we go


Sample Input
tree =      1
         /     \
        2       3
      /   \   /
     4     5 6
          / \
         7   8

			 
- Nested structures within
- how to track ancestor info?
- if the parent's left or right equals what we popped off the stack
- check multiple levels ( up to 3 )?
- Need the func call stack space ( implicit ) 
	stack frame : val = 2 is different from stack frame : val = 3
	level is the same
- update both child & parent @ same time, or just child?
	
Let this be a stack : 
	[1,2,4,......->]
	[1,2,...] 2.left = 4
	4.right = 2
	[1,2,5,...,]
	
Maintain a set to flag visited nodes -O(N)
Push onto stack : push both children or only child @ a time?
	[1,2,4,5,7,8]
	Can push both children? Not possible
Push right first, then push left?



	
Need a way to denote visitation

Sample Output
4 <-> 2 <-> 7 <-> 5 <-> 8 <-> 1 <-> 6 <-> 3 // the leftmost node with value 4
															|
														 D&C
COMBINE(4 <-> 2 <-> 7 <-> 5 <-> 8, 1, 6 <-> 3) : seperate in-order subtree traversals

PSEUDOCODE : 
	Add each node encountered in an in-order traversal to the SLL, a list of UNIQUE addresses
	for(int i = 0; i < sz(SLL) - 1; ++i)
		BinaryTree leftElem = SLL.get(i);
		BinaryTree rightElem = SLL.get(i+1);
		leftElem.right = rightElem;
		rightElem.left = leftElem;
	return first node in the SLL
	

	May need parent info as well
	8->1, 8<-1
	1->6
	In-order traversal
	[8] is the rightmost node in the tree : connect it to [1] here
	
COMPLEXITY
Let N := #-nodes, Let H := height of tree ( log_2(n) balanced, (n) skewed ) 
Time = O(N) 
Space = O(N) ( explicit )
	
COMPLEXITY
Let N := #-nodes, Let H := height of tree ( log_2(n) balanced, (n) skewed ) 
Time = O(N) 
Space = O(1) ( explicit ) O(___) ( implicit ) 
	-> recursive call stack frames/space
	O(H) instead
	Stack data structure


Make a copy of tree with parent info -> O(N) space here

	
	





import java.util.*;

class Program {
  public static BinaryTree flattenBinaryTree(BinaryTree root) 
	{
		if(root == null)
		{
			return null;
		}
		List<BinaryTree> inOrderTraversal = new ArrayList<BinaryTree>();
		traverseInOrder(root, inOrderTraversal);
		changePointers(inOrderTraversal);
		BinaryTree leftmost = inOrderTraversal.get(0);
    return leftmost;
  }
	
	private static void changePointers(List<BinaryTree> traversal)
	{
		int n = traversal.size();
		for(int i = 0; i < n-1; ++i)
		{
			BinaryTree leftElem = traversal.get(i);
			BinaryTree rightElem = traversal.get(i+1);
			leftElem.right = rightElem;
			rightElem.left = leftElem;
		}
	}
	
	private static void traverseInOrder(BinaryTree root, List<BinaryTree> traversal)
	{
		if(root == null)
		{
			return;
		}
		traverseInOrder(root.left, traversal);
		traversal.add(root);
		traverseInOrder(root.right, traversal);
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







