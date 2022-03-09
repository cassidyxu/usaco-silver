//USACO 2012 January Contest, Bronze Division
//Problem 1. Gifts

import java.util.*;
import java.io.*;

public class Gifts {

	public static void main(String[] args)	throws Exception {
		Scanner in = new Scanner(new File("gifts.in"));
		int n = in.nextInt();
	    int b = in.nextInt();
	    Gift[] arr = new Gift[n];
	    for (int i = 0; i < n; i++){
	        arr[i] = new Gift(in);
	    }
		in.close();
		
		Arrays.sort(arr);
		
		//no coupon 
		int next = 0;
		while(next < n && b - arr[next].p+arr[next].s >= 0) {
			b -= arr[next].p+arr[next].s;
			next++;
		}
		
		int result = next;
		
		boolean extra = false;
		if(next<n) {
			for(int i=next; i<n; i++) {
				int reduced = arr[i].p/2+arr[i].s;
				if(b - reduced >= 0) {
					extra = true;
					break;
				}
			}
		}
		if(next<n && !extra) {
			for(int i=0; i<next; i++) {
				int b2 = b + arr[i].p/2;
				if(b2 >= arr[next].p+arr[next].s) {
					extra = true;
					break;
				}
			}
		}
		
		if(extra)	result++;

		PrintWriter out = new PrintWriter(new File("gifts.out"));
		System.out.println(result);
		out.println(result);
		out.close();

	}
	
	static class Gift implements Comparable<Gift>{
		int p;
		int s;
		
		Gift(Scanner sc){
			p = sc.nextInt();
			s = sc.nextInt();
		}
		
		// neg, pos, zero
	    // neg means it comes BEFORE the other one
	    // pod means it comes AFTER the other one
	    // zero means they are TIED
		public int compareTo(Gift other) {
			return (p+s)-(other.p+other.s);
		}
		
	}

}

/*


// http://www.usaco.org/index.php?page=viewproblem2&cpid=103
// gifts

Greedy Algorithm
	always choose the problem based on a rule
		take a guess that that rule is always the best
		hard to be 100% sure that this is the case
	try to disprove it as much as you can
		find a situation where this rule wont choose the best option
	some sort of mini-proof in your head

First, lets think about the problem with no coupon
    Always give the cheapest gifts
		lets assume that if i didnt choose some cheaper cow, i could get more cows in
            then I must be able to get 2 cows in in the space that that cow leaves 
            when it's gone (plus the left over budget)
        that left over budgets wasnt enough for either of those 2 new cows
        those two cows MUST be larger
Adding the Coupon
    what is a good rule for who we should use the coupon on?
        choose the most expensive gift, use it on them
        (i will get the most bang for my buck and get the most money off)
    fill your budget, use the coupon on the most expensive cow
    	it could be that that cow gives us the least amount of saving because it's all in shipping
    fill your budget, use the coupon on the next cow we couldn't fit?
		it could be that that cow gives us the least amount of saving because it's all in shipping
	check them all
        first the ones that are in my budget
        second the ones that are just outside
How many extra cows could we fit with the use of a coupon?
	one at most
	What does this mean?
        looking to use cupon on current cows
            only checking for one possible cow to be entered
        looking to use cupon on cows taht havent made it
            whichever can jump in, we can stop there

*/


