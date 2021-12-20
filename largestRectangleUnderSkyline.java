Write a function that takes in an array of positive integers representing the 
heights of adjacent buildings and returns the area of the largest rectangle 
that can be created by any number of adjacent buildings, including just one building. 

						 0	1	 2	3	 4	5	 6	7	 8
buildings = [1, 3, 3, 2, 4, 1, 5, 3, 2] - 9

Values at each index := height of each building

// Below is a visual representation of the sample input.
//              _
//          _  |5|
//    _ _  | | | |_
//   | | |_| | | | |_
//  _| | |2|2|_| | | |
// |_|_|_|_|_|_|_|_|_|
					  ==========
						= -------> ( go to right )
					^ ^ ^
					~ ~
		=					= =
		
DIAGRAM #2
//              _
//          _  |5|
//         | | | |_
//    _ _ _| | | | |_
//  _|_|_|2|2|_| | | |
// |_|_|_|_|_|_|_|_|_|
					  ^----> as we go 
		 ======
		 
Longest increasing subsequence problem?

L->R, or R->L iteration	
buildings = [2, 1, 2] => 3 ( use all 3 buildings, using minHeight = 1 across all buildings ) 

Strategies : Recursive, DP, Stack, Divide-and-Conquer
Invariant : Buildings are always of STRICTLY increasing height

  =======
As we pop off stack, see how long widths lasted for
[ 1, 3, 3, {2,4} ...] <-- push() or pop() or peek()
	Length/number elements in the stack too
	Alternate behavior @ smaller, equal to, or larger buildings
	How far back to iterate into the stack?
	Two buckets/two stack approach?
[1, 2, 2, 2, 4,1]
....
[1, 1, 1, 1, 1, 1, ... -> ]
 -----------
[1,2,3,4,5,2,...] <-- how many to pop off here?
O(N^2) technically

Stack -> it's not just one value : it's a pair
height, length(height lasted for )

[ 1:1, 3:1, 3:1, ...]
[ 1:1, 2:3, ... ]

[ 1:1, 2:3, 4:1, 1:1 ] 1<4
[ 1:1, 2:3, ] area = 1
[ 1:1 ] 3+area = 4 => 2 * 4 = 8 => new area = 4
[ 1:6 ] => continue

[1,2,3,4,5,1]
[1:1, 2:1, 3:1, 4:1, 5:1, ___ ]
[1:1, 2:1, 3:1, 4:1, ___ ]] width = 1
[1:1, 2:1, 3:1, ___ ]] width = 1+1 = 2
[1:1, 2:1, ___ ]] width = 2+1 = 3
...
[1:1] width = 4
add 4 to existing tuple info
[1:5]
add (1:1) to (1;5)
[1:6, ....]

Adding only height ~ not indices

NAIVE approach
	For each height, go test how far right you can go, for each building
	O(N^2H) time, O(1) space
	Maybe we can tradeoff space for time here
	O(NH) time, O(NH) space?
	Heights of buildings vary 
	
	
IDEAL COMPLEXITY
Let N := #-buildings
Let H := max height across buildings
TIME = O(N)
SPACE = O(N)

TEST CASES
(A) [ 1,2,3,4,5]
(B) [1, 3, 3, 2]
(C) [ 1,2,2,2,3,3,3,3,4,4,5]
(D) [1, 3, 3, 2,3,4,5]
(E) [1,2,3,4,5,2]
		   			 -
					 ===
				 *****

Longest increasing subsequence
Can we use indices here too?



import java.util.*;

class Program {

  public int largestRectangleUnderSkyline(ArrayList<Integer> buildings) 
	{
		int largestArea = 0;
		Stack<Integer> stk = new Stack<Integer>();
		int sz = buildings.size();
		stk.push(buildings.get(0));
		for(int i = 1; i < sz; ++i)
		{
			int curBuilding = buildings.get(i);
			if(curBuilding > stk.peek())
			{
				stk.push(curBuilding);
			}
			else if ( curBuilding < stk.peek())
			{
				int topHeight = stk.peek();
				int numLarger = 0;
				int numPopped = 0;
				while(!stk.isEmpty())
				{
					int curHeight = stk.pop();
					numPopped++;
					if(curHeight == topHeight)
					{
						numLarger++;
						int curArea = numLarger * curHeight;
						if(curArea > largestArea)
						{
							largestArea = curArea;
						}
					}
					else if ( curHeight < topHeight)
					{
						topHeight = curHeight;
						// Might not be reseting a variable here ( for topHeight )
						numLarger++;	// might be incorrect as well ( decreasing sequence of heights )
						int curArea = numLarger * curHeight;
						if(curArea > largestArea)
						{
							largestArea = curArea;
						}
					}
				}
				for(int k = 0; k < numPopped; ++k)
				{
					stk.push(curBuilding);
				}
			}
		}
		// Solve for largest area, as stack not empty after iteration here
		// Increasing sequence : take note of this [ 1,2,3,4,5], [ 1,2,2,2,3,3,3,3,4,4,5]
		// Just number you popped off here
		int numPop = 0;
		while(!stk.isEmpty())
		{
			int curBuild = stk.pop();
			numPop++;
			int curArea = curBuild * numPop;
			if(curArea > largestArea)
			{
				largestArea = curArea;
			}
  	}
    return largestArea;
	}
}

