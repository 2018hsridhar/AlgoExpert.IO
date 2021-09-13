import java.util.*;

class Program_Ambiguous_Measurements {

	/*
		THOUGHT PROCESS : 
		We do not exactly how much fluid is in each cup : just that it is bounded and inbetween
		the low ( L ) line and the high ( H ) line.
		Input is a list of measuring cups with low lines and high lines
		Give a [low,high] for target measurement too
		Can I measure that specific range ( inclusive ) using these cup?
		... hmm ...
		Now it starts seeming combinatorial/trying out assignments. Recursive approach?
		int[][] measuring Cups is an int[][2] with low and high ranges, and 0 <= L <= H 
		( yes, 0 = L = H, or 2 = L = H is possible ) 
		Always given at least one measuring cup ( no null/empty cup cases - handle in prod env ) 
		Can not pour contents of one cup unto another cup
		Once measures -> transfer to a larger bow that MAY contain the target measure too!
		Answer NOT guaranteed uniqueness either -> just return if possible here
		
		Is there a greedy strategy here too ( measure lowest amount to start of with! ) 
		Seems a possibility. Sometimes we do see greedy and recursion well paired!
		Reminds me of the coin / break change problem a bit BUT perhaps entailed with a slight modification too
		
		
		
	*/
  public boolean ambiguousMeasurements(int[][] measuringCups, int low, int high) 
	{
    return false;
  }
}
