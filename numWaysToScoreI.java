
/*
Notice how we can now go all the way to say, 1000 ( instead of being restricted to 40 ) due to 
decreasing our algorithm's asymptotic time complexity from EXP to POLY time ( specifically, Quadratic runtime ) 
*/
public class numWaysToScoreI
{
	public static void main(String[] args)
	{
		int i = 13;
		System.out.printf("Executing Code for Number of Ways to Score I\n");
		int numWays = solveBottomUpDP(i);
		System.out.printf("Num ways to solve for [%d] = %d\n", i, numWays);
	}

	private static int numWaysToScore(int i)
	{
		if(i < 0) return 0;
		if ( i == 0) return 1;
		return numWaysToScore(i-10) + numWaysToScore(i-5) + numWaysToScore(i-3);
	}

	/*
	Bottom-up DP approach using tabulation
	Denote N := the "ith" value

	Time = O(N)
	Space = O(N)

	Base cases from bottom are very easy
	{0,1,2} -> num ways to score is 0, by default

	Common mistake with bottom-up DP approach
		1. Forgetting the offsets with the sequence elements ( such as j-3, j-5, and so on ... ) 
		2. Failure to account for increment by one in some of these cases, with each possible sequence too

	Total number of unique ways for sure
	Say for score = 13 : can be
		{10,3}
		{3,10}
		{5,5,3}
		{5,3,5}
		{3,5,5}
	*/
	private static int solveBottomUpDP(int i)
	{
		if(i < 0 ) return 1;
		if(i < 3) return 1;
		int[] memo = new int[i+1];


		// [1] Fill up the base cases 
		memo[0] = 1;
		memo[1] = 0;
		memo[2] = 0;


		for(int j = 3; j <= i; ++j)
		{
			memo[j] += memo[j-3];
			if(j-5 >= 0)
				memo[j] += memo[j-5];
			if(j - 10 >= 0)
				memo[j] += memo[j-10];
			System.out.printf("j = %d \t memo[j] = %d\n", j, memo[j]);
		}

		return memo[i];
	}
}
