/*
Write whatever you want here.

If the second array a subsequence of the first array
Both List<Integers> : any int value, and can be duplicated integer ( NOT unique ) 
Sequence can have duplicates => not array itself

Any null/empty array/sequence inputs => does not exist

Example : 
array = [5, 1, 22, 25, 6, -1, 8, 10]
				    	 		 		 	  					^ 
							
sequence = [1, 6, -1, 10]
							 					 ^
						
answer : TRUE => corresponding indices = [1,4,5,7]
Duplicates in both types of inputs here

Thought process : 
If both pointers are at the end of (array,sequence) -> return true




Two pointers solution 



Computational Complexity : 
Let M := len(array)
Let N := len(sequence)
Time = O(M) + O(N) => linear-time 
Space = O(1) 

EDGE CASES :
(A)
array = [5, 1, 22, 25, 6, -1, 8, 10]		    	 		 		 	  					
sequence = [1, 6, -1, 10]
TRUE

(B)
array = [5, 1, 22, 25, 6, -1, 8, 10]		    	 		 		 	  					
sequence = [1, 6, -1, 10,11]
FALSE

(C)
array = [5, 1, 22, 25, 6, -1, 8, 10,12,11]		    	 		 		 	  					
sequence = [1, 6, -1, 10]
TRUE ( ptr2 is exhausted BEFORE ptr1 ) 

(D)
array = [5, 1, 1,2,3,4,5]
sequence = [1, 1,1,1]
FALSE ( ptr1 is NULL : ptr2 does not reach end ) 

(E)
array = [1,3,2,4]
sequence = [5,3,2,1]
sequence=[1,3,2,5]
	
WE can only get as good as O(N) if the first element of sequence does not match any value in the array input
	-> perform each element-by-element comparison operation
WE are bounded at best by O(M) + O(N)
Can't apply binary search divide-and-conquer because input is NOT sorted here

*/





import java.util.*;

class Program {
  public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) 
	{
		int m = array.size();
		int n = sequence.size();
		if(n > m )
			return false;
		int a_ptr = 0;
		int s_ptr = 0;
		while(a_ptr < m && s_ptr < n)
		{
			if(array.get(a_ptr) == sequence.get(s_ptr))
				++s_ptr;
			++a_ptr;
		}
		return (s_ptr == n);
  }
}
