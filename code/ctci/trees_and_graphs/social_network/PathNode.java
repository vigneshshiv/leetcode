package code.ctci.trees_and_graphs.social_network;

import java.util.LinkedList;
import java.util.Objects;

public class PathNode {

    private Person person;
    private PathNode previousNode;

    public PathNode(Person person, PathNode previous) {
        this.person = person;
        this.previousNode = previous;
    }

    public Person getPerson() {
        return this.person;
    }

    /**
     * Create a path
     */
    public LinkedList<Person> collapse(boolean startsWithRoot) {
        LinkedList<Person> path = new LinkedList<>();
        PathNode node = this;
        while (Objects.nonNull(node)) {
            if (startsWithRoot) {
                path.add(node.person);
            } else {
                path.addFirst(node.person);
            }
            node = node.previousNode;
        }
        return path;
    }

}
