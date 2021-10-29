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
		dfs(validHood, targetInfo.node, k, target);
		
		// BFS traversal
		Queue<BinaryTree> order = new LinkedList<BinaryTree>();
		order.add(tree);
		while(order.size() != 0)
		{
			BinaryTree candidate = order.remove();
			if(candidate.value == target)
				break;
			Wrapper foundInfo = findTarget(candidate,target,0);
			Wrapper foundInfoLeft = findTarget(candidate.left,target,0);
			Wrapper foundInfoRight = findTarget(candidate.right,target,0);
			if(foundInfo.dist != -1)
			{
				if(k - foundInfo.dist > 0)
				{
					if(foundInfoLeft.dist != -1)
						dfs(validHood, candidate.right, k - 1- foundInfo.dist, target);
					if(foundInfoRight.dist != -1)
						dfs(validHood, candidate.left, k - 1 - foundInfo.dist, target);
				}
				else if ( k - foundInfo.dist == 0)
					validHood.add(candidate.value);
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
	
	public void dfs(ArrayList<Integer> hood, BinaryTree root, int dist, int target)
	{
		if(root == null)
		{
			return;
		}
		else
		{
			if(dist == 0)
			{
				if(root.value != target)
					hood.add(root.value);
			}
			else
			{
				if(root.left != null)
					dfs(hood, root.left, dist - 1, target);
				if(root.right != null)
					dfs(hood, root.right, dist - 1, target);
			}
		}
	}


}

