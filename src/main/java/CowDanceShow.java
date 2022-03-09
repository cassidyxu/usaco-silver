//USACO 2017 January Contest, Silver
//Problem 1. Cow Dance Show

import java.util.*;
import java.io.*;

public class CowDanceShow {
	static int n;
	static int[] cows;
	static int tMax;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("cowdance.in"));
		n = in.nextInt();
	    tMax = in.nextInt();
	    cows = new int[n];
	    for (int i = 0; i < n; i++) {
	      cows[i] = in.nextInt();
	    }
	    
	    in.close();
	    
	    int kMin = 1;
	    int kMax = n;
	    
	    while(kMin<kMax) {
	    	int kMid = (kMin = kMax)/2;
	    	if(checkStageSize(kMid)) {
	    		//kMax = kMid;
	    		kMin = kMid;
	    	}else {
	    		//kMin=kMid+1;
	    		kMax = kMid-1;
	    	}
	    }
	    
	    PrintWriter out = new PrintWriter(new File("cowdance.out"));
	    System.out.println(kMin);
	    out.println(kMin);
	    out.close();
	    
	}
	
	static boolean checkStageSize(int k) {
		
		PriorityQueue<Integer> finishTimes = new PriorityQueue();
		
		//fill the stage for first T
		for(int i=0; i<k; i++) {
			finishTimes.add(cows[i]);
		}
		
		//add remaining cows as space becomes available
		for(int i=k; i<n; i++) {
			int t = finishTimes.poll();
			t+=cows[i];
			if(t>tMax) {
				return false;
			}
			finishTimes.add(t);
		}
		
		return true;

	}
}


/*

Cow Dance Show
http://www.usaco.org/index.php?page=viewproblem2&cpid=690
    one extreme: K=0 -> inifite time
    other extreme: K=N -> max (d(i)) 
        maybe still wont work under Tmax
    
    Some k in between
    
    Part 1
        for some given K, can i find out if it fits under tmax?
        simulate the show
            DO NOT loop through each time step
            keep track of cows on stage
                skip to next cow to leave
                replace with next cow
                keep track of t, if t > tmax, break and say False
        
    Part 2 now what?
        Brute Force
            how to find the lowest K that works?
            check smallest number 1
                see if it works?
                if it doesnt, check next smallest number (2)
        Binary Search

*/



