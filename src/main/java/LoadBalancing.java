//USACO 2016 February Contest, Silver
//Problem 2. Load Balancing

// http://www.usaco.org/index.php?page=viewproblem2&cpid=619
// "Load Balancing"

import java.util.*;
import java.io.*;

public class LoadBalancing {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("balancing.in"));
		int n = in.nextInt();

		// TODO: set up x-sorted array also
		Cow[] xSorted = new Cow[n];
		Cow[] ySorted = new Cow[n];

		// TODO: read in cows, store references to them in BOTH arrays
		for(int i=0; i<n; i++) {
			Cow c = new Cow(in);
			xSorted[i] = c;
			ySorted[i] = c;
		}

		in.close();

		// TODO: sort x-sorted array also, with an appropriate Comparator
		Arrays.sort(xSorted, new Comparator<Cow>() {
			public int compare(Cow a, Cow b) {
				return a.x - b.x;
				// this result will be +, 0, or - depending on the
				//   sorted relationship of a and b
			}
		});
		Arrays.sort(ySorted, new Comparator<Cow>() {
			public int compare(Cow a, Cow b) {
				return a.y - b.y;
				// this result will be +, 0, or - depending on the
				//   sorted relationship of a and b
			}
		});

		// TODO: start the result at a high value so that you can get it
		//   lower as you slide the fences
		int result = n;

		// TODO: slide the fences in both directions, following the strategy
		//   we outlined
		
		int UL=0;
		int LL=0;
		int UR=n; 
		int LR=0;
		
		for(int i=0; i<n; i++) {
			
			
			
			int fenceVert = ySorted[i].y;//horizontal line for each y value
			int tempi = i;
			while(tempi<n && ySorted[i].y == ySorted[tempi].y) {
				UR--;
				LR++;
				tempi++;
			}
			i=tempi-1;
			
			int tempUR = UR;
			int tempLR = LR;
			
			for(int j=0; j<n; j++) {
				
				int fenceHoriz = xSorted[j].x;//vertical line for each x value
				int tempj = j;
				while(tempj<n && xSorted[j].x == xSorted[tempj].x) {
					
					if(xSorted[tempj].y>fenceVert) {
						UL++;
						UR--;
					}else {
						LL++;
						LR--;
					}

					tempj++;
					
				}
				j=tempj-1;
				
				if(Math.max(Math.max(UL, UR), Math.max(LL, LR))<result) {
					result = Math.max(Math.max(UL, UR), Math.max(LL, LR));
				}
				
			}
			
			UL=0;
			LL=0;
			UR=tempUR;
			LR=tempLR;
			
			
		}
		
		

		PrintWriter out = new PrintWriter(new File("balancing.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}

	static class Cow {
		int x, y;

		Cow(Scanner in) {
			x = in.nextInt();
			y = in.nextInt();
		}
	}
}




/* ANALYSIS

http://www.usaco.org/index.php?page=viewproblem2&cpid=619

https://docs.google.com/presentation/d/e/2PACX-1vRWmkUoOOCMQVytzeMnO8PHSRorZV7mqNXhu4ppw7oZmUbj-MH6Us-Q2gZ0yqm00x7WW4UsBm4WcrFR/pub?start=false&loop=false&delayms=3000&slide=id.p

brute force w/coord compression:
try every possible in-between fence position (1000)
  in both directions (1000)
    loop through all cows to see which quadrant they're in (1000)
    find max out of those quadrants (4)

estimate: ~1000^3 = 1 billion - too slow
O(n^3)

optimize this a bit:
sort the cows first, in 2 diff arrays sorted by 2 diff coordinates
then SLIDE the cows from one quadrant to another horizontally,
  keeping track of quadrant counts changing gradually (requires no inner
  loop of 1000 since only a few cows at a time are moving with each slide)
then, SLIDE the cows vertically, reset all the cows horizontally,
  and SLIDE horizontally again
repeat until you have SLIDED the fence all the way from top to bottom
  (and left to right every time)

estimate: 1000*1000 = 1 mil - no problem
O(n^2)

*/





