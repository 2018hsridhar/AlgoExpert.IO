/*

Problem title = firstNonRepeatingCharacter

1. Big plus on functional decomposition with SRP - Single Responsibility Pattern
- but do take note of exerting caution when defining too many functions in your class as well. 


2. Definitely reasoned about the algorithm quickly here as well.

What to work on
- Do not confuse index manipulation with value manipulation in problem-solving.
- do remember to clarify inputs too : can clarify with your interviewer if input is reasonably bounded in sizes ( both min and max ) 
and that they fit in memory too. Improves with practice for sure.
- Do review the "element visited at most twice" semi-formal proof here.

Write whatever you want here.

Given a string of characters in which all the characters are english-letters AND lowercase.
Return first character which is NOT repeating
String length is NOT bounded.
String len == 1 : is valid 
Unsure on empty string OR null strings -> assume empty possible : return -1


Example
"abcdcaf"

set_of_chars { a,b,c,d,f}
c and a repeat here : not {b,d,f}
	=> touch-typing : how did you tell ( typing speed )
	=> have not memorized signs : &*, ${var}, # , (expr)
	|
	'',"" -> UNIX wildcard matching 
	` : shell scripting commands : 
	~ : approximations : a ~= b vs a == b
	{ ASDF, JKL;} on the keyboard
	Thumbs on the spacebar ( both ) 
	GLOBAL local -> caps locking while coding
vi : vim text editor -> might learn special key combos over time?

	
	
Return the INDEX of the first nonRep char = b
If character DNE - return -1

Test cases
(a) "aaaaaaaaaaaaaaaaaa" -> return -1
	All constant letters
(b) "abcd...xyz" -> return 0
	All letters differ : return 0
(c) "aaaaaaaaaaaaab" : return final index of string
	n-1:1 partition
(d) "abczcba" : return 3 ( z is not-repeating here ) 
(e) "aybczcba" : return 1 ( y is not-repeating here , and the first one of this type ) 
(f)
(g)

Hashmap : [a-z] => utilize fact of 26 keys at max only : try to use an array and do an ASCII conversion here
{
	'a' - 97 => 0
	'z' - 122 => 25
}
O(1) constant space for hashmap ( since key range size is already known ) 

Computational Complexity ( of two pass solution ) 
Time = O(N) + O(N) = O(2N) => O(N)
Space = O(1)

One pass - frequency counting before running rest of problem.
Need to inspect all the elements.

aabbc.......c
"zabcdefgz" => suppose all elements unique except for first and last. First = last -> must operate over all elements from left->right


QWERTY keyboard
DVORAK keyboards
Some blind keyboards ( no letters shown here ) 
Motor skills/hand-eye coordination


lambda_calculus
category_theory ( haskell ) 
functional_programming
Big_O
asymptotic_analysis
*/

import java.util.*;

class Program_firstNonRepeatingCharacter {

  public int firstNonRepeatingCharacter(String string) 
	{
		// int resultIdx = -1;
		if(string == null || string.length() == 0)
			return -1;
		if(string.length() == 1)
			return 0;
		
		int[] letterFreqs = new int[26];
		for(int i = 0; i < string.length(); ++i)
		{
			char c = string.charAt(i);
			// Implicit casting is bad practice, but works.
			// Explicit casting preferred
			int charIdx = (int)(c - 'a'); // (int)(expression_eval)
			letterFreqs[charIdx]++;
		}
		
		for(int i = 0; i < string.length(); ++i)
		{
			char c = string.charAt(i);
			int charIdx = (int)(c - 'a');
			if(letterFreqs[charIdx] == 1)
				return i;
		}
		
    return -1;
  }
}
