
public class TNode<T extends Comparable<T>> {
	private T data;
	
	private TNode<T> left;
	private TNode<T> right;

	public TNode() {

		this.left = this.right = null;
	}

	public TNode(T data) {

		this.data = data;
	}

	public TNode getLeft() {
		return left;
	}

	public void setLeft(TNode left) {
		this.left = left;
	}

	public TNode getRight() {
		return right;
	}

	public void setRight(TNode right) {
		this.right = right;
	}

	public boolean isLeaf() {
		return (left == null && right == null);
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}


	public String toString() {
	    String dataString = (data == null) ? "null" : data.toString();
	    String leftString = (left == null) ? "null" : left.toString();
	    String rightString = (right == null) ? "null" : right.toString();

	    return "TNode [data=" + dataString + ", left=" + leftString + ", right=" + rightString + "]";
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
