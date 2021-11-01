
Halloween Sale Extension!
25%-off sale extended!

Ends Nov. 1st, at 11:59 PM PST
(11:59:46)


Buy Products

AlgoExpert

Java
C#
C++
Go
Java
JavaScript
Kotlin
Python
Swift
TypeScript

14px
12px
13px
14px
15px
16px
17px
18px

Sublime
Sublime
Emacs
Vim


Monokai
Blackboard
Cobalt
Dracula
Lucario
Midnight
Monokai
Night
Oceanic Next
Rubyblue
Solarized Dark



00:00:00 | 00:00:00

Timer
00:00:00
Minutes
45

Start
Reset
Stopwatch
00:00:00

Start
Reset

Mock Interview
heather
hari
(You)
Interview 1
Interview 2

Scratchpad
​If pattern lacks "x" or "ys" ---> 

Assume never more than one pair (s1,s2) denoting (x,y) in normal string

pattern = "xxyxxy"
string = "gogopowerrangergogopowerranger"
		  
		  	0	1	2	3	4	5
		  ['g','o','g','o','p','o']
		  
make selection @ beginning ... tells the rest basically ( exploit contiguous characters of a string property )

String.replaceAll('x',"substring") -> do later

	  _
	  _____
	  _________
	  _____________
	['g','o','g','o','p','o','w','g','o','g','o','p','o','w']
	  0   1   2	  3   4	  5   6   7   8   9  10   11  12  13
	  ^       ^                   ^       ^
 "g" shows up at 4 seperate indices

 
 go -> 0
 startIdx = stringIdx + len(substring)
 string.indexOf("go", curIndx)
 
 Get substring s2 IFF we have a gap in the found start indices as well
 0,2,7 ( 7 != 2 + 2 -> mark of discontinuity : 4 )
 2+len(go), 7 -> we have a seperate substring
 
 go -> 2
 gog -> 0
 gog -> 7
 len(gog) = 3
 4 -> 7 : we have a seperaet substring NOT equal to "gog"
 x->"gog"
 y->"gapped" ( "opow")
 
 xxyxxy => "goggogopowgoggogopow" != input string
 
Initially : 
begin	end		substring
0		1		"g"
0		2		"go"
0		3		"gog"
0		4		"gogo"


Strategies : String, Recursive/DP 

["go", "powerranger"]
x = "go"
y = "powerranger"

Input Clarification
- Empty pattern for reconstructed string -> return empty array

Complexity
Let M := len(pattern)
Let N := len(String)
Let K := # susbtrings
Time = O(N^2)
	O(N) index checks
	O(N) for contiguous growing interval candidates
Space = O(1)

TEST BENCH
---> guaranteed uniqueness of string pairs <---
(A) "xxxx"
(B) "yyyy"
(C) "xxxy"
(D) "yyyx"
(E) "xyxxyxyy"?
(F)  "xxxx", "gogogogo"
	["go",""]
(G) "yyyy", "gogogogo"
	["","go"]

