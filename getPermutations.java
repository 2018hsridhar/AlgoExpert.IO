import java.util.*;

class Program 
{
  
		public static List<List<Integer>> getPermutations(List<Integer> array)
		{
			List<List<Integer>> permutations = new ArrayList<List<Integer>>();
			if(array.size() == 0)
				return permutations;
			HashSet<Integer> visited = new HashSet<Integer>();
			List<Integer> currentPerm = new ArrayList<Integer>();
			int depth = 0;
			int n = array.size();
			recurse(array, currentPerm, depth,n, permutations);
			return permutations;
		}

		// Parent caller and child callee functions
		// References
		// Linked-list vs. dynamic array
			// deep-copying those visited sets  as well in each recursive step
			// private static void recurse(List<Integer> elements, Set<Integer> visited, List<Integer> currentPerm, int curDepth, int n, List<List<Integer>> permutations)
		private static void recurse(List<Integer> elements, List<Integer> currentPerm, int curDepth, int n, List<List<Integer>> permutations)
		{
			// System.out.printf("At depth = %d\n", curDepth);
			// Identify base case
			if(curDepth == n)
			{
				// deep copying issues 
				List<Integer> res = new ArrayList<Integer>();
				for(Integer x : currentPerm)
					res.add(x);
				permutations.add(res);
				return;
			}

			// Recursive cases
			// caller parent
			for(int i = 0; i < elements.size(); ++i)
			{
				int elem = elements.get(i);	// pseudo-register
				currentPerm.add(elem);		// O(1) operations in most lists? JAVA specific
				elements.remove(i);
				recurse(elements, currentPerm, curDepth + 1, n, permutations);	// callee child
				elements.add(i, elem);	// add is adding to the end : not back at index position!
				currentPerm.remove(currentPerm.size() - 1);
			}

			// [1,2,3] : get(1) => [1,3] => add(1) => [1,2,3]
		}

	
}


Permutations

Write a function that takes in an array of UNIQUE integers ( no duplicates ) 
and returns all permutations
Return in no particular order
Return empty array if array is empty


sample input
array = [1, 2, 3]
output
[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]

Input clarification
- Can be neg or zero
- [0,upper_bound] : fit in memory 
- array not always sorted

Strategies : 
Recursive,back-tracking
base case : single len input [1] or [10] -> 1 valid permutation -> array itself
[1,2],[8,5]
[1,2],[2,1] OR [5,8],[8,5]

 0 1 2
[1,2,3]
 _,_,_
 0 -> {1,2,3} : 1
 1 -> {2,3}	  : 2
 2 -> {3}	  : 3 = 1 choice available only

Complexity
Let N := len(input)
Time = O(N!) (#-recursive calls) * (work/call)
	EXP or factorial time O(2^n)?
	O(N!) or O(N!*N)
Space = O(N) (implicit) O(1) ( explicit)

Test Bench
(A) [1]
(B) [8,5]
(C) [1, 2, 3]
(D) [1, 2, 3, 6]
(E) [1, 2, 3, 6,1,6,3,1,7,1]


Pseucode ::

public static List<List<Integer>> getPermutations(List<Integer> array)
{
	List<List<Integer>> permutations = new ArrayList<List<Integer>>();
	HashSet<Integer> visited = new HashSet<Integer>();
	List<Integer> currentPerm = new ArrayList<Integer>();
	int depth = 0;
	int n = array.length;
	recurse(array, currentPerm, depth,n, permutations);
	return permutations;
}

// Parent caller and child callee functions
// References
// Linked-list vs. dynamic array
private static void recurse(List<Integer> elements, List<Integer> currentPerm, int curDepth, int n, List<List<Integer>> permutations)
{
	// Identify base case
	if(curDepth == n)
	{
		permutations.add(currentPerm);
		return;
	}
	
	// Recursive cases
	// caller parent
	for(int i = 0; i < elements.length; ++i)
	{
		int elem = elements.get(i);	// pseudo-register
		currentPerm.add(elem);		// O(1) operations in most lists? JAVA specific
		elements.remove(elem);
		recurse(elements, currentPerm, curDepth + 1, n, permutations);	// callee child
		elements.add(i, elem);	// add is adding to the end : not back at index position!
		currentPerm.remove(elem);
	}
	
	// [1,2,3] : get(1) => [1,3] => add(1) => [1,2,3]
}

recurse([1,2,3],[],0,3,{})
	1 : recurse([2,3],[1],1,3,{})
		2 : recurse([3],[1,2],2,3,{})
			3 : recurse([],[1,2,3],3,3,{})
				perms.add([1,2,3])
		3 : recurse([2],[1,3],2,3,{})
			recurse([],[1,3,2],3,3,{})
				perms.add([1,3,2])
	2 : recurse([1,3],[2],1,3,{})
	3 : recurse([1,2],[3],1,3,{})
	
	
TOP-DOWN On a tree

		1
	  /  \
	 2    3
	/ \   /\
   5   7  5 6
   Visit each node only once : N recursive calls ( top-down approach )

JS - list array?
Dynamic arrays - std::vectors : resized
int[] a = new int[5]; 
// cannot chagne the size
std::vector<int> a;
a.push_back(1);
a.push_back(2);
a.push_back(3);




