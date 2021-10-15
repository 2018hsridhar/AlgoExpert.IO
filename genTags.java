/*
How many strings generatable from matching <div></div> tags?
Highly akin to the paran generation problem . E.g. ()() (()) Type of thing
Might be open to a DP approach, if we store the values before each sub-length as well here

numberOfTags is the number of pairs of <div> open and </div> close. 
No need to worry about specific order of output strings

We do not have a boudn on the number of tags here though
Could leverage a stack

Complexity
Let N := number tags
Time = O(2^n - 1) = O(2^n) 
	Can argue that O(2^(n+1)) = O(2^n * 2^1 ) = O(2^n) : can argue that plus one is just a constant multiplier anywyas
	Do we drop constants / offsets in exponention ( multiliers are known though )?
	Time = O(2^(n+1) + 2^(n) + 2^(n-1) + ... + 2^1 + 1) ... akin to a geometric series? Apply the trick of powers of two
	... Time = O(2^(n+2) - 1)
Space = O(2^(n-1)) = O(2^n * 0.5) = O(2^n)
EXP [ Time, Space ] algorithm

Case Testing
(A) 1
(B) 2
(C) 3
(D) 4 
(E) 10
(F) 100

Akin to binom coeffs ... we can't rwite too many test cases here

*/
import java.util.*;

class Program 
{

  public ArrayList<String> generateDivTags(int numberOfTags) 
	{
		// If you initialize your object before handling of NULL/0-len case : does ease up a bit 
		ArrayList<String> results = new ArrayList<String>();
		if(numberOfTags < 1)
			return results;
		int len = numberOfTags * 2;
		StringBuilder sb = new StringBuilder("<div>");
		genTags(results, numberOfTags-1, numberOfTags, sb, len);
    return results;
	}

	public void genTags(ArrayList<String> results, int numOpen, int numClose, StringBuilder sb, int len)
	{
		String curStr = sb.toString();
		if(numOpen == 0 && numClose == 0)
		{
			if(passDivAppendTest(curStr))
			{
				results.add(curStr);
				return;
			}
		}
		else
		{
			// char rightMost = sb.charAt(sb.length() - 1);
			// Need a good way to check if coming in from a </div> or a <div> here as well
			if(numOpen > 0)
			{
				StringBuilder toLeft = new StringBuilder(sb.toString());
				toLeft.append("<div>");
				genTags(results, numOpen-1, numClose, toLeft, len);
			}
			if(numClose > 0)
			{
				StringBuilder toRight = new StringBuilder(sb.toString());
				toRight.append("</div>");
				genTags(results, numOpen, numClose-1, toRight, len);
			}
		}
	}
		
		public boolean passDivAppendTest(String s)
		{
			Stack<String> stk = new Stack<String>();
			String[] arr = s.split("><");
			stk.push(arr[0]);
			int rIdx = 1;
			while(rIdx < arr.length)
			{
				if(arr[rIdx].indexOf("/") != -1)
				{
					if(stk.size() == 0)
						return false;
					String poll = stk.pop();
					if(poll.indexOf("/") != -1)
						return false;
				}
				else
				{
					stk.push(arr[rIdx]);
				}
				rIdx++;
			}
			return stk.isEmpty();
		}
	}
	

// Must check for the balancing property as well here!
