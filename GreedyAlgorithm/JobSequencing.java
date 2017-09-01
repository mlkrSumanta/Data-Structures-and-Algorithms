import java.util.*;

class JobSequencing {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<String> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			int[] start = new int[size];
			int[] end = new int[size];
			int finishTime = 0;
			int result = 0;
			PriorityQueue<Node> heap = new PriorityQueue<>(size, new Check());
			for (int i = 0; i < size; i++) {
				int id = input.nextInt();
				int deadline = input.nextInt();
				int profit = input.nextInt();

				heap.add(new Node(id, deadline, profit));
			}
			while (heap.size() != 0) {
				Node node = heap.remove();	
				if (node.deadline > finishTime) {
					result += node.profit;
					finishTime++;
				}
			}
			String str = String.valueOf(finishTime) + " " + String.valueOf(result);
			resultArr.add(str);
		}

		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}

class Node {
	int id;
	int deadline;
	int profit;

	public Node(int start, int end, int num) {
		id = start;
		deadline = end;
		profit = num;
	}
}

class Check implements Comparator<Node> {
	public int compare(Node node1, Node node2) {
		if (node1.profit < node2.profit) {return 1;}
		else if (node1.profit == node2.profit && node1.deadline < node2.deadline) {return 1;}
		else return -1;
	}
}