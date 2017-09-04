import java.util.*;
import java.lang.*;

class PathInMatrix {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			int[][] array = new int[size+1][size+2];
			int result = Integer.MIN_VALUE;
			for (int i = 1; i <= size; i++) {
				for (int j = 1; j <= size; j++) {array[i][j] = input.nextInt();}
			}
			for (int i = size-1; i > 0; i--) {
				for (int j = size; j > 0; j--) {
					array[i][j] += Math.max(Math.max(array[i+1][j], array[i+1][j+1]), array[i+1][j-1]);
					if (array[i][j] > result) {result = array[i][j];}
				}
			}
			resultArr.add(result);
		}
		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}