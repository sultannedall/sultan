package Lab7;

public class BST<T extends Comparable<T>> {
	TNode<T> root;

	public void insert(T data) {
		if (root == null)
			root = new TNode<T>(data);
		else
			insert(data, root);
	}

	public void insert(T data, TNode<T> node) {
		if (data.compareTo((T) node.data) >= 0) { // insert into right subtree
			if (!node.hasRight())
				node.right = new TNode<T>(data);
			else
				insert(data, node.right);
		} else { // insert into left subtree
			if (!node.hasLeft())
				node.left = new TNode<T>(data);
			else
				insert(data, node.left);
		}
	}

	public void traverseInOrder() {
		traverseInOrder(root);
	}

	private void traverseInOrder(TNode<T> node) {
		if (node != null) {
			if (node.left != null)
				traverseInOrder(node.left);
			System.out.print(node + " ");
			if (node.right != null)
				traverseInOrder(node.right);
		}
	}

	public int size() {
		return size(root);
	}

	private int size(TNode<T> node) {
		if (node != null) {
			return 1 + size(node.getLeft()) + size(node.getRight());
		} else {
			return 0;
		}
	}

	public int CountParents() {
		return CountParents(root);
	}

	private int CountParents(TNode<T> node) {
		if (node == null || node.isLeaf()) {
			return 0;
		}
		return 1 + CountParents(node.getLeft()) + CountParents(node.getRight());

	}

	public boolean delete(T data) {
		if ((root == null))
			return false; // tree empty

		TNode<T> current = root;
		TNode<T> parent = null;
		boolean isLeftChild = false;

		while (current != null && !current.data.equals(data)) {
			parent = current;
			if (data.compareTo((T) current.data) < 0) {
				current = current.left;
				isLeftChild = true;
			} else {
				current = current.right;
				isLeftChild = false;
			}
		}

		// Case 0:

		if (current == null)
			return false; // node to be deleted not found

		// Case 1:
		if (!current.hasLeft() && !current.hasRight()) {
			if (current == root) {
				root = null;
			} else if (isLeftChild) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		}

		// Case 2-ِA:
		else if (current.hasLeft() && !current.hasRight()) {
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		}

		// Case 2-B:
		else if (!current.hasLeft() && current.hasRight()) {
			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		}

		// Case 3:
		else {
			TNode<T> successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = current.left;
		}
		return true;
	}

	private TNode getSuccessor(TNode node) {
		TNode parentOfSuccessor = node;
		TNode successor = node;
		TNode current = node.right;
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.left;
		}
		if (successor != node.right) { // fix successor connections
			parentOfSuccessor.left = successor.right;
			successor.right = node.right;
		}
		return successor;
	}

	public int height() {
		return height(root);
	}

	public int height(TNode<T> node) {
		if (node == null)
			return 0;
		if (node.isLeaf())
			return 1;
		int left = 0;
		int right = 0;
		if (node.hasLeft())
			left = height(node.left);
		if (node.hasRight())
			right = height(node.right);
		return (left > right) ? (left + 1) : (right + 1);
	}

	void traverseLevel(TNode<T> node, int data) {
		if (node == null)
			return;
		if (data == 1) {
			System.out.print(node.data + " ");
		} else if (data > 1) {
			traverseLevel(node.left, data - 1);
			traverseLevel(node.right, data - 1);
		}
	}

	void traverseOrder() {
		int h = height(root);
		int i;
		for (i = 1; i <= h; i++) {
			traverseLevel(root, i);
			System.out.print(System.lineSeparator());
		}
	}

	public boolean isCompleteTree(TNode<T> root) {
		int count = size(root);
		return isComplete(root, 0, count - 1);
	}

	private boolean isComplete(TNode<T> node, int index, int numOfNodes) {
		if (node == null) {
			return index >= numOfNodes;
		}

		if (index > numOfNodes) {
			return false;
		}

		boolean left = isComplete(node.left, 2 * index + 1, numOfNodes);

		boolean right = isComplete(node.right, 2 * index + 2, numOfNodes);

		return (left && right);

	}

	public boolean isFull() {
		return isFull(root);
	}

	private boolean isFull(TNode<T> node) {

		if (node != null) {
			if (node.isLeaf()) {
				return true;
			} else if (node.hasLeft() && node.hasRight()) {
				return (isFull(node.getLeft()) && isFull(node.getRight()));
			}
			return true;
		}
		return false;

	}

	public void preorder() {
		preorder(root);
	}

	private void preorder(TNode<T> node) {
		if (node != null) {
			System.out.print(node.getData() + " ");
			preorder(node.getLeft());
			preorder(node.getRight());
		}
	}

	public void postorder() {
		postorder(root);
	}

	private void postorder(TNode<T> node) {
		if (node != null) {
			postorder(node.getLeft());
			postorder(node.getRight());
			System.out.print(node.getData() + " ");
		}
	}

	public boolean search(T data) {
		return search(root, data);
	}

	private boolean search(TNode<T> node, T data) {
		boolean found = false;
		while ((node != null) && !found) {
			T item = node.getData();
			if (data.compareTo(item) < 0)
				node = node.getLeft();
			else if (data.compareTo(item) > 0)
				node = node.getRight();
			else {
				found = true;
				break;
			}
			found = search(node, data);
		}
		return found;
	}

	public static void main(String[] args) {
		BST<Integer> tree = new BST<>();
		tree.insert(55);
		tree.insert(40);
		tree.insert(60);
		tree.insert(75);
		tree.insert(50);

		tree.traverseInOrder();

		System.out.println("\nSize of the tree is : " + tree.size());
		System.out.println("\n Parents : " + tree.CountParents());

		System.out.println("\n is the tree full? : " + tree.isFull());

	}
}
