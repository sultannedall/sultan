
public class TestQueueArray {

	public static void main(String[] args) {

		QueueArray<String> Queue1 = new QueueArray<>(5);

		Queue1.enqueue("50");
		Queue1.enqueue("55");
		Queue1.enqueue("60");
		Queue1.enqueue("70");

		System.out.println(Queue1.dequeue());

		Queue1.getFront();
	}

	// -----------------------------------------------------------

}
