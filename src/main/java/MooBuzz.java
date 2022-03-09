//USACO 2019 December Contest, Silver
//Problem 1. MooBuzz

import java.util.*;
import java.io.*;

public class MooBuzz {

	public static void main(String[] args) throws FileNotFoundException{
				
		Scanner in = new Scanner(new File("moobuzz.in"));
		
		int n = in.nextInt();

		in.close();
		
		//every 15, 7 are skipped and 8 are displayed
		
		int result = 0;
		
		if(n%8==0) {
			result = (n/8)*15-1;
		}else {
			result = (n/8)*15;
		}
		
		int count = 0;
		int i=1;
		
		while(count<n%8) {
			if(i%3!=0 && i%5!=0) {
				count++;
			}
			result++;
			i++;
			
		}
		
		PrintWriter out = new PrintWriter(new File("moobuzz.out"));
		System.out.println(result);
		out.println(result);
		out.close();

	}
}


