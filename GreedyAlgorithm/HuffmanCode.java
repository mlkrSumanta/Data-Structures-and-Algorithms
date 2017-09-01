import java.util.*;

class HuffmanCode {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		ArrayList<String> resultArr = new ArrayList<>();
		for (int k = 0; k < n; k++) {
			String alphabet = input.next();
			int size = alphabet.length();
			PriorityQueue<TreeNode> heap = new PriorityQueue<>(size, new Check());
			for (int i = 0; i < size; i++) {
				int value = input.nextInt();
				heap.add(new TreeNode(value, null, null));
			}
			while (heap.size() > 1) {
				TreeNode node1 = heap.remove();	
				TreeNode node2 = heap.remove();
				heap.add(new TreeNode(node1.value+node2.value, node1, node2));
			}
			TreeNode node = heap.remove();
			String str = printTree(node, "");
			resultArr.add(str);
		}

		for (int k = 0; k < n; k++) {System.out.println(resultArr.get(k));}
	}

	public static String printTree(TreeNode node, String str) {
		if (node == null) {return str.substring(0, str.length()-1);}
		String left = printTree(node.left, str+String.valueOf(0));
		String right = printTree(node.right, str+String.valueOf(1));
		if (left.equals(right)) {return left;}
		else return (left + " " + right);
	}
}

class Check implements Comparator<TreeNode> {
	public int compare(TreeNode node1, TreeNode node2) {
		if (node1.value < node2.value) {return -1;}
		else return 1;
	}
}

class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;

	public TreeNode(int value, TreeNode left, TreeNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
}