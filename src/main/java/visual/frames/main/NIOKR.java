package visual.frames.main;

import java.util.ArrayList;
import java.util.List;

import mathModel.ModelObjectsContainer;
import mathModel.RnD;
import mathModel.Statistics;
import mathModel.manFun.ManFun;

import org.apache.log4j.Logger;

import visual.prom_modeling.niokr.NIOKR_panel;
import visual.prom_modeling.niokr.NIOKR_panel_param;
import visual.save.TableAndGrafSave;
import visual.save.VisualObjectsContainer;
import exceptions.DataRequiredEx;

public class NIOKR {
	private static List<String> errList;
	private static Logger LOG = Logger.getLogger(NIOKR.class);

	public static void set(NIOKR_panel niokr, int otraslIndex, ModelObjectsContainer model)
			throws DataRequiredEx {
		LOG.debug("Сохраняем НИКОР для отрасли " + otraslIndex);
		errList = new ArrayList<String>();

		NIOKR_panel_param nir = niokr.getNIR();
		NIOKR_panel_param okr = niokr.getOKR();

		ManFun mf = new ManFun();
		mf.setA(nir.getA());
		if (nir.getA() == null) {
			errList.add("Коэффициент А для НИР");
		}
		mf.setAlfa(nir.getAlfa());
		if (nir.getAlfa() == null) {
			errList.add("Коэффициент Аlfa для НИР");
		}
		mf.setBeta(nir.getBeta());
		if (nir.getBeta() == null) {
			errList.add("Коэффициент Beta для НИР");
		}

		mf.setMape(nir.getMape());
		mf.setR(nir.getR2());

		Statistics assets0 = new Statistics();
		assets0.add(Main.Year, nir.getTopPanel().getAssetsT0());
		if (nir.getTopPanel().getAssetsT0() == 0) {
			errList.add("Основные фонды в сфере НИР в начальном году");
		}
		Statistics staff0 = new Statistics();

		staff0.add(Main.Year, nir.getTopPanel().getStaff());
		if (nir.getTopPanel().getStaff() == 0) {
			errList.add("Кадры в сфере НИР в начальном году");
		}
		Statistics partOutcomeStaff = nir.getTopPanel().getPartOutcomeStaff();
		Statistics partIncomeStaff = niokr.getpartIncomeStaff();
		Statistics coefInvest = nir.getTopPanel().getCoefInvest();
		double finInt = niokr.getfinInt();
		if (finInt == 0) {
			errList.add("Объем финансирования НИР за счет отечественных заказчиков в начальном году");
		}
		Statistics coefFinInt = niokr.getcoefFinInt();
		double finExt = niokr.getfinExt();
		if (finExt == 0) {
			errList.add("Объем финансирования НИР за счет иностранных заказчиков в начальном году");
		}
		Statistics coefFinExt = niokr.getcoefFinExt();
		Statistics coefFinPart = niokr.getcoefFinPart();
		Statistics coefNew = nir.getTopPanel().getCoefNew();
		Statistics coefObs = nir.getTopPanel().getCoefObs();
		Statistics coefFin = nir.getTopPanel().getCoefFin();
		Statistics coefProd = nir.getTopPanel().getCoefProd();

		RnD r = new RnD(otraslIndex, mf, staff0, partIncomeStaff, partOutcomeStaff, assets0, coefInvest,
				finInt, coefFinInt, finExt, coefFinExt, coefFinPart, coefNew, coefObs, coefFin, coefProd,
				false);
		if (errList.isEmpty()) {
			model.setRnd_R(otraslIndex, r);
		} else {
		}
		mf = new ManFun();
		mf.setA(okr.getA());
		if (okr.getA() == null) {
			errList.add("Коэффициент А для ОКР");
		}
		mf.setAlfa(okr.getAlfa());
		if (okr.getAlfa() == null) {
			errList.add("Коэффициент Alfa для ОКР");
		}
		mf.setBeta(okr.getBeta());
		if (okr.getBeta() == null) {
			errList.add("Коэффициент Beta для ОКР");
		}
		mf.setGamma(okr.getGamma());
		if (okr.getGamma() == null) {
			errList.add("Коэффициент Gamma для ОКР");
		}

		mf.setMape(okr.getMape());
		mf.setR(okr.getR2());

		assets0 = new Statistics();
		assets0.add(Main.Year, okr.getTopPanel().getAssetsT0());
		if (okr.getTopPanel().getAssetsT0() == 0) {
			errList.add("assets0 для ОКР");
		}

		staff0 = new Statistics();
		staff0.add(Main.Year, okr.getTopPanel().getStaff());
		if (okr.getTopPanel().getStaff() == 0) {
			errList.add("staff0 для ОКР");
		}

		coefFin = okr.getTopPanel().getCoefFin();
		coefInvest = okr.getTopPanel().getCoefInvest();
		coefNew = okr.getTopPanel().getCoefNew();
		coefObs = okr.getTopPanel().getCoefObs();
		coefProd = okr.getTopPanel().getCoefProd();
		partOutcomeStaff = okr.getTopPanel().getPartOutcomeStaff();

		RnD d = new RnD(otraslIndex, mf, staff0, partIncomeStaff, partOutcomeStaff, assets0, coefInvest,
				finInt, coefFinInt, finExt, coefFinExt, coefFinPart, coefNew, coefObs, coefFin, coefProd,
				true);

		if (errList.isEmpty()) {
			model.setRnd_D(otraslIndex, d);
		} else {
			LOG.error("Необходимо ввести или исправить следующие данные: " + errList);
			throw new DataRequiredEx(errList);
		}

		TableAndGrafSave data = VisualObjectsContainer.getInstance().getTableAndGraf(otraslIndex);

		data.setNir(nir.getTable().getTable());
		data.setOkr(okr.getTable().getTable());
	}
}
