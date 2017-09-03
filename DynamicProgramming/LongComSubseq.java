import java.util.*;
import java.lang.*;

class LongComSubseq {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int sizeA = input.nextInt();
			int sizeB = input.nextInt();
			String first = input.next();
			String second = input.next();
			int[][] array = new int[sizeA][sizeB];
			for (int i = 0; i < sizeA; i++) {
				for (int j = 0; j < sizeB; j++) {
					if (i == 0 && j == 0) {
						if (first.charAt(i)==second.charAt(j)) {array[i][j] = 1;}
						else {array[i][j] = 0;}	
					} else if (i == 0 && j > 0) {
						if (first.charAt(i)==second.charAt(j)) {array[i][j] = 1;}
						else {array[i][j] = array[i][j-1];}	
					} else if (i > 0 && j == 0) {
						if (first.charAt(i)==second.charAt(j)) {array[i][j] = 1;}
						else {array[i][j] = array[i-1][j];}	
					} else {
						if (first.charAt(i)==second.charAt(j)) {array[i][j] = 1+array[i-1][j-1];}
						else {array[i][j] = Math.max(array[i][j-1], array[i-1][j]);}
					}
				}
			}
			resultArr.add(array[sizeA-1][sizeB-1]);
		}
		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}	
}