
public class DoubleLinkedList<T extends Comparable<T>> {

	private DNode<T> head;

	public DNode<T> getHead() {
		return head;
	}

	public void setHead(DNode<T> head) {
		this.head = head;
	}

	// -------------------------------------------------------------------------

	public void addSorted(T data) {
		DNode<T> newNode = new DNode<T>(data);
		if (getHead() == null) {
			setHead(newNode);
			newNode.setNext(getHead());
			newNode.setPrev(getHead());
		} else {
			DNode<T> curr = getHead();
			while (curr.getNext() != getHead() && data.compareTo(curr.getData()) <= 0) {
				curr = curr.getNext();
			}

			newNode.setNext(curr);
			newNode.setPrev(curr.getPrev());
			curr.getPrev().setNext(newNode);
			curr.setPrev(newNode);

			if (data.compareTo(getHead().getData()) < 0) {

				setHead(newNode);
			}
		}
	}

	// -------------------------------------------------------------------------

	public void travers() {

		System.out.print(" Head --> ");
		DNode<T> curr = getHead();

		if (curr != null) {

			do {

				System.out.print(curr + " --> ");
				curr = curr.getNext();

			} while (curr != getHead());

		} else {
			System.out.println("Empty List !!");
		}

	}

	// -------------------------------------------------------------------------

	public void insertAtFirst(T data) {
		DNode<T> newNode = new DNode<T>(data);

		if (getHead() == null) {
			setHead(newNode);
			newNode.setNext(getHead());
			newNode.setPrev(getHead());
		} else {
			newNode.setNext(getHead());
			getHead().getPrev().setNext(newNode);
			newNode.setPrev(getHead().getPrev());
			getHead().setPrev(newNode);
			setHead(newNode);
		}

	}

	// -------------------------------------------------------------------------

	public void insertAtLast(T data) {
		DNode<T> newNode = new DNode<>(data);

		if (getHead() == null) {
			setHead(newNode);
			newNode.setNext(getHead());
			newNode.setPrev(getHead());
		} else {
			DNode<T> lastNode = getHead().getPrev();
			lastNode.setNext(newNode);
			newNode.setPrev(lastNode);
			newNode.setNext(getHead());
			getHead().setPrev(newNode);
		}

	}

	// -------------------------------------------------------------------------

	public DNode<T> search(T Data) {
		if (getHead() == null) {
			return null;
		}

		DNode<T> curr = getHead();

		do {
			if (curr.getData().equals(Data)) {
				return curr;
			}

			curr = curr.getNext();
		} while (curr != getHead());

		return null;

	}

	// -------------------------------------------------------------------------

	public void delete(T Data) {
		if (getHead() == null) {
			return;
		}
		DNode<T> curr = getHead();
		DNode<T> prev = null;

		do {
			if (curr.getData().equals(Data)) {
				break;
			}
			prev = curr;
			curr = curr.getNext();
		} while (curr != getHead());

		if (curr == getHead()) {
			if (curr.getNext() == getHead()) {
				setHead(null);
			} else {
				prev = getHead().getPrev();
				setHead(getHead().getNext());
				prev.setNext(getHead());
				getHead().setPrev(prev);
			}
		} else {
			prev.setNext(curr.getNext());
			curr.getNext().setPrev(prev);
		}

	}

	// -------------------------------------------------------------------------

	public void deleteDuplication() {
		if (getHead() == null || getHead().getNext() == null) {
			return;
		}

		DNode<T> curr = getHead();
		do {
			DNode<T> next = curr.getNext();

			if (next != getHead() && curr.getData().equals(next.getData())) {
				curr.setNext(next.getNext());
				if (next.getNext() != null) {
					next.getNext().setPrev(curr);
				}

				if (next == getHead()) {
					setHead(curr.getNext());
				}
			} else {
				curr = curr.getNext();
			}

		} while (curr != getHead());

	}

	// -------------------------------------------------------------------------

	boolean isEmpty() {

		return head == null;
	}
	// -------------------------------------------------------------------------

	public int size() {
		if (!isEmpty()) {

			int count = 0;
			DNode<T> curr = head;
			do {
				count++;
				curr = curr.getNext();
			} while (curr != head);
			return count;
		} else {
			return 0;
		}
	}

	// -------------------------------------------------------------------------

}
