/*

Given an array of buildings and a directino that they all face ( based on their windows )
Return the array of indices of buildings which can obesrve the sunset

Building must be STRICTLY TALLER than all other buildings that come after it in the dir it faces
Input is natural numbers := heights of buildings
Dirs = "EAST/WEST" : [right,left]
Indices should be sorted in ASCENDING order! Collections.sort helps here

Complexity 
Let N := len(buildings)
Time = O(N)
Space = 

Edge Case Testing ( how well do we incorporate peaks/valleys here ) : 
(A) [1,2,...] OR [1,2,3,4,5]
(B) [5,4,...] OR [5,4,3,2,1]
(C) [5,5,...] OR [5,5,5,5]
(D) [1,2,1,3,1,4,1,5]
(E) [5,1,4,1,3,1,2,1]
(f) [5]

It's as if it's a y-axis/x-axis facing problem ( geometry := seen on Leetcode ) 
And we can make a guaranteed
westbound at min contains first element
eastbound at min contains last element
Luckily, we need not code to an interface :-)

*/
import java.util.*;

class Program 
{
  public ArrayList<Integer> sunsetViews(int[] buildings, String direction) 
	{
		if(buildings == null)
			return null;
		else if ( buildings.length == 0)
			return new ArrayList<Integer>();
		
		int n = buildings.length;
    ArrayList<Integer> indices = new ArrayList<Integer>();
		if(direction.equals("EAST"))
		{
			int maxH = buildings[n-1];
			int i = n - 2;
			indices.add(n-1);
			while(i >= 0)
			{
				int curH = buildings[i];
				if(curH > maxH)
				{
					indices.add(i);
					maxH = curH;
				}
				--i;
			}
			Collections.reverse(indices);
		}
		else
		{
			int i = 1;
			int maxH = buildings[0];
			indices.add(0);
			while(i < n)
			{
				int curH = buildings[i];
				if(curH > maxH)
				{
					indices.add(i);
					maxH = curH;
				}
				++i;
			}
		}
		return indices;
  }
}
