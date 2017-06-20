package mathModel;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import exceptions.DataRequiredEx;

public class Population implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(Population.class);
	private Statistics collegeApplAmongGrad9;// output
	private Statistics collegeApplAmongGrad12;// output
	private Statistics univerApplAmongGrad12;// output
	private Statistics schoolGrad9 = new Statistics();// output
	private Statistics schoolGrad12 = new Statistics();// output

	private Statistics newborns;// input
	private double deathCoef;// input
	private Statistics collegeAppl9;// input
	private Statistics collegeApplAfter12;// input
	private Statistics univerApplAfter12;// input

	private ArrayList<String> errList;

	public Statistics getSchoolGrad9() {
		return schoolGrad9;
	}

	public Statistics getSchoolGrad12() {
		return schoolGrad12;
	}

	public Population(Statistics newborns, double deathCoef, Statistics collegeAppl9,
			Statistics collegeApplAfter12, Statistics univerApplAfter12) {
		this.collegeApplAmongGrad9 = new Statistics();
		this.newborns = newborns;
		this.deathCoef = deathCoef;
		this.collegeAppl9 = collegeAppl9;
		this.collegeApplAmongGrad12 = new Statistics();
		this.collegeApplAfter12 = collegeApplAfter12;
		this.univerApplAmongGrad12 = new Statistics();
		this.univerApplAfter12 = univerApplAfter12;
	}

	public Statistics getCollegeApplAmongGrad9() {
		return collegeApplAmongGrad9;
	}

	public Statistics getCollegeApplAmongGrad12() {
		return collegeApplAmongGrad12;
	}

	public Statistics getUniverApplAmongGrad12() {
		return univerApplAmongGrad12;
	}

	public ArrayList<String> getErrList() {
		return errList;
	}

	public double[][] getnewborns() {
		return newborns.getStatisticsData();
	}

	public double[][] getcollegeAppl9() {
		return collegeAppl9.getStatisticsData();
	}

	public double[][] getcollegeApplAfter12() {
		return collegeApplAfter12.getStatisticsData();
	}

	public double[][] getuniverApplAfter12() {
		return univerApplAfter12.getStatisticsData();
	}

	public double getdeathCoef() {
		return deathCoef;
	}

	public void proceed() throws DataRequiredEx {
		double value;
		if (!checkData()) {
			LOG.error("CheckData error: " + errList);
			throw new DataRequiredEx(errList);
		}
		LOG.debug("Input data checked");
		LOG.debug("deathCoef = " + deathCoef);
		LOG.debug("schoolGrad9(i) = newborns(collegeAppl9.getYear(i) - 12) * (1 - deathCoef)");
		for (byte i = 0; i < collegeApplAfter12.size() + 3; i++) {
			LOG.debug("i = " + i);
			value = newborns.getValueAt(collegeAppl9.getYear(i) - 12) * (1 - deathCoef);
			LOG.debug("schoolGrad9(i) = " + newborns.getValueAt(collegeAppl9.getYear(i) - 12) + " * (1 - "
					+ deathCoef + ") = " + value);
			schoolGrad9.add(collegeAppl9.getYear(i), value);
			LOG.debug("schoolGrad9.add(" + collegeAppl9.getYear(i) + "," + value + ")");
		}

		LOG.debug("schoolGrad9(i) = newborns(collegeAppl9.getYear(i) - 15) * (1 - deathCoef)");
		LOG.debug("schoolGrad12(i) = schoolGrad9Value * (1.0 - collegeAppl9(i))");
		LOG.debug("newbornsValue = newborns(collegeApplAfter12(i) - 15);");
		LOG.debug("collegeApplAmongGrad9(i) = newbornsValue * (1 - deathCoef) * collegeAppl9(i + 3)");
		LOG.debug("collegeApplAmongGrad12(i) = schoolGrad12(i) * collegeApplAfter12(i)");
		LOG.debug("univerApplAmongGrad12(i) = univerApplAfter12(i) * schoolGrad12(i)");
		LOG.debug("deathCoef = " + deathCoef);

		for (byte i = 0; i < this.collegeApplAfter12.size(); i++) {
			LOG.debug("i = " + i);

			double schoolGrad9Value = newborns.getValueAt(collegeAppl9.getYear(i) - 15) * (1 - deathCoef);
			LOG.debug("schoolGrad9(i) = newborns(" + collegeAppl9.getYear(i) + " - 15) * (1 - " + deathCoef
					+ ") = " + schoolGrad9Value);

			double schoolGrad12Value = schoolGrad9Value * (1.0 - collegeAppl9.getValue(i));
			LOG.debug("schoolGrad12(i) = " + schoolGrad9Value + " * (1.0 - " + collegeAppl9.getValue(i)
					+ ") = " + schoolGrad12Value);
			schoolGrad12.add(collegeApplAfter12.getYear(i), schoolGrad12Value);
			LOG.debug("schoolGrad12.add(" + collegeApplAfter12.getYear(i) + "," + schoolGrad12Value + ") = "
					+ schoolGrad12Value);

			double newbornsValue = newborns.getValueAt(collegeApplAfter12.getYear(i) - 15);
			LOG.debug("newbornsValue = newborns(" + collegeApplAfter12.getYear(i) + " - 15) = "
					+ newbornsValue);

			double collegeApplAmongGrad9Value = newbornsValue * (1 - deathCoef)
					* collegeAppl9.getValue(i + 3);
			LOG.debug("collegeApplAmongGrad9(i) = " + newbornsValue + " * (1 - " + deathCoef + ") * "
					+ collegeAppl9.getValue(i + 3) + " = " + collegeApplAmongGrad9Value);
			collegeApplAmongGrad9.add(collegeApplAfter12.getYear(i), collegeApplAmongGrad9Value);
			LOG.debug("collegeApplAmongGrad9.add(" + collegeApplAfter12.getYear(i) + ","
					+ collegeApplAmongGrad9Value + ")");

			double collegeApplAmongGrad12Value = schoolGrad12.getValue(i) * collegeApplAfter12.getValue(i);
			LOG.debug("collegeApplAmongGrad12(i) = " + schoolGrad12.getValue(i) + " * "
					+ collegeApplAfter12.getValue(i) + " = " + collegeApplAmongGrad12Value);
			collegeApplAmongGrad12.add(schoolGrad12.getYear(i), collegeApplAmongGrad12Value);
			LOG.debug("collegeApplAmongGrad12.add(" + schoolGrad12.getYear(i) + ","
					+ collegeApplAmongGrad12Value + ")");

			double univerApplAmongGrad12Value = univerApplAfter12.getValue(i) * schoolGrad12.getValue(i);
			LOG.debug("univerApplAmongGrad12(i) = " + univerApplAfter12.getValue(i) + " * "
					+ schoolGrad12.getValue(i) + " = " + univerApplAmongGrad12Value);
			univerApplAmongGrad12.add(univerApplAfter12.getYear(i), univerApplAmongGrad12Value);
			LOG.debug("univerApplAmongGrad12.add(" + univerApplAfter12.getYear(i) + ","
					+ univerApplAmongGrad12Value + ")");
		}
	}

	private boolean checkData() {
		boolean flag;
		if (newborns == null || deathCoef > 1 || deathCoef < 0 || collegeAppl9 == null
				|| collegeApplAfter12 == null || univerApplAfter12 == null) {
			errList = new ArrayList<String>();
			flag = false;

		} else
			flag = true;
		if (newborns == null) {
			LOG.error("newborns statistics input data error");
			errList.add("Статистика новорожденных за 18 лет");
		}
		if (deathCoef < 0 || deathCoef > 1) {
			LOG.error("death coefficient input data error");
			errList.add("Коэффициент смертности");
		}
		if (collegeAppl9 == null) {
			LOG.error("College appl after 9 classes input data error");
			errList.add("Статистика поступающих в ВУЗы 1-2-го уровня аккредитации после 9-ти классов");
		}
		if (collegeApplAfter12 == null) {
			LOG.error("College appl after 11 classes input data error");
			errList.add("Статистика поступающих в ВУЗы 1-2-го уровня аккредитации после 11-ти классов");
		}
		if (univerApplAfter12 == null) {
			LOG.error("University appl after 11 classes input data error");
			errList.add("Статистика поступающих в ВУЗы 3-4-го уровня аккредитации после 11-ти классов");
		}
		return flag;
	}
}
