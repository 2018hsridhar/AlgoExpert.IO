/*

( *** crucial case *** )
"xyxxxyyx"
"baddaddoom baddaddoomi baddaddoom baddaddoom baddaddoom baddaddoomi baddaddoomi baddaddoom"  ~ this is the valid case
"baddaddoombaddaddoom i baddaddoombaddaddoom baddaddoombaddaddoom i baddaddoom i baddaddoom"	~ this is invalid as a case

					  =========== ( true y test values )
						
					 ^ ( what we might have conjured up earlier ) 
How to handle? X = "baddaddoom" , but Y = "baddaddoomi" here
How to pattern match like this? Prefix matching can most def fail with "baddaddoom" in our test here

We have an inefficient pairwise substring solution here for sure
"ba", ["d","dd","dda"] type of testing is a possibility
Also stirng concatenation/memory alloc is a bit of an expensive operation.
It is a bit combinatorial in nature too.

Length(original) = 
Ok : so count(x) = 5 && count(y) = 3
x := baddaddoom => repeat it 5 times => len = 10 * 5 = 50
baddaddoombaddaddoomibaddaddoombaddaddoombaddaddoombaddaddoomibaddaddoomibaddaddoom
	=> len = 83 characters
	83 - 50 = 33 / 3 = 11 : evaluate this, since it is an integral value, to the substring following (baddaddoom ) = 
	baddaddoomi here
	Length arg may have an edge case, depending on how "x" and "y" values were scattered as well
	We must do this up to when we hit a non-prefix letter case ( e.g. if we repl x in "xxxy", we do it at the 4th position here only -> not after the first )
	So count up to that as well

*/

import java.util.*;

class Program {
  public static String[] patternMatcher(String pattern, String str) 
  {
		char firstLetPattern = pattern.charAt(0); // may be 'x' or 'y'
		int n = str.length();
		// Nested for loop : generate all prefixes
		for(int i = 0; i < n; ++i)
		{
			// Get the 2 seperate substrings -> then map to symbol
			StringBuilder firstStr = new StringBuilder("");
			StringBuilder secondStr = new StringBuilder("");
			String prefix = str.substring(0,i+1);
			int initIndex = 0;
			int len = prefix.length();
			int foundIdx = str.indexOf(prefix, initIndex);
			boolean foundGap = false;
			boolean foundTwoStrings = false;

			firstStr.append(prefix);
			
			int xCount = 0;
			int yCount = 0;
			for(int k = 0; k < pattern.length(); ++k)
			{
				char ch = pattern.charAt(k);
				if(ch == 'x')
				{
					++xCount;
				}
				else if ( ch == 'y')
				{
					++yCount;
				}
			}
			
			if(xCount == 0 && yCount != 0 || xCount != 0 && yCount == 0)
			{
				foundTwoStrings = false;
			}
			else
			{
				foundTwoStrings = true;
			}

			// The second substring can be repeated as well ( e.g. yomama )
			// Also check if foundIdx got all the way down here
			// Check whether a string was filled here
			// Always equal to prefix ( the first string)
			// For those failing test cases : we may have to do one replacement strictly ... NOT two replacements
			// as gaps are NOT correctly accounted for from earlier as well
			
			String test = pattern;
			if(foundTwoStrings == false)
			{
				// only one string ( the prefix )
				if(firstLetPattern == 'x')
				{
					String replaced = test.replaceAll("y","").replaceAll("x",firstStr.toString());
					if(replaced.equals(str))
					{
						return new String[] {firstStr.toString(), ""};
					}
				}
				else
				{
					String replaced = test.replaceAll("x","").replaceAll("y",firstStr.toString());
					if(replaced.equals(str))
					{
						return new String[] {"", firstStr.toString()};
					}
				}
			}
			else if ( foundTwoStrings == true ) // aim for more "else-if" statements than "else" : more descriptive to someone foreign to code
			{				
				// two strings
				// .replaceAll is NOT an in-place modifications ( String immutable ... dang ) 
				if(firstLetPattern == 'x')
				{
					int c = 0;
					while(c < pattern.length())
					{
						if(pattern.charAt(c) == 'y')
						{
							break;
						}
						++c;
					}
					int remLen = str.length() - (xCount * prefix.length());
					if(remLen <= 0)
					{
							return new String[] {};
					}
					int lenY = remLen / yCount;
					if(remLen % yCount != 0)
					{
						continue;
					}
					else
					{
						int yIdx = 0 + (prefix.length() * c);
						if(yIdx >= str.length() || (yIdx + lenY) >= str.length())
						{
							break;
						}
						secondStr.append(str.substring(yIdx, yIdx + lenY));
						String replaced = test.replaceAll("x",firstStr.toString()).replaceAll("y",secondStr.toString());
						if(replaced.equals(str))
						{
							return new String[] {firstStr.toString(), secondStr.toString()};
						}	
					}					
				}
				else if(firstLetPattern == 'y')
				{
					int c = 0;
					while(c < pattern.length())
					{
						if(pattern.charAt(c) == 'x')
						{
							break;
						}
						++c;
					}
					int remLen = str.length() - (yCount * prefix.length());
					if(remLen <= 0) break;
					int lenX = remLen / xCount;
					if(remLen <= 0)
					{
							return new String[] {};
					}
					else
					{
						int xIdx = 0 + (prefix.length() * c);
						if(xIdx > str.length() || xIdx + lenX > str.length())
						{
							break;
						}
						secondStr.append(str.substring(xIdx, xIdx + lenX));
						String replaced = test.replaceAll("y",firstStr.toString()).replaceAll("x",secondStr.toString());
						if(replaced.equals(str))
						{
							return new String[] {secondStr.toString(), firstStr.toString()};
						}	
					}		
				}
			}
		}
		return new String[] {};
  }

}
