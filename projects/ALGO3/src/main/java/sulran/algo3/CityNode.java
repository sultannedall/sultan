package sulran.algo3;
public class CityNode {
    public City data;
    public CityNode prev;
    public CityNode next;

    public CityNode(City data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

