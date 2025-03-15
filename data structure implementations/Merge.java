
public class Merge {

	public newList merge(newList list1, newList list2) {
		newList dummy = new newList(0);
		newList curr = dummy;

		while (list1 != null && list2 != null) {
			if (list1.data < list2.data) {
				curr.next = list1;
				list1 = list1.next;
			} else {
				curr.next = list2;
				list2 = list2.next;
			}
			curr = curr.next;
		}

		if (list1 != null) {
			curr.next = list1;
		} else {
			curr.next = list2;
		}

		return dummy.next;
	}

}
