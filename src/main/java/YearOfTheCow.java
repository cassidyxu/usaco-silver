//USACO 2021 February Contest, Silver
//Problem 2. Year of the Cow

//NOT FINISHED!

import java.util.*;
import java.io.*;

public class YearOfTheCow {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);

		int n = in.nextInt();
		int k = in.nextInt();
		
		int[] years = new int[n];
		for(int i=0; i<n; i++){
			years[i]=in.nextInt();
		}
		
		in.close();
		
		TreeSet<Integer> set = new TreeSet();
		set.add(0);
		
		for(int i=0; i<n; i++) {
			int range = years[i]/12;
			if(!set.contains(range))	set.add(range);
		}
		
		int[] ranges = new int[set.size()];
		int index=0;
		
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()){
			ranges[index]=it.next();
			index++;
		}
		
		TreeMap<Integer, Integer> map = new TreeMap();
		//number of portal travels
		int portal = ranges.length;
		int result = (ranges.length-1)*12;
		
		for(int i=0; i<ranges.length-1; i++) {
			if(!map.containsKey(ranges[i+1]-ranges[i])){
				map.put(ranges[i+1]-ranges[i], 1);
			}else {
				map.replace(ranges[i+1]-ranges[i], map.get(ranges[i+1]-ranges[i])+1);
			}
		}
		
		Set<Integer> dist = map.keySet();
		
		
		while(portal>k) {
			Iterator<Integer> iter = dist.iterator();
			Integer min = iter.next();
			
			if(map.get(min)==1) {
				map.remove(min);
			}else {
				map.replace(min, map.get(min)-1);
			}
			
			if(ranges[1]==min) {
				result+=(min)*12;
			}else {
				result+=(min-1)*12;
			}
			
			
			
			portal--;
		}
		
		
		System.out.println(result);

	}

}

/*



divide each year by 12 to get ranges

0.........12..........24..........36..

0..........1...........2...........3

count how many integers there are, thats how many ranges have to be visited

8 3
101
85
100
46
95
187
197
217

3. 7 8 .15 16 18

4+2+4

.3 7 8 .15 16 18

4+6
*/




