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
See how direction of the increment impacts this as well!
	-> could try to join up the intervals AHEAD of schedule ( precomputation) ?
	
List API : 
https://docs.oracle.com/javase/8/docs/api/java/util/List.html#equals-java.lang.Object
Can remove or set but then messy element shifting ensues
But meetings are contiguous blocks ... so just have seperate code to test for the overlapping case as well 
 and now track 2 local variables per meeting ( start, end ) correspondingly
	Let us pretend that the bounds did NOT exist, and that we go through the bounds ONLY after getting 
	the corresponding intersections as well too!
	Throw extra info -> trick you in the accounting
	The gap times also guaranteed to be in the meetings as well here !
	
Folks will assume that timings works like blocks -> but they can be more random 
	E.g. ['9:01', '10:01'], ['11:17', '11:32'] type of scheduling here
	Integer duration needs to account for a possibility such as '9:01' and '17:13'
	with integer = 5. We can keep adding on the minutes here for sure as ALL possible meetings
	must be generated. But we need a modulo operation as well. 
	Also a bad case -> meetingDuration = 90, and we span across 2 different hours
	E.g. '9:58', '11:30' -> then ['9:58', '11:28'] is a valid combo. 
	Idea -> convert HH:MM to an entire "MM" instead, and then reconvert back based on base60 division
	
	
	
	
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
		int m = calendar1.size();
		int n = calendar2.size();
		List<StringMeeting> merged = new ArrayList<StringMeeting>();
		merged.addAll(calendar1);
		merged.addAll(calendar2);
		String[] trueBounds = getBounds(dailyBounds1, dailyBounds2);
		
		// Sort in lexicographic / dictionary ordering
		// First by start times -> then, by their earlier end times ( for nested intervals )
		Collections.sort(merged, new Comparator<StringMeeting>(){
			public int compare(StringMeeting m1, StringMeeting m2)
			{
				int compareStart = compareTimes(m1.start, m2.start);
				if(compareStart == -1)
				{
					return -1;
				}
				else if ( compareStart == 1)
				{
					return 1;
				}
				else
				{
					int compareEnds = compareTimes(m1.end, m2.end);
					return compareEnds;
				}
			}
		});
		// Print out these meetings now
		// Confirm again the correctness of sorting the meeting times here. We may have a bad fully overlapping interval above us
  	ArrayList<StringMeeting> meetings = new ArrayList<StringMeeting>();
		for(int i = 0; i < merged.size(); ++i)
		{
			StringMeeting curr = merged.get(i);
			System.out.printf("[%s,%s]\t", curr.start, curr.end);
		}
		System.out.printf("\n");
		// String tempStart = "09:58";
		// String tempEnd = "13:31";
		// addToMeetingsList(tempStart, tempEnd, 90, meetings);
		// Oh -- you just have to check if the gap is large enough. But you need not actuall do all these splits :-P
		// for(int i = 0; i < meetings.size(); ++i)
			// System.out.printf("[%s,%s]\n",meetings.get(i).start, meetings.get(i).end);
		int ptr1 = 0;
		int ptr2 = 1;
		// Now consecutive A[i], A[i+1] comparisons taking place
		// Two pointer algo, but more like a SLL increment style instead.
		while(ptr1 < merged.size() - 1 && ptr2 < merged.size())
		{
			StringMeeting curr = merged.get(ptr1);
			StringMeeting nxt = merged.get(ptr2);
			// Compare end time ( curr ) with start time ( nxt ) 
			String currEnd = curr.end;
			String nxtStart = nxt.start;
			String nxtEnd = nxt.end;
			// System.out.printf("[%s,%s], ", curr.start, curr.end);
			int gapCheckVal = compareTimes(currEnd, nxtStart);
			if(gapCheckVal != 1)
			{
				// System.out.printf("Have a gap of currEnd with nxtStart \t ");	// printf need not a %s placeholder, if just printing string directly!
				// System.out.printf("\n");
				addToMeetingsList(currEnd, nxtStart, meetingDuration, meetings);
				ptr1 = ptr2;
				ptr2 = ptr1 + 1;
			}
			else if ( gapCheckVal == 1)	// There may be no gap -> but don't we have to go to the later meeting now?
			{
				// No gap -> but now check nestedness and which one is larger
				// Luckily, lexicographic sorting is well leverageable here too
				int compareEndsVal = compareTimes(currEnd, nxtEnd);
				if(compareEndsVal == 1) // first overlaps with second here
				{
					++ptr2;
				}
				else
				{
					ptr1 = ptr2;
					ptr2 = ptr1 + 1;
				}
			}
		}
		// We would add the last meeting here, assuming that there were no collisions, of course
		StringMeeting curr = merged.get(ptr1);
		String currEnd = curr.end;
		int gapCheckLast = compareTimes(currEnd, trueBounds[1]);
		if(gapCheckLast != 1)
		{
			addToMeetingsList(currEnd, trueBounds[1], meetingDuration, meetings);
		}				
		
		// Now clean up the meetings list
		
    return meetings;
  }
	
	// Method adds all meetings in between the gap provided by the two time intervals here
	private static void addToMeetingsList(String currEnd, String nxtStart, int meetingDuration, ArrayList<StringMeeting> meetings)
	{
		int[] currEndTime = getTime(currEnd);
		int[] nxtStartTime = getTime(nxtStart);
		int currBase = 60*currEndTime[0] + currEndTime[1];
		int nxtBase = 60*nxtStartTime[0] + nxtStartTime[1];
		if(currBase + meetingDuration <= nxtBase)
		{
				StringMeeting interval = new StringMeeting(currEnd, nxtStart);
				meetings.add(interval);
		}
	}	
	
	// Method adds all meetings in between the gap provided by the two time intervals here
	private static void addToMeetingsListTwo(String currEnd, String nxtStart, int meetingDuration, ArrayList<StringMeeting> meetings)
	{
		int[] currEndTime = getTime(currEnd);
		int[] nxtStartTime = getTime(nxtStart);
		int currBase = 60*currEndTime[0] + currEndTime[1];
		int nxtBase = 60*nxtStartTime[0] + nxtStartTime[1];
		while(currBase < nxtBase)
		{
			int nextEnd = currBase + meetingDuration;
			if(nextEnd <= nxtBase)
			{
				int leftStartMin = currBase % 60;
				int leftStartHr = currBase / 60;
				int rightStartMin = nextEnd % 60;
				int rightStartHr = nextEnd / 60;
				// Oh shit -> forgetting the string format specifier here ( for leading/trailing zeroes )
				// Should not matter too much -> integers parse back anyways?
				String newStart = String.format("%d:%d", leftStartHr, leftStartMin );
				if(leftStartMin < 9)
				{
					newStart = String.format("%d:0%d", leftStartHr, leftStartMin);
				}
				String newEnd = String.format("%d:%d", rightStartHr, rightStartMin );
				if(rightStartMin < 9)
				{
					newEnd = String.format("%d:%d", rightStartHr, rightStartMin );
				}
				StringMeeting interval = new StringMeeting(newStart, newEnd);
				meetings.add(interval);
				currBase = nextEnd;
			}
			else
			{
				break;
			}
		}
	}
	
			
		private static String[] getBounds(StringMeeting dailyBounds1, StringMeeting dailyBounds2)
		{
			String leftOne = dailyBounds1.start;
			String rightOne = dailyBounds1.end;
			String[] bounds = new String[]{leftOne, rightOne};
			
			String leftTwo = dailyBounds2.start;
			String rightTwo = dailyBounds2.end;
			
			if(compareTimes(leftOne,leftTwo) == 1)
			{
				bounds[0] = leftTwo;
			}
			if(compareTimes(rightOne,rightTwo) == 1)
			{
				bounds[1] = rightTwo;
			}
			return bounds;
		}
	
	// Basically a comparator function
	private static int compareTimes(String m1, String m2)
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
			else if ( times1[1] > times2[1])
			{
				return 1;
			}
			return 0;
		}
	}
	
	// Potential string parsing code involved here
	// Returns an array corresponding to meeting times
	private static int[] getTime(String serial)
	{
		String delim = ":";
		String[] comps = serial.split(delim);
		int hour = Integer.parseInt(comps[0]);
		int min = Integer.parseInt(comps[1]);	// was a bug
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
