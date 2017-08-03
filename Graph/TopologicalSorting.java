import java.util.*;

class TopologicalSorting {
	
	public static void main(String[] args) {
		KahnsAlgo g = new KahnsAlgo(6);
		g.addEdge(5, 2);
	    g.addEdge(5, 0);
	    g.addEdge(4, 0);
	    g.addEdge(4, 1);
	    g.addEdge(2, 3);
	    g.addEdge(3, 1);
	    g.tologicalSort();
	}
}

class KahnsAlgo extends GraphImplement{

	protected int[] inDegree;
	protected int[] visited;

	public KahnsAlgo(int size) {

		super(size);
		inDegree = new int[size];
		visited = new int[size];
	}

	public void addEdge(int vertex, int neighbor) {

		if (vertex <= size && neighbor <= size) {

			connect(vertex, neighbor);
			inDegree[neighbor]++;
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
			inDegree[neighbor]--;
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

	public void tologicalSort() {

		System.out.println("Topological Sorting by Kahn's Algorithm:");

		LinkedList<Node> queue = new LinkedList<Node>();
		for (int i = 0; i < size; i++) {
			if (inDegree[i] == 0) {queue.add(nodeArray.get(i));}
		}

		while (!queue.isEmpty()) {

			Node node = queue.removeFirst();
			System.out.print(node.vertex + " ");
			visited[node.vertex]++;
			node = node.next;
			while (node != null) {

				inDegree[node.vertex]--;
				if (inDegree[node.vertex] == 0) {queue.add(nodeArray.get(node.vertex));}
				node = node.next;
			}
		}

		System.out.println();
	}
}