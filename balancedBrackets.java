
( { [
Write a functino that takes a string made of brackets - (), {}, [] - balance them out
Size constraints : ( 1 <= len <= 256 )
Each opening bracket must have a pair which closes it ( a closing bracket ) 
If each char has a pair -> return true
Else -> return false

string = "([])(){}(())()()"
true

string2 = "(([])"
false
string3 = "a"
Can have characters with no paran -> no empty/null strings
Can have special characters, @ symbols / ASCII characters

"( { ["
TEST BENCH : 
(A) "a" ( zero # pairs here )
	TRUE 
(B) "(a)", "[c(abc...wer)d]"	=> "()","[()]" equivalents
	TRUE
(C) "[](){@}"
	TRUE
(D) "((((((("
(E) ")))))))""
(F) "()()[][]{}{}" 

Single bracket - utilizes a stack : open-close paranthesis
Can we extent on top of that

Complexity
Let N := len(string)
Time = O(N)
Space = O(N)

Pseudocode : 

public static boolean balancedBrackets(String str)
{
	boolean isBalanced = true;
	if(str.length() == 0)
	{
		return true;
	}
	else
	{
		char[] c_arr = str.toCharArray();
		Stack<Character> stk = new Stack<Character>();
		HashMap<Character,Character> pairs = new HashMap<Character,Character>();
		pairs.add(')','(');
		pairs.add('}','{');
		pairs.add(']','[');
		for(int i = 0; i < c_arr.length; ++i)
		{
			char myC = c_arr[i];
			if(myC == '(' || myC == '{' || myC == '[')
			{
				stk.push(myC);
			}
			else if ( myC == ')' || myC == '}' || myC == ']')
			{
				if(stk.size() > 0)
				{
					Character topMost = stk.pop();
					if(topMost != pairs.get(myC))
					{
						isBalanced = false;
						break;
					}
				}
				else
				{
					isBalanced = false;
					break;
				}
			}
		}
	}
	return isBalanced;
}

ASCII mapping : ) -> (, } -> {
	How does conditional logic scale if say 20 unique bracket pairings as well?

Why a stk here?
	""(())""		['(','(',')',')']
								0		1		2		3
	Support a recursive-like processing
								
								
import java.util.*;

class Program {
		public static boolean balancedBrackets(String str)
		{
			if(str.length() == 0)
				return true;
			return checkBalancedBrackets(str);
		}
	
		private static boolean checkBalancedBrackets(String str)
		{
				char[] c_arr = str.toCharArray();
				Stack<Character> stk = new Stack<Character>();
				// HashMap<Character,Character> pairs = constructBracketPairsMap();
				for(int i = 0; i < c_arr.length; ++i)
				{
					char myC = c_arr[i];
					if(myC == '(' || myC == '{' || myC == '[')
					{
						stk.push(myC);
					}
					else if ( myC == ')' || myC == '}' || myC == ']')
					{
						if(stk.size() > 0)
						{
							Character topMost = stk.pop();
							if(myC == ')' && topMost != '(')
								return false;
							else if(myC == '}' && topMost != '{')
								return false;
							else if(myC == ']' && topMost != '[')
								return false;
							// if(topMost != pairs.get(myC))
								// return false;
						}
						else
							return false;
					}
			}
			if(stk.size() != 0)
				return false;
			return true;
		}
	
		private static HashMap<Character,Character> constructBracketPairsMap()
		{
				HashMap<Character,Character> pairs = new HashMap<Character,Character>();
				pairs.put(')','(');
				pairs.put('}','{');
				pairs.put(']','[');
				return pairs;
		}
	}





