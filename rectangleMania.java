/*
THOUGHT PROCESS ::


COMPLEXITY
Side be strictly parallel to the x-axis and the y-axis
No coordinate is 100 units further from the origin ( in R^2, denote as (0,0))

>>> brute force solution <<<
Let N := #-coordinates
We have N^4 possible pairings to exec an "isRectangle()" test
Time = O(N^4)
Space = O(1)

>>> optimized HM solution <<<
Let N := #-coordinates
Time = O(N) points * O(N^2) combinations checking work = O(N^3)
Space = O(2N) = O(N) [ it's not the #-keys here : but rather, all values being mapped somewhere 
	unto their corresponding x-coords and y-coords ] 

Min # = 0 by default

TEST BENCH
(A)
(B)
(C)
(D)
(E)


*/

import java.util.*;

class Program 
{
  public static int rectangleMania(List<Integer[]> coords) 
	{
		int numberRects = 0;
		if(coords.size() <= 3)
			return numberRects;
		
		// x,y, can be confusing - horizontal/vertical delineates colinearity testing direction better
		HashMap<Integer,List<Integer>> colinearHorizontalMap = new HashMap<Integer,List<Integer>>(); 
		HashMap<Integer,List<Integer>> colinearVerticalMap = new HashMap<Integer,List<Integer>>();
		
		execLexicographicSort(coords);
		fillInCoordinateHashMaps(coords, colinearHorizontalMap,colinearVerticalMap);
		
		int n = coords.size();
		for(int k = 0; k < n; ++k)
		{
			Integer[] bottomLeftPoint = coords.get(k);
			int x = bottomLeftPoint[0];
			int y = bottomLeftPoint[1];
			List<Integer> colinearHoriz = colinearHorizontalMap.get(x);
			List<Integer> colinearVert = colinearVerticalMap.get(y);

			// System.out.printf("(x,y) = (%d,%d)\n", x, y);
			for(int i = 0; i < colinearHoriz.size(); ++i)
			{
				int myY = colinearHoriz.get(i);
				for(int j = 0; j < colinearVert.size(); ++j)
				{
					int myX = colinearVert.get(j);
					if(myX > x && myY > y)
					{
						// Now test if (myX,myY) exists
						// You need to check only one here at this point ( not both ) : say upper right = (3,5). Either vert(5) or hoz(3) contains that point
						if(colinearVerticalMap.containsKey(myY))
						{
							List<Integer> colinearVert_two = colinearVerticalMap.get(myY);
							if(colinearVert_two.contains(myX))
									++numberRects;
						}
					}
				}
			}
			// System.out.printf("\n\n\n\n\n");
			
		}
		// 	System.out.printf("[%d,%d]\n", coords.get(i)[0], coords.get(i)[1]);
		
    return numberRects;
  }
	
	public static void fillInCoordinateHashMaps(List<Integer[]> coords, HashMap<Integer,List<Integer>> xMap, HashMap<Integer,List<Integer>> yMap )
	{
		int n = coords.size();
		for(int i = 0; i < n; ++i)
		{
			Integer[] point = coords.get(i);
			int x = point[0];
			int y = point[1];
			
			// [2] Fill out the xMap
			if(!xMap.containsKey(x))
			{
				List<Integer> novel = new LinkedList<Integer>();
				novel.add(y);
				xMap.put(x,novel);
			}
			else if(xMap.containsKey(x))
			{
				List<Integer> curList = xMap.get(x);
				curList.add(y);
			}

			// [2] Fill out the yMap
			if(!yMap.containsKey(y))
			{
				List<Integer> novel = new LinkedList<Integer>();
				novel.add(x);
				yMap.put(y,novel);
			}
			else if(yMap.containsKey(y))
			{
				List<Integer> curList = yMap.get(y);
				curList.add(x);
			}
		}
	}

	public static void execLexicographicSort(List<Integer[]> coords)
		{
			Collections.sort(coords, new Comparator<Integer[]>(){
				// @Override
				public int compare(Integer[] first, Integer[] second)
				{
					if(first[0] < second[0])
						return -1;
					else if ( first[0] > second[0])
						return 1;
					else
					{
						if(first[1] < second[1])
							return -1;
						else if ( first[1] > second[1])
							return 1;
						return 0;
					}
				}
			});
		}
	
  static class Point 
	{
    public int x;
    public int y;

    public Point(int x, int y) 
		{
      this.x = x;
      this.y = y;
    }
  }
}
