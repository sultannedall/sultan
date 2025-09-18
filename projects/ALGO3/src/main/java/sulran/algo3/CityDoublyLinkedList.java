package sulran.algo3;
public class CityDoublyLinkedList {
    private CityNode head;
    private CityNode tail;

    public CityDoublyLinkedList() {
        head = null;
        tail = null;
    }

    public void append(City city) {
        CityNode newNode = new CityNode(city);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void prepend(int cityID, int x, int y) {
        City newCity = new City(cityID, x, y);
        CityNode newNode = new CityNode(newCity);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public CityNode findById(int id) {
        CityNode current = head;
        while (current != null) {
            if (current.data.getId() == id) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void printAllCities() {
        CityNode current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public CityNode getHead() {
        return head;
    }
}
