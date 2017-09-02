import java.util.*;

class FractionalKnapsack {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<Float> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			float maxWeight = input.nextFloat();
			float resultValue = 0f;
			PriorityQueue<Node> heap = new PriorityQueue<>(size, new Check());
			for (int i = 0; i < size; i++) {
				float value = input.nextFloat();
				float weight = input.nextFloat();
				float valuePerWeight = value/weight;
				heap.add(new Node(valuePerWeight, value, weight));
			}
			while (maxWeight > 0 && heap.size() > 0) {
				Node node = heap.remove();
				if (node.weight >= maxWeight) {
					resultValue += maxWeight*node.valuePerWeight;
					maxWeight = 0;
				} else {
					resultValue += node.value;
					maxWeight -= node.weight;
				}
			}
			resultArr.add(resultValue);
		}
		for (int k = 0; k < n; k++) {System.out.printf("%.2f\n", resultArr.get(k));}
	}
}

class Check implements Comparator<Node> {
	public int compare(Node node1, Node node2) {
		if (node1.valuePerWeight > node2.valuePerWeight) {return -1;}
		else return 1;
	}
}

class Node {
	float valuePerWeight;
	float value;
	float weight;

	public Node(float valuePerWeight, float value, float weight) {
		this.valuePerWeight = valuePerWeight;
		this.value = value;
		this.weight = weight;
	}
}