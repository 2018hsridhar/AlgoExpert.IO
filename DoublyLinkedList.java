/*
Write up a DoublyLinkedList ( DLL ) Class
Supports head,tail pointers
Do not create a new node in ANY method -> node taken in as parameters
Input nodes either stand-alone nodes OR already in the SLL
	If in the SLL : we are just moving em aroudn
	Handle said scenario DEFENSIVELY!
	
PY/JS :=> untyped languages
Differences : standalone node VS existing nodes

COMPLEXITY
Let N := #-nodes in the DLL
TIME = O()
SPACE = O()

TEST CASES
(A) [1,2,3,3,4,3] setHead(3)
	=> [3,3,3,1,2,4]

Maybe track a hash set or a hashmap : but seems extra memory footprint here.
containsNodeWithValue() -> stand-alone node determiner.

setHead() and setTail() can be insertion methods in the hiding as well
Your code seems to be getting WAY WAY too complicated here -> take strong note of this!

*/

import java.util.*;

// Feel free to add new properties and methods to the class.

class Program 
{
  static class DoublyLinkedList 
	{
    public Node head;
    public Node tail;
		int size = 0;

    public void setHead(Node node) 
		{
			disconnectNode(node);
			if(size == 0)
			{
				tail = node;
			}
			else if ( size == 1)
			{
				node.next = head;
				node.prev = null;
				head.prev = node;
			}
			else if ( size >= 2 ) 
			{
				node.next = head;
				node.prev = null;
				head.prev = node;
			}
			head = node;
			size++;
    }

    public void setTail(Node node) 
		{
			disconnectNode(node);
			if(size == 0)
			{
				head = node;
			}
			else if ( size == 1)
			{
				tail.next = node;
				node.prev = head;
				node.next = null;
			}
			else if ( size >= 2 ) 
			{
				tail.next = node;
				node.prev = tail;
				node.next = null;
			}
			tail = node;
			size++;
		}
		
		// Manipulate HEAD and TAIL pointers here
		public void disconnectNode(Node nodeToInsert)
		{
			if(nodeToInsert.prev != null || nodeToInsert.next != null)
			{
				if(nodeToInsert.prev == null)
				{
					head = nodeToInsert.next;
					head.prev = null;
					nodeToInsert.next = null;
				}
				else if ( nodeToInsert.next == null)
				{
					tail = nodeToInsert.prev;
					tail.next = null;
					nodeToInsert.prev = null;
				}
				else	// HEAD,TAIL do not change here
				{
					Node before = nodeToInsert.prev;
					Node after = nodeToInsert.next;
					before.next = after;
					after.prev = before;
					nodeToInsert.next = null;
					nodeToInsert.prev = null;
				}
				size--;
			}
		}

		// Perhaps in these other class methods, invoke earlier code too!
		// Read question carefully here. Yes, "3" can be an already existing node!
		// Your code did not test for the stand-alone case. Exert caution here
    public void insertBefore(Node node, Node nodeToInsert) 
		{
			disconnectNode(nodeToInsert);
			if(size == 1 || node == head)
			{
				setHead(nodeToInsert);
			}
			else if ( node == tail)
			{
				// System.out.printf("Size = %d \t tail = %d \n", size, tail.value);
				Node before = tail.prev;
				before.next = nodeToInsert;
				nodeToInsert.prev = before;
				
				nodeToInsert.next = tail;
				tail.prev = nodeToInsert;
				
				size++;
			}
			else
			{
				// System.out.printf("Doing a middle insertion\n");
				Node before = node.prev;
				Node after = node;
				before.next = nodeToInsert;
				nodeToInsert.prev = before;
				after.prev = nodeToInsert;
				nodeToInsert.next = after;
				size++;
			}
    }

		// Size guaranteed to be greater than one, at least, here, for insertBefore and insertAfter
		// Guaranteed existence in the DLL too!
    public void insertAfter(Node node, Node nodeToInsert) 
		{
			// Hey "nodeToInsert" is only one node anyways, and "node" is guaranteed to be in list
			// "nodeToInsert" can be isolated first : just connect that list, based on special cases too
			// Write up a disconnect method here ( generalize it ) 
			// System.out.println(node==tail);
			disconnectNode(nodeToInsert);
			if (size == 1 ||  node == tail)
			{
				setTail(nodeToInsert);
			}
			// Ran into this case, as 2 now the head of the SLL
			// But reality is that you are in the middle here :-O
			else if(node == head)	
			{
				Node follow = head.next;
				nodeToInsert.next = follow;
				follow.prev = nodeToInsert;
				head.next = nodeToInsert;
				nodeToInsert.prev = head;
				size++;
			}
			else
			{
				// Clearly, we are storing null addresses here : connections not establishing right?
				Node before = node;
				Node after = node.next;
				before.next = nodeToInsert;
				nodeToInsert.prev = before;
				after.prev = nodeToInsert;
				nodeToInsert.next = after;
				size++;
			}
		}

		// Final method to correct
		// Disconnecting will have an impact here -> take close note of that as well!
		
    public void insertAtPosition(int position, Node nodeToInsert) 
		{			
			if(size == 1)
			{
				setHead(nodeToInsert);
			}
			else
			{
				Node curr = head;
				for(int i = 1; i < position; ++i)
				{
					if(curr == null)
						break;
					curr = curr.next;
				}
				insertBefore(curr, nodeToInsert);
    	}
		}
		// O(N) Time, O(1) Space for geting nodes with values
		// O(N) Time, O(1) Space for each seperate removal -> yeah it could be better, but whatever for now
    public void removeNodesWithValue(int value) 
		{
			List<Node> nodeWithVal = new ArrayList<Node>();
			Node curr = head;
			while(curr != null)
			{
				if(curr.value == value)
				{
					nodeWithVal.add(curr);
				}
				curr = curr.next;
			}
			for(Node x : nodeWithVal)
			{
				remove(x);
			}
    }

		// Special case checking needed too?
		// Is not just a values removal : addresses are unique and are more specific than values
		// Could have wrote logic with sentinels => but extra space here?
		// O(N) Time, O(1) Space, Iterative
		// Why are we iterative here exactly, is a bit confusing, when we have the stinking node !!
		
    public void remove(Node node) 
		{
			if(node.prev == null && node.next == null)
			{
				head = null;
				tail = null;
			}
			else if(node.prev == null && node.next != null) // equals head
			{
				Node follow = node.next;
				follow.prev = null;
				head = follow;
			}
			else if(node.prev != null && node.next == null) // equals tail
			{
				Node prev = node.prev;
				prev.next = null;
				tail = prev;
			}
			else // in-between
			{
				Node before = node.prev;
				Node after = node.next;
				before.next = after;
				after.prev = before;
			}
			size--;
    }

		// O(N) Time, O(1) Space, Iterative
    public boolean containsNodeWithValue(int value) 
		{
			boolean hasNodeWithVal = false;
			Node curr = head;
			while(curr != null)
			{
				if(curr.value == value)
				{
					hasNodeWithVal = true;
					break;
				}
				curr = curr.next;
			}
      return hasNodeWithVal;
    }
  }

  // Do not edit the class below.
  static class Node {
    public int value;
    public Node prev;
    public Node next;

    public Node(int value) {
      this.value = value;
    }
  }
}
