import java.util.*;

/*
They need to decompose this problem well to its 2Sum equivalent
O(N) time, O(1) space after sort op => O(N^2) time, O(N) space instead

*/

// Must avoid repeated additions of triplet values as well
// Sort array ahead of time just incase as well!
// Must be distinct numbers as well ( no pairs here ! ) 
class Program {
  public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
		Arrays.sort(array);
		List<Integer[]> results = new LinkedList<Integer[]>();
		int n = array.length;
		for(int i = 0; i < n; ++i)
		{
			int subSum = targetSum - array[i];
			int low = i + 1;
			int high = n - 1;
			while(low < high)
			{
				int curSum = array[low] + array[high];
				if(curSum == subSum)
				{
					Integer[] triplet = new Integer[]{array[i], array[low], array[high]};
					results.add(triplet);					
					++low;
					--high;
				}
				else if ( curSum < subSum)
				{
					++low;
				}
				else
				{
					--high;
				}
			}
		}
    return results;
  }
}
