CLARIFICATIONS
Akin to Towers of Hanoi 

3 dimensions : sort by only one dimension
Given an non-empty array of arrays
Each subarr has 3 ints - represents a disk - [ width, depth, height ] = the 3 integers
Goal : stack up the disks and maximize the total height
Restriction : top(disk) should have a ( w, d, h ) > bottom(disk ) ( w, d, h ) 
	- not a strict inequality
	- can we have duplicate disks : assume no for now
	- can NOT rotate the disks
	- Only one valid solution exists 
	- not returning maximal height ( integral ) -> return list ( store the indices )
	- {l,w,h} all differ : no repetitions
	- only positive numbers
	
Return an array of array, of the permutation of disks, that maximizes the height


Sample Input
-> is a fixed height ( we do not toggle here )

disks = [[2, 1, 2], [3, 2, 3], [2, 2, 8], [2, 3, 4], [1, 3, 1], [4, 4, 5]]
						0						1						2					3					4					5
	
=> sort by heights of disks
disks = [[1, 3, 1], [2, 1, 2], [3, 2, 3], [2, 3, 4], [4, 4, 5], [2, 2, 8]]
						^ 																^
					IDX_PTR														combintation met : try from here
						
			
Sample Output
[[2, 1, 2], [3, 2, 3], [4, 4, 5]]
// 10 (2 + 3 + 5) is the tallest height we can get by
// stacking disks following the rules laid out above.

Smallest to largest disk stack
Goal : maximize the height here

Sort, DP, Recursion ( maximal subset ), Backtracking : stacking cuboids
https://leetcode.com/problems/maximum-height-by-stacking-cuboids/
-> power set
-> Sort by heights ( we always want to increment that )

DP COMPLEXITY
Some summation maximized over the subproblems
Let N := #-disks / len(array)
TIME = O(NLOGN) + O(N^2) = O(N^2) ( POLY ) 
SPACE = O(2*N) = O(N) ( POLY ) ( EXP ) O(1) ( IMP ) 

TEST CASES
(A) disks = [[1, 3, 1], [2, 1, 2], [3, 2, 3], [2, 3, 4], [1, 1, 5], [2, 2, 8]]
								^																&						X
							 PTR															use that in summation
(B) [[1, 3, 1]]
		0 ( maxHeight = 1 )
(C) [[1,1,1],[2,2,2],[4,4,4]]
	=> 7 ( entire list ) 
(D) [[1,2,4],[2,1,5],[4,5,6],[5,4,6]]
	=> maxH = 10 ( yep ) 
(E) ( quick test ) 


Optimal Substructure Property : 

	* just for height * 
	DP(i) = 
		{
			i.h, if i = n - 1,
			i.h + Math.max_{j=i+1}{n-1}(DP(j)), where i <= n-1 and l(j) > l(i) & w(j) > w(i)
		}


INVALID CASES
(A) [[1, 3, 1], [2,4,1],[6,2,1]]	=> 
	 {0,1,2} ( maxHeight = 1 ) 




import java.util.*;

class Program 
{
	// disks has been mutated
  public static List<Integer[]> diskStacking(List<Integer[]> disks) 
	{
  	if(disks == null || disks.size() == 0)
		{
			return new ArrayList<Integer[]>();
		}
		
		// [1] Sort step : pass in comparator function 
		Collections.sort(disks, new Comparator<Integer[]>(){
			public int compare(Integer[] dOne, Integer[] dTwo)
			{
				if(dOne[2] < dTwo[2])
				{
					return -1;
				}
				else if ( dOne[2] > dTwo[2])
				{
					return 1;
				}
				else
				{
					return 0;
				}
			}
		});
		
		// [2] DP step
		int n = disks.size();
		
		int globalMaxH = Integer.MIN_VALUE;
		int globalMaxIdx = -1;
		int[] maxHeights = new int[n];
		int[] indices = new int[n];
		
		for(int i = n - 1; i >= 0; --i)
		{
			// Always assume just stack cur elem by itself
			Integer[] curDisk = disks.get(i);
			maxHeights[i] = curDisk[2];
			indices[i] = -1; // -1 denotes the end of an index traversal
			
			// Check all j > i here
			// Get largest possible stack with current element
			int curH = curDisk[2];
			int nextDiskIdx = -1;
			for(int j = i + 1; j < n; ++j)
			{
				// Check disk req here ( length, width ) 
				Integer[] jth_disk = disks.get(j);
				if(jth_disk[0] > curDisk[0] && jth_disk[1] > curDisk[1] && jth_disk[2] > curDisk[2])
				{
					if(curDisk[2] + maxHeights[j] > curH)
					{
						curH = curDisk[2] + maxHeights[j];
						nextDiskIdx = j;
					}
					// curH = Math.max(curH, curDisk[2] + maxHeights[j])
					// nextDiskIdx = j
				}
			}
			// System.out.printf("Next disk idx = %d for ith disk = %d\n", nextDiskIdx, i);
			maxHeights[i] = curH;
			indices[i] = nextDiskIdx;
			
			// See if largest performed better than the global
			if(maxHeights[i] > globalMaxH)
			{
				globalMaxH = maxHeights[i];
				globalMaxIdx = i;
			}
		}
		
		// System.out.printf("Global max height = %d AND global max Idx = %d\n", globalMaxH, globalMaxIdx);
		ArrayList<Integer[]> results = new ArrayList<Integer[]>();
		while(globalMaxIdx != -1)
		{
			results.add(disks.get(globalMaxIdx));
			globalMaxIdx = indices[globalMaxIdx];	// get us to the right
		}
    return results;
  }
}
