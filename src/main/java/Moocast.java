//USACO 2016 December Contest, Silver
//Problem 3. Moocast

import java.util.*;
import java.io.*;

public class Moocast {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("moocast.in"));
		int n = in.nextInt();
		
		Cow4[] cows = new Cow4[n];
		for(int i=0; i<n; i++) {
			cows[i] = new Cow4(in.nextInt(), in.nextInt(), in.nextInt());
		}
		
		in.close();

		int result = 0;
		int[] ranges = new int[n];
		for(int i=0; i<n; i++) {
			ranges[i]=broadcast(i, cows, new boolean[n]);
		}
		
		for(int i=0; i<n; i++) {
			if(ranges[i]>result)	result = ranges[i];
		}
		
		PrintWriter out = new PrintWriter(new File("moocast.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	public static int broadcast(int c, Cow4[] cows, boolean[] visited) {
		int count = 0;
		
		if(visited[c])return 0;
		visited[c]=true;
		
		Cow4 cc = cows[c];
		
		for(int i=0; i<cows.length; i++) {
			Cow4 c2 = cows[i];
			if(c2.getDist(cc)<=cc.power) {
				count+=broadcast(i, cows, visited);
			}
		}
		
		count++;
		
		return count;
	}
	
	public static class Cow4{
		
		int x, y;
		int power;
		
		Cow4(int x, int y, int power){
			this.x=x;
			this.y=y;
			this.power=power;
		}
		
		public double getDist(Cow4 other) {
			//return Math.abs(other.x-x)+Math.abs(other.y-y);
			return Math.sqrt((Math.abs(other.x-x)*Math.abs(other.x-x))+(Math.abs(other.y-y)*(Math.abs(other.y-y))));
		}
		
	}
	
}





