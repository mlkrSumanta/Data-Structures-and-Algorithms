import java.util.*;

class Knapsack01 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			int maxWeight = input.nextInt();
			int[] value = new int[size];
			int[] weight = new int[size];
			int[][] knapsack = new int[size+1][maxWeight+1];

			for (int i = 0; i < size; i++) {value[i] = input.nextInt();}
			for (int i = 0; i < size; i++) {weight[i] = input.nextInt();}			
			
			for (int i = 0; i <= size; i++) {
				for (int j = 0; j <= maxWeight; j++) {
					if (i == 0 || j == 0) {knapsack[i][j] = 0;}
					else if (weight[i-1] <= j) {knapsack[i][j] = Math.max(value[i-1] + knapsack[i-1][j-weight[i-1]], knapsack[i-1][j]);}
					else {knapsack[i][j] = knapsack[i-1][j];}
				}
			}

			resultArr.add(knapsack[size][maxWeight]);
		}
		for (int i = 0; i < n; i++) {System.out.println(resultArr.get(i));}
	}
}