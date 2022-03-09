//USACO 2012 US Open, Bronze Division
//Problem 2. Three Lines

import java.util.*;
import java.io.*;

public class ThreeLines {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("3lines.in"));

		int n = in.nextInt();

		Map<Integer, Set<Integer>> xKeysYVals = new HashMap();
		// keys: x-coordinates
		// values: sets of y-coordinates for cows at that x,y point
		Map<Integer, Set<Integer>> yKeysXVals = new HashMap();
		// keys: y-coordinates
		// values: sets of x-coordinates for cows at that x,y point

		// read inputs into data set "views"
		for (int i = 0; i < n; i++) {
			int x = in.nextInt();
			int y = in.nextInt();

			addToMap(xKeysYVals, x, y);
			addToMap(yKeysXVals, y, x);
		}

		in.close();

		int result = 0;

		// look for 3-horizontal-line and 3-vertical-line solutions
		if (xKeysYVals.size() <= 3 || yKeysXVals.size() <= 3) result = 1;

		// look for 1-horizontal-line solutions
		else if (check2by1(xKeysYVals, yKeysXVals)) result = 1;

		// look for 1-vertical-line solutions
		else if (check2by1(yKeysXVals, xKeysYVals)) result = 1;

		PrintWriter out = new PrintWriter(new File("3lines.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}

	// checks if ONE line from the "one" data structure can cover
	//   enough cows, such that those remaining can be covered
	//   by TWO lines from the "two" data structure
	// note that we don't care which is which, as long as keys
	//   from one are values in the other and vice versa
	// alternative: just write out this logic twice in the main method
	static boolean check2by1(Map<Integer, Set<Integer>> one,
			Map<Integer, Set<Integer>> two) {
		for (int oneKey : one.keySet()) {
			// try "oneKey" as one line in the solution
			Set<Integer> oneVals = one.get(oneKey);

			// that means those cows don't need to be covered by
			//   any lines from "two", so remove them
			for (int oneVal : oneVals) {

				// not necessary but a good reminder:
				int twoKey = oneVal;
				int twoVal = oneKey;

				removeFromMap(two, twoKey, twoVal);
			}

			// now see if "two" only has 2 lines left
			if (two.size() <= 2) {
				// printlns not necessary but nice for debugging!
				System.out.println("One line: " + oneKey);
				System.out.println("Two lines: " + two.keySet());

				return true;
			}

			// time to move on - put the removed cows back in
			for (int oneVal : oneVals) {
				addToMap(two, oneVal, oneKey);
				// add..(two, twoKey, twoVal);

				// the two versions of "add" would do the same thing
				// we'll just avoid the extra vars in this case
			}
		}

		// we tried all the lines from "one" but never found one where
		//   2 complementary lines to covered the remaining cows
		return false;
	}

	// this method "remembers" a pair of coordinates by adding valCoord
	//   to the Set corresponding to the given keyCoord, for instance adding
	//   a y value to the Set corresponding to a given x value, or vice versa
	static void addToMap(Map<Integer, Set<Integer>> map,
			int keyCoord, int valCoord) {
		Set<Integer> valSet = map.get(keyCoord);

		// if set does not exist for given key, must create it
		if (valSet == null) {
			valSet = new HashSet();
			map.put(keyCoord, valSet);
		}

		valSet.add(valCoord);
	}

	// removeFromMap works similarly
	static void removeFromMap(Map<Integer, Set<Integer>> map,
			int keyCoord, int valCoord) {
		// remove the value from the set corresponding the given key
		Set<Integer> valSet = map.get(keyCoord);

		// set should never be null if we follow our plan of only
		//   removing cows known to exist

		valSet.remove(valCoord);

		// if that set is then empty, REMOVE it
		// (if not empty, it can stay)
		if (valSet.isEmpty()) map.remove(keyCoord);
	}
}


