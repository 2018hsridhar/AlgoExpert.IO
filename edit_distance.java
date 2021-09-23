​Sample Input
str1 = "abc"
str2 = "yabd"

2 // insert "y"; substitute "c" for "d"

One direction 
Transitivie property : does goes both way
Return total number of operations to change ( str1=>str2 )
abc -> yabc -> yabd
	-> add -> substitute
	
Return the total minimum

Empty/null inputs -> how to handle it?
-> if empty : 

Let's assume the string is lowercase english alphabet only [a-z] ASCII
I think your algo should be able to generalize to any charcter encoding or additional symbols.



/*
Recursive function : must define a base case/terminating case 

recurse("abcde") recursively from left to right -> how do you terminate?
	print(a) + recurse("bcde")
		print(b) + recurse("cde")
		print(e) { single character } : done 

Try recursively -> #-OUTGOING-EDGES per recursive call

fib(n) = fib(n-1) + fib(n-2) where n >= 2 :
we have two children/two edges in the recursive call stack
fib(5)
	fib(4)
		fib(3)
		fib(2)
	fib(3)
		fib(2)
		fib(1)

Largest subproblem : fib(5) = computation_smaller_subproblems(fib(4),fib(3))

str_one = "abc"
				   ^
					 
str_two = "abd"
				   ^

str_one = "ab"
str_two = "ac"

// Decrease size of one string OR both strings at the same time

solve("ab","ac")
	match(a,a)
	solve("b","ac")
		...
		
CASE_1 :: 
str_one = "abc"
str_two = "dbf"
Expected edit dist = 2 here ( a->d, c->f ) 
2 ops here

solve("abc","dbf")
	match(a, d)
	solve(bc, bf)
	 match(b, b)
	 solve(c, f)
	 	match(c, f)

CASE_2 :: 
str_one = "abc"
str_two = "abf"

1 ops here


Types of recursive cases
	harken back to our set of 3 operations on strings { **insert**, delete, substitute }
	case of a mismatch 
	
Why is this still recursive?

[ if lengths differ ** insert ** ]
[ could do if the lengths are the same ]
solve("abc","def")
	add(d) + solve("abc","ef")
	add(a) + solve("bc","def")
	
"abc","def" => "abc","adef" ( but "a","a" matches in first position ) => 
reduces to "bc',"def" 

We add or delete only from one string at a time -> NOT both!

solve("bc","dbc")
	delete(b) + solve("c","dbc")			// delte from first string
	delete(d) + solve("bc","bc")			// delete from second sgtring
	add("d") + solve("bc","bc") 			// add to first string
		Now inputs = {"dbc","dbc"} -> solving ("bc","bc")
	add("b") + solve("c,"dbc")				// add to second string
		Now inputs = {"bbc","dbc"} => solve("bc","bc")
	substitue(b=>d) + solve("c,"bc")
	substitue(d=>b) + solve("c","bc")
	
solve("a","abcd")
	there's a match => no add or delete => just go to solve("","bcd")
	

Do we have to do add or deletion
	do we this only if the string lengths differ?
	"abc","abcd" = len differ ( a=a, b=b,c=c => solve("",d")) => return 1
	delete(a) => solve(bc,abcd) => delete(b) => solve(c,abcd) => delete(c) => solve("", abcd) => 4 => backtrack = 4+1+1=1 = 7
	
	
																																						Are any overlapping here?
solve("c","bc")
	substitute(c=>b) in first string is equivalent to solve("b","bc") => 				solve("","c")							1
	substitute(b=>c) in second string is equivalent to solve("c","cc") => 			solve("", "c")						1
	*add(b) to first string is equivalent to solve("bc","bc") => 								solve("c","c")						0
	add(c) to second string is equivalent to solve("c","cbc") => 								solve("","bc")						2
	delete(c) from first string is equivalent to solve("","bc") => 							solve("","bc")						2
	*delete(b) from second string is equivalent to solve("c","c") => 						solve("c","c")						0

Recursion is exponential and in a sense, tries out all combinations and select for the best
* Adding b to first_string  OR deleting b from second string are both your best operations

You are a machine : testing out all paths
Read the entirety of inputs : machines read inputs pointer by pointer
	["a","b","c"]
		^ |	-> zone of unknown
	["d","a","b"]
		^ |	-> zone of unknown
		
		Can get a good match later on
		
solve("c","a")
	1 + solve("","a") => 2
	["d","a","b","c"]
				^ |
	["d","a","b"]
				^ |

backtrack : hit terminating condition -> propogate value back up
check min value after backtracking all ways

Draw the tree with each case for inputs of size=2 here ( str_1, str_2 ) : len(str_1) = 1, len(str_2) = 2
	Try strings such as "cd","ca" or "ab","de"



Recursion : oftentimes we run into OVERLAPPING subproblems ( for two different inputs, we get the same subproblem invocation ) 
fib(5) = fib(4) + fib(3)
fib(4) = fib(3) + fib(2)
				Therefore, we can say fib(3) is an overlapping subproblem
				It is part of the solution to fib(4) and fib(5)

Divide-and-conquer algos -> solution(lower_array) and solution(higher_array) are seperate problems themselves


solve("", string_2 where len > 1 ) = len(string_2)
solve(string_1 where len > 1, "" ) = len(string_1)
		There is no adding or substituting an empty character
		Only deletion
	
	
	

solve("abc","abf")
	match(a, a)
	solve(bc, bf)
	 match(b, b)
	 solve(c,f)
	 	match(c,f)
		
		
CASE_1 versus CASE_2 



solve("abc","dbf")
	match(a, d)
	solve(bc, bf)
	 match(b, b)
	 solve(c,f)
	 	match(c,f)
	
// str1 = "abd" => expected value = 3 here
// 	str2 = "yybd"

	a
	b
	c
	
Can peek at a element : no need to pop all the time

Zeno's paradox : 1+1/2+1/4+1/8+...=1 ( the limit point )

solve("abc","abd")
	solve("a", "abd")
		solve("b", "abd")
			solve("c", "abd")

*/

public int rcall(string1) {
	
	return op
}

** Dynamic Programming For Coding Interviews A Bottom-Up Approach to Problem Solving by Meenakshi & Kamal Rawat ( Amazon.com ) ( ~$15.00 USD ) **

