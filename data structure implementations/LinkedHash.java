
public class LinkedHash<T extends Comparable<T>> {

	int m;
	LinkedList<T> table[];

	public LinkedHash(int capcity) {
		this.m = capcity / 5;
		this.table = new LinkedList[m];
		for (int i = 0; i < m; i++) {
			table[i] = new LinkedList<>();
		}
	}

	public void insert(T data) {
		int index = data.hashCode() % m;
		table[index].insertAtHead(data);
	}
	
	public boolean find (T data) {
		int key = data.hashCode();
		int index =  key% m;
		return table [index].search(data);
		
	}
	public void delete (T data) {
		int key = data.hashCode();
		int index =  key% m;
	    table [index].delete(data);;
		
	}
	

	public static void main(String[] args) {
		LinkedHash<Integer> linkedHash = new LinkedHash<>(50);

		linkedHash.insert(55);
		linkedHash.insert(23);
		linkedHash.insert(34);
		linkedHash.insert(17);
		linkedHash.insert(40);
		linkedHash.delete(45);
		
		

		for (int i = 0; i < linkedHash.table.length; i++) {
			System.out.print(i + ": ");
			linkedHash.table[i].traverse();
		}
		System.out.println (linkedHash.find(23));

	}
	public String toString() {
		String s = "index\tdata";
		for (int i = 0; i < m; i++) {
			s+= "\n" +i+ table[i] ;
		}
		return super.toString();
		
	}

}
