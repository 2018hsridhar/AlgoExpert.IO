/*
Prompt : Give a set of queries represneting wait time of tasks
Greedy algorithm sorting input
Return the minimum wait time of tasks
	-> min_wait_time = tasks ready to execute
	[5] => takes 0 time ( not returning 5 here ) 

queries = [5, 1, 4]
[0 5 (5+1)]
sum = 11

Wait time = time for last task to execute
[1, 4, 5]
[0, 1, (1+4)] : sum = 6 = 1 + (1+4)
	=> prefix sums/running sums


queries = [3, 2, 1, 2, 6]
17

[1, 2, 2, 3, 6]
[0, 1, (2+1), (1+2+2), (1+2+2+3)]
[0, 1, 3, 5, 8]
8+(5+3)+1 = 17


Tasks execute in sequence here and each element represents how long it takes to run the individual task.
Queries array => can we change the order, or execute in that specific order.
	-> we can change the order
How big can the array get? [minBound, maxBound ] : no limit, but let us assume it is reasonable.

Can a wait_time for a task take 0 seconds? 
	Always a positive integer.
	
Always given a non-empty array with a single task
Not unique positive integers

Edge cases : 
(a) [9], [5] => 0
(b) [5,5,5]
(c) [1,2,3,4,5]
(d) [5,4,3,2,1]
(e) [1,2,1,2,1,2]
(f) [8 9]
(g) [5 3 7 1 20 8]


Assume built-in library function for sort - O(NlogN) time, O(N) space
Complexity : 
Let N := number of elements in input array
Time = O(NlogN) + O(N) = O(NlogN) + O(N) = O(NlogN)
Space = O(N) + O(1) = O(N)
Compelxity outside standard sort is => [O(N), O(1)]

Mutate input array in place here

Running sum = prefix sum ( summation building up ) 

PSEUDO CODE : 
minWaitTime(queries) :
	sort(queries)
	n = len(queries)
	prefixSum[n]; // zero-initialize this
	for(int i = 1; i < n; ++i) // 0 time to execute first task anyways
		prefixSum[i] += queries[i-1] + prefixSum[i-1]
	minTime = 0;
	for(int i : prefixSum)
		minTime += prefixSum[i];
	return minTime



queries 							[1, 2, 2, 3, 6]
prefix_sum						[0, 0, 0, 0, 0]

n = 5
				queries
				[1,2,2,3,6]
idx 		prefix_sum		COMP
1				[0,1,0,0,0]		pSum[1] = queries[0] + pSum[0]
2				[0,1,3,0,0]		pSum[2] = queries[1] + pSum[1]
3				[0,1,3,5,0]		pSum[3] = queries[2] + pSum[2]
4				[0,1,3,5,8]		pSum[4] = queries[3] + pSum[3]

Return (pSum[1] + ... pSum[4])
idx 		prefix_sum		COMP
										  pSum[0] = 0
1				[0,1,0,0,0]		pSum[1] = queries[0] + pSum[0] = queries[0] + 0
2				[0,1,3,0,0]		pSum[2] = queries[1] + pSum[1] = queries[1] + (queries[0] + pSum[0]) = queries[1] + queries[0]
3				[0,1,3,5,0]		pSum[3] = queries[2] + pSum[2] = queries[2] + (queries[1] + pSum[1]) = queries[2] + (queries[1] + queries[0])
4				[0,1,3,5,8]		pSum[4] = queries[3] + pSum[3] = queries[3] + (queries[2] + pSum[2]) = queries[3] + (queries[2] + (queries[1] + queries[0]))

Total sum = (queries[0]*4 + queries[1] * 3 + queries[2] * 2 + queries[3] * 1)

Sort operation is NOT avoidable here
No way to do better than these bounds
*/


import java.util.*;

class Program_minimumWaitingTime {

  public int minimumWaitingTime(int[] queries) {
		int minTime = 0; // default value
		Arrays.sort(queries);					// [T,S] = [O(nlogn), O(N)]
		int n = queries.length;			
		for(int i = 1; i < n; ++i)			// O(N) time
			minTime += i * queries[(n-1)-i];
		return minTime;
  }
}
