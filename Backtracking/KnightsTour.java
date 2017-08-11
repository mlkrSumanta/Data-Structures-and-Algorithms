class KnightsTour {
	
	public static void main(String[] args) {
		
		BackTrackAlgo board = new BackTrackAlgo(8);
		board.solution();
	}
}

class BackTrackAlgo {

	int size;
	int[][] result;
	int[] nextMoveX;
	int[] nextMoveY;
	
	public BackTrackAlgo(int size) {

		this.size = size;
		result = new int[size][size];
		nextMoveX = new int[] {2, 1, -1, -2, -2, -1, 1, 2};
		nextMoveY = new int[] {1, 2, 2, 1, -1, -2, -2, -1};
	}

	public boolean isPossibleNextMove(int row, int col) {

		if (row >= 0 && row < size && col >= 0 && col < size && result[row][col] == 0) {
			return true;
		}

		return false;
	}

	public boolean isValidMove(int row, int col, int move) {

		if (move == size*size + 1) {return true;}

		for (int i = 0; i < 8; i++) {
			
			int moveX = row + nextMoveX[i];
			int moveY = col + nextMoveY[i];
			if (isPossibleNextMove(moveX, moveY)) {
				
				result[moveX][moveY] = move;
				if (isValidMove(moveX, moveY, move+1)) {
					return true;
				} else {
					result[moveX][moveY] = 0;
				}
			}
		}

		return false;
	}

	public void solution() {

		int move = 1;
		result[0][0] = 1;

		if (!isValidMove(0, 0, 2)) {

			System.out.println("Error");
			return;
		}

		for (int i = 0; i < size; i++) {
			
			for (int j = 0; j < size; j++) {
				System.out.format("%2d ", result[i][j]);
			}
			System.out.println();
		}
	}
}