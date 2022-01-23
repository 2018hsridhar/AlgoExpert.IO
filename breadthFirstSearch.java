Given a node class with the following member fields
- name
- array/list of OPTIONAL children nodes

Nodes form a CYCLIC? like ADT
Implement BFS, taking in an empty list, and stores all NODE names in the input array
nodes form an acyclic tree-like structure.
Directed Graph
graph = A
     /  |  \
    B   C   D
   / \     / \
  E   F   G   H
     / \   \
    I   J   K


["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"]
Left->Right, over each level

Strategies : Recursively, Iteratively

A
{
  "nodes": [
    {"children": [], "id": "A", "value": "A"}
  ],
  "startNode": "A"
}

A
|
B
|
F
|
J

{
  "nodes": [
    {"children": ["B"], "id": "A", "value": "A"},
    {"children": ["F"], "id": "B", "value": "B"},
    {"children": ["I"], "id": "F", "value": "F"},
    {"children": [], "id": "I", "value": "I"}
  ],
  "startNode": "A"
}


TEST CASES
(A) Can get a node with no children
	=> ["A"]
(B) ["A","B","C","D"]
(C)
(D)

Complexity
N := #-vertices
H := BST height ( log_2(N) balanced, (N) skew )
TIME = O(N)
SPACE = O(H) ( AUX ) O(1) ( CALL STK ) 


Recursino Complexity
N := #-vertices
H := BST height ( log_2(N) balanced, (N) skew )
TIME = O(N)
SPACE = O(N) ( AUX ) O(H) ( CALL STK ) 

						1					- L1
					 / \
					2   3				- L2
				 / \ / \
				4  5 6  7			- L3
				
Full tree ( Perfect tree ) 

#-node in tree = 2^3-1 = 7 nodes ( 3 levels )
Final level - 4 nodes = 8/2 (numNodes + 1 / 2 )


execBFS(array, (1))
execBFS(array, (2,3))
execBFS(array, (4,5,6,7)) <--- taking up N amount of space per stack frame with the level list



API - Application Programming Interface
ADT - Abstract Data Type ( data structures )
https://docs.oracle.com/javase/8/docs/api/java/util/List.html


JDBC 	-> 


REST API



List<Integer> numbers;

Collections.sort(numbers);
Collections.getMean()
Collections.getMedian() 	<--- collection/set constitutes an API

API is too set ( webDev/appDev context )
as methods are to members/elements of a set 





import java.util.*;

class Program {
  // Do not edit the class below except
  // for the breadthFirstSearch method.
  // Feel free to add new properties
  // and methods to the class.
  static class Node {
    String name;
    List<Node> children = new ArrayList<Node>();

    public Node(String name) {
      this.name = name;
    }

    // Remove from HEAD, add to TAIL ( in queue ) VS stk -> remove from and add to TAIL
		public List<String> breadthFirstSearch(List<String> array) 
		{
			// [1] Iteratively using a queue
			Queue<Node> traversal = new LinkedList<Node>();
			traversal.add(this);
			while(!traversal.isEmpty())
			{
				Node cur = traversal.poll();
				array.add(cur.name);
				List<Node> myChildren = cur.children;
				for(Node child : myChildren)
				{
					traversal.add(child);
				}
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



    // Remove from HEAD, add to TAIL ( in queue ) VS stk -> remove from and add to TAIL
		public List<String> breadthFirstSearch(List<String> array) 
		{
			// [1] Iteratively using a queue
			List<Node> level = new ArrayList<Node>();
			level.add(this);
			execBFS(array, level);
			return array;
    }
		
		private void execBFS(List<String> array, List<Node> level)
		{
			// Terminating condition -> prevents the stack overflow
			if(level == null || level.size() == 0)
				return;
			
			// [1] Process the parent/the current level
			for(Node x : level)
			{
				array.add(x.name);
			}
			// [2] Create new level list, for the children
			int numNodesCurLevel = level.size();
			for(int i = 0; i < numNodesCurLevel; ++i)
			{
				Node parent = level.remove(0); // remove from the HEAD : O(1) resize
				for(Node y : parent.children)
					level.add(y);								// add to end, if TAIL ptr stored, is O(1)
			}
			
			execBFS(array, level);
			
		}







