/*
Non-empty sorted array of DISTINCT ( UNIQUE ) integers
Make a BST from this
Return the root of this BST

Goal : Minimze the height of the BST
Leverage the sorted, unique property of input
BST property : value > LHS, value <= RHS
Utilize BST insert method as well ( if desired as well ) 
	-> can we try to code up an approach which does not use it ( is your challenge ) 

Complexity : 
Let N := len(array)
Time = 
Space = 

Test Bench
(A) [1]
(B) [1, 2]
(C) [1, 2, 3]
(D) [1, 2, 3, 4]
(E) [1,2,3,4,5,6,7,8,9]
(F) []
(G) NULL
(H) [1,2,3,4,5,6,7,8,9,10]
... can induct from cases of {2,3} here for rest of test bench

https://docs.oracle.com/javase/8/docs/api/java/util/List.html

*/
import java.util.*;

class Program 
{
	// Be warry : input is not an array -> it is a list, sadly 
	// We could convert to array though, if need be
	
  public static BST minHeightBst(List<Integer> array) 
	{
		int n = array.size();
		int[] A = new int[n];
		Iterator<Integer> itr = array.iterator();
		int wIdx = 0;
		while(itr.hasNext())
			A[wIdx++] = itr.next();
		
		BST root = dac(A,0,n-1);;
    return root;
  }
	
	public static BST dac(int[] array, int low, int high)
	{
		BST root = null;
		if(low > high)
			return null;
		else if ( low == high)
		{
			root = new BST(array[low]);
		}
		else
		{
			int mid = (low + high) / 2;
			BST lhs = dac(array, low, mid - 1);
			BST rhs = dac(array, mid + 1, high);
			root = new BST(array[mid]);
			root.left = lhs;
			root.right = rhs;
		}
		return root;
	}
	

  static class BST {
    public int value;
    public BST left;
    public BST right;

    public BST(int value) {
      this.value = value;
      left = null;
      right = null;
    }

    public void insert(int value) {
      if (value < this.value) {
        if (left == null) {
          left = new BST(value);
        } else {
          left.insert(value);
        }
      } else {
        if (right == null) {
          right = new BST(value);
        } else {
          right.insert(value);
        }
      }
    }
  }
}
