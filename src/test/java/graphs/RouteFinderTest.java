package graphs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class RouteFinderTest {
    Graph testGraph;
    RouteFinder routeFinder;

    @Before
    public void init() throws Exception{
        testGraph = new Graph();
        routeFinder = new RouteFinder(testGraph);

        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st = br.readLine();
        String[] routes = st.split(", ");

        for(String route:routes){
            String firstCity = route.split("")[0];
            String secondCity = route.split("")[1];

            int distance = Integer.parseInt(route.split("")[2]);

            CityNode sourceNode = routeFinder.getCityNodeOrGetNew(firstCity);
            CityNode targetNode = routeFinder.getCityNodeOrGetNew(secondCity);

            sourceNode.addDestination(targetNode, distance);
            testGraph.addNode(sourceNode);
            testGraph.addNode(targetNode);
        }
    }

    @Test
    public void testCalculateShortestPathFromSource() {
        CityNode nodeA = routeFinder.getCityNodeIfExists("A");
        CityNode nodeC = routeFinder.getCityNodeIfExists("C");
        routeFinder.calculateShortestPathFromSource(testGraph, nodeA);
        Integer expected = 9;
        Assert.assertEquals(expected, nodeC.getDistance());
    }

    @Test
    public void testCalculateShortestPathFromBtoB() {
        CityNode nodeB = routeFinder.getCityNodeIfExists("B");
        routeFinder.calculateShortestPathFromSource(testGraph, nodeB);
        Integer expected = 9;
        Assert.assertEquals(expected, nodeB.getDistance());
    }

    @Test
    public void testCalculateShortestPathFromCtoC() {
        CityNode nodeC = routeFinder.getCityNodeIfExists("C");
        int total = routeFinder.calculateAllPathsFromStartToEnd(nodeC, nodeC, 0, 30);
        int expected = 7;
        Assert.assertEquals(expected, total);
    }


    @Test
    public void testABCDistanceCounter() {
        Deque<String> routeList = new ArrayDeque<>();
        routeList.addLast("A");
        routeList.addLast("B");
        routeList.addLast("C");
        String distance = routeFinder.routeListDistanceCounter(routeList);
        Integer expected = 9;
        Assert.assertEquals(expected.toString(), distance);
    }

    @Test
    public void testADDistanceCounter() {
        Deque<String> routeList = new ArrayDeque<>();
        routeList.addLast("A");
        routeList.addLast("D");
        String distance = routeFinder.routeListDistanceCounter(routeList);
        Integer expected = 5;
        Assert.assertEquals(expected.toString(), distance);
    }

    @Test
    public void testADCDistanceCounter() {
        Deque<String> routeList = new ArrayDeque<>();
        routeList.addLast("A");
        routeList.addLast("D");
        routeList.addLast("C");
        String distance = routeFinder.routeListDistanceCounter(routeList);
        Integer expected = 13;
        Assert.assertEquals(expected.toString(), distance);
    }

    @Test
    public void testAEBCDDistanceCounter() {
        Deque<String> routeList = new ArrayDeque<>();
        routeList.addLast("A");
        routeList.addLast("E");
        routeList.addLast("B");
        routeList.addLast("C");
        routeList.addLast("D");
        String distance = routeFinder.routeListDistanceCounter(routeList);
        Integer expected = 22;
        Assert.assertEquals(expected.toString(), distance);
    }

    @Test
    public void testAEDDistanceCounter() {
        Deque<String> routeList = new ArrayDeque<>();
        routeList.addLast("A");
        routeList.addLast("E");
        routeList.addLast("D");
        String distance = routeFinder.routeListDistanceCounter(routeList);
        String expected = "NO SUCH ROUTE";
        Assert.assertEquals(expected, distance);
    }

    @Test
    public void testNumOfTripsFromAToC() {
        CityNode nodeA = routeFinder.getCityNodeIfExists("A");
        CityNode nodeC = routeFinder.getCityNodeIfExists("C");
        routeFinder.calculateShortestPathFromSource(testGraph, nodeA);
        Integer expected = 9;
        Assert.assertEquals(expected.toString(), nodeC.getDistance().toString());
    }

    @Test
    public void testNumOfTripsWithMaxStops() {
        CityNode nodeC = routeFinder.getCityNodeIfExists("C");
        Integer result = routeFinder.numOfTripsWithMaxStops(nodeC, nodeC, 3);
        Integer expected = 2;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testNumOfTripsWithExactStops() {
        CityNode nodeA = routeFinder.getCityNodeIfExists("A");
        CityNode nodeC = routeFinder.getCityNodeIfExists("C");
        Integer result = routeFinder.numOfTripsWithExactStops(nodeA, nodeC, 4);
        Integer expected = 3;
        Assert.assertEquals(expected, result);
    }

    /*@Test
    public void testCalculatingTheMinimumDistance() {
        CityNode nodeA = routeFinder.getCityNodeIfExists("A");
        CityNode nodeE = routeFinder.getCityNodeIfExists("E");
        nodeA.addDestination(nodeE, 5);
        Integer expected = 1;

        routeFinder.CalculateMinimumDistance(nodeA, expected, nodeE);

        Assert.assertEquals(expected, nodeA.getDistance());
    }*/
}