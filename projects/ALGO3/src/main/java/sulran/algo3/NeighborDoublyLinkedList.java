package sulran.algo3;

public class NeighborDoublyLinkedList {
    private NeighborNode head;
    private NeighborNode tail;

    public NeighborDoublyLinkedList() {
        head = null;
        tail = null;
    }

    public void append(Neighbor neighbor) {
        NeighborNode newNode = new NeighborNode(neighbor);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void printList() {
        NeighborNode current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public NeighborNode getHead() {
        return head;
    }
}

