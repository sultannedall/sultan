
public class LStack<T extends Comparable<T>> implements Stackable<T> {

	LinkedList<T> list = new LinkedList<>();

	@Override
	public boolean isEmpty() {
		return list.head.next == null;
	}

	// -----------------------------------------------------------

	@Override
	public T peek() {
		if (!isEmpty()) {
			return list.head.next.getData();
		}
		return null;
	}

	// -----------------------------------------------------------

	@Override
	public void push(T data) {
		list.insertAtHead(data);
	}

	// -----------------------------------------------------------

	public T pop() {
		if (!isEmpty()) {
			return list.deleteAtHead();

		}
		return null;

	}

	// -----------------------------------------------------------

	@Override
	public void clear() {
		list = new LinkedList<>();
	}

	// -----------------------------------------------------------

}
