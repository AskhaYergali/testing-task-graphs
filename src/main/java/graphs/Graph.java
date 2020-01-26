package graphs;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<CityNode> nodes = new HashSet<>();

    public void addNode(CityNode nodeA) {
        nodes.add(nodeA);
    }

    public Set<CityNode> getNodes() {
        return nodes;
    }

}
