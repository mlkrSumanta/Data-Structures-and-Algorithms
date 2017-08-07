import java.util.*;

class DAGraph {

	public static void main(String[] args) {
		
		GraphImplement graph = new GraphImplement(6);
		graph.addEdge(0, 1, 5);
		graph.addEdge(0, 2, 3);
		graph.addEdge(1, 3, 6);
		graph.addEdge(1, 2, 2);
		graph.addEdge(2, 4, 4);
		graph.addEdge(2, 5, 2);
		graph.addEdge(2, 3, 7);
		graph.addEdge(3, 4, -1);
		graph.addEdge(4, 5, -2);
//		graph.print();
//		graph.topologicalSort();
//		graph.kahnsTopologicalSort();
		graph.findShortestPath(1);
	}
}

class Edge {

	int source;
	int destination;
	int weight;
	Edge next;

	public Edge(int source, int destination, int weight, Edge next) {

		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.next = next;
	}
}

class GraphImplement {

	int size;
	ArrayList<Edge> edgeArray;

	public GraphImplement(int size) {

		this.size = size;
		edgeArray = new ArrayList<Edge>();
		for (int i = 0; i < size; i++) {edgeArray.add(null);}
	}

	public void addEdge(int source, int destination, int weight) {

		Edge edge = edgeArray.get(source);
		if (edge == null) {
			
			edgeArray.set(source, new Edge(source, destination, weight, null));
			return;
		}

		while (edge.next != null) {edge = edge.next;}
		edge.next = new Edge(source, destination, weight, null);
	}

	public void print() {

		for (int i = 0; i < size; i++) {
			
			Edge edge = edgeArray.get(i);
			while (edge != null) {

				System.out.println(edge.source + "->" + edge.destination);
				edge = edge.next;
			}
		}
	}

	public void topologicalSort() {

		LinkedList<Edge> queue = new LinkedList<>();
		LinkedList<Integer> stack = new LinkedList<>();
		boolean[] visited = new boolean[size];
		boolean[] marked = new boolean[size];

		for (int i = 0; i < size; i++) {
			if (!visited[i]) {
				
				Edge edge = edgeArray.get(i);
				if (edge == null) {
					
					stack.add(i);
					marked[i] = true;
					visited[i] = true;
					continue;
				}

				queue.add(edge);
				while (!queue.isEmpty()) {

					edge = queue.peekLast();
					visited[edge.source] = true;
					edge = edgeArray.get(edge.destination);
					while (edge != null) {
						if (!visited[edge.destination]) {

							queue.add(edge);
							break;
						}
						edge = edge.next;
					}

					if (edge == null) {
						
						edge = queue.removeLast();
						if (!marked[edge.destination]) {

							stack.add(edge.destination);
							marked[edge.destination] = true;
						}
						if (!marked[edge.source]) {
							
							stack.add(edge.source);
							marked[edge.source] = true;
						}
					}
				}
			}
		}

		while (!stack.isEmpty()) {System.out.print(stack.removeLast() + " ");}
		System.out.println();
	}

	public LinkedList<Integer> kahnsTopologicalSort() {

		int[] inDegree = new int[size];
		LinkedList<Integer> sortedArray = new LinkedList<>();
		LinkedList<Integer> queue = new LinkedList<>();

		for (int i = 0; i < size; i++) {
			
			Edge edge = edgeArray.get(i);
			while (edge != null) {

				inDegree[edge.destination]++;
				edge = edge.next;
			}
		}

		for (int i = 0; i < size; i++) {
			if (inDegree[i] == 0) {queue.add(i);}
		}

		while (!queue.isEmpty()) {

			Edge edge = edgeArray.get(queue.peekFirst());
			while (edge != null) {

				if (--inDegree[edge.destination] == 0) {queue.add(edge.destination);}
				edge = edge.next;
			}
			sortedArray.add(queue.removeFirst());
		}
		return sortedArray;
	}

	public void findShortestPath(int vertex) {

		LinkedList<Integer> sortedArray = kahnsTopologicalSort();
		int[] weight = new int[size];

		for (int i = 0; i < size; i++) {weight[i] = Integer.MAX_VALUE;}
		weight[vertex] = 0;
		while (sortedArray.peekFirst() != vertex) {sortedArray.removeFirst();}
		while (!sortedArray.isEmpty()) {

			Edge edge = edgeArray.get(sortedArray.removeFirst());
			while (edge != null) {

				if (weight[edge.destination] > weight[edge.source] + edge.weight) {weight[edge.destination] = weight[edge.source] + edge.weight;}
				edge = edge.next;
			}
		}

		System.out.println("Shortest paths from vertex " + vertex + " is as below:");
		for (int i = 0; i < size; i++) {System.out.println(vertex + "->" + i + ": " + weight[i]);}
	}
}