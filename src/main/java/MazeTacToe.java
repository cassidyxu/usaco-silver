import java.util.*;


public class MazeTacToe {

	static int N;
	static int[][] maze;
	static int cowRowPos, cowColPos;
	static int[][] stepChoices = {{-1, 0 /*north*/},{1, 0 /*south*/}, {0, -1 /*east*/}, {0, 1/*west*/}};
	static int[][] steps = new int[4][2];
	static Set<String> winnerTicTacToes = new HashSet();
	//	static int[][] steps = {{1, 0 /*south*/}, {-1, 0 /*north*/}, {0, -1 /*east*/}, {0, 1/*west*/}};
	static Map<Integer, String> moves = new HashMap<Integer, String>();
	static char NOTSET = Character.MIN_VALUE;
	static List<MatchRule> mMatchRules = new ArrayList<MatchRule>();
	static List<MatchRule> oMatchRules = new ArrayList<MatchRule>();
	static Map<Character, List<MatchRule>> matchRules = new HashMap<Character, List<MatchRule>>();
	static int wins = 0;
	
	private static void setupMatchRules() {
		mMatchRules.add(new MatchRule(-1, 0, 'O', -2, 0, 'O'));
		mMatchRules.add(new MatchRule(1, 0, 'O', 2, 0, 'O'));
		mMatchRules.add(new MatchRule(0, -1, 'O', 0, -2, 'O'));
		mMatchRules.add(new MatchRule(0, 1, 'O', 0, 2, 'O'));
		mMatchRules.add(new MatchRule(-1, -1, 'O', -2, -2, 'O'));
		mMatchRules.add(new MatchRule(1, 1, 'O', 2, 2, 'O'));
		mMatchRules.add(new MatchRule(-1, 1, 'O', -2, 2, 'O'));
		mMatchRules.add(new MatchRule(-1, 1, 'O', -2, 2, 'O'));
		mMatchRules.add(new MatchRule(1, -1, 'O', 2, -2, 'O'));
		matchRules.put('M', mMatchRules);
		
		// O is at the end
		oMatchRules.add(new MatchRule(0, 1, 'O', 0, 2, 'M'));
		oMatchRules.add(new MatchRule(0, -1, 'O', 0, -2, 'M'));
		oMatchRules.add(new MatchRule(-1, 0, 'O', -2, 0, 'M'));
		oMatchRules.add(new MatchRule(1, 0, 'O', 2, 0, 'M'));
		oMatchRules.add(new MatchRule(-1, -1, 'O', -2, -2, 'M'));
		oMatchRules.add(new MatchRule(1, 1, 'O', 2, 2, 'M'));
		oMatchRules.add(new MatchRule(1, -1, 'O', 2, -2, 'M'));
		oMatchRules.add(new MatchRule(-1, 1, 'O', -2, 2, 'M'));
		
		// O is in the middle
		oMatchRules.add(new MatchRule(0, -1, 'O', 0, 1, 'M'));
		oMatchRules.add(new MatchRule(0, -1, 'M', 0, 1, 'O'));
		oMatchRules.add(new MatchRule(-1, 0, 'M', 1, 0, 'O'));
		oMatchRules.add(new MatchRule(-1, 0, 'O', 1, 0, 'M'));
		oMatchRules.add(new MatchRule(-1, -1, 'O', 1, 1, 'M'));
		oMatchRules.add(new MatchRule(-1, -1, 'M', 1, 1, 'O'));
		oMatchRules.add(new MatchRule(1, -1, 'M', -1, 1, 'O'));
		oMatchRules.add(new MatchRule(1, -1, 'O', -1, 1, 'M'));
		matchRules.put('O', oMatchRules);

	}

