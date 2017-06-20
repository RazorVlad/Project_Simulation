package mathModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Statistics implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Integer> year;
	private List<Double> value;
	private static Logger LOG = Logger.getLogger(Statistics.class);

	public Statistics(int year, double value, int modelLenth) {
		this.value = new ArrayList<Double>();
		this.year = new ArrayList<Integer>();

		for (int j = 0; j < modelLenth; j++) {
			this.year.add(year++);
			this.value.add(value);
		}
	}

	public Statistics() {
		this.value = new ArrayList<Double>();
		this.year = new ArrayList<Integer>();
	}

	public Statistics(List<Integer> year, List<Double> value) {
		this.value = value;
		this.year = year;
	}

	public Statistics(int startYear, double[] value) {
		double[] valueCopy = value.clone();
		this.value = new ArrayList<Double>();
		this.year = new ArrayList<Integer>();

		for (double aValueCopy : valueCopy) {
			this.year.add(startYear++);
			this.value.add(aValueCopy);
		}
	}

	public Statistics(int[] year, double[] value) {
		double[] valueCopy = value.clone();
		int[] yearCopy = year.clone();
		this.value = new ArrayList<Double>();
		this.year = new ArrayList<Integer>();
		for (int i = 0; i < yearCopy.length; i++) {
			this.value.add(valueCopy[i]);
			this.year.add(yearCopy[i]);
		}
	}

	public void add(int year, double value) {
		this.year.add(year);
		this.value.add(value);
	}

	public int getYear(int i) {
		return year.get(i);
	}

	public double getValue(int i) {
		return value.get(i);
	}

	public double getValueAt(int year) {
		return value.get(this.year.indexOf(year));
	}

	public void setValue(int i, double value) {
		this.value.set(i, value);
	}

	public void setValueAt(int year, double value) {
		int index = this.year.indexOf(year);
		this.value.set(index, value);
	}

	public int size() {
		return year.size();
	}

	public double[][] getStatisticsData() {
		int size = value.size();
		double[][] data = new double[2][size];
		for (int i = 0; i < size; i++) {
			data[0][i] = year.get(i);
			data[1][i] = value.get(i);
		}
		return data;
	}

	public static Statistics add(Statistics stat1, Statistics stat2) {
		if (stat1.size() == stat2.size()) {
			Statistics res = new Statistics();
			for (int j = 0; j < stat1.size(); j++) {
				res.add(stat1.getYear(j), stat1.getValue(j) + stat2.getValue(j));
			}
			return res;
		} else {
			LOG.error("Trying to merge statistics with unequal size");
			throw new IllegalArgumentException("trying to merge statistics with unequal size");
		}
	}

	@Override
	public String toString() {
		return "years - " + year + " values - " + value;
	}

}
