Two inputs
A # defined as a PI
a list of String[] containing numbers of ANY length

Goal : find smallest number of SPACES that we can add to "pi"
using the list of numbers until we reach the number pi
Coins change problem ( minimization - DP ) 

example:
pi = "3141592653589793238462643383279",
numbers = ["314159265358979323846", "26433", "8", "3279", "314159265", "35897932384626433832", "79"]

314159265358979323846 | 26433 | 8 | 3279
Iterate over the list of numbers to compose PI -> 3 spaces ( usd 4 elements )

Clarification
- can have multiple valid answers : get the minimum
- Composition may not work with all given inputs
- If no valid answer : return "-1"
- Can use an element multiple times 


Problem solving strategies : greedy, recursive ( backtracking ) -> how to break into smaller subproblems ( remaining non-prefixed strings )

pi = "3141592653589793238462643383279",
      ^
			0
			
["314159265358979323846", "314159265", "26433", "8", "3279", "35897932384626433832", "79"]

eval(314159265358979323846)
		314159265 | eval(53589793238462643383279)
		314159265358979323846 | eval(2643383279)
		eval(2643383279)
			<-
			26433 | eval(83279)
					8 | eval(3279)
						3279 | eval("")
			
Base case / Terminating case : String is empty OR one single element here itself equals the string
Len(Element) > len(string) : do not try that element 
First chars unequal : do not try


find index of substrings -> if the index = 0: 


str = ['3','1','4','5,'9'] elements = {"314","59"}
				0		1		2		3	 4
	
	"314" is a substring of given str : foundIdx = 0, len = 3
	(0,3) : remove this prefix OR generate the new string from index 3 itself
	str.substring(3) = 
	['5','9']
		3		4
	['5','9']
		0		1
	
Brute force complexity
Let K := #-elements in input
Let N := #-characters in PI
Time = (K^N)		EXP
Space = O(1) explicit O(N) call stack space

DP Complexity
Time = (K*N)		POLY
Space = O(N) explicit for cache
				O(1) implicit call stack space


TEST BENCH 
(A) pi="314"
		numbers=["31","42","4"]
(B) pi="31415"
		numbers=["31","3141","5"]
(C) pi="31415926"
		numbers=["314","15","92","926"]
(D) pi = "31456"
	numbers=["3","1","4","5","6"]
(E) pi="83279"
	numbers=["23"]
		
TD memoization ( cut @ prefixes : O(N) storage )
	From indices i \in {0,...,n-1}, min-ways-form substring(i,n) is stored in memo[i];
BuDP : start all the way at the end instead

example:
pi = "83279"
numbers = ["314159265358979323846", "26433", "8", "3279", "314159265", "35897932384626433832", "79"]
		DP = [2,1,-1,1,-1]
					0	1	2	 3	4
				 [8,3,2,7,9]	
					
https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#substring(int)

Pseudocode : 

// Prefixes and suffixes
public static int numbersInPi(String pi, String[] numbers) 
{
	int numbersFound = -1;
	int n = pi.length();
	int[] DP = new int[n];
	for(int i = n-1; i >= 0; --i)
	{
		String substr = pi.substring(i,n);
		for(String prefix : numbers)
		{
			// check if a valid prefix here or a perfect match
			DP[i] = Integer.MAX_VALUE; // no valid substrings found
			if(substr.equals(prefix))
			{
				DP[i] = 1;
			}
			else
			{
				boolean foundValidPrefix = false;
				if(substr.indexOf(prefix) == 0)
				{
					int offset = element.length();
					int suffixIdx = i + offset;
					if(suffixIdx < n)
					{
						if(DP[suffixIdx] != -1)
						{
							DP[i] = Math.min(DP[i], DP[suffixIdx] + 1);
							foundValidPrefix = true;
						}
					}
				}
				
				if(foundValidPrefix == false)
				{
					DP[i] = -1;
				}
			}
		}
	}
	numbersFound = DP[0];
	return numbersFound;
}


import java.util.*;

class Program 
{
	public static int numbersInPi(String pi, String[] numbers) 
	{
		int numbersFound = -1;
		int n = pi.length();
		// O(N) alloc space
		int[] DP = new int[n];
		for(int i = 0; i < n; ++i)
			DP[i] = Integer.MAX_VALUE;
		// O(N) outer loop
		for(int i = n-1; i >= 0; --i)
		{
			String substr = pi.substring(i,n);
			boolean foundValidPrefix = false;
			// O(K) inner loop
			for(String prefix : numbers)
			{
				// check if a valid prefix here or a perfect match
				if(substr.equals(prefix))
				{
					DP[i] = 1;
					foundValidPrefix = true;
				}
				else
				{
					if(substr.indexOf(prefix) == 0)
					{
						int offset = prefix.length();
						int suffixIdx = i + offset;
						if(suffixIdx < n)
						{
							if(DP[suffixIdx] != -1)
							{
								DP[i] = Math.min(DP[i], DP[suffixIdx] + 1);
								foundValidPrefix = true;
							}
						}
					}
				}
			}
			if(foundValidPrefix == false)
			{
				DP[i] = -1;
			}
		}
		if(DP[0] == -1)
			return -1;
		numbersFound = DP[0] - 1; // number of spaces ( not number elements under composition )
		return numbersFound;
	}
}
