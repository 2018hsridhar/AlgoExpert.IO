import java.util.*;

class Program {
  public static int[] searchForRange(int[] array, int target) 
		{
			int low = 0;
			int high = array.length - 1;	// the bug was here
			int elIdx = binarySearch(array, target, low, high);
			// System.out.printf("Element idx = %d\n", elIdx);
			int[] res = new int[]{-1,-1};
			if(elIdx == -1)
			{
				return res;
			}
			else
			{
				res[0] = elIdx;
				res[1] = elIdx;
				int lhs_idx = searchRange(array, target, low, elIdx - 1, 'L');
				int rhs_idx = searchRange(array, target, elIdx + 1, high, 'R');
				if(lhs_idx != -1 && lhs_idx < elIdx)
				{
					res[0] = lhs_idx;
				}
				if(rhs_idx != -1 && rhs_idx > elIdx)
				{
					res[1] = rhs_idx;
				}
			}
			return res;
		}
	
		// Pass in a boolean flag or character to denote search direction ? Or two different funcs?	
		// 	// Pass in a boolean flag? Or two different funcs?
		// // (33,45,45,45,45) on indices [3,7] for searchRightRange
		// // At index 5, we equal 45. Goal -> index 4 here!
		public static int searchRange(int[] array, int target, int low, int high, char dir)
		{
			// System.out.printf("In func searchLeftRange: [low,high] = [%d,%d]\n", low, high);
			if(low > high)
				return -1;
			int midIdx = (low + high)/2;
			int midEl = array[midIdx];
			if(midEl < target)
			{
				return searchRange(array,target, midIdx + 1, high, dir);
			}
			else if ( midEl > target)
			{
				return searchRange(array, target, low, midIdx - 1, dir );
			}
			else if ( midEl == target)
			{
				if(dir == 'L')
				{
					int left_sub = searchRange(array,target, low, midIdx - 1, dir);
					if(left_sub != -1)
					{
						return left_sub;
					}
					else
					{
						return midIdx;
					}
				}
				else if ( dir == 'R')
				{
					int right_sub = searchRange(array,target, midIdx + 1, high, dir);
					if(right_sub != -1 && right_sub > midIdx)
					{
						return right_sub;
					}
					else
					{
						return midIdx;
					}
				}
			}
			return -1;
		}

		
		public static int binarySearch(int[] array, int target, int low, int high)
		{
			if(low > high)
			{
				return -1; 
			}
			else
			{
				int mid = (low + high)/2;
				int midEl = array[mid];
				if(midEl == target)
				{
					return mid;
				}
				else if ( midEl < target)
				{
					return binarySearch(array, target, mid + 1, high);
				}
				else if ( midEl > target)
				{
					return binarySearch(array, target, low, mid - 1);
				}
			}
			return -1;
		}
}

Write a function that takes in a sorted array of integers as well as a target integer. 
The function should use a variation of the Binary Search algorithm to find a range of indices 
in between which the target number is contained in the array and should return this range in the form of an array.

The first number in the output array should represent the first index at which the target number is located, 
while the second number should represent the last index at which the target number is located. 
The function should return [-1, -1] if the integer isn't contained in the array.

If you're unfamiliar with Binary Search, we recommend watching the Conceptual Overview section of the 
Binary Search question's video explanation before starting to code

				 0	1		2		3		4		5		6		7		8		9	 10	 11  12
array = [0, 1, 21, 33, 45, 45, 45, 45, 45, 47, 61, 71, 73]
				 ====== L=============  ^  ---->	 G
				        ^ ----------			 ------
								       ^					 new subrange : greater than element
	
	4 local variables in this modified bsearch
	Can go both ways with their corresponding middles equal to the targets as well
	
PSEUDOCODE : 

		Seperate recursive method : search lower range and higher range

	  public static int[] searchForRange(int[] array, int target) 
		{
			int low = 0;
			int elExistsIdx = binarySearch(array, target, low, high);
			
			
			
			
		}
		
		public static int binarySearch(int[] array, int target, int low, int high)
		{
			if(low > high)
			{
				return -1; 
			}
			else
			{
				int mid = (low + high)/2;
				int midEl = array[mid];
				if(midEl == target)
				{
					return mid;
				}
				else if ( midEl < target)
				{
					low = mid + 1;
				}
				else if ( midEl > target)
				{
					high = mid - 1;
				}
			}
			return -1;
		}
		

[6,6]
	=> [4,6] ( lhs )
	=> [4,9] ( rhs )
[4,9] return on the callback

Strategies : Divide-and-Conquer, Binary Search

target = 45
low = 0, high = 12, mid = 6

output: [4,9]

Given a sorted array and a target value
Use a variation of the binary search algorithm
Do not find the index of the target -> find the range of the target
	first index, last index of the target
	
First pass -> determines existence OR return of [-1,-1]

Naive solutions - O(N) Time, O(1) space - linear scan

COMPLEXITY
Let N := len(array) / #-elements
TIME = O(logN)
			O(logN*(logN)) = O(log^2(N))
SPACE = O(1) ( exp/imp for iterative bsearch ) or O(logN) ( imp -> recursive )


TEST CASES
(A) [1,1,1,1,1], target = 1
		idx 2 has 1
		=> [0,4]
(B) [-1,0,1,2,3], target = 1
	=> [2,2]
(C) [-1,0,1,2,3], target = 4
	=> [-1,-1]
(D) [10] , target = 10
	=> [0,0]
(E)
(F)
(G)


	

