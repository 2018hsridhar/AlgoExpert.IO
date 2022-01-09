head = 0 -> 1 -> 2 -> 3 -> 4 -> 5 // the head node with value 0
Sample Output
5 -> 4 -> 3 -> 2 -> 1 -> 0 // the new head node with value 5

Constraints
- Input is a SLL
- Reversal is IN-PLACE : no extra auxillary space creatable
- return new Head
- lats Elem : points to None/NULL
- at least one node ( never empty/null node )
- values can be duplicate : no constraints
- no func call stack space either


Iterative
new Head := final element of the SLL
Two ptr/Three ptr approach ( curr/next OR prev/curr/next )
	Extra ptrs for storage
	Is a sentinel node needed too?
Base case : single node case ( node.next = null : rev(single_node) = (single_node))
Forgot to modify current's point
Use two ptrs only

0 -> 1 -> NULL
^		 ^		^
C		 F		R

0 -> 1 -> 2 -> 3 -> 4 -> 5
^		 ^		^
C		 F		R

<-0 <- 1    2 -> 3 -> 4 -> 5
   		^		  ^		 ^
		  C		  F		 R
		
0 <- 1 <- 2 <- 3 <- 4 ->  5	-> NULL
				  		      ^     ^		  ^
				  		      C     F		  R		
		
0 <- 1 <- 2 <- 3 <- 4 <- 5	-> NULL
				  		           ^		  ^
				  		           C		  N
												
												(newHead) when N points to NULL
												current points to the expected correct head
												
PSEUDOCODE :

	if head.next == null
		ret head
	Node newHead = cur
	
	Node cur = head
	cur.next = null			// adjustment
	Node fwd = head.next
	Node remain = fwd.next
	while remain NOT null :
		fwd.next = cur
		cur = fwd
		fwd = remain
		remain = fwd.next						# but remainder is NULL : exit out cuz of bug
	# remainder null now
	fwd.next = cur
	newHead = fwd
	return newHead

COMPLEXITY
Let N := len(SLL)
TIME = O(N) linear scan
SPACE = O(1)

TEST CASES :
(A) [1]
(B) [1,2]
(C) [1,2,3]
	Parity difference ( odd/even ) -> do well most caes


Head recursive OR tail recursive ( *** call stk optimizable )
Recursive implementation : 


TIME = O(N)
SPACE = O(N) ( CALL STK ) O(1) EXP


NULL<-0 <-1 -> 2 -> 3 -> 4 -> 5
			    ^	   ^
		      C		 F
		 
0 <- 1    2 -> 3 -> 4 -> 5
		 ^		^
		 C		F


0 <- 1 <- 2    3 -> 4 -> 5
				 	^		 ^
		 			C		 F

Return a pointer -> based on whether next node is NULL or not
	<- backtrack a pointer too
	
Pass in two ptrs possible?

wrapper:
	cur = head
	fwd = head.next
	cur.next = null
	recursiveRev(fwd,cur)

Node recursiveRev(Node fwd, Node cur):

	Node remain = fwd.next
	// [1] terminating
	if(remain is NULL)
		fwd.next = cur
		return fwd
	// [2] non-terminating
	else
		fwd.next = cur
		return recursiveRev(remain,fwd)

{
  "head": "0",
  "nodes": [
    {"id": "0", "next": "1", "value": 0},
    {"id": "1", "next": "2", "value": 1},
    {"id": "2", "next": null, "value": 2},
    {"id": "3", "next": "4", "value": 3},
    {"id": "4", "next": "5", "value": 4},
    {"id": "5", "next": null, "value": 5}
  ]
}




Get pointers to the end
Might have additional storage in the call stack frames

										peek	pop
										|		 |
0 -> 1 -> 2 -> 3 -> 4 -> 5
										^		 ^
										F		 R
										
0 -> 1 -> 2 -> 3 <- 4 <- 5
							 ^	  ^	
							 F    R
							 
0 <- 1 <- 2 <- 3 <- 4 <- 5
^	   ^	
F    R

return a pointer to the head


import java.util.*;

class Program {
  public static LinkedList reverseLinkedList(LinkedList head) 
	{
		if(head.next == null)
			return head;
    LinkedList cur = head;
		LinkedList fwd = head.next;
		cur.next = null;
		return recursiveRev(fwd,cur);
  }
	
	// Top-down, tail-recursive manner
	private static LinkedList recursiveRev(LinkedList fwd, LinkedList cur)
	{
		LinkedList remain = fwd.next;
		// [1] terminating
		if(remain == null)
		{
			fwd.next = cur;
			return fwd;
		}
		// [2] non-terminating
		fwd.next = cur;
		return recursiveRev(remain,fwd);	// call stack optimizable
	}
	
  static class LinkedList {
    int value;
    LinkedList next = null;

    public LinkedList(int value) {
      this.value = value;
    }
  }
}

import java.util.*;

class Program {
  public static LinkedList reverseLinkedList(LinkedList head) 
	{
		if(head.next == null)
		{
			return head;
		}
		LinkedList cur = head;
		LinkedList fwd = head.next;
		cur.next = null;
		while(fwd.next != null)
		{
			LinkedList remain = fwd.next;
			fwd.next = cur;
			cur = fwd;
			fwd = remain;
		}
		// remainder null now
		fwd.next = cur;
		LinkedList newHead = fwd;
		return newHead;
  }

  static class LinkedList {
    int value;
    LinkedList next = null;

    public LinkedList(int value) {
      this.value = value;
    }
  }
}




import java.util.*;

class Program {
  public static LinkedList reverseLinkedList(LinkedList head) 
	{
		return reverseLinkedListTopDown(head);
	}
	
	private static LinkedList reverseLinkedListTopDown(LinkedList head)
	{
		if(head.next == null)
			return head;
    LinkedList cur = head;
		LinkedList fwd = head.next;
		cur.next = null;
		LinkedList[] newHead = new LinkedList[1];		// potential bug
		newHead[0] = null;
		LinkedList newTail = topDownHelper(cur,fwd,newHead);
		newTail.next = null;
		return newHead[0];
  }
	
	// Creating new memory for this?
	// Passing it is as an arrray -> changig mem refs
	// C++ : *newHead ( addr ) : reference != ptr 
	private static LinkedList topDownHelper(LinkedList cur, LinkedList fwd, LinkedList[] newHead)
	{
		// [1] Base case / initiaing call stack unwinding
		if(fwd.next == null)
		{
			newHead[0] = fwd;
			fwd.next = cur;
			return cur;
		}
		else
		{
			// [2] Recursive cases
			LinkedList remain = fwd.next;
			LinkedList newTail = topDownHelper(fwd,remain,newHead);
			newTail.next = cur;
			return cur;
		}
	}
	

	
	private static LinkedList reverseLinkedListBottomUp(LinkedList head)
	{
		if(head.next == null)
			return head;
    LinkedList cur = head;
		LinkedList fwd = head.next;
		cur.next = null;
		return recursiveRev(fwd,cur);
  }
	
	// Top-down, tail-recursive manner
	private static LinkedList recursiveRev(LinkedList fwd, LinkedList cur)
	{
		LinkedList remain = fwd.next;
		// [1] terminating
		if(remain == null)
		{
			fwd.next = cur;
			return fwd;
		}
		// [2] non-terminating
		fwd.next = cur;
		return recursiveRev(remain,fwd);	// call stack optimizable
	}
	
  static class LinkedList {
    int value;
    LinkedList next = null;

    public LinkedList(int value) {
      this.value = value;
    }
  }
}
