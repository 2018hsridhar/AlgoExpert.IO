
Input : Given a SLL with a set of nodes and their corresponding pointers
Output : Connect first node with end node, than with 2, then with n-1, ... and so on
	until we reach the middle here
New head of SLL = the first node
Must be in-place : no auxillary space allowed
	Can use auxillary space
	We can have duplicate values ( but addrs unique )
	At minimum, 1 element exists

Parity/length of lists : even/odd
Size : O(N) count #-times pointer is shfited till it points to null
Writing addresses

'1 -> 2 -> 3 ... -> n'
'1 ->n -> 2 -> n-1'

'1 -> 2 -> 3 -> 4 -> null'
	Write @ end, for even len
	[1,4,2,3]
	[1,_,2,_]
	 ^   ^     ( len = 4, 4 % 2 == 0 : even -> write for i in {0,1} : len/2-1)

'1 -> 2-> 3 -> 4 ->5->null' =>  '1 -> 5 -> 2 -> 4 -> 3->null'
	Write @ end-1, for odd len
	[1,5,2,4,3] = the list
	Notice the pattern with (1,2,3) here -> skip an element and write to it
	[1, _, 2, _, 3]
	 ^     ^     ^ ( len = 5, 5 % 2 == 1 : odd -> write for i in {0,2} : len/2)
	1->5
	Overwrite connections later on
	Split list/loop into two parts
	[_, 5, _, 4, _]
						^
		  ^ <---- decrement and go the other way
			
THOUGHT PROCESS
	- two ptrs
	- Get the size of the list
	- Recursive or Iterative ( * faster * ) 
	

COMPLEXITY
TIME = O(N), O(N^2)
SPACE = O(N) space ( explicit) O(1) ( IMP - call stack )

BETTER
TIME = O(N^2), O(1) space

TEST CASES
(A) 1
(B) 1->1->1->1
(C) 1->2->3->4->5

PSEUDOCODE 

		int getSize(LinkedList linkedList)
			size = 0
		  LinkedList head = linkedList;
			while(head != null)
			{
				head = head.next;
				++size;
			}
			return size

		// Order of ops : incr numEncount only after writing to dest array
	  public LinkedList zipLinkedList(LinkedList linkedList) 
		{
			// Get size	& alloc array of n elements for linkedlist ptrs
			n <- getSize(linkedList);
			A[n];
			head = linkedList
			wIdx = 0;					// write Index
			numEncount = 0;
			
			bool isOddLen = (n % 2 == 1);
			while(head != null)
				A[wIdx] = head
				head = head.next
				numEncount++
				if(isOddLen && numEncount == (int)(n/2))
				{
					wIdx = n-2;
					break
				}
				else if ( !isOddLen && numEncount == (int)(n/2) )
				{
					wIdx = n-1
					break
				}
				wIdx += 2;
			
			// Decr by one already here, for odd case
			while(head != null)
				A[wIdx] = head
				head = head.next
				wIdx -= 2;
				
			// Now array has been written to : connect nodes as expected
			for(int i in range(0,n-1))
				A[i].next = A[i+1];
			A[n-1].next = null
			
			return originalHead
		}

import java.util.*;

class Program {
  // This is an input class. Do not edit.
  public static class LinkedList {
    public int value;
    public LinkedList next;

    public LinkedList(int value) {
      this.value = value;
      this.next = null;
    }
  }
	
	private int getSize(LinkedList linkedList)
	{
		int size = 0;
		LinkedList head = linkedList;
		while(head != null)
		{
			head = head.next;
			++size;
		}
		return size;
	}
	
	private void createArrayOfNodes(LinkedList linkedList, LinkedList[] A)
	{
		// Get size	& alloc array of n elements for linkedlist ptrs
		int n = A.length;
		LinkedList head = linkedList;
		int wIdx = 0;					// write Index
		int numEncount = 0;
		
		boolean isOddLen = (n % 2 == 1);
		int halfEls = n/2;
		while(head != null)
		{
			A[wIdx] = head;
			head = head.next;
			numEncount++;
			if(isOddLen && numEncount == (1 + halfEls))	// possible bug
			{
				wIdx = n-2;
				break;
			}
			else if ( !isOddLen && numEncount == halfEls )
			{
				wIdx = n-1;
				break;
			}
			wIdx += 2;
		}
			
		// Decr by one already here, for odd case
		while(head != null)
		{
			A[wIdx] = head;
			head = head.next;
			wIdx -= 2;
		}
	}
	
	
	private void connectArray(LinkedList[] A)
	{
		// Now array has been written to : connect nodes as expected
		for(int i = 0; i < A.length-1; ++i)
		{
			A[i].next = A[i+1];
		}
		A[A.length-1].next = null;
  }
	
	public LinkedList zipLinkedList(LinkedList linkedList) 
	{
		// Trivial base cases
		if(linkedList == null || linkedList.next == null)
		{
			return linkedList;
		}
		LinkedList newHead = linkedList;
		int n = getSize(linkedList);
		LinkedList A[] = new LinkedList[n];
		createArrayOfNodes(linkedList, A);
		connectArray(A);
		return newHead;
	}

}




	public LinkedList zipLinkedList(LinkedList linkedList) 
	{
		// Trivial base cases
		if(linkedList == null || linkedList.next == null)
		{
			return linkedList;
		}
		LinkedList newHead = linkedList;
		Stack<LinkedList> revOrder = new Stack<LinkedList>();
		LinkedList head = linkedList;
		while(head != null)
		{
			revOrder.push(head);
			head = head.next;
		}
		head = linkedList;
		while(head != null || head.next != null)
		{
			if(head == revOrder.peek())
			{
				head.next = null;
				break;
			}
			LinkedList fwd = head.next;
			LinkedList topMost = revOrder.pop();
			head.next = topMost;
			// Even length case
			if(topMost == fwd)
			{
				topMost.next = null;
				break;
			}
			topMost.next = fwd;
			head = fwd;
		}
		
		return newHead;
	}