	public static int[][] getNewStepChoices(int excludeStepIdx) {
		int[][] newSteps = new int[3][2];
		int newIdx = 0;
		for (int i=0; i<4; i++) {
			if (i != excludeStepIdx) {
				newSteps[newIdx] = stepChoices[i];
				newIdx++;
			}
		}
		return newSteps;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		maze = new int[N][N];
		moves = new HashMap<Integer, String>();
		int moveIdx = 1;
		for (int rowIdx=0; rowIdx<N; rowIdx++) {
			String line = scanner.next();
			int colIdx = 0;
			for (int strIdx=0; strIdx<N*3; strIdx+=3) {
				String part = line.substring(strIdx, strIdx + 3);
				if ("###".equals(part)) {
					maze[rowIdx][colIdx] = -1;
				} else if ("BBB".equals(part)) {
					cowRowPos = rowIdx;
					cowColPos = colIdx;
				} else if (!"...".equals(part)){
					maze[rowIdx][colIdx] = moveIdx;
					moves.put(moveIdx, part);
					moveIdx++;
				}
				colIdx++;
			}
		}
		
		setupMatchRules();
//		printMazeWithCowPos(cowRowPos, cowColPos);
//		char[][] ticTacToe = new char[4][4];
//		play(cowRowPos,  cowColPos, Integer.MIN_VALUE, Integer.MIN_VALUE, false, ticTacToe);

		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					if ((i != j) && (i != k) && (j != k)) {
						steps[0] = stepChoices[i];
						steps[1] = stepChoices[j];
						steps[2] = stepChoices[k];
						Set<Integer> choices = new HashSet(Arrays.asList(0, 1, 2, 3));
						choices.remove(i);
						choices.remove(j);
						choices.remove(k);
						steps[3] = stepChoices[choices.iterator().next()];
						char[][] ticTacToe = new char[4][4];
						play(cowRowPos,  cowColPos, Integer.MIN_VALUE, Integer.MIN_VALUE, false, ticTacToe);
					}
				}
			}
		}
