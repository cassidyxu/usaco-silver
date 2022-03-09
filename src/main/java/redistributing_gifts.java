import java.util.*;
import java.io.*;

public class redistributing_gifts {

	public static void main(String[] args) throws Exception{
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		ArrayList<ArrayList<Integer>> prefs = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<n; i++) {
			ArrayList<Integer> pref = new ArrayList();
			boolean found=false;
			for(int j=0; j<n; j++) {
				int gift = in.nextInt();
				if(gift!=i+1 && !found) {
					pref.add(gift);
				}
				if(gift==i+1)	found=true;
			}
			prefs.add(pref);
		}
		
		for(int i=0; i<n; i++) {
			ArrayList<Integer> pref = prefs.get(i);
				
			boolean found=false;
			for(int j=0; j<pref.size(); j++) {
				int gift = pref.get(j);
				if(prefs.get(gift-1).contains(i+1)) {
					System.out.println(gift);
					found=true;
					continue;
				}
			}
			
			if(!found)	System.out.println(i+1);
			
			
		}

	}
	
}

