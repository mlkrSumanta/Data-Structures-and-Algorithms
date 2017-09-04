import java.util.*;
import java.lang.*;

class TomJerryGame {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		int number = 10000;
		ArrayList<Integer> result = new ArrayList<>();
		result.add(0);
		result.add(0);
		for (int i = 2; i <= number; i++) {
			result.add(0);
			for (int j = 1; j < i; j++) {
				if (i%j == 0 && result.get(i-j) == 0) {result.set(i, 1);}
			}
		}
		for (int k = 0; k < n; k++) {
			int tempNumber = input.nextInt();
			if (tempNumber > number) {
				for (int i = number+1; i <= tempNumber; i++) {
					result.add(0);
					for (int j = 1; j < i; j++) {
						if (i%j == 0 && result.get(i-j) == 0) {result.set(i, 1);}
					}
				}
				number = tempNumber;
			}
			resultArr.add(result.get(tempNumber));
		}
		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}