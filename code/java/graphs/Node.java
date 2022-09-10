package code.java.graphs;

public class Node {

    public enum State {
        UNVISITED, VISITING, VISITED
    }

    private String vertex;
    private Node[] adjacent;
    private int adjacentCount;
    public State state;

    public Node(String vertex, int adjacentLength) {
        this.vertex = vertex;
        this.adjacent = new Node[adjacentLength];
        this.adjacentCount = 0;
    }

    public void addAdjacentNode(Node node) {
        if (this.adjacentCount < this.adjacent.length) {
            this.adjacent[this.adjacentCount++] = node;
        } else {
            System.out.println("No more adjacent node can be added");
        }
    }

    public String getVertex() {
        return this.vertex;
    }

    public Node[] getAdjacentNodes() {
        if (this.adjacentCount == 0) return null;
        return this.adjacent;
    }

}

