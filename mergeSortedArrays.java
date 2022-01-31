Goal : Provided with an array containing multiple arrays( nested Lists ) 
Sizes can be dynamic
Merge all those sorted arrays -> return the end sorted array



Input:
arrays = [
  [1, 5, 9, 21],
	 ^
	 P1
  [-1, 0],
	 ^
	 P2 <----- the new lowest value
  [-124, 81, 121],
	       ^
	       P3
  [3, 6, 12, 20, 150],
	 ^
	 P4
]

Order the values dereferneced at ptrs -> heap ( min )
Natural numbers sum #-comparisons 


Input:
arrays = [
  [1, 5, 9, 21],						<- 2
  [-1, 0],									<- 2
  [-124, 81, 121],					<- 2
  [3, 6, 12, 20, 150],			<- 2
]

O(1) Space
Same time complexity

	1->5->9->21
	^
	P1
	-1->0
	^
	P2
	-124->81->121
	^
	P3
	3->6-12->20->150
	^
	P4
	
	Result SLL
	No heap/other ADT permitted
	
	


log_2(#-arrays)

arrays = [
  [-124, -1, 0, 1, 5, 9, 21, 81, 121],
  [3, 6, 12, 20, 150],
]
~ hunch it is quadratic O(N^2)
~ reverse divide and conquer / tournament style


Output:
[-124, -1, 0, 1, 3, 5, 6, 9, 12, 20, 21, 81, 121, 150]

Clarifying questions : 
- input never null or empty
- all integral values
- can have duplicates both within and across arrays
- assume arrays have at least one element
- sorted in non-decreasing order / increasing  ( but not strict : <= )

Two ptr technique
standard merge sort algo -> zip two arrays -> one array
  [1, 2, 9, 21],
	               ^
	              P1
  [-1, 3],
	        ^
	        P2
	 
	 
PSEUDOCODE : 
	
	minHeap = ()
  for each list in arrays
	  readPtr = 0
		minHeap add new triplet(list_idx, readPtr, val[l_idx])	# Custom comparator
	results = ()
	while minHeap is not empty :
		P <-- pop min triplet from heap
		append value(P) to results
		if readPtr + 1 is within bounds of arr being read:
			Triplet nextEl = (list_idx(P), readPtr + 1, val[readPtr+1])
			minHeap add nextEl
	
	return results
		

	 

COMPLEXITY
Let M := #-arrays
Let N := max length(array)
TIME = O(MN*lg(M))	<- remember heapify
	O(#-els_visited * heapify_time)
SPACE = O(M) + O(MN)	<-- heap and size of results for return
No call stack space. Pure iterative approach

TEST CASES
(A)
[
  [1, 1, 1, 1],
  [2, 2],
  [3,3,3]
]
(B)
[
  [1,1,2,3,5]
]
(C)
[
  [1]
]
(D)
[
  [1],
	[4],
	[3],
	[9],
	[7],
	[0],
	[6]
]
(E)
[
  [1,3,4,5,5,7,8,8,8,8,8,10,13,14,14],
  [0,3,4,4,4,7,7,7,7,7,7,10,13,13,14]
]
(F)
[
  [-1,-3,-4,-5,-5,-7,-8,-8,-8,-8,-8,-10,-13,-14,-14],
  [0,3,4,4,4,7,7,7,7,7,7,10,13,13,14]
]
<---- should have failed. AlgoExpert.IO wrong here!
(G)
[
  [-14,-14,-13,-10,-8,-8,-8,-8,-7,-5,-5,-4,-3,-3,-1],
  [0,3,4,4,4,7,7,7,7,7,7,10,13,13,14]
]


If input were a SLL and not an ArrayList
	Check if pointer equals null
	




import java.util.*;

class Program 
{
	
	static class Triplet
	{
		public int lIdx;
		public int rPtr;
		public int val;
		
		public Triplet(){}
		public Triplet(int lIdx, int rPtr, int val)
		{
			this.lIdx = lIdx;
			this.rPtr = rPtr;
			this.val = val;
		}
	}
	
  public static List<Integer> mergeSortedArrays(List<List<Integer>> arrays) 
  {
		// New Object instantiated AND hidden in constructor
		List<Integer> results = new ArrayList<Integer>();
		PriorityQueue<Triplet> minHeap = new PriorityQueue<Triplet>(
			new Comparator<Triplet>() {
				public int compare(Triplet e1, Triplet e2)
				{
					if(e1.val < e2.val)
						return -1;
					else if ( e1.val > e2.val)
						return 1;
					return 0;
				}
			}
		);
		int m = arrays.size();
		// O(M*lg(M))
		for(int i = 0; i < m; ++i)
		{
			Triplet initial = new Triplet(i, 0, arrays.get(i).get(0));	// making assumption
			minHeap.add(initial);
		}
		
		while(minHeap.size() > 0)
		{
			Triplet minTrip = minHeap.poll();
			results.add(minTrip.val);
			int cLIdx = minTrip.lIdx;
			int cRPtr = minTrip.rPtr;
			int cLSize = arrays.get(cLIdx).size();
			if(cRPtr + 1 < cLSize)
			{
				Triplet nextEl = new Triplet(cLIdx, cRPtr + 1, arrays.get(cLIdx).get(cRPtr+1));
				minHeap.add(nextEl);
			}
		}
		
    return results;
  }
}









