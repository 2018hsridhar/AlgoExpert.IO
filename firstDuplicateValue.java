Given an array of integers between 1 and n, where n := len(array)
	No inputs are negative or zero
Return first integer appearing more than once in the array
Iterate over array left -> right
Find the FIRST duplicate number ( not the index )
If non-existent, return -1

3 is a duplicate, but 2 appears first twice before 3 appers twice in the input
array = [2, 3, 1, 5, 2, 3, 4] -> 2

Bitsets/BitMaps ( tiny : n number of bits ) 
	n = 10 : 
		0000000000 { bit string all zeroes }
		array = [1,2,6,2,1]
		1100010000
     ^
		 hit 2 again => is in the bitset
		 n to a million values 
		 O(N) space, but N bits of memory
		 Could do better than a hashset in some cases?
		 
1. Hashset

PSEUDOCODE : 

Negative marking
Allowed to modify array in-place as well
	firstVal = -1
	for each elem in the array
		if(array[elem-1] < 0)
			firstVal = elem
			break
		array[elem - 1] *= -1
	ret firstVal

Naive solution : O(N) space, O(N) time
	firstVal = -1
	hashset = ()
	for each element in the array
		if(hashset does not contain element)
			hashset add element
		else
			firstVal = element
			break
	ret firstVal
	

COMPLEXITY
Let N := #-elemnets in the input
TIME = O(N)
SPACE = O(1) ( IMP ~ call stack )  O(1) ( EXP ) 

Should not sort the array
(A) array = [3, 2, 1, 3, 2, 3, 4] -> 3
	If sorted := [1,2,2,3,3,4] =-> return 2
	Relative ordering of elements in original input matters
	
(B) array = [1,2,3,4,5,6,1] => 1
		Search all elements
(C) array = [1]
	=> -1
(D) [3,2,1]
	^ run time error : resolve that
(E) array = [1,2,3,1,3]
(F) []
(G) [1,2,56,1,3,76,1,2,8,2,5,2]



import java.util.*;

class Program {

	// Negative marking of indices technique
	// Take absolute value of elements only 
  public int firstDuplicateValue(int[] array) 
	{
			int firstVal = -1;
			if(array == null || array.length == 0)
				return firstVal;
			for(Integer x : array)
			{
				x = (int)Math.abs(x);
				if(array[x-1] < 0)
				{
					firstVal = x;
					break;
				}
				else
				{
					array[x-1] *= -1;
				}
			}
			return firstVal;
  }
}

