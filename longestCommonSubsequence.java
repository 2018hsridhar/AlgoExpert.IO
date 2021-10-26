str1 = "ZXVVYZW"
str2 = "XKYKZPW"
-> "X","Y","Z","W"
LCS(str1,str2) = "XYZW"

Find the list of characters here

Strategies : Brute-force recursion, DP ( bottom-up or top-down memo ) 
Base cases : str1 = single_char OR str2 = "single_char" or both. 
	str1 = single_char : check that str2.contains(str1);
	if  str2 = "single_char" : check that str1.contains(str2)
	Or both single chars -> check they are equal here
	Either being empty -> return ""

Reduce the input size till algo converges to base cases
Equal or unequal lengths as well

str1 = "ZXVVYZW"
		    "XVVYZW"
				
str2 = "XKYKZPW"			
			  "KYKZPW"

str1 = ['A','B','C','D']
str2 = ['E','F','G','H']
				 0	 1	 2	 3
				 
str1.substring(0,4) = "ABCD"
str1.substring(1,4) = "BCD"
str1.substring(2,4) = "CD"
str1.substring(3,4) = "D"
str1.substring(4,4) = ""
				 
PSEUDOCODE : 



String longestCommonSubsequence(String str1, String str2)
{
	// Hit the cache
	int r = m - str1.length();
	int c = n - str2.length();
	if(memo[r][c] != NULL)
	{
		return memo[r][c];
	}
	// Let us handle base case as well
	else if(r == m || c == n)
	{
		memo[r][c] = "";
	}
	else
	{
		// Recurse
		StringBuilder sb = new StringBuilder("");
		if(str1.charAt(0) == str2.charAt(0))
		{
			sb.append(c);
			String remaining = longestCommonSubsequence(str1.substring(1), str2.substring(1));
			sb.append(remaining);
		}
		else
		{
			sb.append(c);
			String leftChild = longestCommonSubsequence(str1.substring(1), str2);
			String rightChild = longestCommonSubsequence(str1, str2.substring(1));
			if(leftChild.length() > rightChild.length())
				sb.append(leftChild)
			else
				sb.append(rightChild);
		}
		memo[r][c] = sb.toString():
	}
	return memo[r][c];
	
}



Recursive :
Let M := len(str1)
Let N := len(str2)
Let K := MIN(M,N)
Let L := MAX(M,N)
Time = EXP = O(2^L)
	 top-down memoization
Space = O(L) ( implicit func call stk ) explicit O(L^2)

DP Complexity : 
Let M := len(str1)
Let N := len(str2)
HashMap<K,V> store or a 2D matrix
Time = POLY = O(MN)
Space = O(1) ( implicit ) O(MNL) explicit


TEST BENCH
(A) ["ZXVVYZW", "XKYKZPW" ]
(B) ["ABCD","EFGH"]
(C) ["ABCD","ABCD"]
(D) ["ABCD", "CDAB"]
(E) ["A","BAD"] or ["A", "BCD"]
(F) ["A", "C"] or ["A", "A"]
(G) ["NQEVPAISNCIUEUWEFTROA", "ZHEIQMXNCSFGWEBVTEPROFKL" ]
(H) ["A","BBBBBBBBBBBBBA"]




import java.util.*;

class Program 
{
  	public static List<Character> longestCommonSubsequence(String str1, String str2) 
		{
			String result = bottomUp(str1,str2);
			List<Character> lcs = new LinkedList<Character>();
			for(int i = 0; i < result.length(); ++i)
				lcs.add(result.charAt(i));
			return lcs;
  	}
	
		private static String bottomUp(String str1, String str2)
		{
				// Hit the cache
				int m = str1.length();
				int n = str2.length();
				String[][] memo = new String[m+1][n+1];
				for(int i = 0; i <= m; ++i)
					memo[i][n] = "";
				for(int j = 0; j <= n; ++j)
					memo[m][j] = "";
			
				for(int i = m-1; i >= 0; --i)
				{
					for(int j = n-1; j >= 0; --j)
					{
						StringBuilder sb = new StringBuilder("");
						if(str1.charAt(i) == str2.charAt(j))
						{
							sb.append(str1.charAt(i));
							sb.append(memo[i+1][j+1]);
						}
						else
						{
								String leftChild = memo[i+1][j];
								String rightChild = memo[i][j+1];
								if(leftChild.length() > rightChild.length())
								{
									sb.append(leftChild);
								}
								else
								{
									sb.append(rightChild);
								}
						}
						memo[i][j] = sb.toString();
					}
				}
			
			return memo[0][0];
	}
}
