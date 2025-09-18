package sulran.algo3;
public class Neighbor {
    public int destinationId;
    public double distance;

    public Neighbor(int destinationId, double distance) {
        this.destinationId = destinationId;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "→ City " + destinationId + " (Distance: " + distance + ")";
    }
}
