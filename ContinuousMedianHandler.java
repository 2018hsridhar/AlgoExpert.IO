/*

Continuous insertion of numbers, via insert method
O(1) retrieval of medians as well
	Either top of data structure
	or just an immediate calculation
	
	Given a sorted array -> median is middle ( odd ) or avg mid two elems ( even ) 
	Try to leverage this and dynamic insertion order as well
	
COMPLEXITY : 
Let N := number elements
Insertion time : TIME = O(log(n)) + O(n) = O(n) [ bSearch index + shift elements ]
SPACE = O(1) [ not sure about array resizing underneath ]

TEST CASES : 
(A) insert(1) findMedian
(B) insert(1) insert(0) insert(-1) findMedian
(C) insert(1) insert(1) findMedian insert(1) findMedian insert(1) findMedian
(D) insert(1) insert(3) insert(6) insert(10) insert(5) findMedian 
	^ not working. insertion failing at insert(5) here. Investigate further!
(E) insert(1) insert(3) insert(6) insert(10) insert(2) findMedian 
(F) insert(0) insert(3) insert(6) insert(10) insert(2) findMedian insert(9) insert(1) insert(-1) insert(100) findMedian 
(G) insert(0) insert(3) insert(6) insert(10) insert(2) findMedian insert(9) insert(1) insert(-1) insert(100) insert(6) findMedian 

^ Your test suite provded comprehensive enough here and helped you catch bugs
^ Way to go!

Gotcha : return ONCE the add wraps up here as well!
Their solution is the double heap technique ( max heap and min heap )
But this also works too, and avoids usage of the heap. Is a valid secondary solution!

*/

import java.util.*;

// Do not edit the class below except for
// the insert method. Feel free to add new
// properties and methods to the class.
class Program {
  static class ContinuousMedianHandler {
    double median = 0;
		// is initialization here even permitted to begin with?
		ArrayList<Integer> nums = new ArrayList<Integer>();

    public void insert(int number) 
		{
			if(nums.size() == 0)
			{
				// System.out.printf("Adding first el = %d\n", number);
				nums.add(number);
			}
			else	// assume your preceding input maintains the sorted invariant as well
			{
				int firstEl = nums.get(0);
				int lastEl = nums.get(nums.size() - 1);
				if(number >= lastEl)
				{
					// System.out.printf("Adding lastEl = [%d]\n", lastEl);
					nums.add(number);
				}
				else if ( number <= firstEl )
				{
					nums.add(0, number);
				}
				else // binary search right position
				{
					int low = 0;
					int high = nums.size() - 1;	// Must be this ( imagine singleton case and high = 1 here . That'd be bad! )
					while(low <= high)
					{
						int mid = (low + high)/2;
						int midEl = nums.get(mid);
						if(midEl == number)
						{
							nums.add(mid + 1,number);
							return;
						}
						else if ( midEl < number ) // must insert to the right now
						{
							if(mid < high)
							{
								int next = mid + 1;
								int nextEl = nums.get(next);
								if(nextEl < number)
								{
									low = mid + 1;	// set here
								}
								else if ( midEl <= number && nextEl >= number )
								{
									nums.add(next,number);
									return;
								}
							}
							else if ( mid == high)
							{
								nums.add(number);
								return;
							}
						}
						else if ( midEl > number ) // Cleary you never check your right here anyways. No point. Check left
						{
							if(mid > 0)
							{
								int prev = mid - 1;
								int prevEl = nums.get(prev);
								if(prevEl <= number && number <= midEl)
								{
									nums.add(mid,number); // one plus preceding here
									return;
								}
								else if ( prevEl > number)
								{
									high = mid - 1;
								}
							}
							else if ( mid == 0)
							{
								nums.add(0, number);
								return;
							}
						}
					}
				}
			}
    }

    public double getMedian() 
		{
			if(nums == null || nums.size() == 0)
			{
				return 0.0;
			}
			int length = nums.size();
			// System.out.println(length);
			boolean isEven = (length % 2 == 0);
			// System.out.println(isEven);
			if(isEven == true)
			{
				// System.out.printf("here in length = even\n");
				int firstEl = nums.get(length / 2);
				int secondEl = nums.get((length / 2 ) - 1);
				// System.out.printf("firstEl = %d \t secondEl = %d\n", firstEl, secondEl);
				median = 0.5 * (firstEl + secondEl);
			}	
			else if ( isEven == false )
			{
				// System.out.printf("Here in len = odd\n ");
				median = (nums.get(length / 2));
			}
      return median;
    }
  }
}
