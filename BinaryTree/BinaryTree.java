import java.util.ArrayList;

class BinaryTree {

	public static void main(String[] args) {

		bstImplement bt = new bstImplement();
		bt.add(3);
		bt.add(7);
		bt.add(1);
		bt.add(36);
		bt.add(2);
		bt.inOrderTraverse();
		bt.delete(7);
		bt.inOrderTraverse();
		bt.add(47);
		bt.add(32);
		bt.add(13);
		bt.tobalancedBST();
		bt.inOrderTraverse();
	}
}

class bstImplement {

	private Node root;
	private ArrayList<Integer> sortedArray;

	public bstImplement() {
		root = null;
	}

	public Node getRoot() {
		return root;
	}

	private class Node {

		int element;
		Node left;
		Node right;

		public Node(int element, Node left, Node right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}
	}

	public void add(int element) {

		Node tmp = new Node(element, null, null);
		
		if (root != null) {

			Node temp = root;
			Node tmpRoot = null;
			while (temp != null) {

				tmpRoot = temp;
				if (temp.element > element) {temp = temp.left;}
				else {temp = temp.right;}
			}
			if (tmpRoot.element > element) {tmpRoot.left = tmp;}
			else {tmpRoot.right = tmp;}
		} else {root = tmp;}
	}

	public void delete(int element) {

		Node temp = root;
		while (temp != null) {

			if (temp.element == element) {

				Node leaf = temp;
				while (leaf.left != null) {
					leaf = leaf.left;
				}
				if (leaf != temp) {
					temp.element = leaf.element;
					leaf = null;
				}
				else if (temp.right != null) {
					Node ri = temp.right;
					temp.element = ri.element;
					temp.right = null;
				} else {temp = null;}
				break;
			} 
			else if (temp.element > element) {temp = temp.left;} 
			else {temp = temp.right;}
		}
	}

	public void search(int element) {

	}

	public void inOrderTraverse() {
		
		sortedArray = new ArrayList<Integer>();
		System.out.println();
		traverse(root);
		System.out.println();
	}

	private void traverse(Node link) {

		if (link == null) {return;}
		traverse(link.left);
		System.out.print(link.element + " ");
		sortedArray.add(link.element);
		traverse(link.right);
		return;
	}

	public void tobalancedBST() {

		inOrderTraverse();
		root = null;
		int start = 0;
		int end = sortedArray.size() - 1;
		int mid = (start+end) / 2;
		addToBalancedBST(start, mid, end);
	}

	public void addToBalancedBST(int start, int mid, int end) {

		add(sortedArray.get(mid));
		if (start == mid) {return;}

		int leftMid = (start+mid) / 2;
		int rightMid = (mid+end+1) / 2;
		addToBalancedBST(start, leftMid, mid);
		addToBalancedBST(mid + 1, rightMid, end);
		return;
	}
}
