package code.java.trees_and_graphs.build_order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {

    public enum State {
        UNVISITED, VISITING, VISITED
    }

    private String name;
    public State state = State.UNVISITED;
    private List<Project> dependencies;
    private Map<String, Project> map;
    private int noOfDependencies = 0;

    public Project(String name) {
        this.name = name;
        this.dependencies = new ArrayList<>();
        this.map = new HashMap<>();
    }

    public void addDependency(Project project) {
        if (!map.containsKey(project.getName())) {
            dependencies.add(project);
            map.put(project.getName(), project);
            project.incrementDependencies();
        }
    }

    public String getName() {
        return this.name;
    }

    public List<Project> getDependencies() {
        return this.dependencies;
    }

    public int getNoOfDependencies() {
        return this.noOfDependencies;
    }

    public void incrementDependencies() {
        this.noOfDependencies++;
    }

    public void decrementDependencies() {
        this.noOfDependencies--;
    }

}
