package code.java.graphs;

import java.util.*;

public class ConnectedPathCount {

    /**
     * n - # of nodes
     * e - # of edges
     *
     * Time complexity: O(e)
     * Space complexity: O(n)
     */
    private static int connectedComponentsCount(String[][] edges) {
        int count = 0;
        Map<String, List<String>> graph = Graph.buildGraph(edges);
        if (Objects.isNull(graph)) {
            return count;
        }
        Set<String> visited = new HashSet<>();
        for (String node : graph.keySet()) {
            if (explore(graph, node, visited)) {
                count++;
            }
        }
        return count;
    }

    private static boolean explore(Map<String, List<String>> graph, String node, Set<String> visited) {
        if (Objects.isNull(node) || visited.contains(node)) return false;
        if (visited.add(node)) {
            List<String> adjacencyNodes = graph.get(node);
            if (Objects.nonNull(adjacencyNodes) && !adjacencyNodes.isEmpty()) {
                for (String adjacentNode : adjacencyNodes) {
                    explore(graph, adjacentNode, visited);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[][] edges = {
                {"0", "8"}, {"0", "1"}, {"0", "5"}, {"5", "8"}, {"2", "3"}, {"2", "4"}, {"3", "4"}
        }; // 2
        System.out.println("Count - " + connectedComponentsCount(edges));

        edges = new String[][] {
                {"1", "2"}, {"2", "8"}, {"6", "7"}, {"9", "8"}, {"7", "8"}
        }; // 1
        System.out.println("Count - " + connectedComponentsCount(edges));

        edges = new String[][] {
                {"3", null}, {"4", "6"}, {"6", "5"}, {"6", "7"}, {"6", "8"}, {"1", "2"}
        }; // 3
        System.out.println("Count - " + connectedComponentsCount(edges));

        edges = new String[][] {
                {}
        }; // 0
        System.out.println("Count - " + connectedComponentsCount(edges));

        edges = new String[][] {
                {"0", "4"}, {"0", "7"}, {"1", null}, {"2", null}, {"3", "6"}, {"8", null}
        }; // 5
        System.out.println("Count - " + connectedComponentsCount(edges));
    }

}
