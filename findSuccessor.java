/*

Note is a binary tree ( not a BST ) 
Nodes do have pointers to their parents nodes BTW -> remember this
Given a BT and a node guaranteed to be contained in the tree
Node addresses are unique -> but not values themselves can be unique

Complexity : 
Let N := number of nodes in the Binary Tree
Let H := height of the tree
	balanced : H = log(N)
	unbalanced : H = N
Time = O(H)
Space = O(1) explicit, O(1) explicit if iterative, O(H) implicit if recursive

Edge Case Testing : 
(A)
(B)
(C)
(D)
(E)
(F)

naive solutino : generate the entire inorder traversal, and store their addresses/node objects in a node list.
Perform obj address comparison
Engenders a O(N), O(N) time-space solution : can't we do better?


*/

import java.util.*;

class Program {
  // This is an input class. Do not edit.
  static class BinaryTree {
    public int value;
    public BinaryTree left = null;
    public BinaryTree right = null;
    public BinaryTree parent = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

  public BinaryTree findSuccessor(BinaryTree tree, BinaryTree node) {
    // Write your code here.
    return null;
  }
}
