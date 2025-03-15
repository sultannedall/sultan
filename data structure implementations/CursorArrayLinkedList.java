
public class CursorArrayLinkedList<T extends Comparable<T>> {

	CursorArrayNode<T>[] cursorArray = new CursorArrayNode[11];

	public CursorArrayLinkedList(CursorArrayNode<T>[] cursorArray) {
		this.cursorArray = cursorArray;
	}

	public CursorArrayLinkedList() {
		initialization();
	}

	// -----------------------------------------------------------

	public int initialization() {
		for (int i = 0; i < cursorArray.length - 1; i++)
			cursorArray[i] = new CursorArrayNode<>(null, i + 1);
		cursorArray[cursorArray.length - 1] = new CursorArrayNode<>(null, 0);
		return 0;
	}

	// -----------------------------------------------------------

	public int malloc() {
		int p = cursorArray[0].next;
		cursorArray[0].next = cursorArray[p].next;
		return p;
	}

	// -----------------------------------------------------------

	public void free(int p) {
		cursorArray[p] = new CursorArrayNode(null, cursorArray[0].next);
		cursorArray[0].next = p;
	}

	// -----------------------------------------------------------

	public boolean isNull(int l) {
		return cursorArray[l] == null;
	}

	// -----------------------------------------------------------

	public boolean isEmpty(int l) {
		return cursorArray[l].next == 0;
	}

	// -----------------------------------------------------------

	public boolean isLast(int p) {
		return cursorArray[p].next == 0;
	}

	// -----------------------------------------------------------

	public int createList() {
		int l = malloc();
		if (l == 0)
			System.out.println("Error: Out of space!!!");
		else
			cursorArray[l] = new CursorArrayNode("-", 0);
		return l;
	}

	// -----------------------------------------------------------

	public void insertAtHead(T data, int l) {
		if (isNull(l)) // list not created
			return;
		int p = malloc();
		if (p != 0) {
			cursorArray[p] = new CursorArrayNode(data, cursorArray[l].next);
			cursorArray[l].next = p;
		} else
			System.out.println("Error: Out of space!!!");
	}

	// -----------------------------------------------------------

	public void insertAtTail(T data, int l) {
		if (isNull(l)) { // list not created
			return;
		}

		int p = malloc();

		int tail = l;
		if (p != 0) {
			while (!isLast(l)) {
				l = cursorArray[l].getNext();
			}
			cursorArray[p] = new CursorArrayNode<>(data, 0);
			cursorArray[l].setNext(p);
		}

		else {
			System.out.println("Error: Out of space!!!");
		}
	}

	// -----------------------------------------------------------

	public void traversListItiration(int l) {
		System.out.print("list " + l + " --> ");
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].next;
			System.out.print(cursorArray[l] + " --> ");
		}
		System.out.println("null");
	}

	// -----------------------------------------------------------

	public void traverseListRecursion(int l) {

		if (isNull(l)) {
			System.out.println("null");

			return;
		}

		System.out.print(cursorArray[l] + " --> ");
		if (!isEmpty(l)) {

			traverseListRecursion(cursorArray[l].next);
		}
	}

	public void traverseListRecursionHelperMethod(int l) {

		traverseListRecursion(cursorArray[l].getNext());

	}

	// -----------------------------------------------------------

	public T deleteAtHead(int l) {

		if (!isEmpty(l)) {
			int c = cursorArray[l].getNext();
			T temp = cursorArray[c].getData();
			cursorArray[l].setNext(cursorArray[c].getNext());
			free(c);
			return temp;
		}
		return null;
	}

	// -----------------------------------------------------------

	public int length(int i) {
		if (isEmpty(i)) {
			return 0;
		}
		return 1 + length(cursorArray[i].next);
	}

	// -----------------------------------------------------------

	public int findPrevious(T data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			if (cursorArray[cursorArray[l].next].data.equals(data))
				return l;
			l = cursorArray[l].next;
		}
		return -1;
	}

	// -----------------------------------------------------------
	public void insertSorted(int l, T data) {
		if (isNull(l) == true) {
			return;
		} else {
			int p = malloc();
			if (p != 0) {
				int pre = l, curr = l;
				while (!isNull(curr) && !isLast(curr)) {
					pre = curr;
					curr = cursorArray[curr].getNext();
					if (cursorArray[curr].getData().compareTo(data) >= 0) {
						break;
					}
				}
				if (curr == l) {// insert at head
					cursorArray[p] = new CursorArrayNode(data, cursorArray[l].getNext());
					cursorArray[l].setNext(p);
				} else if (cursorArray[curr].getData().compareTo(data) < 0) {// insert at tail
					cursorArray[p] = new CursorArrayNode(data, 0);
					cursorArray[curr].setNext(p);
				} else {// data between
					cursorArray[p] = new CursorArrayNode(data, curr);
					cursorArray[pre].setNext(p);
				}
			} else {
				System.out.println("out of space");
			}
		}
	}

	// -----------------------------------------------------------

	public T getFirstElement(int l) {

		if (!isEmpty(l))
			return cursorArray[cursorArray[l].next].data;

		return null;

	}

	// -----------------------------------------------------------

}
