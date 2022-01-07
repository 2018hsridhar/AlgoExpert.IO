import java.util.*;

class Program {

	private HashMap<Integer,Set<Integer>> constructPointsMap(int[][] points)
	{
		// [1] Construct HashMap
		HashMap<Integer,Set<Integer>> coords = new HashMap<Integer,Set<Integer>>();
		int n = points.length;
		for(int i = 0; i < n; ++i)
		{
			int[] point = points[i];
			int x = point[0];
			int y = point[1];
			if(!coords.containsKey(x))
			{
				coords.put(x, new HashSet<Integer>());
				coords.get(x).add(y);
			}
			else if ( coords.containsKey(x))
			{
				coords.get(x).add(y);
			}
		}
		return coords;
	}
	
	public int minimumAreaRectangle(int[][] points) 
	{	
		if(points == null || points.length == 0 )
			return 0;

		HashMap<Integer,Set<Integer>> pointMap = constructPointsMap(points);
		int minArea = getMinArea(points,pointMap);
		return minArea;
	}
	
	private int getMinArea(int[][] points, HashMap<Integer,Set<Integer>> pointMap)
	{
		int minAreaRect = Integer.MAX_VALUE;
		int n = points.length;
		
		for(int i = 0; i < n; ++i)
		{
			for(int j = i+1; j < n; ++j)
			{
				int[] bottomLeft = points[i];
				int[] topRight = points[j];
				if(topRight[0] > bottomLeft[0] && topRight[1] > bottomLeft[1])	// Inequality test
				{
					// Inspect this for bugs
					int[] topLeft = new int[]{bottomLeft[0], topRight[1]};
					int[] bottomRight = new int[]{topRight[0], bottomLeft[1]};
					if(foundPointInPointsMap(topLeft, pointMap) && foundPointInPointsMap(bottomRight, pointMap))
					{
						int length = (int)Math.abs(bottomRight[0] - bottomLeft[0]); // safety 
						int width = (int)Math.abs(topLeft[1] - bottomLeft[1]); // safety 
						int curArea = length * width;
						minAreaRect = Math.min(curArea,minAreaRect);
					}
				}
	
				// Order was incorrect here
				int[] topLeft = points[i];
				int[] bottomRight = points[j];
				if(bottomRight[0] > topLeft[0] && bottomRight[1] < topLeft[1])	// Inequality test
				{
					// Inspect this for bugs
					int[] bottomLeft_two = new int[]{topLeft[0], bottomRight[1]};
					int[] topRight_two = new int[]{bottomRight[0], topLeft[1]};
					if(foundPointInPointsMap(bottomLeft_two, pointMap) && foundPointInPointsMap(topRight_two, pointMap))
					{
						int length = (int)Math.abs(bottomRight[0] - bottomLeft_two[0]); // safety 
						int width = (int)Math.abs(topLeft[1] - bottomLeft_two[1]); // safety 
						int curArea = length * width;
						minAreaRect = Math.min(curArea,minAreaRect);
					}
				}
			}
		}
		
		if(minAreaRect == Integer.MAX_VALUE)
			return 0;
		return minAreaRect;
  }
	
	private boolean foundPointInPointsMap(int[] point, HashMap<Integer,Set<Integer>> pointMap)
	{
		if(pointMap.containsKey(point[0]))
		{
			if(pointMap.get(point[0]).contains(point[1]))
			{
				return true;
			}
		}
		return false;
	}
	
}

Minimum Area Rectangle
You're given an array of points plotted on a 2D graph (the xy-plane). 
Write a function that returns the minimum area of any rectangle that 
can be formed using any 4 of these points such that the rectangle's 
sides are parallel to the x and y axes (i.e., only rectangles with
horizontal and vertical sides should be considered--no rectangles 
with diagonal sides). If no rectangle can be formed, your function 
should return 0.

The input array will contain points represented by arrays of two 
integers [x, y]. The input array will never contain duplicate points.

Points never duplicates

Sample Input
points = 
[
  [1, 5],
  [5, 1],
  [4, 2],
  [2, 4],
  [2, 2],
  [1, 2],
  [4, 5],
  [2, 5],
  [-1, -2],
]
Sample Output
3
// The rectangle with corners [1, 5], [2, 5], [1, 2], and [2, 2]
// has the minimum area: 3.


Given some array of pointers, represneted as integers
Never receive duplicate points

Assume the input fits into RAM


Strategies : Combinatorial ( Recursive/DP ), Sort ( 1D : X,Y or 2D ) , Enumerative


Assume each as a "bottom-left" corner



points = 
[
  [-1, -2],				
  [1, 2],									(1,2)->(2,2)->(1,5)->(2,5)
  [1, 5],
  [2, 2],		<-		index = i here
  [2, 4],
  [2, 5],
  [4, 2],
  [4, 5],	<- can be a top right
  [5, 1]
]

			X				Y
HM : Ints -> Sets
{
	-1 => (-2),
	1  => (2,5),
	2 => 	(2,4,5)
	4 => 	(2,5)
	5 => 	(1)
}

  [-1, -2] = bottomLeft		
	[2, 2]	 = topRight
	
	[-1,2]    = topLeft					: is a HM entry
	[2,-2]		= bottomRight			: not a HM entry

N^2 way of doing it 
	-> suppose we take the bottom-left and top-right
	-> we know what topLeft and bottomRight should be
	
	
																	        ----
On y = 2, points on it are : (1,2),(2,2),(4,2)
On x = 2, points on it are : (2,2),(2,4),(2,5)
																	 -----------
						Can get to N^3
						(4,2) & (2,4) => (4,4)	NO
						(4,2) & (2,5) => (4,5)	YES
						
Provided we sort ahead of time, hashmap value lists maintain sort -> avoid sorting elems as pushed into HM values

PSEUDOCODE : 

	# O(nlgn) T, O(1) S
	Lexicographicaly sort points, in increasing x, then by increasing y
	
	minArea = MAX_VAL
	for each point in points:
		bottomLeft <- point
		do we have a bottomRight
		do we have a topLeft
		do we have a topRight
		curArea <- calculate area length*height
		minArea = min(minArea, curArea)
		
	ret minArea

Complexity
Let N := #-points
Naive : O(N^3), O(N)
Better : O(N^2), O(N) space
				O(N^2) + O(NlgN), O(1) + O(1)
TIME = O()
SPACE = O()

Test Cases
(A)
[
  [1, 1],
	[3,1],
	[1,3],
	[3,3],
  [2, 1],
  [1, 2],
  [2, 2]
]
	=> 1 ( area of two ) 
(B)
[
	[1,1],
	[2,2],
	[3,3],
	[4,4]
]

(C) []
	=> 0
(D)



[
  [1, 5],
  [5, 1],
  [4, 2],
  [2, 4],
  [2, 2],
  [1, 2],
  [4, 5],
  [2, 5],
  [-1, -2]
]





