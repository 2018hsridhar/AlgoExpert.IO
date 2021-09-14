import java.util.*;

/*

longestPeak.java : under AlgoExpert.IO 

Input is an array of integers : let us assume it fits in memory
and is not a NULLPTR or zero-len array
We need at least three values in this array for any semblance of validity
Let us define a sequence such as [a_1,...a_n,b,c_1,...c_n], where
for all 1 <= i <= n, a_i < a_{i+1}, c_i > c_{i+1}, and a_n < b and b > c_1

Can we use a sliding window technique here?

Computational Complexity : 
Let N := number of elements in our array
Brute-forcing it
Time = O(N^2)
Space = O(1)

Better ( with an array space ) 
Time = O(N)
Space = O(N) + O(N) = O(2N) = O(N) after dropping the constant multipliers




Test Cases : 
(a) [1,2,3,4] -> return 0
(b) [5,4,3,2,1] -> return 0
(c) [1,2,3,2,1] -> return 5 
(d) [1,2,3,2,1,5,6,7,8,9,8,7,6,5] -> returns second peak of len = 9
=== BETTER TEST CASES ===
(e) [1,2,1,2,3,2,1] -> return 5 
(f) [1,2,3,2,1,2,1] -> return 5 
(g) [1,1,1] => return 0 [ the constant input case ] 

Try to establish this inducitvely?
Get length of longest increase as we go over each element of the array, as well as longest decrease ( from both ends )
Double x the space here
Then calculate their corresponding summations too
If either entry is a 0, we do NOT consider it too!



*/
class Program 
{
  public static int longestPeak(int[] array)
	{
		int longestPeak = 0;
		if(array == null || array.length < 3) return 0;
		int n = array.length;
		int[] incr = new int[n];
		int[] decr = new int[n];
		// ZERO-initialize our input array here ( I guess we can be lucky to skip this ) 

		for(int i = 1; i < array.length; ++i)
			if(array[i-1] < array[i])
				incr[i] = 1 + incr[i-1];

		for(int i = array.length - 2; i >= 0; --i)
			if(array[i] > array[i+1])
				decr[i] = 1 + decr[i+1];
		
		for(int i = 0; i < n; ++i)
			if(incr[i] != 0 && decr[i] != 0)
				longestPeak = Math.max(longestPeak, 1 + incr[i] + decr[i]);
		
    return longestPeak;
  }
}
