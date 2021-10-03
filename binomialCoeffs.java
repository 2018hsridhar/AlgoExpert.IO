

/*
Complexity
Time = ???
Space = O(depth(n-1))
*/
public class binomialCoeffs
{
	public static void main(String[] args)
	{
		int maxLevel = 40;
		System.out.printf("Executing Code for Binomial Coefficients\n");
		solveRecursively(maxLevel);
		System.out.printf("Wrapped up execution of binomial coefficients code\n");
	}


	/*
	Bottom-up DP approach
	
	*/

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
