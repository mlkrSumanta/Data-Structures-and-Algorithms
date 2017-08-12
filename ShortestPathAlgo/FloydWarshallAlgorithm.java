class FloydWarshallAlgorithm {
	
	public static void main(String[] args) {
		
		Floyd_WarshallAlgo graph = new Floyd_WarshallAlgo(4);
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

class Floyd_WarshallAlgo extends GraphImplement {

	public int[][] resultArray;

	public Floyd_WarshallAlgo(int size) {

		super(size, true);
		resultArray = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				if (i == j) {resultArray[i][j] = 0;continue;}
				resultArray[i][j] = Integer.MAX_VALUE;}
		}
	}

	public void findShortestPath() {

		for (int j = 0; j < edgeSourceArray.size(); j++) {
			
			EdgeNode node = edgeSourceArray.get(j);
			while (node != null) {

				resultArray[node.source][node.destination] = node.key;
				node = node.next;
			}
		}

		for (int k = 0; k < size; k++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (resultArray[k][j] != Integer.MAX_VALUE && resultArray[i][k] != Integer.MAX_VALUE) {
						if (resultArray[i][j] > resultArray[i][k] + resultArray[k][j]) {resultArray[i][j] = resultArray[i][k] + resultArray[k][j];}
					}
				}
			}
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {System.out.println("Shortest path from " + i + " to " + j + " is: " + resultArray[i][j]);}
		}
	}
}