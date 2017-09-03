import java.util.*;

class LongIncSubseq {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			int[] array = new int[size];
			int[] result = new int[size];
			int maxResult = Integer.MIN_VALUE;
			for (int i = 0; i < size; i++) {array[i] = input.nextInt();}
			if (size >= 1) {result[0] = 1; maxResult = 1;}
			if (size > 1) {
				if (array[1] > array[0]) {result[1] = 2; maxResult = 2;}
				else {result[1] = 1;}
				for (int i = 2; i < size; i++) {
					int max = Integer.MIN_VALUE;
					for (int j = 0; j < i; j++) {if (array[i] > array[j] && 1+result[j] > max) {max = 1+result[j];}}
					if (max < 0) {max = 1;}
					result[i] = max;
					if (result[i] > maxResult) {maxResult = result[i];}
				}
			}
			if (size >= 1) {resultArr.add(maxResult);}
			else {resultArr.add(0);}
		}
		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}