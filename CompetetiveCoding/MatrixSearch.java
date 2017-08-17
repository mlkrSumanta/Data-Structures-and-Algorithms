import java.util.*;

class MatrixSearch {
	
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Integer> resultArr = new ArrayList<>();
		
		class Solution {

			int row, col, numToFind, result;
			int[][] array;
			
			public Solution(int row, int col, int[][] array, int numToFind) {
				
				this.row = row;
				this.col = col;
				this.array = array;
				this.numToFind = numToFind;
				result = -1;
			}

			void solution() {

				for (int i = 0; i < row; i++) {
					if (numToFind < array[i][col-1]) {
						
						int mid = (0+col-1)/2;
						if (array[i][mid] == numToFind) {result = 1;}
						else if (array[i][mid] > numToFind) {result = binarySearch(numToFind, i, 0, mid-1);}
						else if (array[i][mid] < numToFind) {result = binarySearch(numToFind, i, mid+1, col-1);}
						break;
					}
					else if (numToFind == array[i][col-1]) {result = 1; break;}
				}
				if (result == -1) {result = 0;}
				resultArr.add(result);
			}

			int binarySearch(int num, int row, int start, int end) {

				if (start == end) {
					
					if (array[row][start] == num) {return 1;}
					else {return 0;}
				}

				if (start > end) {return 0;}

				int mid = (start+end)/2;
				if (array[row][mid] == num) {return 1;}
				if (array[row][mid] > num) {return binarySearch(num, row, start, mid-1);}
				if (array[row][mid] < num) {return binarySearch(num, row, mid+1, end);}
				return 0;
			}
		}

		for (int k = 0; k < n; k++) {
		
			int[] size = new int[2];
			int row, col, numToFind;
			
			for (int i = 0; i < 2; i++) {size[i] = input.nextInt();}
			int[][] array = new int[size[0]][size[1]];
			for (int i = 0; i < size[0]; i++) {
				for (int j = 0; j < size[1]; j++) {array[i][j] = input.nextInt();}
			}
			numToFind = input.nextInt();
			
			Solution sol = new Solution(size[0], size[1], array, numToFind);
			sol.solution();	
		}

		for (int i = 0; i < resultArr.size(); i++) {System.out.println(resultArr.get(i));}
	}
}