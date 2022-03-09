

import java.util.*;
import java.io.*;

public class SocialDistancing {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("socdist.in"));
		int n = in.nextInt();
		int m = in.nextInt();

		Interval[] intervals = new Interval[m];
		for(int i=0; i<m; i++) {
			intervals[i] = new Interval(in.nextInt(), in.nextInt());
		}
		
		Arrays.sort(intervals);

		in.close();

		int result = 0;
		
		int min = 1;
	    int max = intervals[intervals.length-1].end;
	    
	    while(min<max) {
	    	int mid = -1;
	    	if((min + max)%2==1) {
	    		mid = (min + max)/2 +1;
	    	}else {
	    		mid = (min + max)/2;
	    	}
	    	
	    	if(works(intervals, mid, n)) {
	    		min = mid;
	    	}else {
	    		max = mid-1;
	    	}
	    }
	    
	    result = min;


		PrintWriter out = new PrintWriter(new File("socdist.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}

	public static boolean works(Interval[] intervals, int d, int n) {
		
		ArrayList<Integer> cowLocs = new ArrayList();

		int count = 0;
		int curr = -d;
		
		for(int i=0; i<intervals.length; i++) {
			Interval range = intervals[i];
			int begin = range.begin;
			int end = range.end;
			
			if(curr<=begin) {
				curr = begin;
			}else if(curr>end){
				continue;
			}
			
			while(curr<=end) {
				count++;
				cowLocs.add(curr);
				curr+=d;
			}
			
			
		}
		
		if(count>=n) {
			return true;
		}
		
		return false;

	}



}

class Interval implements Comparable<Interval>{
	int begin;
	int end;

	Interval(int begin, int end){
		this.begin = begin;
		this.end = end;
	}

	public int compareTo(Interval other) {
		return this.begin-other.begin;
	}

}


/*

Complete this problem, for which binary search works as a primary approach. 
You can find out how well cows "fit" in the intervals in a reasonable amount of time - 
as long as you use coordinate compression - if you have already "assumed" a certain amount 
of distance will be between them. Therefore, you can binary search on what you should assume 
the distance to be, to find the greatest distance that "works" to fit all cows.

A note from Andrew: Incorrect advice may have been given in the past about the "averaging" 
process to find the midpoint of a search area. As it turns out, in this problem (depending on 
your setup) you may well need to specifically round UP rather than simply truncating when finding
the midpoint! Apologies for not thinking about this case when introducing the concept the first time. As always, thinking about specific cases will reveal these issues!

Relatedly, the "direction" of what works and what doesn't is somewhat flipped here - 
pay close attention to the meaning of your variables that control the search process; 
how do they describe what you know based on the distances you've checked? Also note that 
the coordinates themselves are very large, up to 10^18. Make sure to use appropriate 
variable types, including the Scanner methods used. Another hint: There may be a sorting
aspect of this problem that the description didn't make obvious. A final bit of advice: 
Do think carefully about the idea of "spacing cows out" within the intervals given - 
the math is not advanced, but can be tricky. Write down an example to be sure!


 */



