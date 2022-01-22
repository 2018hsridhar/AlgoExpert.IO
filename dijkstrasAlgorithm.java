Famous single-source, all targets, shortest-path algorithms for a positively weighted directed graph
- Cycle handling ( may OR may not handle cycles ) 
- Fails @ negative edge weights
- Note the case where no path is found as well ( output = -1 ) 
- no self-loops

PSEUDOCODE : 

( minHeap ) Greedy, Recursive, BFS/DFS

def Node
{
	int nodeID;
	int dist;
}

TIME = O((V+E)*logV)
SPACE = O(V)			<- heap space

O(V) := visited space

Graph exploration algos : 
	// O(V+E)
	visit each vertex								// O(V)
		visit the neighborhood of each vertex		// neighborhood

def dijkstrasAlgorithm(start, edges):
	v = len(edges)
	minDists = [+inf for i in v]
	minDists[start] = 0
	# [0 inf ... inf ]
	minHeap = ()
	visited = ()
	
	while(minHeap is not empty):							// O(V)
		curNode <- minHeap.pop()
		if curNode is in visited :
			continue
		else :
			visited.add(curNode)
			for each node v in neighborhood(curNode):			// O(E)
				dist_v = minDists[curNode] + edges[curNode][v][1]
				if dist_v < minDists[v]
					minDists[v] = dist_v
				# Add back to the queue
				minHeap.push(v)									// O(lg(minHeap)) = O(lg(V))

	return dists

	Neihgborhood(0) = {1,2,3}	# No particular order
	nodes = [0,1,2,3]
	minDists = [0,8,9,7]
	
	node : current minimum distance from source
	minHeap : top->bottom [(3:7), (1:8), (2:9)]
	
[
  [
    [1, 8], [2,9],[3,7]
  ],
  [
	  [2,2]
  ],
  [
  ],
  [
    [2, 1]
  ]
]

		  0
	   / |   \ 7
    8 +  |9   +
     1 -+ 2 +- 3
	   2	1
	 
	-+ : directed edge
	   
Node with shortest distance from source -> always the node under exploration ( minHeap root ) 

TEST CASES
(A)
(B)
(C)
(D)
(E)
	   
Sample Input
start = 0				# 0-indexed vertex
edges = [
  [[1, 7]],
  [[2, 6], [3, 20], [4, 3]],
  [[3, 14]],
  [[4, 2]],
  [],
  [],
]

Vertex starts in range 0,1,2,3,4,5

Sample Output
[0, 7, 13, 27, 10, -1]

|V| = len(edges)

You're given an integer start and a list edges of pairs of integers.

The list is what's called an adjacency list, and it represents a graph. 
The number of vertices in the graph is equal to the length of edges, where 
each index i in edges contains vertex i's outbound edges, in no particular order.
Each individual edge is represented by an pair of two numbers, [destination, distance], 
where the destination is a positive integer denoting the destination vertex and the distance 
is a positive integer representing the length of the edge (the distance from vertex i to vertex
destination). Note that these edges are directed, meaning that you can only travel from a 
particular vertex to its destination—not the other way around (unless the destination vertex 
itself has an outbound edge to the original vertex).

Write a function that computes the lengths of the shortest paths between start and all of 
the other vertices in the graph using Dijkstra's algorithm and returns them in an array. 
Each index i in the output array should represent the length of the shortest path between
start and vertex i. If no path is found from start to vertex i, then output[i] should be -1.

Note that the graph represented by edges won't contain any self-loops (vertices that have 
an outbound edge to themselves) and will only have positively weighted edges (i.e., no negative
distances).

If you're unfamiliar with Dijkstra's algorithm, we recommend watching the Conceptual Overview 
section of this question's video explanation before starting to code.

import java.util.*;

class Program 
{

  class Node
  {
	  public int id;
	  public int distFromS;
	  
	  public Node(){}
	  public Node(int id, int distFromS)
	  {
		  this.id = id;
		  this.distFromS = distFromS;
	  }
  }
	
	
  public int[] dijkstrasAlgorithm(int start, int[][][] edges) 
  {
	if(edges == null || edges.length == 0)
    	return new int[] {};
	int V = edges.length;
	int[] minDists = new int[V];
	for(int i = 0; i < V; ++i)
		minDists[i] = Integer.MAX_VALUE;
	minDists[start] = 0;
	  // define a lambda comparator function
	PriorityQueue<Node> minHeap = new PriorityQueue<Node>( new Comparator<Node>()
	  {
			public int compare(Node one, Node two)
			{
				if(one.distFromS < two.distFromS)
				{
					return -1;
				}
				else if ( one.distFromS > two.distFromS)
				{
					return 1;	
				}
				return 0;
			}
	  });
	Node starter = new Node(start,0);
	minHeap.add(starter);
	Set<Integer> visited = new HashSet<Integer>();

	while(minHeap.size() != 0)
	{
		Node curNode = minHeap.poll();
		if(!visited.contains(curNode.id))
		{
			int u = curNode.id;
			int[][] hood = edges[u];
			for(int i = 0; i < hood.length; ++i)
			{
				int v = hood[i][0];
				int weight_uv = hood[i][1];
				int dist_v = minDists[u] + weight_uv;	// edge relaxation
				if(dist_v < minDists[v])
				{
					minDists[v] = dist_v;
				}
				// # Add back to the queue
				minHeap.add(new Node(v, minDists[v]));		
			}
			// Once done exploring the neighborhood, add to the visited set
			// Denote we have done the best from the given node
			visited.add(u);
		}
	}
	  // Iterate over minDists : any entry equal to infinite -> set to -1
    for(int i = 0; i < minDists.length; ++i)
	{
		if(minDists[i] == Integer.MAX_VALUE) 
		{
			minDists[i] = -1;
		}
	}
	return minDists;	
  }
}

