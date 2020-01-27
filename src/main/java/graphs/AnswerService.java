package graphs;

import java.util.ArrayDeque;
import java.util.Deque;

public class AnswerService {
    Graph graph;
    RouteFinder routeFinder;
    String[] routes;

    public AnswerService(String[] routes) {
        this.routes = routes;
        graph = new Graph();
        routeFinder = new RouteFinder(graph);
        routesInit(routes);
    }

    private void routesInit(String[] routes) {
        for(String route:routes){
            String firstCity = route.split("")[0];
            String secondCity = route.split("")[1];

            int distance = Integer.parseInt(route.split("")[2]);

            CityNode sourceNode = routeFinder.getCityNodeOrGetNew(firstCity);
            CityNode targetNode = routeFinder.getCityNodeOrGetNew(secondCity);

            sourceNode.addDestination(targetNode, distance);
            graph.addNode(sourceNode);
            graph.addNode(targetNode);
        }
    }

    public String firstAnswer() {
        Deque<String> routeList = new ArrayDeque<>();
        routeList.addLast("A");
        routeList.addLast("B");
        routeList.addLast("C");
        return routeFinder.routeListDistanceCounter(routeList);
    }

    public String secondAnswer() {
        Deque<String> routeList = new ArrayDeque<>();
        routeList.addLast("A");
        routeList.addLast("D");
        return routeFinder.routeListDistanceCounter(routeList);
    }

    public String thirdAnswer() {
        Deque<String> routeList = new ArrayDeque<>();
        routeList.addLast("A");
        routeList.addLast("D");
        routeList.addLast("C");
        return routeFinder.routeListDistanceCounter(routeList);
    }

    public String fourthAnswer() {
        Deque<String> routeList = new ArrayDeque<>();
        routeList.addLast("A");
        routeList.addLast("E");
        routeList.addLast("B");
        routeList.addLast("C");
        routeList.addLast("D");
        return routeFinder.routeListDistanceCounter(routeList);
    }

    public String fifthAnswer() {
        Deque<String> routeList = new ArrayDeque<>();
        routeList.addLast("A");
        routeList.addLast("E");
        routeList.addLast("D");
        return routeFinder.routeListDistanceCounter(routeList);
    }

    public String sixthAnswer() {
        CityNode nodeC = routeFinder.getCityNodeIfExists("C");
        Integer result = routeFinder.numOfTripsWithMaxStops(nodeC, nodeC, 3);
        return result.toString();
    }

    public String seventhAnswer() {
        CityNode nodeA = routeFinder.getCityNodeIfExists("A");
        CityNode nodeC = routeFinder.getCityNodeIfExists("C");
        Integer result = routeFinder.numOfTripsWithExactStops(nodeA, nodeC, 4);
        return result.toString();
    }

    public String eightsAnswer() {
        CityNode nodeA = routeFinder.getCityNodeIfExists("A");
        CityNode nodeC = routeFinder.getCityNodeIfExists("C");
        routeFinder.calculateShortestPathFromSource(graph, nodeA);
        Integer result = nodeC.getDistance();
        return result.toString();
    }

    public String ninesAnswer() {
        CityNode nodeB = routeFinder.getCityNodeIfExists("B");
        routeFinder.calculateShortestPathFromSource(graph, nodeB);
        Integer result = nodeB.getDistance();
        return result.toString();
    }

    public String tensAnswer() {
        CityNode nodeC = routeFinder.getCityNodeIfExists("C");
        Integer result = routeFinder.calculateAllPathsFromStartToEnd(nodeC, nodeC, 0,30);
        return result.toString();
    }

}
