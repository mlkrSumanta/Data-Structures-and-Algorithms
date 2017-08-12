import java.lang.Math;

class NQueens {
	
	public static void main(String[] args) {
		
		BackTrackAlgo chess = new BackTrackAlgo(8);
		chess.solution();
	}
}

class BackTrackAlgo {

	int size;
	int result[][];

	public BackTrackAlgo(int size) {

		this.size = size;
	}

	public boolean isMovePossible(int row, int col) {
		
		if (row >= 0 && row < size && col >= 0 && col < size) {
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (result[i][j] == 1 && (row == i || col == j || 
						Math.abs(row-i) == Math.abs(col-j))) {
						
						return false;
					}
				}
			}
			return true;
		}

		return false;
	}

	public boolean isMovePlaced(int row, int col, int queen) {

		if (queen == size+1) {return true;}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (result[i][j] == 0 && (i != row || j != col || Math.abs(i-row) != Math.abs(j-col))) {
					if (isMovePossible(i, j)) {
						
						result[i][j] = 1;
						if (isMovePlaced(i, j, queen+1)) {return true;}
						else {result[i][j] = 0;}
					}
				}
			}
		}
		return false;
	}

	public void solution() {

		System.out.println();
		int res = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				
				res = 0;
				result = new int[size][size];
				result[i][j] = 1;

				if (isMovePlaced(i, j, 2)) {res = 1;}
				if (res == 1) {break;}
			}
			if (res == 1) {break;}
		}

		if (res == 1) {
			for (int k = 0; k < size; k++) {
				for (int l = 0; l < size; l++) {
					System.out.print(result[k][l] + " ");
				}
				System.out.println();
			}
		}
	}
}