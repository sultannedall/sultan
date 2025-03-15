
public class Cursor<T extends Comparable<T>> {

	private Node<T>[] cursorArray;

	public Cursor(int size) {
		super();
		this.cursorArray = new Node[size];
		initialization();
	}

	public void initialization() {
		for (int i = 0; i < cursorArray.length - 1; i++)
			cursorArray[i] = new Node<>(null, i + 1);
		cursorArray[cursorArray.length - 1] = new Node<>(null, 0);
	}

	public int malloc() {
		int p = cursorArray[0].getNext();
		cursorArray[0].setNext(cursorArray[p].getNext());
		return p;
	}

	public void free(int p) {
		cursorArray[p] = new Node<>(null, cursorArray[0].getNext());
		cursorArray[0].setNext(p);
	}

	public boolean isNull(int l) {
		return cursorArray[l] == null;
	}

	public boolean isEmpty(int l) {
		return cursorArray[l].getNext() == 0;
	}

	public boolean isLast(int p) {
		return cursorArray[p].getNext() == 0;
	}

	public int createList() {
		int l = malloc();
		if (l == 0)
			System.out.println("Error: Out of space!!!");
		else
			cursorArray[l] = new Node("-", 0);
		return l;
	}

	public void insertAtHead(T data, int l) {
		if (isNull(l)) // list not created
			return;
		int p = malloc();
		if (p != 0) {
			cursorArray[p] = new Node<>(data, cursorArray[l].getNext());
			cursorArray[l].setNext(p);
		} else
			System.out.println("Error: Out of space!!!");
	}

	public void traversList(int l) {
		System.out.print("list_" + l + " --> ");
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].getNext();
			System.out.print(cursorArray[l] + " --> ");
		}
		System.out.println("null");
	}

	public int find(T data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].getNext();
			if (cursorArray[l].getData().equals(data))
				return l;
		}
		return -1; // not found
	}

	public int findPrevious(T data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			if (cursorArray[cursorArray[l].getNext()].getData().equals(data))
				return l;
			l = cursorArray[l].getNext();
		}
		return -1; // not found
	}

	public Node<T> delete(T data, int l) {
		int p = findPrevious(data, l);
		if (p != -1) {
			int c = cursorArray[p].getNext();
			Node<T> temp = cursorArray[c];
			cursorArray[p].setNext(temp.getNext());
			free(c);
		}
		return null;
	}

	public void insertLast(T data, int l) {
		if (isNull(l)) // list not created
			return;
		int p = malloc();
		if (p != 0) {
			cursorArray[p] = new Node<>(data, 0);
			while (!isLast(l)) // cursorArray[i].getNext() != 0
				l = cursorArray[l].getNext();
			cursorArray[l].setNext(p);
		} else
			System.out.println("Error: Out of space!!!");
	}

	public int length(int l, int count) {
		if (isNull(l))
			return 0;

		if (isLast(l))
			return count;

		return length(cursorArray[l].getNext(), ++count);
	}

	public void traverseListRec(int l) {
		System.out.print("list_" + l + " --> ");
		traverseListR(l);
		System.out.println("null");
	}

	public void traverseListR(int l) {
		if (!isNull(l) && !isLast(l)) {
			System.out.print(cursorArray[cursorArray[l].getNext()] + " --> ");
			traverseListR(cursorArray[l].getNext());
		}
	}

	public Node<T> deleteFromHead(int l) {
		if (!isNull(l) && !isEmpty(l)) {
			int p = cursorArray[l].getNext();
			cursorArray[l].setNext(cursorArray[p].getNext());
			free(p);
		}
		return null;
	}

	public void insertSorted(T data, int l) {
		if (isNull(l))
			return;

		if (isEmpty(l))
			insertAtHead(data, l);
		else {
			int prev = l;
			int curr = cursorArray[l].getNext();
			while (!isLast(curr) && cursorArray[curr].getData().compareTo(data) < 0) {
				prev = curr;
				curr = cursorArray[curr].getNext();
			}

			if (isLast(curr))
				if (cursorArray[curr].getData().compareTo(data) < 0) {
					prev = curr;
					curr = 0;
				}

			int p = malloc();
			if (p != 0) {
				cursorArray[p] = new Node<>(data, curr);
				cursorArray[prev].setNext(p);
			}
		}
	}
}