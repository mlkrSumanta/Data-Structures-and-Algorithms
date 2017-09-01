import java.util.*;

class CoinPiles {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			int maxDiff = input.nextInt();
			int[] array = new int[size];
			int start = 0, end = size-1;
			int result = 0;
			for (int i = 0; i < size; i++) {array[i] = input.nextInt();}
			Arrays.sort(array);			
			while (start < end) {
				if ((array[end]-array[start]) > maxDiff) {
					int nowDiff = array[end]-array[start]-maxDiff;
					if (nowDiff > array[start]) {
						result += array[start];
						start++;
					} else {
						result += array[end]-array[start]-maxDiff;
						end--;
					}
				} else {break;}
			}
			resultArr.add(result);
		}

		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}