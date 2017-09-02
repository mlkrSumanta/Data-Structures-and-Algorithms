import java.util.*;
import java.lang.*;

class CoinPiles {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			int maxDiff = input.nextInt();
			int[] array = new int[size];
			int start = 0, temp = 0;
			int result = Integer.MAX_VALUE;
			for (int i = 0; i < size; i++) {array[i] = input.nextInt();}
			Arrays.sort(array);			
			for (int i = 0; i < size-1; i++) {
				temp = start;
				for (int j = i+1; j < size; j++) {
					if (array[j]-array[i] > maxDiff) {temp += array[j]-array[i]-maxDiff;}
				}
				result = Math.min(temp, result);
				start += array[i];
			}
			resultArr.add(result);
		}

		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}