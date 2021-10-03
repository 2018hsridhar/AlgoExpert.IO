
/*
Notice how we can now go all the way to say, 1000 ( instead of being restricted to 40 ) due to 
decreasing our algorithm's asymptotic time complexity from EXP to POLY time ( specifically, Quadratic runtime ) 
*/
public class binomialCoeffs
{
	public static void main(String[] args)
	{
		int maxLevel = 1000;
		System.out.printf("Executing Code for Binomial Coefficients\n");
		// solveRecursively(maxLevel);
		solveBottomUpDP(maxLevel);
		System.out.printf("Wrapped up execution of binomial coefficients code\n");
	}

	/*
	Bottom-up DP approach
	Denote M := the max level, we have the following

	Time = O(M^2)
	Space = O(M^2) in the 2D matrix

	*/
	private static void solveBottomUpDP(int maxLevel)
	{

		maxLevel = Math.max(2,maxLevel+1); // account for case where maxLevel = 0 OR maxLevel = 1 as well!
		long[][] memo = new long[maxLevel + 1][maxLevel + 1];

		// [1] Fill up the base cases for 0th and 1st levels
		memo[0][0] = 1;
		memo[1][0] = 1;
		memo[1][1] = 1;

		// [2] Fill in the diagonal matrix row,cols as well as the left most column
		for(int i = 0; i < maxLevel + 1; ++i)
			memo[i][0] = 1;
		for(int i = 0; i < maxLevel + 1; ++i)
			memo[i][i] = 1;

		// [3] Fill in the interior nodes of the matrix
		for(int level = 2; level < maxLevel + 1; ++level) // but start at level = 2 here
		{
			for(int j = 1; j < maxLevel + 1; ++j)
			{
				memo[level][j] = memo[level-1][j] + memo[level-1][j-1];
			}
		}

		// [4] Print out the results of computation here
		for(int level = 0; level <= maxLevel ; level++)
		{
			String space = " ";
			String spaces = space.repeat(maxLevel - level);
			StringBuilder sb = new StringBuilder("");
			for(int j = 0; j <= level; j++)
			{
				sb.append(memo[level][j]);
				sb.append(" ");					
			}
			String levelString = sb.toString().trim();
			StringBuilder levelSentence = new StringBuilder("");
			levelSentence.append(spaces);
			levelSentence.append(levelString);
			levelSentence.append(spaces);
			System.out.printf("%s\n", levelSentence.toString());
			// Trim the space at the final string output here as well too
		}
	}

	/*
	Highly recursive
	Will execute prints too
	Denote {source, dest, extra} nodes here as well too
	Denote number of elements to move as well ( but do HALT at case of 1 node ) 
	Just pass in the Strings themselves anyways
	*/
	// Consistency with incr/decr operators does MATTER a lot as well too!

	private static void solveRecursively(int maxLevel)
	{
		for(int level = 0; level <= maxLevel ; level++)
		{
			String space = " ";
			String spaces = space.repeat(maxLevel - level);
			StringBuilder sb = new StringBuilder("");
			for(int j = 0; j <= level; j++)
			{
				int curComb = solveForComb(level,j);
				sb.append(curComb);
				sb.append(" ");					
			}
			String levelString = sb.toString().trim();
			StringBuilder levelSentence = new StringBuilder("");
			levelSentence.append(spaces);
			levelSentence.append(levelString);
			levelSentence.append(spaces);
			System.out.printf("%s\n", levelSentence.toString());
			// Trim the space at the final string output here as well too
		}
	}

	// Exception in thread "main" java.lang.StackOverflowError
	// Recursive, EXP time solution
	private static int solveForComb(int n, int m)
	{
		// System.out.printf("(m,n) = (%d,%d)\n", n, m);
		// [1] The Base Cases
		if(m < 0 || n < 0)
			return 0;
		if(m == 0 && n == 0)
			return 1;
		if(m == n)
			return 1;
		if((m == 1 && n == 0) || (m == 1 && n == 1))
			return 1;

		// [2] The Recursive Cases
		int comb = solveForComb(n-1,m) + solveForComb(n-1,m-1);
		return comb;
	}

}
