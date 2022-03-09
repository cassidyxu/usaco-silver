//USACO 2019 January Contest, Silver
//Problem 2. Icy Perimeter

import java.io.*;
import java.util.*;

public class IcyPerimeter {
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("perimeter.in"));
		int n = in.nextInt();
		char[][] grid = new char[n][n];
		for(int i=0; i<n; i++) {
			String st = in.next();
			for(int j=0; j<n; j++) {
				grid[i][j]=st.charAt(j);
			}
		}
		
		ArrayList<Integer> areas = new ArrayList();
		ArrayList<Integer> perimeters = new ArrayList();
		boolean[][] flooded = new boolean[n][n];
		boolean[][] flooded2 = new boolean[n][n];
		for(int r=0; r<grid.length; r++) {
			for(int c=0; c<grid[0].length; c++) {
				if(!flooded[r][c]) {
					areas.add(floodArea(r, c, grid, flooded));
					perimeters.add(floodPerimeter(r, c, grid, flooded2));
				}
			}
		}
		
		int result = -1;	//largest area
		int result2 = -1;	//perimeter
		for(int i=0; i<areas.size(); i++) {
			int area = areas.get(i);
			if(area>result) {
				result = area;
				result2 = perimeters.get(i);
			}
			if(area==result && result2!=0) {
				if(perimeters.get(i)<result2)	result2 = perimeters.get(i);
			}
		}
		
		PrintWriter out = new PrintWriter(new File("perimeter.out"));
		System.out.println(result + " " + result2);
		out.println(result + " " + result2);
		out.close();
		
	}
	
	public static int floodArea(int r, int c, char[][] grid, boolean[][] flooded) {
		
		int area = 0;
		
		// out of bounds
		if(r<0 || c<0 || r>=grid.length || c>=grid[0].length) {
			return 0;
		}
		
		// already visited
		if(flooded[r][c])	return 0;	
		
		// if its not a #
		if(grid[r][c]=='.') {
			return 0;
		}
		
		flooded[r][c]=true;
		
		area++;
		area+=floodArea(r+1, c, grid, flooded);
		area+=floodArea(r-1, c, grid, flooded);
		area+=floodArea(r, c+1, grid, flooded);
		area+=floodArea(r, c-1, grid, flooded);
		
		return area;
		
	}
	
	public static int floodPerimeter(int r, int c, char[][] grid, boolean[][] flooded) {

		int perimeter = 0;

		if(r<0 || c<0 || r>=grid.length || c>=grid[0].length) {
			return 0;
		}

		if(flooded[r][c])	return 0;	

		if(grid[r][c]=='.') {
			return 0;
		}

		flooded[r][c]=true;
		
		if(r+1<grid.length && grid[r+1][c]=='.') {
			perimeter++;
		}
		if(r-1>=0 && grid[r-1][c]=='.') {
			perimeter++;
		}
		if(c+1<grid[0].length && grid[r][c+1]=='.') {
			perimeter++;
		}
		if(c-1>=0 && grid[r][c-1]=='.') {
			perimeter++;
		}
		
		if(r==0 || r==grid.length-1) {
			if(c==0 || c==grid[0].length-1) {
				perimeter+=2;
			}else {
				perimeter++;
			}
		}else if (c==0 || c==grid[0].length-1) {
			perimeter++;
		}
		

		perimeter+=floodPerimeter(r+1, c, grid, flooded);
		perimeter+=floodPerimeter(r-1, c, grid, flooded);
		perimeter+=floodPerimeter(r, c+1, grid, flooded);
		perimeter+=floodPerimeter(r, c-1, grid, flooded);

		return perimeter;

	}

}


/*

##....
....#.
.#..#.
.#####
...###
....##

33....
....3.
.3..2.
.22102
...201
....22

*/
