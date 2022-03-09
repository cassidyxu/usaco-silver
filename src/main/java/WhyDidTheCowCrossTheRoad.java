//USACO 2017 February Contest, Silver
//Problem 1. Why Did the Cow Cross the Road

//incorrect

import java.util.*;
import java.io.*;

public class WhyDidTheCowCrossTheRoad {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("helpcross.in"));
		int c = in.nextInt();
		int n = in.nextInt();

		List<Integer> chickens = new ArrayList<Integer>();
		PriorityQueue<Cow2> cows = new PriorityQueue(
				n,
				new Comparator<Cow2>() {
					public int compare(Cow2 c1, Cow2 c2) {
						return c1.b == c2.b ? c2.a - c1.a : c1.b - c2.b;
					}
				}
		);


		for(int i=0; i<c; i++) {
			chickens.add(in.nextInt());
		}
		Collections.sort(chickens);
		for(int i=0; i<n; i++) {
			cows.add(new Cow2(in.nextInt(),in.nextInt()));
		}

		in.close();

		int result = 0;

		while (cows.size() > 0) {
			Cow2 cow = cows.poll();
			int chickenIndex = getClosestChickenIndex(chickens, cow);
			if (chickenIndex != -1 && chickens.get(chickenIndex) >= cow.a) {
				result++;
				chickens.remove(chickenIndex);
			}
		}

		//		while (chicken != null && cow != null) {
//			if (chicken >= cow.a && chicken <= cow.b) {
//				result++;
//				cow = cows.poll();
//				chicken = chickens.poll();
//			} else {
//				while (cow.a > chicken && chickens.peek() != null) {
//					chicken = chickens.poll();
//				}
//				while (cow.b < chicken && cows.peek() != null) {
//					cow = cows.poll();
//				}
//				while (cow.a > chicken && chickens.peek() != null) {
//					chicken = chickens.poll();
//				}
//				while(a>chicken && chickens.peek()!=null) {
//					chicken = chickens.poll();
//				}
//				
//				if(chicken>=a && chicken<=b) {
//					result++;
//					cow = cows.poll();
//					chicken = chickens.poll();
//				} else {
//					while(b<chicken && cows.peek()!=null) {
//						cow = cows.poll();
//						a = cow.a;
//						b = cow.b;
//					}
//				}
//				
//			}
//		}





		PrintWriter out = new PrintWriter(new File("helpcross.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}

	// get the chicken that's the closest left to cow's end time
	static int getClosestChickenIndex(List<Integer> chickens, Cow2 cow) {
		for (int i = 0; i < chickens.size(); i++) {
			if (chickens.get(i) > cow.b) {
				return i - 1;
			}
		}
		return -1;			
	}
}

class Cow2 {

	public int a, b;

	Cow2(int a, int b){
		this.a=a;
		this.b=b;
	}

	@Override
	public boolean equals(Object o) {
		if (null == o || !(o instanceof Cow2)) {
			return false;
		}
		Cow2 c = (Cow2)o;
		return c.a == a && c.b == b;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(a, b);
	}
	
}




/*

"Why Did the Cow Cross the Road" (Silver version 1)- 
helpcross: http://usaco.org/index.php?page=viewproblem2&cpid=714

Combine a "linear sweep" approach with a cow priority system to solve this problem. 
Specifically, you will want to sweep through the chickens and cows in a logical order 
and "assign" them to each other.

We call this priority system a "greedy algorithm" when we can go with an apparoch 
along the lines of "whatever chicken is crossing, just pick the next available cow". 
Think about what exact criteria would determine "the next cow" to choose, to pair up 
chickens and cows as efficienctly as possible.

Linear sweep means working through the data based on some criteria you are 
"sweeping through" in order, gradually updating your answer with each "movement" 
through the data. This is similar to the "edges" of a sliding window producing 
gradual changes in a result, except this is changing the overall result gradually 
rather than describing something in a window.

A TreeSet can be used for prioritization, but a PriorityQueue is slightly more 
efficient for this case where we only ever want "the next one" within a priority system. 
You can look up its documentation on the official Java reference site if you aren't 
familiar with it, or just use a TreeSet for now.


 */


/*






		Cow2 cow = cows.poll();
			int a = cow.a;
			int b = cow.b;

			int chicken = chickens.poll();

			while(chicken > b && cows.peek()!=null) {
				cow = cows.poll();
				a = cow.a;
				b = cow.b;
			}

			while(chicken<a && chickens.peek()!=null) {
				chicken = chickens.poll();
			}

			if(chicken>=a && chicken<=b) {
				result++;
			}



 */