Pseudocode : 

  // frequency a "prefix" is repeated
  public static String[] patternMatcher(String pattern, String str) 
  {
  		// Get the 2 seperate substrings -> then map to symbol
		StringBuilder firstStr = new StringBuilder("");
		StringBuilder secondStr = new StringBuilder("");
		char firstLetPattern = pattern.charAt(0); // may be 'x' or 'y'
  		int n = str.length;
		// Nested for loop : generate all prefixes
  		for(int i = 0; i < n; ++i)
		{
			String prefix = str.substring(0,i+1);
			int initIdex = 0;
			int len = prefix.length();
			int foundIdx = str.indexOf(prefix, initIndex);
			boolean foundGap = false;
			boolean foundTwoStrings = false;
			
			while(foundIdx != -1)
			{
				int nextStartingIndex = foundIdx + len;
				if(nextStartingIndex >= n)
				{
					break;
				}
				int followingIdx = str.indexOf(prefix, nextStartingIndex);
				if(followingIdx == -1)
				{
					// check if second string exists here!
					if(nextStaringIndex <= (n-1))
					{
						firstStr.append(prefix);
						secondStr.append(str.substring(nextStartingIndex));
						foundTwoStrings = true;
					}
					break; // can't find anymore?
				}
				else if(followingIdx != -1 && followingIdx > nextStartingIndex)
				{
					foundGap = true;
					firstStr.append(prefix);
					secondStr.append(str.substring(nextStartingIndex,followingIdx));
					foundTwoStrings = true;
					break;
				}
				else
				{
					foundIdx = nextStartingIndex;
				}
			}
			
			// Also check if foundIdx got all the way down here
			// Check whether a string was filled here
			// Always equal to prefix ( the first string)
			
			String test = pattern;
			if(foundTwoStrings == false)
			{
				// only one string ( the prefix )
				if(firstLetPattern == 'x')
				{
					test.replaceAll('x',firstStr.toString());
					test.replaceAll('y',"");
					if(test.equals(str))
					{
						return new String[] {firstStr.toString(), ""};
					}
				}
				else
				{
					test.replaceAll('y',firstStr.toString());
					test.replaceAll('x',"");
					if(test.equals(str))
					{
						return new String[] {"", firstStr.toString()};
					}
				}
			}
			else
			{
				// two strings
				if(firstLetPattern == 'x')
				{
					test.replaceAll('x',firstStr.toString());
					test.replaceAll('y',secondStr.toString());
					if(test.equals(str))
						return new String[] {firstStr.toString(), secondStr.toString()};
				}
				else
				{
					test.replaceAll('x',secondStr.toString());
					test.replaceAll('y',firstStr.toString());
					if(test.equals(str))
						return new String[] {secondStr.toString(), firstStr.toString()};
				}
			}
			return new String[] {};
		}
  }

	['g','o','g','o','p','o','w','g','o','g','o','p','o','w']
	  0   1   2	  3   4	  5   6   7   8   9  10   11  12  13
	  ^       ^                   ^       ^
	['g','o','p','o','w']
	  0   1   2	  3   4	 
	['g','o','g','o','p','o','w']
	  0   1   2	  3   4	  5	  6 
	['g','o','g','o']
	  0   1   2	  3  
	  
	  
// https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#substring(int)
String	replace(CharSequence target, CharSequence replacement)
Replaces each substring of this string that matches the literal target sequence with the specified literal replacement sequence.
boolean	equals(Object anObject)
Compares this string to the specified object.

17
​
18
pattern = "xxyxxy"
19
string = "gogopowerrangergogopowerranger"
20
      
21
        0 1 2 3 4 5
22
      ['g','o','g','o','p','o']
23
      
24
make selection @ beginning ... tells the rest basically ( exploit contiguous characters of a string property )
25
​
26
String.replaceAll('x',"substring") -> do later
27
​
28
    _
29
    _____
30
    _________
31
    _____________
32
  ['g','o','g','o','p','o','w','g','o','g','o','p','o','w']
33
    0   1   2   3   4   5   6   7   8   9  10   11  12  13
34
    ^       ^                   ^       ^
35
 "g" shows up at 4 seperate indices
36
​
37
 
38
 go -> 0
39
 startIdx = stringIdx + len(substring)
40
 string.indexOf("go", curIndx)
41
 
42
 Get substring s2 IFF we have a gap in the found start indices as well
43
 0,2,7 ( 7 != 2 + 2 -> mark of discontinuity : 4 )
44
 2+len(go), 7 -> we have a seperate substring
45
 
46
 go -> 2
47
 gog -> 0
48
 gog -> 7
49
 len(gog) = 3
50
 4 -> 7 : we have a seperaet substring NOT equal to "gog"
51
 x->"gog"
52
 y->"gapped" ( "opow")
53
 
54
 xxyxxy => "goggogopowgoggogopow" != input string
55
 
56
Initially : 
57
begin end   substring
58
0   1   "g"
59
0   2   "go"
60
0   3   "gog"
61
0   4   "gogo"
62
​
63
​
64
Strategies : String, Recursive/DP 
65
​
66
["go", "powerranger"]
67
x = "go"
68
y = "powerranger"
69
​
70
Input Clarification
71
- Empty pattern for reconstructed string -> return empty array
72
​
73
Complexity
74
Let M := len(pattern)

Tests

Quick Test

Sandbox

1
1

Your Solutions



