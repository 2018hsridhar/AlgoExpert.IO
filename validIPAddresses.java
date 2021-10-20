/*

String length is guaranteed to be fewer than 12 charactrers long
Consists only of digits
Return all possible IP addressses created by insert three "." delimeters in the string
	( seems recursive/backtracking -> combinations ) 
End ordering does not matter
Digits may be repeatable 
Leading zeroes are invalid CASES

	
COMPLEXITY
Depth of tree -> 4 for sure
Number of children -> 3
Desire a substring conversion to its integer equivalent : but check if first char is a zero, as well as checking
if first character = last character = 0

Let S := len(string)
Time = 
Space =

TEST BENCH
(A) "1921680"
(B) "0000"
(C) "1111"
	[1.1.1.1]
(D) "11111"
	[1.1.1.11, 1.1.11.1,1.11.1.1,11.1.1.1]
(E) "00192168"
	["0.0.192.168"]
(F) "0192168"
	["0.1.92.168", "0.192.168"]
(G) 19216801 [ local host IP]
(H) 127.0.0.1 [ the local host IP ]

Must meet the three "." conditions as well
Can have leading zeroes ( BUT they are singleton zeroes ) . Not more than that though ( e.g. 00.192.168.1 )

___.___.___.___ = IPv4 address ( longest = 3 digit each portion ) 
_._._._ ( 1 digit each )
*/

Pseucode : 

List<String> mySet = ips(s,4);
Identify base cases ( always )




ArrayList<String> ips(String s, int depth)
	ArrayList<String> addrs = new ArrayList<String>();
	int n = s.length();
	
	if(depth == 0)
		return new ArrayList<String>();
	
	// generate the set of valid prefixes ahead of time
	// Work off the substring, provided that is is valid as well !
	int s = 0;
	for(int e = 1; e <= 3; ++e)
	{
		if(s.length() > e)
		{
			// generate a sublist of the subaddresses ( from fewer periods ) 
			String prefix = s.substring(0,e);
			ArrayList<String> subAddrs;

			if(prefix.length() > 1 && prefix.charAt(0) == '0') // that dumb zero case
			{
				continue;
			}
			else
			{
				Integer val = Integer.parseInt(prefix);
				if(( 0 <= val.intValue() && val.intValue() <= 255 ) && depth == 1)
				{
						String suffix = s.substring(e+1);
						if(suffix.length() == 0)
						{
							addrs.append(prefix);
						}
				}
				else if ( depth > 1)
				{
						if(0 <= val.intValue() && val.intValue() <= 255)
						{
							subAddrs = ips(s.substring(e+1),depth-1);						
						}

						// Append the prefix now
						if(subAddrs != null || subAddrs.length != 0)
						{
							for(String subDomain : subAddrs)
							{
								StringBuilder parentIP = new StringBuilder(prerfix);
								parentIP.append(subDomain);
								parentIP.append('.');
								addrs.add(parentIP.toString());
							}
						}
				}
			}
		}
	}
	return addrs;
	
Base case is actually @ depth = 1 : not @ depth = 0
depth = 1 is information enough : we can not go further

"1111"
	111.ips("1",2)
		<- empty
	<- empty

ips("1921680",4)
	1.+ips("921680",3)
		1.9.+ips("21680",2)
			1.9.2+ips("1680",1)
				1.9.2 prefix = 1				-> "680" be suffix
				1.9.2 prefix = 16		-		> "80" be suffix
				1.9.2 prefix = 168			-> "0" be suffix
				<- {}
			<-{}
		<-{}
	<-{}
			1.9.21+ips("680",1)
			1.9.216+ips("80",1)
		1.92.+ips("16801",2)
			1.9.168+ips("01",1)
				1.9.168.01+ips("",0)
		1.921.+ips("6801",2) X
	19.+ips("216801",3)
	192.+ips("16801",3)

Even though depth = 0, we can NOT really add here : so do not append the prefix
See this is where to exert caution -> prefix or suffix adding

We can seperate out the code for IPv4 address validation -> can entail extra work though!

Complexity : 
Time = O(1)
Space = O(1)

*/

import java.util.*;

class Program 
{

  public ArrayList<String> validIPAddresses(String string) 
	{
		ArrayList<String> res = new ArrayList<String>();
		if(string == null || string.length() == 0)
	    return new ArrayList<String>();
		res = ips(string, 4);
		return res;
  }
	
	private ArrayList<String> ips(String input, int depth)
	{
			System.out.printf("input = %s \t depth = %d\n", input, depth);
			ArrayList<String> addrs = new ArrayList<String>();
			int n = input.length();

			if(depth == 0)
				return new ArrayList<String>();

			// generate the set of valid prefixes ahead of time
			// Work off the substring, provided that is is valid as well !
			int s = 0;
			for(int e = 1; e <= 3; ++e)
			{
				if(e <= input.length())
				{
					// generate a sublist of the subaddresses ( from fewer periods ) 
					String prefix = input.substring(0,e);
					ArrayList<String> subAddrs;

					if(prefix.length() > 1 && prefix.charAt(0) == '0') // that dumb zero case
					{
						continue;
					}
					else
					{
						Integer val = Integer.parseInt(prefix);
						if(( 0 <= val.intValue() && val.intValue() <= 255 ) && depth == 1)
						{
								String suffix = input.substring(e);
								System.out.printf("@ Depth = 1 \t suffix = %s\n", suffix);
								if(suffix.length() == 0)
								{
									addrs.add(prefix);
								}
						}
						else if ( depth > 1)
						{
								if(0 <= val.intValue() && val.intValue() <= 255)
								{
									subAddrs = ips(input.substring(e),depth-1);						
									// Append the prefix now
									if(subAddrs != null || subAddrs.size() != 0)
									{
										for(String subDomain : subAddrs)
										{
											StringBuilder parentIP = new StringBuilder(prefix);
											parentIP.append('.');
											parentIP.append(subDomain);
											addrs.add(parentIP.toString());
										}
									}
								}
						}
					}
				}
			}
			return addrs;
	}
	
}
