
public interface QueueInterface<T> {

	void enqueue(T data);

	T dequeue();

	T getFront();

	boolean isEmpty();

	void clear();

	T pop();
	

}
