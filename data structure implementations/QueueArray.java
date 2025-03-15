
public class QueueArray<T> implements QueueInterface<T> {

	int count = 0;
	int front = -1;
	int back = -1;
	private T[] items;
	int MAX_QUEUE = 11;

	public QueueArray(int size) {

		items = (T[]) new Object[size];
		MAX_QUEUE = size;

	}

	@Override
	public void enqueue(T data) {

		if (count < MAX_QUEUE) {
			back = (++back) % MAX_QUEUE;
			items[back] = data;
			++count;
			if (count == 1) {
				front = back;
			}
		} else {
			System.out.println("Full Queue !!!");
		}

	}

	@Override
	public T dequeue() {
		if (count > 0) {
			T data = items[front];
			front = (++front) % MAX_QUEUE;
			--count;

			if (count == 0) {
				front = back = -1;
				return data;
			}
		}

		System.out.println("Empty Queue !!! ");
		return null;

	}

	@Override
	public T getFront() {
		if (!isEmpty()) {
			return items[front];

		} else
			return null;
	}

	@Override
	public boolean isEmpty() {

		return count == 0;

	}

	@Override
	public void clear() {
		front = back = -1;
		count = 0;

	}

	@Override
	public T pop() {
		if (!isEmpty()) {
			T data = items[back];
			back = (back - 1 + MAX_QUEUE) % MAX_QUEUE;
			--count;

			if (count == 0) {
				front = back = -1;
			}
			return data;

		}

		return null;
	}

}
