package sulran.algo3;

public class CityInfo { // for dij store temporary info per city during the algorithm.
    public int cityId;
    public double distance;
    public boolean visited;
    public int previous; // previous city in path

    public CityInfo(int cityId) {
        this.cityId = cityId;
        this.distance = Double.POSITIVE_INFINITY;
        this.visited = false;
        this.previous = -1;
    }
}

