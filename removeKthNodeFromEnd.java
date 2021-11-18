import java.util.*;

class Program {
  public static void removeKthNodeFromEnd(LinkedList head, int k) 
	{
		LinkedList sentinel = new LinkedList(0);
		sentinel.next = head;		// even if still reading head here : exert caution
		
		// LinkedList kthPlusOneNode = null; // init points to null : fill in by calls
		// findSpecialNode(head, kthPlusOneNode, k+1);
		// Wrapper info = findSpecialNode(head, k+1);
		Wrapper info = findSpecialNode(sentinel, k+1);
		LinkedList kthPlusOneNode = info.node;
		// System.out.printf("kth plus one node val = %d\n", kthPlusOneNode.value);
		
		// Case where kthPlusOne = sentinel
		// if(kthPlusOneNode == null)
		if(kthPlusOneNode == sentinel)
		{
			sentinel.next = null;
			LinkedList cons = head.next;
			// You need to do this. WOW!
			head.value = cons.value;
			head.next = cons.next;
		}
		else
		{
			// Perform operation 
			LinkedList kthNode = kthPlusOneNode.next;	// null pointer exception
			LinkedList kthMinusOneNode = kthNode.next;
			kthPlusOneNode.next = kthMinusOneNode;
		}
		return;
  }
	
	static class Wrapper
	{
		public int depth;
		public LinkedList node;
		
		public Wrapper(){}
		public Wrapper(LinkedList node, int depth)
		{
			this.node = node;
			this.depth = depth;
		}
	}
	
	//  Need one or > 1 pieces of info.
	// Backtrack up correct depth here
	// private static int findSpecialNode(LinkedList head, LinkedList kthPlusOneNode, int k)
	private static Wrapper findSpecialNode(LinkedList head, int k)
	{
		if(head == null)
		{
			return new Wrapper(null,0);
		}
		else
		{
			Wrapper childInfo = findSpecialNode(head.next,k);
			if(childInfo.depth == k)
			{
				return childInfo;
			}
			else
			{
				int parentDepth = 1 + childInfo.depth;
				if(parentDepth == k)
				{
					return new Wrapper(head, parentDepth);
				}
				else
				{
					childInfo.depth += 1;
					return childInfo;
				}
			}
		}
	}

  static class LinkedList {
    int value;
    LinkedList next = null;

    public LinkedList(int value) {
      this.value = value;
    }
  }
}




Goal : Remove the kth node from the end of a SLL
Given the head pointer of a SLL and an int k
Remove the kth node from the end of the list
Remove should be in-place : original data structure to mutate 
Input head should still remaining head after removal ( even if head = node to remove )
	Simply mutate the value and next pointer
	
Func has no return type
SLL has at least 2 nodes.

Input clarification : 
- no k > size ( guaranteed 1 <= k <= size ) 
- can k = 0 : is tail -> yes ( no negative k )
- no null or empty SLL cases
- 1-indexed
- [ INT_MIN, INT_MAX] for node values. 


Each node has an int value & a next ptr node : 

TEST CASES : 
(A) head = 0 -> 1 -> 2, k = 3 ( 0th node )
	1->2, or 0->1->2
							^
							head
		- make assumption : pointer the head to the next node ( 1st node )

Furthermore, the input head of the linked list should remain the head of 
the linked list after the removal is done, even if the head is the node 
that's supposed to be removed. In other words, if the head is the node 
that's supposed to be removed, your function should simply mutate its value and next pointer.

(B)


sample input:
																	(k=4)	(k=3)
																	 4th	3rd	 2nd	1st
head = 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 // the head node with value 0
k = 4

Deleting 4th nodes ~= setting 5th node's next ptr to the 3rd node


// No output required.
// The 4th node from the end of the list (the node with value 6) is removed.
0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 7 -> 8 -> 9

Removed the 4th node ( with node val = 6 ) here

Complexity : 
Let N := size of SLL
Time = 
Space = ___ ( implicit ) __ ( explicit ) 

Pseucode : 

	Attach a sentinel node to beginning of list
	Use recursion to find the kth+1 node from end ( backtrack up depth vals ) 
	Once at kth + 1 node, set kth+1 next to the kth-1 node

