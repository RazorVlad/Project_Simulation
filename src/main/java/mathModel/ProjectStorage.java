package mathModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mathModel.project.Project;

public class ProjectStorage {

	private Map<String, Project> projects = new HashMap<String, Project>();

	private static ProjectStorage instance;

	private ProjectStorage() {
	}

	public static final synchronized ProjectStorage getInstance() {
		if (instance == null) {
			instance = new ProjectStorage();
		}
		return instance;
	}

	public void add(Project pr) {
		projects.put(pr.getName(), pr);
	}

	public Project getProject(String name) {
		return projects.get(name);
	}

	public Collection<Project> getProjects() {
		return projects.values();
	}

}
