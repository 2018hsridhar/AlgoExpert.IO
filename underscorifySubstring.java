/*

Inputs = main string, a possible substring ( may not actuall be ) of the main string
Split up the main string by spaces as well
Substr may be < or > in len compared to the main string

Complexity
Let M := #-words in the string / array formed from string
Let N := #_chars(max_len(word))
Let K := # unique words in string
Let L := # unique symbols in string

Time = O(MN)
Space = O(N)?

TEST BENCH : 
(A) "this is not a test", "alex"
	  "this is not a test"
(B) "aol", "aol"
	"_aol_"
(C) "alpha", "alp"
	"_alp_ha"
(D) "alpha", "lph",
	"a_lph_a"
(E) "alpha", "pha"
	"al_pha_"
(F) "", "abc" OR "", ""
	""
(G) "tettabttto", "t"
	"_t_e_tt_ab_ttt_o"
(G) "tetabto", "t"
	"_t_e_t_ab_t_o"
(H) "testthis is a testtest to see if testestest it works", "test"
(I) "testtesttest", "test"
(J) "testesttest", "test"
(K) "testestest", "test"
(L) "alpha", "alphabet"

Not an "in-place" modification either
Can read string like a char[] array ( char-by-char ) 

https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#indexOf(int)


*/

import java.util.*;

class Program 
{
  public static String underscorifySubstring(String str, String substring) 
	{
		if(str.equals(""))
			return str;
		else if(substring.equals(""))
		{
			StringBuilder sb = new StringBuilder();
			sb.append("_");
			sb.append(str);
			sb.append("_");
			return sb.toString();
		}
		String[] tokens = str.split("\\s+");
		int n = tokens.length;
		String[] results = new String[n];
		for(int i = 0; i < n; ++i)
		{
			results[i] = underscore(tokens[i], substring);
		}
		String res = String.join(" ", results);
		return res;
	}
	
	public static String underscore(String str, String substring)
	{
   
		// Perform a test here
		int m = substring.length();
		StringBuilder sb = new StringBuilder("");
		int fromIndex = 0;
		Set<Integer> encount = new HashSet<Integer>();
		int sHit = str.indexOf(substring, fromIndex);
		encount.add(sHit);
		while(sHit != -1)
		{
			encount.add(sHit);
			sHit += substring.length() - 1; // do add here
			if(substring.length() == 1)
			{
				sHit += 1;
			}
			sHit = str.indexOf(substring, sHit);
		}
		
		// Perform look over what we have here and test if overlap OR if side-by-side
		char[] c_arr = str.toCharArray();
		int s_idx = 0;
		int e_idx = 0;
		/*
			len = 4 : s_idx + len - 1 = (0,3). Be careful though for the single letter case!
			len = 1 : s_idx + len - 1 = (0,0). Back to square one here cuz no overlap
		*/
		while(s_idx < c_arr.length)	
		{
			if(encount.contains(s_idx))
			{
				e_idx = s_idx + m; // assuming a default value here
				int tmp = s_idx;
				if(m != 1)
				{
					while(encount.contains(e_idx - 1) || encount.contains(e_idx))
					{
						while (str.substring(tmp,tmp+m).equals(substring) && str.substring(tmp+1,tmp+1+m).equals(substring))
						{
							++tmp;
							e_idx++;
						}
						if(encount.contains(e_idx-1))
						{
							e_idx = e_idx + m - 1;
						}
						else if ( encount.contains(e_idx))
						{
							e_idx = e_idx + m;
						}
					}
				}
				else
				{
					while(encount.contains(e_idx))
					{
						e_idx = e_idx + 1;
					}					
				}
				
				// Assume we have end_idx and start_idx here
				String subLet = str.substring(s_idx, e_idx);
				sb.append("_");
				sb.append(subLet);
				sb.append("_");
				s_idx = e_idx;
			}
			else
			{
				sb.append(c_arr[s_idx]);
				++s_idx;
				++e_idx;
			}
		}
		
		String underScored = sb.toString();
    return underScored;
  }
}
