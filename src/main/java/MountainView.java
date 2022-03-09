//USACO 2019 January Contest, Silver
//Problem 3. Mountain View

import java.util.*;
import java.io.*;

public class MountainView {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("mountains.in"));
		int n = in.nextInt();
		Mountain[] mountains = new Mountain[n];
		for(int i=0; i<n; i++) {
			mountains[i]= new Mountain(in.nextInt(), in.nextInt());
		}
		in.close();
		
		Arrays.sort(mountains);

		int result = 0;
		int rightmost = Integer.MIN_VALUE;
		
		for(int i=0; i<n; i++) {
			
			Mountain m = mountains[i];
			if(m.x+m.y > rightmost) {
				result++;
				rightmost = m.x+m.y;
				
			}
			
		}

		PrintWriter out = new PrintWriter(new File("mountains.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	public static class Mountain implements Comparable<Mountain>{
		int x, y;
		
		Mountain(int x, int y){
			this.x=x;
			this.y=y;
		}
		
		public int compareTo(Mountain other) {
			
			if(other.x-other.y == x-y) {
				return (other.x+other.y)-(x+y);
			}
			
			return ((x-y)-(other.x-other.y));
			
		}

		
	}
	
}


