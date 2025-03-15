
public class HashNode <T extends Comparable<T>> {

	T data;

	final char E = 'E'; // e: empty

	final char F = 'F'; // f: full

	final char D = 'D'; // d: deleted

	char flag = E;

	public HashNode(T data) {
		this.data = data;
		flag = F;
	}

	public HashNode() {

	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public char getFlag() {
		return flag;
	}

	public void setFlag(char flag) {
		if (flag == D || flag == E || flag == F) {
			this.flag = flag;
		} else {
			System.out.println("Flag has to be F,D,E !!!! ");
		}
	}

	public void setDeleted() {
		flag = D;
	}

	public boolean isFull() {

		return (flag == F);

	}

	public boolean isEmpty() {
		return (flag == E || flag == D);

	}

	@Override
	public String toString() {
		return data + "," + flag;
	}

}
