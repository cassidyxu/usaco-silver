// 9/12/21

import java.io.*;
import java.util.*;

public class MaxSpan {

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N+1];
		for(int j=1; j<=N; j++) {
			A[j] = Integer.parseInt(br.readLine());
		}
		
		
		//ID1[v]: the first location v(value) appears in A
		//ID1[v]: the last location v(value) appears in A
		int[] ID1 = new int[1000001]; 
		int[] ID2 = new int[1000001];
		
		for(int j=1; j<=N; j++) {
			int v = A[j];
			if(ID1[v]==0) {	//haven't seen v yet
				ID1[v]=j;
			}else {
				ID2[v]=j;
			}
		}
		
		//find max span of any v
		int maxSpan = 1;
		for(int v=0; v<=1000000; v++) {
			maxSpan = Math.max(maxSpan,  ID2[v]-ID1[v]+1);
		}
		System.out.println(maxSpan);
		br.close();
		
	}

}
