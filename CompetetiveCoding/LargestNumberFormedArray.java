import java.util.*;

class LargestNumberFormedArray {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<String> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			int size = input.nextInt();
			String result = "";
			String[] array = new String[size];
			Node[] nodeArray = new Node[10];
			for (int i = 0; i < size; i++) {
				array[i] = input.next();
				String temp = array[i];
				Node head = nodeArray[Character.getNumericValue(temp.charAt(0))];
				nodeArray[Character.getNumericValue(temp.charAt(0))] = add(temp, head);
			}
			for (int i = 9; i >= 0; i--) {
				Node node = nodeArray[i];
				while (node != null) {
					result += node.data;
					node = node.next;
				}
			}
			resultArr.add(result);
		}

		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}

	public static Node add(String num, Node head) {
		if (head == null) {head = new Node(num, null); return head;}
		String strOp1 = num + head.data;
		String strOp2 = head.data + num;
		if (strOp2.compareTo(strOp1) <= -1) {
			Node temp = head;
			head = new Node(num, temp);
		} else {
			Node node = head.next;
			head.next = add(num, node);
		}
		return head;
	}
}

class Node {
	String data;
	Node next;

	public Node(String data, Node next) {
		this.data = data;
		this.next = next;
	}
}