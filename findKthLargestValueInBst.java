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

  public int findKthLargestValueInBst(BST tree, int k) 
    {
	    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override 
			public int compare(Integer a, Integer b) {
				return a - b; 
			}
		}); 
        fillHeap(maxHeap, tree, k);
        return maxHeap.poll();
    }
    
    /*
        Assume right has greatest and fill from there
        Then fill at parent root
        Then left, as those are STRICTLY smaller
    */
    public void fillHeap(PriorityQueue<Integer> maxHeap, BST root, int k)
    {
        if(root.left != null)
            fillHeap(maxHeap, root.left, k);
			
			  if(maxHeap.size() >= k)
            maxHeap.poll();
        maxHeap.add(root.value);
        
				if(root.right != null)
            fillHeap(maxHeap, root.right, k);
      
    }
	
}
