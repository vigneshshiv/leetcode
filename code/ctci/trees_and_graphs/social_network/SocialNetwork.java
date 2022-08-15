package code.ctci.trees_and_graphs.social_network;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SocialNetwork {

    /**
     * BFS Iterative - Find friend connection path
     *
     * Time complexity: O(n) time
     * Space complexity: O(n) space
     */
    private static LinkedList<Person> findPathBFS(Map<Integer, Person> people, int source, int destination) {
        Queue<PathNode> toVisit = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        // Add it to queue for processing
        toVisit.add(new PathNode(people.get(source), null));
        visited.add(source);
        //
        while (!toVisit.isEmpty()) {
            PathNode node = toVisit.poll();
            Person person = node.getPerson();
            // Check
            if (person.getID() == destination) {
                return node.collapse(false);
            }
            // Search in friends
            List<Integer> friends = node.getPerson().getFriends();
            for (int friendID : friends) {
                if (!visited.contains(friendID)) {
                    PathNode friend = new PathNode(people.get(friendID), node);
                    toVisit.add(friend);
                    visited.add(friendID);
                }
            }
        }
        return null;
    }

    /**
     * Bidirectional BFS
     */
    private static LinkedList<Person> findPathBidirectionalBFS(Map<Integer, Person> people, int source, int destination) {
        BFSData sourceData = new BFSData(people.get(source));
        BFSData destinationData = new BFSData(people.get(destination));
        //
        while (!sourceData.isFinished() && !destinationData.isFinished()) {
            // Search from Source
            Person collision = search(people, sourceData, destinationData);
            if (Objects.nonNull(collision)) {
                return mergePaths(sourceData, destinationData, collision.getID());
            }
            // Search from Destination
            collision = search(people, destinationData, sourceData);
            if (Objects.nonNull(collision)) {
                return mergePaths(sourceData, destinationData, collision.getID());
            }
        }
        return null;
    }

    /**
     * Search one level at a time, count how many nodes are currently in the primary's level, process only that nodes.
     */
    private static Person search(Map<Integer, Person> people, BFSData primary, BFSData secondary) {
        int size = primary.toVisit.size();
        for (int i = 0; i < size; i++) {
            PathNode node = primary.toVisit.poll();
            int personID = node.getPerson().getID();
            // Check in destination visited nodes
            if (secondary.visited.containsKey(personID)) {
                return node.getPerson();
            }
            // Add friends to the queue
            List<Integer> friends = node.getPerson().getFriends();
            for (int friendID : friends) {
                if (!primary.visited.containsKey(friendID)) {
                    PathNode friend = new PathNode(people.get(friendID), node);
                    primary.toVisit.add(friend);
                    primary.visited.put(friendID, friend);
                }
            }
        }
        return null;
    }

    /**
     * Merge Paths
     */
    private static LinkedList<Person> mergePaths(BFSData source, BFSData destination, int connection) {
        PathNode start = source.visited.get(connection);
        PathNode end = destination.visited.get(connection);
        //
        LinkedList<Person> firstHalf = start.collapse(false);
        LinkedList<Person> secondHalf = end.collapse(true);
        //
        secondHalf.removeFirst();
        firstHalf.addAll(secondHalf);
        return firstHalf;
    }

    public static void main(String[] args) {
        int size = 10;
        int[][] edges = {
                {1, 4}, {1, 2}, {1, 3}, {3, 2}, {4, 6}, {3, 7}, {6, 9}, {9, 10}, {5, 10}, {2, 5}
        };
        int start = 1, end = 10;
        //
        Map<Integer, Person> people = new HashMap<>();
        IntStream.rangeClosed(1, size).forEach(idx -> people.put(idx, new Person(idx)));
        for (int[] edge : edges) {
            // Source
            Person source = people.get(edge[0]);
            source.addFriend(edge[1]);
            // Destination
            Person destination = people.get(edge[1]);
            destination.addFriend(edge[0]);
        }
        LinkedList<Person> path = findPathBFS(people, start, end);
        System.out.println("Path from 1 to 10 - " + path.stream().map(Person::getID).collect(Collectors.toList()));
        path = findPathBidirectionalBFS(people, start, end);
        System.out.println("Bidirectional search Path from 1 to 10 - " + path.stream().map(Person::getID).collect(Collectors.toList()));
    }

}
