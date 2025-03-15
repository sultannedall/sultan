import java.util.ArrayList;
import java.util.List;

public class TestLinkedList {
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();

		System.out.println("Sorted List: ");
		list.insertSorted("50");
		list.insertSorted("80");
		list.insertSorted("90");
		list.insertSorted("60");
		list.insertSorted("70");
		list.travers();

	}
}
