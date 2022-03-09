//http://static.ktbyte.com/cs91/skyline.pdf

import java.util.*;
import java.io.*;

public class Skyline {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("skyline.in"));
		int n = in.nextInt();
		int b = in.nextInt();

		int[] buildings = new int[n+1];

		for(int i = 1; i <= n; i++){
			buildings[i] = in.nextInt() / b;
		}
		in.close();

		int result = 0;

		int sum = 0;
		for(int i=1; i<=b; i++) {
			sum+=buildings[i];
		}
		result = sum;

		for(int right = b+1; right <= n; right++) {
			int left = right - b + 1;
			sum -= buildings[left - 1];
			sum += buildings[right];

			result = Math.max(result, sum);

		}

		PrintWriter out = new PrintWriter(new File("skyline.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}



/*

//my code

import java.io.*;
import java.util.*;

public class skyline {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("skyline.in"));
		int n = in.nextInt();
		int b = in.nextInt();

		long[] buildings = new long[n];
		for(int i=0; i<n; i++) {
			buildings[i]=in.nextInt();
		}

		in.close();

		long result = 0;
		int center = 0;

		long sum = 0;
		for(int i=0; i<b; i++) {
			sum+=buildings[i];
		}
		result = sum;
		center = b/2+1;

		for(int i=1; i<=n-b; i++) {
			sum-=buildings[i-1];
			sum+=buildings[i+b-1];
			if(sum>result) {
				result = sum;
				center = i+b/2+1;
			}
		}

	    PrintWriter out = new PrintWriter(new File("skyline.out"));
	    System.out.println(center);
	    System.out.println(result/b);
	    out.println(center);
	    out.println(result/b);
	    out.close();

	}

}

 */


/*


Welcome to CS91 week 3!

http://static.ktbyte.com/cs91/skyline.pdf


Skyline Photography




height goes up to 1 billion
    long[] can store values up to 1 billion
    int[] can store the height / by B

B = 5

100 *****
200 ***** *****
120 ***** **
60  ***
100 *****
220 ***** ***** **
60  ***

100 200 120 60 100 -> 116
    200 120 60 100 220 -> 
        120 60 100 220 60 -> 

Brute Force
    checking each starting position of window, go through and add sum, divide to get average

Keep a sum of current window
    each time the window shifts
        subtract the number that was at my previous starting index
        add the number that just entered the window, new starting index + b

Position
    start of the frame

concept of a sliding window

 */



