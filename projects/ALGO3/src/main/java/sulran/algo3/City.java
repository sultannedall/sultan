package sulran.algo3;

public class City {
    private int id;
    private int x;
    private int y;
    private NeighborDoublyLinkedList neighbors;

    public City(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.neighbors = new NeighborDoublyLinkedList();
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public NeighborDoublyLinkedList getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(int destinationId, double distance) {
        neighbors.append(new Neighbor(destinationId, distance));
    }

    @Override
    public String toString() {
        return "City " + id + " (" + x + ", " + y + ")";
    }
}
