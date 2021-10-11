/*


	



Complexity 
Let N := num_nodes(BST)
Let H := height(BST) ( log_2(n) avg case, n worst case ) 
Time = O(N)
Space = O(H)

Case Testing : 
(A)
(B)
(C)
(D)
(E)
(F)



*/

import java.util.*;

class Program {
  // This is an input class. Do not edit.
  static class BST {
    public int value;
    public BST left = null;
    public BST right = null;

    public BST(int value) {
      this.value = value;
    }
  }

  public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) 
	{
		boolean firstOrder = dfs(nodeOne,nodeTwo) && dfs(nodeTwo, nodeThree);
		boolean secondOrder = dfs(nodeThree,nodeTwo) && dfs(nodeTwo, nodeOne);
		boolean satisfiedOrderProperty = (firstOrder || secondOrder);
		return satisfiedOrderProperty;
  }
	
	private boolean dfs(BST src, BST dst)
	{
		boolean res = false;
		if(src == dst)
			return true;
		if(src == null)
			return false;
		else
		{
			if(src.value < dst.value)
				res = dfs(src.right, dst);
			else
				res = dfs(src.left,dst);
		}
		return res;
	}
	
}
