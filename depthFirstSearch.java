"C,JAVA,Javascript","Python" => good combo
Focus on interviewing.io

Given a node class containing name and an array of OPTIONAL children nodes
Implement DFS method on the node class 
Takes in empty array
Covers tree using DFS approach
Store all the nodes in the input array

Is this a directed graph?
When put together, nodes form an acyclic tree-like structure.

graph = A
     /  |  \
    B   C   D
   / \     / \
  E   F   G   H
     / \   \
    I   J   K
					 |	  ~												 ~		~
Output = ["A", "B", "E", "F", "I", "J", "C", "D", "G", "K", "H"]?
root->left->right ( preorder traversal )

Have a list of children
List<Node> children for A = {B,C,D} in this ordering 

Input = List<String> array?

array.add(node.name )

Can you ID the root of the graph?
root = this;

perform depthFirstSearch from a given node
array at start is empty list
Fill the array in the DFS to resemble the output ( add node.name )

""

Complexity
For a given graph with N number of nodes
TIME = O(N)
SPACE = O(H) ( CALL STK ) O(1) ( AUX )
	where H := log_2(N) ( balanced ) (N) ( skew ) 

Iterative
SPACE = O(H) ( AUX ) O(1) ( CALL STK ) 

graph = A
     /  |  \
    B   C   D
   / \     / \
  E   F   G   H
     / \   \
    I   J   K

Output = ["A", "D","C","B"] <- could have ventured the other way


Node novel = new Node();
# novel is a pointer to where that Node exists in memory ( on the heap )
From within the class => lack a pointer provided to you => "this" allows for self-referencing
or a class invoking its own methos without need for instantiation of a newly created object.



import java.util.*;

class Program {
  // Do not edit the class below except
  // for the depthFirstSearch method.
  // Feel free to add new properties
  // and methods to the class.
  static class Node {
    String name;
    List<Node> children = new ArrayList<Node>();

    public Node(String name) {
      this.name = name;
    }

		// Method in the node class
    public List<String> depthFirstSearch(List<String> array) 
		{
			// array = "";
			// array.add(node.name);
						// this.name
			// this.children
			// Q : use "addChild()" method?
			// Never out of scope of a node
			// if(this == null)	// can never self-reference as NULL
				// return array;
			array.add(this.name);
			List<Node> myChildren = this.children;
			for (Node child : myChildren)	// if children is a null list, do NOT iterate
			{
				child.depthFirstSearch(array);	// Same array under reference
			}
      return array;
    }

    public Node addChild(String name) {
      Node child = new Node(name);
      children.add(child);
      return this;
    }
  }
}


		// Method in the node class
    public List<String> depthFirstSearch(List<String> array) 
		{
			// array = "";
			// array.add(node.name);
						// this.name
			// this.children
			// Q : use "addChild()" method?
			// Never out of scope of a node
			// if(this == null)	// can never self-reference as NULL
				// return array;
			Stack<Node> traversal = new Stack<Node>();
			traversal.push(this);
			while(!traversal.isEmpty())
			{
				// [1] Operate on PARENT
				Node curr = traversal.pop();
				array.add(curr.name);
				
				// [2] Operate on CHILDREN -> adds to stack fully
				List<Node> myChildren = curr.children;
				int i = myChildren.size() - 1;
				while(i >= 0)
				{
					Node child = myChildren.get(i);
					traversal.push(child);
					--i;
				}
			}
      return array;
    }












