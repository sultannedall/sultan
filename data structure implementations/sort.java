import java.util.ArrayList;
import java.util.List;

public class sort {

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

	public newList sort(List<newList> lists) {
		if (lists == null || lists.isEmpty()) {
			return null;
		}

		while (lists.size() > 1) {
			List<newList> newLists = new ArrayList<>();
			for (int i = 0; i < lists.size(); i += 2) {
				if (i + 1 < lists.size()) {
					newLists.add(merge(lists.get(i), lists.get(i + 1)));
				} else {
					newLists.add(lists.get(i));
				}
			}
			lists = newLists;
		}

		return lists.get(0);
	}

}
