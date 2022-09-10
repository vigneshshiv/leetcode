package code.java.graphs;

import code.java.graphs.Graph;

import java.util.*;

public class UndirectedPath {

    /**
     * n - # of nodes
     * e - # of edges
     *
     * Time complexity: O(e)
     * Space complexity: O(n)
     */
    private static boolean undirectedPath(String[][] edges, String src, String dst) {
        Map<String, List<String>> graph = Graph.buildGraph(edges);
        return hasPath(graph, src, dst, new HashSet<>());
    }

    private static boolean hasPath(Map<String, List<String>> graph, String src, String dst, Set<String> visited) {
        if (Objects.equals(src, dst)) return true;
        if (visited.add(src)) {
            List<String> adjacencyNodes = graph.get(src);
            if (Objects.nonNull(adjacencyNodes) && !adjacencyNodes.isEmpty()) {
                for (String adjacentNode : adjacencyNodes) {
                    boolean pathFound = hasPath(graph, adjacentNode, dst, visited);
                    if (pathFound) return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[][] edges = {
                {"i", "j"}, {"k", "i"}, {"m", "k"}, {"k", "l"}, {"o", "n"}
        };
        String source = "j", destination = "m";
        System.out.println(source + " has a path to " + destination + " - " + undirectedPath(edges, source, destination));
        //
        source = "m";
        destination = "j";
        System.out.println(source + " has a path to " + destination + " - " + undirectedPath(edges, source, destination));
        //
        source = "l";
        destination = "j";
        System.out.println(source + " has a path to " + destination + " - " + undirectedPath(edges, source, destination));
        //
        source = "k";
        destination = "o";
        System.out.println(source + " has a path to " + destination + " - " + undirectedPath(edges, source, destination));
        //
        source = "i";
        destination = "o";
        System.out.println(source + " has a path to " + destination + " - " + undirectedPath(edges, source, destination));
        //
        edges = new String[][] {
                {"b", "a"}, {"c", "a"}, {"b", "c"}, {"q", "r"}, {"q", "s"}, {"q", "u"}, {"q", "t"}
        };
        source = "a";
        destination = "b";
        System.out.println(source + " has a path to " + destination + " - " + undirectedPath(edges, source, destination));
        //
        source = "a";
        destination = "c";
        System.out.println(source + " has a path to " + destination + " - " + undirectedPath(edges, source, destination));
        //
        source = "r";
        destination = "t";
        System.out.println(source + " has a path to " + destination + " - " + undirectedPath(edges, source, destination));
        //
        source = "r";
        destination = "b";
        System.out.println(source + " has a path to " + destination + " - " + undirectedPath(edges, source, destination));
        //
        edges = new String[][] {
                {"s", "r"}, {"t", "q"}, {"q", "r"}
        };
        source = "r";
        destination = "t";
        System.out.println(source + " has a path to " + destination + " - " + undirectedPath(edges, source, destination));
    }

}
