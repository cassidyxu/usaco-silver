import java.util.*;
import java.io.*;

public class Searching {

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] personalities = new int[2*n];
		
		for(int i=0; i<2*n; i++) {
			personalities[i]=in.nextInt();
		}
		
		for(int i=0; i<personalities.length-1; i+=2) {
			int a=personalities[i];
			int b=personalities[i+1];
			
			
			
		}
		
		
	}

}





/*


if a>b:
	if even, div by 2
	if odd, +1
	keep divdng until a <= b





*/




