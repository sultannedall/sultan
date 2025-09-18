package sulran.algo3;
import java.io.BufferedReader;
import java.io.FileReader;

public class MapLoader {

    public static CityDoublyLinkedList loadCitiesAndEdges(String filename) {
        CityDoublyLinkedList cityList = new CityDoublyLinkedList();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine(); // First line: number of cities and edges
            String[] counts = line.trim().split("\\s+");
            int cityCount = Integer.parseInt(counts[0]);
            int edgeCount = Integer.parseInt(counts[1]);

            // Load cities
            for (int i = 0; i < cityCount; i++) {
                line = reader.readLine();
                String[] parts = line.trim().split("\\s+");
                int id = Integer.parseInt(parts[0]);
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                City city = new City(id, x, y);
                cityList.append(city);
            }

            // Load edges
            for (int i = 0; i < edgeCount; i++) {
                line = reader.readLine();
                if (line == null || line.isEmpty()) continue;
                String[] parts = line.trim().split("\\s+");
                int fromId = Integer.parseInt(parts[0]);
                int toId = Integer.parseInt(parts[1]);

                CityNode fromNode = cityList.findById(fromId);
                CityNode toNode = cityList.findById(toId);

                if (fromNode != null && toNode != null) {
                    City fromCity = fromNode.data;
                    City toCity = toNode.data;
                    double dist = distance(fromCity, toCity);

                    fromCity.addNeighbor(toId, dist);
                    toCity.addNeighbor(fromId, dist); // undirected edge
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityList;
    }

    private static double distance(City a, City b) {
        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
