/*
 2D array ( matrix ) where w,h can be unequal, containing letters
 Given a list of words
 Return an List of all words contained in the board
 Words need NOT be in ANY order
 Connect adjacent ( hoz,vert,diag) letters - do not use a letter more than once though at a given position
 Words can have duplicate letters, BUT, they must originate from elsewhere in said board
 >= 2 words can overlap and use the same letters
 Attempt @ confusion : general thinking is the words are [a-z]. they can include numbers and other symbols
 	-> can not always mark entries in the board with a symbol for own visition ( e.g. X or -1 ) 
	-> hence, you need a seperate board object ( boolean for ease ) 

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
 (E) [["a", "b", "c", "c"]]
 	["a","ab","bcd","abcd"]
(F)
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
SPACE = O(H) + O(MN) + O(H) = O(MN) + O(H)

Modularization makes reasoning of Big-O easier as well
* Clement's solution utilizes a trie underneath, and therefore, their TS reasoning will differ substantially


*/
import java.util.*;

class Program 
{
	// Try to abstract away data structure conversions : may prove confusing
  public static List<String> boggleBoard(char[][] board, String[] words) 
	{
// 		List<int[]> neighbors = new LinkedList<int[]>();
// 		neighbors.add(new int[]{1,1});
// 		neighbors.add(new int[]{2,2});
// 		neighbors.add(new int[]{3,3});
// 		neighbors.add(new int[]{4,4});
// 		for(int[] nx : neighbors)
// 			System.out.printf("[%d,%d]\n", nx[0], nx[1]);
		
		
		
		Set<String> foundWords = playBoggle(board,words);
    List<String> found = new ArrayList<String>();
		found.addAll(foundWords); // useful method : list.addAll(set)
		return found;
	}
	
	// Seperation : Discovery of start position from the actual dfs call
	private static Set<String> playBoggle(char[][] board, String[] words)
	{
		int m = board.length;
		int n = board[0].length;
		int[][] visited = new int[m][n]; // because IDK if bools initialize to false or true, but ints always 0-init
		Set<String> foundWords = new HashSet<String>();
		List<String> found = new ArrayList<String>();
		for(String token : words)
		{
			for(int i = 0; i < m; ++i)
			{
				for(int j = 0; j < n; ++j)
				{
					if(board[i][j] == token.charAt(0))
						if(dfs(board,visited,token,0,i,j) == true)	// the other mistake : starting every ( say, is, at ). Should be a set honetly
							if(!foundWords.contains(token))
								foundWords.add(token);
				}
			}
		}
		return foundWords;
  }
	
	// Return of the data type may trip up folks here
	private static boolean dfs(char[][] board, int[][] visited, String word, int idx, int i, int j)
	{
		int m = board.length;
		int n = board[0].length;
		if(i < 0 || i >= m || j < 0 || j >= n)
		{
			return false;
		}
		if(visited[i][j] == 1)
		{
			return false;
		}
		// base case handling : single len string
		if(word.charAt(idx) == board[i][j])
		{
			if(word.length()-1 == idx)	// Need to now search like this
			{
				return true;
			}
			else
			{
				visited[i][j] = 1;
				// Need the diagonals now too
				boolean toTop = dfs(board, visited, word, idx+1, i+1, j);
				boolean toBottom = dfs(board, visited, word, idx+1, i-1, j);
				boolean toLeft = dfs(board, visited, word, idx+1, i, j-1);
				boolean toRight = dfs(board, visited, word, idx+1, i, j+1);
				
				boolean topRight = dfs(board, visited, word, idx+1, i-1, j+1);
				boolean bottomRight = dfs(board, visited, word, idx+1, i+1, j+1);
				boolean topLeft = dfs(board, visited, word, idx+1, i-1, j-1);
				boolean bottomLeft = dfs(board, visited, word, idx+1, i+1, j-1);
				// unset the visited here bfeore the return though
				visited[i][j] = 0;
				boolean cardinals = (toTop || toBottom || toLeft || toRight);
				boolean diagonals = (topRight || bottomRight || topLeft || bottomLeft);
				boolean res = (cardinals || diagonals);
				return res;
			}
		}
		else
		{
			return false;
		}
	}
	
	
}
