Problem : Given two singly-linked list heads
Goal : merge these SLLs 
Results : list nodes have to be in sorted order
Can not create a new SLL : must be done in-place

Strategy : two pointers, comparison, iterative ( recursion ) 
	-> current and nextPtr
	
	
headOne = 2 -> 6 -> 7 -> 8 // the head node with value 2
					     ^		^
							 c1		n1			|-
headTwo = 1 -> 3 -> 4 -> 5 -> 9 -> 10 // the head node with value 1
							 		  		 ^		^
							 		  		c2		n2
												
												
				 1 -> 3 -> 4 -> 5->6->7->8  		9 -> 10
																 ^			^
																 c1 		c2
																 
headOne = 2 -> 3 6 -> 7 -> 8
								 
headTwo = 1 -> 2->3 -> 4 -> 5 -> 6->7->8->9 -> 10
				 			              							^		 ^
							              		 					c2   n2
					mH			
					
mergedHead ( mH )  = headTwo
																 
				 
Partition as we go
5->6
6->9
but I expect 6->7->8->9
That is a bug

Why not a temp swp?

1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 // the new head node with value 1

Need to compare both the next and the cur elem for range insertion

Inputs : 
	1. Can either heads be null or empty lists? - yes
	2. Can sizes differ - yes
	3. Can we have dupls - Yes
	4. Can be any z \in Z - Yes
	5. Guaranteed sorted Order in both

Modify either headOne or headTwo here
Which one is the minimum head ( <= )

Complexity : 
Let M := len(headOne), N := len(headTwo)
Time = O(M+N) : each elem iterated only once
Space = O(1) ( explicit ) O(1) ( implicit ) 

Test Cases : 
[],[] = headOne,headTwo
(A) [1],[1]
(B) [1],[2] or [2],[1]
(C) [1,2,3],[] or [],[1,4,5]
	list1
(D) [1,3], [4,5,6] 
(E) [1,3,5],[2,4]		( alternating sequence  )
(F) 
(G) 

Pseudocode : 
	





// Just  second
import java.util.*;

class Program {
  // This is an input class. Do not edit.
  public static class LinkedList {
    int value;
    LinkedList next;

    LinkedList(int value) {
      this.value = value;
      this.next = null;
    }
  }

	// Iterative approach
	// Why not a sentinel dummy node : headOne = MIN_INT, headTwo = MIN_INT [-10K,10K] : sentinel = MIN_INT
	// return the next
	
  public static LinkedList mergeLinkedLists(LinkedList headOne, LinkedList headTwo) 
	{
		if(headOne == null && headTwo == null)
		{
			return null;
		}
		LinkedList sentinel = new LinkedList(Integer.MIN_VALUE);
		sentinel.next = headOne;
		LinkedList curOne = sentinel;
    LinkedList curTwo = headTwo;
		
    // Need to preserve state during cross-list merging of pointers
		LinkedList nextOne = sentinel.next;
    LinkedList nextTwo = headTwo.next;
		
		// If they are both equal : can break easily
		while(curOne != null && curTwo != null)
		{
			// Ensure this inequality works as expected
			if(curOne.value <= curTwo.value)
			{
				if(nextOne == null)
				{
					curOne.next = curTwo;
					break;
				}
				else if(nextOne.value <= curTwo.value )
				{
					curOne = curOne.next;
				}
				else if ( nextOne.value > curTwo.value )
				{
					nextOne = curOne.next;
					curOne.next = curTwo;
					curOne = nextOne;
				}
				nextOne = curOne.next;
			}
			// careful if next == null : can not get .value from that!
			else if(curTwo.value < curOne.value)
			{
				if(nextTwo == null)
				{
					curTwo.next = curOne;
					break;
				}
				else if (nextTwo.value < curOne.value)
				{
					curTwo = curTwo.next;
				}
				else if ( nextTwo.value >= curOne.value)
				{
					nextTwo = curTwo.next;
					curTwo.next = curOne;
					curTwo = nextTwo;
				}
				nextTwo = curTwo.next;
			}
		}
		// No need to iterate over rests of SLLL : leverage increasing order property
    // return mergedHead;
		return sentinel.next;
  }
}

