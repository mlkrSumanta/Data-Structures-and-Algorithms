import java.util.*;

class Graph {
	
	public static void main(String[] args) {
		
		JohnsonAlgo graph = new JohnsonAlgo(4);
		graph.addEdge(0, 2, -2);
		graph.addEdge(1, 0, 4);
		graph.addEdge(1, 2, 3);
		graph.addEdge(2, 3, 2);
		graph.addEdge(3, 1, -1);
		graph.findShortestPath();
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
			index = parent(index);
		}

		if (index != 0 && heapArray[parent(index)].vertex == heapArray[index].vertex) {
			
			node = new VertexNode(heapArray[parent(index)].vertex, Integer.MIN_VALUE);
			decreaseKey(parent(index), node);
			extractMin();
		}
	}
}

class GraphImplement {

	public int size;
	public ArrayList<EdgeNode> edgeSourceArray;
	public boolean directed;

	public GraphImplement(int size, boolean directed) {

		this.size = size;
		edgeSourceArray = new ArrayList<EdgeNode>();
		this.directed = directed;

		for (int i = 0; i < size; i++) {edgeSourceArray.add(null);}
	}

	public void addEdge(int src, int dest, int wt) {

		if (src < size && dest < size && !directed) {

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

		if (src < size && dest < size && directed) {

			EdgeNode edge = new EdgeNode(wt, src, dest, null);
			EdgeNode node = edgeSourceArray.get(src);
			
			if (edgeSourceArray.get(src) == null) {

				edgeSourceArray.set(src, edge);
				return;
			}
			
			while (node.next != null) {node = node.next;}
			node.next = edge;
		}
	}
}

class DijkstraAlgo extends GraphImplement {

	public int[] vertexArray;
	public int[] resultArray;
	public Heap heap;
	public int size;

	public DijkstraAlgo(int size) {

		super(size, true);
		this.size = size;
	}

	public void findShortestPath(int v) {

		heap = new Heap(size);
		vertexArray = new int[size];
		for (int i = 0; i < size; i++) {vertexArray[i] = Integer.MAX_VALUE;}
		vertexArray[v] = 0;
		EdgeNode node = edgeSourceArray.get(v);
		while (node != null) {

			vertexArray[node.destination] = node.key;
			VertexNode vertex = new VertexNode(node.destination, node.key);
			heap.insertKey(vertex);
			node = node.next;
		}

		while (heap.heapSize > 0) {

			VertexNode ver = heap.extractMin();
			node = edgeSourceArray.get(ver.vertex);
			while (node != null) {

				if (vertexArray[node.destination] > (ver.weight + node.key)) {

					heap.insertKey(new VertexNode(node.destination, ver.weight + node.key));
					vertexArray[node.destination] = ver.weight + node.key;
				}
				node = node.next;
			}
		}

//		for (int i = 0; i < size; i++) {System.out.println("Vertex: " + i + " Weight from Vertex " + v + ": " + vertexArray[i]);}
	}
}

class Bellman_FordAlgo extends GraphImplement {

	public int[] resultArray;
	public int size;

	public Bellman_FordAlgo(int size) {

		super(size, true);
		this.size = size;
	}

	public boolean findShortestPath(int vertex) {

		resultArray = new int[size];
		for (int i = 0; i < size; i++) {resultArray[i] = Integer.MAX_VALUE;}
		resultArray[vertex] = 0;
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < edgeSourceArray.size(); j++) {
				
				EdgeNode node = edgeSourceArray.get(j);
				while (node != null) {

					if (resultArray[node.destination] > resultArray[node.source] + node.key && resultArray[node.source] != Integer.MAX_VALUE) 
						{resultArray[node.destination] = resultArray[node.source] + node.key;}
					node = node.next;
				}
			}
		}

		for (int j = 0; j < edgeSourceArray.size(); j++) {
				
			EdgeNode node = edgeSourceArray.get(j);
			while (node != null) {

				if (resultArray[node.destination] > resultArray[node.source] + node.key && resultArray[node.source] != Integer.MAX_VALUE) {

					System.out.println("Cycle exists");
					return false;
				}
				node = node.next;
			}
		}
//		for (int i = 0; i < size; i++) {System.out.println("Vertex: " + i + " Weight from Vertex " + vertex + ": " + resultArray[i]);}
		return true;
	}
}

class JohnsonAlgo extends GraphImplement {

	public int size;
	
	public JohnsonAlgo(int size) {

		super(size + 1, true);
		this.size = size;
	}

	public void findShortestPath() {

		for (int i = 0; i < size; i++) {addEdge(size, i, 0);}

		Bellman_FordAlgo g = new Bellman_FordAlgo(size+1);
		g.edgeSourceArray = new ArrayList<EdgeNode>(edgeSourceArray);

		if (g.findShortestPath(size)) {
			for (int i = 0; i < size; i++) {
				
				EdgeNode node = edgeSourceArray.get(i);
				while (node != null) {
	
					node.key = node.key + g.resultArray[node.source] - g.resultArray[node.destination];
					node = node.next;
				}
			}
		}

		edgeSourceArray.remove(edgeSourceArray.size() - 1);
		DijkstraAlgo gr = new DijkstraAlgo(size);
		gr.edgeSourceArray = new ArrayList<EdgeNode>(edgeSourceArray);
		
		for (int i = 0; i < size; i++) {

			gr.findShortestPath(i);
			for (int j = 0; j < size; j++) {
				
				int val = gr.vertexArray[j] - g.resultArray[i] + g.resultArray[j];
				System.out.println(i + "->" + j + ": " + val);
			}
		}
	}
}