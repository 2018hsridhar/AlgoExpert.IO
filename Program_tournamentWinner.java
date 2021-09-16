
/*
Notes on the prompt
> Tournament algorithms : teams compete with each other to solve problems as fast as possible
> Round robin : each team faces off against all others
	- 2 compete at a time
	- one team designated HOME_TEAM
	- other team designated AWAY_TEAM
	- always one winner, one loser => no ties
	- [win,loose] = [3,0] points
	- winner = whom receives most points
> Given an array of pairs representing teams competing against each other
	[ home_team, away_team] = pairs representation
> Write a func that returns return of tournament
> competition element : in form of [home_team,away_team]
	String at most 30 character for the team name
> result[i] = whom the winner in each corresponding competition[i] 
	1 = HOME_TEAM won
	0 = AWAY_TEAM won
> Guaranteed that only one team wins the full tournament
> Each team competes against all others once only
> Tournament has at least two teams [ n = 2 : at least two team ] 
> BOUND(number_teams) or BOUND(numer_competitions)?
	=> unspecified in input
 
 n = 2 : at least one competition at minimum
 n = 3 : 3 competitions total
 {1,2},{1,3},{2,3} = {2,1},{3,1}{3,2} : symmetrical pairs / combination
competitions = [
  ["HTML", "C#"],
  ["C#", "Python"],
  ["Python", "HTML"],
]
results = [0, 0, 1]
idx : 
0 => C#
1 => Python
2 => Python

output = "Python"
Scores total { 0,3,6} - { HTML, C#, Python }

Using a hashmap data structure : keep track of the score for each team
	> If the key DNE is hashmap : hm.put(key,score) { score="3" if a win, else "0"}
	> If the key does is hashmap : hm.put(key,hm.get(key) + score) { score="3" if a win, else "0"}	
	> Sovle for key based on the winner of that tournament too ( during score updates ) 
In the end, we return a max score and the corresponding team ( String ) 



k teams => (k(k-1)/2 = n) #-of competitions
[(k^2-k)/2]

Complexity :
Runtime is linear time complexity
k := number of players in the competition
Time = O(m) + O(k) = O(m+k)
Since k < n : we can say time = O(n+c*n) = O((1+c)n) = O(n)
0 < c <= n ( to represent k ) 

We know 
2 teams => 1 comp only
3 teams => 3 comps
4 => 6 comps
5 => 10 comps
Space = O(k)

{} = arraylist ( is a type of List datastructure in Java => akin to C++ vectors ) : dynamically changes in size ( can add or remove from end or beginning easily ) 
	(Array)List<typename> x = new ArrayList<typename>();
	(Linked)List<typename> x = new LinkedList<typename>();
	akin to std::vector<typename> x 
	List is an abstract class : ArrayList/LinkedList = subclasses
	
[] = array syntax

Test Cases
(a) n = 2 : competitions = {"HTML","C#"} results = {0} => "HTML"< results=[1] => "C"
(b) n = 3 : competitions = [["HTML", "C#"], ["C#", "Python"], ["Python", "HTML"],], results = [0, 0, 1] 
	 { 0,3,6} - { HTML, C#, Python } => "Python"
*/

import java.util.*;

class Program_tournamentWinner {

  public String tournamentWinner(ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) 
	{
		HashMap<String,Integer> teamScores = new HashMap<String,Integer>();
		int m = results.size();
		int max_score = 0; 
		int max_team_idx = 0;
		boolean isHome = true;
		for(int i = 0; i < m; ++i)
		{
			ArrayList<String> curComp = competitions.get(i);
			int curRes = results.get(i);
			String home = curComp.get(0);
			String away = curComp.get(1);
			
			// Check if hashmap contains keys or not here
			if(!teamScores.containsKey(home))
				teamScores.put(home,0);
			if(!teamScores.containsKey(away))
				teamScores.put(away,0);
			
			// Keys do exist : have a score for current game
			if(teamScores.containsKey(home) && curRes == 1)
			{
				teamScores.put(home, teamScores.get(home) + 3);
				if(teamScores.get(home) > max_score)
				{
					max_score = teamScores.get(home);
					max_team_idx = i;
					isHome = true;
				}
			}
			if(teamScores.containsKey(away) && curRes == 0)
			{
				teamScores.put(away, teamScores.get(away) + 3);
				if(teamScores.get(away) > max_score)
				{
					max_score = teamScores.get(away);
					max_team_idx = i;
					isHome = false;
				}
			}
		}
		
		if(isHome)
			return competitions.get(max_team_idx).get(0);
		else
			return competitions.get(max_team_idx).get(1);			
  }
}
