/*
Goal : return longest palindromic substring
Exploit the contiguous property of arrays

Strategies : recursion, backtracking, DP
Base case : a single-character string
Always guaranteed at least one longest valid solution
You solved the problem of obtaining lengths earlier

Bottom-up DP Complexity
Let N := len(str)
Time = O(N^2)
Space = O(1) (implicit) O(N^2) (explicit)

TEST BENCH : 
- Do handle null/empty string inputs ( but do not worry much ) 
(A) abccba
(B) a
(C) ab
(D) abxz
(E) abccbaxedqsa
(F) xawefbqzaaaa
(G) aaaaaaaa

*/
import java.util.*;

class Program {
  public static String longestPalindromicSubstring(String str) 
	{
		String lps = "";
		if(str == null || str.equals(""))
			return null;
		int n = str.length();
		String[][] DP = new String[n][n];
		for(int i = 0; i < n; ++i)
		{
			DP[i][i] = str.substring(i,i+1);
		}
		for(int i = 0; i < n-1; ++i)
		{
			if(str.charAt(i) == str.charAt(i+1))
			{
				DP[i][i+1] = str.substring(i,i+2);
			}
			else
			{
				DP[i][i+1] = str.substring(i,i+1);				
			}
		}
		
		// Fill from the left -> then from the bottom of each col -> for this type of DP approach
		// Nested structure : j for colwise -> --i for rowwise ops
		for(int j = 1; j < n; ++j)
		{
			for(int i = j-2; i >= 0; --i)
			{
				String leftCharOut = DP[i][j-1];
				String rightCharOut = DP[i+1][j];
				if(leftCharOut.length() > rightCharOut.length())
					DP[i][j] = leftCharOut;
				else
					DP[i][j] = rightCharOut;
				String bothCharsOut = DP[i+1][j-1];
				int lenBothOut = (j-1)-(i+1)+1;
				if(bothCharsOut.length() == lenBothOut && str.charAt(i) == str.charAt(j))
				{
					StringBuilder sb = new StringBuilder("");
					sb.append(str.charAt(i));
					sb.append(bothCharsOut);
					sb.append(str.charAt(j));
					DP[i][j] = sb.toString();
				}
			}
		}
		
		// A quick print debug
		// for(int i = 0; i < n; ++i)
		// {
		// 	for(int j = 0; j < n; ++j)
		// 	{
		// 		System.out.printf("%s\t", DP[i][j]);
		// 	}
		// 	System.out.printf("\n");
		// }
		lps = DP[0][n-1];
		return lps;
  }
}
