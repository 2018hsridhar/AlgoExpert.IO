/*

Have access to two calendars -> your calendar and your coworker's calendar
Both contain meetings for the day in form of [ startTime, endTime ] 
Also both daily bounds -> ( earliest, latest times ) for THEIR entire days
Meetings are given for a SPECIFIC day 
Return a list of all intersections possible from earliest time blocks to latest time blocks

STRATEGIES 
2 pointers, sorted meeting times, interval intersection

COMPLEXITY
Let M := #-meetings calendar1
Let N := #-meetings calendar2
Time = O(_)
Space = O(_) 

TEST CASES
(A)
(B)
(C)
(D)
(E)

We are working on MILITARY time here ( hh::ss format ) 
Timings in calendar sorted by start time in ascending time ( akin to Google Calendar ) 

Meetings times stored here as 4-character length strings, all type digit too
Note that we are also provided a "meeting duration" length : imagine this as a block seperator too

Why not just (max(left_bound(c1,c2)), min(right_bound(c1,c2)))?
Oh - we are trying to fill in the meeting slots ( as if Outlook app here ) 
Look for where empty intervals

There will be a division operation involved here later on
Akin to zip 2 linked list ( in the merge sort code ) 

*/
import java.util.*;

class Program {
  public static List<StringMeeting> calendarMatching(
      List<StringMeeting> calendar1,
      StringMeeting dailyBounds1,
      List<StringMeeting> calendar2,
      StringMeeting dailyBounds2,
      int meetingDuration) 
	{
  	ArrayList<StringMeetings> meetings = new ArrayList<StringMeetings>();
		int ptr1 = 0;
		int ptr2 = 0;
		int m = calendar1.length();
		int n = calendar2.length();
		
		
		// Write your code here.
    return meetings;
  }
	
	// Basically a comparator function
	private int compareTimes(String m1, String m2)
	{
		int[] times1 = getTime(m1);
		int[] times2 = getTime(m2); 
		if(times1[0] < times2[0])
		{
			return -1;
		}
		else if ( times1[0] > times2[0])
		{
			return 1;
		}
		else
		{
			if(times1[1] < times2[1])
			{
				return -1;
			}
			else if ( times[1] > times2[1])
			{
				return 1;
			}
			return 0;
		}
		return 0;
	}
	
	// Potential string parsing code involved here
	// Returns an array corresponding to meeting times
	private int[] getTime(String serial)
	{
		String delim = ":";
		String[] comps = serial.split(delim);
		int hour = Integer.parseInt(comps[0]);
		int min = Integer.parseInt(comps[0]);
		int[] times = new int[]{hour, min};
		return times;
	}
	
	// Potential StringMeeting comparison as well
  static class StringMeeting {
    public String start;
    public String end;

    public StringMeeting(String start, String end) {
      this.start = start;
      this.end = end;
    }
  }
}
