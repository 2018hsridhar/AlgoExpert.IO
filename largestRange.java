Write a func that takes an array of ints -> returns array len = 2
Represent largest range of ints contained in the array input

First output = first # in the range
Second  output = last # in the range

Range = set of #'s, comes right after each other in the set of real integers


	Input : Numbers need NOT be sorted or adjacent. 
	Assume only ONE largest range ( one valid solution ) 
	Output array = [2, 6] => {2, 3, 4, 5, 6} : length = 5 : is a contiguous set here
	Output array = actual values
	Can we mutate the input -> is a valid approach
	Can array vals be negative - yes
	Can array vals be outside the range of the array length ( len = 11, can have values like 20 ) - yes
	Can we have duplicate numbers - yes
	INT_MIN, INT_MAX = element range
	
Sample Input
array = [1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6]
				 0	 1	2	 3	4		5	 6	7	 8	 9	10	11
				 					 ^									 ^
				 PTR --->	
				 SWPS ---> input
				 
				 				Keep track of the longest RANGE of those indices
				 				====================== ( a[i], a[i+1]) 
Sorted array  = [0, 1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 15]									 
									
									Test in range [-100,100]
									Min,max element in the hashmap : where to iterate from (range )
									[0,15]
										[-100,-1] : we don't have any place to jump
										[0,6] -> .we can make a jumps
										7 : no jump possible -> collect range max -> keep testing
										[7,9] -> no jumps
										[10,11] -> jumps
									7->8 : 8 is not a key in the hash
									7 -> {6,8} : you could update 2 hashmap entries : 6 -> 7, 7->8
									SLLs in the hashmap
									Element value itself denotes as end 
									15=>15 ( 15=>16 : we have a seq : 15=>other elem : we are done )
									
						HASHMAP ( unordered keys ) 
							{
								5 => 6				T
								4 => 5				T
								3 => 4				T
								7 => __				F
								12 => __			F
								6 => 7				T
								11 => 12			T
								10 => 11			T	
								15 => __			F	
							}
							INT = key, BOOL = val
							Starting key makes this a bit harder ( unless we had a minimal key )
							Jumping across the keys ( lack order )
							Suppose the first I start the search = 5
							{3,4,5,6,7}
							5->6->7
							3->4 ( can go to a back elem) : test two directions here
 
 -> all these elements get hashed
 																			 (discontinuity )
	======= interval #1 ====									======= interval #2 ====
[INT_MIN,INT_MIN+1,INT_MIN+2,...,-3,-2,___,0,1,2,3,4,5,....,INT_MAX]
Iterate over (INT_MAX-(INT_MIN) # elements in a hashmap )

Sample Output
[0, 7]

COMPLEXITY
N := #-array elements
TIME = O(NLOGN) + O(N) = O(NLogN)
SPACE = O(1) ( IMP & EXP )

BETTER : 
TIME = O(2N) = O(N)
SPACE = O(N)


How to avoid a sort solution?
Better : O(N), O(logN) [ binary search ] : one or two pass approach

Use a heap : O(NlogN).

Data structure : stacks, queues, trees, hashmaps, hashsets, BST, heaps/PQ, linked lists
Try a hashmap
	(K,V) mappings needed
	Naivest pairing : index -> values
	unordered_map, ordered_map ( tree map ) 
	
Check if I have an element (+1) or element (-1) 
	insert 2 into set : 2-1, 2+1

Know the min and max : O(N) - one linear scan : the theoretical largest range
array = [1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6]
Set = {1,3,11}
	insert(0). We don't have -1, but we have 1
	{0,1,3,11} -> [0,1] at least
	insert(15) -> don't have 14, 16
	insert(5)
	{0,1,3,5, 11,15}
	insrt(2) : we have 1,3
	[0,3] is now doable
	{0,1,2,3,5,11,15}
	
	array = [0,11,1,12,2,13,3]
	[0,2] exists as well as [11,13]
	=> now it's [0,3] : respond with this range
	=> keep a list of ranges too
	
	array = [11,15,12,14,13]
					 ==========
	Initial : [11,12] OR [14,15]
	{11,15,12,14} => 13
	[11,15] => now return this
	
	
	Can have two competing ranges?
	Ordering suggests a need for range.
	Save all integers in a hashmap
	
Can remove hashmap/hashset elements as we iterate



import java.util.*;

class Program {
  public static int[] largestRange(int[] array) 
	{
		
		// [1] Initialize the hashmap
		HashMap<Integer,Boolean> visited = new HashMap<Integer,Boolean>();
		int n = array.length;
		for(int i = 0; i < n; ++i)
		{
			int elem = array[i];
			if(!visited.containsKey(elem))
			{
				visited.put(elem,false);
			}
		}
		
		// [2] Perform hashmap range expansion
		int rangeSize = 1; // min range
		int[] range = new int[]{-1,-1};
		Set<Integer> keySet = visited.keySet();
		Iterator<Integer> itr = keySet.iterator();
		while(itr.hasNext())
		{
			Integer key = itr.next();
			if(visited.get(key) == true)
			{
				continue;
			}
			else
			{
				boolean isExpandable = true;
				visited.put(key,true);
				int keyPlusOne = key;
				int keyMinusOne = key;
				int[] curRange = new int[]{keyMinusOne,keyPlusOne};
				int curSize = 1;
				while(isExpandable)
				{
					keyPlusOne = keyPlusOne + 1;
					keyMinusOne = keyMinusOne - 1;
					boolean hasKeyPlus = keySet.contains(keyPlusOne) && ( keySet.contains(keyPlusOne) == false );
					boolean hasKeyMinus = keySet.contains(keyMinusOne) && ( keySet.contains(keyMinusOne) == false );
					if(hasKeyPlus)
					{
						curRange[1] = keyPlusOne;
						visited.put(keyPlusOne,true);
						curSize++;
					}
					if(hasKeyMinus)
					{
						curRange[0] = keyMinusOne;
						visited.put(keyMinusOne,true);
						curSize++;
					}
					isExpandable =  hasKeyPlus || hasKeyMinus;
				}
				if(curSize > rangeSize)
				{
					range[0] = curRange[0];
					range[1] = curRange[1];
					rangeSize = curSize;
				}
		}
		}
		return range;
  }
}
