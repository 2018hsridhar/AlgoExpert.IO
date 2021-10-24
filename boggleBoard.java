/*
 2D array ( matrix ) where w,h can be unequal, containing letters
 Given a list of words
 Return an List of all words contained in the board
 Words need NOT be in ANY order
 Connect adjacent ( hoz,vert,diag) letters - do not use a letter more than once though at a given position
 Words can have duplicate letters, BUT, they must originate from elsewhere in said board
 >= 2 words can overlap and use the same letters

(4x4)
TEST BENCH 
(A) [[]]
(B) [["t","h","i","s"], ["o","a","o","a"],["t","m","t","h"],["e","b","a","d"]]
	["this", "that", "tame", "iota", "some", "tote"]
	["iota", "some", "tame", "that", "this", "tote"]
(C) [["t","h","i","s"], ["o","a","o","a"],["t","m","t","h"],["e","b","a","d"]]
	["thid", "thpt", "sand"]
	[]
(D) [["t","h","i","s"], ["o","a","o","a"],["t","m","t","h"],["e","b","a","d"]]
	 ["song","slme"]
	 []
 (E)
 [
  ["t", "h", "i", "s", "i", "s", "a"],
  ["s", "i", "m", "p", "l", "e", "x"],
  ["b", "x", "x", "x", "x", "e", "b"],
  ["x", "o", "g", "g", "l", "x", "o"],
  ["x", "x", "x", "D", "T", "r", "a"],
  ["R", "E", "P", "E", "A", "d", "x"],
  ["x", "x", "x", "x", "x", "x", "x"],
  ["N", "O", "T", "R", "E", "-", "P"],
  ["x", "x", "D", "E", "T", "A", "E"]
]
["this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED"]

Strategies : recursion, DFS, flood-fill
Start of each word / first character = thy exploration root
Can test each word independently ( you may have to actually )
Can we sort the input to test alphabets as well ( a hash, in a sense )?

Recall that guy's global visited technique as well too on the callback

COMPLEXITY
Let M,N := dimensions of board
Let H := length longest string in words
Let W := number of words in the input
Assuming that the input is NOT sorted
TIME = O(W*(2*MN)) = O(WMN)
	start = O(MN), expl = O(MN)
SPACE = O(H)

*/
import java.util.*;

class Program 
{
  public static List<String> boggleBoard(char[][] board, String[] words) 
	{
    return new ArrayList<String>();
  }
}