Run Code
Solution 1
Solution 2
Solution 3
1
import java.util.*;
2
​
3
class Program 
4
{
5
  // Return in order of "x","y"
6
 public static String[] patternMatcher(String pattern, String str) 
7
  {
8
    char firstLetPattern = pattern.charAt(0); // may be 'x' or 'y'
9
    int n = str.length();
10
    // Nested for loop : generate all prefixes
11
    for(int i = 0; i < n; ++i)
12
    {
13
        // Get the 2 seperate substrings -> then map to symbol
14
      StringBuilder firstStr = new StringBuilder(""); 
15
      StringBuilder secondStr = new StringBuilder("");
16
      
17
      String prefix = str.substring(0,i+1);
18
      System.out.printf("Testing prefix = %s\n", prefix);
19
      int initIndex = 0;
20
      int len = prefix.length();
21
      int foundIdx = str.indexOf(prefix, initIndex);
22
      boolean foundGap = false;
23
      boolean foundTwoStrings = false;
24
      
25
      // Moduarlize this logic away 
26
      while(foundIdx != -1)
27
      {
28
        int nextStartingIndex = foundIdx + len;
29
        if(nextStartingIndex >= n)
30
        {
31
          break;

Custom Output

Raw Output
---------- Test Case 1 ----------
Testing prefix = g
Following idx = 2
Testing prefix = go
Following idx = 2
Following idx = 15
Testing prefix = gog
Following idx = 15
Testing prefix = gogo
Following idx = 15
Testing prefix = gogop
Following idx = 15
Testing prefix = gogopo
Following idx = 15
Testing prefix = gogopow
Following idx = 15
Testing prefix = gogopowe
Following idx = 15
Testing prefix = gogopower
Following idx = 15
Testing prefix = gogopowerr
Following idx = 15
Testing prefix = gogopowerra
Following idx = 15
Testing prefix = gogopowerran
Following idx = 15
Testing prefix = gogopowerrang
Following idx = 15
Testing prefix = gogopowerrange
Following idx = 15
Testing prefix = gogopowerranger
Following idx = 15
Found two string candidates
First str =  	 second string = 
Testing prefix = gogopowerrangerg
Following idx = -1
Testing prefix = gogopowerrangergo
Following idx = -1
Testing prefix = gogopowerrangergog
Following idx = -1
Testing prefix = gogopowerrangergogo
Following idx = -1
Testing prefix = gogopowerrangergogop
Following idx = -1
Testing prefix = gogopowerrangergogopo
Following idx = -1
Testing prefix = gogopowerrangergogopow
Following idx = -1
Testing prefix = gogopowerrangergogopowe
Following idx = -1
Testing prefix = gogopowerrangergogopower
Following idx = -1
Testing prefix = gogopowerrangergogopowerr
Following idx = -1
Testing prefix = gogopowerrangergogopowerra
Following idx = -1
Testing prefix = gogopowerrangergogopowerran
Following idx = -1
Testing prefix = gogopowerrangergogopowerrang
Following idx = -1
Testing prefix = gogopowerrangergogopowerrange
Following idx = -1
Testing prefix = gogopowerrangergogopowerranger
Found two string candidates
First str =  	 second string = 






Halloween Sale Extension!
25%-off sale extended!

Ends Nov. 1st, at 11:59 PM PST
(11:59:30)


Buy Products

AlgoExpert

Java
C#
C++
Go
Java
JavaScript
Kotlin
Python
Swift
TypeScript

14px
12px
13px
14px
15px
16px
17px
18px

Sublime
Sublime
Emacs
Vim


Monokai
Blackboard
Cobalt
Dracula
Lucario
Midnight
Monokai
Night
Oceanic Next
Rubyblue
Solarized Dark



00:00:00 | 00:00:00

Timer
00:00:00
Minutes
45

Start
Reset
Stopwatch
00:00:00

Start
Reset

Mock Interview
heather
hari
(You)
Interview 1
Interview 2

Scratchpad
If pattern lacks "x" or "ys" ---> 

Assume never more than one pair (s1,s2) denoting (x,y) in normal string

pattern = "xxyxxy"
string = "gogopowerrangergogopowerranger"
		  
		  	0	1	2	3	4	5
		  ['g','o','g','o','p','o']
		  
make selection @ beginning ... tells the rest basically ( exploit contiguous characters of a string property )

String.replaceAll('x',"substring") -> do later

	  _
	  _____
	  _________
	  _____________
	['g','o','g','o','p','o','w','g','o','g','o','p','o','w']
	  0   1   2	  3   4	  5   6   7   8   9  10   11  12  13
	  ^       ^                   ^       ^
 "g" shows up at 4 seperate indices

 
 go -> 0
 startIdx = stringIdx + len(substring)
 string.indexOf("go", curIndx)
 
 Get substring s2 IFF we have a gap in the found start indices as well
 0,2,7 ( 7 != 2 + 2 -> mark of discontinuity : 4 )
 2+len(go), 7 -> we have a seperate substring
 
 go -> 2
 gog -> 0
 gog -> 7
 len(gog) = 3
 4 -> 7 : we have a seperaet substring NOT equal to "gog"
 x->"gog"
 y->"gapped" ( "opow")
 
 xxyxxy => "goggogopowgoggogopow" != input string
 
Initially : 
begin	end		substring
0		1		"g"
0		2		"go"
0		3		"gog"
0		4		"gogo"


Strategies : String, Recursive/DP 

["go", "powerranger"]
x = "go"
y = "powerranger"

Input Clarification
- Empty pattern for reconstructed string -> return empty array

Complexity
Let M := len(pattern)
Let N := len(String)
Let K := # susbtrings
Time = O(N^2)
	O(N) index checks
	O(N) for contiguous growing interval candidates
Space = O(1)

TEST BENCH
---> guaranteed uniqueness of string pairs <---
(A) "xxxx"
(B) "yyyy"
(C) "xxxy"
(D) "yyyx"
(E) "xyxxyxyy"?
(F)  "xxxx", "gogogogo"
	["go",""]
(G) "yyyy", "gogogogo"
	["","go"]

Pseudocode : 

  // frequency a "prefix" is repeated
  public static String[] patternMatcher(String pattern, String str) 
  {
  		// Get the 2 seperate substrings -> then map to symbol
		StringBuilder firstStr = new StringBuilder("");
		StringBuilder secondStr = new StringBuilder("");
		char firstLetPattern = pattern.charAt(0); // may be 'x' or 'y'
  		int n = str.length;
		// Nested for loop : generate all prefixes
  		for(int i = 0; i < n; ++i)
		{
			String prefix = str.substring(0,i+1);
			int initIdex = 0;
			int len = prefix.length();
			int foundIdx = str.indexOf(prefix, initIndex);
			boolean foundGap = false;
			boolean foundTwoStrings = false;
			
			while(foundIdx != -1)
			{
				int nextStartingIndex = foundIdx + len;
				if(nextStartingIndex >= n)
				{
					break;
				}
				int followingIdx = str.indexOf(prefix, nextStartingIndex);
				if(followingIdx == -1)
				{
					// check if second string exists here!
					if(nextStaringIndex <= (n-1))
					{
						firstStr.append(prefix);
						secondStr.append(str.substring(nextStartingIndex));
						foundTwoStrings = true;
					}
					break; // can't find anymore?
				}
				else if(followingIdx != -1 && followingIdx > nextStartingIndex)
				{
					foundGap = true;
					firstStr.append(prefix);
					secondStr.append(str.substring(nextStartingIndex,followingIdx));
					foundTwoStrings = true;
					break;
				}
				else
				{
					foundIdx = nextStartingIndex;
				}
			}
			
			// Also check if foundIdx got all the way down here
			// Check whether a string was filled here
			// Always equal to prefix ( the first string)
			
			String test = pattern;
			if(foundTwoStrings == false)
			{
				// only one string ( the prefix )
				if(firstLetPattern == 'x')
				{
					test.replaceAll('x',firstStr.toString());
					test.replaceAll('y',"");
					if(test.equals(str))
					{
						return new String[] {firstStr.toString(), ""};
					}
				}
				else
				{
					test.replaceAll('y',firstStr.toString());
					test.replaceAll('x',"");
					if(test.equals(str))
					{
						return new String[] {"", firstStr.toString()};
					}
				}
			}
			else
			{
				// two strings
				if(firstLetPattern == 'x')
				{
					test.replaceAll('x',firstStr.toString());
					test.replaceAll('y',secondStr.toString());
					if(test.equals(str))
						return new String[] {firstStr.toString(), secondStr.toString()};
				}
				else
				{
					test.replaceAll('x',secondStr.toString());
					test.replaceAll('y',firstStr.toString());
					if(test.equals(str))
						return new String[] {secondStr.toString(), firstStr.toString()};
				}
			}
			return new String[] {};
		}
  }

	['g','o','g','o','p','o','w','g','o','g','o','p','o','w']
	  0   1   2	  3   4	  5   6   7   8   9  10   11  12  13
	  ^       ^                   ^       ^
	['g','o','p','o','w']
	  0   1   2	  3   4	 
	['g','o','g','o','p','o','w']
	  0   1   2	  3   4	  5	  6 
	['g','o','g','o']
	  0   1   2	  3  
	  
	  
// https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#substring(int)
String	replace(CharSequence target, CharSequence replacement)
Replaces each substring of this string that matches the literal target sequence with the specified literal replacement sequence.
boolean	equals(Object anObject)
Compares this string to the specified object.

17
​
18
pattern = "xxyxxy"
19
string = "gogopowerrangergogopowerranger"
20
      
21
        0 1 2 3 4 5
22
      ['g','o','g','o','p','o']
23
      
24
make selection @ beginning ... tells the rest basically ( exploit contiguous characters of a string property )
25
​
26
String.replaceAll('x',"substring") -> do later
27
​
28
    _
29
    _____
30
    _________
31
    _____________
32
  ['g','o','g','o','p','o','w','g','o','g','o','p','o','w']
33
    0   1   2   3   4   5   6   7   8   9  10   11  12  13
34
    ^       ^                   ^       ^
35
 "g" shows up at 4 seperate indices
36
​
37
 
38
 go -> 0
39
 startIdx = stringIdx + len(substring)
40
 string.indexOf("go", curIndx)
41
 
42
 Get substring s2 IFF we have a gap in the found start indices as well
43
 0,2,7 ( 7 != 2 + 2 -> mark of discontinuity : 4 )
44
 2+len(go), 7 -> we have a seperate substring
45
 
46
 go -> 2
47
 gog -> 0
48
 gog -> 7
49
 len(gog) = 3
50
 4 -> 7 : we have a seperaet substring NOT equal to "gog"
51
 x->"gog"
52
 y->"gapped" ( "opow")
53
 
54
 xxyxxy => "goggogopowgoggogopow" != input string
55
 
56
Initially : 
57
begin end   substring
58
0   1   "g"
59
0   2   "go"
60
0   3   "gog"
61
0   4   "gogo"
62
​
63
​
64
Strategies : String, Recursive/DP 
65
​
66
["go", "powerranger"]
67
x = "go"
68
y = "powerranger"
69
​
70
Input Clarification
71
- Empty pattern for reconstructed string -> return empty array
72
​
73
Complexity
74
Let M := len(pattern)

Tests

Quick Test

Sandbox

1
1

Your Solutions



Run Code
Solution 1
Solution 2
Solution 3
​import java.util.*;

class Program 
{
	// Return in order of "x","y"
 public static String[] patternMatcher(String pattern, String str) 
  {
		char firstLetPattern = pattern.charAt(0); // may be 'x' or 'y'
		int n = str.length();
		// Nested for loop : generate all prefixes
		for(int i = 0; i < n; ++i)
		{
				// Get the 2 seperate substrings -> then map to symbol
			StringBuilder firstStr = new StringBuilder("");	
			StringBuilder secondStr = new StringBuilder("");
			
			String prefix = str.substring(0,i+1);
			System.out.printf("Testing prefix = %s\n", prefix);
			int initIndex = 0;
			int len = prefix.length();
			int foundIdx = str.indexOf(prefix, initIndex);
			boolean foundGap = false;
			boolean foundTwoStrings = false;
			
			// Moduarlize this logic away 
			while(foundIdx != -1)
			{
				int nextStartingIndex = foundIdx + len;
				if(nextStartingIndex >= n)
				{
					break;
				}
				int followingIdx = str.indexOf(prefix, nextStartingIndex);
				System.out.printf("Following idx = %d\n", followingIdx);
				if(followingIdx == -1)
				{
					// check if second string exists here!
					if(nextStartingIndex <= (n-1))
					{
						firstStr.append(prefix);
						secondStr.append(str.substring(nextStartingIndex));
						foundTwoStrings = true;
					}
					break; // can't find anymore?
				}
				else if(followingIdx != -1 && followingIdx > nextStartingIndex)
				{
					foundGap = true;
					firstStr.append(prefix);
					secondStr.append(str.substring(nextStartingIndex,followingIdx));
					foundTwoStrings = true;
					break;
				}
				else
				{
					foundIdx = nextStartingIndex;
				}
			}
			
			
			// Also check if foundIdx got all the way down here
			// Check whether a string was filled here
			// Always equal to prefix ( the first string)
			
			String test = pattern;
			if(foundTwoStrings == false)
			{
				System.out.printf("Found two string candidates\n");
				System.out.printf("First str = %s \t second string = %s\n", firstStr.toString(), secondStr.toString());
				// only one string ( the prefix )
				if(firstLetPattern == 'x')
				{
					test.replaceAll("x",firstStr.toString());
					test.replaceAll("y","");
					if(test.equals(str))
					{
						return new String[] {firstStr.toString(), ""};
					}
				}
				else
				{
					test.replaceAll("y",firstStr.toString());
					test.replaceAll("x","");
					if(test.equals(str))
					{
						return new String[] {"", firstStr.toString()};
					}
				}
			}
			else
			{
				// two strings
				if(firstLetPattern == 'x')
				{
					test.replaceAll("x",firstStr.toString());
					test.replaceAll("y",secondStr.toString());
					if(test.equals(str))
						return new String[] {firstStr.toString(), secondStr.toString()};
				}
				else
				{
					test.replaceAll("x",secondStr.toString());
					test.replaceAll("y",firstStr.toString());
					if(test.equals(str))
						return new String[] {secondStr.toString(), firstStr.toString()};
				}
			}
			
		}
		return new String[] {};
	 
  }
}

76
          {
77
            return new String[] {firstStr.toString(), ""};
78
          }
79
        }
80
        else
81
        {
82
          test.replaceAll("y",firstStr.toString());
83
          test.replaceAll("x","");
84
          if(test.equals(str))
85
          {
86
            return new String[] {"", firstStr.toString()};
87
          }
88
        }
89
      }
90
      else
91
      {
92
        // two strings
93
        if(firstLetPattern == 'x')
94
        {
95
          test.replaceAll("x",firstStr.toString());
96
          test.replaceAll("y",secondStr.toString());
97
          if(test.equals(str))
98
            return new String[] {firstStr.toString(), secondStr.toString()};
99
        }
100
        else
101
        {
102
          test.replaceAll("x",secondStr.toString());
103
          test.replaceAll("y",firstStr.toString());
104
          if(test.equals(str))
105
            return new String[] {secondStr.toString(), firstStr.toString()};
106
        }
107
      }
108
      
109
    }
110
    return new String[] {};
111
   
112
  }
113
}
114
​

