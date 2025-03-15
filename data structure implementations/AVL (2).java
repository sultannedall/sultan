
public class AVL<T extends Comparable<T>> {
	TNode<T> root;

	private TNode<T> rotateRightLeft(TNode<T> node) {
		node.setRight(rotateRight(node.getRight()));

		return rotateLeft(node);
	}

	private TNode<T> rotateLeft(TNode<T> node) {
		TNode<T> nodec = node.getRight();
		node.setRight(nodec.getLeft());
		nodec.setLeft(node);
		return nodec;
	}

	private TNode<T> rotateLeftRight(TNode<T> node) {
		node.setLeft(rotateLeft(node.getLeft()));

		return rotateRight(node);
	}

	private TNode<T> rotateRight(TNode<T> node) {
		TNode<T> nodec = node.getLeft();
		node.setLeft(nodec.getRight());
		nodec.setRight(node);

		return nodec;
	}

	private TNode<T> rebalance(TNode<T> nodeN) {
		int diff = getHeightDifference(nodeN);
		if (diff > 1) { // addition was in node's left subtree
			if (getHeightDifference(nodeN.getLeft()) > 0)
				nodeN = rotateRight(nodeN);
			else
				nodeN = rotateLeftRight(nodeN);
		} else if (diff < -1) { // addition was in node's right subtree
			if (getHeightDifference(nodeN.getRight()) < 0)
				nodeN = rotateLeft(nodeN);
			else
				nodeN = rotateRightLeft(nodeN);
		}
		return nodeN;
	}

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(TNode<T> node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.print(node.getData() + " ");
            traverseInOrder(node.getRight());
        }
    }

    public void printTree() {
        System.out.print("Tree nodes (in-order): ");
        traverseInOrder();
        System.out.println();
    }

	private int getHeightDifference(TNode<T> nodeN) {
		if (nodeN == null) {
			return 0;
		} else {
			int leftHeight = getHeight(nodeN.getLeft());
			int rightHeight = getHeight(nodeN.getRight());

			return leftHeight - rightHeight;
		}
	}

	private int getHeight(TNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
		}
	}

	public void insert(T data) {
		if (root == null)
			root = new TNode<>(data);
		else {
			TNode rootNode = root;
			addEntry(data, rootNode);
			root = rebalance(rootNode);
		}
	}

	public void addEntry(T data, TNode<T> rootNode) {
	    assert rootNode != null;

	    if (data == null) {
	        // Handle the case where data is null
	        // You may want to throw an exception or handle it based on your requirements
	        return;
	    }

	    if (rootNode.getData() == null || data.compareTo(rootNode.getData()) < 0) { // right into left subtree
	        if (rootNode.hasLeft()) {
	            TNode<T> leftChild = rootNode.getLeft();
	            addEntry(data, leftChild);
	            rootNode.setLeft(rebalance(leftChild));
	        } else {
	            rootNode.setLeft(new TNode<>(data));
	        }
	    } else { // right into right subtree
	        if (rootNode.hasRight()) {
	            TNode<T> rightChild = rootNode.getRight();
	            addEntry(data, rightChild);
	            rootNode.setRight(rebalance(rightChild));
	        } else {
	            rootNode.setRight(new TNode<>(data));
	        }
	    }
	}

}
