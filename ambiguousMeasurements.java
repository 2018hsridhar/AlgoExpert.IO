import java.util.*;

class Program {

	/*
		THOUGHT PROCESS : 
		We do not exactly how much fluid is in each cup : just that it is bounded and inbetween
		the low ( L ) line and the high ( H ) line.
		Input is a list of measuring cups with low lines and high lines
		Give a [low,high] for target measurement too
		Can I measure that specific range ( inclusive ) using these cup?
		... hmm ...
		Now it starts seeming combinatorial/trying out assignments. Recursive approach?
		int[][] measuring Cups is an int[][2] with low and high ranges, and 0 <= L <= H 
		( yes, 0 = L = H, or 2 = L = H is possible ) 
		Always given at least one measuring cup ( no null/empty cup cases - handle in prod env ) 
		Can not pour contents of one cup unto another cup
		Once measures -> transfer to a larger bow that MAY contain the target measure too!
		Answer NOT guaranteed uniqueness either -> just return if possible here
		
		Is there a greedy strategy here too ( measure lowest amount to start of with! ) 
			-> we always have to get to that low value here as well
		Seems a possibility. Sometimes we do see greedy and recursion well paired!
		Reminds me of the coin / break change problem a bit BUT perhaps entailed with a slight modification too
		Can try half of each range too, I guess?
		Either you are exactly WITHIN the range, or equal to the range, BUT never greater than the range in either directio
		
		
		Complexity
		Time = 
		Space = 
		
		Edge Case Testing
		(a) [[200,210],[450,465],[800,850]], 2100, 2300
		(b) [[200,210]], 215, 220
		(c) [[200,210]], 190, 195
		(d) [[200,210]], 200, 200
		(e) [[200,210]], 202, 206
		(f) [[]], ___, ___
		
		A range can be considered INVALIDATED if we go over the max of the RHS here as well
		We can also aim for a recursive strategy ( if NOT a greedy strategy ) here and test the combinations?
		
		
	*/
	
	// Optimization : try to initialize a lot of 0-initialized data with a common method
	// This is a global/static initialization taking place! Hence, value seldom changes!
	// This is why those recursive wrappers OR dynamic memory matters more than you think for your access patterns
	// 0 initialized = no hit
  public boolean ambiguousMeasurements(int[][] measuringCups, int low, int high) 
	{
		int tLow = low - high;
		int tHigh = high - low;
		int[][] memo = new int[high+1][high+1];	// This is max range that can be hit anyways
		memo[0][0] = 1;	// the null cup case here
		boolean hitTargetRange = dfs(memo, measuringCups, low, high, tLow, tHigh);
		// boolean hitTargetRange = helper(measuringCups, low,high,sLow,sHigh);
		return hitTargetRange;
	 }
	
	/*
	 [cLow,cHigh] = current [low,high]
	 [tlow,tHigh] = target [ low,high ]
	 Is it head or tail recursive too? 
	 One of these children has to be either VALID or INVALID as well too
	 Due to a desire to terminate once a valid destination is reached -> consider a function call stack in place instead?
	 Iterative stacks themselves let you manipulate more of those ARs ( Activation Records ) in a recursive call stack
	 			OR leverage a global ( since a boolean expression over return types is non-trivial ) 
	 The recursion is correct BUT not the most efficient :-\. We probably need DP here!
	 
	*/
	
	// Out of resources in test case 4 here
	// memoization can run into a problem if WE dip negative though!
	public boolean dfs(int[][] memo, int[][] measureCups, int low, int high, int tLow, int tHigh)
	{
			// check the memoized cache first
			if(memo[low][high] != 0)
			{
				if(memo[low][high] == 1)
					return true;
				else
					return false;
			}
			boolean parentStatus = false;
			int n = measureCups.length;
			for(int i = 0; i < n; ++i)
			{
				int[] cup = measureCups[i];
				int newLow = low - cup[0];
				int newHigh = high - cup[1];
				// Ok so what if both 0 : now what?
				if(newLow >= 0 && newHigh >= 0)	// yes a low and a high cup can both measure 0 :-)
				{
					boolean childStatus = dfs(memo, measureCups, newLow, newHigh, tLow, tHigh);
					if(childStatus == true)
					{
						parentStatus = true;
						break;
					}
				}
				else
				{
					// check if cup is a sunrange at least
					if(low <= cup[0] && cup[1] <= high)
					{
						parentStatus = true;
						break;
					}
				}
			}
			if(parentStatus == true)
				memo[low][high] = 1;
			else
				memo[low][high] = 2;
			return parentStatus;
	}
	
	
	// Ran out of memory here ( in the recursive call stack ) 
	// How to investigate memory leaks too?
// 	public void helper(int[][] measureCups, int tLow, int tHigh, int cLow, int cHigh)
// 	{
// 		// System.out.printf("Current [low,high] = [%d,%d]\n", cLow, cHigh);
// 		// System.out.printf("target [low,high] = [%d,%d]\n", tLow, tHigh);
// 		if(hitTargetRange) // void extra recursion here
// 			return;
// 		if(cHigh > tHigh)
// 			return;			
// 		if(tLow <= cLow && cHigh <= tHigh)
// 		{
// 			hitTargetRange = true;
// 			return;
// 		}
		
// 		for(int i = 0; i < measureCups.length; ++i)
// 		{
// 			int[] cup = measureCups[i];
// 			int nextLow = cLow + cup[0];
// 			int nextHigh = cHigh + cup[1];
// 			helper(measureCups, tLow, tHigh, nextLow, nextHigh );
// 		}
// 	}
	
	
}
