import java.util.*;

class MajorityElement {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<String> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			int[] array = new int[size];
			int maxCount = 0, count = 1, index = 0;
			for (int i = 0; i < size; i++) {array[i] = input.nextInt();}
			Arrays.sort(array);
			for (int i = 0; i < size-1; i++) {
				if (array[i] == array[i+1]) {
					count++;
					if (count > maxCount) {maxCount = count; index = i;}
				} else {count = 1;}
			}
			if (maxCount > size/2) {resultArr.add(String.valueOf(array[index]));}
			else {resultArr.add("NO Majority Element");}
		}

		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}