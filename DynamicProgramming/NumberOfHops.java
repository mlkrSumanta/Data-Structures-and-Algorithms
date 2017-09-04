import java.util.*;

class NumberOfHops {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int distance = input.nextInt();
			int[] result = new int[4];
			if (distance > 3) {result = new int[distance+1];}
			result[0] = 0;
			result[1] = 1;
			result[2] = 2;
			result[3] = 4;
			for (int i = 4; i <= distance; i++) {result[i] = result[i-3]+result[i-2]+result[i-1];}
			resultArr.add(result[distance]);
		}
		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}