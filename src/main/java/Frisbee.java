import java.util.*;

public class Frisbee {

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		int[] heights = new int[n];
		ArrayList<Cow> cows = new ArrayList();
		for(int i=0; i<n; i++) {
			int height = in.nextInt();
			heights[i]=height;
			cows.add(new Cow(height, i));
		}
		
		Collections.sort(cows);
		
		System.out.println(recursion(-1, cows.size(), cows));
		
		

		
	}
	
	public static int recursion(int l, int r, ArrayList<Cow> cows) {
		
		int distance=0;
		
		if(r-l<=1) {
			return 0;
		}
		
		for(int i=0; i<cows.size(); i++) {
			int index = cows.get(i).index;
			if(index>l && index<r) {
				
				
				if(l<0 && cows.get(i).index == 0) {
					l=0;
				}else{
					distance+=index-l+1;
				}
				
				if(r==cows.size() && cows.get(i).index == cows.size()-1) {
					r=index;
				}else {
					distance+=r-index+1;
				}
				
				cows.remove(i);
				
				distance+=recursion(l,index, cows);
				distance+=recursion(index,r, cows);
				
				break;
			}
		}
		
		return distance;
		
	}
	
	static class Cow implements Comparable<Cow> {
		int height;
		int index;
		
		public Cow(int height, int index) {
			this.height = height;
			this.index = index;
		}
		
		public int compareTo(Cow other) {

			return other.height-this.height;
			
		}
		
	}

}
