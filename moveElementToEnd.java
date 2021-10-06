
/*
Yes - the problem says "given an array", but this is a typo : the input is actually a List<Integer>
You may return either an ArrayList<Integer> or a LinkedList<Integer> 


Complexity
Let N := len(list)
Time = O(N)
Space = O(1)

Edge Case Testing : 
(A) ([5,4,3,2,1], 0)
	=> no elements to move to the end here
(B) ([5,4,3,2,1], 1)
	=> elems already at the end
(C) ([5,4,3,2,1,1,1], 1)
=> elems already at the end
(D) ([1,1,1,5,4,3,2], 1)
=> elems already at the start : shift to end
=> ([5,4,3,2,1,1,1])
(E) ([5,4,1,1,1,3,2], 1)
=> elems in the middle
=> ([5,4,3,2,1,1,1])
(F) (E) ([5,4,1,3,1,2,1,0], 1)
=> elems in the middle, but NOT continuous
=> ([5,4,3,2,1,1,1])
(G) [2],2 or [2], 0
=> singleton test
(H)
(I)
(J)



*/

import java.util.*;

class Program {
  public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) 
	{
		int sz = array.size();
		int count = 0;
		// This is the type of bug folks WOULD get tripped on ( removal changing sz of the SLL ) 
		int i = 0;
		while(i < sz)
		{
			int curEl = array.get(i);
			if(curEl == toMove)
			{
				array.remove(i);
				--sz;
				++count;
			}
			else
			{
				++i;
			}
		}
	// Add takes place only at end too : assume we have a TAILPTR in the JAVA API here
		for(int j = 0; j < count; ++j)
			array.add(toMove);
    return array;
  }
}


/*
https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

*/

import java.util.*;

// No support for using Iterator-based syntax : remove lacks logic for removal at specific indices
// Or at portions of the SLL
class Program {
  public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) 
	{
		int count = 0;
		// This is the type of bug folks WOULD get tripped on ( removal changing sz of the SLL ) 
		Iterator<Integer> itr = array.iterator();
		while(itr.hasNext())
		{
			int curEl = itr.next();
			if(curEl == toMove)
			{
				itr.remove();
				++count;
			}
		}
		
		for(int j = 0; j < count; ++j)
			array.add(toMove);
    return array;
  }
}

