'''
1884. Egg Drop With 2 Eggs and N Floors
URL = https://leetcode.com/problems/egg-drop-with-2-eggs-and-n-floors/

It is a classical example of a DP problem
N bounded in the integers inbetween of [1,1000], exclusive
\exists floor f, 0 <= f <= n, s.t. an egg dropped at floors higher than f break, any below or at will NOT break
    -> yet you will not be provided such a <F>, as you must ascertain it
    -> can reuse unbroken eggs in future moves! 
    
Ex-2 : sequence is decreasing w.r.t. magnitude of finite differences between consecutive sequence elements
    Feel like this is a major hint
    Two eggs -> one egg problem ( always two only ) 
    Must test each level with the second egg : is a band ( we can not binary search this, as 8 -> 4 -> break at (4) yields nothing for us w.r.t. 1-3 )
    
Problem state parameters 
    - height
    - number eggs
    
Natural numbers sum[1,13] = 91
Can not add a difference of 14 atop the 91 : gets us over 100
Your DP problem would have to ask if the egg breaks at a given floor or not, at least.
Seems to be bottom-up DP here, as well too ( and collect mins over all of that ) 
Base case always the first floor as well
 
==> go upwards here
==> above floors f property too : is must exist!
I   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20
DP  1   
MIN 1    
 
COMPLEXITY
Let N := height of the building
Time = O(N^2)
Space = O(N) ( exp ) O(1) ( imp )
Strategy : Iterative Bottom-up Dynamic Programming

It is possible for an egg to not break at ANY floor as well!

TEST CASES
(A) n = 1
    1
(B) n = 2
    2
(C) n = 100
    14
(D) n = 5
    
(E) n = 0
    0
    
PSEUDOCODE : 

    fn(maxHeight,numEggs):
        if(maxHeight <= 2):
            return maxHeight
        
        # Base case initializations 
        DP[maxHeight + 1][2]    # 1 off boudns error
        DP[0][1] = 0
        DP[1][1] = 1
        DP[2][1] = 2
        for i in range(0,maxHeight,1):
            DP[i][1] = i    # is building height, IFF one egg anyways
            
        # Non-base case values
        for i in range(3,maxHeight,1):
            localMinNumMoves = float("inf")
            for each height from 1 to maxHeight:
                 subHeight = i - height
                 localMinNumMoves = min(localMinNumMoves, 1 + DP[subHeight][1],1 + DP[subHeight][2])
            DP[i][2] = localMinNumMoves 
       
        globalMinNumMoves = DP[height][2]   # This is the true global min ( BUDP builds up ! ) 
        return globalMinNumMoves

*** read this inequality carefully!

https://www.youtube.com/watch?v=FkJ9MYHuuRk&t=4s

'''

import sys

class Solution(object):
    def twoEggDrop(self, n):
        """
        :type n: int
        :rtype: int
        """
        if(n <= 2):
            return n
        
        # Base case initializations 
        numRows = 2
        numCols = n+1        # 1 off boudns error
        # List comprehension syntax and loops
        # Columns -> then the rows
        # As if you create each row : nested parantheses like math
        DP  = [[0 for i in range(numCols)] for j in range(numRows)]
        # print(DP)
        DP[0][0] = 0
        DP[0][1] = 1
        globalMinNumMoves = float('inf')
        for i in range(0,n+1,1):
            DP[0][i] = i    # is building height, IFF one egg anyways
        # Fill in base case for n = 2 as well!
        DP[1][0] = 0;
        DP[1][1] = 1;
        
        # Non-base case values
        # We need the certainty case as well : how to maximize it, given DP structure?
        # Each drop tested counts as 1 valid move.
        # Not correct for larger test cases -> diagnose further!
        
        # Going down and going up are diff directions too
        # You seem super close. not sure what is going on here with some cases?
        # Check why an earlier input may be off as well
        # Is height = 2 also a special case
        for height in range(2,n+1,1): # Range inclusivity here!
            localMinNumMoves = height # Is height itself ( each floor ) 
            for i in range(1,height + 1,1):
                heightDown = i - 1
                heightUp = i+1
                remainHeightUp = height - i
                numMoves = 0
                if(i == 1):
                    numMoves = 1 + DP[1][remainHeightUp]
                elif(i == height):
                    numMoves = 1 + DP[0][heightDown]
                else:
                    numMoves = max(1 + DP[0][heightDown],1 + DP[1][remainHeightUp]) # Analyze when the case breaks or does not break too!
                localMinNumMoves = min(localMinNumMoves, numMoves)
            DP[1][i] = localMinNumMoves 
       
        globalMinNumMoves = DP[1][n]   # This is the true global min ( BUDP builds up ! ) 
        return globalMinNumMoves
        
