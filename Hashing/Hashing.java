class Hashing {

	public static void main(String[] args) {
		
		cuckooHashImplement hTable = new cuckooHashImplement(10);
		hTable.add(21);
		hTable.add(43);
		hTable.add(36);
		hTable.add(42);
		hTable.add(74);
		hTable.add(68);
		hTable.add(23);
		hTable.add(85);
		hTable.add(24);
		hTable.add(45);
		hTable.print();
	}
}

class cuckooHashImplement {

	private int size;
	private int ver = 2;
	private int[][] hashTable;
	private int[] pos;
	private int loopLookup;

	public cuckooHashImplement(int size) {

		this.size = size;
		hashTable = new int[2][size];
		pos = new int[ver];

		for (int i = 0; i < ver; i++) {
			for (int j = 0; j < size; j++) {
				hashTable[i][j] = Integer.MIN_VALUE;
			}
		}
	}

	private int hash(int function, int value) {

		switch (function) {

			case 0: return value % size;
			case 1: return (value/size) % size;
		}
		return 0;
	}

	public void add(int value) {

		loopLookup = 0;
		calculate(value, 0);
	}

	public void calculate(int value, int tableId) {

		if (loopLookup >= size) {return;}

		for (int i = 0; i < ver; i++) {

			pos[i] = hash(i, value);
			if (hashTable[i][pos[i]] == value) {return;}
		}

		if (hashTable[tableId][pos[tableId]] != Integer.MIN_VALUE) {

			loopLookup++;
			int temp = hashTable[tableId][pos[tableId]];
			hashTable[tableId][pos[tableId]] = value;
			calculate(temp, (tableId+1)%ver);
		} else {

			hashTable[tableId][pos[tableId]] = value;
		}
	}

	public void print() {

		for (int i = 0; i < ver; i++) {
			System.out.println("Table: " + String.valueOf(i+1));
			for (int j = 0; j < size; j++) {
				String str = (hashTable[i][j] == Integer.MIN_VALUE) ? "__" : String.valueOf(hashTable[i][j]);
				System.out.print(str + " ");
			}
			System.out.println();
		}

		System.out.println();
	}
}