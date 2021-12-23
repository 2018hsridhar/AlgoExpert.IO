/*

Clarifications
- String is a non-empty input
- Assume String composed of only English ASCII characters as well

Return MIN # cuts needed to perform on string s.t. each substring ( after cut ) be a palindrome
- optimization problem
- combinatorial ( make different choices W.R.T. the cuts ) 
- Strategies : Dp and Recursion

Do we need algo for checking if a string is a palindrome as well?
Single character at end guaranteed the isPalindromic property
Subproblem size : denote as <k>, the length of the string suffix ( after cutting away some 
number of letters composing our prefix ) 
Sliding window technique?
Prefix in the cut still must respect palindrome property

"nnnnnn" as a 'supposed' prefix
Length = 6
But "nnnnn", len = 5, is a palindrome
So too, "nnnn" ( len = 4 ) AND "nnn" ( len = 3 )
So sliding window deletes only last character @ a time

COMPLEXITY
Let N := #-characters in the input string
Aiming for : O(N^2) time complexity
TIME = O(N^2*K) = O(N^3) ( POLY ) 
SPACE = O(N)

TEST CASES
(A) "a"
	=> 0
(B) "ab"
	=> 1
(C) "aa"
	=> 0
(D) "aabbbb"
(E) "daad"
	=> cut = 0 ( naturally palindromic )  
(F) "aad"
	=> cut = 1 ( not a natural palindrome -> but smaller subproblem )
(G) "abcdefghijkl"
	-> cut each character : de facto -> every character stand alone is a palindrome
(H) "nonnonabbad"
	=> "non|non|(abbad)"
		<- to get here, begetted solving "non|(nonabbad)" as well!
	=> "nonnon|(abbad)"
		We have an overlapping subproblem ( returning a count )
		But we MUST chop off from the prefix here
		And must ASSERT that said prefixes engender valid palindromes
(I) "abccba"
	=> 0
(J) ""
(K) null

There may exist room for premature optimization as well?




*/
import java.util.*;

class Program 
{
  public static int palindromePartitioningMinCuts(String str) 
	{
		// String is non-empty : thus, base_addr never points to null, but we can check
		int minCuts = Integer.MAX_VALUE;
		if(str == null || str.length() == 0)
		{
			return 0;
		}
		
		// https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
		// Bottom-up DP here ( good news -> suffix not needed here : subproblems computed already ! ) 
		int n = str.length();
		int[] numCuts = new int[n];
		numCuts[n-1] = 0;	// default ( single letter ) 
		// Generate all possible prefixes from (index = i) here
		for(int i = n-2; i >= 0; --i)
		{
			int subprobMinCuts = Integer.MAX_VALUE; 
			for(int j = i+1; j <= n; ++j)	// cuz of .substring() method
			{
				int startIdx = i;
				int endIdx = j;
				String prefix = str.substring(startIdx, endIdx);
				if(isPalindrome(prefix) == true)
				{
					// System.out.printf("Prefix = %s is a plaindrome\n", prefix);
					int curMinCut = 0;	// set this to 0 ( not 1 ) 
					if(j < n) // when j = 1, we do not consider it
					{
						curMinCut += (1 + numCuts[j]);	// Getting you 0 when "aa" is a palin, and so too, "bbbb"
					}
					if(curMinCut < subprobMinCuts)
					{
						subprobMinCuts = curMinCut;
					}
				}
			}
			numCuts[i] = subprobMinCuts;
		}
		minCuts = numCuts[0]; 	// guaranteed to start here
    return minCuts;
  }
	
	// Optimize by passing indices instead?
	private static boolean isPalindrome(String s)
	{
		int ptr1 = 0;
		int ptr2 = s.length() - 1;
		while(ptr1 <= ptr2)
		{
			if(s.charAt(ptr1) != s.charAt(ptr2))
			{
				return false;
			}
			else
			{
				++ptr1;
				--ptr2;
			}
		}
		return true;
	}
	
}
