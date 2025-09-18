package sulran.algo3;

public class Dijkstra {

    public static CityDoublyLinkedList computeShortestPath(CityDoublyLinkedList cities, int sourceId, int targetId) {
        CityInfoDoublyLinkedList infoList = new CityInfoDoublyLinkedList();

        // 1. Initialize CityInfo for all cities
        CityNode current = cities.getHead();
        while (current != null) {
            CityInfo info = new CityInfo(current.data.getId());
            if (info.cityId == sourceId)
                info.distance = 0;
            infoList.append(info);
            current = current.next;
        }

        // 2. Dijkstra loop
        while (true) {
            CityInfoNode minNode = infoList.getMinUnvisited();
            if (minNode == null || minNode.data.cityId == targetId)
                break;

            minNode.data.visited = true;

            CityNode fromCityNode = cities.findById(minNode.data.cityId);
            if (fromCityNode == null) continue;

            NeighborNode neighbor = fromCityNode.data.getNeighbors().getHead();
            while (neighbor != null) {
                CityInfoNode neighborInfoNode = infoList.find(neighbor.data.destinationId);
                if (neighborInfoNode != null && !neighborInfoNode.data.visited) {
                    double newDist = minNode.data.distance + neighbor.data.distance;
                    if (newDist < neighborInfoNode.data.distance) {
                        neighborInfoNode.data.distance = newDist;
                        neighborInfoNode.data.previous = minNode.data.cityId;
                    }
                }
                neighbor = neighbor.next;
            }
        }

        // 3. Build path list
        CityDoublyLinkedList path = new CityDoublyLinkedList();
        int stepId = targetId;

        while (stepId != -1) {
            CityNode cityNode = cities.findById(stepId);
            if (cityNode != null)
                path.prepend(cityNode.data.getId(), cityNode.data.getX(), cityNode.data.getY());

            CityInfoNode info = infoList.find(stepId);
            if (info == null) break;
            stepId = info.data.previous;
        }

        return path;
    }

    public static double getDistanceToTarget(CityInfoDoublyLinkedList infoList, int targetId) {
        CityInfoNode targetInfo = infoList.find(targetId);
        return targetInfo != null ? targetInfo.data.distance : Double.POSITIVE_INFINITY;
    }
}

