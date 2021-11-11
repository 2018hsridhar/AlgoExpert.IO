Write a function that takes in an array of integers and returns 
an array of the same length, where each element in the output array 
corresponds to the number of integers in the input array that are to 
the right of the relevant index and that are strictly smaller 
than the integer at that index.

In other words, the value at output[i] represents the number of integers that are to the right of i and that are strictly smaller than input[i].
ArrayList or a LinkedList
						------------------
								--------------
								!!!!!!!!!!!!!!	( explore this interval twice ) 
				 0	1		2		3	 4	5	 6
array = [8, 5, 11, -1, 3, 4, 2]
output = [5, 4, 4, 0, 1, 1, 0]

Question : rightSmallerThan 
rightmost elem always known
Use DP in the BST
	Track sizes of the subtrees from given nodes


Input : Take an array of integers ( as a SLL ) 
Output : Array of same length, but each element corresponds to #-integers in input array 
				right to relevant index and strictly smaller than the int @ that index
				Binary search inserting -> maintain this sorted list as we go.
				
				 0	1		2		3	 4	5	 6
array = [8, 5, 11, -1, 3, 4, 2]          |
						^	 --------------- 	{-1,2,3,4,11} : find where 5 belongs -> see that 4 elems < 5
				> insert(5) into that dynamic data structure
				 0	1		2		3	 4	5	 6
array = [8, 5, 11, -1, 3, 4, 2]							 |
				 ^	------------------- 	{-1,2,3,4,5,11} : find where 8 belongs -> see that 5 elems < 8
				> insert(8) into that dynamic data structure
				
1. Heap data structure
	- avoid polling and adding all elements back
2. Order set as well ( uses trees )?

	-> array list : can always find the insertion index quick via binary search?
		-> O(1) resize/insert operation
		-> O(log(n)) find location operation
	0	1	2	3	4	5
{-1,2,3,4,5,11}
					 ^
					 Element closest to the value 8 to the left ( or to the right ) but not both
					 |11-8| = |5-8| = 3
					 
		-> O(log(n)) insertion, O(log(n)) find
		-> self-balancing binary tree ( minHeap : minimum is at the root ) 
		-> Queues and Stacks
		
					 
https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html

Ideas : 

Naive solution : 
	O(N^2), O(1) space : 2 nested for loops
	Do better : O(N) or O(NlogN) , O(N) or O(K) space 
	Some overlap in intervals : shared computation done
	Sorting the input helps too much -> avoiding that
	


Complexity
Let N := len(list)
Time = O(NlogN)
Space = O(N)  (explicit ) O(1) (implicit)
# [8, -4, 5, 11, -1, 3, 4, 2]
[8, 5, 11, -1, 3, 4, 9]
					elems(<11)	= 4
					elems(<5)		= 3
					elems(<-4)
					Goal : solve for elems(<8)
					elems(<11)-elems(<5) = elems in range [6,10] = 1
					5 was the max encountered before encountering 11				
					elems(<8) = 4 ( includes 5 ) 
					
							11
						 /
						5
						
							11
						 /				<{8,9,10}
						7
					 /
					5 
					
BST is from the child : NOT the parent!
Arr could have duplicates ( assume unique vals case though )
					
insert to right of 2
	+1 ( size of left subtree at 1 ) 
	+1 ( root ) 
	+1 ( root at 4 ) 
	+1 ( left subtree at 3 )
	+1 ( root at 11 ) 
	
2
|->4 ( R) 
	 |->11 ( R)
	 	  |->5 ( L)
	 |->3 ( L )
|->-1 ( L)



Insert(5) after inserting(11)
Grab parent info : 11 => 4
Save the ranges?
What if lacking the full range info?

As we go down the tree : keep track from the roots

Binary Tree
3
|->4
	 |->11
	 		|->10
			|->15
|->-7
	 |->-8
	 |->1
	 	  |->0
			|->-2
					|->-3
						  |->4

len = 3 ( subtree 1 )
len = 5 ( subtree 2 ) 

Start with BST
[-1,2,3,4,5,8,11]

4
|->2
	 |->-1
	 |->3
|->8
	 |->5
	 |->11

Create the BST as we go

Pseucode : 

	// Save the counts too
	BST root = new BST();
	List<Integer> results = new ArrayList<Integer>();
	for(int i = n; i >= 0; --i)
	{
		int val = array.get(i);
		int numElemsLess = root.insert(val)
		results.add(numElemsLess);
	}
	return results

-> all 0 elems 
[1,2,3,4,5]

--> growing nested case
[5,4,3,2,1]
						insert from right to left
						insert on the right : always the parent
			1 : 1
			 \
			  2 : 2
				 \
				  3 : 3
					 \
					  4 : 4
						 \
						  5 : 5

				5 : 0
		 	 /	
    4 : 0
		/
	 3:0
	 /
	2:0
	/
 1:0

--> growing nested case
[5,1,4,2,3]


				5 : 0
			 / \
		0	1   4 1
		 /   /
		2 	3
		
		
		
import java.util.*;

// class BinaryTree
// {
// 	int val;
// 	BinaryTree left;
// 	BinaryTree right;
// 	int leftTreeHeight;
// 	int rightTreeHeight;
	
// 	BinaryTree(int val)
// 	{
// 		this.val = val;
// 		this.left = null;
// 		this.right = null;
// 	}
	
// 	insert(int val)
// 	{
		
// 	}
	
// }

class Node
{
	int val;
	Node left;
	Node right;
	int subTreeSize;
	
	Node(int val)
	{
		this.val = val;
		this.left = left;
		this.right = right;
		this.subTreeSize = 0;
	}
	
}

// We never remove from the BST class
class BST
{
	Node root;
	
	// need cond logic for the zig-zagging
	public void insert(int newVal)
	{
		Node newRoot = root; // initially assume we insert at root ( we may insert @ intermediary as well )
		while(true)
		{
			if(newVal >= newRoot.val)
			{
				Node newChild = new Node(newVal);
				if(newRoot.right == null)	// leaf case
				{
					newRoot.right = newChild;
					break;
				}
				else if ( newRoot.left < newNode.val)	// intermediary
				{
					Node lst = newRoot.left;
					newRoot.left = newChild;
					newChild.left = lst;
					newChild.subTreeSize = 1 + lst.size;
					break;
				}
			}
			else if ( newVal < newRoot.val)
			{
				Node newChild = new Node(newVal);
				if(newRoot.left == null)	// leaf case
				{
					newRoot.left = newChild;
					break;
				}
				else if ( newRoot.right > newNode.val)	// intermediary
				{
					Node rst = newRoot.right;
					newRoot.right = newChild;
					newChild.right = rst;
					newChild.subTreeSize = 1 + rst.size;
					break;
				}
			}
	}
}


class Program 
{
  public static List<Integer> rightSmallerThan(List<Integer> array) 
	{
    List<Integer> results = new ArrayList<Integer>();
		return results;
  }
}






