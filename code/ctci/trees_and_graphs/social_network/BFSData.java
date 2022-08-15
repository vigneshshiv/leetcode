package code.ctci.trees_and_graphs.social_network;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BFSData {

    public Queue<PathNode> toVisit = new LinkedList<>();
    public Map<Integer, PathNode> visited = new HashMap<>();

    public BFSData(Person root) {
        PathNode source = new PathNode(root, null);
        toVisit.add(source);
        visited.put(root.getID(), source);
    }

    public boolean isFinished() {
        return toVisit.isEmpty();
    }

}
