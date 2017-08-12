class DepthFirstSearch {
	
	public static void main(String[] args) {
		
		GraphImplement graph = new GraphImplement(5);
		graph.addEdge(0, 1);
		graph.addEdge(0, 4);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(0, 3);
		graph.print();
		graph.dfSearch(0);
	}
}

class Node {

	public int vertex;
	public Node next;

	public Node(int vertex, Node next) {

		this.vertex = vertex;
		this.next = next;
	}
}

class GraphImplement {

	protected int size;
	protected ArrayList<Node> nodeArray;

	public GraphImplement(int size) {

		this.size = size;
		nodeArray = new ArrayList<Node>(size);

		for (int i = 0; i < size; i++) {
			
			Node newNode = new Node(i, null);
			nodeArray.add(newNode);
		}
	}

	public void addEdge(int vertex, int neighbor) {

		if (vertex <= size && neighbor <= size) {

			connect(vertex, neighbor);
			connect(neighbor,vertex);
		}
	}

	protected void connect(int vertex, int neighbor) {

		Node node = nodeArray.get(vertex);
		Node prev = null;
		if (node != null) {

			while (node != null) {

				if (node.vertex == neighbor) {return;}
				prev = node;
				node = node.next;
			}
			Node temp = new Node(neighbor, null);
			prev.next = temp;
			return;
		}

		Node temp = new Node(neighbor, null);
		Node newNode = new Node(vertex, temp);
		nodeArray.set(vertex, newNode);
	}

	public void deleteEdge(int vertex, int neighbor) {

		if (vertex <= size && neighbor <= size) {

			disconnect(vertex, neighbor);
			disconnect(neighbor,vertex);
		}	
	}

	protected void disconnect(int vertex, int neighbor) {

		Node node = nodeArray.get(vertex);
		Node prev = null;
		while (node != null) {

			if (node.vertex == neighbor) {

				prev.next = node.next;
				return;
			}
			prev = node;
			node = node.next;
		}		
	}

	public void print() {

		for (Node node: nodeArray) {

			while(node != null) {

				System.out.print(node.vertex + " ");
				node = node.next;
			}
			System.out.println();
		}
	}

	public void dfSearch(int vertex) {

		System.out.println("DFS: " + vertex);

		boolean[] visited = new boolean[size];
		LinkedList<Node> queue = new LinkedList<Node>();
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
		System.out.println();
	}
}