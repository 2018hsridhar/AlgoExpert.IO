Longest String Chain
Given a list of strings, write a function that returns the longest 
string chain that can be built from those strings.

A string chain is defined as follows: let string A be a string in the 
initial array; if removing any single character from string A yields 
a new string B that's contained in the initial array of strings, then 
strings A and B form a string chain of length 2. Similarly, if removing 
any single character from string B yields a new string C that's contained 
in the initial array of strings, then strings A, B, and C form a string 
chain of length 3.

The function should return the string chain in descending order 
(i.e., from the longest string to the shortest one). Note that string 
chains of length 1 don't exist; if the list of strings doesn't contain 
any string chain formed by two or more strings, the function should 
return an empty array.

You can assume that there will only be one longest string chain.

Sample Input
strings = ["abde", "abc", "abd", "abcde", "ade", "ae", "1abde", "abcdef"]
						0				1				2				3				4			5			6					7

"abde"->"ade"->"ae"
	4				5			-1


Sample Output
["abcdef", "abcde", "abde", "ade", "ae"]

THOUGHT PROCESS ::
Maximization problem -> Recursive,DP,Greedy
	- dictionary order sort
	- any single character from string A -> string B -> string C ( a chain ) 
	- longset to shortet string chain
	- return empty list if no chain exists
	- only one exists
	- hashmaps/hashset
	
Naively : 
	Given some string -> remove a character from each position and chek if that substring exists
	"abde"
		|-bde			X
			|-
			|-
		|-ade			Y
			|-
			|-
		|-abe			X
			|-
			|-
		|-abd			Y
			|-
			|-
	"abcde"
		|-"bcdef" X
		|-"acdef" X
		|-"abde"	Y ( already computed "abde" )  : overlapping subproblem
			( "abde", max_string_chain_len )
			
Returning actual path -> store the other string under reference
DP + DAG
# Len sort -> Bottom-UP DP Possible


PSEUDOCODE : 

	maxLen = 0
	maxIndex = -1
	Create HashSet from strings input = memo : string -> index pairing
	n = len(strings)
	lens[n]
	indexes[n] # initialize to -1 default ( no chain ) 
	for each string k in strings:
		# Splicing
		(curProblemLen,curProblemIndex) = computeStringChain(memo, kth_string)
		if curProblemLen > maxLen :
			maxLen = curProblemLen
			maxIndex = curProblemIndex
	
	List = ()
	while(maxIndex != -1):
		List.append(maxIndex)
		maxIndex = indexes[maxIndex]
	
	return List
	
	
	// Top-down Memo
	int[] computeStringChain(HashMap<String,Int> memo, String s):
		if memo.containsKey(s) :
			idx = memo.get(substr)
			ret [lens[idx],indexes[idx]]
		else :
			curProblemLen = 1
			curProblemIndex = -1
			
			bool foundChain = false
			for each index i in the string:
				substr = s[:,i-1] + s[i,:]	# inclusive
				if HM containsKey(substr):
					foundChain = true
					(subProblemLen,subProblemIndex) = computeStringChain(memo, substr)
					if subProblemLen + 1 > curProblemLen
						curProblemLen = subProblemLen
						curProblemIndex = memo.get(substr)
					
			k = memo.get(s)
			if(not foundChain)
				indexes[k] = -1
				len[k] = 1
			else
				indexes[k] = -1
				len[k] = 1
			memo.put(s,curProblemLen)
			return [len[k], indexes[k]]		


	# Reconstruct the list
	

COMPLEXITY
Let N := #-strings
Let S := len(longest string)
TIME = O(N*S)	(POLY)
SPACE = O(N) ( exp ) O(N) ( imp ) 

["abcde","bcde","cde","de"]
		-------------------->
		O(n) call stack
["de","cde","bcde","abcde"]
	O(1) call stack
	
TEST CASES
(A) ["abde", "abc", "abd", "abcde", "ade", "ae", "1abde", "abcdef"] 
	( *** provided *** ) 
(B) ["a"]
	=> empty
(C) ["a","aa"]
	=> 2
(D) ["a","aaa","aa","aaaa"]
	=> 4
(E) ["a","aaa","aa","aaaa","b","bcd","cd","ebcdf","bcdf","aebcdf"]
	=> 5
(F) []
	=> []
	



import java.util.*;

class Program {
  public static List<String> longestStringChain(List<String> strings) 
	{
		int maxLen = 1;
		int maxIndex = -1;
		HashMap<String,Integer> strInd = new HashMap<String,Integer>();
		HashMap<String,Integer> memo = new HashMap<String,Integer>();
		int n = strings.size();
		for(int i = 0; i < n; ++i)
		{
			strInd.put(strings.get(i),i);
		}
			
		int[] lens = new int[n];
		int[] indexes = new int[n];
		for(String s : strings)
		{
			// System.out.printf("For string = %s \t \n ",s);
			int[] curProblem = computeStringChain(strInd, memo, s, lens, indexes);
			int curProblemLen = curProblem[0];
			int curProblemIdx = curProblem[1];
			// System.out.printf("len,idx = (%d,%d)\n", curProblemLen, curProblemIdx);
			if(curProblemLen > maxLen)
			{
					maxLen = curProblemLen;
					maxIndex = strInd.get(s);
			}
		}
		
		List<String> stringChain = new LinkedList<String>();
		while(maxIndex != -1)
		{
			stringChain.add(strings.get(maxIndex));
			maxIndex = indexes[maxIndex];
		}
		return stringChain;
  }
	
	private static int[] computeStringChain(HashMap<String,Integer> strInd, HashMap<String,Integer> memo, String s,
																	int[] lens, int[] indexes)
	{
		if(memo.containsKey(s))
		{
			int idx = strInd.get(s);
			return new int[]{lens[idx],indexes[idx]};
		}
		else
		{
			int curProblemLen = 1;
			int curProblemIndex = -1;
			
			boolean foundChain = false;
			for(int i = 0; i < s.length(); ++i)
			{
				// Investigate this for bugs carefully
				StringBuilder sb = new StringBuilder("");
				sb.append(s.substring(0,i));
				sb.append(s.substring(i+1));
				String substr = sb.toString();
				System.out.printf("Substring = %s\n", substr);

				if(strInd.containsKey(substr))
				{
					foundChain = true;
					int[] subProblem = computeStringChain(strInd, memo, substr, lens, indexes);
					int subProblemLen = subProblem[0];
					if(subProblemLen + 1 > curProblemLen)
					{
						curProblemLen = subProblemLen + 1;
						curProblemIndex = strInd.get(substr);
					}
				}
			}
			
			int k = strInd.get(s);
			if(foundChain == false)
			{
				indexes[k] = -1;
				lens[k] = 1;
			}
			else
			{
				indexes[k] = curProblemIndex;
				lens[k] = curProblemLen;
			}
			memo.put(s,curProblemLen);
			return new int[]{lens[k], indexes[k]};		
		}
	}
}
