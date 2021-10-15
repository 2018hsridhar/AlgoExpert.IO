Thought Process : 
You know how to dial a number
You have digits and assoc characteres on a dialpad
Given an integer phone number -> return all possible word combinations
	


 ----- ----- -----
  |     |     |     |
  |  1  |  2  |  3  |
  |     | abc | def |
   ----- ----- -----
  |     |     |     |
  |  4  |  5  |  6  |
  | ghi | jkl | mno |
   ----- ----- -----
  |     |     |     |
  |  7  |  8  |  9  |
  | pqrs| tuv | wxyz|
   ----- ----- -----
        |     |
        |  0  |
        |     |
				
				
				
				phoneNumber = "1905"
				[
  "1w0j",
  "1w0k",
  "1w0l",
  "1x0j",
  "1x0k",
  "1x0l",
  "1y0j",
  "1y0k",
  "1y0l",
  "1z0j",
  "1z0k",
  "1z0l",
]
			
			phoneNumber = "8"
			[
			"t",
			"u",
			"v"
			]


Pseudo-code :

Precomputation step
Do not map non-entries ( 1/0 ) 
HashMap<Integers, List<Character>>
	2=>{'a','b','c'}
	9=>{'w','x','y','z'}
	8 digits, 26 letters max -> constant space data structure O(1)
Recursive calls : subproblems := rest of the phone after characters analyzed 1 by 1
	max 4 choices/node ( avg = 3 )
	

Complexity : 
Let N := #-digits in phone number ( varying ) 
	Always valid input ( can be 0 )
Time = O(4^n)
Space = O(n) - implicit

Edge Case Testing : 
(A) "9"
	'w','x','y','z'
(B) "000"
	000
(C) "111"
	111
(D) "9999"
(E) "0918"
	0[wxyz]1[tuv]



import java.util.*;

class Program 
{
	
	static HashMap<Character,List<Character>> mappings = new HashMap<Character,List<Character>>(8);

  public ArrayList<String> phoneNumberMnemonics(String phoneNumber) 
	{
    ArrayList<String> mnemonics = new ArrayList<String>();
		int n = phoneNumber.length();
		if(n == 0) 
			return mnemonics;
		createPhoneNumbersHash();
		dfs(phoneNumber, 0, new StringBuilder(""), mnemonics);
		return mnemonics;
  }
	
	// int array -> characters as well
	private void createPhoneNumbersHash()
	{
		// Can not instantiate to an interface : only declare
		mappings.put('0', Arrays.asList('0'));
		mappings.put('1', Arrays.asList('1'));
		mappings.put('2', Arrays.asList('a','b','c'));
		mappings.put('3', Arrays.asList('d','e','f'));
		mappings.put('4', Arrays.asList('g','h','i'));
		mappings.put('5', Arrays.asList('j','k','l'));
		mappings.put('6', Arrays.asList('m','n','o'));
		mappings.put('7', Arrays.asList('p','q','r','s'));
		mappings.put('8', Arrays.asList('t','u','v'));
		mappings.put('9', Arrays.asList('w','x','y','z'));
	}
	
	 // "2" -> dfs(a,1,.p..) : 0 = start index
	private void dfs(String phoneNumber, int idx, StringBuilder mnemonic, ArrayList<String> results)
	{
		if(idx == phoneNumber.length())
		{
			results.add(mnemonic.toString());
			return;
		}
		else
		{
			char c = phoneNumber.charAt(idx);
			List<Character> digits = mappings.get(c);
			for(Character digit : digits)
			{
				StringBuilder nextLevel = new StringBuilder(mnemonic.toString());
				nextLevel.append(digit);
				dfs(phoneNumber, idx+1, nextLevel, results);
			}
		}
		return;
	}
	
}
