
public class TestLinkedList {

	public static void main(String[] args) {
		CDLinkedList<Integer> list = new CDLinkedList<Integer>();

//		list.insertAtHead(50);
//		list.insertAtHead(60);
//		list.insertAtHead(40);
//		list.insertAtHead(70);
//		list.insertAtHead(80);
//		list.insertAtHead(90);
//		list.insertAtHead(20);

		System.out.println(" List Befor Sorted:");
		list.insertAtLast(40);
		list.insertAtLast(80);
		list.insertAtLast(60);
		list.insertAtLast(50);
		list.insertAtLast(100);
		list.insertAtLast(90);
		list.insertAtLast(60);

		list.travers();

		System.out.println();
		System.out.println("-----------------------------------------------");

		RadixSort.radixsort(list, list.size());

		System.out.println(" List After Sorted:");
		RadixSort.print(list);

		System.out.println();
		System.out.println("-----------------------------------------------");

		System.out.println(" Search Method:");
		list.search(50);
		System.out.println(" Node " + list.search(50) + " exists");

		System.out.println("-----------------------------------------------");

		System.out.println(" Delete Method:");
		list.delete(50);
		list.travers();

		System.out.println();
		System.out.println("-----------------------------------------------");

		System.out.println(" Delete Duplication Method:");
		list.deleteDuplication();
		list.travers();

		System.out.println();
		System.out.println("-----------------------------------------------");

		System.out.println();

	}

}
