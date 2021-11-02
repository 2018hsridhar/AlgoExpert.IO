/*
Function takes in a non-empty string representing a valid UNIX-shell path
Return the SHORTERED/ABRIDGED version of said path
Paths may be absolute paths or relative paths 

/ := the directory seperator
.. := the parent directory
. := the current directory
Once at root directory, future parent directory accesses perform no operation as well
/ ../../..

Yes operations (.) and (..) are followed by the directory seperator '/'


> Common pattern : long->short path, and both point to the same shared element
Abriged path and long path must point to the same file

COMPLEXITY
Let N := length of path
Let D := the depth of thy path
Time = 
Space = 

TEST BENCH
(A) "/foo/../test/../test/../foo//bar/./baz"
(B) "/foo/test/bar/../baz"
(C)  ../../foo/bar/baz
(D)  ../../foo/../../bar/baz


*** hone in here ***
(E) "/../../../this"
(F) "../../../this"


(G)

Strategies : token splitting ( /, .., . ), string parsing, stack
Symbol "/" can be repeated sequentially SANS CONSEQUENCE ( sa too, . ). But not ..


*/
import java.util.*;

class Program 
{
  public static String shortenPath(String path) 
	{
		String[] tokens = path.split("/");
		Stack<String> stk = new Stack<String>();
		int n = tokens.length;
		char firstChar = path.charAt(0);
		for(int i = 0; i < n; ++i)
		{
			String token = tokens[i];
			if(token.equals(".."))	// you still have to push this ( even if stk size > 0 : say ../../../ case : check topmost element)
			{
				if(stk.size() > 0)
				{
					String topMostEl = stk.peek();
					if(topMostEl.equals(".."))
						stk.push("..");
					else
						stk.pop();
				}
				else
				{
					if(firstChar != '/')
						stk.push("..");
				}
			}
			else if ( token.equals("."))
			{
				continue;				
			}
			else
			{
				if(!token.equals(""))
					stk.push(token);
			}
		}
		
		int finalNumTokens = stk.size();
		if(finalNumTokens == 0)
			return "/";
		String[] results = new String[finalNumTokens];
		int wIdx = results.length - 1;
		while(!stk.isEmpty())
		{
			String elem = stk.pop();
			results[wIdx] = elem;
			wIdx--;
		}
		
		String resultant = String.join("/", results);
		StringBuilder sb = new StringBuilder("");
		if(!results[0].equals("..") && !results[0].equals("."))
		{
			if(path.charAt(0) == '/')
				sb.append("/");
		}
		sb.append(resultant);
		return sb.toString();
		
		
		
  }
}
