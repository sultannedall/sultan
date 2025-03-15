
public class LinkedList<T extends Comparable<T>> {

	private Node<T> head;

	public LinkedList() {
		Node dummy = new Node(null);
		head = dummy;
	}

	public void inserAtHead(T data) {
		Node node = new Node(data);
		node.setNext(this.head.getNext());
		this.head.setNext(node);

	}

	public void travers() {

		Node curr = this.head.getNext();
		while (curr != null) {
			System.out.print(curr.getData()  + "  ");
			curr = curr.getNext();
		}
	}

	public int length() {
		int count = 0;
		Node<T> curr = head.getNext();
		while (curr != null) {
			count++;

			curr = curr.getNext();
		}
		return count;
	}

	private int lengthByRecursion(Node<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + lengthByRecursion(node.getNext());
	}

	public int lengthHelper() {
		return lengthByRecursion(head.getNext());
	}

	public void insertSorted(T data) {
		Node<T> newNode = new Node<>(data);
		Node<T> curr = head.getNext();
		Node<T> prev = head;

		while ((curr != null) && (newNode.getData().compareTo(curr.getData())) > 0) {
			prev = curr;
			curr = curr.getNext();
		}

		newNode.setNext(curr);
		prev.setNext(newNode);
	}

	public void delete(T data) {

		Node<T> curr = head.getNext();
		Node<T> prev = head;
		while ((curr != null) && data.compareTo(curr.getData()) > 0) {
			prev = curr;
			curr = curr.getNext();
		}
		prev.setNext(curr.getNext());
	}

	public T search(T data) {
		Node<T> curr = this.head.getNext();

		while (curr != null && curr.getData().compareTo(data) <= 0) {
			if (curr.getData().equals(data))
				return curr.getData();
			curr = curr.getNext();
		}
		return null;
	}

	public T SearchByRecursion(Node<T> curr, T data) {
		if (curr == null) {
			return null;
		}
		if (curr.getData().equals(data)) {
			return curr.getData();
		}
		if (curr.getData().compareTo(data) > 0) {
			return null;
		}
		return SearchByRecursion(curr.getNext(), data);
	}

	public T SearchHelper(T data) {
		return SearchByRecursion(head.getNext(), data);
	}

	Node reverse() {

		Node<T> curr = head.getNext();
		Node<T> prev = null;
		Node<T> next;

		while (curr != null) {
			next = curr.getNext();
			curr.setNext(prev);
			prev = curr;
			curr = next;
		}
		return prev;
	}

	public Node reverseRecursive(Node head) {
		if (head == null || head.getNext() == null)
			return head;

		Node rem = reverseRecursive(head.getNext());
		head.getNext().next = head;
		head.setNext(null);
		return rem;
	}

}
