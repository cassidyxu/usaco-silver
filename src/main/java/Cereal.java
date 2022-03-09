import java.util.*;

public class Cereal {

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		
		Cow[] cows = new Cow[n];

		for(int i=0; i<n; i++) {
			cows[i] = new Cow(in.nextInt(), in.nextInt());
		}
		
		Arrays.sort(cows);
		
		//int[] cereals = new int[m+1];
		ArrayList<HashSet<Cow>> cereals = new ArrayList(Collections.nCopies(m+1, new HashSet<Cow>()));
		
		ArrayList<HashSet<Integer>> systems_cereal = new ArrayList();
		ArrayList<HashSet<Integer>> systems_cows = new ArrayList();
		HashSet<Integer> first = new HashSet();
		first.add(cows[0].a);
		first.add(cows[0].b);
		systems_cereal.add(first);
//		cereals[cows[0].a]++;
//		cereals[cows[0].b]++;
		cereals.get(cows[0].a).add(cows[0]);
		System.out.println(cows[0].a);
		cereals.get(cows[0].b).add(cows[0]);
		HashSet<Integer> cow = new HashSet();
		cow.add(1);
		systems_cows.add(cow);
		
		
		
		for(int i=1; i<cows.length; i++) {
			
			int a = cows[i].a;
			int b = cows[i].b;
			
			boolean found = false;
			for(int j=0; j<systems_cereal.size(); j++) {
				if(systems_cereal.get(j).contains(a) || systems_cereal.get(j).contains(b)) {
					systems_cereal.get(j).add(a);
					systems_cereal.get(j).add(b);
					
					systems_cows.get(j).add(i+1);
					
//					cereals[a]++;
//					cereals[b]++;
					cereals.get(a).add(cows[i]);
					cereals.get(b).add(cows[i]);
					
					
					found = true;
					continue;
				}
			}
			
			if(!found) {
				HashSet<Integer> temp = new HashSet();
				temp.add(a);
				temp.add(b);
				systems_cereal.add(temp);
				
				HashSet<Integer> temp2 = new HashSet();
				temp2.add(i+1);
				systems_cows.add(temp2);
				
//				cereals[a]++;
//				cereals[b]++;
				cereals.get(a).add(cows[i]);
				cereals.get(b).add(cows[i]);
		
			}

					
		}
		
		int hungry = 0;
		for(int i=0; i<systems_cereal.size(); i++) {
			if(systems_cereal.get(i).size() < systems_cows.get(i).size()) {
				hungry+=systems_cows.get(i).size()-systems_cereal.get(i).size();
			}
		}
		
		System.out.println(hungry);
		
		int[] permutation = new int[n+1];
		
		HashSet<Integer> hungry_cows = new HashSet<>();
		
		for(int i=0; i<cows.length; i++) {
			int a = cows[i].a;
			int b = cows[i].b;
			
			if(permutation[a]!=0 && permutation[b]!=0) {
				hungry_cows.add(i+1);
			} else if (permutation[a]==0) {
				permutation[a]=i+1;
			} else if (permutation[b]==0) {
				permutation[b]=i+1;
			}
			
		}
		
		System.out.println();

	}

	static class Cow implements Comparable<Cow>{
		int a,b;

		public Cow(int a, int b) {
			this.a=a;
			this.b=b;
		}

		public int compareTo(Cow other) {

			if(this.a-other.a==0) {
				return this.b-other.b;
			}

			return this.a-other.a;

		}
	}

}
