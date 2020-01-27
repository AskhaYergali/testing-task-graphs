package graphs;

import java.util.*;

public class CityNode implements Comparable<CityNode>{
    private String name;

    private LinkedList<CityNode> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<CityNode, Integer> adjacentNodes = new HashMap<>();

    public CityNode(String name) {
        this.name = name;
    }

    public void addDestination(CityNode destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public String getName() {
        return name;
    }

    public Map<CityNode, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public LinkedList<CityNode> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<CityNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CityNode cityNode = (CityNode) obj;
        return Objects.equals(name, cityNode.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(CityNode other) {
        if (this.distance > other.distance) {
            return 1;
        } else if (this.distance < other.distance) {
            return -1;
        } else {
            return 0;
        }
    }
}
