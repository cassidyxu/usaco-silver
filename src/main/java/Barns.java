import java.util.*;
import java.io.*;

public class Barns {

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		
		while(in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			
			ArrayList<HashSet<Integer>> systems = new ArrayList();
			HashSet<Integer> first = new HashSet();
			first.add(in.nextInt());
			first.add(in.nextInt());
			systems.add(first);
			
			//creating the system
			for(int i=1; i<m; i++) {
				int a = in.nextInt();
				int b = in.nextInt();
				
				boolean found = false;
				for(int j=0; j<systems.size(); j++) {
					if(systems.get(j).contains(a) || systems.get(j).contains(b)) {
						systems.get(j).add(a);
						systems.get(j).add(b);
						
						found = true;
						continue;
					}
				}
				
				if(!found) {
					HashSet<Integer> temp = new HashSet();
					temp.add(a);
					temp.add(b);
					systems.add(temp);
			
				}

			}
			
			
			int minCost = Integer.MAX_VALUE;
			
			//assuming first system has 1 and last system has n
			if(systems.size()>2) {
				
				for(int i=1;i<systems.size()-1; i++) {
					
					int cost = closestDist(systems.get(0), systems.get(i)) * closestDist(systems.get(0), systems.get(i));
					cost += closestDist(systems.get(systems.size()-1), systems.get(i)) * closestDist(systems.get(systems.size()-1), systems.get(i));

					if(cost<minCost)	minCost=cost;
				}
				
			}else if(systems.size()==2){
				
				//either 1 connection direrctly
				//or 2 connects to midpoint between the min dist
				
				int cost = 0;
				int dist = closestDist(systems.get(0), systems.get(1));
				if(dist%2==0) {
					cost = (dist/2)*(dist/2) + (dist/2)*(dist/2);
				}else {
					cost = (dist/2)*(dist/2) + ((dist/2)+1)*((dist/2)+1);
				}
				
				minCost = cost;
				
			}
			
			System.out.println(minCost);
			
		}

	}
	
	public static int closestDist(HashSet<Integer> x, HashSet<Integer> y) {
//		HashSet<Integer> x = new HashSet(a);
//		HashSet<Integer> y = new HashSet(b);
//		a.clear();
//		a.addAll(x);
//		b.clear();
//		b.addAll(y);
		ArrayList<Integer> a = new ArrayList(x);
		ArrayList<Integer> b = new ArrayList(y);
		int minDist=Integer.MAX_VALUE;
		for(int i=0; i<a.size(); i++) {
			for(int j=0; j<b.size(); j++) {
				if(Math.abs(a.get(i)-b.get(j))<minDist)	minDist=Math.abs(a.get(i)-b.get(j));
			}
		}
		return minDist;
	}

}

/*

1
12 7
1 4
2 4
2 8
3 7
3 9
4 5
6 12

2


1
30 7
1 9
5 25
7 11
7 21
9 18
9 25
22 30

5


1
30 11
1 9
3 26
3 12
5 25
7 11
7 21
9 18
9 25
12 23
12 27
22 30

2


*/





