package code.java.graphs.build_order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private List<Project> dependencies;
    private Map<String, Project> map;

    public Graph() {
        this.dependencies = new ArrayList<>();
        this.map = new HashMap<>();
    }

    public Project getOrCreateProject(String name) {
        if (!map.containsKey(name)) {
            Project project = new Project(name);
            dependencies.add(project);
            map.put(name, project);
        }
        return map.get(name);
    }

    public void addEdge(String base, String dependent) {
        Project project = getOrCreateProject(base);
        Project dependency = getOrCreateProject(dependent);
        project.addDependency(dependency);
    }

    public List<Project> getDependencies() {
        return this.dependencies;
    }

}
