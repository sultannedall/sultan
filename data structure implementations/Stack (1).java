
public class Stack<T extends Comparable<T>> implements Stackable<T> {

	CursorArrayLinkedList<T> c = new CursorArrayLinkedList<>();
	int top = c.createList();

	// -----------------------------------------------------------

	@Override
	public boolean isEmpty() {
		return c.isEmpty(top);
	}

	// -----------------------------------------------------------

	@Override
	public T peek() {

		return c.getFirstElement(top);
	}

	// -----------------------------------------------------------

	@Override
	public void push(T data) {

		c.insertAtHead(data, top);
	}

	// -----------------------------------------------------------

	@Override
	public T pop() {
		return c.deleteAtHead(top);

	}

	// -----------------------------------------------------------

	@Override
	public void clear() {

		while (!isEmpty())
			pop();
	}

	// -----------------------------------------------------------

}
