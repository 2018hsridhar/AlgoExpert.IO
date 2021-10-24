/*
THOGUHT PROCESS :: 

N is guaranteed to be a non-negative integer [ 0, reasonable_upper_bound ]
Return #-possible binary tree topologies created w/exactly n-nodes

Combinations => recursive/DP esque ( remember that LC full-trees question )?


Complexity 
Let N := #-max-nodes
Time = O(N^2)
Space = O(N)
1 pass solution ( with nested intervals here ) 
Tabularization / Bottom-up DP

TEST BENCH : 
... Check for akinness to fibonacci, or someething FIB-esque as well here
(A) n = 1	:	[1]
(B) n = 2	:	[2]
(C) n = 3	:	[5]
(D) n = 4	:	[14]
(E) n = 56 : [580984888]

They can assume that 'n' here is reasonably bounded as well
They need to explain recursion vs DP strategies as well


*/
import java.util.*;

class Program 
{
	// For n = 3 : must solve for {0,1,2,3}
  public static int numberOfBinaryTreeTopologies(int n) 
	{
		if(n <= 1)
		{
			return 1;
		}
		int[] DP = new int[n+1];
		DP[0] = 1;
		DP[1] = 1;
		// O(N)
		for(int i = 2; i <= n; ++i)	// i = subtree size
		{
			// O(i-1) ~= O(N)
			for(int j = 0; j <= (i-1); ++j)	// i is our upper boud here
			{
				DP[i] += DP[j] * DP[(i-1)-j];
			}
		}
		
		return DP[n];
  }
}
