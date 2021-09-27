import java.util.*;


/*
Refer to page. 51 of "Dynamic Programming for Interviews" 

*/

public class length_longest_string
{
	/*
	Check if the context is STACK or NOT
	 Note "refernecing" really means "addressing" unnearth. It is the contestation 
	 of static memory VS non-stack memory space here!
	 May be because a function deemed static, in a program, has at max one AR - Activation Record / stack frame!
	 Make a public method executed a private method here as well !
	 Belong to class -> static helps : only once instance of said method here?

	*/
	public static void main(String[] args)
	{
		// String testInput = "31421243";	// Palindromes help in this case especially!
		// String testInput = "142124";	// Palindromes help in this case especially!
		// String testInput = "9430723";	// Palindromes help in this case especially!	Breaking in this case BTW!
		String testInput = "11222288888888333333";	// If you subsitute [8] => [4], do expect a very weird test case

		int len_longest_str_sum = getLongestLenEqualSumString(testInput);
		System.out.printf("Longest string of equal strings both sides = %d\n", len_longest_str_sum);
	}

	private static int getLongestLenEqualSumString(String s)
	{
		int n = s.length();
		int maxlen = 0;					// 0 be a guaranteed minimum bound as well
		int[][] DP = new int[n][n];
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < n; ++j)
				DP[i][j] = 0;

		// Col-wise iteration : utilize j here
		for(int j = 0; j < n; ++j)
		{
			for(int i = j; i >= 0; --i)	// Go up each row, per col : use i here
			{
				if(i == j)
					DP[i][j] = Integer.parseInt(s.charAt(i) + "");
				else if ( i < j )
				{
					// System.out.printf("(i,j) = (%d,%d)\n", i, j); // (1,0)
					DP[i][j] = DP[i][j-1] + DP[i+1][j] - DP[i+1][j-1]; // potential for bug here
				}
				if(Math.abs(i-j) % 2 == 1) // is even length here [ 0,3],[1,6]
				{
					// System.out.printf("==================\n");
					// System.out.printf("(i,j) = (%d,%d)\n", i, j);
					// System.out.printf("(i,j/2) = (%d,%d)\n", i, (i+j)/2);
					// System.out.printf("(j/2,j) = (%d,%d)\n", 1+((i+j)/2), j);
					// System.out.printf("==================\n");
					if( DP[i][(i+j)/2] == DP[1+((i+j)/2)][j])
					{
						int curMaxlen = Math.abs(i-j)+1;
						maxlen = Math.max(maxlen, curMaxlen);
					}
				}
			}
		}

		// Tabulate here to BETTER denote empty spaces as well!
		// for(int i = 0; i < n; ++i)
		// {
			// for(int j = 0; j < n; ++j)
				// System.out.printf("%d\t ", DP[i][j]);
			// System.out.printf("\n");
		// }

		return maxlen; // range [0-n] here BTW

	}
}
