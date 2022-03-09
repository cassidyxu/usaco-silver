//USACO 2012 US Open, Bronze Division
//Problem 3. Islands

//wrong result??

import java.util.*;
import java.io.*;

public class Islands {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("islands.in"));
		int n = in.nextInt();
		// go through the farms in order of height
	    // know whats to the left or right of a piece of land
		Field[] farm = new Field[n+2];
		farm[0]=new Field(-1, 0);
		farm[n+1]=new Field(-1,n+1);
		
		for(int i=1; i<=n; i++) {
			farm[i]=new Field(in.nextInt(), i);
		}
		in.close();
		
		int result = 0;
		
		PriorityQueue<Field> hPQ = new PriorityQueue(n, 
			new Comparator<Field>() {
				public int compare(Field a, Field b) {
					return a.h-b.h;
				}
			}
		);
		
		for(int i=1; i<=n; i++) {
			hPQ.add(farm[i]);
		}
		
		while(hPQ.size()>1) {
			int islands = 0;
			Field curr = hPQ.poll();
			int ch = curr.h;	//currrent height
			
			// case 1
			if(farm[curr.x-1].h <= ch && ch > farm[curr.x+1].h) {
				islands--;
			}
			
			// case 2
			if(farm[curr.x-1].h > ch && ch <= farm[curr.x+1].h) {
				islands++;
			}
			
			Field next = hPQ.peek();
			if(next == null || next.h > ch) {
				result = Math.max(result, islands);
			}
		}
		

		PrintWriter out = new PrintWriter(new File("islands.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}

	static class Field {
		int h, x;

		Field(int h, int x) {
			this.h = h;
			this.x = x;
		}
	}


}


/*


islands
http://www.usaco.org/index.php?page=viewproblem2&cpid=132


Brute Force (but not even)
    Go through all of the water levels (1 billion different height)
        scroll on through and check how many islands there are

Real Brute Force
    1 billion different possible heights
    BUT N different heights we care about
    Go through each land mass: choose it's height as water level
        scroll on through and check how many islands there are
            HOW?
            for loop go through each piece of land
                check if its under water or over
                if we see land
                when we go from land to water (must be the end of an island)
                    islands++;

Linear Sweep
    go through all the water levels from bottom to top
    take the heights of the landmasses and sort them
        goes from bottom to top, BUT its 100,000 instead of 1 bil
        how do we find out how many islands there are?
            look only at the pecies of land that sank

Cases when a peice of land goes under         
1.     L   R    <-- still land
         M      <---- going under  islands++

2.       M      <----  going under  islands--
       L   R
       
3.     L
         M      <---- going under no change
           R

4.           R
          M      <---- going under no change
       L       


if we update the results every time islands changes, ties can overcount
    only update results after a whole water level is done
        when going from one water level to the other

Approach 1. keep track of which pieces of land you've checked

Approach 1.5 precook the code, find any contiguous landmasses with the same height, replace with 1 feild

Approach 2. break the ties where you pretend that L is lower and R is higher

5.   L  M       <-----  M going under
           R

     L  M  R   <------  M going under? 


assume every tie is lower
    -
   -
 -  

-3


assume every tie on left is less, and rihgt is more
         --
    -  --
 --- --

0

*/





