package code.java.graphs;

import java.util.*;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseScheduleII {

    /**
     * Not a valid answer
     */
    private static int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        int[] empty = new int[] {0};
        if (prerequisites.length == 0 || prerequisites[0].length == 0) return empty;
        Map<Integer, List<Integer>> courses = buildPrerequisites(prerequisites);
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> cycle = new HashSet<>();
        for (Integer course : courses.keySet()) {
            if (!dfs(courses, course, visited, cycle, result)) {
                return empty;
            }
        }
        return !result.isEmpty() ? result.stream().mapToInt(e -> e).toArray() : empty;
    }

    private static boolean dfs(Map<Integer, List<Integer>> courses, int course, Set<Integer> visited, Set<Integer> cycle,
                               List<Integer> result) {
        // Already visited, found a cycle
        if (cycle.contains(course)) return false;
        // Visited before, no need to traverse the same edges again
        if (visited.contains(course)) return true;
        cycle.add(course);
        // Check the prerequisites graph
        List<Integer> prerequisites = courses.get(course);
        if (Objects.nonNull(prerequisites)) {
            for (int prereq_course : prerequisites) {
                if (!dfs(courses, prereq_course, visited, cycle, result)) {
                    return false;
                }
            }
        }
        cycle.remove(course);
        visited.add(course);
        result.add(course);
        return true;
    }

    private static Map<Integer, List<Integer>> buildPrerequisites(int[][] prerequisites) {
        Map<Integer, List<Integer>> courses = new HashMap<>();
        for (int[] course : prerequisites) {
            int start = course[0], end = course[1];
            List<Integer> dependencies = courses.getOrDefault(start, new ArrayList<>());
            dependencies.add(end);
            courses.put(start, dependencies);
            //
            courses.putIfAbsent(end, null);
        }
        return courses;
    }

    /**
     * Reference <code>BuildOrderEdgeRemoval</code>
     */
    private static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] empty = new int[] {};
        if (prerequisites.length == 0 || prerequisites[0].length == 0) return empty;
        // In-degree courses
        int[] dependency = new int[numCourses];
        Map<Integer, List<Integer>> courses = buildPrerequisites(prerequisites, dependency);
        // Result order
        int[] order = new int[numCourses];
        Arrays.fill(order, -1);
        // build non dependency courses list first and find offset
        int offset = addNonDependentCourses(order, dependency, 0);
        int toBeProcessed = 0;
        while (toBeProcessed < numCourses) {
            // Current courses list
            List<Integer> list = courses.get(toBeProcessed);
            if (Objects.nonNull(list)) {
                // return null; // Circular dependency;
                // Decrement dependencies
                list.stream().forEach(e -> dependency[e] -= 1);
                // Add finished courses
                for (int finished_course : list) {
                    if (dependency[finished_course] == 0) {
                        order[offset++] = finished_course;
                    }
                }
            }
            toBeProcessed++;
        }
        return order;
    }

    /**
     * Build dependency in opposite order
     */
    private static Map<Integer, List<Integer>> buildPrerequisites(int[][] prerequisites, int[] dependency) {
        Map<Integer, List<Integer>> courses = new HashMap<>();
        for (int[] course : prerequisites) {
            int post = course[0], pre = course[1];
            List<Integer> dependencies = courses.getOrDefault(pre, new ArrayList<>());
            dependencies.add(post);
            courses.put(pre, dependencies);
            // Count the number of dependencies
            dependency[post] += 1;
        }
        return courses;
    }

    private static int addNonDependentCourses(int[] order, int[] dependency, int offset) {
        for (int course : dependency) {
            if (course == 0 && order[course] == -1) {
                order[offset++] = course;
            }
        }
        return offset;
    }

    private static int[] findOrderQueueApproach(int numCourses, int[][] prerequisites) {
        int[] empty = new int[] {};
        if (prerequisites.length == 0 || prerequisites[0].length == 0) return empty;
        // In-degree courses
        int[] dependency = new int[numCourses];
        Map<Integer, List<Integer>> courses = buildPrerequisites(prerequisites, dependency);
        // Result order
        int[] order = new int[numCourses];
        // Process in queue
        Deque<Integer> q = new ArrayDeque<>();
        // Add non dependent courses
        for (int i = 0; i < numCourses; i++) {
            if (dependency[i] == 0) {
                q.offer(i);
            }
        }
        int toBeProcessed = 0;
        while (!q.isEmpty()) {
            int course = q.poll();
            order[toBeProcessed++] = course;
            if (courses.containsKey(course)) {
                for (int next : courses.get(course)) {
                    dependency[next] -= 1;
                    if (dependency[next] == 0) {
                        q.offer(next);
                    }
                }
            }
        }
        return (toBeProcessed == numCourses) ? order : empty;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {
                {1, 0}
        };
        int[] result = findOrderDFS(numCourses, prerequisites);
        System.out.println(Arrays.toString(result));
        //
        numCourses = 4;
        prerequisites = new int[][] {
                {1, 0}, {2, 0}, {3, 1}, {3, 2}
        };
        result = findOrder(numCourses, prerequisites);
        System.out.println("Iteration - " + Arrays.toString(result));
        result = findOrderQueueApproach(numCourses, prerequisites);
        System.out.println("Queue - " + Arrays.toString(result));
        //
        numCourses = 1;
        prerequisites = new int[][] { {} };
        result = findOrderDFS(numCourses, prerequisites);
        System.out.println(Arrays.toString(result));
    }

}
