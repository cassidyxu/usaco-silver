import java.util.*;

public class DoYouKnowYourABCs {

	static final int MISSING = Integer.MIN_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
	
		setupRulesToFillInMissingNumber();
		
		int T = scanner.nextInt();
		for (int i=0; i<T; i++) {
			int N = scanner.nextInt();
			int[] numbers = new int[N];
			for (int y=0; y<N; y++) {
				numbers[y] = scanner.nextInt();
			}
			Arrays.sort(numbers);
			if (N == 7) {
				int[][] choices = new int[2][7];
				choices[0] = numbers;

				System.arraycopy(choices[0], 0, choices[1], 0, 7);
				choices[1][2] = choices[0][3];
				choices[1][3] = choices[0][2];

				if (validateChoice(choices[0]) ||
					validateChoice(choices[1])) {
					System.out.println(1);
				} 
				else {
					System.out.println(0);
				}
						
			}
			if (N == 6) {
				int[][] choices = new int[14][7];
				int rowIdx = 0;
				for (int y=0; y<7; y++) {
					choices[rowIdx][y] = MISSING;
					int copyIdx = 0;
					for (int n=0; n<N; n++) {
						if (choices[rowIdx][copyIdx] == MISSING) {
							copyIdx++;
						}
						choices[rowIdx][copyIdx] = numbers[n];
						copyIdx++;
					}
					if (choices[rowIdx][2] != MISSING && choices[rowIdx][3] != MISSING) {
						System.arraycopy(choices[rowIdx], 0, choices[rowIdx+1], 0, 7);
						choices[rowIdx+1][2] = choices[rowIdx][3];
						choices[rowIdx+1][3] = choices[rowIdx][2];
						rowIdx += 2;
					} else {
						rowIdx++;
					}
				}
				System.out.println(fillAllMissingNumbers(choices, rowIdx));
			}
			if (N == 5) {
				int[][] choices = new int[42][7];
				int rowIdx=0;
				for (int y=0; y<7; y++) {
					for (int z=y+1; z<7; z++) {
						choices[rowIdx][y] = MISSING;
						choices[rowIdx][z] = MISSING;
						int copyIdx = 0;
						for (int n=0; n<N; n++) {
							while (choices[rowIdx][copyIdx] == MISSING) {
								copyIdx++;
							}
							choices[rowIdx][copyIdx] = numbers[n];
							copyIdx++;
						}
						if (choices[rowIdx][2] != MISSING && choices[rowIdx][3] != MISSING) {
							System.arraycopy(choices[rowIdx], 0, choices[rowIdx+1], 0, 7);
							choices[rowIdx+1][2] = choices[rowIdx][3];
							choices[rowIdx+1][3] = choices[rowIdx][2];
							rowIdx += 2;
						} else {
							rowIdx++;
						}
					}
				}
				System.out.println(fillAllMissingNumbers(choices, rowIdx));
			}
			
			if (N == 4) {
				int[][] choices = new int[70][7];
				int rowIdx=0;
				for (int y=0; y<7; y++) {
					for (int z=y+1; z<7; z++) {
						for (int w=z+1; w<7; w++) {
							choices[rowIdx][y] = MISSING;
							choices[rowIdx][z] = MISSING;
							choices[rowIdx][w] = MISSING;
							int copyIdx = 0;
							for (int n=0; n<N; n++) {
								while (choices[rowIdx][copyIdx] == MISSING) {
									copyIdx++;
								}
								choices[rowIdx][copyIdx] = numbers[n];
								copyIdx++;
							}
							if (choices[rowIdx][2] != MISSING && choices[rowIdx][3] != MISSING) {
								System.arraycopy(choices[rowIdx], 0, choices[rowIdx+1], 0, 7);
								choices[rowIdx+1][2] = choices[rowIdx][3];
								choices[rowIdx+1][3] = choices[rowIdx][2];
								rowIdx += 2;
							} else {
								rowIdx++;
							}
						}
					}
				}
				System.out.println(fillAllMissingNumbers(choices, rowIdx));
			}
		}
		scanner.close();
	}

	static private Map<Integer, List<String>> rules = new HashMap<Integer, List<String>>();
	private static void setupRulesToFillInMissingNumber() {
		List<String> fillRule = new ArrayList<String>();
		
		// Rule to fill in missing A+B+C
		fillRule.add("01+2+"); // A + B + C
		fillRule.add("05+");	// A + (B + C)
		fillRule.add("14+");	// B + (A + C)
		fillRule.add("23+");	// C + (A + B) or (A + B) + C
		rules.put(6, fillRule);
				
		// Rule to fill in missing B + C
		fillRule = new ArrayList<String>();
		fillRule.add("12+"); // B+C
		fillRule.add("60-");	// (A+B+C)-A
		fillRule.add("34+0-0-");	// (A+B)+(B+C)-A-A
		rules.put(5, fillRule);
		
		// Rule to fill in missing A + C
		fillRule = new ArrayList<String>();
		fillRule.add("02+"); // A+C
		fillRule.add("61-");	// (A+B+C)-B
		fillRule.add("35+1-1-");	// (A+B)+(B+C)-B-B
		rules.put(4, fillRule);
		
		// Rule to fill in missing A + B
		fillRule = new ArrayList<String>();
		fillRule.add("01+"); // A+B
		fillRule.add("62-");	// (A+B+C)-C
		fillRule.add("45+2-2-");	// (A+C)+(B+C)-C-C
		rules.put(3, fillRule);
		
		// Rule to fill in missing C
		fillRule = new ArrayList<String>();
		fillRule.add("40-"); // (A+C)-A
		fillRule.add("51-");	// (B+C)-B
		fillRule.add("63-");	// (A+B+C)-(A+B)
		rules.put(2, fillRule);

		// Rule to fill in missing B
		fillRule = new ArrayList<String>();
		fillRule.add("30-"); // (A+B)-A
		fillRule.add("52-");	// (B+C)-C
		fillRule.add("64-");	// (A+B+C)-(A+C)
		rules.put(1, fillRule);

		// Rule to fill in missing A
		fillRule = new ArrayList<String>();
		fillRule.add("31-"); // (A+B)-B
		fillRule.add("42-");	// (A+C)-C
		fillRule.add("65-");	// (A+B+C)-(B+C)
		rules.put(0, fillRule);
		
	}
	
	private static int fillAllMissingNumbers(int[][] choices, int totalChoices) {
		Set<String> validChoices = new HashSet<String>();
		for (int i=0; i<totalChoices; i++) {
//			System.out.println(Arrays.toString(choices[i]));
			do {
				for (int j=0; j<7; j++) {
					if (choices[i][j] == MISSING) {
						fillOneMissingNumber(choices[i], j);
					}
				}
			} while (hasMissingNumber(choices[i]));
			if (validateChoice(choices[i])) {
//				System.out.println(Arrays.toString(choices[i]));
				Arrays.sort(choices[i]);
//				StringBuilder strBuilder = new StringBuilder();
//				strBuilder.append(choices[i][0]);
//				strBuilder.append(",");
//				strBuilder.append(choices[i][1]);
//				strBuilder.append(",");
//				strBuilder.append(choices[i][2]);
//				validChoices.add(strBuilder.toString());
				validChoices.add(Arrays.toString(choices[i]));
			}
		}
//		for (String validChoice : validChoices) {
//			System.out.println(validChoice);
//		}
		return validChoices.size();
	}

	private static boolean validateChoice(int[] choice) {
		int A = choice[0];
		int B = choice[1];
		int C = choice[2];
		int APlusB = choice[3];
		int APlusC = choice[4];
		int BPlusC = choice[5];
		int APlusBPlusC = choice[6];
		
		return A+B == APlusB && A+C == APlusC && B+C==BPlusC && A+B+C == APlusBPlusC;
	}
	
	private static boolean hasMissingNumber(int[] choice) {
		for (int i=0; i<choice.length; i++) {
			if (choice[i] == MISSING) {
				return true;
			}
		}
		return false;
	}
	
	private static void fillOneMissingNumber(int[] choice, int missingNumberIndex) {
		List<String> fillRules = rules.get(missingNumberIndex);
		for (String rule : fillRules) {
			Stack<Integer> stack = new Stack<Integer>();
			boolean valid = true;
			for (int i = 0;  i < rule.length(); i++){
			    char c = rule.charAt(i);        
			    if (c == '+' || c=='-') {
			    	int number1 = stack.pop();
			    	int number2 = stack.pop();
			    	if (c == '+') {
			    		stack.push(number1 + number2);
			    	} else if (c == '-') {
			    		stack.push(number2 - number1);
			    	} 
			    } else {
			    	int choiceIndex = Character.getNumericValue(c);
			    	if (choice[choiceIndex] == MISSING) {
			    		valid = false;
			    		break;
			    	} else {
			    		stack.push(choice[choiceIndex]);
			    	}
			    }
			}
			if (valid) {
				choice[missingNumberIndex] = stack.pop();
				break;
			}	
		}
	}
	
    
}
