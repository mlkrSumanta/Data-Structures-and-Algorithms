import java.util.*;

class DAG {
	
	public static void main(String[] args) {
		
		DAGimplement graph = new DAGimplement(6);
		graph.addEdge(2, 3);
		graph.addEdge(3, 1);
		graph.addEdge(5, 2);
		graph.addEdge(5, 0);
		graph.addEdge(4, 0);
		graph.addEdge(4, 1);
		graph.print();
//		graph.deleteEdge(0, 3);
//		System.out.println("After Deletion");
//		graph.print();
		graph.bfSearch();
		graph.dfSearch();
		graph.topologicalSorting();	
	}
}

class DAGimplement extends GraphImplement {

	public DAGimplement(int size) {

		super(size);
	}

	public void addEdge(int vertex, int neighbor) {

		if (vertex <= size && neighbor <= size) {connect(vertex, neighbor);}
	}

	public void deleteEdge(int vertex, int neighbor) {

		if (vertex <= size && neighbor <= size) {disconnect(vertex, neighbor);}
	}

	public void bfSearch() {

		System.out.println("BFS:");
		boolean[] visited = new boolean[size];
		LinkedList<Node> queue = new LinkedList<Node>();
			
		for (int vertex = 0; vertex < size; vertex++) {			
			if (!visited[vertex]) {

				Node node = nodeArray.get(vertex);
				visited[vertex] = true;
				queue.add(node);

				while (!queue.isEmpty()) {
				
					node = nodeArray.get(queue.peekFirst().vertex);
					node = node.next;
					while (node != null) {				
						if (!visited[node.vertex]) {
						
							queue.add(node);
							visited[node.vertex] = true;
							break;	
						}
						node = node.next;
					}

					if (node == null) {

						Node tmp = queue.removeFirst();
						System.out.print(tmp.vertex + " ");
					}
				}
			}
		}
		System.out.println();
	}

	public void dfSearch() {

		System.out.println("DFS:");
		boolean[] visited = new boolean[size];
		LinkedList<Node> queue = new LinkedList<Node>();
			
		for (int vertex = 0; vertex < size; vertex++) {
			if (!visited[vertex]) {

				Node node = nodeArray.get(vertex);
				visited[vertex] = true;
				queue.add(node);

				while (!queue.isEmpty()) {
				
					node = nodeArray.get(queue.peekLast().vertex);
					node = node.next;
					while (node != null) {				
						if (!visited[node.vertex]) {
						
							queue.add(node);
							visited[node.vertex] = true;
							break;	
						}
						node = node.next;
					}

					if (node == null) {

						Node tmp = queue.removeLast();
						System.out.print(tmp.vertex + " ");
					}
				}
			}
		}
		System.out.println();	
	}

	public void topologicalSorting() {

		System.out.println("DFS:");
		boolean[] visited = new boolean[size];
		LinkedList<Node> queue = new LinkedList<Node>();
		LinkedList<Integer> stack = new LinkedList<Integer>();
			
		for (int vertex = 0; vertex < size; vertex++) {
			if (!visited[vertex]) {

				Node node = nodeArray.get(vertex);
				visited[vertex] = true;
				queue.add(node);

				while (!queue.isEmpty()) {
				
					node = nodeArray.get(queue.peekLast().vertex);
					node = node.next;
					while (node != null) {				
						if (!visited[node.vertex]) {
						
							queue.add(node);
							visited[node.vertex] = true;
							break;	
						}
						node = node.next;
					}

					if (node == null) {

						Node tmp = queue.removeLast();
						stack.add(tmp.vertex);
					}
				}
			}
		}

		while (!stack.isEmpty()) {System.out.print(stack.removeLast() + " ");}
		System.out.println();	
	}
}