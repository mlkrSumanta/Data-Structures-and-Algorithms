class ChessDominos {
	
	public static void main(String[] args) {
		
		BackTrackAlgo chess = new BackTrackAlgo(8);
		chess.solution();
	}
}

class BackTrackAlgo {

	int size;
	int rows, cols;
	int[][] board;
	int[] nextPosX;
	int[] nextPosY;

	public BackTrackAlgo(int size) {

		this.size = size;
		nextPosX = new int[] {0, 0, -1, 1};
		nextPosY = new int[] {1, -1, 0, 0};
	}

	public boolean isPossibleNextMove(int row, int col) {
		return (row >= 0 && row < size && col >= 0 && col < size &&
			board[row][col] == 0);
	}

	public boolean isValidPosition(int row, int col) {

		for (int i = 0; i < 4; i++) {
			
			int moveX1 = row;
			int moveX2 = row + nextPosX[i];
			int moveY1 = col;
			int moveY2 = col + nextPosY[i];

			if (isPossibleNextMove(moveX1, moveY1) && isPossibleNextMove(moveX2, moveY2)) {
				
				board[moveX1][moveY1] = 1;
				board[moveX2][moveY2] = 1;

				return true;
			}
		}

		return false;
	}

	public void solution() {

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int m = 0; m < size; m++) {
					for (int n = 0; n < size; n++) {

						if ((i != m || j != n) && i <= m && j <= n) {
	
							int count = 0;						
							board = new int[size][size];
							board[i][j] = 1;
							board[m][n] = 1;
							for (int k = 0; k < size; k++) {
								for (int l = 0; l < size; l++) {
									if (isValidPosition(k, l)) {
										count++;
									}
								}
							}

							if (count < 31) {
								System.out.print("(" + i + "," + j + ")" + "," + "(" + m + "," + n + ")" + "\t");
							}
						}
					}
				}
			}
		}

		/*board = new int[size][size];
		board[0][0] = 1;
		board[7][7] = 1;
		int count = 0;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (isValidPosition(i, j)) {
					count++;
				}
			}
		}

		if (count == 31){System.out.println("Yep");}*/
	}
}