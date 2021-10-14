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

*/
import java.util.*;

class Program 
{
	// Be warry : input is not an array -> it is a list, sadly 
	// We could convert to array though, if need be
	
  public static BST minHeightBst(List<Integer> array) 
	{
    // Write your code here.
    return null;
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
