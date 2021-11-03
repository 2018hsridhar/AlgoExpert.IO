Write a func that takes in three strings -> returns a boolean if the third string is formeable by the 
interweawve of frist two strings

Merge by alternating their letters : no specific pattern

("abc","123")
"a1b2c3"
"abc123"
"123abc"
"1a2b3c"

Letters must maintain their relative ordering within the interwoven string

sample input
one = "algoexpert"
two = "your-dream-job"
three = "your-algodream-expertjob"

sample output
	- True

COMPLEXITY ( recursive ) 
Let A,B,C := lens(one,two,three)
Let D := len(one) + len(two) = len(three)
Time = (2^D) 				EXP
Space = O(D) ( call stack space ) O(1) ( explicit )


Top-down memoized strategy
Time = (AB) 				POLY
Space = O(1) ( call stack space ) O(AB) ( explicit )



CLARIFICATIONS 
- Handling of null strings | empty strings -> do not ( can write logic if desired ) 
- can have non-interwoven cases
- sz(three) VS sz(one) | sz(two) ?
- sz(three) = sz(one) + sz(two)

STRATEGIES 
- string, combinations ( recursive, backtracking ) : overlapping subproblem ( DP )

Base cases :
(one,"") 	-> one
("", two) -> two
("","")		->	""

("abc","123")
"a1" ("bc","23")
"1a" ("bc","23")

TEST BENCH
(A)  ("abc","123", "ab12")
	FALSE
(B) ("abc","123", "ab12c3")
	TRUE
(C) ("abc","1234","abc1234")
	TRUE
(D) ("1234","abc","1234abc")
	TRUE
(E) ("abc","123","abd123")
	FALSE
(F) ("algoexpert", "your-dream-job", "your-algodream-expertjob")

(G)

			['a','b','c','1','2','3','4']
				0		1		2		3		4		5		6
				A		B		A		B		A		B		A
				B		A		B		A		B		A		B
				
				a[i],a[i+1] -> {one,two} or {two,one}
				
Interwoven = alternating seq ( no pattern )
one = "abc"
two = "123"
three = "ab12c3"
"a","1" -> consider them 


one="bac"
two="1a2"

can be taken from either <one,two> is initially possible
Can not access "a" without having used "1" earliier

"ab12c3" ( 'b','1' != 'a')

one="abc"
two="b123"


one="bc"
two="b123"

one="c"
two="b123"

one="bc"
two="123"

ab123

Chop prefixes/first characters considered from "one" or "two" or both
Chopping first character from "third"

Pseudocode : 
  public static boolean interweavingStrings(String one, String two, String three) 
	{
		helper(one, two, three);
	}
	
	// Pass in an index for string three
	private static boolean helper(String one, String two, String three) 
	{
		// BASE CASES
		if(one.isEmpty && two.isEmpty && three.isEmpty))
		{
			return true;
		}
		else if(one.isEmpty && two.equals(three))
		{
					return true;
		}
		else if(two.isEmpty && one.equals(three))
		{
			return true;
		}
		
		// Recursive cases
		char first_one = one.charAt(0);
		char first_two = two.charAt(0);
		char first_three = three.charAt(0);
		String one_suffix = one.substring(1);
		String two_suffix = two.substring(1);
		String three_suffix = three.substring(1);
		if(first_one == first_three && first_two == first_three)
		{
			boolean lhs = helper(one_suffix, two, three_suffix);
			boolean rhs = helper(one, two_suffix, three_suffix);
			return (lhs || rhs);
		}
		else if ( first_one == first_three )
		{
			return helper(one_suffix, two, three_suffix);
		}
		else if ( first_two == first_three ) 
		{
			return helper(one, two_suffix, three_suffix);
		}
		
	}
	
	



														A					B													ABABAB			 AAABBB
For instance, the strings "abc" and "123" can be interwoven as "a1b2c3", as "abc123",
				AABABB
and as "ab1c23" (this list is nonexhaustive).
---> play scrabble basically <--- ( one,two = your scrabble set ) 


import java.util.*;

class Program 
{
	// No nested funcs in JAVA
	// 0 -> not filled in yet, 1-> true, 2 -> false
	static int[][] memo;
  public static boolean interweavingStrings(String one, String two, String three) 
	{
		// Slight optimization here
		int A = one.length();
		int B = two.length();
		if(A + B != three.length())
			return false;
		memo = new int[A+1][B+1];
		return memo(one, two, three);
	}
	
	// Pass in an index for string three
	// Pass in indices : strings are references : still have to be passed in as well
	// But we get length information
	// In memoized, it is top-down : unlike bottom-up DP, we do not fill in for the base cases as much
	private static boolean memo(String one, String two, String three) 
	{
		// First check the memoized cache if it has value
		int A = one.length();
		int B = two.length();
		if(memo[A][B] != 0)
		{
			return(memo[A][B] == 1);
		}
		// Else, go compute it
		// BASE CASES
		boolean status = false;
		if(one.isEmpty() && two.isEmpty() && three.isEmpty())
		{
			status = true;
		}
		else if(one.isEmpty())
		{
			status = two.equals(three);
		}
		else if(two.isEmpty())
		{
			status = one.equals(three);
		}
		else
		{
			// Recursive cases
			// possibility for bug here
			char first_one = one.charAt(0);
			char first_two = two.charAt(0);
			char first_three = three.charAt(0);
			String one_suffix = one.substring(1);
			String two_suffix = two.substring(1);
			String three_suffix = three.substring(1);
			if(first_one == first_three && first_two == first_three)
			{
				boolean lhs = memo(one_suffix, two, three_suffix);
				boolean rhs = memo(one, two_suffix, three_suffix);
				status = (lhs || rhs);
			}
			else if ( first_one == first_three )
			{
				status =  memo(one_suffix, two, three_suffix);
			}
			else if ( first_two == first_three ) 
			{
				status = memo(one, two_suffix, three_suffix);
			}
			else
			{
				status = false;
			}
		}
		
		if(status == true)
		{
			memo[A][B] = 1;
		}
		else
		{
			memo[A][B] = 2;
		}
		return status;
		
	}
}

