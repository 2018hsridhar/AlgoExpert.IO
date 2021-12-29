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
(B)
(C)
(D)
(E)

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

		// setHead(3) but 3 repeated multiple times? Is it the first 3, or all 3's here then?
		// Maybe collect a list of them instead? 
		// Size check needed too!
		// Make sure to set the HEADS and TAILS correctly!
    public void setHead(Node node) 
		{
			if(size == 0)
			{
				head = node;
				tail = node;
				size++;
			}
			else if ( size == 1)
			{
				node.next = head;
				node.prev = null;
				head.prev = node;
				head = node;
				size++;
			}
			else if ( size >= 2 ) 
			{
				int nodeVal = node.value;
				if(containsNodeWithValue(nodeVal))
				{
					// [1] Create a list of value equal nodes
					List<Node> valEqualNodes = new ArrayList<Node>();
					Node curr = head;
					while(curr != null)
					{
						if(curr.value == nodeVal)
						{
							valEqualNodes.add(curr);
						}
						curr = curr.next;
					}

					// [2] Perform pointer manipulations quickly
					// Just handle the base cases ( e.g. node equals head OR node equals tail )
					for(Node x : valEqualNodes)
					{
						if(x.prev == null) // head of DLL
						{
							continue;
						}
						else if ( x.next == null) // tail of DLL
						{
							Node prev = x.prev;
							prev.next = null;
							x.prev = null;
							x.next = head;
							head = x;
						}
						else	// middle of DLL
						{
							Node before = x.prev;
							Node after = x.next;
							before.next = after;
							after.prev = before;
							x.prev = null;
							x.next = head;
							head = x;
						}
					}
				}
				else
				{
					// Standalone case : easy to handle
					node.next = head;
					node.prev = null;
					head.prev = node;
					++size;
				}
			}
    }

    public void setTail(Node node) 
		{
			// System.out.println("At set tail");
			if(size == 0)
			{
				head = node;
				tail = node;
				size++;
			}
			else if ( size == 1)
			{
				tail.next = node;
				node.prev = head;
				node.next = null;
				tail = node;
				size++;
			}
			else if ( size >= 2 ) 
			{
				// System.out.println("At size >= 2");
				int nodeVal = node.value;
				if(containsNodeWithValue(nodeVal))
				{
					// System.out.printf("Contains node with val = %d\n", nodeVal);
					// [1] Create a list of value equal nodes
					List<Node> valEqualNodes = new ArrayList<Node>();
					Node curr = head;
					while(curr != null)
					{
						if(curr.value == nodeVal)
						{
							valEqualNodes.add(curr);
						}
						curr = curr.next;
					}

					// [2] Perform pointer manipulations quickly
					// Just handle the base cases ( e.g. node equals head OR node equals tail )
					for(Node x : valEqualNodes)
					{
						if(x.next == null) // tail of DLL
						{
							continue;
						}
						else if ( x.prev == null) // head of DLL
						{
							Node newHead = x.next;
							tail.next = x;
							x.prev = tail;
							x.next = null;
							head = newHead;
						}
						else	// middle of DLL
						{
							Node before = x.prev;
							Node after = x.next;
							before.next = after;
							after.prev = before;
							
							
							tail.next = x;
							x.prev = tail;
							x.next = null;
							tail = x;
						}
					}
				}
				else
				{
					// Standalone case : easy to handle
					// System.out.printf("At sstandalon case");
					tail.next = node;
					node.prev = tail;
					node.next = null;
					tail = node;
					++size;
				}
			}
		}
		
		public void disconnectNode(Node nodeToInsert)
		{
			if(nodeToInsert.prev != null || nodeToInsert.next != null)
			{
				if(nodeToInsert.prev == null)
				{
					nodeToInsert.next = null;
				}
				else if ( nodeToInsert.next == null)
				{
					nodeToInsert.prev = null;
				}
				else
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
				Node earlierTail = tail;
				Node before = tail.prev;
				before.next = null;
				tail.prev = null;
				setTail(nodeToInsert);
				setTail(earlierTail);
			}
			else
			{
				Node before = node.prev;
				Node after = node.next;
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
				// System.out.println("Setting tail");
				setTail(nodeToInsert);
			}
			else if(node == head)
			{
				Node earlierHead = head;
				Node after = head.next;
				after.prev = null;
				head.next = null;
				setHead(nodeToInsert);
				setHead(earlierHead);
			}
			else
			{
				Node before = node.prev;
				Node after = node.next;
				before.next = nodeToInsert;
				nodeToInsert.prev = before;
				after.prev = nodeToInsert;
				nodeToInsert.next = after;
				size++;
			}
		}

    public void insertAtPosition(int position, Node nodeToInsert) 
		{
			disconnectNode(nodeToInsert);
      // Write your code here.
			if(position <= 1 || size == 1)
			{
				setHead(nodeToInsert);
			}
			// We could generalize to position >= size here
			else if ( position >= size ) // || (size == 1 && position == 2))
			{
				setTail(nodeToInsert);
			}
			else
			{
				Node curr = head;
				for(int i = 1; i < position; ++i)
				{
					if(curr == null)
					{
						break;
					}
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
    public void remove(Node node) 
		{
			Node curr = head;
			while(curr != null)
			{
				if(curr == node) // hit node : check cases now
				{
					if(curr.prev == null && curr.next == null)
					{
						head = null;
						tail = null;
						break;
					}
					else if(curr.prev == null) // equals head
					{
						Node follow = curr.next;
						follow.prev = null;
						head = follow;
					}
					else if(curr.next == null) // equals tail
					{
						Node prev = curr;
						prev.next = null;
						tail = prev;
					}
					else // in-between
					{
						Node before = curr.prev;
						Node after = curr.next;
						before.next = after;
						after.prev = before;
					}
				}
				curr = curr.next;
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
