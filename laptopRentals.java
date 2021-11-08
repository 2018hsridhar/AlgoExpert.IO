
Goal : Given a list of time intervals during which students may rent laptops
Timed intervals repped by pairs of (start_i,end_i) ints where start >= 0 and start < end
start and end do NOT denote real times : can both be > 24

NO two students can use a laptop @ same time
One student @ a time
[0,2] = first interval
[2,___] = any open time
Closed intervals : inclusive

Return min-# laptops that the school needs to rent such that
all students always have access to a laptop when they need one

Input clarifications / constraints : 
Does the intervals [a,b] and [b,c] collide where a < b < c?
		[2,3] and [3,4]? Or are they independent?
Can we get a null list or a list with just a single interval?


Sample Input
times = 
[
  [0, 2],
  [1, 4],
  [4, 6],
  [0, 4],
  [7, 8],
  [9, 11],
  [3, 10],
]
Sample Output
3

Problem solving strategies : 
	Lexicographic sort : sort by increasing start time, then by decreasing end time
	Greedy or recursive strategy ( optimization problem : minimization )
	
BEGIN POINT SORT :
times = 
	[
		[0, 4], ()
		[0, 2], XXX
		[1, 4], ()
		[3, 10], ****
		[4, 6],
		[7, 8],
		[9, 11],
	]
	
ENDPOINT SORT :
times = 
	[
		[0, 2],								<----intervalPtr
		[0, 4],
		[1, 4],
		[4, 6],
		[7, 8],
		[3, 10],
		[9, 11],
	]	
	
@ [4,6] : we escaped [0,4] and [1,4], but not [3,10]
We might consider the endpoints as well

We can run this timestep from the minimal time to the maximal time [ 0, 11 ]
Timesteps denote when stepping into an interval

		Max := 3 for list size
		Dynamic list : sliding window technique
		Sliding window : can be like a minHeap sorted by the end time
		Sz(heap) at max is N, insertion is log(N) time
		
t		intervals
0		[0,2],[0,4]
1		[0,2],[0,4],[1,4]
2		[0,2],[0,4],[1,4]
3		[0,4],[1,4],[3,10]		*					kicked out [0,2]
4		[3,10],[4,6]					*
5		[3,10],[4,6]	
6		[3,10],[4,6]
7		[3,10],[7,8]					*
8		[3,10],[7,8]
9		[3,10],[9,11]
10	[3,10],[9,11]
11	[9,11]




	
	
[0,4] and [0,2] has an overlap => [0,4]
[1,4] and [0,4] has an overlap => [0,4]
[3,10] and [0,4] has an overlap => [0,10]
... 
[4,6] which has an overlap with [0,10]
[7,8] also has that overlap

	
Find out if there is a conflict	


Complexity
Let N := len(intervals)
Time = O(NlogN) + O(S)
Space = 


TEST BENCH
(A) [[1,2],[3,4],[5,6],[7,8]]
		1
(B) [[1,4],[1,3],[1,2]]
		3
(C) [[1,2],[1,4],[3,5]]
		2
(D) [[1,1],[2,2,],[3,3]]
(E) [[1,2],[1,3],[1,4],[4,5]]


NESTED CASE ::
I1			<---------------->	
I2			<---------->
I3			<------>

4-TYPES OF OVERLAP CASES ::

I1					<---------->
I2			<---------------->
		
I1			<---------------->
I2				<------>

I1			<---------------->
I2							<---------------->

I1									<---------------->
I2				<---------------->


