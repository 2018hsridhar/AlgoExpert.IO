/*
words = ["this", "that", "did", "deed", "them!", "a"]
					0				1				2				3				4				5
					
Given an input array of strings/words
Goal : output an array of characters where you show the minimal chars required 
in order to build the input array
Longes word ~ 50 chars

["t", "t", "h", "i", "s", "a", "d", "d", "e", "e", "m", "!"]
Assume input reasonably sized
Can have empty strings
Output order does not matter

"t,d" -> repeated twice
"that" -> 2 t's
"them,this" -> 1 t
"did,deed,a" -> 0 ts
Never missing one letter : scrabble
"t,h,a,l" -> "thl", "that", "this"


Empty/null inputs -> must handle
words = "["this","this","this"]"

Can have [a-z], punction, [A-Z], [0-9] : ASCII characters
TEST BENCH : 

(A) null
(B) []
(C) ["this","this","this"]
		["t","h","i","s"]
(D) ["0","981","89"]
	["0","1","8","9"]
(E) ["", "a", "ba"]
(F) ["97@","$!@", "abc$"]
(G) ["abcde...xyz"]
	["a","b","c",..."z"]
(H) ["ab","cd","ef","ghi","jklm"]
(I) ["aaaaaa"]

Hashmap<Character,Integer> (K,V) use an Array[ASCII->idx]: 

words = ["this", "that"]
					0				1			
				
				["t","h","i","s"]
				["a","t"]
				Maximal character frequency of a given character in a word
				
"this"
[
	't' -> 1
	'h' -> 1
	'i' -> 1
	's' -> 1
]

"that"
[
	't' -> 2
	'h' -> 1
	'a' -> 1
]

MAX(('that','h'),('this',h')) = MAX(1,1) = 1
MAX(('that','t'),('this',t')) = MAX(2,1) = 2

Compare hashmaps as we go

Expected result 
[
	't' -> 2
	'h' -> 1
	'a' -> 1
	'i' -> 1
	's' -> 1
]


Complexity : 
Let N := length(words)
Let K := length(longest_word)
Let M := #-unique characters/symbols in our language ( we know it's 255/127 for ASCII ) 
	O(M) ~= O(1)
Time = O(NK) -> POLY
Space = O(1)

// https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/ASCII-Table.svg/1261px-ASCII-Table.svg.png
*/

Pseudocode : 

char[] minimumCharactersForWords(String[] words)

	// Seperate method
	HM<> minimalSet;
	for(String word : words)
	{
		HM<> symbolFreq = getSymbolFreqs(word);
		for(key let : symbolFreq)
		{
			if(minimalSet.containsKey(key))
			{
				int freq = symbolFreq.get(key);
				minimalSet.put(key, Math.max(minimalSet.get(key), freq));
			}
			else
			{
				minimalSet.put(key,1);
			}
		}
	}
	
	// Know the size of the keyset at this point
	// conversion method
	int m = minimalSet.size();
	char[] res;
	int i = 0;
	for(key let : minimalSet)
	{
		for(int i  = 0; i < minimalSet.get(let); ++i)
		{
			res[i] = let;
			++i
		}
	}
	return res;
	
