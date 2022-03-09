import java.util.Arrays;
import java.util.Scanner;

public class ConvolutedIntervals {

	private static class Interval implements Comparable<Interval> {
		int a;
		int b;
		public Interval(int a, int b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(Interval o) {
			return this.a - o.a;
		}
	};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();

		Interval[] intervals = new Interval[n];
		for(int i=0; i<n; i++) {
			intervals[i] = new Interval(in.nextInt(), in.nextInt());
		}
		Arrays.sort(intervals);
	
		
		int beginSearchIdx = 0;
		Interval[][] memoization = new Interval[n][n];
		for (int k=0; k<=m*2; k++) {
			long count = 0;
			int mid = k/2;
			beginSearchIdx = Arrays.binarySearch(intervals, beginSearchIdx >= 0 ? beginSearchIdx : 0, intervals.length, new Interval(mid, mid));
			if (beginSearchIdx >= 0) {
				while (beginSearchIdx+1 < intervals.length && intervals[beginSearchIdx+1].a == mid) {
					beginSearchIdx++;
				}
			} else {
				beginSearchIdx = (beginSearchIdx + 1) * -1 - 1;
			}

			for (int i=0; i<=beginSearchIdx; i++) {
				for (int y=i; y<intervals.length; y++) {
					if (memoization[i][y] == null) {
						memoization[i][y] = new Interval(intervals[i].a + intervals[y].a,
								intervals[i].b + intervals[y].b);
					}
					if (memoization[i][y].a <= k &&
						memoization[i][y].b >= k ) {
						count++;
						if (i != y) {
							count++;
						}
					}
				}
			}
			System.out.println(count);
		}

	}

}
