package code.java.trees_and_graphs;

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

}
