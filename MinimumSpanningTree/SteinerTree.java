import java.util.*;

class SteinerTree {

	public static void main(String[] args) {
		
		Graph graph = new Graph(9);
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
		int[] arr = {1, 3, 6, 4};
		graph.findSteinerTree(arr);
	}
}

class EdgeNode {

	public int key;
	public int source;
	public int destination;
	public EdgeNode next;
	
	public EdgeNode(int key, int source, int destination, EdgeNode next) {

		this.key = key;
		this.source = source;
		this.destination = destination;
		this.next = next;
	}
}

class VertexNode {

	public int vertex;
	public int weight;

	public VertexNode(int vertex, int weight) {

		this.vertex = vertex;
		this.weight = weight;
	}
}

class Heap {

	public VertexNode[] heapArray;
	public int heapSize;

	public Heap(int size) {

		heapArray = new VertexNode[size];
		heapSize = 0;
	}

	public int left(int index) {return (2*index + 1);}

	public int right(int index) {return (2*index + 2);}

	public int parent(int index) {return (index-1)/2;}

	public void decreaseKey(int index, VertexNode value) {

		heapArray[index] = value;
		while (index != 0 && heapArray[parent(index)].weight > heapArray[index].weight) {

			VertexNode temp = heapArray[parent(index)];
			heapArray[parent(index)] = heapArray[index];
			heapArray[index] = temp;
			index = parent(index);
		}
	}

	public VertexNode extractMin() {

		if (heapSize == 0) {return null;}
		if (heapSize == 1) {return heapArray[--heapSize];}

		VertexNode node = heapArray[0];
		heapArray[0] = heapArray[--heapSize];
		minHeapify(0);

		return node;
	}

	public void minHeapify(int index) {

		int smallest = index;
		if (left(index) < heapSize && heapArray[left(index)].weight < heapArray[smallest].weight) {smallest = left(index);}
		if (right(index) < heapSize && heapArray[right(index)].weight < heapArray[smallest].weight) {smallest = right(index);}
		if (smallest != index) {
			
			VertexNode node = heapArray[index];
			heapArray[index] = heapArray[smallest];
			heapArray[smallest] = node;
			minHeapify(smallest);
		}
	}

	public void insertKey (VertexNode node) {

		heapSize++;
		int index = heapSize - 1;
		heapArray[index] = node;

		while (index != 0 && heapArray[parent(index)].weight > heapArray[index].weight && heapArray[parent(index)].vertex != heapArray[index].vertex) {
			
			VertexNode temp = heapArray[parent(index)];
			heapArray[parent(index)] = heapArray[index];
			heapArray[index] = temp;
		}

		if (index != 0 && heapArray[parent(index)].vertex == heapArray[index].vertex) {
			
			node = new VertexNode(heapArray[parent(index)].vertex, Integer.MIN_VALUE);
			decreaseKey(parent(index), node);
			extractMin();
		}
	}
}

class Graph {

	public int size;
	public ArrayList<EdgeNode> edgeSourceArray;

	public Graph(int size) {

		this.size = size;
		edgeSourceArray = new ArrayList<EdgeNode>(size);

		for (int i = 0; i < size; i++) {edgeSourceArray.add(null);}
	}

	public void addEdge(int src, int dest, int wt) {

		if (src < size && dest < size) {

			EdgeNode edge = new EdgeNode(wt, src, dest, null);
			EdgeNode node = edgeSourceArray.get(src);
			
			if (edgeSourceArray.get(src) == null) {

				edgeSourceArray.set(src, edge);
				
				edge = new EdgeNode(wt, dest, src, null);
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

			edge = new EdgeNode(wt, dest, src, null);
			if (edgeSourceArray.get(dest) == null) {

				edgeSourceArray.set(dest, edge);
				return;
			}
			node = edgeSourceArray.get(dest);
			while (node.next != null) {node = node.next;}
			node.next = edge;
		}
	}

	public void findSteinerTree(int[] terminal) {

		int[] weight = new int[size];
		int[] parent = new int[size];
		int count = 0, ver = 0;
		Heap heap = new Heap(size);
		for (int i = 0; i < size; i++) {weight[i] = Integer.MAX_VALUE;}
		weight[terminal[0]] = 0;
		parent[terminal[0]] = terminal[0];
		heap.insertKey(new VertexNode(terminal[0], 0));

		while (heap.heapSize > 0) {

			if (count > terminal.length) {break;}
			VertexNode vertexNode = heap.extractMin();
			EdgeNode edgeNode = edgeSourceArray.get(vertexNode.vertex);

			while (edgeNode != null) {

				if (weight[edgeNode.destination] > vertexNode.weight + edgeNode.key) {
					
					weight[edgeNode.destination] = vertexNode.weight + edgeNode.key;
					parent[edgeNode.destination] = vertexNode.vertex;
					heap.insertKey(new VertexNode(edgeNode.destination, weight[edgeNode.destination]));
					
					for (int i = 0; i < terminal.length; i++) {
						if (edgeNode.destination == terminal[i]) {count++;}
					}
				}
				edgeNode = edgeNode.next;
			}
		}

		for (int i = 0; i < terminal.length; i++) {
			
			System.out.print(terminal[i]);
			int j = terminal[i];
			while (j != terminal[0]) {

				System.out.print("->" + parent[j]);
				j = parent[j];
			}
			System.out.println();
		}
	}
}