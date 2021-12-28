Task Assignment : 

Given an integer k := #-workers
Given an array of positive ints :=> duration fo tasks to have workers completes
Workers can work on ONE task at a time
Must finish two task thought 
2*k tasks
Tasks have time durations -> always non negative ( equal 0 : edge case )
Can have duplicate values

Returns optimal assignment of tasks to each worker : wrap up as fast as possible
Return a list of pairs here : tasks each worker completes in format of ( task_1, task_2 )
Ordering does not matter here

k >= 1
Tasks = 2*k ( never changes ) 

COMPLEXITY
Let N := #-tasks ( equal to 2*k ) 
TIME = O(NLGN) + O(N) = O(N)		( POLY ) 
SPACE = O(N) ( exp ) O(1) ( imp ) 

TEST CASES :

HashMap(N)
	{
	 1 -> {0,4}
	 3 -> {1,3}
	 4 -> {5}
	 5 -> {2}
	}
	Pop off the first value -> shifting the HM
	HM<Integer,Stack<Integer>>
	
k = 3
sort =  [1,	1, 3, 3, 4, 5]
tasks = [1, 3, 5, 3, 1, 4] := values
				 0	1	 2	3	 4	5	 := indices
[
  [0, 2], // tasks[0] = 1, tasks[2] = 5 | 1 + 5 = 6
  [4, 5], // tasks[4] = 1, tasks[5] = 4 | 1 + 4 = 5
  [1, 3], // tasks[1] = 3, tasks[3] = 3 | 3 + 3 = 6
] // The fastest time to complete all tasks is 6.

Assignment based on the MINIMAL fastest time here across all of the workers
Workers work on tasks in parallel -> not in serial

Strategies : Constraint optimization problem -> DP, Greedy
					Two tasks assignment -> two pointer approach
					Greedy -> sorting the input as well
					Exhaustively search all valid assignments ~ factorial/exp : O(n!) or O(base^n)
				 === === ===
Tasks = [1,1,3,3,4,5]
				     ^ ^
				     P1	P2
				 half the elements ~ O(n/2) = O(N)
				 
				1+1 = 2
				3+3 = 6
				4 + 5 = 9
				(3->5) = 8
				(3->4) = 7 	-> I can do better than the current assignment
				
				Greedy choice : min and max of the input arrays at each stage
				
(A) k = 2, [1,1,1,1]
		[[1,1],[1,1]]
(B) k = 3, [1,2,3,4,5,6]
	 [[1,6],[2,5],[3,4]]
(C) k = 1, [1,10]
(D) k = 5 [1,1,1,1,1,1,1,1,1,4]
					1 -> {9 indices}
					4 -> {1 index }
					
PSEUDOCODE : 
	
	// Always even in length
	n := len(tasks)
	// O(nlgn) ( assuming a merge sort or other sort algo )
	sort the tasks array in increasing order
	ptr1 = 0
	ptr2 = n-1
	assingment = ()	// Keep in mind space complexity here
	// O(N)
	while(ptr1 <= ptr2)
		minTask = tasks[ptr1]
		maxTask = tasks[ptr2]
		make new list<minTask,maxTask> = pairing
		append pairing to assignment
		ptr1++
		--ptr2
	return assignment

Tasks never empty or null : has @ least two elements
Wants indices ( in the original listing ) 


import java.util.*;

class Program {

	
	private Map<Integer,Stack<Integer>> precomputeMap(List<Integer> tasks)
	{
		HashMap<Integer,Stack<Integer>> valIndices = new HashMap<Integer,Stack<Integer>>();
		int n = tasks.size();
		// O(N), O(N)
		for(int i = 0; i < n; ++i)
		{
			int val = tasks.get(i);
			if(valIndices.containsKey(val))
			{
				valIndices.get(val).push(i);
			}
			else
			{
				valIndices.put(val, new Stack<Integer>());
				valIndices.get(val).push(i);
			}
		}
		return valIndices;
	}

	
  public ArrayList<ArrayList<Integer>> taskAssignment(int k, ArrayList<Integer> tasks) 
	{
		// [1] Precomputation -> do it before we sort
		Map<Integer,Stack<Integer>> valIndices = precomputeMap(tasks);
		Collections.sort(tasks);	// default sort
		ArrayList<ArrayList<Integer>> assignment = solveAssignment(valIndices, tasks);
		return assignment;
	}
	
	private ArrayList<ArrayList<Integer>> solveAssignment(Map<Integer,Stack<Integer>> valIndices, ArrayList<Integer> tasks)
	{
		// [3] ITERATIVE Algorithm
		int n = tasks.size();
		int ptr1 = 0;
		int ptr2 = n-1;
    ArrayList<ArrayList<Integer>> assignment = new ArrayList<ArrayList<Integer>>();
		while(ptr1 <= ptr2)
		{
			int minTask = tasks.get(ptr1);
			int maxTask = tasks.get(ptr2);
			int minIdx = valIndices.get(minTask).pop();
			int maxIdx = valIndices.get(maxTask).pop();
			ArrayList<Integer> pairing = new ArrayList<Integer>();
			pairing.add(minIdx);
			pairing.add(maxIdx);
			assignment.add(pairing);
			++ptr1;
			--ptr2;
		}
		return assignment;
  }
}
