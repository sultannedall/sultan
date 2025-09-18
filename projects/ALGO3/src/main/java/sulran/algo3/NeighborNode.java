package sulran.algo3;

public class NeighborNode {
    public Neighbor data;
    public NeighborNode next;
    public NeighborNode prev;

    public NeighborNode(Neighbor data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

