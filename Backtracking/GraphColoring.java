import java.util.*;

class GraphColoring {
	
	public static void main(String[] args) {
		
		GraphColor graph = new GraphColor(10);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(1, 4);
		graph.addEdge(1, 8);
		graph.addEdge(2, 6);
		graph.addEdge(2, 7);
		graph.addEdge(3, 5);
		graph.addEdge(3, 9);
		graph.addEdge(4, 5);
		graph.addEdge(4, 7);
		graph.addEdge(5, 6);
		graph.addEdge(6, 8);
		graph.addEdge(7, 9);
		graph.addEdge(8, 9);
		graph.solution();
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

class GraphColor {

	protected int size;
	protected ArrayList<Node> nodeArray;
	public int[] colorResult;

	public GraphColor(int size) {

		this.size = size;
		nodeArray = new ArrayList<Node>(size);
		colorResult = new int[size];

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

	public boolean isSafeColor(int vertex, int color) {

		Node node = nodeArray.get(vertex);
		while (node != null) {
			
			if (colorResult[node.vertex] == color) {
				return false;
			}
			node = node.next;
		}
		return true;
	}

	public boolean isColored(int vertex, int colors) {

		if (vertex == size) {return true;}

		for (int i = 1; i <= colors; i++) {
			if (isSafeColor(vertex, i)) {
				
				colorResult[vertex] = i;
				if (isColored(vertex+1, colors)) {
					return true;
				} else {
					colorResult[vertex] = 0;
				}
			}
		}

		return false;
	}

	public void solution() {

		boolean check = false;
		for (int i = 1; i <= size; i++) {
			
			colorResult[0] = 1;
			if (isColored(1, i)) {
				check = true;
				break;
			}
		}

		if (check) {
			for (int i = 0; i < size; i++) {
				System.out.print(colorResult[i] + " ");
			}
		}
		System.out.println();
	}
}