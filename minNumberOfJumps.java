import java.util.*;

class Program {
  public static int minNumberOfJumps(int[] array) 
	{
			if(array == null || array.length < 2)
            return 0;
        
        int n = array.length;
        int minNumJumps = n - 1;
        array[n-1] = 0; // last elem by default ( and min num from last el ) 
        for(int i = n - 2; i >= 0; --i)
        {
            int minJumpsFromPos = (n-i); // we ran into data overflow somehow? OH ... I see
            // int minJumpsFromPos = Integer.MAX_VALUE - 1; // wow this is kinda crummy for sure!
            int numSteps = array[i];
            for(int j = 1; j <= numSteps; ++j) // perform bounds check as well
            {
                int nextPos = i + j;
                if(nextPos < n)
                    minJumpsFromPos = Math.min(minJumpsFromPos, 1 + array[nextPos]);
            }
            array[i] = minJumpsFromPos;
        }
        minNumJumps = array[0];
        return minNumJumps;
  }
}
