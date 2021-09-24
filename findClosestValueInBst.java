import java.util.*;

class Program {
  public static int findClosestValueInBst(BST tree, int target) {
		if(tree == null)
			return 0;
		int closestVal = tree.value;
		int minDist = Math.abs(closestVal - target);
		while(tree != null)
		{
			if(Math.abs(tree.value - target) < Math.abs(closestVal - target))
				closestVal = tree.value;
			if(tree.value == target)
				return target;
			else if ( tree.value < target)
				tree = tree.right;
			else
				tree = tree.left;
		}
    return closestVal;
  }

  static class BST {
    public int value;
    public BST left;
    public BST right;

    public BST(int value) {
      this.value = value;
    }
  }
}
