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
        int[] empty = {};
        Map<Integer, List<Integer>> courses = buildPrerequisites(numCourses, prerequisites);
        List<Integer> result = new ArrayList<>();
        // States: 0 - UNVISITED, 1 - VISITING, 2 - VISITED
        int[] visited = new int[numCourses];
        for (int idx = 0; idx < numCourses; idx++) {
            if (!dfs(courses, idx, visited, result)) {
                return empty;
            }
        }
        return !result.isEmpty() ? result.stream().mapToInt(e -> e).toArray() : empty;
    }

    private static boolean dfs(Map<Integer, List<Integer>> courses, int course, int[] visited, List<Integer> result) {
        int state = visited[course];
        // Already visited, found a cycle
        if (state == 1) return false;
        // Visited before, no need to traverse the same edges again
        if (state == 2) return true;
        visited[course] = 1;
        // Check the prerequisites graph
        List<Integer> prerequisites = courses.get(course);
        if (Objects.nonNull(prerequisites)) {
            for (int prereq_course : prerequisites) {
                if (!dfs(courses, prereq_course, visited, result)) {
                    return false;
                }
            }
        }
        visited[course] = 2;
        result.add(0, course);
        return true;
    }

    private static Map<Integer, List<Integer>> buildPrerequisites(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> courses = new HashMap<>();
        for (int idx = 0; idx < numCourses; idx++) {
            courses.put(idx, new ArrayList<>());
        }
        for (int[] course : prerequisites) {
            int start = course[0], end = course[1];
            List<Integer> dependencies = courses.getOrDefault(end, new ArrayList<>());
            dependencies.add(start);
            courses.put(end, dependencies);
        }
        return courses;
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

    private static int[] findOrderQueueApproach(int numCourses, int[][] prerequisites) {
        int[] empty = {};
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
        int visited = 0;
        while (!q.isEmpty()) {
            int course = q.poll();
            order[visited++] = course;
            if (courses.containsKey(course)) {
                for (int next : courses.get(course)) {
                    dependency[next] -= 1;
                    if (dependency[next] == 0) {
                        q.offer(next);
                    }
                }
            }
        }
        return (visited == numCourses) ? order : empty;
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
        result = findOrderDFS(numCourses, prerequisites);
        System.out.println("DFS - " + Arrays.toString(result));
        result = findOrderQueueApproach(numCourses, prerequisites);
        System.out.println("Queue - " + Arrays.toString(result));
        //
        numCourses = 1;
        prerequisites = new int[][] {};
        result = findOrderDFS(numCourses, prerequisites);
        System.out.println(Arrays.toString(result));
        //
        numCourses = 2;
        prerequisites = new int[][] { {0, 1} };
        result = findOrderDFS(numCourses, prerequisites);
        System.out.println(Arrays.toString(result));
        //
        numCourses = 2;
        prerequisites = new int[][] {};
        result = findOrderDFS(numCourses, prerequisites);
        System.out.println(Arrays.toString(result));
    }

}
