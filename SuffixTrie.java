/*

THOUGHT PROCESS : 
1. Suffix trie is highly akin to the Prefix trie class ( it does use those type of trie nodes ) 
2. The mapping for children tends to remain what is is -> never changes
3. Not sure whether these nested classes have to be static or not as well

COMPUTATIONAL COMPELXITY: 

Let N := len(string)
Let K := len(suffix) ( longest = N anyways ) 
Time = O(NK)
Space = O(1) explicit O(K) call stack

TEST BENCH : 
(A)
(B)
(C)

*/

import java.util.*;

class Program 
{
  // Do not edit the class below except for the
  // populateSuffixTrieFrom and contains methods.
  // Feel free to add new properties and methods
  // to the class.
  static class TrieNode 
	{
		char rootLet = ' ';	// is not default initialized in this class though! ( sadly no empty character literal )
    Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
		
		public TrieNode()
		{
			
		}
		
		public TrieNode(Character newChar)
		{
			rootLet = newChar;
		}
		
  }

	// You forgot to code up the '*' terminator as well hfere!
	// But where is this boolean even populated?
  static class SuffixTrie 
	{
    TrieNode root = new TrieNode();
    char endSymbol = '*';

    public SuffixTrie(String str) {
      populateSuffixTrieFrom(str);
    }

		// Most string substring algorithms will incorporate the two pointers technique underneath as well
		// Populating a tree always commences from the root as well
		// O(N) Time, O(1) Space
		// Is cool cuz the HM is dynamic itself : two levels of indirection taking place
    public void populateSuffixTrieFrom(String str) 
		{
			int n = str.length();
			for(int i = n-1; i >= 0; --i)
			{
				int left = i;
				int right = n;
				String suffix = str.substring(left,right);
				System.out.printf("Inserting suffix = %s\n", suffix);
				// if(!contains(suffix))
				// {
					insertSuffixIntoTrie(suffix);
				// }
			}
    }
		
		// O(K) time, O(1) space, where k := len(string)
		// There is a problem : it is akin to a prefix tree, but the initial suffixes are empty as well
		// The initial trie node is a sentinel node : denote with the '' empty character literal
		private void insertSuffixIntoTrie(String suffix)
		{
			// Check the first parent / the root of our suffix tree first
			int n = suffix.length();
			char lastLet = suffix.charAt(n - 1);
			if(root.children.get(lastLet) == null)
			{
				root.children.put(lastLet, new TrieNode(lastLet));
			}
			
			// Your code is a parent -> child check . Take close note of this
			// Insertion must still work in the event of a leaf / terminal node as well too
			TrieNode curParent = root;
			for(int i = 0; i < n; ++i)
			{
				char ch = suffix.charAt(i);
				// Ok so the current parent letter = ch, BUT, it might not have this child as well too!
				TrieNode childTree = curParent.children.get(ch);
				if(childTree == null)
				{
					childTree = new TrieNode(ch);
					curParent.children.put(ch, childTree);
				}
				curParent = childTree;	// continue iteration after an addition to a leaf node here
			}
			
			// NOw at the leaf node : let us go add a (*) as well :-)
			curParent.children.put('*', null);	// It needs to be null : NOT a new trieNode ( woah ) !
		}

    public boolean contains(String str) 
		{
			char starSymbol = '*';
			boolean contains = true;
			TrieNode curParent = root;
			int n = str.length();
			for(int i = 0; i < n; ++i)
			{
				char ch = str.charAt(i);
				TrieNode child = curParent.children.get(ch);
				if(child != null)
				{
					curParent = child;
				}
				else
				{
					contains = false;
					break;
				}
			}
			if(curParent == null)
			{
				return false;
			}
			if(!curParent.children.containsKey('*'))
				 contains = false;
			return contains;
    }
  }
}
