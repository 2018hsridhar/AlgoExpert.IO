/*
Goal : Detect if jumps engender a cycle
+x : jump x indices fwd
-y : jump y indices bkwds
Array is circular as well here

The cycle can be started at any index here s well
Each array element is visited exactly once as well

Assume the jumps can be duplicated ( -4,-4)
Assume integers are reasonable ( just modulo here ) 
2

Brute-force solution
Let N := number of elements in our array
Time = O(N)
Space = O(N)
Must be N, at worst case, if we traverse each node on the visit for the single cycle
Perhaps a O(1) solution is possible, BUT, unsure how to demark the differences in the nodes encountered OR not as well too!


TEST CASES : 
(A) [2,3,1,-4,-4,2]
	TRUE
(B) [1,1,1,1,1]
	TRUE
(C) [0,0,0,0,0]
	FALSE
(D) [1,1,-1,2,-1,-1]
	FALSE
	Two subcycles here : does NOT encompass traversal over all elements though!
(E) [0,0,0]
	FALSE
	each cycle is its own node here ( self-looping cases ) 
(F) [1], [3]
	FALSE ( it just moves forward, no matter the int value here )
(G)"array": [1, 1, 1, 1, 2]
	=> 15/16 : the one test case NOT working as expected!
(H)
(I)

*/


import java.util.*;

class Program 
{
  public static boolean hasSingleCycle(int[] array) 
	{
		if(array.length == 0 || array == null)
			return true;
		boolean hasSingCycle = false;
		int n = array.length;
		Set<Integer> visited = new HashSet<Integer>();
		int start_idx = 0;
		int cur_idx = start_idx;
		int freq_hit_start_idx = 0;
		int next_idx = 0;
		while(freq_hit_start_idx != 2)
		{
			// System.out.printf("Cur idx = %d\n", cur_idx);
			visited.add(cur_idx);
			if(cur_idx == start_idx)
				freq_hit_start_idx++;
			
			next_idx = (((cur_idx + array[cur_idx]) % n)+n)%n;
				// this nextIdx will trip you up if values are well negative as well!
			if(visited.contains(next_idx))
				break;
			cur_idx = next_idx;
		}
		hasSingCycle = (visited.size() == n && next_idx == start_idx);
    return hasSingCycle;
  }
}
