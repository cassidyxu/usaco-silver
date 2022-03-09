import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClosestCow {
	
	static Map<Integer, Integer> patches = new HashMap<>();
	static int[] patchPositions;
	static int[] NhojCows; 

	private static class Range implements Comparable<Range> {
		long tasteTotalWithOneCow;
		long tasteTotalWithTwoCows;
		double left;
		boolean leftInclusive;
		double right;
		boolean rightInclusive;
		public Range(double left, boolean leftInclusive, double right, boolean rightInclusive, 
				long tasteTotalWithOneCow, long tasteTotalWithTwoCows) {
			this.left = left;
			this.leftInclusive = leftInclusive;
			this.right = right;
			this.rightInclusive = rightInclusive;
			this.tasteTotalWithOneCow = tasteTotalWithOneCow;
			this.tasteTotalWithTwoCows = tasteTotalWithTwoCows;
		}
		@Override
		public int compareTo(Range o) {
			if (o.tasteTotalWithOneCow == this.tasteTotalWithOneCow) {
				return 0;
			} else if (o.tasteTotalWithOneCow > this.tasteTotalWithOneCow) {
				return 1;
			} else
				return -1;
		}
	}
	
	static List<Range> ranges = new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);

		int K = in.nextInt();
		int M = in.nextInt();
		int N = in.nextInt();
		
		patchPositions = new int[K];
		for (int i=0; i<K; i++) {
			int pos = in.nextInt();
			patches.put(pos, in.nextInt());
			patchPositions[i] = pos;
		}
		Arrays.sort(patchPositions);
		
		NhojCows = new int[M];
		for (int i=0; i<M; i++) {
			NhojCows[i] = in.nextInt();
		}
		Arrays.sort(NhojCows);
		
		in.close();
		
		int leftPos = 0;
		boolean leftInclusive = true;
		int beginSearchIdx = 0;
		for (int i=0; i<M; i++) {
			if (NhojCows[i] == leftPos) {
				continue;
			} 
			
			beginSearchIdx = generateRange(beginSearchIdx, leftPos, leftInclusive, NhojCows[i], false);
			
			leftPos = NhojCows[i];
			leftInclusive = false;
		}
		
		if (NhojCows[M-1] != 200000) {
			generateRange(beginSearchIdx, NhojCows[M-1], false, 200000, true);
		}
		
		Collections.sort(ranges);
		
		long tasteTotal = 0;
		while (N > 0) {
			if (N >=2 ) {
				int twoCowsRangeIdx = findHighestTwoCowsRange();
				if (twoCowsRangeIdx != -1) { 
					long firstTwoOneCowTotalTaste = getFirstTwoOneCowTotalTaste();
					if (firstTwoOneCowTotalTaste < ranges.get(twoCowsRangeIdx).tasteTotalWithTwoCows) {
						tasteTotal += ranges.get(twoCowsRangeIdx).tasteTotalWithTwoCows;
						ranges.remove(twoCowsRangeIdx);
						N -=2;
					} else {
						if (ranges.size() == 0) {
							break;
						}
						tasteTotal += ranges.get(0).tasteTotalWithOneCow;
						ranges.remove(0);
						N--;
					}
				} else {
					if (ranges.size() == 0) {
						break;
					}
					tasteTotal += ranges.get(0).tasteTotalWithOneCow;
					ranges.remove(0);
					N--;					
				}
			} else {
				if (ranges.size() == 0) {
					break;
				}
				tasteTotal += ranges.get(0).tasteTotalWithOneCow;
				ranges.remove(0);
				N--;				
			}
		}
		
		System.out.println(Long.toString(tasteTotal));
	}
	
	private static int findHighestTwoCowsRange() {
		int idx = -1;
		long totalTasteWithTwoCows = 0;
		for (int i=0; i<ranges.size(); i++) {
			if (ranges.get(i).tasteTotalWithTwoCows > totalTasteWithTwoCows) {
				totalTasteWithTwoCows = ranges.get(i).tasteTotalWithTwoCows;
				idx = i;
			}
		}
		return idx;
	}
	
	private static int getFirstTwoOneCowTotalTaste() {
		int totalTaste = 0;
		int count = 0;
		for (Range range : ranges) {
			if (count == 2) {
				break;
			}
			if (range.tasteTotalWithTwoCows == 0) {
				count++;
				totalTaste += range.tasteTotalWithOneCow;
			}
		}
		return totalTaste;
	}
	
	private static int generateRange(int beginSearchIdx, int leftPos, boolean leftInclusive, int rightPos, boolean rightInclusive) {
		int leftPatchIdx = Arrays.binarySearch(patchPositions, beginSearchIdx, patchPositions.length, leftPos);
		if (leftPatchIdx >= 0) {
			if (!leftInclusive) {
				leftPatchIdx++;
			} 
		} else {
			leftPatchIdx = (leftPatchIdx + 1) * -1;
		}
		int rightPatchIdx = Arrays.binarySearch(patchPositions, leftPatchIdx == 0 ? leftPatchIdx : leftPatchIdx-1, patchPositions.length, rightPos);
		if (rightPatchIdx >=0) {
			if (!rightInclusive) {
				rightPatchIdx--;
			}
		} else {
			rightPatchIdx = (rightPatchIdx + 1) * -1 -1;
		}
		
		long tasteTotal = 0;
		
		List<Integer> patchPositionsInRange = new ArrayList<>();
		for (int i=leftPatchIdx; i<=rightPatchIdx; i++) {
			patchPositionsInRange.add(patchPositions[i]);
			tasteTotal += patches.get(patchPositions[i]);
		}
		long oneCowTasteTotal = 0;
		if (patchPositionsInRange.size() > 1 && leftInclusive == false && rightInclusive == false) {
			for (int i=0; i<patchPositionsInRange.size(); i++) {
				long maxTasteWithOneCow = 0;
				for (int y=0; y<patchPositionsInRange.size(); y++) {
					int distBetween = Math.abs(patchPositionsInRange.get(i)-patchPositionsInRange.get(y));
					int dist2Left = Math.abs(patchPositionsInRange.get(y)- leftPos);
					if (dist2Left <= distBetween) {
						continue;
					}
					int dist2Right = Math.abs(rightPos - patchPositionsInRange.get(y));
					if (dist2Right <= distBetween) {
						continue;
					}
					maxTasteWithOneCow+= patches.get(patchPositionsInRange.get(y));
				}
				oneCowTasteTotal = Math.max(oneCowTasteTotal, maxTasteWithOneCow);
				if (oneCowTasteTotal == tasteTotal) {
					break;
				}
			}
			if (oneCowTasteTotal < tasteTotal) {
				ranges.add(new Range(leftPos, leftInclusive, rightPos, false, oneCowTasteTotal, tasteTotal));
			} else {
				ranges.add(new Range(leftPos, leftInclusive, rightPos, false, oneCowTasteTotal, 0));
			}
			return rightPatchIdx;
		}
		if (tasteTotal > 0) {
			ranges.add(new Range(leftPos, leftInclusive, rightPos, false, tasteTotal, 0));
			return rightPatchIdx;
		}
		return rightPatchIdx;
	}

}
 