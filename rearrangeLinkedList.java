Goal : Write a func, 
Given a number <k>, rearrange the nodes of the list s.t. vals < k are before it, and vals > k 
should be after it ( akin to the quick sort pivot step ) 


PSEUDOCODE : 

	LinkedList headLess = null;
	LinkedList headMore = null;
	LinkedList headEqaul = null;
	
	// One pass
	boolean foundHeadLess = false;
	boolean foundHeadMore = false;
	boolean foundHeadEqual = false;
	LinkedList cur = head;
	// Single-pass
	while(cur != null)
	{
		if(cur.val > k && foundHeadMore == false)
		{
			headMore = cur;
			headFoundMore = true;
		}
		else if ( cur.val < k && foundHeadLess == false)
		{
			headLess = cur;
			headFoundLess = true;		
		}
		else if ( cur.val == k && foundHeadEqual == false)
		{
			headEqual = cur;
			headFoundEqual = true;		
		}
		cur = cur.next;
	}
	
	LinkedList tailLess = headLess;
	LinkedList tailMore = headMore;
	LinkedList tailEqual = headEqual;
	
	// Must compare each node val to <k> here
	// Addresse : compare address as well!
	// Do not double include with heads already set
	// Single pass
	ListNode cur = head;
	while(cur != null)
	{
		if(cur.val < k)
		{
			if(cur != headLess)
			{
				tailLess.next = cur;
				tailLess = tailLess.next;
			}
		}
		else if ( cur.val > k ) 
		{
			if(cur != headMore)
			{
				tailMore.next = cur;
				tailMore = tailMore.next;
			}
		}
		else
		{
			if(cur != headEqual)
			{
				tailEqual.next = cur;
				tailEqual = tailEqual.next;
			}
		}
		ListNode fwd = cur.next;
		cur.next = null;
		cur = fwd;
	}
	
	if(headLess != null)
	{
		tailLess.next = headEqual;
		if(headEqual != null)
		{
			tailEqual.next = headMore;
		}
		else
		{
			tailLess.next = headMore;
		}
		return headLess;
	}
	else
	{
		if(headEqual != null)
		{
			tailEqual.next = headMore;
			return headEqual;
		}
		else
		{
			return headMore;
		}
	}

k = 100
k = -100

head = 3 -> 0 -> 5 -> 2 -> 1 -> 4 // the head node with value 3
			 ^
			HEAD
			TAIL	^
					 NEWHEAD
					 
3C2 combos of list ptrs

Let us work with tails here
Problem : can all tails truly be set to null here?
Maybe need nested loops for ( tG, tL, tE ) ptrs & ( hG, hL, hE ) ptrs. Also adj in general
	3 lists?
	list < , list =, list >
	
May not even have ( hE, tE ) case
	
Keep the heads and the tails of the '>' and the '<' SLLs 
head = 3 -> 0 -> 5 -> 2 -> 1 -> 4 // the head node with value 3
			 		
			  		    tG	  tL

head = 2 -> 1 -> 4 // the head node with value 3
			 	    ^		 ^
			 	    tL   tG

			3->5->4
			0->2
			 
Check which one we broke out of first ( bool scoped var )
while(tails of each list not null )

While(tL != null & tG != null) 

if(tG == null && tL != null)
	tL.next = null
if(tL == null && tG !+ null)
	tG.next = null

tL.next = hG
return hL;

			 
k = 3

										|
output = 0 -> 2 -> 1 -> 3 -> 5 -> 4

Clarifications
(1) Original relative ordering of the SLL preserved - yes
(2) SLL can not be null or empty : has at least one node ( singleton ) 
(3) Can have duplicate values
(4) Addresses have to change properly for the new head of SLL
(5) Can have negative values and are integer data type
(6) Can k be out of range of node els - yes
(7) k need not be in the input
(8) Must modify in-place : no creation
Trivial case - singleton

Thought process : 
- single-pass, done inductively




COMPLEXITY
Let N := len(SLL)
Time = O(N)
Space = O(1) ( implicit ) O(1) ( explicit ) 

TEST CASES
(A) 3 -> 0 -> 5 -> 2 -> 3 -> 3 -> 3 -> 1 -> 4 
	0 -> 2 -> 1 -> 3 -> 3 -> 3 -> 5 -> 4
(B) 3 -> 0 -> 5 -> 2 -> 1 -> 4 , k = -100
(C) 3 -> 0 -> 5 -> 2 -> 1 -> 4 , k = 100
(D) 1->0->3->-1, k = 2
(E)
(F)

Integers are a well-ordered set
	

import java.util.*;

class Program {
  public static LinkedList rearrangeLinkedList(LinkedList head, int k) 
	{
		if(head == null || head.next == null)
		{
			return head;
		}
		LinkedList headLess = null;
		LinkedList headMore = null;
		LinkedList headEqual = null;

		// One pass
		boolean foundHeadLess = false;
		boolean foundHeadMore = false;
		boolean foundHeadEqual = false;
		LinkedList cur = head;
		while(cur != null)
		{
			if(cur.value > k && foundHeadMore == false)
			{
				headMore = cur;
				foundHeadMore = true;
			}
			else if ( cur.value < k && foundHeadLess == false)
			{
				headLess = cur;
				foundHeadLess = true;		
			}
			else if ( cur.value == k && foundHeadEqual == false)
			{
				headEqual = cur;
				foundHeadEqual = true;		
			}
			cur = cur.next;
		}
	
		LinkedList tailLess = headLess;
		LinkedList tailMore = headMore;
		LinkedList tailEqual = headEqual;
	
		cur = head;
		while(cur != null)
		{
			if(cur.value < k)
			{
				if(cur != headLess)
				{
					tailLess.next = cur;
					tailLess = tailLess.next;
				}
			}
			else if ( cur.value > k ) 
			{
				if(cur != headMore)
				{
					tailMore.next = cur;
					tailMore = tailMore.next;
				}
			}
			else
			{
				if(cur != headEqual)
				{
					tailEqual.next = cur;
					tailEqual = tailEqual.next;
				}
			}
			LinkedList fwd = cur.next;
			cur.next = null;
			cur = fwd;
		}
	
		if(headLess != null)
		{
			tailLess.next = headEqual;
			if(headEqual != null)
			{
				tailEqual.next = headMore;
			}
			else
			{
				tailLess.next = headMore;
			}
			return headLess;
		}
		else
		{
			if(headEqual != null)
			{
				tailEqual.next = headMore;
				return headEqual;
			}
			else
			{
				return headMore;
			}
		}		
  }

  static class LinkedList {
    public int value;
    public LinkedList next;

    public LinkedList(int value) {
      this.value = value;
      next = null;
    }
  }
}




	
