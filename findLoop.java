Write a function that takes in the head of a Singly Linked List that contains a loop 
(in other words, the list's tail node points to some node in the list instead of None / null). 
The function should return the node (the actual node--not just its value) 
from which the loop originates in constant space.

Each LinkedList node has an integer value as well as a next node pointing to the next node in the list.


Input is the head of a SLL
There can be a loop/a cycle
Return the head of the loop

Clarifications
- const space : implicit ( recursively - no ) & excplicit
- Aim for an iterative approach
- SLL can point to null
- Values can be duplicates?
- Guaranteed cycle existence
- Modifying not allowed

Strategies : 
Had space : Visited set
2 pointers approach ( fast & slow ) 




Complexity
Let N := length(SLL ) 
Naive 
	Time = O(N), O(N) Space
Better
	Time = O(N)
	Space = O(1)
	
Node that is in between the cyclic and non-cyclic portions

		HEAD
				 		( S,F)
		[]->[]->[]
				|		 |
				^----<

			 	d	  		 					 					 (s,f)
		-------------------  p
		c                   ----------s
head = 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 // the head node with value 0
                           ^         v
                           9 <- 8 <- 7 
	while c!= s:
		c = c.next
		s = s.next
		
		
	d is lenght from head of ll to the head of the loop
	p is             head of the loop to the plae where 2 pointer meet
	l is the lenth of the loop 
	d + p
	2*(d+p)
	
	faster has traveled: d + l + p = 2*(d+p)
							l = d+p
							d = l-p
		
											d		
								========== 
 					head = 2 -> 3 -> 4 -> 5 -> 6 // the head node with value 0
									 				 ^         v
									 				 9 <- 8 <- 7 
															 (s,f)
																
						  4->4 : l = 6
						  4->6 : p
						  6->4 : l - p = d
						  l - p = 4
							d = 2
							p = 4
							
l is the length of loop

Let P := len of portion to where non-cyclical met the pointer
Can keep counter for #-steps
Slow pointer  - 6 steps: d + p
Fast pointer - 	12 steps: 2(d+p)

		6		=	d+p
		12	=	2(d+p)
		12 = 	 (d+p) + l
		l = 12 - 6
		
		2(d+p) = (d+p) + l
		
		2d + 2p = d+p+l
		d+p = l = 6 ( d,p are still unsolved )
		l - p = d
		
		

NOde 4 : 4 steps away from node 0
Cycle length = 6 here
	-> need to be 2 steps away ( node 4 ) 
	





import java.util.*;

class Program {
  public static LinkedList findLoop(LinkedList head) 
	{
		if(head.next == head)
		{
			return head;
		}
		else
		{
			LinkedList slow = head;
			LinkedList fast = head;
			slow = slow.next;
			fast = fast.next.next;
			while(slow != fast)
			{
				slow = slow.next;
				fast = fast.next.next;
			}
			// Slow,fast have met
			LinkedList cur = head;
			while(cur != slow)
			{
				slow = slow.next;
				cur = cur.next;
			}
			return cur;
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








