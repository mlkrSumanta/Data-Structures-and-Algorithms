import java.util.*;

class Graph {
	
	public static void main(String[] args) {
		
		PrimsAlgo graph = new PrimsAlgo(9);
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 7, 8);
		graph.addEdge(1, 2, 8);
		graph.addEdge(1, 7, 11);
		graph.addEdge(2, 5, 4);
		graph.addEdge(2, 8, 2);
		graph.addEdge(2, 3, 7);
		graph.addEdge(3, 4, 9);
		graph.addEdge(3, 5, 14);
		graph.addEdge(4, 5, 10);
		graph.addEdge(5, 6, 2);
		graph.addEdge(6, 7, 1);
		graph.addEdge(6, 8, 6);
		graph.addEdge(7, 8, 7);
		graph.findSpanningTree();
	}
}

class HeapNode {

	public int key;
	public int source;
	public int destination;
	public HeapNode next;
	
	public HeapNode(int key, int source, int destination, HeapNode next) {

		this.key = key;
		this.source = source;
		this.destination = destination;
		this.next = next;
	}
}

class KeyHeap {

	public HeapNode[] nodeArray;
	public int heapSize;

	public KeyHeap(int size, ArrayList<HeapNode> array) {

		heapSize = 0;
		nodeArray = new HeapNode[4*size];
	}

	public int left(int i) {return 2*i + 1;}

	public int right(int i) {return 2*i + 2;}

	public int parent(int i) {return (i-1)/2;}

	public void decreaseKey(int i, HeapNode val) {

		nodeArray[i] = val;
		while (i != 0 && nodeArray[parent(i)].key > nodeArray[i].key) {

			HeapNode temp = nodeArray[parent(i)];
			nodeArray[parent(i)] = nodeArray[i];
			nodeArray[i] = nodeArray[parent(i)];
			i = parent(i);
		}
	}

	public HeapNode extractMin() {

		if (heapSize == 0) {return null;}
		if (heapSize == 1) {heapSize--; return nodeArray[0];}

		HeapNode temp = nodeArray[0];
		nodeArray[0] = nodeArray[heapSize-1];
		heapSize--;
		minHeapify(0);

		return temp;
	}

	public void minHeapify(int i) {

		int smallest = i;
		if (left(i) < heapSize && nodeArray[left(i)].key < nodeArray[i].key) {smallest = left(i);}
		if (right(i) < heapSize && nodeArray[right(i)].key < nodeArray[smallest].key) {smallest = right(i);}

		if (smallest != i) {

			HeapNode temp = nodeArray[i];
			nodeArray[i] = nodeArray[smallest];
			nodeArray[smallest] = temp;
			minHeapify(smallest);
		}
	}

	public void insertKey(HeapNode node) {

		heapSize++;
		nodeArray[heapSize-1] = node;
		int i = heapSize - 1;

		while (i != 0 && nodeArray[parent(i)].key > nodeArray[i].key) {

			HeapNode temp = nodeArray[parent(i)];
			nodeArray[parent(i)] = nodeArray[i];
			nodeArray[i] = temp;
			i = parent(i);
		}

		if (i != 0 && nodeArray[parent(i)].equals(nodeArray[i])) {

			nodeArray[parent(i)].key = Integer.MIN_VALUE;
			decreaseKey(parent(i), nodeArray[i]);
			extractMin();
		}
	}

	public void sortedArray() {

		System.out.println();
		while (heapSize > 0) {

			HeapNode node = extractMin();
			System.out.print(node.destination + "," + node.key + " | ");
		}
		System.out.println();	
	}
}	

class SpanningTree {

	public int size;
	public ArrayList<HeapNode> edgeSourceArray;

	public SpanningTree(int size) {

		this.size = size;
		edgeSourceArray = new ArrayList<HeapNode>(size);

		for (int i = 0; i < size; i++) {edgeSourceArray.add(null);}
	}

	public void addEdge(int src, int dest, int wt) {

		if (src < size && dest < size) {

			HeapNode edge = new HeapNode(wt, src, dest, null);
			HeapNode node = edgeSourceArray.get(src);
			
			if (edgeSourceArray.get(src) == null) {

				edgeSourceArray.set(src, edge);
				
				edge = new HeapNode(wt, dest, src, null);
				if (edgeSourceArray.get(dest) == null) {

					edgeSourceArray.set(dest, edge);
					return;
				}
				node = edgeSourceArray.get(dest);
				while (node.next != null) {node = node.next;}
				node.next = edge;
				return;
			}
			
			while (node.next != null) {node = node.next;}
			node.next = edge;

			edge = new HeapNode(wt, dest, src, null);
			if (edgeSourceArray.get(dest) == null) {

				edgeSourceArray.set(dest, edge);
				return;
			}
			node = edgeSourceArray.get(dest);
			while (node.next != null) {node = node.next;}
			node.next = edge;
		}
	}
}


class PrimsAlgo extends SpanningTree {

	public PrimsAlgo(int size) {super(size);}

	public void findSpanningTree() {

		KeyHeap heap = new KeyHeap(size, edgeSourceArray);
		boolean[] visited = new boolean[size];
		visited[0] = true;

		HeapNode node = edgeSourceArray.get(0);
		while (node != null) {

			heap.insertKey(node);
			node = node.next;
		}

		while (heap.heapSize > 0) {

			node = heap.extractMin();
			if (!visited[node.destination]) {System.out.println(node.source + "->" + node.destination + " : " + node.key);}
			visited[node.source] = true;
			visited[node.destination] = true;
			node = edgeSourceArray.get(node.destination);
			while (node != null) {

				if (!visited[node.destination]) {heap.insertKey(node);}
//				System.out.print(node.source + " " + node.destination + " " + node.key);
				node = node.next;
			}

		}
	}
}

class KruskalsAlgo {

}

class BoruvkasAlgo {

}