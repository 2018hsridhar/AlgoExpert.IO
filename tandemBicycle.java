import java.util.*;
import java.util.Collections;

class Program {

  public int tandemBicycle(int[] redShirtSpeeds, int[] blueShirtSpeeds, boolean fastest) 
	{
		if(redShirtSpeeds == null || blueShirtSpeeds == null)
			return 0;
		if(redShirtSpeeds.length == 0 || blueShirtSpeeds.length == 0)
			return 0;
		
		int n = redShirtSpeeds.length;
		int totalSpeed = 0; // can be max or min
		// maximum across elements
		Arrays.sort(redShirtSpeeds);
		Arrays.sort(blueShirtSpeeds);
		if(fastest == true)
			for(int i = 0; i < n; ++i)
				totalSpeed += Math.max(redShirtSpeeds[i], blueShirtSpeeds[(n-1)-i]); 
		// min speed
		else
			for(int i = 0; i < n; ++i)
				totalSpeed += Math.max(redShirtSpeeds[i], blueShirtSpeeds[i]); 
		
    return totalSpeed;
  }
	
}
