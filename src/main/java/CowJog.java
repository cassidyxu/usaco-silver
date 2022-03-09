
//USACO 2014 December Contest, Bronze
//Problem 3. Cow Jog

import java.util.*;
import java.io.*;

public class CowJog {

	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner in = new Scanner(new File("cowjog.in"));
		
		int n = in.nextInt();
		long t = in.nextInt();
		
		
		int[] pos = new int[n];
		long[] speeds = new long[n];
		long[] finalPos = new long[n];
		
		for(int i=0; i<n; i++) {
			pos[i] = in.nextInt();
			speeds[i] = in.nextInt();
			finalPos[i] = pos[i]+speeds[i]*t;
		}
		
		int result = 1;
		
		long curr = finalPos[n-1];
		for(int i=n-2; i>=0; i--) {
			if(finalPos[i]<curr) {
				result++;
				curr=finalPos[i];
			}
		}
		
		PrintWriter out = new PrintWriter(new File("cowjog.out"));
		System.out.println(result);
		out.println(result);
		out.close();

	}

}


/*

5 3  

0 1  3
1 2  7
2 3  11
3 2  9
6 1  9


 */






