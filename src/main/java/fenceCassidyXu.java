import java.util.*;
import java.io.*;

public class fenceCassidyXu {

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		ArrayList<Interval> intervals = new ArrayList();
		for(int i=0; i<n; i++) {
			intervals.add(new Interval(in.nextInt(), in.nextInt()));
			
		}
		
		Collections.sort(intervals);
		
		int count=1;
		int l=intervals.get(0).l;
		int r=intervals.get(0).r;
		for(int i=1; i<n; i++) {
			if(intervals.get(i).l<=r) {
				if(intervals.get(i).r>r) {
					r=intervals.get(i).r;
				}
			}else {
				count++;
				l=intervals.get(i).l;
				r=intervals.get(i).r;
			}
		}
		
		System.out.println(count);
		

	}
	
	static class Interval implements Comparable<Interval>{
		int l,r;
		
		Interval(int l, int r){
			this.l=l;
			this.r=r;
		}
		
		public int compareTo(Interval other) {
			if(this.l==other.l) {
				return this.r-other.r;
			}
			
			return this.l-other.l;
		}
		
	}

}
