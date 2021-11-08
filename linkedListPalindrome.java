
You are provided a SLL as an input
Write a func to check if that LL is a palindrome or not

Not allowed to use auxillary data structures ( stk/set/etc., )
Assume SLL always has at least one node ( not null ) 
It can have duplicates chars & the alphabet should not matter
Most vals are integers here


head = 0 -> 1 -> 2 -> 2 -> 1 -> 0
Problem solving strategies : two pointers, recursion, iterative, in-place modifications of pointers

  l --><-- r
['a','b','a'], ['a','a']
Random access pattern lacking : 
DLL : it's easier

Complexity
We can use space
Do not create another SLL
Recursive methods : implicit func call stack ( try to exploit that )
Let N := #-nodes / len of the SLL
Time = O(N)
Spac = O(1) ( explicit ) O(1) ( implicit ) 

TEST BENCH : 
(A) 1
	-> always valid ( singleton ) 
(B) 1->1
		 |
(C) 1->2->1	 ( odd length cases : can skip the middle in a sense : reflexive here )
			 |
(D) 1->2->3->4
			 |
			 mid	
			 head of second partition = 2.next
			 2.next = null
				
				
(E) 1->2->3->2->1
					|
(F) 1->2->3->4->5
					|
					mid
		1->2->3->4->5
		4->5 ( 3.next)
		1->2->3->4->5
		3 = null
		1->2
					
first half			1->2->3		
reversed first	3->2->1
second half			3->4->5
head(reversed first ) = head(second) anyways
 
List parity : even/odd
(F) 1->2->3->4
first half			1->2		
reversed first	2->1
second half			3->4
head(reversed first ) != head(second) anyways

fast : 2 nodes, second node ( head.next ) 
slow : 1 node, first node ( head )


  public boolean linkedListPalindrome(LinkedList head) 
	{
		boolean isPalin = false;
		if(head == null || head.next == null)
		{
			return true;
		}
		LinkedList[] parts = getPartitions(head);
		LinkedList reversedHead = reverse(parts[0]);
		isPalin = isSame(reversedHead, parts[1]);
		return isPalin;
  }
	
	// Sizes guaranteed sameness here anyways
	// O(N) Time, O(1) space
	private boolean isSame(LinkedList one, LinkedList two)
	{
		boolean status = true;
		while(one != null)
		{
			if(one.value != two.value)
			{
				status = false;
				break;
			}
			else
			{
				one = one.next;
				two = two.next;
			}
		}
		return status;
	}
	
	private LinkedList reverse(LinkedList head)
	{
		LinkedList prev = null;
		LinkedList curr = head;
		LinkedList next = curr.next;
		while(next != null)
		{
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}
	
	
	
	
	// Get parity information here quickly
	// O(N) T, O(1) S
	public LinkedList[] getPartitions(LinkedList head)
	{
		int len = 0;
		LinkedList slow = head;
		LinkedList fast = head.next;
		// while(fast != null && fast.next != null)	// double skipping here ( short-circuit evaluation )
		while(fast != null )
		{
			fast = fast.next;
			if(fast == null)
			{
				break;
			}
			else
			{
				++len;
				fast = fast.next;
				if(fast != null)
				{
					++len;
				}
				slow = slow.next;
			}
		}
		// Have a length value : get partitions
		LinkedList partOne = head
		LinkedList partTwo = slow.next;
		if(len % 2 == 1)
		{
			slow = null;
		}
		else
		{
			slow.next = null;
		}
		return new LinkedList[]{partOne, partTwo};
  }
	
	
	
	
	
	
	public LinkedList getMiddleNode(LinkedList head) 
	{
		LinkedList slow = head;
		LinkedList fast = head.next;
		// while(fast != null && fast.next != null)	// double skipping here ( short-circuit evaluation )
		while(fast != null )
		{
			fast = fast.next;
			if(fast == null)
			{
				break;
			}
			else
			{
				fast = fast.next;
				slow = slow.next;
			}
		}
		return slow;
  }
	
	




import java.util.*;

class Program {
  // This is an input class. Do not edit.
	//can you hear me - no. Try reconnecting. 
  public static class LinkedList {
    public int value;
    public LinkedList next;

    public LinkedList(int value) {
      this.value = value;
      this.next = null;
    }
  }

  public boolean linkedListPalindrome(LinkedList head) 
	{
		boolean isPalin = false;
		if(head == null || head.next == null)
		{
			return true;
		}
		LinkedList[] parts = getPartitions(head);
		System.out.printf("Head of partition one = [%d] \t Head of partition two = [%d]\n", parts[0].value, parts[1].value);
		// LinkedList reversedHead = reverse(parts[0]);
		LinkedList temp = parts[0];
		while(temp != null)
		{
			System.out.printf("%d\t", temp.value);
			temp = temp.next;
		}
		// isPalin = isSame(reversedHead, parts[1]);
		return isPalin;
  }
	
	// Sizes guaranteed sameness here anyways
	// O(N) Time, O(1) space
	private boolean isSame(LinkedList one, LinkedList two)
	{
		boolean status = true;
		while(one != null)
		{
			if(one.value != two.value)
			{
				status = false;
				break;
			}
			else
			{
				one = one.next;
				two = two.next;
			}
		}
		return status;
	}
	
	private LinkedList reverse(LinkedList head)
	{
		LinkedList prev = null;
		LinkedList curr = head;
		LinkedList next = curr.next;
		while(next != null)
		{
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}
	
	// Get parity information here quickly
	// O(N) T, O(1) S
	private LinkedList[] getPartitions(LinkedList head)
	{
		int len = 1;
		LinkedList slow = head;
		LinkedList fast = head.next;
		// while(fast != null && fast.next != null)	// double skipping here ( short-circuit evaluation )
		// Increment {slow,fast} in an atomic way to get exact mid element as well
		while(fast != null )
		{
			if(fast.next != null)
			{
				if(fast.next.next != null)	// even len case : can incr fast by two
				{
					// can do a double skip
					fast = fast.next.next;
					len += 2;
					slow = slow.next;
				}
				else // odd length case ( can incr fast by only 1)
				{
					
				}
			}
			else	// also an odd case
			{
				
			}
			
			
			
			fast = fast.next;
			++len;
			if(fast == null)
			{
				break;
			}
			else
			{
				fast = fast.next;
				++len;
				slow = slow.next;
			}
		}
		System.out.printf("len = %d\n", len);
		// Have a length value : get partitions
		LinkedList partOne = head;
		LinkedList partTwo = slow;
		if(len % 2 == 1)
		{
			partTwo = slow.next;
			slow = null;
		}
		else
		{
			slow.next = null;
		}
		return new LinkedList[]{partOne, partTwo};
  }
	
	
	
	
	
	
}







https://www.amazon.com/Dynamic-Programming-Coding-Interviews-Bottom-Up/dp/1946556696/ref=asc_df_1946556696?tag=bingshoppinga-20&linkCode=df0&hvadid=80264402445016&hvnetw=o&hvqmt=e&hvbmt=be&hvdev=c&hvlocint=&hvlocphy=&hvtargid=pla-4583863988717465&psc=1
Dynamic Programming for Coding Interviews: A Bottom-Up approach to problem solving 
by Meenakshi  (Author), Kamal Rawat  (Author)

















