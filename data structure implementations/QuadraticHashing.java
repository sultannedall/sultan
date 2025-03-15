
public class QuadraticHashing<T extends Comparable<T>> {

	HashNode<T>[] table;
	int m;

	public QuadraticHashing(int n) {
		m = prime(2 * n);
		table = new HashNode[m];
	}

	public int prime(int num) {
		while (!isPrime(++num)) {
		}
		return num;
	}

	private boolean isPrime(int n) {

		if (n <= 1)
			return false;
		for (int i = 2; i < n; i++)
			if (n % i == 0)
				return false;
		return true;
	}

	public void insert(T data) {
		int key = data.hashCode();
		int h0 = key % m;
		int index = h0;
		int i = 0;

		while (table[index] != null && !table[index].isEmpty()) {
			i++;
			index = (h0 + (i * i)) % m;
		}

		table[index] = new HashNode(data);

	}

	public void delete(T data) {
		int key = data.hashCode();
		int h0 = key % m;
		int index = h0;
		int i = 0;

		while (table[index] != null && !table[index].isEmpty()) {
			if (table[index].getData().compareTo(data) == 0) {
				table[index].setDeleted();

				System.out.println("Deleted successfull");

				return;
			}
			i++;
			index = (h0 + (i * i)) % m;
		}

		System.out.println("The data you are trying to delete was not found");
	}

	public boolean search(T data) {
		int key = data.hashCode();
		int h0 = key % m;
		int index = h0;
		int i = 0;

		while (table[index] != null && !table[index].isEmpty()) {
			if (table[index].getData().compareTo(data) == 0) {
				System.out.println("Data found at index " + index);
				return true;
			}
			i++;
			index = (h0 + (i * i)) % m;
		}

		System.out.println("Data not found ");
		return false;
	}

	@Override
	public String toString() {
		String s = "index\tdata" + "\n";
		for (int i = 0; i < table.length; i++) {
			s += i + "\t" + table[i] + "\n";
		}
		return s;
	}

}
