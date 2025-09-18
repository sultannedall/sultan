package sulran.algo3;
public class CityInfoDoublyLinkedList {
    private CityInfoNode head;

    public CityInfoDoublyLinkedList() {
        head = null;
    }

    public void append(CityInfo info) {
        CityInfoNode newNode = new CityInfoNode(info);
        if (head == null) {
            head = newNode;
        } else {
            CityInfoNode current = head;
            while (current.next != null)
                current = current.next;
            current.next = newNode;
            newNode.prev = current;
        }
    }

    public CityInfoNode find(int cityId) {
        CityInfoNode current = head;
        while (current != null) {
            if (current.data.cityId == cityId)
                return current;
            current = current.next;
        }
        return null;
    }

    public CityInfoNode getMinUnvisited() {
        CityInfoNode current = head;
        CityInfoNode minNode = null;
        double minDistance = Double.POSITIVE_INFINITY;

        while (current != null) {
            if (!current.data.visited && current.data.distance < minDistance) {
                minDistance = current.data.distance;
                minNode = current;
            }
            current = current.next;
        }

        return minNode;
    }
}

