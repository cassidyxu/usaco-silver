//USACO 2011 December Contest, Bronze Division
//Problem 3. Escaping the Farm

import java.util.*;
import java.io.*;

public class EscapingTheFarm {
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("escape.in"));
		int n = in.nextInt();
		int[] weights = new int[n];
		for(int i=0; i<n; i++) {
			weights[i]=in.nextInt();
		}
		in.close();

		int result = 0;
		result = recursion(0,0,weights);

		PrintWriter out = new PrintWriter(new File("escape.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	public static int recursion(int sum, int i, int[] weights) {
		int count1 = 0;
		int count2 = 0;
		
		if(i==weights.length) {
			return 0;
		}
		
		if(!causesCarries(sum, weights[i])) {
			count1++;
			count1+=recursion(sum+weights[i], i+1, weights);
		}
		count2+=recursion(sum, i+1, weights);
		
		
		return Math.max(count1, count2);
		
	}

	static boolean causesCarries(int a, int b) {
		while (a > 0 && b > 0) {
			if (a % 10 + b % 10 >= 10) return true;

			a /= 10;
			b /= 10;
		}

		return false;
	}
}





