Assumptions
- no integer data overflow
- all fits into memory
- check for null array as well

Given an input array with a formula expression where (a,b,c,d) are indices in increasing order
in the array. 
Indices : a < b < c < d
Goal : return the largest possible value for the expression

TwoSum/ThreeSum problem
May have duplicate values in the input ( indices unique : not values ) 

Strategies : Iterative DP, Recursive , Sort, HashMaps, Store
O(4*n) space
O(4*n) storage : solve for A[c]-A[d], A[b]+A[c]-A[d], then A[a]-A[b]+A[c]-A[d] ... not sure



INT_MIN values as well
Expressions						 0		1		2			3		4			5																														
A[d]									[3, 	6, 		1	,	 	-3, 		2, 			7]																														
A[c]-A[d]							[4, 	10, 	4	,  	-5, 	-5,			?] ( c = idx 5, no exists idx d > c / > 5 )																																			
*A[b]+A[c]-A[d]				[12 	  10, 	-9	,  -10, ?, 		?]																																			
A[a]-A[b]+A[c]-A[d]																																		
maximum								[^.... array .... ^ ]

A[a] - (A[b]+A[c]-A[d])
== A[a] + A[b] - A[c] + A[d] : not correct here
--> include the signage as we go
A[a] + (-A[b] + A[c] + -A[d] )
	= A[a] -A[b] + A[c] - A[d]
	

Limitation to number of indices solveable for
? : unsure if this is to be sovled for or left "as-is"



				 0	1	 2	 3	4	 5
array = [3, 6, 1, -3, 2, 7]
array[a] - array[b] + array[c] - array[d]
6-(-3)+2-7 = 4
Signange has an impact as well!

	int maximizeExpression(int[] array)

		


NAIVE Solution : 
	O(N^4) T, O(1) S
	b = a+1, c + b+1, d = c + 1
	for(a in (0,len(arr)))
		for(b in (a+1,len(arr)))
			for(c in (b+1,len(arr)))
				for(d in (c+1,len(arr)))
					compare to current running maximum
					Update said maximum
					
	Lookig for O(N), O(N^2) : tradeoff time for space
	May desire a sort ~ breaks relative ordering
	
COMPLEXITY
Let N := Number elements in the array/length
TIME = O(4*N^2) = O(N^2)
SPACE = O(4*N) = O(N)



import java.util.*;

class Program {

  public int maximizeExpression(int[] A) 
	{
		if(A.length < 4)
			return 0;
		int maximum = Integer.MIN_VALUE;
		int n = A.length;
		int[][] calculations = new int[4][n];	// zero initialized ( get this correct too ) 
		for(int i = 0; i < 4; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				calculations[i][j] = Integer.MIN_VALUE;	// zero initialization throwing you off!
			}
		}
		// Seperate out the loops ( for readability/debug ) 
		
		// Set the first row : -A[d]
		for(int i = 0; i < n; ++i)
		{
			calculations[0][i] = -1 * A[i];
		}
		
		// Set the second row : A[c] + (-A[d])
		// Go the right direction too here
		// There is a run-time bug here
		for(int i = n-2; i >= 0; --i)
		{
			for(int j = n-1; j >= i+1; --j)
			{
				int nextVal = calculations[0][j] + A[i];
				calculations[1][i] = Math.max(calculations[1][i], A[i] + calculations[0][j]);
			}
		}

		
		// Set the third row : -A[b] + (A[c]-A[d])
		for(int i = n-3; i >= 0; --i)
		{
			for(int j = n-2; j >= i+1; --j)
			{
				calculations[2][i] = Math.max(calculations[2][i],-1*A[i] + calculations[1][j]);
			}
		}
		

		// Set the fourth row : A[a] + (-A[b] + (A[c]-A[d]))
		for(int i = n-4; i >= 0; --i)
		{
			for(int j = n-3; j >= i+1; --j)
			{
				calculations[3][i] = Math.max(calculations[3][i], A[i] + calculations[2][j]);
				maximum = Math.max(maximum, calculations[3][i]);
			}
		}
		
		return maximum;
  }
}

