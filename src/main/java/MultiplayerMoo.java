//USACO 2018 US Open Contest, Silver
//Problem 3. Multiplayer Moo

import java.util.*;
import java.io.*;

public class MultiplayerMoo {

	public static void main(String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new File("multimoo.in"));

		int n = in.nextInt();
		int[][] grid = new int[n][n];
		ArrayList<Integer> nums = new ArrayList();
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				int x = in.nextInt();
				grid[r][c]=x;
				nums.add(x);
			}
		}
		
		in.close();
		
		int result = 0;
		ArrayList<Integer> results = new ArrayList();
		int num = -1;
		TreeSet<Integer> neighbors = new TreeSet();
		
		boolean[][] visited = new boolean[n][n];
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(!visited[r][c])	results.add(flood(grid, visited, r, c, grid[r][c]));
			}
		}
		
		for(int i=0; i<results.size(); i++) {
			if(results.get(i)>result) {
				result = results.get(i);
//				int r = i/4;
//				int c = i%4;
//				boolean[][] visited = new boolean[n][n];
//				neighbors = getNeighbors(grid, visited, r, c, grid[r][c]);
//				num = grid[r][c];
			}
		}
		
		
		int result2 = 0;
		ArrayList<Integer> results2 = new ArrayList();
		int count = 0;
		
		//lots of overcount - same region being counted over and over 
		//	solution - use set, except then i dont know the coords of where a region is
		//		right now for r, c i used i/4 and i%4 but if i make set, coords will be ?
		for(int i=0; i<nums.size()-1; i++) {
			for(int j=i+1; j<nums.size(); j++) {
				boolean[][] visited2 = new boolean[n][n];
				results2.add(flood2(grid, visited2, i/4, i%4, nums.get(i), nums.get(j)));
				
			}
			
		}
		System.out.println(count);
		
		
		
		for(int i=0; i<results2.size(); i++) {
			if(results2.get(i)>result2) {
				result2 = results2.get(i);
			}
		}
		
		
		
		PrintWriter out = new PrintWriter(new File("multimoo.out"));
		System.out.println(result);
		out.println(result);
		//out.close();
		
//		System.out.print(num + " ");
//		for(Integer i: neighbors) {
//			System.out.print(i + " ");
//		}
//		
//		System.out.println();
		
		System.out.println(result2);
		out.println(result2);
		out.close();

	}
	
	public static int flood(int[][] grid, boolean[][] visited, int r, int c, int target) {
		
		int count = 0;
		
		if(r<0 || r>=grid.length || c<0 || c>=grid.length)	return 0;
		if(visited[r][c])	return 0;
		if(grid[r][c]!=target)	return 0;
		
		visited[r][c]=true;
		count++;
		
		count+=flood(grid, visited, r, c+1, target);
		count+=flood(grid, visited, r, c-1, target);
		count+=flood(grid, visited, r+1, c, target);
		count+=flood(grid, visited, r-1, c, target);
		
		return count;
		
	}
	
	public static int flood2(int[][] grid, boolean[][] visited, int r, int c, int target1, int target2) {

		int count = 0;

		if(r<0 || r>=grid.length || c<0 || c>=grid.length)	return 0;
		if(visited[r][c])	return 0;
		if(grid[r][c]!=target1 && grid[r][c]!=target2)	return 0;

		visited[r][c]=true;
		count++;

		count+=flood2(grid, visited, r, c+1, target1, target2);
		count+=flood2(grid, visited, r, c-1, target1, target2);
		count+=flood2(grid, visited, r+1, c, target1, target2);
		count+=flood2(grid, visited, r-1, c, target1, target2);

		return count;

	}
	
	//extra
	public static TreeSet<Integer> getNeighbors(int[][] grid, boolean[][] visited, int r, int c, int target) {

		TreeSet<Integer> set = new TreeSet();

		if(r<0 || r>=grid.length || c<0 || c>=grid.length)	return set;
		
		if(grid[r][c]!=target) {
			set.add(grid[r][c]);
			return set;
		}
		if(visited[r][c])	return set;

		visited[r][c]=true;

		set.addAll(getNeighbors(grid, visited, r, c+1, target));
		set.addAll(getNeighbors(grid, visited, r, c-1, target));
		set.addAll(getNeighbors(grid, visited, r+1, c, target));
		set.addAll(getNeighbors(grid, visited, r-1, c, target));

		return set;

	}

}
