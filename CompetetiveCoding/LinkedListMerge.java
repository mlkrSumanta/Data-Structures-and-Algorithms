class LinkedListMerge {
    public static void main(String[] args) {
        Node node = new Node(40, null);
        Node node3 = new Node(15, node);
        Node node4 = new Node(10, node3);
        Node node1 = new Node(5, node4);
        node3 = new Node(20, null);
        node4 = new Node(3, node3);
        Node node2 = new Node(2, node4);
        GfG g = new GfG();
        Node res = g.mergeResult(node1, node2);
        while (res != null) {System.out.print(res.data + " ");res = res.next;}
        System.out.println();
    }
}

class Node {
	int data;
	Node next;
	
	Node(int d, Node n) {
		data = d;
		next = n;
	}
} 

class GfG {
    Node mergeResult(Node node1, Node node2) {
        Node head;
        if (node1.data > node2.data) {
            head = new Node(node2.data, null);
            node2 = node2.next;
        } else {
            head = new Node(node1.data, null);
            node1 = node1.next;
        }        
        while (node1 != null || node2 != null) {
            if (node1 == null) {
                head = merge(node2, head);
                node2 = node2.next;
            } else if (node2 == null) {
                head = merge(node1, head);
                node1 = node1.next;                
            } else {
                if (node1.data > node2.data) {
                    head = merge(node2, head);
                    node2 = node2.next;
                } else {
                    head = merge(node1, head);
                    node1 = node1.next;
                }
            }
        }
        return head;
    }
    
    Node merge(Node node, Node head) {
        Node temp = head;
        head = new Node(node.data, null);
        head.next = temp;
        return head;
    }
}