import java.util.*;

class KnightsTourV2 {

	public static void main(String[] args) {
		
		WarnsdroffsAlgorithm board = new WarnsdroffsAlgorithm(8);
		board.solution();
	}
}

class WarnsdroffsAlgorithm {

	int size;
	int[][] result;
	int[] nextMoveX;
	int[] nextMoveY;

	public WarnsdroffsAlgorithm(int size) {

		this.size = size;
		result = new int[size][size];
		nextMoveX = new int[] {2, 1, -1, -2, -2, -1, 1, 2};
		nextMoveY = new int[] {1, 2, 2, 1, -1, -2, -2, -1};
	}

	public boolean isPossibleNextMove(int row, int col) {

		return (row >= 0 && row < size && col >= 0 &&
			col < size && result[row][col] == 0);
	}

	public int index(int row, int col) {

		int count = 0;
		for (int i = 0; i < 8; i++) {

			int moveX = row + nextMoveX[i];
			int moveY = col + nextMoveY[i];
			if(isPossibleNextMove(moveX, moveY)){count++;}
		}
		return count;
	}

	public boolean isValidMove(int row, int col, int move) {

		int index = 10;
		int moveX, moveY, minIndex = Integer.MAX_VALUE;
		ArrayList<Integer> sameAccessibilty = new ArrayList<>();

		if (move == size*size + 1) {return true;}
		for (int i = 0; i < 8; i++) {

			moveX = row + nextMoveX[i];
			moveY = col + nextMoveY[i];
			int ind = index(moveX, moveY);

			if (isPossibleNextMove(moveX, moveY) && index > ind) {
				
				index = ind;
				minIndex = i;
				sameAccessibilty.clear();
			} else if (isPossibleNextMove(moveX, moveY) && index == ind) {				
				sameAccessibilty.add(i);
			}
		}

		if (index != 10 && minIndex != Integer.MAX_VALUE) {
			
			sameAccessibilty.add(minIndex);
			for (int j = 0; j < sameAccessibilty.size(); j++) {
				
				index = sameAccessibilty.get(j);
				result[row + nextMoveX[index]][col + nextMoveY[index]] = move;
				if (isValidMove(row + nextMoveX[index], col + nextMoveY[index], move+1)) {
					return true;
				} else {
					result[row + nextMoveX[index]][col + nextMoveY[index]] = 0;
				}
			}
		}
		
		return false;
	}

	public void solution() {

		System.out.println();
		result[0][0] = 1;

		if (!isValidMove(0, 0, 2)) {
			
			System.out.println("Error");
			return;
		}

		for (int i = 0; i < size; i++) {
			
			for (int j = 0; j < size; j++) {
				System.out.format("%3d ", result[i][j]);
			}
			System.out.println();
			System.out.println();
		}
	}
}