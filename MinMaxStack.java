/*
THOUGHTS : 
- We still probably have to leverage the use of the stack data structure here
- Can we consider two stacks at a time as well? 
- Or consider a minHeap and a maxHeap at the same time?
- We can have data structures underneath : BUT the class methods THEMSELVES, should execute
in constant time and in constant sapce, when run independently.

There are two means of doing this
	(1) A triplet class -> 3*n storage, but preserves information well
	(2) A single integer stack, but with other dstructures
	
PSEUDOCODE :

COMPLEXITY :
Let N := #-elements currently residing on the stack ( pushed/popped ) 
TIME = O(1)
SPACE = O(1)

TEST CASES :
(A) push(5) getMin() getMax() peek() push(4) getMin() getMax() peek() push(6) getMin() getMax() peek()
(B)
(C)
(D)

Return "-1" if pop() or peek() operations fail here

*/

import java.util.*;

class Program {
  // Feel free to add new properties and methods to the class.
  static class MinMaxStack {
		
		Stack<Integer> elems = new Stack<Integer>();
		Stack<Integer> mins = new Stack<Integer>();
		Stack<Integer> maxes = new Stack<Integer>();
		
    public int peek() 
		{
			if(elems.size() == 0)
			{
				return -1;
			}
			int topElem = elems.peek();
      return topElem;
    }

    public int pop() 
		{
			if(elems.size() == 0)
			{
				return -1;
			}
			int topElem = elems.pop();
			mins.pop();
			maxes.pop();
      return topElem;
    }

    public void push(Integer number) 
		{
			if(elems.size() == 0)
			{
				mins.push(number);
				maxes.push(number);
			}
			else
			{
				int curMin = Math.min(mins.peek(), number);
				int curMax = Math.max(maxes.peek(), number);
				mins.push(curMin);
				maxes.push(curMax);
			}
			elems.push(number);
    }

    public int getMin() 
		{
			if(mins.size() == 0)
			{
				return -1;
			}
      return mins.peek();
    }

    public int getMax() 
		{
			if(maxes.size() == 0)
			{
				return -1;
			}
      return maxes.peek();
    }
  }
}

/*
THOUGHTS : 
- We still probably have to leverage the use of the stack data structure here
- Can we consider two stacks at a time as well? 
- Or consider a minHeap and a maxHeap at the same time?
- We can have data structures underneath : BUT the class methods THEMSELVES, should execute
in constant time and in constant sapce, when run independently.

There are two means of doing this
	(1) A triplet class -> 3*n storage, but preserves information well
	(2) A single integer stack, but with other dstructures
	
PSEUDOCODE :

COMPLEXITY :
Let N := #-elements currently residing on the stack ( pushed/popped ) 
TIME = O(1)
SPACE = O(1)

TEST CASES :
(A) push(5) getMin() getMax() peek() push(4) getMin() getMax() peek() push(6) getMin() getMax() peek()
(B)
(C)
(D)

Return "-1" if pop() or peek() operations fail here

*/

import java.util.*;

class Program {
	
	static class Triplet
	{
		public int elem;
		public int min;
		public int max;
		
		public Triplet()
		{
			
		}
		
		public Triplet(int elem, int min, int max)
		{
			this.elem = elem;
			this.min = min;
			this.max = max;
		}
	}
	
  // Feel free to add new properties and methods to the class.
  static class MinMaxStack {
		
		Stack<Triplet> meta = new Stack<Triplet>();
		
    public int peek() 
		{
			if(meta.size() == 0)
			{
				return -1;
			}
			Triplet topElem = meta.peek();
      return topElem.elem;
    }

    public int pop() 
		{
			if(meta.size() == 0)
			{
				return -1;
			}
			Triplet topElem = meta.pop();
      return topElem.elem;
    }

    public void push(Integer number) 
		{
			if(meta.size() == 0)
			{
				Triplet newElem = new Triplet(number,number,number);
				meta.push(newElem);
			}
			else
			{
				int curMin = Math.min(meta.peek().min, number);
				int curMax = Math.max(meta.peek().max, number);
				Triplet newElem = new Triplet(number,curMin,curMax);
				meta.push(newElem);
			}
    }

    public int getMin() 
		{
			if(meta.size() == 0)
			{
				return -1;
			}
      return meta.peek().min;
    }

    public int getMax() 
		{
			if(meta.size() == 0)
			{
				return -1;
			}
      return meta.peek().max;
    }
  }
}
