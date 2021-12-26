/*
String is composed only of parantheses : ()
Return an int denoting length of longest BALANCED substring W.R.T. the parantheses

Can consider a stack based approach : seems typical

Naive Solution
We would just use a stack at each index, and see how far we can go
We need the contiguous property as well in each location
Can we solve subproblems too?
O(N^2) Time, O(N) Space
	~ clear the stack at each iteration here

Optimal Complexity for Bottom-Up Dynamic Programming : 
Let N := length of the string
Time = O(N)
Space = O(N)

Balanced if {#_open = #_closed} : and no other substring here as well
They want to trip you on an empty stack case BTW
We still desire mapping to the balanced open-close bracket problem using a stack underneath

TESTS : 
(A) "((((((((((("
	=> 0
	=> caused a bug : investigate again
	=> is a gotcha case : you theoretically must evaluate to the end here -> but you keep adding zero
	=> we need a stopper instead!
(B) ")))))))))))"
	=> 0
(C) "((((()))))"
	=> 5
(D) "()(())((()))"
	=> 12
(E) "(()))("
	=> 4
(F) "(()))(())"
	=> 4
(G) "()()()()()()()()()()"
	=> 20
(H) "(((((((((((()"
	=> buggy as well
(I) "(((()"
	=> breaks
(J) "))()()))())(()(()"
	=> also breaking
(K) ")))))))))))((((((((("

Todo -> add all elements onto the stack, or add as we go type of reasoning instead?
Need to maintain a counter as well

Apparentely there exists a O(N) time, O(1) space solution

*/

import java.util.*;

class Program {
  public int longestBalancedSubstring(String string) 
	{
		if(string == null || string.length() == 0)
		{
			return 0;
		}
		
		int lenLongest = 0;
		int n = string.length();
		int[] DP = new int[n]; // zero initialized
		DP[n-1] = 0;
		for(int i = n-2; i >= 0; --i)
		{
			char ch = string.charAt(i);
			if(ch == '(')
			{
				// System.out.printf("Here at '(' case\n");
				int subStrLen = 0;
				int nxt = i + 1;
				char follow = string.charAt(nxt);
				// but this can be a while loop -> exert caution as well
				boolean brokeRight = false;
				while(nxt < n)
				{
					if(string.charAt(nxt) == '(')
					{
						int contigLen = DP[nxt]; // adding by length will still work out here
						if(contigLen == 0)	// the stopper case : can NOT go further 
						{
							break;
						}
						nxt += contigLen; 
						subStrLen += contigLen;
					}
					else if ( string.charAt(nxt) == ')') // exploit the contiguous property
					{
						brokeRight = true;
						subStrLen += 2;	// always add this first
						if(nxt + 1 < n)
						{
							subStrLen += DP[nxt+1];	// whatever the heck this eqausl here
						}	
						break;	// early terminate here
					}
				}
				// Make sure to check that nxt is referenceable, if we still have a '(' dangling!
				DP[i] = subStrLen;
				if(brokeRight == false)
				{
					// System.out.printf("At index = [%d]\tbrokeRight = %s\n", i, brokeRight);
					subStrLen = 0;
					DP[i] = 0;
				}
				if(subStrLen > lenLongest)
				{
					lenLongest = subStrLen;
				}
			}
			else if ( ch == ')')	// we can not start a string from here anyways!
			{
				DP[i] = 0;
			}
		}
		return lenLongest;
  }
}
