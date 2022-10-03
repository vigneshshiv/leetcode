package code.java.graphs;

import java.util.*;

/**
 * https://leetcode.com/problems/bus-routes/
 */
public class BusRoutes {

    private static int numBusesToDestination(int[][] routes, int source, int target) {
        // Base case
        if (source == target) return 0;
        // Build graph
        Map<Integer, List<Integer>> graph = buildGraph(routes);
        // If source and target is not exist in the graph, then we can't reach target
        if (!graph.containsKey(source) || !graph.containsKey(target)) return -1;
        // Iterate through every pit stop index
        Deque<Integer> q = new ArrayDeque<>();
        boolean[] busTaken = new boolean[routes.length];
        Set<Integer> stopVisited = new HashSet<>();
        // Iterate from source route
        q.add(source);
        int count = 0;
        while (!q.isEmpty()) {
            count += 1;
            int size = q.size();
            while (size-- > 0) {
                int currentStop = q.poll();
                for (int bus : graph.get(currentStop)) {
                    // Bus already taken
                    if (busTaken[bus]) {
                        continue;
                    }
                    busTaken[bus] = true;
                    for (int nextStop : routes[bus]) {
                        if (!stopVisited.add(nextStop)) {
                            continue;
                        }
                        if (nextStop == target) {
                            return count;
                        }
                        q.offer(nextStop);
                        // stopVisited.add(nextStop);
                    }
                }
            }
        }
        return -1;
    }

    private static Map<Integer, List<Integer>> buildGraph(int[][] routes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // Iterates through route path and add pit stop index
        for (int i = 0; i < routes.length; i++) {
            int[] route = routes[i];
            int n = route.length;
            for (int j = 0; j < n; j++) {
                int stop = route[j];
                graph.putIfAbsent(stop, new ArrayList<>());
                graph.get(stop).add(i);
            }
        }
        return graph;
    }

    public static void main(String[] args) {
        int[][] routes = {
                {1, 2, 7}, {3, 6, 7}
        };
        int source = 1, target = 6;
        int count = numBusesToDestination(routes, source, target);
        System.out.println("Count - " + count);
        //
        routes = new int[][] {
                {7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}
        };
        source = 15; target = 12;
        count = numBusesToDestination(routes, source, target);
        System.out.println("Count - " + count);
    }

}