//		for (String working : winnerTicTacToes) {
//			System.out.println(working);
//		}
		System.out.println(winnerTicTacToes.size()*2);
		
		scanner.close();

	}
	
	private static void setupStepsVariations() {

	}
	
	private static void play(int cowRowPos, int cowColPos, int prevCowRowPos, int prevCowColPos, boolean ticTacToeChanged, char[][] ticTacToe) {

//		int skipNeighbor = -1;
//		for (int stepIdx=0; stepIdx<4; stepIdx++) {
//			int newRowIdx = cowRowPos + steps[stepIdx][0];
//			int newColIdx = cowColPos + steps[stepIdx][1];
//			if (newRowIdx == prevCowRowPos && newColIdx == prevCowColPos) {
//				skipNeighbor = stepIdx;
//			}
//		}
//		
//		int[][] newStepChoices = stepChoices;
//		int limit = 4;
//		if (skipNeighbor != -1) {
//			newStepChoices = getNewStepChoices(skipNeighbor);
//			limit = 3;
//		}
//		
//		int[][][] combinations = new int[24][limit][2];
//		int numCombinations = 0;
//		for (int i=0; i<limit; i++) {
//			for (int j=0; j<limit; j++) {
//					if ((i != j)) {
//						int[][] newSteps = new int[limit][2];
//						newSteps[0] = newStepChoices[i];
//						newSteps[1] = newStepChoices[j];
//						if (limit == 3) {
//							Set<Integer> choices = new HashSet<>(Arrays.asList(0, 1, 2));
//							choices.remove(i);
//							choices.remove(j);
//							newSteps[2] = newStepChoices[choices.iterator().next()];
//							combinations[numCombinations] = newSteps;
//							numCombinations++;
//						} else {
//							for (int k=0; k<limit; k++) {
//
//								if (k != i && k!= j) {
//									Set<Integer> choices = new HashSet<>(Arrays.asList(0, 1, 2, 3));
//									choices.remove(i);
//									choices.remove(j);
//									choices.remove(k);
//									newSteps[2] = newStepChoices[k];
//									newSteps[3] = newStepChoices[choices.iterator().next()];
//									combinations[numCombinations] = newSteps;
//									numCombinations++;
//
//								}
//							}
//						}
//
//					}
//				}
//			}
//		for (int combIdx=0; combIdx < numCombinations; combIdx++) {
//			int[][] newSteps = combinations[combIdx];
		for (int stepIdx=0; stepIdx<4; stepIdx++) {
			int newRowIdx = cowRowPos + steps[stepIdx][0];
			int newColIdx = cowColPos + steps[stepIdx][1];
			if (newRowIdx < 0 || newRowIdx >= N ||
				newColIdx < 0 || newColIdx >= N) {
				continue;
			}
				
			if (newRowIdx == prevCowRowPos && newColIdx == prevCowColPos) {
				continue;
			}

			if (maze[newRowIdx][newColIdx] == -1) {
				continue;
			} 

			char letter = NOTSET;
			int rtttIdx=-1;
			int ctttIdx=-1;
			
//			printMazeWithCowPos(newRowIdx, newColIdx);

			if (maze[newRowIdx][newColIdx] == 0) {
				play(newRowIdx, newColIdx, cowRowPos, cowColPos, false, ticTacToe);
			} else {
				String move = moves.get(maze[newRowIdx][newColIdx]);
				letter = move.charAt(0);
				rtttIdx = Character.getNumericValue(move.charAt(1));
				ctttIdx = Character.getNumericValue(move.charAt(2));
				if (ticTacToe[rtttIdx][ctttIdx] == NOTSET) {
					ticTacToe[rtttIdx][ctttIdx] = letter;
//					printTicTacToe(ticTacToe);
					boolean found = findMOO(letter, rtttIdx, ctttIdx, ticTacToe);
					if (found) {
//						printTicTacToe(ticTacToe);
						winnerTicTacToes.add(convertTicTacToeToString(ticTacToe));
						ticTacToe[rtttIdx][ctttIdx] = NOTSET;
//						wins++;
					} else {
						play(newRowIdx, newColIdx, cowRowPos, cowColPos, true, ticTacToe);
					}
					
				} else {
					letter = NOTSET;
				}
			}

//			printMazeWithCowPos();
		}

//		}
	}
	
	private static void printTicTacToe(char[][] ticTacToe) {
		for (int i=0; i<4; i++) {
			System.out.println(Arrays.toString(ticTacToe[i]));
		}
		System.out.println();
	}
	
	private static String convertTicTacToeToString(char[][] ticTacToe) {
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<4; i++) {
			builder.append(Arrays.toString(ticTacToe[i]));
		}
		return builder.toString();
	}
	
	private static char[][] copyTicTacToe(char[][] src) {
		char[][] dest = new char[4][4];
		for (int i=0; i<4; i++) {
			System.arraycopy(src[i], 0, dest[i], 0, 4);
		}
		return dest;
	}
	
	private static void printMazeWithCowPos(int cowRowPos, int cowColPos) {
		int tmp = maze[cowRowPos][cowColPos];
		maze[cowRowPos][cowColPos] = '*';
		for (int i=0; i<N; i++) {
			System.out.println(Arrays.toString(maze[i]));
		}
		System.out.println();
		maze[cowRowPos][cowColPos] = tmp;
	}
	
	private static boolean findMOO(char letter, int rowIdx, int colIdx, char[][] ticTacToe) {
		
		List<MatchRule> rules = matchRules.get(letter);
		for (MatchRule rule : rules) {
			if (rule.match(rowIdx, colIdx, ticTacToe)) {
				return true;
			}
		}
		return false;
	}
	
	static class MatchRule {
		int rowIdx1, rowIdx2, colIdx1, colIdx2;
		char letter1, letter2;
		
		public MatchRule(int rowIdx1, int colIdx1, char letter1, int rowIdx2, int colIdx2, char letter2) {
			this.rowIdx1 = rowIdx1;
			this.colIdx1 = colIdx1;
			this.letter1 = letter1;
			this.rowIdx2 = rowIdx2;
			this.colIdx2 = colIdx2;
			this.letter2 = letter2;	
		}
		
		public boolean match(int rowIdx, int colIdx, char[][] ticTacToe) {
			if (rowIdx+rowIdx1 >= 4 || rowIdx+rowIdx1 <= 0 ||
				rowIdx+rowIdx2 >= 4 || rowIdx+rowIdx2 <= 0 ||
				colIdx+colIdx1 >= 4 || colIdx+colIdx1 <= 0 ||
				colIdx+colIdx2 >= 4 || colIdx+colIdx2 <= 0)  {
				return false;
			}
			return ticTacToe[rowIdx + rowIdx1][colIdx + colIdx1] == letter1 &&
				ticTacToe[rowIdx + rowIdx2][colIdx + colIdx2] == letter2;
		}
	}

}
