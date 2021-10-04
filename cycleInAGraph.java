
#_vertices = len(edges) = 6

edges = [
  [1, 3],							0
  [2, 3, 4],					1
  [0],								2
  [],									3
  [2, 5],							4
  [],									5
]

has cycle : 0->1->2->0

Zero-indexed up to len(edges)
Each element denotes neighborhood relationship ( adjacency ) 
Is a direct graph

0->1, 0->3
1->2, 1->3, 1->4

Determine existence of a cycle in a directed graph
Problem bounds
	Can have no edges OR can have self-loops ( naturally a cycle ) 
	Assume max # nodes reasonable here
	No parallel/duplicate edges
	

Complexity : 
Let V := number of vertices
Let E := number of edges
Time = O(V*(V+E))
Space = O(V)

DFS each unique vertex
Visited set per DFS path -> not the entirety of theg raph
DFS(0,{})
	DFS(3,{0})
	DFS(1,{0})
		DFS(3,{0,1})
		DFS(4,{0,1})
			DFS(5,{0,1,4})
		DFS(2,{0,1})
			DFS(0,{0,1,2})		=> 0 is in the visited set : hence, we found a cycle

Utilize sets as "visited path" -> entail a deep-copy per each neighbor of a node
Independent path information but not copying
Backtracking

Global which we add/remove from the last (LL ) 
0
0->3
0
0->1
0->1->3
0->1
0->1->4
0->1->4->5
0->1->4
0->1->4->2
0->1->4->2->0 = a cycle

visiting = {0, }
done_visited = {2, 3, 5, 4, 1}

3 is NOT in the starting path from node ID = 0
2 independent sets
	one -> path under exploration
	second -> nodes we performed DFS from
	
	
edges = [
  [1],
	[2],
	[3]
	[]
]

edges = [
  [1],
  [2]
  [],
  [4],
  [5],
  [3],
]

Iterate over all node IDs and check if we are done visiting them or not
 for i = 0,1,...5
		
visiting = {3,4,5} - run into cycle - return boolean
done_visited = {2, 1, 0, 5, 4, 3}

edges = [
  [],
  [2]
  [],
  [4],
  [],
  [],
]

6 nodes - dfs 4 seperate nodes
dfs(0), dfs(1), dfs(3),dfs(5) -> approximate ( or worst case, exact to V ) 
complexity of dfs in itself - O(V+E)
V*(V+E) => O(V^2 + VE)

V := total number of vertices ( across all disconnected components ) 
O(V+E) in the end


import java.util.*;

class Program 
{
	Set<Integer> done_visited;
	Set<Integer> visiting;
	
	// Both are sets : order agnostic for add/remove operations - O(1)
  public boolean cycleInGraph(int[][] edges) 
	{
  	done_visited = new HashSet<Integer>();
		visiting = new HashSet<Integer>();
		int V = edges.length;
		for(int i = 0; i < V; ++i)
		{
			if(!done_visited.contains(i))
			{
				boolean hasCyc = dfs(edges, i);
				// System.out.printf("For node = %d, hasCyc = %s\n", i, hasCyc);
				if(hasCyc == true)
					return true;
			}
		}
    return false;
  }
	
	// true => has cycle | false => no cycle
	private boolean dfs(int[][] edges, int parent)
	{
		int[] adj = edges[parent];
		// Denote parent as visited before exploring children
		visiting.add(parent);
		// Explore children
		if(adj.length == 0)
		{
			done_visited.add(parent);
			if(visiting.contains(parent))
				visiting.remove(parent);
		}
		else
		{
			for(int i = 0; i < adj.length; ++i)
			{
				int child = adj[i];
				if(!visiting.contains(child))
				{
					boolean hasCycInternal = dfs(edges, child);					
					if(hasCycInternal == true)
						return true;
				}
				else
					return true;
			}
			if(!done_visited.contains(parent))
				done_visited.add(parent);
			if(visiting.contains(parent))
					visiting.remove(parent);
		}
		return false;
	}
	
	
}
