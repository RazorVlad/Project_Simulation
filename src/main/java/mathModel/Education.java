package mathModel;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import exceptions.DataRequiredEx;

public class Education implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(Education.class);
	private Population p;
	private Statistics collegeStud; // output +
	private Statistics staffRnD;// output
	private Statistics staffEdu;// output
	private Statistics staffWork; // output
	private Statistics univerStud;// output +

	private Statistics fieldCollegeAppl9;// input +
	private Statistics fieldCollegeAppl12;// input +
	private Statistics statFieldCollegeAppl;// input +
	private Statistics succCollegeGrad;// input +
	private Statistics univerApplAmongCollegeGrad;// input +
	private Statistics fieldUniverAppl12;// input +
	private Statistics statFieldUniverAppl;// input +
	private Statistics succUniverGrad;// input +
	private Statistics collegeGradWork; // input +
	private Statistics univerGradWork;// input +
	private Statistics univerGradEdu; // input +
	private Statistics univerGradRnD;// input +
	private Statistics collegeAppl;// output +
	private Statistics collegeGrad;// output+
	private Statistics univerAppl; // output+
	private Statistics univerGrad;// output+

	private ArrayList<String> errList;

	public Statistics getStaffRnD() {
		return staffRnD;
	}

	public Statistics getStaffWork() {
		return staffWork;
	}

	public Statistics getStaffEdu() {
		return staffEdu;
	}

	// for tests
	public Statistics getuniverAppl() {
		return univerAppl;
	}

	public Statistics getuniverGrad() {
		return univerGrad;
	}

	public Statistics getCollegeAppl() {
		return collegeAppl;
	}

	public Statistics getCollegeGrad() {
		return collegeGrad;
	}

	//

	public Education(Statistics collegeStud0, Statistics fieldCollegeAppl9, Statistics fieldCollegeAppl12,
			Statistics statFieldCollegeAppl, Statistics succCollegeGrad,
			Statistics univerApplAmongCollegeGrad, Statistics fieldUniverAppl12,
			Statistics statFieldUniverAppl, Statistics succUniverGrad, Statistics collegeGradWork,
			Statistics univerGradWork, Statistics univerGradEdu, Statistics univerGradRnD,
			Statistics univerStud) {
		this.collegeStud = collegeStud0;
		this.fieldCollegeAppl9 = fieldCollegeAppl9;
		System.out.println(fieldCollegeAppl9);
		this.fieldCollegeAppl12 = fieldCollegeAppl12;
		this.statFieldCollegeAppl = statFieldCollegeAppl;
		this.succCollegeGrad = succCollegeGrad;
		this.univerStud = univerStud;
		this.univerApplAmongCollegeGrad = univerApplAmongCollegeGrad;
		this.fieldUniverAppl12 = fieldUniverAppl12;
		this.statFieldUniverAppl = statFieldUniverAppl;
		this.succUniverGrad = succUniverGrad;
		this.collegeGradWork = collegeGradWork;
		this.univerGradWork = univerGradWork;
		this.staffWork = new Statistics();
		this.univerGradEdu = univerGradEdu;
		this.staffEdu = new Statistics();
		this.univerGradRnD = univerGradRnD;
		this.staffRnD = new Statistics();
		this.collegeAppl = new Statistics();
		this.collegeGrad = new Statistics();
		this.univerAppl = new Statistics();
		this.univerGrad = new Statistics();
	}

	public void collegeGrad() {
		double value;
		LOG.debug("collegeGrad");
		for (byte i = 0; i < fieldCollegeAppl12.size(); i++) {
			LOG.debug("i = " + i);

			value = p.getCollegeApplAmongGrad9().getValue(i) * fieldCollegeAppl9.getValue(i)
					+ p.getCollegeApplAmongGrad12().getValue(i) * fieldCollegeAppl12.getValue(i);
			LOG.debug("collegeApplValue = " + p.getCollegeApplAmongGrad9().getValue(i) + " * "
					+ fieldCollegeAppl9.getValue(i) + " + " + p.getCollegeApplAmongGrad12().getValue(i)
					+ " * " + fieldCollegeAppl12.getValue(i) + " = " + value);
			collegeAppl.add(fieldCollegeAppl12.getYear(i), value);
			LOG.debug("collegeAppl.add(" + fieldCollegeAppl12.getYear(i) + "," + value + ")");

			if (i < 3) {
				LOG.debug("i < 3");

				value = statFieldCollegeAppl.getValue(i) * succCollegeGrad.getValue(i);
				LOG.debug("collegeGradValue=" + statFieldCollegeAppl.getValue(i) + "*"
						+ succCollegeGrad.getValue(i) + " = " + value);
				collegeGrad.add(fieldCollegeAppl12.getYear(i), value);
				LOG.debug("collegeGrad.add(" + fieldCollegeAppl12.getYear(i) + "," + value + ")");
			} else {
				LOG.debug("i >= 3");

				value = collegeAppl.getValue(i - 3) * succCollegeGrad.getValue(i);
				LOG.debug("collegeGradValue = " + collegeAppl.getValue(i - 3) + "*"
						+ succCollegeGrad.getValue(i) + " = " + value);
				collegeGrad.add(collegeAppl.getYear(i), value);
				LOG.debug("collegeGrad.add(" + collegeAppl.getYear(i) + "," + value + ")");
			}
		}
	}

	public void univerGrad() {
		double value;
		LOG.debug("univerGrad");
		for (byte i = 0; i < collegeGrad.size(); i++) {
			LOG.debug("i = " + i);

			value = collegeGrad.getValue(i) * univerApplAmongCollegeGrad.getValue(i)
					+ p.getUniverApplAmongGrad12().getValue(i) * fieldUniverAppl12.getValue(i);
			LOG.debug("univerApplValue = " + collegeGrad.getValue(i) + " * "
					+ univerApplAmongCollegeGrad.getValue(i) + " + "
					+ p.getUniverApplAmongGrad12().getValue(i) + " * " + fieldUniverAppl12.getValue(i)
					+ " = " + value);
			univerAppl.add(collegeGrad.getYear(i), value);
			LOG.debug("univerAppl.add(" + collegeGrad.getYear(i) + "," + value);

			if (i < 5) {
				LOG.debug("i < 5");
				value = statFieldUniverAppl.getValue(i) * succUniverGrad.getValue(i);
				LOG.debug("value=" + statFieldUniverAppl.getValue(i) + " * " + succUniverGrad.getValue(i)
						+ " = " + value);
				univerGrad.add(collegeGrad.getYear(i), value);
				LOG.debug("univerGrad.add(" + collegeGrad.getYear(i) + "," + value + ")");
			} else {
				LOG.debug("i >= 5");
				value = univerAppl.getValue(i - 5) * succUniverGrad.getValue(i);
				LOG.debug("univerGradValue=" + univerAppl.getValue(i - 5) + " * "
						+ succUniverGrad.getValue(i) + " = " + value);
				univerGrad.add(succUniverGrad.getYear(i), value);
				LOG.debug("univerGrad.add(" + succUniverGrad.getYear(i) + "," + value + ")");
			}
		}
	}

	public void staff() {
		double value;
		LOG.debug("Staff");
		for (byte i = 0; i < fieldCollegeAppl12.size(); i++) {
			LOG.debug("i = " + i);

			value = univerStud.getValue(i) + univerAppl.getValue(i) - univerGrad.getValue(i);
			LOG.debug("univerStudValue=" + univerStud.getValue(i) + " + " + univerAppl.getValue(i) + " - "
					+ univerGrad.getValue(i) + " = " + value);
			univerStud.add(univerAppl.getYear(i), value);
			LOG.debug("univerStud.add(" + univerAppl.getYear(i) + "," + value);

			value = collegeGrad.getValue(i) * collegeGradWork.getValue(i) + univerGrad.getValue(i)
					* univerGradWork.getValue(i);
			LOG.debug("staffWorkValue=" + collegeGrad.getValue(i) + " * " + collegeGradWork.getValue(i)
					+ " + " + univerGrad.getValue(i) + " * " + univerGradWork.getValue(i) + " = " + value);
			staffWork.add(univerGrad.getYear(i), value);
			LOG.debug("staffWork.add(" + univerGrad.getYear(i) + "," + value);

			value = univerGrad.getValue(i) * univerGradEdu.getValue(i);
			LOG.debug("staffEduValue =" + univerGrad.getValue(i) + " * " + univerGradEdu.getValue(i) + " = "
					+ value);
			staffEdu.add(univerGrad.getYear(i), value);
			LOG.debug("staffEdu.add(" + univerGrad.getYear(i) + "," + value);

			value = univerGrad.getValue(i) * univerGradRnD.getValue(i);
			LOG.debug("staffRnDValue =" + univerGrad.getValue(i) + " * " + univerGradRnD);
			staffRnD.add(univerGrad.getYear(i), value);
			LOG.debug("staffRnD.add(" + univerGrad.getYear(i) + "," + value);
		}
	}

	public void collegeStud() {
		double value;
		LOG.debug("CollegeStud");
		for (byte i = 0; i < fieldCollegeAppl12.size(); i++) {
			LOG.debug("i = " + i);

			value = collegeStud.getValue(i) + collegeAppl.getValue(i) - collegeGrad.getValue(i);
			LOG.debug("collegeStudValue = " + collegeStud.getValue(i) + " + " + collegeAppl.getValue(i)
					+ " - " + collegeGrad.getValue(i) + " = " + value);
			LOG.debug("collegeStud.add(" + fieldCollegeAppl12.getYear(i) + "," + value + ")");
			collegeStud.add(fieldCollegeAppl12.getYear(i), value);
		}
	}

	public void proceed() throws DataRequiredEx {
		this.p = ModelObjectsContainer.getInstance().getPopulation();
		if (!checkData()) {
			LOG.error(errList);
			throw new DataRequiredEx(errList);
		}
		collegeGrad();

		collegeStud();

		univerGrad();

		staff();
	}

	private boolean checkData() {
		boolean flag;
		if (fieldCollegeAppl9 == null || fieldCollegeAppl12 == null || statFieldCollegeAppl == null
				|| succCollegeGrad == null || univerApplAmongCollegeGrad == null || fieldUniverAppl12 == null
				|| statFieldUniverAppl == null || succUniverGrad == null || collegeGradWork == null
				|| univerGradWork == null || univerGradEdu == null || univerGradRnD == null) {

			errList = new ArrayList<String>();

			flag = false;

			if (fieldCollegeAppl9 == null) {
				errList.add("дол€ выпускников 9-х классов, поступающих в ¬”«ы 1-2-го ур.  "
						+ "аккредитации наспециальности, относ€щиес€ к данной отрасли");
			}
			if (fieldCollegeAppl12 == null) {
				errList.add("дол€ выпускников 12-х классов, поступающих в ¬”«ы 1-2-го ур. "
						+ "аккредитации наспециальности, относ€щиес€ к данной отрасли");
			}
			if (statFieldCollegeAppl == null) {
				errList.add("статистика поступивших в ¬”«ы 1-2-го ур. аккредитации "
						+ "по данному направлению за 3 предшествующих года†");
			}
			if (succCollegeGrad == null) {
				errList.add("процент студентов ¬”«ов 1-2-го ур. аккредитации,"
						+ "  успешно закончивших обучение");
			}
			if (univerApplAmongCollegeGrad == null) {
				errList.add("процент выпускников ¬”«ов 1-2-го ур. аккредитации, "
						+ "поступающих в ¬”«ы 3-4-го ур. аккредитации");
			}
			if (fieldUniverAppl12 == null) {
				errList.add("процент выпускников 12-х классов, поступающих в ¬”«ы 3-4-го ур. аккредитации "
						+ "на специальности, относ€щиес€ к данной отрасли ");
			}
			if (statFieldUniverAppl == null) {
				errList.add("статистика поступивших в ¬”«ы 3-4-го ур. аккредитации "
						+ "по данному направлению за 5 предшествующих лет");
			}
			if (succUniverGrad == null) {
				errList.add("процент студентов ¬”«ов 3-4-го ур. аккредитации, "
						+ "успешно закончивших обучение");
			}
			if (collegeGradWork == null) {
				errList.add("дол€ выпускников ¬”«ов 1-2-го ур. аккредитации, "
						+ "поступающих на работу в отрасль");
			}
			if (univerGradWork == null) {
				errList.add("дол€ выпускников ¬”«ов 3-4-го ур. аккредитации, "
						+ "поступающих на работу в отрасль");
			}
			if (univerGradEdu == null) {
				errList.add("дол€ выпускников ¬”«ов 3-4-го ур. аккредитации, "
						+ "поступающих на работу в сферу образовани€");
			}
			if (univerGradRnD == null) {
				errList.add("дол€ выпускников ¬”«ов 3-4-го ур. аккредитации, "
						+ "поступающих на работу в сферу Ќ»ќ – ");
			}
		} else {
			flag = true;
		}

		return flag;
	}

	public double getStaffWork(int j) {
		return staffWork.getValue(j);
	}

	public double getStaffEdu(int j) {
		return staffEdu.getValue(j);
	}

	public double getStaffRnD(int j) {
		return staffRnD.getValue(j);
	}

	public double getcollegeStud(int j) {
		return collegeStud.getValue(j);
	}

	public double getCollegeAppl(int j) {
		return collegeAppl.getValue(j);
	}

	public double getCollegeGrad(int j) {
		return collegeGrad.getValue(j);
	}

	public double getuniverStud(int j) {
		return univerStud.getValue(j);
	}

	public double getuniverAppl(int j) {
		return univerAppl.getValue(j);
	}

	public double getuniverGrad(int j) {
		return univerGrad.getValue(j);
	}

	public Statistics getfieldCollegeAppl9() {
		return fieldCollegeAppl9;
	}

	public Statistics getfieldCollegeAppl12() {
		return fieldCollegeAppl12;
	}

	public Statistics getstatFieldCollegeAppl() {
		return statFieldCollegeAppl;
	}

	public Statistics getsuccCollegeGrad() {
		return succCollegeGrad;
	}

	public Statistics getuniverApplAmongCollegeGrad() {
		return univerApplAmongCollegeGrad;
	}

	public Statistics getfieldUniverAppl12() {
		return fieldUniverAppl12;
	}

	public Statistics getstatFieldUniverAppl() {
		return statFieldUniverAppl;
	}

	public Statistics getsuccUniverGrad() {
		return succUniverGrad;
	}

	public Statistics getcollegeGradWork() {
		return collegeGradWork;
	}

	public Statistics getuniverGradWork() {
		return univerGradWork;
	}

	public Statistics getuniverGradEdu() {
		return univerGradEdu;
	}

	public Statistics getuniverGradRnD() {
		return univerGradRnD;
	}
}
