/*
input: "AAAAAAAAAAAAABBCCCCDD"
output: "9A4A2B4C2D"
Funcs takes a non empty string
Returns its run-length encoding
A form of loseless data compression
Store data as (data:count) strings
AAA => 3A

Input string can contain special characters ( includes numbers )
Encoded data must be decodable -> can run locally

"AAAAAAAAAAAA"
"9A3A" in lieu of "12A" : only single-digit allowed ( NO double-digits )
"AAAAAAAAAAAAAA....AAA" ( 100 times )
"9A9A9A9A....(11 times)1A

Can string be a null string or empty?
	-> if null / empty => return empty string

input:@@@@@
output:5@

input:(15 1s ) 
output:9161

No need to read it / decode it here
Upper bound NOT specified ( assume it be reasonable ) 

Complexity : 
Let N := number of characters in input string
Worst case setting : every character in string is unique "abcd...xyz0...9"
Time = O(N*K) K = max digit  ( while loop )
	Possible to O(N) algo here
Space = O(1)

Pseudo-code : 
input: "AAAAAAAAAAAAABBCCCCDD" => {A,B,C,D}
Denote s = string ( array ) 
StringBuilder sb("");
int curFreq
char myC
for(int i = 0; i < len(s) - 1; ++i) // avoided Index OOB exception
	curFreq = 1;
	myC = s[i]
	if(s[i] == s[i+1])			// "AA" => [0,1]
		++curFreq;
	else
		while(curFreq > 9)
			sb.append(9)
			sb.append(myC)
			curFreq -= 9;
		sb.append(curFreq)
		sb.append(myC)
		curFreq = 1;	
while(curFreq > 9)				// dangling last index ( or else condition never executed )
	sb.append(9)
	sb.append(myC)
	curFreq -= 9;
sb.append(curFreq)
sb.append(myC)
curFreq = 1;	
		
return sb.toString();		
		
Edge Case
(A) "A"
(B) "AA"							ELSE never executes
(C) "abcd...xyz0...9"				IF never executes
(D) "xyz"
(E) "abababab"
(F) "         aaaaa" => return 9' '9' '5a
(G) "AAAAAAAAAAAAAAAAAAAAAAAAAAA"
82 times : 82/9 = 9, 82%9 = 1
15 times : 15/9 = 1, 15%9 = 6


for i in 1, stringlen : 
	if string[i-1] != string[i] or counter == 9: 
		clear counter 
		clear value
		
		append to result 
		
result = append to result 
return result 

| := read up to 9 of same characters
AAA...|.AAAA|...AA|A

Why did you do this divison with string.repeat() weird logic??
*/

import java.util.*;

class Program {
  public String runLengthEncoding(String string) 
  {
	  if(string == null || string.length() == 0)
		  return "";
	  StringBuilder sb = new StringBuilder("");
	  int n = string.length();
	  int curFreq = 1; // default initialization
	  char myC = 0;
	  for(int i = 0; i < (n-1); ++i)
	  {
		  myC = string.charAt(i);
		  if(string.charAt(i) == string.charAt(i+1))
			  ++curFreq;
		  else
		  {
			while(curFreq > 9)
		  	{
		    	sb.append(9);
				sb.append(myC);
				curFreq -= 9;
		    }
			if(curFreq > 0)
			{
				sb.append(curFreq);
				sb.append(myC);
			}
		    curFreq = 1;
		  }
	  }
	  myC = string.charAt(n-1); 
	  while(curFreq > 9)
	  {
		  sb.append(9);
		  sb.append(myC);
		  curFreq -= 9;
	  }
	  if(curFreq > 0)
	  {
		  sb.append(curFreq);
		  sb.append(myC);
	  }
	  
	  return sb.toString();
  }
}
