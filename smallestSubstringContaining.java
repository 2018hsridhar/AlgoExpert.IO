bigString = "abcd$ef$axb$c$"
smallString = "$$abf"

output: "f$axb$"
Given two strings as input
Write a func which returns smallest substring containing "smallString" containing all these characters
Continuous substring : NOT a subsequence

3 conditions
Came up with optimal solution with alacrity & conjuring of solution as well
Suggestions minor here

(a) substring can contain other characters NOT found in the small string
	See 'x' in bigString, absent in the smallString
(b) chars in substring need NOT be in the same order as they appear in "smallString"
(c) smallString can have duplicate characters : must account for character frequency

Clarifying questions : 
- characters as ASCII : [a-zA-Z0-9punctuation/special characters ]
- sz(smallString) <= sz(bigString)
- may have an empty bigString, or empty smallString
- can run into non-existence case : return "" string [ default value ] 

Strategies : two pointer, hashmap, sliding window technique ( deque )

bigString = "abcd$ef$axb$c$"
	0		1		2		3		4		5		6		7		8		9		10	11	12	13
['a','b','c','d','$','e','f','$','a','x','b','$','c','$']
			~								 		^
			l										r
			
uniqCount = 4
'$' => 2
Need bsHM
			
	
Each iteration : HM comparison ( small string's hash keys ) 
After the add/put oper to the hashmap : perform smallHM \subset largeHM
Set a length ( max running len ) as well as the indices ( left, right)
l = r = -1 ( indicate no window existed )
[0,7]
l = 1, r = 8
l = 2, r = 8
l = 2, r = 10 ( len = 8 )
Once right goes past the bigString : halt
{
	a => 	1
	b	=> 	1
	$	=>	2
	f	=>	1
}
total count = 5

if(totalCount = sz(smallString) ) : we are good to go

Can store characters appearing in small string only

count sums to 5 here
smallString = "$$abf"
bigString = "$$aaabbbbbb"
						          ^
"$$aaabbbbf"

keySet = {$,a,b,f}
	0		1		2		3		4	
['$','$','a','b','f']
{
	$	=>	2
	a	=>	1
	b	=>	1
	f	=>	1
}

#-visited unique chars ( init to 0 )


{
	$	=>	0
	a	=>	0
	b	=>	0
	f	=>	0
}

Complexity
Each element / indx hit twice
Let M := len(bigString)
Let N := len(smallString)
In practice, M >> N most cases ( best case : M = N )
Let K := sz(alphabet) ( 255 : const ) 
Linear time and linear space algo
Time = O(M*N)	
	N-size hashmap
	O(M) if alphabet size / num keys is known
	best is O(M)
Space = O(M) + O(N)
		Space = O(N)

Test Bench
(a) "", ""
	""
(b) "", "abcd"
	""
(c) "abcd", ""
	""
	^^^ none cases valid inputs
(d) "1","1"
	"1"
(e) "abcd...xyz", "abcd...xyz"
	"abcd...xyz"
(f) "abcd","efg"
	""
(g) "$$aaabbbbbb", "$$aaabbbbf"
(h) "abcd$ef$axb$c$", "$$abf" 4
  noOfVisitedChar = 

(i) "abcdefgh", "adg"
Pseudocode ::

  public static String smallestSubstringContaining(String bigString, String smallString) 
	{
	
		// construct small string hashmap
		HashMap<Character,Integer> ssHM = new HashMap<String,Integer>();
		HashMap<Character,Integer> bsHM = new HashMap<Character,Integer>();
		constructHashMaps(smallString, ssHM, bsHM);
		int[] indices = getMinimalWindow(bigString, ssHM, bsHM);
		int lIdx = indices[0];
		int rIdx = indices[1];
		if(lIdx != -1 && rIdx != -1)
		{
			return bigString.substring(lIdx, rIdx + 1);	
		}
		else
		{
	    return "";
		}
  }
			
	private static int[] getMinimalWindow(String bigString, HashMap<Character,Integer> ssHM, HashMap<Character,Integer> bsHM)
	{
		int[] indices = new int[]{-1,-1};
		char[] c_arr = bigString.toCharArray();
		int m = bigString.length;
		int leftPtr = 0;
		int rightPtr = 0;
		int minLen = 0;			// minimal dflt val
		while(rightPtr < m)
		{
			char ch = c_arr[rightPtr];
			// Add to bsHM before performing comparisons
			if(ssHM.contains(ch))
			{
				bsHM.put(ch, bsHM.get(ch) + 1);
			}
			boolean haveSlidingWindow = stringContained(ssHM, bsHM);
			while(haveSlidingWindow)
			{
				int windowLength = (rightPtr - leftPtr)	;
				if(windowLength < minLen)
				{
					indices[0] = leftPtr;
					indices[1] = rightPtr;
					minLen = windowLength;
				}
				++leftPtr;
				char prec = c_arr[leftPtr-1];
				if(ssHM.containsKey(prec))
				{
					bsHM.put(prec, bsHM.get(prec) - 1);	
				}
				haveSlidingWindow = stringContained(ssHM, bsHM);
			}
			++rightPtr;
		}
		return indices;
	}
	
	private boolean stringContained(HashMap<Character,Integer> ssHM, HashMap<Character,Integer> bsHM)
	{
		for(Map.Entry<Character,Integer> e : bsHM.entrySet())
		{
			Character key = e.getKey();
			if ( ssHM.get(key) > bsHM.get(key))
			{
				return false;
			}
		}
		return true;
	}
	
	private static void	constructSmallStringHashMap(String smallString, HashMap<Character,Integer> ssHM)
	{
		for(int i = 0; i < smallString.length(); ++i)
		{
			char ch = smallString.charAt(i);
			if(ssHM.containsKey(ch))
			{
				ssHM.put(ch,ssHM.get(ch) + 1);
			}
			else
			{
				ssHM.put(ch,1);
				bsHM.put(ch,0);
			}
		}
	}

	
	import java.util.*;

class Program {
 public static String smallestSubstringContaining(String bigString, String smallString) 
	{
	
		// construct small string hashmap
		HashMap<Character,Integer> ssHM = new HashMap<Character,Integer>();
		HashMap<Character,Integer> bsHM = new HashMap<Character,Integer>();
		constructHashMaps(smallString, ssHM, bsHM);
		int[] indices = getMinimalWindow(bigString, ssHM, bsHM);
		int lIdx = indices[0];
		int rIdx = indices[1];
		if(lIdx != -1 && rIdx != -1)
		{
			return bigString.substring(lIdx, rIdx + 1);	
		}
		else
		{
	    return "";
		}
  }
			
	private static int[] getMinimalWindow(String bigString, HashMap<Character,Integer> ssHM, HashMap<Character,Integer> bsHM)
	{
		int[] indices = new int[]{-1,-1};
		char[] c_arr = bigString.toCharArray();
		int m = bigString.length();
		int leftPtr = 0;
		int rightPtr = 0;
		int minLen = Integer.MAX_VALUE;			// minimal dflt val
		while(rightPtr < m)
		{
			char ch = c_arr[rightPtr];
			// Add to bsHM before performing comparisons
			if(ssHM.containsKey(ch))
			{
				bsHM.put(ch, bsHM.get(ch) + 1);
			}
			boolean haveSlidingWindow = stringContained(ssHM, bsHM);
			while(haveSlidingWindow)
			{
				int windowLength = (rightPtr - leftPtr)	;
				if(windowLength < minLen)
				{
					indices[0] = leftPtr;
					indices[1] = rightPtr;
					minLen = windowLength;
				}
				++leftPtr;
				char prec = c_arr[leftPtr-1];
				if(bsHM.containsKey(prec))
				{
					bsHM.put(prec, bsHM.get(prec) - 1);	
					// check during addition here
				}
				// no need to write a seperate method to check matching
				haveSlidingWindow = stringContained(ssHM, bsHM);
			}
			++rightPtr;
		}
		return indices;
	}
	
	private static boolean stringContained(HashMap<Character,Integer> ssHM, HashMap<Character,Integer> bsHM)
	{
		for(Map.Entry<Character,Integer> e : bsHM.entrySet())
		{
			Character key = e.getKey();
			if ( ssHM.get(key) > bsHM.get(key))
			{
				return false;
			}
		}
		return true;
	}
	
	private static void	constructHashMaps(String smallString, HashMap<Character,Integer> ssHM, HashMap<Character,Integer> bsHM)
	{
		for(int i = 0; i < smallString.length(); ++i)
		{
			char ch = smallString.charAt(i);
			if(ssHM.containsKey(ch))
			{
				ssHM.put(ch,ssHM.get(ch) + 1);
			}
			else
			{
				ssHM.put(ch,1);
				bsHM.put(ch,0);
			}
		}
	}
}

	
	
	
	
	






