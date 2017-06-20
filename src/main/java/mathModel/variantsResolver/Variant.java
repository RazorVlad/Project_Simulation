package mathModel.variantsResolver;

import java.util.List;

import mathModel.project.Project;

import org.apache.log4j.Logger;

public class Variant {

	private List<Integer> before;
	private List<Integer> notAfter;
	private List<Project> projects;
	private static Logger LOG = Logger.getLogger(Variant.class);
	
	public Variant() {
	}

	public void setBefore(List<Integer> before) {
		this.before = before;
	}

	public void setNotAfter(List<Integer> notAfter) {
		this.notAfter = notAfter;
	}

	public void setProjects(List<Project> vector) {
		this.projects = vector;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public List<Integer> getBefore() {
		return before;
	}

	public List<Integer> getNotAfter() {
		return notAfter;
	}

	public void proceed() {
		for (Project p : projects) {
			p.proceed();
		}
	}

	public double getAssetsOutcomeAt(int year) {
		double result = 0.;
		for (Project pr : projects) {
			result += pr.getAssetsOutcomeAt(year);
		}
		return result;
	}

	public double getDiscountAt(int year) {
		double result = 0.;
		for (Project p : projects) {
			result += p.getDiscountAt(year);
		}
		return result;
	}

	public double getLumpSumCosts(int year) {
		double result = 0.;
		for (Project p : projects) {
			result += p.getLumpSumCosts(year);
		}
		return result;
	}

	public double getOutputChangeAt(int year) {
		double result = 0.;
		for (Project pr : projects) {
			result += pr.getOutputChangeAt(year);
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Variant) {
			return ((Variant) obj).getProjects().equals(projects);
		}
		return false;
	}

}
