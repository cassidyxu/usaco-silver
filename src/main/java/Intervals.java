import java.util.*;
import java.io.*;

public class Intervals {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		
		ArrayList<Interval> intervals = new ArrayList();
		for(int i=0; i<n; i++) {
			intervals.add(new Interval(in.nextInt(), in.nextInt()));
		}
		Collections.sort(intervals);
		
		int c=0;
		for(int i=0; i<=2*m; i++) {
			int k=i;
			long count=0;
			loop2:
			for(int j=0; j<n; j++) {
				Interval a = intervals.get(j);
				if(a.l <= k) {
					
					loop3:
					for(int o=0; o<=j; o++) {
						c++;
						
						Interval b = intervals.get(o);
						if(a.l+b.l<=k) {
							if(a.r+b.r>=k) {
								if(!a.equals(b)) {
									count++;
								}
								count++;
							}

						}else {
							break loop3;
						}
						
					}
					
				}else {
					break loop2;
				}
		
				
			}
			System.out.println(count);
		}
		
		//System.out.println(c);

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
		
		public boolean equals(Interval other) {
			if(this.l==other.l && this.r==other.r)	return true;
			return false;
		}
	}

}
