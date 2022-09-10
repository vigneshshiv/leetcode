package code.java.graphs.build_order;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

public class BuildOrderDFS {

    private static Graph buildGraph(String[] projects, String[][] dependencies) {
        Graph graph = new Graph();
        Arrays.stream(projects).forEach(project -> graph.getOrCreateProject(project));
        Arrays.stream(dependencies).forEach(dependency -> graph.addEdge(dependency[0], dependency[1]));
        return graph;
    }

    private static Stack<Project> orderProjects(List<Project> projects) {
        Stack<Project> order = new Stack<>();
        // Change State
        for (Project project : projects) {
            if (Objects.equals(project.state, Project.State.UNVISITED)) {
                if (!dfs(project, order)) return null;
            }
        }
        return order;
    }

    private static boolean dfs(Project project, Stack<Project> order) {
        if (Objects.equals(project.state, Project.State.VISITING)) return false; // Cycle
        if (Objects.equals(project.state, Project.State.UNVISITED)) {
            project.state = Project.State.VISITING;
            for (Project dependency : project.getDependencies()) {
                if (!dfs(dependency, order)) return false;
            }
            project.state = Project.State.VISITED;
            order.push(project);
        }
        return true;
    }

    private static String[] findBuildOrder(String[] projects, String[][] dependencies) {
        Graph graph = buildGraph(projects, dependencies);
        Stack<Project> buildOrder = orderProjects(graph.getDependencies());
        return Objects.isNull(buildOrder) ? null
                : buildOrder.stream().map(order -> order.getName()).collect(Collectors.toList()).toArray(String[]::new);
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
