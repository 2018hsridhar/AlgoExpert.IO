LRU := Least Recently Used Cache

Goal := implement the LRU cache
Insertion of (K,V) pairs
Retrieve value from key as well
Retrieve most recently used key ( retrieve key most recently used ) 
- read data OR wrote new data

O(1) time methods
max size property set to cache size

If we can not insert more (K,V) pairs -> evict the least recent used (K,V) pair
Simply replace (K,V) pairing if same key appears twice

("Apple",5)
("Apple",11) -> evict value at existing key = "Apple"

Retrieval from non-existent key in cache : return null



Sample Usage
// All operations below are performed sequentially.
LRUCache(3): - // instantiate an LRUCache of size 3
insertKeyValuePair("b", 2): -
insertKeyValuePair("a", 1): -
insertKeyValuePair("c", 3): -
getMostRecentKey(): "c" // "c" was the most recently inserted key
getValueFromKey("a"): 1
getMostRecentKey(): "a" // "a" was the most recently retrieved key
insertKeyValuePair("d", 4): - // the cache had 3 entries; the least recently used one is evicted
getValueFromKey("b"): None // "b" was evicted in the previous operation
insertKeyValuePair("a", 5): - // "a" already exists in the cache so its value just gets replaced
getValueFromKey("a"): 5


(K,V) pairings : only one value, at max, associated with each unique key
- hash collisions, linear probing, chaining => not a concern
- keep track of a count of keys


Complexity
All ops : O(1)
No Aux space constraints

Ideas : 
Hashmaps, sets : constant-time operations
Sliding window technique
- priority queue, list, track sequence
- maintain order OF insertion/key use

Key orderings : 

		b->a->c
		getValueFromKey("a")
		b->c->a
		insert("d",4)
		c->a->d
		
	e->c->a->b->f->d
	readVal("a")
	e->c->b->f->d->a
	...
	c->b->f->d->a->e
	readVal("a")
	c->b->f->d->e->a
		
	c<->b<->f<->d<->a<->e
	^					^
	TAIL				HEAD
	
Stacks OR Queues

HEAD := most recent key
TAIL :+ least recent key

Track additional state as well

Naive HM : 
	( key_inserted, value_inserted ) 
	( key_inserted, node )
	( prev, next ) ptrs : DLL
	
	
Consider sentinel node?

	
		
		
		
+> Circular buffer / circular linked list

TEST CASES
(A) cache siez = 1
	insert("a",1)
	insert("a",1)
	insert("a",1)
	insert("a",1)
	insert("a",1)








import java.util.*;

// Do not edit the class below except for the insertKeyValuePair,
// getValueFromKey, and getMostRecentKey methods. Feel free
// to add new properties and methods to the class.
class Program {
	
  static class Node {
	  public String key;
	  public int val;
	  public Node prev;
	  public Node next;
	  
	  public Node()
	  {
		  this.key = "";
		  this.val = 0;
		  this.prev = null;
		  this.next = null;
	  }
	  
	  public Node(String key, int val, Node prev, Node next)
	  {
		  this.key = key;
		  this.val = val;
		  this.prev = prev;
		  this.next = next;
	  }
  }
	
	
  static class LRUCache {
    int maxSize;
	HashMap<String, Node> hm;
	Node headSentinel;
	Node tailSentinel;

    public LRUCache(int maxSize) {
      this.maxSize = maxSize > 1 ? maxSize : 1;
	  this.hm = new HashMap<String,Node>();
	  this.headSentinel = new Node();
	  this.tailSentinel = new Node();
	  headSentinel.next = tailSentinel;
	  tailSentinel.prev = headSentinel;
	}

	  // Inserting value into existing key
	  // Seperate subroutines in place of conditional blocks
	  // [1] DELETION and then [2] INSERTION AGAIN ( if (K,V) pair exists ) 
	  // [1] INSERTION ( (K,V) pair DNE ) 
    public void insertKeyValuePair(String key, int value) 
	{
		// Check if key exists already
		if(hm.containsKey(key))	// not just a single sentinel node case : (SENTINEL->NODE)
		{
			hm.get(key).val = value;
			Node underRef = hm.get(key);
			Node before = underRef.prev;
			Node after = underRef.next;
			before.next = after;
			after.prev = before;
			
			// Set the head here to new node
			Node trueHead = headSentinel.next;
			trueHead.prev = underRef;
			underRef.next = trueHead;
			headSentinel.next = underRef;
			underRef.prev = headSentinel;
		}
		else if ( !hm.containsKey(key))
		{
			// Exec size check : if at capacity, set tail accordingly
			if(hm.keySet().size() == this.maxSize)
			{
				// Evict final entry
				Node tail = tailSentinel.prev;
				hm.remove(tail.key);
				Node before = tail.prev;
				tail.prev = null;	// FOR JVM Garbage collection contexts
				tail.next = null;
				before.next = tailSentinel;
				tailSentinel.prev = before;
			}
			// Now assume cache is not at capacity limit
			Node head = headSentinel.next;
			Node toInsert = new Node(key, value, head, headSentinel);
			head.prev = toInsert;
			headSentinel.next = toInsert;
			hm.put(key, toInsert);
			// Node tail = tailSentinel.prev;
			// Node toInsert = new Node(key, value, tail, tailSentinel);
			// tail.next = toInsert;
			// tailSentinel.prev = toInsert;
			// hm.put(key, toInsert);
		}
    }

    public LRUResult getValueFromKey(String key) 
	{
		
		if(!hm.containsKey(key))
			return new LRUResult(false, 0);	// primitives can not point to null
		else
		{
			insertKeyValuePair(key,hm.get(key).val);
			return new LRUResult(true, hm.get(key).val);
		}
    }

    public String getMostRecentKey() 
	{
		// Check if LRU cache has entries
		if(hm.keySet().size() == 0)
			return null;
  		// HEAD = sentinel.next
		return headSentinel.next.key;
    }
  }

  static class LRUResult {
    boolean found;
    int value;

    public LRUResult(boolean found, int value) {
      this.found = found;
      this.value = value;
    }
  }
}





