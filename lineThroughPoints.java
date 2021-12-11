What is being asked : Given an array of points plotted in a 2D graph on the XY Cartesian place
Returns max #-points that a single line can pass through
Maximize # of collinear point

Input array = points represented by 2 ints { x, y}
No duplicate points
Always contains at least one point
Special case for vertical slots	( "?" -> vertical case, "D" -> default case )
Slopes have to be doubles as well ( slope of "1|3" )

=====================
-> LEXICOGRAPHIC SORT ( incr(x), incr(y) if x the same )
Sample Input
points = [
  [-2, 6],		< I
	[-1,12],		< J
  [0, 4],			{ entry ~ -1, entry ~ -3, entry ~ -3/2 } 
									^ I					^ J
  [1, 1],
  [2, 1],				<- index (i)
  [2, 2],				<- index (j) where j = i + 1
  [3, 3],				<- j+1
  [4, 0],				<- j + 2
]
Sample Output
4 // A line passes through points: [-2, 6], [0, 4], [2, 2], [4, 0].
===========================



[2,2] to [4,0] : slope = -1, [0,4] to [2,2], slope = -1 => extrapolate from that
[2,2] to [3,3] : slope = +1
	For earlier candidates : slopes can vary
	Can have many slopes too. May need more tracking
	HM : slope -> #-points on the slope

Strategies : 
Need some concept of slop ( delta_y / delta_x ) 
Recursive/DP manner
Order the elements -> 4 dirs for lines

Set the two points for initial exploration : n^2 pairings

[-2,6] and [0,4] : slope = -1
[0,4] and [1,1] 	: slope = -3
-3 != -1 : do not explore that candidate further
[0,4] and [2,2] : slope = -1 -> continue checking other solutions for maximal


BRUTE FORCE NAIVE COMPLEXITY ( backtracking ) 
Let N := #-points
Set of all subsets, P(S) := powerset: |P(S)| = 2^N for a set of N elements
TIME = O(EXP) -> O(2^N) -> check all of them collinear with one another
SPACE = O(1) ( EXP ) O(N) ( IMP )

The brute-force approach to solve this problem is to consider every single pair of
points and to form a line using them. Then, for each line, you determine how many 
points lie on that line by using the equation of the line you formed and checking 
if each point's coordinates solve the equation. This solution runs in O(n^3) time; 
can you come up with a better approach?

				 -----
Better : O(N^2), O(N), O(NlgN), O(N^2lgN), or O(N^2\sqrt(N))

BETTER ( Bottom-up DP Solution ) 
TIME = POLY = O(NlogN) + O(N^2) = O(N^2)
	-> we check ONLY one entry in the hashmaps stored in each DP cell
SPACE = O(N^2) ( EXP ) O(1) ( IMP ) 

TEST CASES : 
(A) ( all hoz ) 
	[[1,1],[2,1],[3,1],[4,1]]
	=> 4
(B) ( all vert ) 
	[[1,1],[1,2],[1,3],[1,4]]
	=> 4
(C) ( All scattered )
(D) [[1,1]]
	=> 1 point ( default slope ) 

import java.util.*;

class Program {

  public int lineThroughPoints(int[][] points) 
	{
		int numPoints = 1;
		int n = points.length;
		if(n == 1)
		{
			return 1;
		}
		
		// Precompute DP array
		// Precompute hashmaps ( with default values too ) 
		// Array HMs not valid in JAVA		
		List<HashMap<String,Integer>> numRightPoints = new ArrayList<HashMap<String,Integer>>();
		for(int i = 0; i < n; ++i)
		{
			HashMap<String,Integer> novel = new HashMap<String,Integer>();
			novel.put("D", 1);	// "D" => default value
			numRightPoints.get(i) = novel;
		}
		
		// Sort the slope points in dictionary order ( incr(x), then incr(y) for tie-breaker cases of x ) 
		Arrays.sort(points, new Comparator<int[]>(){
			public int compare(int[] pOne, int[] pTwo)
			{
				if(pOne[0] < pTwo[0])
				{
					return -1;
				}
				else if ( pOne[0] > pTwo[0])
				{
					return 1;
				}
				else
				{
					if(pOne[1] < pTwo[1])
					{
						return -1;
					}
					else if (pOne[1] > pTwo[1])
					{
						return 1;
					}
					return 0;
				}
			}
		});
		
		// Verify correctness of the sort
		for(int i = 0; i < n; ++i)
			System.out.printf("[%d,%d]\n", numPoints[0], numPoints[1]);
		
		
		
		
		
		
		
		return numPoints;
  }
}

