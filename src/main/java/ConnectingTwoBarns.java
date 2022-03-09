import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ConnectingTwoBarns {

	static Map<Integer, List<Integer>> paths = new HashMap<>();
	static List<Set<Integer>> middleConnectedSets = new ArrayList<>();
	static Set<Integer> connectedSetFor1 =  null;
	static Set<Integer> connectedSetForN = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);

		int T = in.nextInt();
		for (int i=0; i<T; i++) {
			int N = in.nextInt();
			int M = in.nextInt();
			
			paths.put(1, new ArrayList<>());
			paths.put(N, new ArrayList<>());
			for (int y=0; y<M; y++) {
				int from = in.nextInt();
				int to = in.nextInt();
				
				addPath(from, to);
				addPath(to, from);
			}
			
			for (Map.Entry<Integer, List<Integer>> entry : paths.entrySet()) {
				if (isAlreadyConnected(entry.getKey()))
					continue;
				Set<Integer> connected = new HashSet<>();
				connected.add(entry.getKey());
				Deque<Integer> toProcess = new ArrayDeque<>();
				toProcess.addAll(entry.getValue());
				while (toProcess.size() != 0) {
					int field = toProcess.remove();
					if (connected.contains(field)) {
						continue;
					}
					connected.add(field);
					if (paths.containsKey(field)) {
						paths.get(field).removeAll(connected);
						toProcess.addAll(paths.get(field));
					}
				}
				boolean contains1 = connected.contains(1);
				boolean containsN = connected.contains(N);
				if (contains1 && containsN) {
					System.out.println("0");
					paths.clear();
					middleConnectedSets.clear();
					break;
				} else if (contains1) {
					connectedSetFor1 = connected;
				} else if (containsN) {
					connectedSetForN = connected;
				} else {
					middleConnectedSets.add(connected);
				}
			}
			

			long minDist = Long.MAX_VALUE;
			int dist = findMinDist(connectedSetFor1, connectedSetForN);
			minDist = dist;
			if (middleConnectedSets.size() > 0) {
				for (Set<Integer> connectedSet : middleConnectedSets) {
					int distTo1 = findMinDist(connectedSet, connectedSetFor1);
					int distToN = findMinDist(connectedSet, connectedSetForN);
					minDist = Math.min(minDist, distTo1 + distToN);
				}
			}
			
			long cost = 0;
			long divBy2 = minDist/2;
			if (minDist%2 == 0) {
				cost = divBy2*divBy2*2;
			} else {
				cost = divBy2*divBy2 + (divBy2+1)*(divBy2+1);
			}
			
			System.out.println(Long.toString(cost));
			
			paths.clear();
			middleConnectedSets.clear();
			connectedSetFor1 = null;
			connectedSetForN = null;
			
		}
		
		in.close();
	}
	
	private static int findMinDist(Set<Integer> set1, Set<Integer> set2) {
		int minDist = Integer.MAX_VALUE;
		for (Integer field1 : set1) {
			for (Integer field2: set2) {
				int dist = Math.abs(field1 - field2);
				minDist = Math.min(minDist, dist);
			}
		}
		return minDist;
	}
	
	private static void addPath(int from, int to) {
		if (paths.containsKey(from)) {
			paths.get(from).add(to);
		} else {
			List<Integer> tos = new ArrayList<>();
			tos.add(to);
			paths.put(from, tos);
		}
	}
	
	private static boolean isAlreadyConnected(int field) {
		if (connectedSetFor1 != null && connectedSetFor1.contains(field)) {
			return true;
		}
		if (connectedSetForN != null && connectedSetForN.contains(field)) {
			return true;
		}
		for (Set<Integer> connectedSet : middleConnectedSets) {
			if (connectedSet.contains(field)) {
				return true;
			}
		}
		return false;
	}

}
