import java.util.*;
import java.io.*;

public class polynomialCassidyXu {

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int d = in.nextInt();
		
		int[] p_coef = new int[n+1];
		int[] q_coef = new int[m+1];
		
		for(int i=0; i<n+1; i++) {
			p_coef[i]=in.nextInt();
		}
		for(int i=0; i<m+1; i++) {
			q_coef[i]=in.nextInt();
		}
		
		double a=7+Math.pow(10, 9);
		int count = 0;
		for(int i=0; i<=d; i++) {
			if(p(i,n,p_coef)%a>q(i,m,q_coef)%a) {
				count++;
			}
		}
		
		System.out.println(count);

	}
	
	public static int p(int x, int n, int[] p_coef) {
		int sum=0;
		
		for(int i=0; i<p_coef.length; i++) {
			sum+= p_coef[i]*Math.pow(x, n-i);
		}
		
		return sum;
	}
	
	public static int q(int x, int m, int[] q_coef) {
		int sum=0;
		
		for(int i=0; i<q_coef.length; i++) {
			sum+= q_coef[i]*Math.pow(x, m-i);
		}
		
		return sum;
	}

}
