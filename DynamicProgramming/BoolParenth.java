import java.util.*;

class BoolParenth {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			String string = input.next();
			int charCount = size/2+1;
			int opCount = size/2;
			char[] chars = new char[charCount];
			char[] ops = new char[opCount];
			for (int i = 0, j = 0, m = 0; i < size; i++) {
				if (string.charAt(i) == 'T' || string.charAt(i) == 'F') {chars[j++] = string.charAt(i);}
				else {ops[m++] = string.charAt(i);}
			}
			int[][] trueResult = new int[charCount][charCount];
			int[][] falseResult = new int[charCount][charCount];
			for (int i = 0; i < charCount; i++) {
				if (chars[i] == 'T') {
					trueResult[i][i] = 1;
					falseResult[i][i] = 0;
				} else {
					trueResult[i][i] = 0;
					falseResult[i][i] = 1;
				}
			}
			for (int i = 0; i < charCount; i++) {
				for (int j = 0; j < charCount; j++) {
					if (i != j) {
						for (int m = 0; m < j; m++) {
							if (ops[m] == '&') {
								trueResult[i][j] = trueResult[i][m]*trueResult[m+1][j];
								int ktal = trueResult[i][m]+falseResult[i][m];
								int k1tal = trueResult[m+1][j]+falseResult[m+1][j];
								falseResult[i][j] = (ktal*k1tal) - (trueResult[i][m]*trueResult[m+1][j]);
							} else if (ops[m] == '|') {
								int ktal = trueResult[i][m]+falseResult[i][m];
								int k1tal = trueResult[m+1][j]+falseResult[m+1][j];
								trueResult[i][j] = (ktal*k1tal)-(falseResult[i][m]*falseResult[m+1][j]);
								falseResult[i][j] = falseResult[i][m]*falseResult[m+1][j];
							} else if (ops[m] == '^') {
								trueResult[i][j] = trueResult[i][m]*falseResult[m+1][j] + falseResult[i][m]*trueResult[m+1][j];
								falseResult[i][j] = trueResult[i][m]*trueResult[m+1][j] + falseResult[i][m]*falseResult[m+1][j];
							}
						}
					}
				}
			}
			for (int i = 0; i < charCount; i++) {
				for (int j = 0; j < charCount; j++) {
					System.out.print(trueResult[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			for (int i = 0; i < charCount; i++) {
				for (int j = 0; j < charCount; j++) {
					System.out.print(falseResult[i][j] + " ");
				}
				System.out.println();
			}
			resultArr.add(trueResult[0][charCount-1]);
		}
		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}