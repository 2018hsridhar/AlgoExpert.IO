Start by traversing the potential substring and building out a pattern table. 
This 1-dimensional array should store, for every position in the substring,
the last index at which a matching pattern has been seen;
more specifically, this index should be the ending index of a
prefix in the substring that is also a suffix at the given position.
For example, the string "abcababcd" should yield the following pattern table: 
[-1, -1, -1, 0, 1, 0, 1, 2, -1].
 0 	 1	 2	 3	4  5  6	 7  8   
"abcababc" = "abc ab abc"
"abc" is a prefix and a suffix at this substring @ position = 7


Sample Input:
string = "aefo  aefcdae  fcdaed"
                     --  ------
susbtring = "aefcdaed"

"f","ef","aef" all appear for sure

 0   1    2   3   4  5  6   7
[a,  e,   f,  c,  d, a, e,  d]
[-1, -1, -1, -1, -1, 0, 1,-1]
     <-------------------
	 start substring search at index = 2 now ( "fcdaed")
	 


 "aefoaefc" | "aefcdaef" | "aefcdaed"
substring = "aefcdaed" (len = 8)
			 ++++(look ahead)
Partitioning the substring?
	"aef","cd","aed"?

Map string elements to meaningful output?
aefcdaefcdaed
--------|
     ++++
     ========
     ** ( overlapping : DP )?

 "efcdaefc"
 ...
 "daefc"
 "aefc"


substring
Prefixes/suffixes :
"a"
"ae"
"aef"
"aefc"
"aefcd"
"aefcda"
"aefcdae"
"aefcdaed"
o(M^2) space
O(N+M), O(M) or O(N)


substring = "aefcdaed"
             -------
Two ptr strategy?

Stored a set of indices to all "a"'s, and each step, increment them collectively?
{0,  4,  9, 14 }
{1,  5,  10, 15 }
{2,  6,  11, 16 }	chuck out mismatches too ( have initials offsets as well )?

	 
	 
Sample Output:
true

Find if substring exists in string or not

COMPLEXITY
Let N := len(string)
Let M := len(substring)
Naive : O(NM) Time, O(1) space, two pointers, each contiguous subarrar



O(2*n) or O(4*n) or O(M+N)
Better : O(N) Time, O(max(M,N)) space?
Have space - consider data structures?
> arrays
> lists
> hashes
> sets
> trees
> slls
Sliding window technique

Suffix Trie


"a" => only start searches at indices = "a"
{0,  4,  9, 14 }
String = "aaaaaaaaaaaaaaaa"

TEST CASES : 
(1) 
(2)
(3)
(4)

 0. 1. 2. 3. 4. 5. 6. 7
[a, e, f, c, d, a, e, d]
[I, I, I, I, I, 0, 1, 4]

 0  1. 2. 3. 4  5. 6. 7. 8
[a, b, c, a, b, a, b, c, d]
[I, I, I, 0, 1, 0, 1, 2, I]



0123456789
aefaefaefd
         ^ ( idx = 6 )
aefaefd

                      ~
['a','e','f','a','e','f','d']
[ -1, -1, -1, 0,  1,  2,  -1]
                              ^
		3 in the substring ( 1 off )

Main string = "aefaefaefaefd"
			                   ^
					 
['a','e','f','a','e','f','a','e','f',d']
[ -1, -1, -1, 0,  1,  2,  3,  4,  5, -1],-1,-1,-1,-1,-1,-1
                                  ~
				                          ~
One contiguous nonnegative window
If -, full restart!
Rollback by one character <- see if start over possible

import java.util.*;

class Program {
  public static boolean knuthMorrisPrattAlgorithm(String string, String substring) 
  {
	  int n = string.length();
	  int m = substring.length();
		if(m > n)
		{
			return false;
		}
		int[] patternTable = constructPatternTable(substring);
		System.out.println();
	  boolean hasSubstring = false;
	  char[] s_arr = string.toCharArray();
		int i = 0;
		int j = 0;
	  while(i < n)
	  {
			if(j >= m)	// we out of range
			{
				hasSubstring = true;
				break;
			}
			char c = s_arr[i];
			if(c == substring.charAt(j))
			{
				++j;
			}
			else if ( c != substring.charAt(j))
			{
				if(j == 0)
					j = 0;	// start over again
				else
				{
					j = patternTable[j-1] + 1; // But also exert caution here as well			
					i--;
				}
			}
			++i;			
	  }
		hasSubstring = (j >= m);
	  return hasSubstring;
  }
	
  // Two ptr algorithm
  public static int[] constructPatternTable(String input)
  {
	  int m = input.length();
	  char[] c_arr = input.toCharArray();
	  int[] patternTable = new int[m];
	  int retIdx = -1;
	  int j = 0;
	  int inContigSeq = 0; // if 1, in seq, if >1, out seq
	  for(int i = 0; i < m; ++i)
	  {
			char c = c_arr[i];
			if(i > j && c_arr[i] == c_arr[j] && inContigSeq <= 1)
			{
				inContigSeq = 1;
				retIdx = j;
				j++;
			}
			else
			{
				if(inContigSeq == 1)
					inContigSeq = 2;
				retIdx = -1;
			}
			patternTable[i] = retIdx;
	  }
	  return patternTable;
  }
	
}

