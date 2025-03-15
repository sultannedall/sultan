
public class TestQueueStack {

	public static void main(String[] args) {

		QueueStack<String> Queue1 = new QueueStack<>();

		Queue1.enqueue("A");
		Queue1.enqueue("B");
		Queue1.enqueue("C");

		System.out.println(Queue1.dequeue());

	}

	// -----------------------------------------------------------

}
