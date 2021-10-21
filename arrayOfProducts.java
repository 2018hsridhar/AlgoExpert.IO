import java.util.*;

class Program {
  public int[] arrayOfProducts(int[] array) 
	{
		if(array == null || array.length == 0)
	    return new int[] {};
		int n = array.length;
		int[] products = new int[n];
		for(int i = 0; i < n; ++i)
		{
			products[i] = 1;
		}
		
		// left pass approach
		int left_prod = 1;
		for(int i = 1; i < n; ++i)
		{
			left_prod *= array[i-1];
			products[i] *= left_prod;
		}
		
		// right pass approach
		int right_prod = 1;
		for(int i = n-2; i >= 0; --i)
		{
			right_prod *= array[i+1];
			products[i] *= right_prod;
		}
		
		return products;
  }
}


/*
Given an input array of integers
Returns array of same len, where each elem in output array = produt of every other
	-> must be new array ( not in-place mod ) 
# in the input array

Prefix sum / running sum idea

array =  [5, 1, 4, 2]

				[_, 1, 4, 2]
				[5, _, 4, 2]
				[5, 1, _, 2]
				[5, 1, 4, _]
				 0	1	 2	3
				 
				[1,1,1,1]
				[5,1,1,1]
				[5,5,1,1]	// keep track of a running product as we go
				[1,5,5,20]	// keep track of a running product as we go
				
				[1,5,5,20]
				[1,5,10,20]
				[1,40,10,20]
				[8,40,10,20]
				
				
				
Wrap-around the array

output = [8, 40, 10, 20]
				  0  1    2   3
				[ (1*4*2), (5*4*2),(5*1*2),(5*1*4)]
				[ (1,2,3), (0,2,3),(0,1,3),(0,1,2)]
				
				[_,_,_,_]
				
Problem constraint : must solve WITHOUT use of the division operator ( addition,subtraction,mult) operations?
Array is non-empty
	- can have duplicates
	- [5] : it is possible ( you would return 1 in this case ) ?
	- no upper bound ( is reasonable ) 
	- can have a zero and negatives as well
	

Complexity : 
Let N := #-elements in the input array
Time = O(N^2)
Space = O(1)

Optimize to 
Time = O(N)
	-> one pass or a two pass approach
Space = O(1)

Pseudocode 
	int[] arrayOfProducts(int[] array)
	{
		int n = array.length;
		int[] products = new int[n];
		for(int i = 0; i < n; ++i)
		{
			products[i] = 1;
		}
		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				if(j != i)
				{
					products[i] *= products[j];
				}
			}
		}
		return products;
 }
 
 [0 9 8]
  0 1 2
 n = 2
 products = [1,1,1]
 
 i		j				products
 
 0		1			[9,1,1]
 0		2			[72,1,1]
 1		0			[72,0,1]
 1		2			[72,0,1]
 2		0			[72,0,0]
 2		1			[72,0,0]
 
 return [72,0,0]
 
 
TEST BENCH : 
(A) [5]
(B) [5, 4]
(C) [5, 4, 2]
(D) [-1, 2, 3]
(E) [0, 9, 8]
(F) [1,0,4,0,8,0,2]
(G) [1,99,4,-5,8,0,2,7,0,4,0,5,3,2]
(H) [INT_MIN,INT_MAX] ... data overflow? Maybe I return a long for the output ( double )


*/

			
