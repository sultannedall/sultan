
public class RadixSort {

	// -------------------------------------------------------------------------

	private static void counterSorted(CDLinkedList<Integer> list, int n, int num) {

		int[] sorted = new int[n];

		int[] counter = new int[10];

		DNode<Integer> curr = list.getHead();

		do {
			int digit = (curr.getData() / num) % 10;
			counter[digit]++;
			curr = curr.getNext();
		} while (curr != list.getHead());

		for (int i = 1; i < 10; i++) {
			counter[i] += counter[i - 1];
		}

		curr = list.getHead();
		do {
			int digit = (curr.getData() / num) % 10;
			sorted[counter[digit] - 1] = curr.getData();
			counter[digit]--;
			curr = curr.getNext();

		} while (curr != list.getHead());

		curr = list.getHead();
		for (int i = 0; i < n; i++) {
			curr.setData(sorted[i]);
			curr = curr.getNext();
		}
	}

	// -------------------------------------------------------------------------

	private static int getMaxDigits(int d) {
		if (d <= 0) {
			return 1;
		}

		return (int) Math.log10(d) + 1;
	}

	// -------------------------------------------------------------------------

	public static void radixsort(CDLinkedList<Integer> list, int num) {
		int max = getMaxValue(list);

		for (int i = 1; max / i > 0; i *= 10) {
			counterSorted(list, num, i);
		}
		int maxDigits = getMaxDigits(max);
		counterSorted(list, num, (int) Math.pow(10, maxDigits - 1));
	}

	// -------------------------------------------------------------------------

	private static int getMaxValue(CDLinkedList<Integer> list) {
		if (list == null || list.isEmpty()) {
			return 0;
		}

		int max = Integer.MIN_VALUE;
		DNode<Integer> current = list.getHead();
		do {
			int value = current.getData();
			if (value > max) {
				max = value;
			}
			current = current.getNext();
		} while (current != list.getHead());

		return max;
	}

	// -------------------------------------------------------------------------

	static void print(CDLinkedList<Integer> list) {
		list.travers();
	}

	// -------------------------------------------------------------------------

}
