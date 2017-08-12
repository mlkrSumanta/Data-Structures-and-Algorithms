class BellManFordAlgorithm {
	
	public static void main(String[] args) {
		
		Bellman_FordAlgo graph = new Bellman_FordAlgo(5);
		graph.addEdge(0, 1, -1);
		graph.addEdge(0, 2, 4);
		graph.addEdge(1, 2, 3);
		graph.addEdge(1, 3, 2);
		graph.addEdge(3, 1, 1);
		graph.addEdge(3, 2, 5);
		graph.addEdge(1, 4, 2);
		graph.addEdge(4, 3, -3);
		graph.findShortestPath(0);
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
		for (int i = 0; i < size; i++) {System.out.println("Vertex: " + i + " Weight from Vertex " + vertex + ": " + resultArray[i]);}
		return true;
	}
}