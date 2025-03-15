
public class TestCursorArray {

	public static void main(String[] args) {

		Cursor<Integer> c = new Cursor<>();
		int l = c.createList();

//		c.insertAtTail(50, l);
//		c.insertAtTail(60, l);
//		c.insertAtTail(70, l);
//		c.insertAtTail(80, l);
//
//		System.out.println("Travers List Itiration:");
//		c.traversListItiration(l);
//		System.out.println("---------------------------------------------");
//
//		System.out.println("Travers List Recursion:");
//		c.traverseListRecursionHelperMethod(l);
//		System.out.println();
//		System.out.println("---------------------------------------------");
//
//		System.out.println("List After Delete At Head:");
//		c.deleteAtHead(l);
//		c.traversListItiration(l);
//		System.out.println("---------------------------------------------");
//
//		System.out.println("Length of List:" + (c.length(l)));
//		System.out.println("---------------------------------------------");

		c.insertSorted(l, 8);
		c.insertSorted(l, 2);
		c.insertSorted(l, 4);
		c.insertSorted(l, 11);
		c.insertSorted(l, 1);
		c.insertSorted(l, 5);

		c.traversListItiration(l);
		
		
		
	}

}
