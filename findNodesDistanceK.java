Given the root node of a binary tree ( not a BST ) 
Target value of a node guaranteed contained in tree
Pos int k 
Return all nodes a distance k away from node of target vavlue

Dist between two nodes = #-edges to traverse to get from one node and another
Dist to immediate left or right = 1 ( rev holds as well ) 
Each btree node has an int value, a left child node, a right child node
Children nodes can be binary tree nodes themselves or NULL/NONE
Return output values in any order
Binary tree node values are all unique

Clarify input : 
	(1) Any null/empty binary trees -> no null root trees : same for empty
	(2) Size of tree : no max sz given ( 16-20 nodes ) 
	
	1 <= k <= len_longest_path? ( assume k = 0 can be true ) 
	
COMPLEXITY : 
Let N := total # nodes
Let H := height ( balanced = log(N), skewed = N )
Time = O(N) * O(1) [ #-recursive-calls] * [work/call]
Space = ( impicit ) O(H) (explicit) O(1)

TEST BENCH 
	
sample input
tree = 1
     /   \
    2     3
  /   \     \
 4     5     6
      / \   /  \
     3   9  7   8	
    /
	 6
	 
target = 9
vals		{ 1,2,5,9} , { L, R, R } 
idxs		{ 3,2,1,0}
					    ^
k = 2

sample input
tree = 1
     /   \
    2     3
  /   \     \
 4     5     6
           /   \
          7     8
target = 3
k = 2

tree = 1
     /   \
    2     3
  /   \     \
 4     5*     6
           /   \
          7     8
					
L3 : {4,5,6} - {2,0,4}
If I hit 5 ... stop the BFS ... DFS from 5 ( 5 is a root ) 
Roots to explore always one level above
DFS depth from the root to the target ( #-edges traversed = #-levels to do BFS on )



K := minHeap/maxHeap
BFS idea?
					
sample output = [2, 7, 8] // These values could be ordered differently.

Can have a one child ( 4 cases of checking node classification ) 
Strategies : tree traversals, recursive ( DFS-BFS), backtracking
dfs(3)

path_len(1,2) + path_len(1,3) = 2
path_len(1,4) + path_len(1,3) = 3
path_len(1,5) + path_len(1,3) = 3
path_len(1,3) = 1
	nodes must be a distance of 1 or two away from the root
	Distance remaining is negative ... do not explore
path_len = 1
1-1 = 0 => 1
path_len = 0
0-1 = -1
	
Pseudocode : 

public class Wrapper
{
	int dist;
	BinaryTree node;
	public Wrapper(){dist = 0; node = null};
	public Wrapper(int dist, BinaryTree node){this.dist = dist; this.node = node;}
}

public ArrayList<Integer> findNodesDistanceK(BinaryTree tree, int target, int k)
{
	ArrayList<Integer> validHood = new ArrayList<Integer>(); 
	Wrapper targetInfo = findTarget(tree, target, 0);
	dfs(validHood, targetInfo.node, k);
	int lhs_dist = k- targetInfo.dist;
	if(lhs_dist >= 0)
		dfs(validHood, tree, lhs_dist);
	return validHood;
}

// O(N) T, O(H) space
public Wrapper findTarget(BinaryTree tree, int target, int depth)
{
	if(tree == null)
	{
		return new Wrapper(-1,null);
	}
	else if ( tree.value == target)
	{
		return new Wrapper(depth,tree);
	}
	else
	{
		Wrapper lhs = findTarget(tree.left, target, depth + 1);
		Wrapper rhs = findTarget(tree.right, target, depth + 1);
		if(lhs.dist != -1)
			return lhs;
		if(rhs.dist != -1)
			return rhs;
	}
}

public void dfs(ArrayList<Integer> hood, TreeNode root, int dist)
{
	if(root == null)
	{
		return;
	}
	else
	{
		if(dist == 0)
		{
			hood.add(root.val);
		}
		else
		{
			if(root.left != null)
				dfs(hood, root.left, dist - 1);
			if(root.right != null)
				dfs(hood, root.right, dist - 1);
		}
	}
}




import java.util.*;

class Program {
  // This is an input class. Do not edit.
  static class BinaryTree {
    public int value;
    public BinaryTree left = null;
    public BinaryTree right = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }
	
	// Could keep direction info ( starting at each root ) 
	static class Wrapper
	{
		int dist;
		BinaryTree node;
		public Wrapper(){dist = 0; node = null;}
		public Wrapper(int dist, BinaryTree node){this.dist = dist; this.node = node;}
	}

	public ArrayList<Integer> findNodesDistanceK(BinaryTree tree, int target, int k)
	{
		// Base case : k == 0
		ArrayList<Integer> validHood = new ArrayList<Integer>(); 
		if(k == 0)
		{
			validHood.add(target);
			return validHood;
		}
		Wrapper targetInfo = findTarget(tree, target, 0);
		dfs(validHood, targetInfo.node, k);
		
		// BFS traversal
		Queue<BinaryTree> order = new LinkedList<BinaryTree>();
		order.add(tree);
		while(order.size() != 0)
		{
			BinaryTree candidate = order.remove();
			if(candidate.value == target)
				break;
			Wrapper foundInfo = findTarget(candidate,target,0);
			if(foundInfo.dist != -1)
			{
				if(k - foundInfo.dist >= 0)
					dfs(validHood, candidate, k - foundInfo.dist);
			}
			if(candidate.left != null)
				order.add(candidate.left);	
			if(candidate.right != null)
				order.add(candidate.right);
		}
		
		return validHood;
	}
	
	// O(N) T, O(H) space
	public Wrapper findTarget(BinaryTree tree, int target, int depth)
	{
		if(tree == null)
		{
			return new Wrapper(-1,null);
		}
		else if ( tree.value == target)
		{
			return new Wrapper(depth,tree);
		}
		else
		{
			Wrapper lhs = findTarget(tree.left, target, depth + 1);
			Wrapper rhs = findTarget(tree.right, target, depth + 1);
			if(lhs.dist != -1)
				return lhs;
			if(rhs.dist != -1)
				return rhs;
		}
		return new Wrapper(-1,null);
	}
	
	public void dfs(ArrayList<Integer> hood, BinaryTree root, int dist)
	{
		if(root == null)
		{
			return;
		}
		else
		{
			if(dist == 0)
			{
				hood.add(root.value);
			}
			else
			{
				if(root.left != null)
					dfs(hood, root.left, dist - 1);
				if(root.right != null)
					dfs(hood, root.right, dist - 1);
			}
		}
	}


}





