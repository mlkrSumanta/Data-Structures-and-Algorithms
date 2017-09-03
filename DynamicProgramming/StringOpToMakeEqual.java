import java.util.*;
import java.lang.*;

class StringOpToMakeEqual {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int sizeA = input.nextInt();
			int sizeB = input.nextInt();
			String first = input.next();
			String second = input.next();
			int[][] array = new int[sizeA+1][sizeB+1];
			for (int i = 0; i <= sizeA; i++) {
				for (int j = 0; j <= sizeB; j++) {
					if (i == 0) {array[i][j] = j;}
					else if (j == 0) {array[i][j] = i;}
					else if (first.charAt(i-1) == second.charAt(j-1)) {array[i][j] = array[i-1][j-1];}
					else {array[i][j] = 1+Math.min(Math.min(array[i][j-1], array[i-1][j]), array[i-1][j-1]);}
				}
			}
			resultArr.add(array[sizeA][sizeB]);
		}
		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}	
}