Pseudocode : 

  public int laptopRentals(ArrayList<ArrayList<Integer>> times)
	{
		sortIntervals(times);
		int lowestTime = times.get(0).get(0);
		int highestTime = times.get(times.size() - 1).get(1);
		for(int i = lowestTime; i <= highestTime; ++i)
		{
			
		}
		PriorityQueue<ArrayList<Integer>> 
		
		
	}
	
	private void sortIntervals(ArrayList<ArrayList<Integer>> times)
	{
		Collections.sort(times, new Comparator<ArrayList<Integer>>(){
			public int compare(ArrayList<Integer> one, ArrayList<Integer> two)
			{
				if(one.get(0) < two.get(0))
				{
					return -1;
				}
				else if(one.get(0) > two.get(0))
				{
					return 1;
				}
				else
				{
					if(one.get(0) > two.get(0))
					{
						return -1;
					}
					else if(one.get(0) < two.get(0))
					{
						return 1;
					}
					return 0;
				}
			}
		});
  }
	
	private boolean hasOverlap(ArrayList<Integer> one, ArrayList<Integer> two) 
	{
	
	
	
	
	
  }
	
	// Minimum of the left, the maximal of the right
	// [1,5],[2,8] -> [1,8] : [6,7] or [1,2] : they collide with one of the subintervals
	private ArrayList<Integer> merge(ArrayList<Integer> one, ArrayList<Integer> two)
	{
		ArrayList<Integer> merged = new ArrayList<Integer>();
		int newLeft = Math.min(one.get(0),two.get(0));
		int newRight = Math.min(one.get(1),two.get(1));
		merged.add(newLeft);
		merged.add(newRight);
		return merged;
	}


import java.util.*;

class Program {

 public int laptopRentals(ArrayList<ArrayList<Integer>> times)
	{
	 	// base case handling
	 	if(times == null)
		{
			return 0;
		}
	 	else if(times.size() <= 1)
		{
			return times.size();
		}
		sortIntervals(times);
	 	int n = times.size();
	 
		int lowestTime = times.get(0).get(0);
		int highestTime = times.get(n - 1).get(1);
	 	// Endpoint sorting here for minHeap
	 	// minHeap sorted by endpoint
	 	PriorityQueue<ArrayList<Integer>> minHeap = new PriorityQueue<ArrayList<Integer>>(new Comparator<ArrayList<Integer>>()
		{
				public int compare(ArrayList<Integer> one, ArrayList<Integer> two)
				{
					if(one.get(1) < two.get(1))
					{
						return -1;
					}
					else if(one.get(1) > two.get(1))
					{
						return 1;
					}
					return 0;
				}
		});
		// int leftMostEndPoint = times.get(0).get(1);
		int maxRentals = 0; // your default :-)
		// [minTime, maxTime] iteration : 
		// for(int start = lowestTime; start <= highestTime; ++start)	// this will take a lot
		minHeap.add(times.get(0));
	 	int ptr = 1;
		while(ptr < n)																																									
		{
			// Addition to minHeap tail
			// System.out.printf("%d\n", minHeap.peek().get(0));
			ArrayList<Integer> nextInterval = times.get(ptr);
			int nextIntervalLeft = nextInterval.get(0);
			if( nextIntervalLeft <=  minHeap.peek().get(0))
			{
				System.out.printf("Adding a new interval\n");
				minHeap.add(times.get(ptr));
			}
			else
			{
				// Remove from minHeap head
				while(minHeap.size() > 0)
				{
					ArrayList<Integer> leftMost = minHeap.peek();
					if(leftMost.get(1) <= nextIntervalLeft)
					{
						minHeap.poll();
					}
					else
					{
						break;
					}
				}
				// after removals : add the new interval
				minHeap.add(nextInterval);
			}
			++ptr;
			
			int curSize = minHeap.size();
			if(curSize > maxRentals)
			{
				maxRentals = curSize;
			}
		}
		return maxRentals;
	}
	
	// Expect to see : [[0,1],[0,2],[0,4],[1,3],[1,6],[2,9]]
	private void sortIntervals(ArrayList<ArrayList<Integer>> times)
	{
		Collections.sort(times, new Comparator<ArrayList<Integer>>(){
			public int compare(ArrayList<Integer> one, ArrayList<Integer> two)
			{
				if(one.get(0) < two.get(0))
				{
					return -1;
				}
				else if(one.get(0) > two.get(0))
				{
					return 1;
				}
				else
				{
					if(one.get(1) > two.get(1))
					{
						return 1;
					}
					else if(one.get(1) < two.get(1))
					{
						return -1;
					}
					else
					{
						return 0;
					}
				}
			}
		});
  }
}





