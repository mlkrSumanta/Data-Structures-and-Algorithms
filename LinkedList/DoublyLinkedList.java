class DoublyLinkedList {
	
	public static void main(String[] args) {

		llImplement<String> dll = new llImplement<String>();
		dll.addFirst("I");
		dll.addFirst("Am");
		dll.addLast("Super");
		dll.addLast("Man");
		dll.iterateForward();
		dll.iterateBackward();
		dll.search("Super");
		dll.deleteElement("Man");
		dll.iterateForward();
		dll.iterateBackward();
	}
}

class llImplement<T> {

	private Node head;
	private Node tail;
	private int size;

	public llImplement() {size = 0;}

	private class Node {

		T element;
		Node next;
		Node prev;

		public Node(T element, Node next, Node prev) {

			this.element = element;
			this.next = next;
			this.prev = prev;
		}
	}

	public int size() {return size;}

	public boolean isEmpty() {return size == 0;}

	public void addFirst(T element) {

		Node tmp = new Node(element, head, null);
		if (head != null) {head.prev = tmp;}
		head = tmp;
		if (tail == null) {tail = tmp;}
		size++;
	}

	public void addLast(T element) {

		Node tmp = new Node(element, null, tail);
		if (tail != null) {tail.next = tmp;}
		tail = tmp;
		if (head == null) {head = tmp;}
		size++;
	}

	public void iterateForward() {

		Node tmp = head;
		while (tmp != null) {
			
			System.out.print(tmp.element + " ");
			tmp = tmp.next;
		}
		System.out.println();
	}

	public void iterateBackward() {

		Node tmp = tail;
		while (tmp != null) {

			System.out.print(tmp.element + " ");
			tmp = tmp.prev;
		}
		System.out.println();
	}

	public void search(T element) {

		Node tmp = head;
		int i = 0;
		while (tmp != null) {
			
			if (tmp.element == element) {
				System.out.println("Element found at " + i + "th index");
			}
			tmp = tmp.next;
			i++;
		}
	}

	public void deleteElement(T element) {

		Node tmp = head;
		while (tmp != null) {
			
			if (tmp.element == element) {
				Node newNext = tmp.next;
				Node newPrev = tmp.prev;
				if (newPrev == null) {
					
					head = newNext;
					newNext.prev = null;
				} else if (newNext == null) {

					tail = newPrev;
					newPrev.next = null;
				} else {

					newPrev.next = newNext;
					newNext.prev = newPrev;
				}
			}
			tmp = tmp.next;
		}	
	}
}