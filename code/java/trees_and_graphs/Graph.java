package code.java.trees_and_graphs;

import java.util.*;

public class Graph {

    public static final int MAX_VERTICES = 6;
    private Node[] vertices;
    private int count;

    public Graph() {
        this.vertices = new Node[MAX_VERTICES];
        this.count = 0;
    }

    public void addNode(Node node) {
        if (this.count < this.vertices.length) {
            this.vertices[this.count++] = node;
        } else {
            System.out.println("Graph is full");
        }
    }

    public Node[] getNodes() {
        return this.vertices;
    }

    public Node getNode(String vertex) {
        if (Objects.isNull(vertex) || vertex.isEmpty() || this.count == 0) {
            return null;
        }
        for (Node node : getNodes()) {
            if (Objects.nonNull(node) && Objects.equals(node.getVertex(), vertex)) {
                return node;
            }
        }
        return null;
    }

    public static Map<String, List<String>> buildGraph(String[][] edges) {
        if (Objects.isNull(edges) || edges.length == 0 || edges[0].length == 0) return null;
        //
        Map<String, List<String>> graph = new HashMap<>();
        for (String[] edge : edges) {
            String src = edge[0];
            String dst = edge[1];
            // Source
            List<String> adjacencyNodes = graph.getOrDefault(src, new ArrayList<>());
            if (Objects.nonNull(dst)) {
                adjacencyNodes.add(dst);
                graph.put(src, adjacencyNodes);
            } else {
                graph.put(src, null);
            }
            // Destination
            if (Objects.nonNull(dst)) {
                adjacencyNodes = graph.getOrDefault(dst, new ArrayList<>());
                adjacencyNodes.add(src);
                graph.put(dst, adjacencyNodes);
            }
        }
        return graph;
    }

}
