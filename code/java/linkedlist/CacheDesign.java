package code.java.linkedlist;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.IntStream;

public class CacheDesign {

    private static final int MAX_SIZE = 20;
    private Node head;
    private Node tail;
    private Map<String, Node> cache;
    private int size = 0;

    public CacheDesign() {
        cache = new HashMap<>();
    }

    private void moveToFront(Node node) {
        if (Objects.isNull(node) || Objects.equals(head, node)) {
            return;
        }
        // Remove From Linked List
        removeFromLinkedList(node);
        node.next = head;
        if (Objects.nonNull(head)) {
            head.prev = node;
        }
        head = node;
        size++;
        if (Objects.isNull(tail)) {
            tail = node;
        }
    }

    private void removeFromLinkedList(Node node) {
        if (Objects.isNull(node)) {
            return;
        }
        if (Objects.nonNull(node.prev) || Objects.nonNull(node.next)) {
            size--;
        }
        Node prev = node.prev;
        if (Objects.nonNull(prev)) {
            prev.next = node.next;
        }
        Node next = node.next;
        if (Objects.nonNull(next)) {
            next.prev = prev;
        }
        if (Objects.equals(head, node)) {
            head = next;
        }
        if (Objects.equals(tail, node)) {
            tail = prev;
        }
        node.prev = null;
        node.next = null;
    }

    public void insertResults(String query, String[] results) {
        if (cache.containsKey(query)) {
            Node node = cache.get(query);
            node.results = results;
            moveToFront(node);
            return;
        }
        Node node = new Node(query, results);
        moveToFront(node);
        cache.put(query, node);
        // Check if the limit exceeds
        if (size > MAX_SIZE) {
            cache.remove(tail.query);
            removeFromLinkedList(tail);
        }
    }

    public String[] getResults(String query) {
        if (cache.containsKey(query)) {
            Node node = cache.get(query);
            // Move to front
            moveToFront(node);
            return node.results;
        }
        return null;
    }

    public static void main(String[] args) {
        Function<Integer, String[]> generateResults = idx -> new String[] {"resultA_" + idx, "resultB_" + idx, "resultC_" + idx};
        // Insert Logger
        BiConsumer<Integer, CacheDesign> insertLogger = (idx, cache) ->
                System.out.println("query_" + idx + " inserted. Current Head - " + cache.head.query + ", Tail - " + cache.tail.query);
        // Access Logger
        BiConsumer<Integer, CacheDesign> accessLogger = (idx, cache) ->
                System.out.println("query_" + idx + " accessed. Current Head - " + cache.head.query + ", Tail - " + cache.tail.query);
        // Result Logger
        BiConsumer<Integer, String[]> resultLogger = (idx, results) -> {
            if (Objects.nonNull(results))
                System.out.println("Idx - " + idx + ", Results - " + Arrays.toString(results));
            else
                System.out.println("Idx - " + idx + ", Null result");
        };
        //
        CacheDesign cache = new CacheDesign();
        IntStream.range(0, MAX_SIZE).forEach(idx -> {
            cache.insertResults("query_" + idx, generateResults.apply(idx));
            insertLogger.accept(idx, cache);
            // Temp check
            if (idx == 9 || idx == 16 || idx == 19) {
                System.out.println();
                // Access 2nd query
                cache.getResults("query_" + 2);
                accessLogger.accept(2, cache);
                // Access 6th query
                cache.getResults("query_" + 6);
                accessLogger.accept(6, cache);
                // Access 9th query
                cache.getResults("query_" + 9);
                accessLogger.accept(9, cache);
                System.out.println();
            }
        });
        System.out.println();
        IntStream.range(MAX_SIZE - 3, MAX_SIZE + 2).forEach(idx -> {
            resultLogger.accept(idx, cache.getResults("query_" + idx));
        });
        System.out.println();
        System.out.println("Last Accessed item, Head - " + cache.head.query + ", Tail - " + cache.tail.query);
    }

}

class Node {
    Node prev, next;
    String query;
    String[] results;

    public Node(String query, String[] results) {
        this.query = query;
        this.results = results;
    }

}
