
/*
Given two groups - red/blue - with their heights
One group must be in front of the other
Must follow a rule
Everyone in front must have less height than those in the back
Check whether we can form them in that assignment
	=> class photo : tallest in the back, shortest in the front
	
Array lengths are the same here?
Can receive empty or very long array lists ( let's assume it fits in ) . Handle NULLPTR case
Can have duplicates
Heights information - all positive integers ( can we have 0 here ) .

Complexity : 
Let N := length of arrays
Time = 2*O(NlogN) +O(N) = O(NlogN)
Space = O(1)

Leaning towards a greedy strategy : sort both reds and blues
Red => [5,9,7,11]
Blue => [10,3,4,8]

Sort(Red)
Sort(Blue)

inFront(Red,Blue)
Red=>		[5,7,9,11]
Red=> 	[1,2,3,4]
Blue=>	[3,4,8,10]
				   ^
					 
How to discern which group to place first?
Red  =>	 	[1,5,9,11]
Blue =>		[3,4,8,10]

n = 4
redInFirst = true ( red in front ) 
i		redVal	blueVal	
0		1				3
1		5				4
2
3

Earlier stopping

inFront(Blue,Red)
Blue => [3, 4, 5, 8, 10]
Red =>  [5, 7, 8, 9, 11] => fails because @ idx = 0, red(0) > blue(0) AS 5 > 3


Collections.sort(arraylists) => O(1) space [ not accounting for space used by .sort() ]

Insert(Red - {11}, {3,4,5,8,10})
Insert(Blue - {14}, {5,7,8,9,11})
	2 binary searches AND then the class photo check - O(Logn)
		Try to amortize this here? Insert(start) = O(N)? 
		O(N) + O(logN) insertion time for both element inserts here
	We expect in next iteration of insertion random (red,blue) pair that existing input is sorted
	

They may not fit in either
Red - {8}
	> binary search location to insert red
Blue-	{8}
Insertion sorting ( pairwise swapping check if invariant holds ) 
Maintain sorted invariant for insertion too
Greedy insertion strategy

	
Inputs : 
[red,red,red,red]
[blue,blue,blue,blue]
	
Output : 
[red,blue,red,blue]
[blue,red,blue,red] => colors must match within group
Can alternate within the group OR groups in their totality



(A)
[5, 8, 1, 3, 4]
[6, 9, 2, 4, 5]

Expected => return TRUE

(B)
front = [1,1,1]
back =  [1,1,1]

Expected => return FALSE

(C)

front = [9]
back = [7]
return FALSE
You decide who goes in front vs in back


(D)

front = [7]
back = [9]
return TRUE

*/

import java.util.*;

class Program {

  public boolean classPhotos(
      ArrayList<Integer> redShirtHeights, ArrayList<Integer> blueShirtHeights) 
	{
		boolean canTakePhoto = true;
		if(redShirtHeights == null || blueShirtHeights == null)
			return true;
		if(redShirtHeights.size() == 0 || blueShirtHeights.size() == 0)
			return true;
		
		Collections.sort(redShirtHeights);
		Collections.sort(blueShirtHeights);

		// Single greedy comparison for determining which group goes first
		int firstRedVal = redShirtHeights.get(0);
		int firstBlueVal = blueShirtHeights.get(0);
		boolean redInFirst = false;
		if(firstRedVal < firstBlueVal)
			redInFirst = true;
		else
			redInFirst = false;
		
		int n = redShirtHeights.size();
		for(int i = 0; i < n; ++i)
		{
			int myRedVal = redShirtHeights.get(i);
			int myBlueVal = blueShirtHeights.get(i);
			if(redInFirst == true)
			{
				if(myRedVal >= myBlueVal)
				{
					canTakePhoto = false;
					break;					
				}
			}
			else
			{
				if(myBlueVal >= myRedVal)
				{
					canTakePhoto = false;
					break;					
				}
			}
		}
		
		
		return canTakePhoto;
  }
}
