package mathModel.variantsResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import mathModel.Statistics;
import mathModel.project.Project;

public class VRObjectsContainer {
	static VRObjectsContainer instance;

	private static Logger LOG = Logger.getLogger(VRObjectsContainer.class);

	private Statistics alfa;
	private int startYear;
	private int duration;
	private int aimYear;
	private int maxDuration;
	private int lastTime;
	private List<Project> projects;
	private double norm = 1.0;
	private double income;
	private double outcome;
	private double innov;
	private List<Variant> variants = new ArrayList<Variant>();
	private List<Integer> years = new ArrayList<Integer>();

	public List<Integer> getYears() {
		return years;
	}

	public void setYears(List<Integer> years) {
		this.years = years;
	}

	private Map<Integer, Statistics> govOrderOrd = new HashMap<Integer, Statistics>();

	public void setGovOrderOrd(int ved, Statistics value) {
		govOrderOrd.put(ved, value);
	}

	public void setGovOrderInn(int ved, Statistics value) {
		govOrderInn.put(ved, value);
	}

	public Map<Integer, Statistics> getGovOrderOrd() {
		return govOrderOrd;
	}

	public void setGovOrderOrd(Map<Integer, Statistics> govOrderOrd) {
		this.govOrderOrd = govOrderOrd;
	}

	public Map<Integer, Statistics> getGovOrderInn() {
		return govOrderInn;
	}

	public void setGovOrderInn(Map<Integer, Statistics> govOrderInn) {
		this.govOrderInn = govOrderInn;
	}

	public void setVariants(List<Variant> variants) {
		this.variants = variants;
	}

	private Map<Integer, Statistics> govOrderInn = new HashMap<Integer, Statistics>();
	private Map<Integer, Integer> chosenVariantsIndexes = new HashMap<Integer, Integer>();

	public void putChosenVariantsIndex(int t, int variant) {
		chosenVariantsIndexes.put(t, variant);
	}

	public void addVariant(Variant var) {
		variants.add(var);
	}

	public List<Variant> getVariants() {
		return variants;
	}

	public int getVariantIndexAt(int year) {
		return chosenVariantsIndexes.get(year);
	}

	public Variant getVariantByIndex(int index) {
		return variants.get(index);
	}

	public static synchronized VRObjectsContainer getInstance() {
		return instance;
	}

	public Map<Integer, Integer> getChosenVariantsIndexes() {
		return chosenVariantsIndexes;
	}

	public void setChosenVariantsIndexes(Map<Integer, Integer> chosenVariantsIndexes) {
		this.chosenVariantsIndexes = chosenVariantsIndexes;
	}

	public int getLastTime() {
		return lastTime;
	}

	public void setLastTime(int lastTime) {
		this.lastTime = lastTime;
	}

	public double getInnov() {
		return innov;
	}

	public void setInnov(double innov) {
		this.innov = innov;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getOutcome() {
		return outcome;
	}

	public void setOutcome(double outcome) {
		this.outcome = outcome;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Statistics getAlfa() {
		return alfa;
	}

	public void setAlfa(Statistics alfa) {
		this.alfa = alfa;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getNorm() {
		return norm;
	}

	public void setNorm(double norm) {
		this.norm = norm;
	}

	public int getAimYear() {
		return aimYear;
	}

	public void setAimYear(int aimYear) {
		this.aimYear = aimYear;
	}

	public int getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(int maxDuration) {
		this.maxDuration = maxDuration;
	}

	public void setVRObjectsContainer(int duration, double norm, int aimYear, int maxDuration,
			List<Project> projects, int lastTime) {
		VRObjectsContainer instance = new VRObjectsContainer();
		instance.setDuration(duration);
		instance.setAimYear(aimYear);
		instance.setNorm(norm);
		instance.setStartYear(getStartYear(projects));
		instance.setMaxDuration(maxDuration);
		instance.setProjects(projects);
		instance.setLastTime(lastTime);
	}

	public double getAlfaAt(int t) {
		Statistics alfa = instance.getAlfa();
		if (alfa == null) {
			LOG.debug("alfa = null ==> create new alfa");
			LOG.debug("alfa(year) = norm^(aimYear - year)");
			LOG.debug("norm = " + instance.getNorm());
			alfa = new Statistics();
			double alfaValue = 0.;
			for (int time = instance.getStartYear(); time < instance.getDuration(); time++) {
				alfaValue = Math.pow(instance.getNorm(), instance.getAimYear() - time);
				alfa.add(time, alfaValue);
				LOG.debug("alfa(" + time + ") = " + alfaValue);
			}
			instance.setAlfa(alfa);
		}
		return alfa.getValueAt(t);
	}

	int getL(int t) {
		int result = 1 - t;
		if (t + instance.getMaxDuration() - 1 < instance.getDuration()) {
			result += t + instance.getMaxDuration() - 1;
		} else {
			result += instance.getDuration();
		}
		return result;
	}

	private int getStartYear(List<Project> projects) {
		int result = Integer.MAX_VALUE;
		for (Project p : projects) {
			if (p.getBeginYear() < result) {
				result = p.getBeginYear();
			}
		}
		return result;
	}
}
