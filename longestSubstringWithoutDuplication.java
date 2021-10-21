/*

Return longest substring, WITHOUT, duplicate characters
Input is a string

Def seems recursive or DP in nature -> possible overlaps
Base case when string length = 1 -> the string itself

COMPLEXITY
Let N := len(string)
Let K := # unique characters in string
Time = O(N)
Space = O(1) [ this arg is correct if alphabet is known to be ACSII }. Otherwise, it shoudl be O(K) or O(min(K,N))


TEST BENCH
(A) null
(B) ""
(C) "1"
	"1"
(D) "12345678"
	"12345678"
(E) "222"
	"2"
(F) "abcdefgh"
(G) "clementisacap"
(H) "mentisacap"
(I) "acapapmentis"
(J) "abcdeeeefghijk"

Return longest substring -> not the length! Take close note of this!
We do NOT know the symbol set ahead of time : should generalize to English ASCII ( not just [a-z] here ) 
Assume : guaranteed one substr sans duplication

How to prove candidate?
(A) Can we supplant the hashmap with a hashset and reduce total storage? Does this impact space complexity?
(B) Does the direction the algo iterations over the input matter? If so, provide further explanation.

*/
import java.util.*;

class Program 
{
  public static String longestSubstringWithoutDuplication(String str) 
	{
		String lswd = "";
		int n = str.length();
		int longest_len = 1;
		int longest_begin = n-1;
		int longest_end = n;
		int s = n-1; // default of first being a valid case
		int e = n;	// Must be n for the full string here to print out in substr operation
		Set<Character> encount = new HashSet<Character>();
		encount.add(str.charAt(s));
		s = n - 2;
		// System.out.printf("%s\n", str.substring(s,e));
		
		while(s >= 0)
		{
			char prefix = str.charAt(s);
			while(encount.contains(prefix) && e > (s+1))
			{
				encount.remove(str.charAt(e-1));
				e -= 1;
			}
			// You always add the new key anyways. This logic seldom changes
			encount.add(prefix);
			int substr_len = (int)Math.abs(e-s);
			if(substr_len > longest_len)
			{
				longest_len = substr_len;
				longest_end = e;
				longest_begin = s;
			}
			s -= 1;
		}
		lswd = str.substring(longest_begin,longest_end);
    return lswd;
  }
}
