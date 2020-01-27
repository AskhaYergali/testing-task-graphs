package graphs;

import java.util.*;

public class RouteFinder {
    Graph graph;

    public RouteFinder(Graph graph) {
        this.graph = graph;
    }

    Map<Integer, List<CityNode>> createTripsMap(CityNode startCity, CityNode endCity, int maxStops) {
        Map<Integer, List<CityNode>> trips = new HashMap<>();
        List<CityNode> cities = new ArrayList<>();

        cities.add(startCity);

        trips.put(0, cities);

        for (int i = 0; i < maxStops; i++) {
            for(CityNode city:trips.get(i)) {
                List<CityNode> oldCities = trips.getOrDefault(i+1, new ArrayList<>());
                List<CityNode> newCities = new ArrayList<>((city.getAdjacentNodes().keySet()));
                newCities.addAll(oldCities);
                trips.put(i + 1, newCities);
            }
        }
        return trips;
    }

    public int numOfTripsWithMaxStops(CityNode startCity, CityNode endCity, int maxStops) {
        int result = 0;
        Map<Integer, List<CityNode>> trips = createTripsMap(startCity, endCity, maxStops);
        for (int i = 1; i <= maxStops; i++) {
            result += trips.get(i).stream().filter(it -> it.getName().equals(endCity.getName())).count();
        }
        return result;
    }

    public int numOfTripsWithExactStops(CityNode startCity, CityNode endCity, int exactStops) {
        int result = 0;
        Map<Integer, List<CityNode>> trips = createTripsMap(startCity, endCity, exactStops);

        result += trips.get(exactStops).stream().filter(it -> it.getName().equals(endCity.getName())).count();

        return result;
    }

    public String routeListDistanceCounter(Deque<String> cityNamesSeq) {
        try {
            Integer result = 0;
            String firstCityName = cityNamesSeq.poll();
            CityNode firstCityNode = getCityNodeIfExists(firstCityName);
            while(!cityNamesSeq.isEmpty()){
                String cityName = cityNamesSeq.poll();
                CityNode cityNode = getCityNodeIfExists(cityName);
                int weight = getDistanceBetweenTwoNodes(firstCityNode, cityNode);
                result += weight;
                firstCityNode = cityNode;
            }
            return result.toString();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public CityNode getCityNodeIfExists(String name) {
        return graph.getNodes().stream()
                .filter(it -> it.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("NO SUCH ROUTE"));
    }

    public CityNode getCityNodeOrGetNew(String name) {
        return graph.getNodes().stream()
                .filter(it -> it.getName().equals(name))
                .findFirst()
                .orElse(new CityNode(name));
    }

    public int getDistanceBetweenTwoNodes(CityNode first, CityNode second) {
        return first.getAdjacentNodes().entrySet().stream()
                .filter(it -> it.getKey().getName().equals(second.getName()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("NO SUCH ROUTE"));
    }

    public int calculateAllPathsFromStartToEnd(CityNode source, CityNode end, int distance, int maxDistance) {
        int counter = 0;
        for (Map.Entry<CityNode, Integer> adjacencyPair : source.getAdjacentNodes().entrySet()) {
            CityNode adjacentNode = adjacencyPair.getKey();
            Integer edgeWeight = adjacencyPair.getValue();
            int currDistance = distance;
            currDistance += edgeWeight;
            if (currDistance < maxDistance) {
                if (adjacentNode.getName().equals(end.getName())) {
                    counter += 1;
                }
                counter += calculateAllPathsFromStartToEnd(adjacentNode, end, currDistance, maxDistance);
            }
        }
        return counter;
    }

    public Graph calculateShortestPathFromSource(Graph graph, CityNode source) {
        source.setDistance(0);

        Set<CityNode> settledNodes = new HashSet<>();
        Set<CityNode> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            CityNode currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<CityNode, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                if (adjacencyPair.getKey().getName().equals(source.getName())) {
                    if (source.getDistance() == 0 || adjacencyPair.getValue() + currentNode.getDistance() < source.getDistance()) {
                        source.setDistance(adjacencyPair.getValue() + currentNode.getDistance());
                    }
                }
                CityNode adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static void CalculateMinimumDistance(CityNode evaluationNode, Integer edgeWeigh, CityNode sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<CityNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private static CityNode getLowestDistanceNode(Set<CityNode> unsettledNodes) {
        CityNode lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (CityNode node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

}