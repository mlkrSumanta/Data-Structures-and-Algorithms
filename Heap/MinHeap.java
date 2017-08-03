class MinHeap {

	public static void main(String[] args) {
		
		hpImplement heap = new hpImplement(10);
		heap.insert(24);
		heap.insert(36);
		heap.insert(43);
		heap.insert(10);
		heap.insert(534);
		heap.insert(2);
		heap.insert(67);
		heap.insert(464);
		heap.insert(34);
		heap.print();
		heap.printArray();
		heap.delete(1);
		heap.print();
		heap.sortedArray();
	}
}

class hpImplement {
	
	private int[] refArr;
	private int capacity;
	private int heap_size;

	public hpImplement(int capacity) {

		heap_size = 0;
		this.capacity = capacity;
		refArr = new int[capacity];
	}

	public int parent(int i) {return (i-1)/2;}

	public int left(int i) {return (2*i + 1);}

	public int right(int i) {return (2*i + 2);}

	public int getMin() {return refArr[0];}

	public void decreaseKey(int i, int val) {

		refArr[i] = val;
		while (i != 0 && refArr[parent(i)] > refArr[i]) {

			int j = parent(i);
			int temp = refArr[j];
			refArr[j] = refArr[i];
			refArr[i] = temp;
			i = j;
		}
	}

	public int extractMin() {

		if (heap_size <= 0) {return Integer.MAX_VALUE;}
		else if (heap_size == 1) {

			heap_size--;
			return refArr[0];
		}

		int root = refArr[0];
		refArr[0] = refArr[heap_size - 1];
		heap_size--;
		minHeapify(0);

		return root;
	}

	public void minHeapify(int i) {

		int l = left(i);
		int r = right(i);
		int smallest = i;
		if (l < heap_size && refArr[l] < refArr[i]) {smallest = l;}
		if (r < heap_size && refArr[r] < refArr[smallest]) {smallest = r;}
		if (smallest != i) {

			int temp = refArr[i];
			refArr[i] = refArr[smallest];
			refArr[smallest] = temp;
			minHeapify(smallest);
		}
	}

	public void insert(int key) {

		if (heap_size == capacity) {

			System.out.println("Overflow");
			return;
		}

		heap_size++;
		int i = heap_size - 1;
		refArr[i] = key;

		while (i != 0 && refArr[parent(i)] > refArr[i]) {

			int j = parent(i);
			int temp = refArr[j];
			refArr[j] = refArr[i];
			refArr[i] = temp;
			i = j;
		}
	}

	public void delete(int index) {

		decreaseKey(index, Integer.MIN_VALUE);
		extractMin();
	}

	public void print() {

		System.out.println();
		printInOrder(0);
		System.out.println();
	}

	public void printInOrder(int index) {

		if (index >= heap_size) {return;}
		printInOrder(left(index));
		System.out.print(refArr[index] + " ");
		printInOrder(right(index));

		return;
	}

	public void printArray() {

		System.out.println();
		for (int i = 0; i < heap_size; i++) {
			System.out.print(refArr[i] + " ");
		}
		System.out.println();
	}

	public void sortedArray() {

		System.out.println();
		while (heap_size > 0) {System.out.print(extractMin() + " ");}
		System.out.println();	
	}
}