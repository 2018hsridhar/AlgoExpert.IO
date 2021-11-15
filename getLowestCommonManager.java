Prompt : we have a hierarchy of an organization ( managers point to its direct report )
Can have more than one direct report

Given two reportees : figure out their lowest common manager
lowest common ancestor in a n-ary tree

Input clarification :
- Can get null org chart nodes here
- Posssilble for a report to NOT be in our tree
- return null if LCA not discoverable
- reportOne and reportTwo are never duplicates, but can be same report

Strategies : Recursion, DFS/BFS, backtracking ( return a value upwards ), traversal order
By default, if the direct reports are nodes in the org chart -> node A is a guaranteed ancestor
DFS tree to determine existence

Brute force approach : 

// From the organizational chart below.
topManager = Node A
reportOne = Node E
reportTwo = Node I
          A								- level 1
       /     \
      B       C						- level 2
    /   \   /   \
   D     E F     G
 /   \
H     I

[H,I]

          A								- level 1
       /     \
      B       C						- level 2
    /   \   /   \
   D     E F     G
 /   \
I     H

[A,I] here
[I,E]
 call(A)
	call(B)
		call(D)
			call(I)
			call(H)
		call(E)
	<---B [ don't return I or H ]
	call(C)
	<---null

4 cases on the call back
	- found neither report
	- found one of the reports
	- found both reports

(nodeFound, flag ) : flag := it was LCA or not ( both reports were found ) 

Set here : ask if I equalled reportOne or reportTwo on a call?
	pass in the node itself ( not flags ) : { null, reportOne, reportTwo }
Pass in : 
	level
	parent
	
Callback : return a boolean or a Wrapper(boolean,level)
					return the reports found?
					
lca = B

Can we avoid doing multiple DFS calls
DFS(A) calls DFS(B) and DFS(C)
DFS(B) calls DFS(D) and DFS(E)
	-> overlapping problems / calls
	-> bottom up more efficient

Brute force complexity
Let N := number nodes
Let H := height ( balanced - log_k(N), skewed - N )
Let K := the max number of children for a given node
Time = O(N^2)
Space = __ ( implicit ) __ ( explicit ) 

Better : Time = O(N)
Space = O(H) ( implicit ) O(1) ( explicit ) 


PSEUDOCODE : 



TEST CASES : 
(A) [A], A, A => A
(B) [A,B,E], E,E => E
(C)
(D)
(E)
(F)



import java.util.*;

class Program {
	// Wrapper to recursive call
	  public static OrgChart getLowestCommonManager(
      OrgChart topManager, OrgChart reportOne, OrgChart reportTwo)
		{
			Wrapper lcaInfo = findLCA(topManager, reportOne,reportTwo);
			return lcaInfo.node;
  }
	
	static class Wrapper
	{
		public OrgChart node;
		public boolean flag;
		
		public Wrapper(){}
		public Wrapper(OrgChart node, boolean flag)
		{
			this.node = node;
			this.flag = flag;
		}
	}
	
	private static Wrapper findLCA(OrgChart root, OrgChart reportOne, OrgChart reportTwo)
	{
		// System.out.printf("Evaluating findLca on root node = %s\n", root.name);
		Wrapper res = new Wrapper(null, false); // default initialization
		if(root == null)
		{
			return new Wrapper(null,false);
		}
		// work of parent
		if(root == reportOne && root == reportTwo)
		{
			return new Wrapper(root,true);
		}
		
		// local variables : go thru all children
		// Need some more accounting here
		// leftmost : reportOne, rightmost : reportTwo
		List<OrgChart> children = root.directReports;
		int n = children.size();
		boolean foundReportOne = false;
		boolean foundReportTwo = false;
		OrgChart reportOneChild = null;
		OrgChart reportTwoChild = null;
		
		// Test parent cases first
		if(root == reportOne) // not checking if a descendant = reportTwo
		{
				foundReportOne = true;
				reportOneChild = root;
		}
		
		if(root == reportTwo)
		{
				foundReportTwo = true;
				reportTwoChild = root;
		}
		
		// Then children cases
		for(int i = 0; i < n; ++i)
		{
			OrgChart child = children.get(i);
			Wrapper childInfo = findLCA(child, reportOne, reportTwo);
			if(childInfo.node != null)
				// System.out.printf("For child = %s\t childInfo node = %s \t flag = %s\n", child.name, childInfo.node.name, childInfo.flag);
			if(childInfo.flag == true)	// whatever is true goes back up anyways
			{
				return childInfo;
			}
			else if ( childInfo.flag == false && childInfo.node == reportOne)
			{
				foundReportOne = true;
				reportOneChild = childInfo.node;
			}
			else if ( childInfo.flag == false && childInfo.node == reportTwo)
			{
				foundReportTwo = true;
				reportTwoChild = childInfo.node;
			}
		}
		// System.out.printf("For node = %s \t Found child one = %s \t found child two = %s\n", root.name, foundReportOne, foundReportTwo);
		if(foundReportOne == true && foundReportTwo == true)
		{
			return new Wrapper(root, true);
		}
		else if (foundReportOne == true)
		{
			return new Wrapper(reportOneChild, false);
		}
		else if (foundReportTwo == true)
		{
			return new Wrapper(reportTwoChild, false);
		}	
		
		return res;
	}


  static class OrgChart {
    public char name;
    public List<OrgChart> directReports;

    OrgChart(char name) {
      this.name = name;
      this.directReports = new ArrayList<OrgChart>();
    }

    // This method is for testing only.
    public void addDirectReports(OrgChart[] directReports) {
      for (OrgChart directReport : directReports) {
        this.directReports.add(directReport);
      }
    }
  }
}






