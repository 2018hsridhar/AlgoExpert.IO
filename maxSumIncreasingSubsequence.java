/*

Input := non-empty array of integers
Ret greatest sum generatable from a STRICTLY-INCREASING subseq in the array
Subseq elems need not have adjacency, but must maintain their ordering
Single number and array itself :=> both valid subsequences
Always have at least one increasing subseq ( can be the elem itself ). Start at Int.MIN_VALUE

STRATEGIES : 
- is an optimization problem
- def open to DP solving here

COMPLEXITY ( BUDP ) 
Let N := #-elements in the array
TIME = O(N^2)
SPACE = O(N)
Store indices that lead to max summations as well ( or -1, if not a max summation too ) 
-> keep iterating as we traverse 

Could we get a O(N) Time, O(1) Space solution? Who knows?


TEST CASES : 
(A) [10] 
	=> 10
(B) [10,20,30]
	=> {60, [10,20,30]}
		X : investigate again!
(C) [10,20,30,-1]
	=> {60, [10,20,30]}
		X : investigate again!
		Tested a drop here
(D) [-10,-1,-6,-4,-8,-1,0,1,2]
	=> test this sequence ( alternate terms ) 
(E) (*** provided *** ) [10, 70, 20, 30, 50, 11, 30]
(G) [1,1,1,1,1]
	=> [1,[1]]

*/
import java.util.*;

class Program 
{
	// In DP, they are testing how well you split those local subproblems, from the global
	// overarching problem, as well as your order of operations here for computation
  public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) 
	{
		// The max sum always equals the elem itself anyways
		int n = array.length;
		int glblSum = Integer.MIN_VALUE;
		int glblIdx = -1;
		int[] sums = new int[n]; // zero-initialized
		int[] indices = new int[n]; // zero-> initialized : set to -1 later
		for(int i = n-1; i >= 0; i--)
		{
			int curVal = array[i];
			int curMax = array[i];
			int nxtIdx = -1;
			for(int j = i+1; j < n; ++j)
			{
				int nxtVal = array[j];
				if(curVal < nxtVal)
				{
					int possibleMax = array[i] + sums[j];
					if(possibleMax > curMax)
					{
						curMax = possibleMax;
						nxtIdx = j;
					}
				}
			}
			sums[i] = curMax;
			indices[i] = nxtIdx;
			
			if(curMax > glblSum)
			{
				glblSum = curMax;
				glblIdx = i;
			}
		}
		
		ArrayList<Integer> sumList = new ArrayList<Integer>();
		sumList.add(glblSum);

		ArrayList<Integer> idxList = new ArrayList<Integer>();
		while(glblIdx != -1)
		{
			idxList.add(array[glblIdx]);
			glblIdx = indices[glblIdx];
		}

		// Write your code here.
		return new ArrayList<List<Integer>>() {
		{
        add(sumList); // Example max sum
        add(idxList); // Example max sequence
      }
    };
  }
}
