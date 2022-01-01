Youngest Common Ancestor
You're given three inputs, all of which are instances of an AncestralTree 
class that have an ancestor property pointing to their youngest ancestor. 
The first input is the top ancestor in an ancestral tree (i.e., the only 
instance that has no ancestor--its ancestor property points to None / 
null), and the other two inputs are descendants in the ancestral tree.

Write a function that returns the youngest common ancestor to the two 
descendants.

Note that a descendant is considered its own ancestor. So in the simple 
ancestral tree below, the youngest common ancestor to nodes A and B is 
node A.

topAncestor = node A
descendantOne = node E
descendantTwo = node I
			  A
		   /     \
		  B       C
		/   \   /   \
	   D     E F     G
	 /   \
	H     I
   /
  J
  
From given node, we have it's children and it's parent
nodes point only to parent
E is an ancestor of I, or other way -> just keep going up
	nodes were (I,G)
Restriction -> only go up in the tree
Lengths of the paths to the descendants ( 4 steps to J, 2 steps to E, from A)
	go up from node further down in tree till I hit depth of node further up
	J->H->D ( level = 2 )
	When levels equal : perform comparison
	D->B,E->B ( depth = 1, same ancestor )

Complexity
Time = O(2*H) + O(2*H) = O(H)
Space = O(1)

Cae (D,G)
	depth(D) = 2
	depth(G) = 2
	D!= G
	D->B,G->C
	depth(B) = depth(C) = 1, but B != C
	B->A,C-A
	
	

node B

ancestors(I) => {D,B,A}
ancestors(E) =>   {B,A}
root/top ancestor is an ancestor of all other nodes in the tree

A,D -> YCA = A
A => {A}			[]
D => {D,B,A}		{B,A} ( excluded curr node ) 

Inlcude the current node as well
Two pointer comparison approach after making the traversal of all upwards nodes
	I -> [A,B,D]
		       ^
		       p1
	E -> [A,B]
		      ^
		      p2
			  
	either a pointer out of range in it's list or addresses do not match -> break

Can we do in constant space


PSEUDOCODE : 

	listUpToRoot = ()
	for (dOne,dTwo)
		cur = dOne
		while cur node is not null
			listUpToRoot.add(cur)
			cur = cur.ancestor
	
	ptr1 = 0
	ptr2 = 0
	yca = listOne[0]
	while(ptr1 < len(listOne) and ptr2 < len(listTwo))
		if(addr(listOne[ptr1]) == addr(listTwo[ptr2]))
			yca = listOne[ptr1]
			++ptr1
			++ptr2
		else
			break
			
	ret yca
	
Complexity
Iterative algo
Let N := #-nodes in the tree
Let H := height of the binary tree ( O(log_2(n)) balanced, O(n) worst )
TIME = O(H) + O(H) = O(H)
SPACE = O(H) + O(H) = O(H) 
best => log_2(n) and worst => n

Get youncest common ancestor to the two descendants
Provided a tree here

topAncestor := root of the tree
static class for object here
	Ancestor always points to the parent too ( for root, points to NULL ) 
	E -> B, I -> D
	
Test cases
- assume tree never empty or null : at least have one node here
(A)
(B)
(C)





import java.util.*;

class Program {
  public static AncestralTree getYoungestCommonAncestor(
      AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) 
  {
		AncestralTree yca = topAncestor;
		int heightOne = getHeight(descendantOne, topAncestor);
		int heightTwo = getHeight(descendantTwo, topAncestor);
	  	int minHeight = Math.min(heightOne,heightTwo);
	  	while(heightOne > heightTwo)
		{
			descendantOne = descendantOne.ancestor;
			heightOne -= 1;
		}
	  	while(heightOne < heightTwo)
		{
			descendantTwo = descendantTwo.ancestor;
			heightTwo -= 1;
		}
	  	while(minHeight > 0)
		{
			if(descendantOne == descendantTwo)
			{
				yca = descendantOne;
				break;
			}
			else
			{
				descendantOne = descendantOne.ancestor;			
				descendantTwo = descendantTwo.ancestor;
				--minHeight;
			}
		}
		return yca; // Replace this line
  }
	
  public static int getHeight(AncestralTree node, AncestralTree topAncestor)
  {
	  int height = 0;
	  while(node != topAncestor)
	  {
		  node = node.ancestor;	// root's ancestor points to null
		  height += 1;
	  }
	  return height;
  }
	

  static class AncestralTree {
    public char name;
    public AncestralTree ancestor;

    AncestralTree(char name) {
      this.name = name;
      this.ancestor = null;
    }

    // This method is for testing only.
    void addAsAncestor(AncestralTree[] descendants) {
      for (AncestralTree descendant : descendants) {
        descendant.ancestor = this;
      }
    }
  }
}


https://www.hackerearth.com/
https://450dsa.com/
https://takeuforward.org/interviews/strivers-sde-sheet-top-coding-interview-problems/

https://github.com/donnemartin/system-design-primer

chris richardson - microservices patterns - microservices.io
brendan burns - distributed systems
