
public class LinkedList<T extends Comparable<T>> {

	Node<T> head;

	public LinkedList() {
		Node<T> dummy = new Node(null);
		head = dummy;
	}

	public T deleteAtHead() {

		if (head.next != null) {
			T toto = head.next.data;
			head.next = head.next.next;
			return toto;
		}

		return null;
	}

	public void insertAtHead(T data) {
		Node<T> newNode = new Node<>(data);
		newNode.next = head.next;
		head.next = newNode;
	}

	public String toString() {
		String s = "";
		Node<T> current = head.next;

		while (current != null) {

			s += current + " ";
			current = current.next;
		}

		return s;
	}

	public void traverse() {
		Node<T> current = head.next;

		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
		System.out.println();

	}

	public int length() {
		int count = 0;
		Node<T> current = head.next;

		while (current != null) {
			count++;
			current = current.next;
		}

		return count;
	}

	public void insertSorted(T data) {
		Node<T> newNode = new Node<T>(data);

		Node<T> curr = head.next;
		Node<T> prev = head;

		while (curr != null && data.compareTo(curr.data) > 0) {
			prev = curr;
			curr = curr.next;
		}
		prev.next = newNode;
		newNode.next = curr;
	}
// deleteSorted
	public void delete(T data) {

		Node<T> curr = head.next;
		Node<T> prev = head;

		while (curr != null && data.compareTo(curr.data) != 0) {
			prev = curr;
			curr = curr.next;
		}

		if (curr != null) {
			prev.next = curr.next;
		}
	}

	public boolean search(T data) {
		Node<T> curr = head.next;

		while (curr != null) {
			if (data.equals(curr.data)) {
				return true;
			}
			curr = curr.next;
		}
		return false;
	}

	public boolean recursiveSearch(T data) {
		return recursiveSearch(data, head.next);
	}

	private boolean recursiveSearch(T data, Node<T> current) {
		if (current == null) {
			return false;
		}

		if (data.equals(current.data)) {
			return true;
		}

		return recursiveSearch(data, current.next);
	}

	public void reverseData() {
		if (head.next == null || head.next.next == null) {
			return;
		}

		Node<T> current = head.next;
		Node<T> prev = null;
		Node<T> nextNode;

		while (current != null) {
			nextNode = current.next;
			current.next = prev;
			prev = current;
			current = nextNode;
		}

		head.next = prev;

		current = head.next;
		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
	}

}
