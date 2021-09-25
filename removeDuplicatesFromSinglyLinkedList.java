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

  public LinkedList removeDuplicatesFromLinkedList(LinkedList head) 
	{
		if(head == null || head.next == null)
			return head;
		LinkedList cur = head;
		LinkedList after = head.next;
		helper(cur, after);
		return head;
	}
	
	// Break down into smaller subproblems for convergence
	// Passing two pointers here for recursive approach
	public void helper(LinkedList cur, LinkedList after) 
	{
		if(after == null)
			cur.next = after;
		else
		{
			if(cur.value == after.value)
			{
				after = after.next;
				helper(cur,after);
			}
			else
			{
				cur.next = after;
				after = after.next;
				cur = cur.next;
				helper(cur,after);
			}
		}
  }
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

  public LinkedList removeDuplicatesFromLinkedList(LinkedList head) 
	{
		if(head == null || head.next == null)
		{
			return head;
		}
		else
		{
			LinkedList cur = head;
			LinkedList after = head.next;
			while(after != null)
			{
				if(cur.value == after.value)
					after = after.next;
				else
				{
					cur.next = after;
					after = after.next;
					cur = cur.next;
				}
			}
			cur.next = after; // after is null at this point, current is final node
		}
		return head;
  }
}



/*
Remove duplicates from a SLL
Given the head of a SLL who nodes are in SORTED order W.R.T. their values
Returns a modified version of SLL without duplicate nodes

linkedList = 1 -> 1 -> 3 -> 4 -> 4 -> 4 -> 5 -> 6 -> 6 
Output = 1 -> 3 -> 4 -> 5 -> 6
Return : 1->3->4->5->6

Modify in place : do not create AUX storage for new SLL
Nodes should still be sorted W.R.T. values

size(SLL) : reasonable
Let's handle empty/null -> return that list

Values \in Z ( integers ) 

Complexity : 
Let N := len(SLL)/#-elements
Time - O(N)
Space - O(1)

Edge Cases 
(A) null/empty SLL
(B) {1}
	=> {1}
(C) {1,2,3}
	=>{1,2,3}
(D) {1,1,2}
	{1,2}
(J) {1,0,0}
	{1,0}
(E) {1,1,2,2,3,3,3}
	{1,2,3}
(F) {1,1,1,2,2,3}
	{1,2,3}
(G) {-2,-2,-1,0,1}
	{-2,-1,0,1}
(H) {1,1,1,2,3,4}
(I) { 2,3,4,5,5,5}
(J) {1,2,2,2,2,3}
			 ^       ^ 
			 c       a
(K) {1,2,2,2}
	     ^      ^
			 c      a

Recursive/Iterative approach
HashSet<Integer>
Pseudocode
3 pointers { prev, cur, next } 
pairwise element comparison ( cur,next ) 

removeDuplicates(SLL head) :
	if(head == null || head.size() == 0)
		return head;
	ListNode cur = head;
	ListNode after = head.next;
	while(after != null)
	{
		int curVal = cur.value;
		int afterVal = after.value;
		if(curVal == afterVal)
		{
			cur.next = after.next;
			after = after.next;
			cur = cur.next;
		}
		else
		{
			after = after.next;
			cur = cur.next;
		}
	}
	return head;	
*/

Implicit function call stack space -> takes space
Runtimes : no change there in time-space complexity


O(N) implicit function call stack space ( activation records/stack frames ) 







