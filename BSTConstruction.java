BST Construction : 

Write a BST class for a Binary Search Tree
- insertion of values
- removal of values : removes FIRST INSTANCE of given value
- search ( with contains ) : ANY INSTANCE of a value

Can not remove values from a single-node tree
-> is a NOP

Each visited node has an integer value, a left child node, & right child node
Node is valid IFF BST property met :
    val > every node to left
		val <= every node to the right
		children nodes : BSTs or NULL
		
		
// Assume the following BST has already been created:
         10
       /     \
      5      15
    /   \   /   \
   2     5 13   22
 /           \
1            14
						/
					 13
insert(13)

         10
       /     \
      5      15
    /   \   /   \
   2     5 13   22
 /         / \
1         11  13
					/		 \
				 10 	  (14
				 					\
										15)
				 
				 rightNOde : (14
				 							\
											 15)
				 
				  BST rightNode = curNode.right;
					curNode.right = newChild;
					newChild.right = rightNode;
					
					
					
				 
insert(13)
					 


// All operations below are performed sequentially.
insert(12):   10
            /     \
           5      15
         /   \   /   \
        2     5 14   22
      /        /  
     1        12  
								\
								13
	
	remove(13)
	
	In-order successor of a node guaranteed a null left
	If no right node -> 2nd-level parent : just remove
	If a right node -> leftmost child
	
							10
            /     \
           5      15
         /   \   /   \
        2     5 14   22
      /        /  
     1        12  
								\
								13
	
						
							10
            /     \
           5      15
         /   \   /   \
        2     5 14   22
      /        /  
     1        12  
								\
								13
	
	remove(10);
	
	14.left = 12;
	
	curNode.left! = null && curNode.left.left == null
		curNode.left = curNode.left.right
	
	
	currNode = currNode.right
	
							12
            /     \
           5      15
         /   \   /   \
        2     5 13   22
      /        /	\  
     1        12	14
							
							
							
<- parent of the node to delete
<- parent of the inorder node to substittue

							
										15
									/		\
								 14		 22
							  /
							12
						/   \
           5     13
         /   \    
        2     5   
      /          	
     1          	
							
							
								
	
		14.left = 12.right;
		
		5.left = 2;
		
		5.left = 2.left;
		
		
		currNode = 2;
		if(2.right == null){
			currNode = currNode.left;
		}
		
		

successor

if(right == null) 
	if(left = null)
		curr = null;
		
		2.left  = null
		
		
		
		
		
	else
		curr = curr.left;



12 : <= all values on the right of 10

remove(13)
-> take 14

Get the leftmost of the right BST subtree
Get the value

						10
					 /
					5
				 / \
				2	  5

delete(10) => 
			
				5
			 / \
			2   5
			
			10 is intermediary, but lacks a right child/right BST subtree
			Need a parent node and a child node
			
			

remove(10):   12
            /     \
           5      15
         /   \   /   \
        2     5 13   22
      /           \
     1            14

- tree rebalancing
- 12 = new root ( sifting up operation : heap ) 
- harder


contains(15): true


Level of difficulty : contains -> insertion -> removal


Complexity


		OP						Insert		Remove		Contains
		TIME					O(H)			O()				O(H)
AUXILLARY SPACE		O(1)			O()				O(1)
CALL STK SPACE		O(1)			O()				O(1)

N := #-nodes in the BST
H := BST Height ( log_2(n) balanced, (n) worst/skew )


import java.util.*;

class Program {
  static class BST {
    public int value;
    public BST left;
    public BST right;

    public BST(int value) {
      this.value = value;
    }

		// Iterative insertion : loop & conditional flow
		// Not insertin in middle : only to the leaf frontier
		// Check the child value as well
    public BST insert(int value) 
		{
      // Write your code here.
			// Have a "this" self-referential pointer
			BST curNode = this;
			BST newChild = new BST(value);
			// Break out of loop once a null child hit at least
			while(true)
			{
				// Not operating on the left of curNode
				if(value == curNode.value)
				{
					BST rightNode = curNode.right;
					curNode.right = newChild;
					newChild.right = rightNode;
					break;
				}
				else if(value > curNode.value)
				{
					BST rightNode = curNode.right;
					if(rightNode == null)
					{
						curNode.right = newChild;
						break;
					}
					else if ( rightNode != null)
					{
						curNode = rightNode
					}
				}
				else if ( value < curNode.value)
				{
					BST leftNode = curNode.left;
					if(leftNode == null)
					{
						curNode.left = newChild;
						break;
					}
					else if ( leftNode != null)
					{
						curNode = leftNode;
					}
				}
			}			
      // Do not edit the return statement of this method.
      return this;
    }

    public boolean contains(int value) 
		{
			boolean hasValue = false;
			BST curNode = this;
			while(curNode != null)
			{
				if(curNode.value == value)
				{
					hasValue = true;
					break;
				}
				else if ( value > curNode.value)
				{
					curNode = curNode.right;
				}
				else if ( value < curNode.value)
				{
					curNode = curNode.left;
				}
			}
      return hasValue;
    }

    public BST remove(int value) {
			BST parentNode = null;
			BST curNode = this;
			if(contains(value) == false)
				return null;
			// single node tree check
			if(curNode.left == null && curNode.right == null)
				return null;
			
			
			// find the node you are deleting and parent node
			
			while(curNode != value)
			{
				parentNode = curNode;
				if(value > curNode.value)
					curNode = curNode.right;
				else if ( value < curNode.value)
					curNode = curNode.left;
				else
					break;
			}
			
			if(curNode.right == null)
			{
				if(parentNode.right == curNode)
					parentNode.right = null;
				else
					parentNode.left = curNode.
			}
			else
			{
				BST leftMost = curNode.right;
				while(leftMost.left != null)
					leftMost = leftMost.left;
				leftMost.left = curNode.left;
			}
				
			
			
      // Do not edit the return statement of this method.
      return this;
    }
  }
}



















