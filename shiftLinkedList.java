/*

Function takes in the head of a SLL an an integer <K>
Shifts the list "in-place" ( do not create a brand new list ) by k positions
Return the new head

Shift = move fwd or move bkwd : wrap around where needed
Signage of k determines a fwds or a bkwds shift
ONLY the TAIL or a SLL points to the NULLPTR as well
Assume list node always has at least ONE node here

Handle k = 0 case => immediate return
If +k -> shift tail to be new head
If -k => shift head to be new tail
	Seems -k version is a tad bit easier
	
Leverage modulus to figure out the new start and the new tail as well?

Complexity : 
Let N := sz(head)
Time = O(N)
Space = O(1)

Emphasis on ordering of functional decomposition here as well!

*/

import java.util.*;

class Program 
	{

	// Grab size info => linear time => to perform a modulus operation
	public static LinkedList shiftLinkedList(LinkedList head, int k) 
	{
		int sz = getSizeOfLinkedList(head);
		LinkedList tail = getTailOfLinkedList(head);
		int shift = getTrueShift(k,sz);
		LinkedList resultStart = performShift(head,tail,shift);
		return resultStart;
  }
	
		// Make an iterative approach
	private static int getSizeOfLinkedList(LinkedList head)
	{
		int sz = 0;
		LinkedList cur = head;
		while(cur != null)
		{
			sz++;
			cur = cur.next;
		}
		return sz;
	}
	
	// Grab tail information as well : we append to that
	private static LinkedList getTailOfLinkedList(LinkedList head)
	{
		LinkedList tail = head;
		while(tail.next != null)
			tail = tail.next;
		return tail;
	}
	
	private static int getTrueShift(int k, int sz)
	{
		int shift = (k % sz)%sz;
		if(shift < 0)
			shift = (int)Math.abs(shift);
		else if ( shift > 0)
			shift = (int)Math.abs(shift - sz);
		return shift;
	}
	
	private static LinkedList performShift(LinkedList head, LinkedList tail, int shift)
	{
		LinkedList resultStart = head;
		if(shift == 0)
			return head;
		LinkedList lhs = head;
		for(int i = 0; i < (shift-1); ++i)
			lhs = lhs.next;
		resultStart = lhs.next;
		lhs.next = null;
		tail.next = head;
		return resultStart;
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
