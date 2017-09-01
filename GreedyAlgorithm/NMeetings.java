import java.util.*;

class NMeetings {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<String> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			int[] start = new int[size];
			int[] end = new int[size];
			int finishTime = 0;
			String result = "";
			PriorityQueue<Node> heap = new PriorityQueue<>(size, new Check());
			for (int i = 0; i < size; i++) {start[i] = input.nextInt();}
			for (int i = 0; i < size; i++) {end[i] = input.nextInt();}
			for (int i = 0; i < size; i++) {
				Node node = new Node(start[i], end[i], i+1);
				heap.add(node);
			}
			while (heap.size() != 0) {
				Node node = heap.remove();	
				if (node.startTime >= finishTime) {
					result += node.number + " ";
					finishTime = node.endTime;
				}
			}
			resultArr.add(result);
		}

		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}
}

class Node {
	int startTime;
	int endTime;
	int number;

	public Node(int start, int end, int num) {
		startTime = start;
		endTime = end;
		number = num;
	}
}

class Check implements Comparator<Node> {
	public int compare(Node node1, Node node2) {
		if (node1.endTime < node2.endTime) {return -1;}
		else return 1;
	}
}