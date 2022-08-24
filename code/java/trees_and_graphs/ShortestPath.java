package code.java.trees_and_graphs;

import java.util.*;

public class ShortestPath {

    record NodeData(String node, int distance) {};

    /**
     * n - # of nodes
     * e - # of edges
     *
     * Time complexity: O(e)
     * Space complexity: O(n)
     */
    private static int findShortestPath(String[][] edges, String src, String dst) {
        Map<String, List<String>> graph = Graph.buildGraph(edges);
        return shortestPath(graph, src, dst);
    }

    private static int shortestPath(Map<String, List<String>> graph, String src, String dst) {
        Set<String> visited = new HashSet<>();
        Queue<NodeData> queue = new LinkedList<>();
        queue.add(new NodeData(src, 0));
        //
        while (!queue.isEmpty()) {
            NodeData current = queue.poll();
            if (visited.add(current.node())) {
                if (Objects.equals(current.node(), dst)) {
                    return current.distance();
                }
                List<String> adjacencyNodes = graph.get(current.node());
                if (Objects.nonNull(adjacencyNodes) && !adjacencyNodes.isEmpty()) {
                    for (String adjacentNode : adjacencyNodes) {
                        queue.add(new NodeData(adjacentNode, current.distance() + 1));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[][] edges = {
                {"w", "x"}, {"x", "y"}, {"z", "y"}, {"z", "v"}, {"w", "v"}
        };
        String source = "w", destination = "z";
        System.out.println("Shortest Path distance - " + findShortestPath(edges, source, destination)); // 2
        //
        source = "y";
        destination = "x";
        System.out.println("Shortest Path distance - " + findShortestPath(edges, source, destination)); // 1
        //
        edges = new String[][] {
                {"a", "c"}, {"a", "b"}, {"c", "b"}, {"c", "d"}, {"b", "d"}, {"e", "d"}, {"g", "f"}
        };
        source = "a";
        destination = "e";
        System.out.println("Shortest Path distance - " + findShortestPath(edges, source, destination)); // 3
        //
        source = "e";
        destination = "c";
        System.out.println("Shortest Path distance - " + findShortestPath(edges, source, destination)); // 2
        //
        source = "b";
        destination = "g";
        System.out.println("Shortest Path distance - " + findShortestPath(edges, source, destination)); // -1
        //
        edges = new String[][] {
                {"c", "n"}, {"c", "e"}, {"c", "s"}, {"c", "w"}, {"w", "e"}
        };
        source = "w";
        destination = "e";
        System.out.println("Shortest Path distance - " + findShortestPath(edges, source, destination)); // 1
        //
        source = "n";
        destination = "e";
        System.out.println("Shortest Path distance - " + findShortestPath(edges, source, destination)); // 2
        //
        edges = new String[][] {
                {"m", "n"}, {"n", "o"}, {"o", "p"}, {"p", "q"}, {"t", "o"}, {"r", "q"}, {"r", "s"}
        };
        source = "m";
        destination = "s";
        System.out.println("Shortest Path distance - " + findShortestPath(edges, source, destination)); // 6
    }

}
