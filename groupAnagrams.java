import java.util.*;

class Program {
  public static List<List<String>> groupAnagrams(List<String> words) 
	{
		if(words == null || words.size() == 0)
		{
	    return new ArrayList<List<String>>();
		}
		List<List<String>> anagrams = new ArrayList<List<String>>();
		HashMap<String,List<String>> groups = new HashMap<String,List<String>>();
		for(String word : words)
		{
			String rle = generateRLEKey(word);
			if(groups.containsKey(rle))
			{
				groups.get(rle).add(word);
			}
			else
			{
				ArrayList<String> newList = new ArrayList<String>();
				newList.add(word);
				groups.put(rle,newList);
			}
		}
		
		Set<String> keys = groups.keySet();
		Iterator<String> key_itr = keys.iterator();
		while(key_itr.hasNext())
		{
			String key = key_itr.next();
			anagrams.add(groups.get(key));
		}
		
		return anagrams;
  }
	
	private static String generateRLEKey(String word)
	{
		StringBuilder rle = new StringBuilder("");
		int[] freqCount = new int[26];
		for(int i = 0; i < word.length(); ++i)
		{
			char c = word.charAt(i);
			freqCount[(int)(c-'a')]++;
		}
		for(int i = 0; i < 26; ++i)
		{
			if(freqCount[i] > 0)
			{
				rle.append(freqCount[i]);
				rle.append((char)(i + 'a'));
			}
		}
		return rle.toString();
	}

	
	
}

Write a function that takes in an array of strings and groups anagrams together.

Anagrams are strings made up of exactly the same letters, 
where order doesn't matter. For example, "cinema" and "iceman" are anagrams; 
similarly, "foo" and "ofo" are anagrams.

Your function should return a list of anagram groups in no particular order.


PSEUDOCODE :

words = ["yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp"]


Groups
	{
		'1o1y' => {"yo","oy"},
		'1a1c1t' => {"act","tac","cat"},		=> "act"
		'1f1l1o1p' => {"flop","olfp"}
		'1f1o1o' => {"foo"}
	}

	// [1] Generate the anagrams hashmap
def List<> groupAnagrams(words):
	List<List<String>> anagrams = ()
	HM groups <String rle, List{words}>
	for each word in words:
			String rle = generateRLEKey(word)
			if groups containsKey(rle):
				groups.get(rle).add(word)
			else:
				groups.put(rle,new List(word))
				
	// [2] Generate the anagrams list from the grouped anagrams mapping
	for each (key,val) in groups:
		anagrams.add(val)
	
	ret anagrams


a -> '97', z ->'123' in ASCII ( CaeserCipher )
[a,z] -> decrement 'a' = 97
0 to 25 here

// [1,0,1,0,....,0]
		a,b,c,d,...,z
Run-length_encoding
https://en.wikipedia.org/wiki/
Run-length_encoding#:~:text=Run%2Dlength%20encoding%20(RLE),than%20as%20the%20original%20run.

// O(m) 
def generateRLEKey(String word):
	rle = ""
	zero initialized freqCount[26] array
	for each character 'c' in word
		freqCount[(int)(c-'a')]++
	for each index in freqCount
		if freqCount[index] > 0:
			rle += freqCount
			rle += (char)(i + 'a');
	ret rle

RLE : 
	"aaabbc"
	int[] freqCount = new int[26];
	{
		a=>3,
		b=>2
		c=>1
	}
	iterate over each key here
	3a2b1c
	
	"cbaaab"
	
	HM
	{
		"3a2b1c"	=> {"aaabbc","cbaaab"}
	}
	
	
Input:
words = ["yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp"]

key:
	

yo
{
	y=>1,
	o=>1
},
oy
{
	o=>1,
	y=>1
}

Sample Output
[["yo", "oy"], ["flop", "olfp"], ["act", "tac", "cat"], ["foo"]]
		GROUP_1				GROUP_2						GROUP_3							GROUP_4
		
Strategies : Iterative, Hashing/ID scheme, Character Frequency Counting
		
Complexity
Let W := #-words in the input
Let M := max length of a word
Let G := #-unique groups
TIME = O(W*M) + O(G)
SPACE = O(W) ( expclit ) 

Clarifications
- Assume list fits in RAM
- Handle invalid user inputs
- Lowercase English Alphabets [a-z]

Test Cases :
(A) ["aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","aa","a"]
			~
(B) ["cat","atc","tca"]
	=> {"cat","atc","tca"}
(C) ["a"]
(D) ["a","a","a"]
(E) ["cat","atc","tca","ab","ba"]

["yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp"]


