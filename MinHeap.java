Goal : Build a minHeap
6 functions :
- buildHeap
	> make many calls to insert
- remove/deletion ( get rid of the min ) 
	> siftDown
- insert
	> meat of the algo ( siftDown/siftUp )
	> siftUp
- peek ( read the top/minEl )
- siftDown ( insert | remove )
- siftUp   ( insert | remove )

DIAGRAM : 
	 
arr = [1,2,4,3,7,9,12]
					^
				   lastEleme
				   
		15
	   / \
	  2	  4
	 / \ / \
	1  7 9 12		<- complete tree
   /
  8
  
  8 -> 1 : 1 swp
  8 -> 2 : 2 swp
  8 -> 15 : 3 swps
  ...
  8 -> root : log_n
  Most swps at lower level
  
  1*n/4 + 2*n/8 + 3*n/16 ... + log_n * n/2^log_n
  
  

	insert(3)
	
	insert(3)
	
	6 element in heap : 1 ( top ) 2 ( next ) 3 ( last )
	
	
heaps : swap operations ( compare parent->child nodes )

		 2
	   /  \
	  7	   4
	 / \  / \
	3   8 9 12		

	   0 1 2 3 4 5 6
arr = [2,7,4,3,8,9,12]

Bottom-up heap construction :


Implement a MinHeap class that supports:

    Building a Min Heap from an input array of integers.
    Inserting integers in the heap.
    Removing the heap's minimum / root value.
    Peeking at the heap's minimum / root value.
    Sifting integers up and down the heap, which is to be 
	used when inserting and removing values.

Note that the heap should be represented in the form of an array.

If you're unfamiliar with Min Heaps, we recommend watching the Conceptual Overview section of this question's video explanation before starting to code. 


array = [48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41]

// All operations below are performed sequentially.
MinHeap(array): - // instantiate a MinHeap (calls the buildHeap method and populates the heap)
buildHeap(array): - [-5, 2, 6, 7, 8, 8, 24, 391, 24, 56, 12, 24, 48, 41]
insert(76): - [-5, 2, 6, 7, 8, 8, 24, 391, 24, 56, 12, 24, 48, 41, 76]
peek(): -5
remove(): -5 [2, 7, 6, 24, 8, 8, 24, 391, 76, 56, 12, 24, 48, 41]
peek(): 2
remove(): 2 [6, 7, 8, 24, 8, 24, 24, 391, 76, 56, 12, 41, 48]
peek(): 6
insert(87): - [6, 7, 8, 24, 8, 24, 24, 391, 76, 56, 12, 41, 48, 87]


Complexity ( of each function ) ?
Let N := #-elements in the heap

Test Cases :
(A) (*** ) [48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41]
(B) [4,1,2]
(C) [4,1,3,8,4,6,0,1,2]
	*** buildHeap/insert/peek/siftUp : 
(D)
(E)
(F)
(G)
(H)

Clarifications
(a) Test case can be empty ( not null )
(b) allowed helper method


O(nlgn)
O(N)



import java.util.*;

// Do not edit the class below except for the buildHeap,
// siftDown, siftUp, peek, remove, and insert methods.
// Feel free to add new properties and methods to the class.
class Program {
  static class MinHeap {
	  
	// Dynamic Array
    List<Integer> heap = new ArrayList<Integer>();

	// O(N) build heap?
    public MinHeap(List<Integer> array) 
	{
      heap = buildHeap(array);
    }

    // avoid extra space
  // Build bottom-up
    public List<Integer> buildHeap(List<Integer> array) 
	{
		if(array == null || array.size() == 0)
	      return new ArrayList<Integer>();
		for(Integer el : array)
			insert(el);
		return heap;
    }

	// Recursive
	// Can have left, not right ( completeTree property )
   // case of tie breakers
    public void siftDown(int currentIdx, int endIdx, List<Integer> heap) 
	{
		int sz = heap.size();
		int parent = heap.get(currentIdx);
		int leftIdx = currentIdx*2+1;
		int rightIdx = currentIdx*2+2;
		while(leftIdx < sz)
		{
			// Check boundary and compare
			int maxChildIdx = -1;
			if(leftIdx >= sz)
			{
				return;
			}
			if(leftIdx < sz)
			{	
				if(parent > heap.get(leftIdx))
				{
					maxChildIdx = leftIdx;
				}
			}
			if(rightIdx < sz)
			{
				// bug was here
				if(parent > heap.get(rightIdx) && heap.get(rightIdx) < heap.get(leftIdx))
				{
					maxChildIdx = rightIdx;
				}
			}
			if(maxChildIdx == -1)
			{
				return;
			}
			swp(currentIdx, maxChildIdx, heap);
			currentIdx = maxChildIdx;
			parent = heap.get(currentIdx);
			leftIdx = currentIdx*2+1;
			rightIdx = currentIdx*2+2;
		}
    }

	// faster ( iterative )
    public void siftUp(int curIdx, List<Integer> heap) 
	{
		while(curIdx != 0)
		{
			int offset = 2 - (curIdx % 2);	// even : offset = 2 : odd : offset = 1?
			int parentIdx = (curIdx - offset) / 2;
			if(heap.get(parentIdx) > heap.get(curIdx))
			{
				swp(parentIdx, curIdx, heap);
				curIdx = parentIdx;
			}
			else
			{
				break;
			}
		}
		
    }

    public int peek() 
	{
		if(heap.size() == 0)
			return 0;
		// System.out.printf("Heap size = %d\n", heap.size());
		return heap.get(0);
    }

    public int remove() 
	{
		if(heap.size() == 0)
			return -1;
		int min = peek();
		swp(0,heap.size() - 1, heap);	// swp, then remove!
		heap.remove(heap.size() - 1);
		siftDown(0,0,heap);
		return min;
    }

    public void insert(int value) 
	{
		int oldSize = heap.size();
		heap.add(value);
		siftUp(oldSize,heap);
    }
	  
	private void swp(int i, int j, List<Integer> heap)
	{
		int temp = heap.get(i);
		heap.set(i,heap.get(j));
		heap.set(j,temp);
	}
	
  }
}
