import java.util.*;

class Graph {
	
	public static void main(String[] args) {
		
		int numberOfEdges = 14;
//		PrimsAlgo graph = new PrimsAlgo(9, 2*numberOfEdges);
		KruskalsAlgo graph = new KruskalsAlgo(9, 2*numberOfEdges);
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

	public KeyHeap(int size) {

		heapSize = 0;
		nodeArray = new HeapNode[size];
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

		/*if (i != 0 && nodeArray[parent(i)].equals(nodeArray[i])) {

			nodeArray[parent(i)].key = Integer.MIN_VALUE;
			decreaseKey(parent(i), nodeArray[i]);
			extractMin();
		}*/
	}

	public void sortedArray() {

		while (heapSize > 0) {

			HeapNode node = extractMin();
			System.out.println(node.source + "->" + node.destination + " : " + node.key);
		}
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

	public int numberOfEdges;
	public PrimsAlgo(int size, int numberOfEdges) {

		super(size);
		this.numberOfEdges = numberOfEdges;
	}

	public void findSpanningTree() {

		KeyHeap heap = new KeyHeap(numberOfEdges);
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
				node = node.next;
			}

		}
	}
}

class KruskalsAlgo extends SpanningTree {

	public int numberOfEdges;
	public int size;
	public ArrayList<HeapNode> mstArray;
	public ArrayList<HeapNode> tempArray;

	public KruskalsAlgo(int size, int numberOfEdges) {

		super(size);
		this.size = size;
		this.numberOfEdges = numberOfEdges;
		mstArray = new ArrayList<HeapNode>();
	}

	public void findSpanningTree() {

		KeyHeap heap = new KeyHeap(numberOfEdges);

		for (int i = 0; i < size; i++) {
			
			HeapNode node = edgeSourceArray.get(i);
			while (node != null) {

				heap.insertKey(node);
				node = node.next;
			}		
		}

//		heap.sortedArray();

		while (heap.heapSize > 0) {

			HeapNode node = heap.extractMin();
			 
			if (mstArray.isEmpty()) {
			 	
			 	mstArray.add(node);
			 	continue;
			}

			tempArray = new ArrayList<HeapNode>(mstArray);
			tempArray.add(node);
			if (!hasCycle()) {mstArray.add(node);}
		}

		for (int i = 0; i < mstArray.size(); i++) {
			
			HeapNode node = mstArray.get(i);
			System.out.println(node.source + "->" + node.destination + " : " + node.key);
		}
	}

	public boolean hasCycle() {

		int[] parent = new int[size];
		for (int i = 0; i < size; i++) {parent[i] = -1;}
		for (int i = 0; i < tempArray.size(); i++) {
			
			HeapNode temp = tempArray.get(i);
			int x = findParent(parent, temp.source);
			int y = findParent(parent, temp.destination);

			if (x == y) {
				return true;
			}

			union(parent, x, y);
		}

		return false;
	}

	public int findParent(int[] parent, int vertex) {

		if (parent[vertex] == -1) {return vertex;}
		return findParent(parent, parent[vertex	]);
	}

	public void union(int[] parent, int sourceParent, int destinationParent) {

		int sP = findParent(parent, sourceParent);
		int dP = findParent(parent, destinationParent);
		parent[dP] = sP;
	}
}