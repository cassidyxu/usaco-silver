//USACO 2018 December Contest, Silver
//Problem 3. Mooyo Mooyo

import java.io.*;
import java.util.Scanner;

public class MooyoMooyo {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("mooyomooyo.in"));
		int n = in.nextInt();
		int k = in.nextInt();
		
		int[][] grid = new int[n][10];
		for(int i=0; i<n; i++) {
			String s = in.next();
			for(int j=0; j<10; j++) {
				grid[i][j]=Integer.parseInt(String.valueOf(s.charAt(j)));
			}
		}
		in.close();
		
		boolean done = false;
		while(!done) {
			if(pop(grid, k))	done = true;
			//gravity
		}
		
		
		PrintWriter out = new PrintWriter(new File("mooyomooyo.out"));
		
		for(int r=0; r<grid.length; r++) {
			for(int c = 0; c<grid[0].length; c++) {
				System.out.print(grid[r][c]);
				out.print(grid[r][c]);
			}
			System.out.println();
			out.println();
		}
		

	}
	
	public static boolean pop(int[][] grid, int k) {
		boolean noPops = true;
		
		for(int r=0; r<grid.length; r++) {
			for(int c=0; c<grid[0].length; c++) {
				//if(grid[r][c]==0)	continue;
				
				boolean[][] flooded = new boolean[grid.length][10];
				if(floodSize(r, c, grid, flooded, grid[r][c])>=k) {
					boolean[][] flooded2 = new boolean[grid.length][10];
					floodZero(r, c, grid, flooded2, grid[r][c]);
					noPops=false;
				}
			}
		}
		
		return noPops;
	}
	
	public static void gravity() {
		
	}
	
	public static int floodSize(int r, int c, int[][] grid, boolean[][] flooded, int val) {
		
		int size = 0;
		
		if(r<0 || c<0 || r>=grid.length || c>=grid[0].length) {
			return 0;
		}
		
		if(flooded[r][c])	return 0;
		
		if(grid[r][c]==0)	return 0;
		
		flooded[r][c]=true;
		
		if(grid[r][c]!=val) {
			return 0;
		}
		
		size++;
		size+=floodSize(r+1, c, grid, flooded, val);
		size+=floodSize(r-1, c, grid, flooded, val);
		size+=floodSize(r, c+1, grid, flooded, val);
		size+=floodSize(r, c-1, grid, flooded, val);
		
		return size;
		
	}
	
	public static void floodZero(int r, int c, int[][] grid, boolean[][] flooded, int val) {

		if(r<0 || c<0 || r>=grid.length || c>=grid[0].length) {
			return;
		}

		if(flooded[r][c])	return;

		flooded[r][c]=true;

		if(grid[r][c]!=val) {
			return;
		}
		
		grid[r][c]=0;

		floodZero(r+1, c, grid, flooded, val);
		floodZero(r-1, c, grid, flooded, val);
		floodZero(r, c+1, grid, flooded, val);
		floodZero(r, c-1, grid, flooded, val);


	}

}



/*


Mooyo Mooyo
http://www.usaco.org/index.php?page=viewproblem2&cpid=860

    STEP 1: find connected Regions, pop them
        FLOOD FILL -> find out how big the region is
        FLOOD FILL -> change them all to 0
    
    
    STEP 2: gravity
        whats the rule for when things fall?
            if there is 0 below me, i move down 1
        
        Repeat if anything shifted last round
            Loop through every square
                check if the rule applies, apply it!
                
        Does this give us the right answer?
            it does!
        
        Does this run in time?
            100000
            
        
        
        We can do better
            if there are multiple 0s, drop all of them at once
                might still be looping N times for each cell in board
                1, 0,1 ,0,1,0,1,0,1,0 problem
            
            take care of each column at a time
            
            keep track of # of nums>0 in each row
                requires work from STEP 1 as well
    
    STEP 3: REPEAT!



*/



