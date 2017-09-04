import java.util.*;

class MinDiffPartition {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			int sum = 0;
			int[] array = new int[size];
			for (int i = 0; i < size; i++) {array[i] = input.nextInt(); sum += array[i];}
			int halfSum = sum/2;
			boolean[][] result = new boolean[halfSum+1][size+1];
			for (int i = 0; i <= size; i++) {result[0][i] = true;}
			for (int i = 1; i <= halfSum; i++) {result[i][0] = false;}	
			for (int i = 1; i <= halfSum; i++) {
				for (int j = 1; j <= size; j++) {
					result[i][j] = result[i][j-1];
					if (i >= array[j-1]) {result[i][j] = result[i][j] || result[i-array[j-1]][j-1];}
				}
			}
			int i = halfSum;
			while (!result[i][size] && i > 0) {i--;}
			resultArr.add(sum-2*i);
		}
		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}