//momentum problem

import java.io.*;
import java.util.*;
public class CowWall {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();  //string to be processed
		String s2 = br.readLine();  //wall string
		br.close();
		
		long cnt = 0;  //# of times "cow" appears in s1
		ArrayList<Integer> locs = new ArrayList();
		
		int n = s1.length();
		int m = s2.length();
		for(int j=0; j+m<=n; ) {
			if(s1.substring(j, j+m).equals(s2)) {
				locs.add(j);
				j += m;
			}
			else
				j++;
		}
		locs.add(n);
		
		//process each segment [locs[j], locs[j+1]) and count the # of times "cow" appears within
		
		// work backwards
		// w, ow, cow  - recursion
		for(int l=0; l<locs.size(); l++) {
			
		}
		
		System.out.println(cnt);
	}
	
	public int find(int left, int right, char target, String s1) {
		
		int count = 0;
		int product = 1;
		
		for(int i=right; i>=left; i--) {
			if(s1.charAt(i)==target) {
				
			}
			
		}
		
		//NOT DONE
		return 0;
		
	}
	
}
