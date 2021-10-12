/*
k is a non-neg integer ( can be zero though ) 
k-sorted array of integers ( guaranteed bounds here from [INT_MIN, INT_MAX] )
Return sorted version of array
Can sort in place or allocate new array obj
	in-place mod will be the challenge too

Every elem is @ most ( strictness ) k positions away from its sorted position

Must sort faster than standard comparison sorting algorithms - O(nlog(n))

Complexity 
Let N := len(array), and k stand as is
Time = O(nlog(k)) or O(n) [ what we aim for ]
Space = O(k) or O(n) or O(1). 
	-> most space complexities are not logarithmic, save for the sizes of heaps
	
Edge case testing : 
(A)
(B)
(C)
(D)
(E)
(F)

Can we come up with a brute force solution of O(nk)?
	Is an issue as k can equal n as well too
	Expect folks to forgot the swp operation
	
*/


import java.util.*;

class Program {

  public int[] sortKSortedArray(int[] array, int k) 
	{
		if(array == null || array.length <= 1 || k == 0)
			return array;
		
		int n = array.length;
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		int wIdx = 0;		// do not try fancy (wIdx -k - 1) stuff here. Messes up with weird k as well
		// Your modulus was your bug boyo!
		
		// Order of size, then add/remove checks, matters a lot as well too
		for(int i = 0; i < n; ++i)
		{
			if(minHeap.size() == k+1 ) // makes sense when i == k only ( e.g. i = 3, k = 3 )
			{
				int curMin = minHeap.poll();
				array[wIdx++] = curMin;
			}
			minHeap.add(array[i]);
		}
		
		// Expect key gotcha at a non-empty iterative data structure, with element still remaining as well too
		// Fails at test case of only 2 elemens - [0,1] - and k = 1 here
		while(minHeap.size() > 0)
			array[wIdx++] = minHeap.poll();
		// while(wIdx < n )
			// array[wIdx++] = minHeap.poll();
    return array;
  }
}
