//http://www.usaco.org/index.php?page=viewproblem2&cpid=359
// ??

import java.util.*;
import java.io.*;

public class Baseball {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new File("baseball.in"));
    int n = in.nextInt();
    
    int[] cows = new int[n];
    for(int i=0; i<n; i++) {
    	cows[i] = in.nextInt();
    }
    
    in.close();
    
    Arrays.sort(cows);

    int result = 0;
    
    for(int a=0; a<n-2; a++) {
    	for(int b=a+1; b<n-1; b++) {
    		int dist = cows[b]-cows[a];
    		
    		int cxLeft = cows[b]+dist;
    		int cxRight = cows[b]+2*dist;
    		//inclusive x values
    		
    		//inclusive
    		// the index of the first cow that can be Z
    		int ciLeft = Arrays.binarySearch(cows, cxLeft);
    		//maybe i got the index of the first cow in range to be Z!
            //maybe i didnt ...
            if(ciLeft<0){
                ciLeft = -ciLeft - 1;
                //just use the insertion point
            }
            
            //exclusive
            // the index of the first cow that cannot be Z
            int ciRight = Arrays.binarySearch(cows, cxRight);
            if(ciRight<0){
                ciRight = -ciRight - 1;
                //just use the insertion point
            }else {
            	ciRight++;
            }
            
            result += ciRight - ciLeft;
            
    		
    	}
    	
    }

    PrintWriter out = new PrintWriter(new File("baseball.out"));
    System.out.println(result);
    out.println(result);
    out.close();
  }
}


/*

choose x and y first, then find possible z

3 nested for loops


 http://www.usaco.org/index.php?page=viewproblem2&cpid=359
 baseball

  
       A          B
  X--------Y--------|---Z----|

  
 2A <= A+B <= 3A
  
  nested loops
    loop through all possible X
        loop through all possible Y (after X)
            loop through all possible Z (after Y)
                search for the insertion points of y+A and y+2A
    Good but 1 billion itterations (TOO MUCH!)
  
  Binary search
    in a sorted array, help find where the insertion point is of an element
        is i was to add something, where would it go to keep the sorting?
    O(log(n))
  
  Arrays JavaDoc
    https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html


cR
on hit
    give us 13: the last cow that could possibly be c
on miss
    give us -14 -> insertion point 13: 
        the first cow that cannot possibly be c

                              9      10  11   12       13
 *    *    **  *    *     * * *      *   *     *       *  * *
 
      ^             ^                ^               ^
      a             b                cL              cR






*/



