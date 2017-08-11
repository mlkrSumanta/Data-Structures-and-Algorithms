class ChessDominos {
	
	public static void main(String[] args) {
		
		BackTrackAlgo chess = new BackTrackAlgo(6);
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
		board = new int[size][size];
		nextPosX = new int[] {0, 2};
		nextPosY = new int[] {2, 0};
	}

	public boolean isPossibleNextMove(int row, int col) {
		return (row >= 0 && row < size && col >= 0 && col < size &&
			board[row][col] == 0);
	}

	public boolean isValidPosition(int row, int col, int domino) {

		if (domino == 31) {return true;}

		for (int i = 0; i < 2; i++) {
			if (nextPosX[i] == 0) {
				if (isPossibleNextMove(row+nextPosX[i], col+nextPosY[i]) && 
					isPossibleNextMove(row+nextPosX[i], col+nextPosY[i]+1)) {
					
					board[row+nextPosX[i]][col+nextPosY[i]] = 1;
					board[row+nextPosX[i]][col+nextPosY[i]+1] = 1;

					if (isValidPosition(row+nextPosX[i], col+nextPosY[i], domino+1)) {
						return true;
					} else {

						board[row+nextPosX[i]][col+nextPosY[i]] = 0;
						board[row+nextPosX[i]][col+nextPosY[i]+1] = 0;						
					}
				}
			} else {
				if (isPossibleNextMove(row+nextPosX[i], col+nextPosY[i]) && 
					isPossibleNextMove(row+nextPosX[i]+1, col+nextPosY[i])) {
					
					board[row+nextPosX[i]][col+nextPosY[i]] = 1;
					board[row+nextPosX[i]+1][col+nextPosY[i]] = 1;

					if (isValidPosition(row+nextPosX[i], col+nextPosY[i], domino+1)) {
						return true;
					} else {

						board[row+nextPosX[i]][col+nextPosY[i]] = 0;
						board[row+nextPosX[i]+1][col+nextPosY[i]] = 0;						
					}
				}
			}
		}

		return false;
	}

	public void solution() {

		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int m = 0; m < size; m++) {
					for (int n = 0; n < size; n++) {
						if (i < m && j < n) {
							
							board[i][j] = 1;
							board[m][n] = 1;
							for (int k = 0; k < size; k++) {
								for (int l = 0; l < size; l++) {
									if (k != i && l != j) {
										if (isValidPosition(k, l, 1)) {

											count++;
											break;
										}
									}
								}
							}

							if (count == 0) {
								System.out.println("(" + i + "," + j + ")" + "," + "(" + m + "," + n + ")");
							}
						}
					}
				}
			}
		}
	}
}