Custom Output

Raw Output
---------- Test Case 1 ----------
Testing prefix = g
Following idx = 2
Testing prefix = go
Following idx = 2
Following idx = 15
Testing prefix = gog
Following idx = 15
Testing prefix = gogo
Following idx = 15
Testing prefix = gogop
Following idx = 15
Testing prefix = gogopo
Following idx = 15
Testing prefix = gogopow
Following idx = 15
Testing prefix = gogopowe
Following idx = 15
Testing prefix = gogopower
Following idx = 15
Testing prefix = gogopowerr
Following idx = 15
Testing prefix = gogopowerra
Following idx = 15
Testing prefix = gogopowerran
Following idx = 15
Testing prefix = gogopowerrang
Following idx = 15
Testing prefix = gogopowerrange
Following idx = 15
Testing prefix = gogopowerranger
Following idx = 15
Found two string candidates
First str =  	 second string = 
Testing prefix = gogopowerrangerg
Following idx = -1
Testing prefix = gogopowerrangergo
Following idx = -1
Testing prefix = gogopowerrangergog
Following idx = -1
Testing prefix = gogopowerrangergogo
Following idx = -1
Testing prefix = gogopowerrangergogop
Following idx = -1
Testing prefix = gogopowerrangergogopo
Following idx = -1
Testing prefix = gogopowerrangergogopow
Following idx = -1
Testing prefix = gogopowerrangergogopowe
Following idx = -1
Testing prefix = gogopowerrangergogopower
Following idx = -1
Testing prefix = gogopowerrangergogopowerr
Following idx = -1
Testing prefix = gogopowerrangergogopowerra
Following idx = -1
Testing prefix = gogopowerrangergogopowerran
Following idx = -1
Testing prefix = gogopowerrangergogopowerrang
Following idx = -1
Testing prefix = gogopowerrangergogopowerrange
Following idx = -1
Testing prefix = gogopowerrangergogopowerranger
Found two string candidates
First str =  	 second string = 

