Max num ways for how to make change
Given $100, and given {$1} -> 1 way to make $100 ( a 100 $1-bills )
{$1,$99} -> {100 1-s, 1 [1,99]} = 2 ways
Given n and a set of denominations in a list


n = 6
denoms = [1, 5]
Sort the denominatinos ahead  of time
2 // ( 1x1 + 1x5 ) and ( 6x1 )

Strategies : 
Combinatorial ( Recursive, DP ) , Backtracking

n = 4
denoms = {1,3}
	4 = 1+3 = 3 + 1 ( same )
	4-1 : numWays(3)
	4-3 : numWays(1)
	Can not sum up both

		4
		|-3 ( -1 )
		  X
		|-1 ( -3 )
		  |-0 ( -1) : add here

n = 10
denoms = {1,2,3,4}

10
|-9
  |-8
    |-7
	  |... |-1 ( all 1s ) depth = 10/min(d) = 10
	|-6
	|-5
	|-4
  |-7
  |-6
  |-5
|-8
|-7
|-6


Complexity
Let N := total change
Let D := #-denominations
Naive Recursion : O(D^N) : stricter O(D^(N/min(D))


DP : 
TIME = O(D^2N) + O(DlgD) Solution
SPACE = O(DN) + O(1) Solution

TIME : O(N) <- best possible
	   O(ND) or O(Nlg(D,N))

Best theoretical space : O(N)
Greedy approach

	6{5,1}
	numWays : 1->5
	
	1:n-1 has been solved for -> solve for <n>
	
	
Clarifications
- N >= 0 ( nonnegative ) 
- denoms from [1,n] is NOT true ( can be greater )
- return 0 : no change makeable

Test Cases
(A)
(B)
(C)
(D)
(E)
(F)
dp[]

n=2 (1, 2)

dp = 1
dp += (dp-1)
 - ( count 1 )
 --- ( count 1,2 ) when using <3>
 dp = [1,1,2,0,....,1]
[2,1,3]
[1,2,3,4,5....100]
for each denomination
	subProblem = curVal - denomination
	if subProblem >= 0
		DP[curVal] += 1
			3 = 1 + 2 = 1 + 1  + 1
				2,{1,1} to get to two
				add {1} now
				
n = 11
	denoms = [1,5]
			  0 1
			  
	DP(11,1) = 
		add 1 if DP(6,0) is non zero
		DP(6,1) is also a solution
		DP(11,1) = DP(6,1) + DP(6,0) + DP(10,0) + DP(10, 1)
		  			2			1		1
			DP(6,0) might be a part of DP(6,1)!
			
				(6x1)
				(6x1) + (5*1+1*5)
				(10x1)

		DP(11,1) = DP(6,1) + DP(10,0)





def numberOfWaysToMakeChange(n, denoms):
	if(n == 0):
		return 1;
	denoms.sort()
	numWays = 0
	if n == 0:
		return 0
	# D = max(denoms) #
	D = len(denoms)
	N = n
	DP = [[0 for i in range(D)] for j in range(N+1)]	# Only a range is iterable! 
	# print(DP)
	for j in range(len(denoms)):
		DP[0][j] = 1	# 1 way to make a zero

	# topmost row always 0 here
	for i in range(1,N+1,1):
		subProblem = i
		for j in range(len(denoms)):	
			subProblemNumWays = 0
			# Seems we have another nested loop here as well. Take note of this too!
			# j is an index into the sorted denominations
			# index here growing anyways : that makes most sense as well
			for k in range(0,j+1,1): # Need to add here for the denominatinos as well!
				nextProblem = subProblem - denoms[k]
				nextProblemIndex = k
				if nextProblem == 0:
					subProblemNumWays += 1
				elif nextProblem > 0:
					subProblemNumWays += DP[nextProblem][nextProblemIndex]
			DP[i][j] = subProblemNumWays
	
	# print(DP)
	numWays = DP[-1][-1]
	# numWays = DP[N][D-1]	# final entry [-1][-1]
	return numWays
