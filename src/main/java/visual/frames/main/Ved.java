package visual.frames.main;

import java.util.ArrayList;
import java.util.List;

import org.jfree.util.Log;

import exceptions.DataRequiredEx;
import mathModel.ModelObjectsContainer;
import mathModel.Statistics;
import mathModel.manFun.ManFun;
import mathModel.manufacturing.Manufacturing;
import visual.prom_modeling.ved.Ved_panel;
import visual.prom_modeling.ved.Ved_panel_params;
import visual.save.TableAndGrafSave;
import visual.save.VisualObjectsContainer;

public class Ved {
	static List<String> errList;

	public static void set(int vedIndex, int otraslIndex, Ved_panel ved, ModelObjectsContainer model)
			throws DataRequiredEx {
		Log.info("Сохраняем ВЭД " + vedIndex);
		errList = new ArrayList<String>();

		Ved_panel_params inn = ved.getInn();
		Ved_panel_params ord = ved.getOrd();

		ManFun mf = new ManFun();
		mf.setA(ved.getCoefsInn().getANew());
		if (ved.getCoefsInn().getANew() == null) {
			errList.add("Коэффициент А для инновационного производства");
		}
		mf.setAlfa(ved.getCoefsInn().getAlfaNew());
		if (ved.getCoefsInn().getAlfaNew() == null) {
			errList.add("Коэффициент Alfa для инновационного производства");
		}
		mf.setBeta(ved.getCoefsInn().getBetaNew());
		if (ved.getCoefsInn().getBetaNew() == null) {
			errList.add("Коэффициент Beta для инновационного производства");
		}
		mf.setGamma(ved.getCoefsInn().getGammaNew());
		if (ved.getCoefsInn().getGammaNew() == null) {
			errList.add("Коэффициент Gamma для инновационного производства");
		}

		mf.setMape(ved.getCoefsInn().getMAPE());
		mf.setR(ved.getCoefsInn().getR2());

		double coefSalaryOut = 0.363;
		Statistics coefOutcome = inn.getCoefOutcome();
		Statistics coefExtraCost = ved.getInnIndexes().getCoefExtraCost();
		Statistics coefProdPrice = ved.getInnIndexes().getCoefProdPrice();
		Statistics coefRawPrice = ved.getInnIndexes().getCoefRawPrice();
		Statistics coefSalaryChange = ved.getInnIndexes().getCoefSalaryChange();

		Statistics extraCost0 = new Statistics();
		extraCost0.add(Main.Year, ved.getInnIndexes().getExtraCost());
		if (ved.getInnIndexes().getExtraCost() == 0) {
			errList.add("extraCost0 для инновационного производства");
		}

		Statistics prodPrice0 = new Statistics();
		prodPrice0.add(Main.Year, ved.getInnIndexes().getProdPrice());
		if (ved.getInnIndexes().getProdPrice() == 0) {
			errList.add("prodPrice0 для инновационного производства");
		}

		Statistics rawPrice0 = new Statistics();
		rawPrice0.add(Main.Year, ved.getInnIndexes().getRawPriceT0());
		if (ved.getInnIndexes().getRawPriceT0() == 0) {
			errList.add("rawPrice0 для инновационного производства");
		}

		double staff0 = ved.getInn().getStaffTo();
		if (staff0 == 0) {
			errList.add("staff0 для инновационного производства");
		}
		double assest0 = inn.getAssetsT0();
		if (assest0 == 0) {
			errList.add("assest0 для инновационного производства");
		}

		Statistics coefAmort1 = inn.getCoefAmort1();
		Statistics coefAmort2 = inn.getCoefAmort2();
		Statistics coefObsIntangibles = inn.getCoefObsIntangibles();
		Statistics coefWC = inn.getCoefWC();
		Statistics workingPart = inn.getWorkingPart();

		double salary0 = ved.getInnIndexes().getSalaryT0();
		if (salary0 == 0) {
			errList.add("salary0 для инновационного производства");
		}

		Statistics coefObsFunds = inn.getCoefObsFunds();
		Statistics partForFunds = inn.getPartForFunds();
		Statistics qPart = inn.getqPart();
		Statistics workingIntangiblesPart = inn.getWorkingIntengiblesPart();
		// Statistics intangibles0 = inn.getIntangibles();
		double intangibles0 = inn.getIntangibles();
		if (intangibles0 == 0) {
			errList.add("intangibles0 для инновационного производства");
		}
		// Statistics wc = inn.getWc();
		double wc = inn.getWc();
		if (wc == 0) {
			errList.add("wc для инновационного производства");
		}
		Statistics partAmort = ord.getPartAmort();
		Statistics column = ved.getFirstColumnOfTableInn();
		Statistics potential0 = new Statistics();
		potential0.add(column.getYear(0), column.getValue(0));
		Statistics fieldPart = ord.getqPart();
		Manufacturing manufInn = new Manufacturing(true, vedIndex, otraslIndex, mf, coefOutcome, fieldPart,
				qPart, coefAmort1, coefAmort2, workingPart, workingIntangiblesPart, coefObsIntangibles,
				coefProdPrice, rawPrice0, coefRawPrice, coefWC, prodPrice0, intangibles0, potential0,
				salary0, coefSalaryChange, coefSalaryOut, coefExtraCost, extraCost0, staff0, assest0,
				coefObsFunds, partForFunds, partAmort, wc);

		if (errList.isEmpty()) {
			model.setVedInn(vedIndex, manufInn);
		}

		mf = new ManFun();
		mf.setA(ved.getCoefsOrd().getANew());
		if (ved.getCoefsInn().getBetaNew() == null) {
			errList.add("Коэффициент A для рядового производства");
		}
		mf.setAlfa(ved.getCoefsOrd().getAlfaNew());
		if (ved.getCoefsInn().getBetaNew() == null) {
			errList.add("Коэффициент Alfa для рядового производства");
		}
		mf.setBeta(ved.getCoefsOrd().getBetaNew());
		if (ved.getCoefsInn().getBetaNew() == null) {
			errList.add("Коэффициент Beta для рядового производства");
		}

		mf.setMape(ved.getCoefsOrd().getMAPE());
		mf.setR(ved.getCoefsOrd().getR2());

		coefOutcome = ord.getCoefOutcome();
		coefExtraCost = ved.getOrdIndexes().getCoefExtraCost();
		coefProdPrice = ved.getOrdIndexes().getCoefProdPrice();
		coefRawPrice = ved.getOrdIndexes().getCoefRawPrice();
		coefSalaryChange = ved.getOrdIndexes().getCoefSalaryChange();

		extraCost0 = new Statistics();
		extraCost0.add(Main.Year, ved.getOrdIndexes().getExtraCost());
		if (ved.getOrdIndexes().getExtraCost() == 0) {
			errList.add("extraCost0 для рядового производства");
		}

		prodPrice0 = new Statistics();
		prodPrice0.add(Main.Year, ved.getOrdIndexes().getProdPrice());
		if (ved.getOrdIndexes().getProdPrice() == 0) {
			errList.add("prodPrice0 для рядового производства");
		}

		rawPrice0 = new Statistics();
		rawPrice0.add(Main.Year, ved.getOrdIndexes().getRawPriceT0());
		if (ved.getOrdIndexes().getRawPriceT0() == 0) {
			errList.add("rawPrice0 A для рядового производства");
		}

		staff0 = ved.getOrd().getStaffTo();
		if (staff0 == 0) {
			errList.add("staff0 A для рядового производства");
		}
		salary0 = ved.getOrdIndexes().getSalaryT0();
		if (salary0 == 0) {
			errList.add("salary0 A для рядового производства");
		}
		assest0 = ord.getAssetsT0();
		if (assest0 == 0) {
			errList.add("assest0 A для рядового производства");
		}
		coefAmort1 = ord.getCoefAmort1();
		coefAmort2 = ord.getCoefAmort2();
		coefObsIntangibles = inn.getCoefObsIntangibles();
		coefWC = ord.getCoefWC();
		coefObsFunds = ord.getCoefObsFunds();
		partForFunds = ord.getPartForFunds();
		qPart = ord.getqPart();
		workingIntangiblesPart = inn.getWorkingIntengiblesPart();
		intangibles0 = inn.getIntangibles();
		if (intangibles0 == 0) {
			errList.add("intangibles0 A для рядового производства");
		}
		partAmort = ord.getPartAmort();
		wc = ord.getWc();
		if (wc == 0) {
			errList.add("wc A для рядового производства");
		}
		workingPart = inn.getWorkingPart();
		column = ved.getFirstColumnOfTableOrd();

		potential0 = new Statistics();
		potential0.add(column.getYear(0), column.getValue(0));
		if (column.getValue(0) == 0) {
			errList.add("potential0 для рядового производства");
		}

		Manufacturing manufOrd = new Manufacturing(false, vedIndex, otraslIndex, mf, coefOutcome, fieldPart,
				qPart, coefAmort1, coefAmort2, workingPart, workingIntangiblesPart, coefObsIntangibles,
				coefProdPrice, rawPrice0, coefRawPrice, coefWC, prodPrice0, intangibles0, potential0,
				salary0, coefSalaryChange, coefSalaryOut, coefExtraCost, extraCost0, staff0, assest0,
				coefObsFunds, partForFunds, partAmort, wc);

		if (errList.isEmpty()) {
			model.setVedOrd(vedIndex, manufOrd);
		} else {
			throw new DataRequiredEx(errList);
		}

		TableAndGrafSave data = VisualObjectsContainer.getInstance().getTableAndGraf(vedIndex);
		data.setInn(ved.getTableAndGrafInn().getTable());
		data.setOrd(ved.getTableAndGrafOrd().getTable());

	}
}
