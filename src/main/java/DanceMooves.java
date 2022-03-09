//USACO 2021 January Contest, Silver
//Problem 1. Dance Mooves

//not working

import java.util.*;

public class DanceMooves {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		
		int[] cows = new int[n];
		for(int i=0; i<n; i++) {
			cows[i]=i;
		}

		int[] froms = new int[k];
		int[] tos = new int[k];
		for (int i=0; i<k; i++) {
			froms[i] = in.nextInt();
			tos[i] = in.nextInt();
		}

		List<Set<Integer>>  reached = new ArrayList<Set<Integer>>();
		for (int i=0; i<n+1; i++) {
			Set<Integer> reach = new HashSet<Integer>();
			reach.add(i);
			reached.add(reach);
		}

		Set<String> rounds = new HashSet<String>();

		String round = Arrays.toString(cows);
		while (!rounds.contains(round)) {
			rounds.add(round);
			for (int i=0; i<k; i++) {
				reached.get(cows[froms[i]]).add(tos[i]);
				reached.get(cows[tos[i]]).add(froms[i]);
				int tmp = cows[froms[i]];
				cows[froms[i]] = cows[tos[i]];
				cows[tos[i]] = tmp;
			}
			round = Arrays.toString(cows);
		}

		for (int i=1; i<n+1; i++) {
			System.out.println(reached.get(i).size());
		}


	}

}
