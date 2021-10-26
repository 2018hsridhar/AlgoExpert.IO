/*
THOUGHT PROCESS : 

input is in ANY order : not always sorted
All students must receive @ least one reward
Must get strictly more than an adj student ( immediate left/right ) with a lower score
strictly fewer than an adj student with a higher score
Can not get dulicate scores ( uniqueness property ) 

Strategies : one-pass, two-pass, assignment, two pointers, subsequences ( incr/decr ) 

Can we start at the global minimia OR the global maxima as well?
Return min-# rwds : make sure the gap is just 1 in between positins as well


COMPLEXITY
Let N := length(scores)
Time = O(2N) = O(N)
	scalars for incr/decr vals as well
Space = O(N) = O(N)

TEST BENCH
(A) [1]
(B) [8, 4, 2, 1, 3, 6, 7, 9, 5]
(C) [3, 2, 1]
(D) [1,3,10,15]
	return [1,2,3,4] = 10
(E) [4,3,2,1]
	10
(F) [1,3,6,7,9,5]
(G) [1,3,6,7,9,5,10,15,20]
	[1,2,3,4,5,1,2,3,4]
	25
(H) [1,3,6,7,9,5,10,15,20,25,30,35,40]
	[1,2,3,4,5,1,2,3,4,5,6,7,8]
	51
	If we drop ... we start over yet again ( from index 1 ) 
(I) [40,35,30,25,20,15,10,5]
	[8,7,6,5,4,3,2,1]
	 36
(J) [2,5,1,4,3]
	[1,2,1,2,1] : sum = 7
(K) [1,2,0,4,5,3,7,6] : sum = 7
	
	
Rwd values can be duplicated as well
Increasing sequences from the minima ( and decreasing sequences too ) 
Longest CONSECUTIVE increasing/decreasing subsequence

*/
import java.util.*;


class Program {
  public static int minRewards(int[] scores) 
	{
		int minRwd = 0;
		int n = scores.length;
		int[] incr = new int[n];
		int[] decr = new int[n];
		incr[0] = 1;
		for(int i = 1; i < n; ++i)
		{
			if(scores[i] > scores[i-1])
			{
				incr[i] = incr[i-1] + 1;				
			}	
			else
			{
				incr[i] = 1; 
			}
		}
		decr[n-1] = 1;
		for(int i = n-2; i >= 0; --i)
		{
			if(scores[i] > scores[i+1])
			{
				decr[i] = decr[i+1] + 1;				
			}	
			else
			{
				decr[i] = 1; 
			}
		}
		for(int i = 0; i < n; ++i)
		{
			minRwd += Math.max(incr[i], decr[i]);
		}
		
    return minRwd;
  }
}
