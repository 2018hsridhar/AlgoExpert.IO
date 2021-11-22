Given a 2D array representing structure of a waterfall
A pos int denoting col/src of the water source 
Water src starts directly above the structure

Each row has a 0 := free space, 1 := a block 
Imagine that last row contains buckets waterfall flows into [ zeroes out ] 
Walls on both sides of the structure 
	- trapped against wall
	- flow to one of the last buckets in the row
	
If a block hit : splits evenly to the left or to the right ( 50%-50% )
If waterstream can not flow : it becomes trapped in said direction ( 50% forever last ) 

Input area has @ least 2 rows & 1 col
First row or array always empty

Returns percentage of water inside of the bottom buckets after water flows over entire structure



					3rd col
						|
						^
array = 
[
  [0, 0, 0, 0, 0, 0, 0],
  [1, 0, 0, 0, 0, 0, 0],
  [0, 0, 1, 1, 1, 0, 0],
  [0, 0, 0, 0, 0, 0, 0],
  [1, 1, 1, 0, 0, 1, 0],
  [0, 0, 0, 0, 0, 0, 1],
  [0, 0, 0, 0, 0, 0, 0],
]
source = 3 ( set to 100% ) 

output:

[0, 0, 0, 25, 25, 0, 0]

[0.0,1.0] : 0.0 = empty, 1.0 = fill
Have {0,1} in the input


// The water will flow as follows:
// [
//   [0, 0, 0, ., 0, 0, 0],
//   [1, ., ., ., ., ., 0],
//   [0, ., 1, 1, 1, ., 0],
//   [., ., ., ., ., ., .],				<- water lost here ( stuck : not over edge ) 
//   [1, 1, 1, ., ., 1, 0],
//   [0, 0, 0, ., ., 0, 1],
//   [0, 0, 0, ., ., 0, 0],
// ]


//   [0, 0, 0, 			100%, 0, 	0, 0],
//   [1, 50%, 50%, 100%, 50%, 50%, 0],
//   [0, 50%, 	1, 		1, 		1, 	50%, 	0],
//   [25%, 50%, 25%, 25%, 25%, 50%, 25%],


Million rows : do iteratively now

// The water will flow as follows:
// [
//   [0, 0, 0, ., 0, 0, 0],
//   [1, ., ., ., ., ., 0],
//   [0, ., 1, 1, 1, ., 0],
//   [., ., ., ., 1, ., .],				<- water lost here ( stuck : not over edge ) 
//   [1, 1, 1, ., ., ., 0],
//   [0, 0, 0, 1, 1, ., 1],
//   [0, 0, 0, 1, 1, ., 0],
//   [0, 0, 0, 1, 1, ., 1],
//   [0, 0, 0, 1, 1, ., 0],
// ]

[0, 0, 0, ., 0, 0, 0],
[1, 0, _, ., _, 0, 0],
[0, 0, 1, 1, 1, 0, 0],  
	
Cases : 
	top		bottom	dir 		analysis
	0				0			down			fall
	0				1			down			split 50-50
	0				1			right			right
	0				1			left			left

Not allocating new space

Pseucode : 
	
	recurse(array, results, 0, source, 100, 'D')

	void recurse(double[][] array, double[] results, int row, int col, int amount, char dir) :
		if(array[row][col] == 1)
		{
			return;
		}
		if(row == array.length)
		{
			results[col] += amount
		}
		else
		{
			int nextRow = row + 1;
			int valTop = array[row][col]
			int valBottom = array[nextRow][col]
			if(valTop == 0 && valBottom == 0)
			{
				recurse(array, results, nextRow, col, amount, 'D')
			}
			else if(valTop == 0 && valBottom == 1)
			{
				if(dir == 'D')
				{
					if(col - 1 >= 0)
						recurse(array, results, nextRow, col - 1, amount / 2, 'L')
					if(col + 1 < array[0].length)
						recurse(array, results, nextRow, col + 1, amount / 2, 'R')
				}
				else if ( dir == 'R')
				{
					if(col + 1 < array[0].length)
						recurse(array, results, nextRow, col + 1, amount, 'R')
				}
				else if ( dir == 'L')
				{
					if(col - 1 >= 0)
						recurse(array, results, nextRow, col - 1, amount, 'L')
				}
			}
		}
		
	
	

fill(array, 0,3,100)
	fill(array, 1,3,100)
		fill(array, 1,2,50)
			fill(array, 1,1,50)
			fill(array, 1,2,50)
		fill(array, 1,4,50)

Just add & sum up to the destination row here for recombination
Focus on two rows


Terminating conditions
(a) Hit walls both ways one way but @ boundary : can't go
(b) hit the final row

Waterfall : downwards . = waterfall
Need a ".." for a plateau case
Water can recombine as well
Take note : at a 1 : split (left,right) => we take half of what was passed in

INPUT : 
1. Dimensions can differs 
	-> M >= 2, N >= 1
2. Matrix not null or empty
3. Can have a case of no water reaching down
	(2nd-to-last row : all ones ) 
4. Source always in there
5. Assume matrix fits in-mem

Percentage = ( count / n )

THOUGHT PROCESS : 


STRATEGIES : BFS/DFS ( flood-fill / island counting ), recursively
Card dir : { bottom, left, right } 
May need a visited set : may be able to modify in-place

COMPLEXITY
Let M,N := ( rows, cols )  of the matrix
Time = O(MN) POLY
Space = O(1) ( explicit ) O(MN) ( implicit ) 


//   [0, ., 0, ., 0, 0, 0]
//   [1, 1, ., ., ., ., 0]
//   [1, 1, 1, ., ., ., 0]
//   [1, 1, 1, 1, ., ., 0]
//   [1, 1, 1, 1, 1, ., 0]




import java.util.*;

class Program 
{
  public double[] waterfallStreams(double[][] array, int source) 
	{
		int m = array.length;
		int n = array[0].length;
		double[] results = new double[n];	// dflt 0-initialized
		recurse(array, results, 0, source, 100, 'D');
    return results;
  }
	
	private	void recurse(double[][] array, double[] results, int row, int col, double amount, char dir)
	{
		if(array[row][col] == 1)
		{
			return;
		}
		else if (row == array.length - 1) 	// last row always zero here
		{
			results[col] += amount;
		}
		else
		{
			int nextRow = row + 1;
			double valTop = array[row][col];
			double valBottom = array[nextRow][col];
			if(valTop == 0 && valBottom == 0)
			{
				recurse(array, results, nextRow, col, amount, 'D');
			}
			else if(valTop == 0 && valBottom == 1)
			{
				if(dir == 'D')	// get a split
				{
					if(col - 1 >= 0)
						recurse(array, results, row, col - 1, amount / 2, 'L');
					if(col + 1 < array[0].length)
						recurse(array, results, row, col + 1, amount / 2, 'R');
				}
				else if ( dir == 'R' && col + 1 < array[0].length)
				{
						recurse(array, results, row, col + 1, amount, 'R');
				}
				else if ( dir == 'L' && col - 1 >= 0)
				{
						recurse(array, results, row, col - 1, amount, 'L');
				}
			}
		}
	}
}
