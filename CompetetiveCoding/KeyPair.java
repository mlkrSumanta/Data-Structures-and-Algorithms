import java.util.*;

class KeyPair {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<String> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			int sum = input.nextInt();
			int[] array = new int[size];
			String result = "No";
			for (int i = 0; i < size; i++) {array[i] = input.nextInt();}
			Arrays.sort(array);
			int i = 0, j = size - 1;
			while (i < j) {
				if (array[i] + array[j] == sum) {result = "Yes"; break;}
				if (array[i] + array[j] > sum) {j--;}
				else if (array[i] + array[j] < sum) {i++;}
			}
			resultArr.add(result);
		}

		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}