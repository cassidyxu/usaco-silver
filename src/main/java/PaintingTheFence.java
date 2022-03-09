//USACO 2013 January Contest, Bronze
//Problem 2. Painting the Fence

import java.util.*;
import java.io.*;

public class PaintingTheFence {

	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner in = new Scanner(new File("paint.in"));
	    
	    int n = in.nextInt();
	    Map<Integer, Integer> coatChange = new TreeMap();
	    int prevLoc = 0;
	    
	    for(int i=0; i<n; i++) {
	    	int steps = in.nextInt();
	    	boolean isRight = in.next().equals("R");
	    	int loc = prevLoc + (isRight ? +1: -1) * steps;
	    	
	    	int left = Math.min(loc, prevLoc);
	    	int right = Math.max(loc, prevLoc);
	    	
	    	
	    	Integer change = coatChange.get(left);
	    	if(change == null) {
	    		change = 0;
	    	}
	    	coatChange.put(left,  change+1);
	    	
	    	if(change == null) {
	    		change = 0;
	    	}
	    	coatChange.put(right,  change+1);
	    	
	    }
	    
	    in.close();
	    int result = 0;
	    
	    
	    PrintWriter out = new PrintWriter(new File("paint.out"));
	    System.out.println(result);
	    out.println(result);
	    out.close();
		
	}

}


/*

Coordinate
    instead of keeping track of info for each position

    keep track of the important points and the ranges

Paint

    http://www.usaco.org/index.php?page=viewproblem2&cpid=224


    sample

    -4 -3  0  2

    		|--|

     |------|

     |-|
     
	not keeping track of the coats
    keep track of the CHANGES in coats and where they happen


    info we need to know

    -4 to -3: 2
    -3 to 0: 1
    0 to 2: 2
    
    Map
    let us store the point of interest
    also make them point to a value: relative change in coating
    
    2R (start at 0)
    0 to 2
    key 0: value +1
    key 2: value -1
    key 2: value = value - 1
    key -4: value = value + 1


    Simple solution: make an array to keep track of each pos coating
    2 bil long, too slow, too much memory

    ArrayList: modular length
    solves for MOST cases
    worst case scenario still bad



*/


/*

template

import java.util.*;
import java.io.*;

public class problem {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new File("problem.in"));
    
    
    
    in.close();
    int result = 0;
    
    
    PrintWriter out = new PrintWriter(new File("problem.out"));
    System.out.println(result);
    out.println(result);
    out.close();
  }
}


 */



