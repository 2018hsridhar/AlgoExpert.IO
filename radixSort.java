/*
Array of non-neg integers
Returns the sorted version of that array

Based on the radixes of each value as well
Is it a comparative pairwise sorting algorithm? -> no 
Does it preserve order of elemenets as encountered -> yes

Linked List in the input ( for binning -> akin to a sharding/hash strategy )
#-radixes is guaranteed to be a fixed number [ 0-9 ]
Binned originally by their set of predecing digits as well

Complexity : 
Let N := #-elements in the input array
Let K := length(longest input)
Radix :=> key in the has ( known to be a constant -> 9 : M )
Time = O(NK)
Space = O(MN) = O(9N) = O(N) 

-> Let us vary the radices @ the end
TEST BENCH : 
(A) [1,5]
(B) [1,5,12,23]
(C) [0,1,12,23,344,455]
(D) [12,23]
(E) [344,455]
(F) [], NULL
(G) [9876], [1]
[ * PROVIDED * ] (H) [8762, 654, 3008, 345, 87, 65, 234, 12, 2]
(I) [1,11]
	FAILING CASE
(J)

EXTENSION QUESTIONS :: 

(1) What if a sort on a dictionary or a language with a highly finite-sized alphabet or infinite-sized alphabet is desired?
	-> Does this impact the reasoning of compelxity?>
	-> Does this impact our code as well?

(2) 
	
	


*/
import java.util.*;

class Program 
{

	// Unable to code to an interface
  public ArrayList<Integer> radixSort(ArrayList<Integer> array) 
	{
		// Need terminating condition : length(longest radix) : precomputation step
		ArrayList<Integer> output = new ArrayList<Integer>();
		int lengthLongestRadix = getLongestRadix(array);
		// System.out.printf("Length longest radix = %d\n", lengthLongestRadix);
		performSort(array,lengthLongestRadix);
		return array;
  }
	
	// Hashmap instantiation could be precomputed in a seperate method, IFF radix keys are known ahead of schedule!
	// Mod by 10 extracts the digit => compute that dividend after each growing divisor on the quotient
	// The first step requires our hashmap to be populated though
	// Or can we avoid hashmap, and use a customer comparator instead? -> NOT tenable since this is it's own comparison sort though
	// 
	private void performSort(ArrayList<Integer> array,int longestRadix)
	// private void performSort(ArrayList<Integer> array,int longestRadix, ArrayList<Integer> output)
	{
		int n = array.size();
		HashMap<Integer,List<Integer>> bins = new HashMap<Integer,List<Integer>>();
		// Can remove and add back to the end during each hashmap iteration after the first binning takes place
		for(int r = 0; r < longestRadix; r++)
		{
			System.out.printf("\ntesting radix = %d\n", r);
			for(int i = 0; i < n; ++i)
			{
				int dividend = array.get(i);
				int divisor = (int)Math.pow(10,r);
				int quotient = dividend / divisor;
				int radix = quotient % 10;
				// System.out.printf("For elem = %d \t radix = %d\n", dividend, radix);
				
				if(!bins.containsKey(radix))
				{
					ArrayList<Integer> newBin = new ArrayList<Integer>();
					newBin.add(dividend);
					bins.put(radix, newBin);
				}
				else
				{
					List<Integer> balls = bins.get(radix);
					balls.add(dividend);
				}
			}
			// Write HM back to input arraylist
			int wIdx = 0;
			Set<Integer> keys = bins.keySet();
			Iterator<Integer> keyItr = keys.iterator();
			while(keyItr.hasNext())
			{
				Integer key = keyItr.next();
				List<Integer> balls = bins.get(key);
				Iterator<Integer> ballsItr = balls.iterator();
				while(ballsItr.hasNext())
				{
					int val = ballsItr.next();
					// System.out.printf("Val = %d\n", val);
					array.set(wIdx, val);
					// System.out.printf("@ wIdx = %d \t val = %d\n", wIdx, val);
					wIdx++;
				}
			}
			bins.clear();
		}
	}
	
	// bins and balls analogy
	
	// O(N), O(1)
	// Convert to a string and get length
	private int getLongestRadix(ArrayList<Integer> array)
	{
		int radixLen = 0; // in case of empty array ( else dflt = 1 ) 
		Iterator<Integer> itr = array.iterator();
		while(itr.hasNext())
		{
			String el = itr.next() + ""; // implicit cast : explicit preference
			radixLen = Math.max(radixLen, el.length());
			 // runs out of memory in an infinite loop
		}
		return radixLen;
	}
	
}
