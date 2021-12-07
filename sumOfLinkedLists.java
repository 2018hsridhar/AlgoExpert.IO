/*

SLLs can be of unequal length
Node values can be non-negative integers
First nodes = LSB / leasat significant digits of said integers
We need a carry operation
Can we do this iteratively, ideally ( not recursively )
Build a new SLL := sum ( two inputs ) 

Do NOT modify the inputs as you go over this.
Can we avoid an initial pass to get their sizes as well here?

You return the head of a new SLL, which points to the Least Sig Digit, of the resultant value from the computation as well.

*/

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

	// Problem will ask a boudn too : 9999 + 9999 < 10000 + 10000 = 20000. Only one digit appended at worst
	// So for 99 + 999999, we append only one digit
	// 999999 + 999999 < 1000000 + 1000000 = 2000000 ( although all others get us 1 digit to : bounded below by 1000000 ) 
  public LinkedList sumOfLinkedLists(LinkedList linkedListOne, LinkedList linkedListTwo) 
	{
		LinkedList resultHead = null;
		LinkedList prev = null;
		LinkedList ptr1 = linkedListOne;
		LinkedList ptr2 = linkedListTwo;
		int carry = 0;
		int sum = 0;
		while(ptr1 != null && ptr2 != null)
		{
			sum = (ptr1.value + ptr2.value + carry);	// Note order of eval : for both carry and the sum too. 
			if(sum >= 10)
			{
				carry = sum / 10;
				sum -= 10;
			}
			else
			{
				carry = 0;
			}
			LinkedList sumNode = new LinkedList(sum);
			if(resultHead == null)
			{
				resultHead = sumNode; 	// so set this pointer, but ONLY one one case too!
				prev = resultHead; 			// for future step
			}
			else
			{
				prev.next = sumNode;
				prev = sumNode;
			}
			
			ptr1 = ptr1.next;
			ptr2 = ptr2.next;
		}
		// To make more general - see which one is null actually : shortens considerably!
		LinkedList rem = ptr1;
		if(ptr1 == null && ptr2 != null)
		{
			rem = ptr2;
		}
		// Check if we have a remainder of a list here too ( for ptr1 OR for ptr2 ) 
		while(rem != null)
		{
			sum = (rem.value + carry);	// Note order of eval : for both carry and the sum too. 
			if(sum >= 10)
			{
				carry = sum / 10;
				sum -= 10;
			}
			else
			{
				carry = 0;
			}
			LinkedList sumNode = new LinkedList(sum);
			prev.next = sumNode;
			prev = sumNode;
			rem = rem.next;
		}
		
		// Now check if carry is non zero here
		if(carry != 0)
		{
			LinkedList sumNode = new LinkedList(carry);
			prev.next = sumNode;
			prev = sumNode;
		}
		
   	return resultHead;
  }
}
