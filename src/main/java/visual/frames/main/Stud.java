package visual.frames.main;

import java.util.ArrayList;
import java.util.List;

import org.jfree.util.Log;

import exceptions.DataRequiredEx;
import mathModel.Education;
import mathModel.ModelObjectsContainer;
import mathModel.Statistics;
import visual.prom_modeling.stud.Stud_panel;

public class Stud {
	static List<String> errList;

	public static void set(Stud_panel stud, int otraslIndex, ModelObjectsContainer model)
			throws DataRequiredEx {
		Log.debug("Сохраняем образование для отрасли " + otraslIndex);
		errList = new ArrayList<String>();
		Statistics collegeStud0 = new Statistics();
		collegeStud0.add(Main.Year, stud.getCollegeStudT0());
		if (stud.getCollegeStudT0() == 0) {
			errList.add("Количество студентов в вузах 1-2 уровня аккредитации в начальном году");
		}
		Statistics fieldCollegeAppl9 = stud.getFieldCollegeApple9();
		Statistics fieldCollegeAppl12 = stud.getFieldCollegeAppl12();
		Statistics fieldUniverAppl12 = stud.getFieldUniverAppl12();
		Statistics univerStud = new Statistics();
		univerStud.add(Main.Year, stud.getUniverStudT0());
		if (stud.getUniverStudT0() == 0) {
			errList.add("Количество студентов в вузах 3-4 уровня аккредитации в начальном году");
		}
		Statistics univerGradWork = stud.getUniverGradWork();
		Statistics univerGradRnD = stud.getUniverGradRnD();
		Statistics univerGradEdu = stud.getUniverGradEdu();
		Statistics collegeGradWork = stud.getCollegeGradWork();
		Statistics succUniverGrad = stud.getSuccUniverGrad();
		Statistics statFieldUniverAppl = stud.getStatFieldUniverAppl();
		Statistics statFieldCollegeAppl = stud.getStatFieldCollegeAppl();
		Statistics univerApplAmongCollegeGrad = stud.getUniverApplAmongCollegeGrad();
		Statistics succCollegeGrad = stud.getSuccCollegeGrad();

		Education edu = new Education(collegeStud0, fieldCollegeAppl9, fieldCollegeAppl12,
				statFieldCollegeAppl, succCollegeGrad, univerApplAmongCollegeGrad, fieldUniverAppl12,
				statFieldUniverAppl, succUniverGrad, collegeGradWork, univerGradWork, univerGradEdu,
				univerGradRnD, univerStud);

		if (errList.isEmpty()) {
			model.setEducation(otraslIndex, edu);
		} else {
			throw new DataRequiredEx(errList);
		}
	}
}
