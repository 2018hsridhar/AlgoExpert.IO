
Input
{MIN_INT,MAX_INT}
	8 bits * 4 per ints
	Fill in bits on the leading portion ( not the trailing portion ) [ negative marking MSB manipulation ]
	
array = [1, 2, 3, 5, 6, 8, 9]

Return new array, of some length, of the squares of these values, in ascending sorted order
Values can be negative or 0 : in the set of integers
Upper bound : by Integer data type
Let's assume it fits in memory
Assume array can be empty or a NULLPTR as well
Can have duplicate inputs as well

Output
[1, 4, 9, 25, 36, 64, 81]


Test cases : 
(A) [1,2,2,3]
		=> {1,4,4,9}
(B) [-2,-1,0,1,2]
	=> {0,1,1,4,4}
(C) [-3,-2,-1]
	=>{1,4,9}
(D) [1,2,4]
	=>{1,4,16}
(E) [2]
	=>{4}
(F)

(G)

Complexity : 
Let N := # elements in input array
Time = O(N)
Space = O(1)
	> to modify in place or if we need to allocate additional memory in the method
  
	0	 1  2  3 4 5 6 7 8	
[-4,-3,-2,-1,0,1,2,3,4]
^										 ^
lftPtr							 rightPtr

 0	 1  2  3 4   5 6   7 8	
[0, _, _, _, _, 9, 9, 16, 16]
lftPtr	rightPtr	leftSquare	rightSquare	wIdx	
0				8					16 (-4)				16 (4)		8					
1				8					9 (-3)				16 (4)		7
1				7					9 (-3)				9 (3)			6
2				7					4 (-2)				9 (3)			5
...
5				5					0 (0)					0 (0)				0
6				5			

We stop the algorithm once leftPtr > rightPtr
[2]

	0	 1  2  3 4 5 6 7 8	
[-4,-3,-2,-1,0,1,2,3,4]
[0, 1, 1, 4, 4,9,9,16,16]

If loosing original data needed for future computation -> can NOT really do as an "in-place" solution

	0	 1  2  3 4 5 6 7 8	
[-4,-4,-3,-2,-1,0,1,2,3,4,4]
  ^  								 ^
[-4,-3,-2,-1,0,1,2,3,16]
  ^  							 ^
[-4,-3,-2,-1,0,1,2,16,16]
    ^  						 ^
[-3,-3,-2,-1,0,1,2,16,16]
  ^  							 ^
[-3,-3,-2,-1,0,1,2,16,16]
^  							 ^
[-2,-3,-2,-1,0,1,9,16,16]
  ^  						 ^
	
swp() operations within the array to do it in-place?
swp() operation might break the sorted property of the input
	-> swp signage?
	-> may break the subproblem property
	<-leftPtr			->rightPtr
	
A = [-4,-3,-2,-1,0,1,2,3,4]	=> sorted
B = [_,-3,-2,-1,0,1,2,3,_] 	=> sorted
B \subset A

To start from {-1,0}

	

recurse(array, results, 0, 8, 8)
	recurse(array, results, 0, 7, 7)
		recurse(array, results, 1, 7, 6)
			...
				recurse(array, results, 3, 5, 0)

Possible compiler loop optimization here

O(N) call stack pace here
dfs
	dfs()
		dfs()
	dfs
		dfs
			dfs
			dfs
		dfs
	dfs
dfs

	0	 1  2  3|4 5 6 7 8	
[-4,-3,-2,-1,0,1,2, 3,4]
  				 ^,^
[0,-3,-2,-1,-4,1,2,3,16]
  			 ^    ^
[-4,-3,-2,-1,0,1,2,16,16]
  				 ^,    ^
[-3,-3,-2,-1,0,1,2,16,16]
  	^,              ^
[-3,-3,-2,-1,0,1,2,16,16]
  		^,              ^

Show that an in-place modification is NOT possible here, due to loss of original computer data


import java.util.*;

class Program {

	// int[] results ( Global pollution scoping ) 
  public int[] sortedSquaredArray(int[] array) 
	{
		if(array == null || array.length == 0)
			return new int[]{};
		int n = array.length;
		int[] results = new int[n];
		int leftPtr = 0;
		int rightPtr = n - 1;
		int wIdx = n - 1; 
		recurse(array, results, leftPtr, rightPtr, wIdx);
		return results;
	}
	
	// Head recursion : do work in the parent ( caller ) and then recurse on the child ( callee )
	// Each stack frames ( activation records ) has 3 local integer variables & 2 integer pointers
	
	public void recurse(int[] array, int[] results, int leftPtr, int rightPtr, int wIdx)
	{
		// if(leftPtr > rightPtr)
			// return;
		if(wIdx < 0)
			return;
		
		int leftSquare = array[leftPtr] * array[leftPtr];
		int rightSquare = array[rightPtr] * array[rightPtr];
		if(leftSquare >= rightSquare)
		{
			results[wIdx--] = leftSquare;
			++leftPtr;
		}
		else if ( leftSquare < rightSquare)
		{
			results[wIdx--] = rightSquare;
			--rightPtr;
		}
		recurse(array, results, leftPtr, rightPtr, wIdx);
  }
}


import java.util.*;

class Program {

  public int[] sortedSquaredArray(int[] array) 
	{
		if(array == null || array.length == 0)
			return new int[]{};
		int n = array.length;
		int[] results = new int[n];
		int leftPtr = 0;
		int rightPtr = n - 1;
		int wIdx = n - 1; 
		while(leftPtr <= rightPtr)
		{
			int leftSquare = array[leftPtr] * array[leftPtr];
			int rightSquare = array[rightPtr] * array[rightPtr];
			if(leftSquare >= rightSquare)
			{
				results[wIdx--] = leftSquare;
				++leftPtr;
			}
			else if ( leftSquare < rightSquare)
			{
				results[wIdx--] = rightSquare;
				--rightPtr;
			}
		}
		return results;
  }
}

