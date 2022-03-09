//USACO 2013 US Open, Bronze
//Problem 2. Blink

import java.util.*;
import java.io.*;

public class Blink {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File("blink.in"));
		int n = in.nextInt();
		long b = in.nextInt();
		
		int[] bulbs = new int[n];
		for(int i=0; i<n; i++) {
			bulbs[i]=in.nextInt();
		}
		
	    in.close();
	    
	    // key: toString-ified blulbs of that patern
	    Map<String, Long> prevStates = new HashMap();

	    int result = 0;
	    
	    long t = 0;
	    while(t<b) {
	    	String curState = Arrays.toString(bulbs);
	        if(prevStates.containsKey(curState) ){
	            long prevTime = prevStates.get(curState);
	            long cycleLen = t - prevTime;

	            long remaining = b - t;
	            
	            // long actuallyNeed = remaining % cycleLen;
	            
	            // OPTION 1: calculate how many cycles to go through
	            // TODO: write a for loop, then break
	            
	            
	            // OPTION 2: skip t ahead to the actual time desired
	            long fullCycles = remaining / cycleLen;
	            
	            t += fullCycles * cycleLen;
	            
	            prevStates.clear();    // like setting a boolean to false to not try to skip any more
	        } else {
	            prevStates.put(curState, t);
	            t++;
	            step(bulbs);
	        }
	    }
	    
	    PrintWriter out = new PrintWriter(new File("blink.out"));
	    System.out.println(result);
	    out.println(result);
	    out.close();
		

	}
	
	public static void step(int[] bulbs) {
		int lastBulb = bulbs[bulbs.length-1];
		
		for(int i = bulbs.length-1; i>0; i--) {
			if(bulbs[i-1]==1) {
				bulbs[i] = 1-bulbs[i];
			}
			
		}
		
		if(lastBulb == 1) {
			bulbs[0] = 1-bulbs[0];
		}
		
	}

}


/*

http://www.usaco.org/index.php?page=viewproblem2&cpid=279


largest int: 2^31 - 1
    long: 


      toggle 0 to 1, 1 to 0?
    1. use if
    2. 1 - bulbs[i]
    3. (bulbs[i] + 1)%2
    4. abs(bulbs[i] - 1)


Problem: B is too big
    we need some way to skip ahead

Find a repeated pattern
    skip ahead?
    


*/



