Task : Reverse a singly-linked list
Every two nodes in the list : you reverse them 

Write a func, where you swap every pair, or adjacent nodes, in-place
No additional auxillary space allowed

Thought process
- 2 pointer approach, Iterative (Recursively), One Pass/Two Pass
- use a boolean flag instead : counter operation costs computation

PSEUDOCODE : 

// May need to link to previous parent too
// May break out later
Node prev = null;	// check this too ( first case ) 
Node cur = head;
Node fwd = cur.next;
// Update the adjacent pointers information over iteration
while(cur != null || cur.next != null)
{
	fwd = cur.next;
	cur.next = fwd.next;
	fwd.next = cur;
	cur = cur.next;
	if(prev != null)
	{
		prev.next = fwd;
	}
	prev = fwd;
}


head = 0 -> 1 -> 2 -> 3 -> 4 -> 5 //
			 ^		^
			 C		N

		1 -> 0 -> 2 -> 3 -> 4 -> 5 
				 ^		^		 ^				
				 P		C		 N
										
		1 -> 0 -> 3 -> 2 -> 5 -> 4->7
									 ^		^		 ^
									 P		C		 N
		
		1 -> 0 -> 3 -> 2 -> 5 -> 4->7->9->10
													   ^	^	 ^
									 					 P	C	 N	
		
		1 -> 0 -> 3 -> 2 -> 5 -> 4->9->7->10
													   		^	 ^  ^
									 					 		P	 C  N	
		
		Previous always equal to wherever next was in the former step
		
		The address of the head changes : must swap node objects themselves ( the addresses ) 
		New head points to new node
		
		Invariant : head is always the second node 

Clarify : 
1. Can SLL be null/empty : yes -> just return head as is
2. Can be any length/any parity ( odd/even ). Do not reverse final element
3. Reversing must start from first element
4. Can not use additional data structures
5. In-place modifications allowed
6. Values in the nodes could be duplicates
7. No cycles

Complexity
Let N := len(SLL)
Time = O(N) one-pass
Space = O(1) ( explicit & implicit ) 

Test Cases : 
(A) 1
(B) null
(C) 1->2
(D) 1->2->3
(E) 1->2->3->4
(F) 1->2->3->4->5

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

  public LinkedList nodeSwap(LinkedList head) 
	{
		if(head == null || head.next == null)
		{
			return head;
		}
		LinkedList newHead = head.next;
		// May need to link to previous parent too
		// May break out later
		LinkedList prev = null;	// check this too ( first case ) 
		LinkedList cur = head;
		LinkedList fwd = cur.next;
		// Update the adjacent pointers information over iteration
		int iter = 0;
		while(cur != null && cur.next != null)
		{
			fwd = cur.next;
			cur.next = fwd.next;
			fwd.next = cur;
			LinkedList formerCur = cur;
			cur = cur.next;
			if(prev != null)
			{
				prev.next = fwd;
			}
			prev = formerCur;
		}
		
    return newHead;
  }
}


	// We need to track the prev node, and the cur node
	private LinkedList recurse(LinkedList head)
	{
		if(head == null && head.next == null)
		{
			return head;
		}
		else
		{
			LinkedList prev = null;
			LinkedList cur = head;
			LinkedList fwd = cur.next;
			LinkedList formerCur = cur;
			LinkedList formerFwd = fwd;
			
			fwd = cur.next;
			cur.next = fwd.next;
			fwd.next = cur;
			cur = cur.next;
			
			prev = formerCur;	
			LinkedList remainder = recurse(cur);
			prev.next = remainder;
			
			return formerFwd;
		}
  }


