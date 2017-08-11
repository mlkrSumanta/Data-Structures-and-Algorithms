class RatInMaze {
	
	public static void main(String[] args) {
		
		int[][] maze = new int[][] {{1, 0, 0, 0},
									{1, 1, 0, 1},
									{0, 1, 0, 0},
									{1, 1, 1, 1}};
		BackTrack board = new BackTrack(4, 4, new int[] {0, 0},
			new int[] {3, 3}, maze);
		board.solution();
	}
}

class BackTrack {

	int[][] maze, result;
	int[] moveX, moveY, source, destination;
	int row, col; 

	public BackTrack(int row, int col, int[] source, int[] destination, int[][] maze) {

		this.maze = maze;
		this.row = row;
		this.col = col;
		this.source = source;
		this.destination = destination;
		result = new int[row][col];
		moveX = new int[] {0, 1};
		moveY = new int[] {1, 0};
	}

	public boolean isNextMovePossible(int rowNum, int colNum) {
		return (rowNum >= 0 && rowNum < row && colNum >= 0 &&
			colNum < col && maze[rowNum][colNum] == 1);
	}

	public boolean isValidMove(int row, int col) {

		if (row == destination[0] && col == destination[1]) {return true;}

		for (int i = 0; i < 2; i++) {
			
			int nextX = row + moveX[i];
			int nextY = col + moveY[i];
			if (isNextMovePossible(nextX, nextY)) {
				
				result[nextX][nextY] = 1;
				if (isValidMove(nextX, nextY)) {
					return true;
				} else {
					result[nextX][nextY] = 0;
				}
			}
		}

		return false;
	}

	public void solution() {

		result[source[0]][source[1]] = 1;
		if (!isValidMove(source[0], source[1])) {
			
			System.out.println("Error");
			return;
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}
}