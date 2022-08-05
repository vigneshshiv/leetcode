package code.ctci.trees_and_graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class RouteBetweenNodes {

    private static Graph createGraph() {
        Graph graph = new Graph();
        Node[] nodes = new Node[Graph.MAX_VERTICES];
        // Index 0 - a: b,c,d
        nodes[0] = new Node("a", 3);
        nodes[1] = new Node("b", 0);
        nodes[2] = new Node("c", 0);
        nodes[3] = new Node("d", 1);
        nodes[4] = new Node("e", 1);
        nodes[5] = new Node("f", 0);
        //
        nodes[0].addAdjacentNode(nodes[1]);
        nodes[0].addAdjacentNode(nodes[2]);
        nodes[0].addAdjacentNode(nodes[3]);
        // Index 3 - d: e
        nodes[3].addAdjacentNode(nodes[4]);
        // Index 4 - e: f
        nodes[4].addAdjacentNode(nodes[5]);
        // Attach to graph
        Arrays.stream(nodes).forEach(node -> graph.addNode(node));
        return graph;
    }

    private static boolean nodesAreConnected(Graph graph, Node start, Node end) {
        if (Objects.isNull(graph.getNodes()) || graph.getNodes().length == 0) {
            System.out.println("Graph nodes are empty");
            return false;
        }
        if (Objects.isNull(start) || Objects.isNull(end)) {
            System.out.println("Neither Start nor End node shouldn't be Null");
            return false;
        }
        if (start == end) return true;
        // Change State
        BiConsumer<Node, Node.State> changeState = (node, state) -> node.state = state;
        // Initialize to UNVISITED state
        Arrays.stream(graph.getNodes()).forEach(node -> changeState.accept(node, Node.State.UNVISITED));
        // Start node
        changeState.accept(start, Node.State.VISITING);
        // Queue for DFS
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        // Iterate till Queue is Empty
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (Objects.nonNull(node)) {
                for (Node adjacentNode : node.getAdjacentNodes()) {
                    if (adjacentNode.state == Node.State.UNVISITED) {
                        if (adjacentNode == end) return true;
                        queue.add(adjacentNode);
                        changeState.accept(adjacentNode, Node.State.VISITING);
                    }
                }
                // Change it to VISITED State
                changeState.accept(node, Node.State.VISITED);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph graph = createGraph();
        Node[] nodes = graph.getNodes();
        Node start = nodes[3];
        Node end = nodes[5];
        System.out.println("Are D node and F nodes are connected - " + nodesAreConnected(graph, start, end));
        System.out.println("Are B node and E nodes are connected - " + nodesAreConnected(graph, nodes[1], nodes[4]));
    }

}