package code.java.trees_and_graphs.build_order;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BuildOrderEdgeRemoval {

    private static Graph buildGraph(String[] projects, String[][] dependencies) {
        Graph graph = new Graph();
        Arrays.stream(projects).forEach(project -> graph.getOrCreateProject(project));
        Arrays.stream(dependencies).forEach(dependency -> graph.addEdge(dependency[0], dependency[1]));
        return graph;
    }

    /**
     * Similar to Topological Sort way of doing this.
     *
     * 1. Build the graph and set the counter for incoming edges
     * 2. Find all the nodes with zero incoming edges and add them first and process in that order
     * 3. Those add project has any dependency, decrease the dependency count by 1 and add non dependencies list again
     * 4. Repeat until the build order is complete or if any NULL cases found it has circular dependencies
     *
     * Time complexity: O(P + D) time, where P is the number of projects and D is the no. of dependency
     * Space complexity: O(P)
     */
    private static Project[] orderProjects(List<Project> projects) {
        Project[] order = new Project[projects.size()];
        // build non dependency list first and find offset
        int offset = addNonDependentProject(order, projects, 0);
        int toBeProcessed = 0;
        while (toBeProcessed < order.length) {
            Project current = order[toBeProcessed];
            if (Objects.isNull(current)) {
                return null; // Circular dependency;
            }
            // Decrement dependencies
            current.getDependencies().stream().forEach(dependency -> dependency.decrementDependencies());
            // Add Non dependency list
            offset = addNonDependentProject(order, current.getDependencies(), offset);
            toBeProcessed++;
        }
        return order;
    }

    private static String[] findBuildOrder(String[] projects, String[][] dependencies) {
        Graph graph = buildGraph(projects, dependencies);
        Project[] buildOrder = orderProjects(graph.getDependencies());
        return Objects.isNull(buildOrder) ? null
                : Arrays.stream(buildOrder).map(project -> project.getName()).collect(Collectors.toList()).toArray(String[]::new);
    }

    /**
     * Add Non Dependent project
     */
    private static int addNonDependentProject(Project[] order, List<Project> projects, int offset) {
        for (Project project : projects) {
            if (project.getNoOfDependencies() == 0) {
                order[offset++] = project;
            }
        }
        return offset;
    }

    public static void main(String[] args) {
        String[] projects = {"a", "b", "c", "d", "e", "f"};
        String[][] dependencies = {
                {"a", "d"}, {"f", "b"}, {"b", "d"}, {"f", "a"}, {"d", "c"}
        };
        String[] buildOrder = findBuildOrder(projects, dependencies);
        System.out.println("Build Order - " + Arrays.toString(buildOrder));
    }

}
