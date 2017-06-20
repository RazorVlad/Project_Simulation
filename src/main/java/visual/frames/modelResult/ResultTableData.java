package visual.frames.modelResult;

import mathModel.Education;
import mathModel.RnD;
import mathModel.manufacturing.Manufacturing;
import visual.frames.main.Main;

public class ResultTableData {
	public static String[][] setStud(int vedID, Education edu, String[][] dataValues) {
		// Сфера образования
		for (int j = 2; j < Main.ModelLenght + 2; j++) {
			dataValues[1][j] = String.valueOf(edu.getStaffWork(j));
			dataValues[2][j] = String.valueOf(edu.getStaffEdu(j));
			dataValues[3][j] = String.valueOf(edu.getStaffRnD(j));
			dataValues[5][j] = String.valueOf(edu.getcollegeStud(j));
			dataValues[6][j] = String.valueOf(edu.getCollegeAppl(j));
			dataValues[7][j] = String.valueOf(edu.getCollegeGrad(j));
			dataValues[9][j] = String.valueOf(edu.getuniverStud(j));
			dataValues[11][j] = String.valueOf(edu.getuniverAppl(j));
			dataValues[12][j] = String.valueOf(edu.getuniverGrad(j));
		}

		return dataValues;
	}

	// Сфера ниокр
	public static String[][] setRnD_R(RnD rndR, String[][] dataValues) {

		// Научно-исслед. сектор

		for (int j = 2; j < Main.ModelLenght + 2; j++) {
			dataValues[15][j] = String.valueOf(rndR.getStaff(j));
			dataValues[16][j] = String.valueOf(rndR.getIncomeStaff(j));
			dataValues[17][j] = String.valueOf(rndR.getOutcomeStaff(j));
			dataValues[18][j] = String.valueOf(rndR.getAssets(j));
			dataValues[19][j] = String.valueOf(rndR.getAssetsIncome(j));
			dataValues[20][j] = String.valueOf(rndR.getAssetsOutcome(j));
			dataValues[21][j] = String.valueOf(rndR.getReal(j));
		}
		return dataValues;
	}

	public static String[][] setRnD_D(RnD rndD, String dataValues[][]) {
		// Опытно-констр. сектор
		for (int j = 2; j < Main.ModelLenght + 2; j++) {
			dataValues[15][j] = String.valueOf(rndD.getStaff(j));
			dataValues[16][j] = String.valueOf(rndD.getIncomeStaff(j));
			dataValues[17][j] = String.valueOf(rndD.getOutcomeStaff(j));
			dataValues[18][j] = String.valueOf(rndD.getAssets(j));
			dataValues[19][j] = String.valueOf(rndD.getAssetsIncome(j));
			dataValues[20][j] = String.valueOf(rndD.getAssetsOutcome(j));
			dataValues[21][j] = String.valueOf(rndD.getReal(j));
		}
		return dataValues;
	}

	public static String[][] setVed(Manufacturing ved, int startIndex, String[][] dataValues) {
		// Сфера производства
		// Кадры

		for (int j = 2; j < Main.ModelLenght + 2; j++) {
			dataValues[startIndex + 2][j] = String.valueOf(ved.getStaff(j));
			dataValues[startIndex + 3][j] = String.valueOf(ved.getStaffIncome(j));
			dataValues[startIndex + 4][j] = String.valueOf(ved.getStaffOutcome(j));
			dataValues[startIndex + 5][j] = String.valueOf(ved.getEduAbility(j));
			dataValues[startIndex + 6][j] = String.valueOf(ved.getStaffDemand(j));
			dataValues[startIndex + 7][j] = String.valueOf(ved.getStaffDeficit(j));
			dataValues[startIndex + 8][j] = String.valueOf(ved.getStaffExcess(j));
			// основные фонды
			dataValues[startIndex + 10][j] = String.valueOf(ved.getAssets(j));
			dataValues[startIndex + 11][j] = String.valueOf(ved.getAssetsIncome(j));
			dataValues[startIndex + 12][j] = String.valueOf(ved.getAssetsOutcome(j));
			// оборотные средства
			dataValues[startIndex + 14][j] = String.valueOf(ved.getWc(j));
			dataValues[startIndex + 15][j] = String.valueOf(ved.getWcIncome(j));
			dataValues[startIndex + 16][j] = String.valueOf(ved.getWcOutcome(j));
			// нематер. активы
			dataValues[startIndex + 18][j] = String.valueOf(ved.getIntangibles(j));
			dataValues[startIndex + 19][j] = String.valueOf(ved.getIntangiblesIncome(j));
			dataValues[startIndex + 20][j] = String.valueOf(ved.getIntangiblesOutcome(j));
			// производство(выпуск продукции)

			dataValues[startIndex + 22][j] = String.valueOf(ved.getPotential(j));
			// for (int j = 2; j < Main.ModelLenght + 2; j++)
			// dataValues[startIndex + 23][j] = String
			// .valueOf(fi.getDemand(j, ved.getIndexInFieldsInteraction()));
			// for(int
			// j=2;j<Main.ModelLenght+2;j++)dataValues[startIndex+24][j]=String.valueOf(fieldsInteraction.getOutcome(j));
			// for(int
			// j=2;j<Main.ModelLenght+2;j++)dataValues[startIndex+25][j]=String.valueOf(fieldsInteraction.getOutcome(j)
			// / ved.getProdPriceAt(j));
			// for(int
			// j=2;j<Main.ModelLenght+2;j++)dataValues[startIndex+26][j]=String.valueOf(fi.getDemand(j,
			// ved.getIndexInFieldsInteraction()) -
			// fieldsInteraction.getOutcome(j));//валовый спрос на инню - объем
			// выпуска в денежном
			// for(int
			// j=2;j<Main.ModelLenght+2;j++)dataValues[startIndex+27][j]=String.valueOf(fieldsInteraction.getImport(j));
			// for(int
			// j=2;j<Main.ModelLenght+2;j++)dataValues[startIndex+28][j]=String.valueOf(fieldsInteraction.getImportVed(j));
			dataValues[startIndex + 29][j] = String.valueOf(ved.getTotalCost(j));
			dataValues[startIndex + 30][j] = String.valueOf(ved.getVarCost(j));
			dataValues[startIndex + 31][j] = String.valueOf(ved.getConstCost(j));
			dataValues[startIndex + 32][j] = String.valueOf(ved.getSalary(j));
			dataValues[startIndex + 33][j] = String.valueOf(ved.getSalary2(j));
			// for (int j = 2; j < Main.ModelLenght + 2; j++)
			// dataValues[startIndex + 34][j] = String.valueOf(ved
			// .getTotalAddedValue(j));// ??
			dataValues[startIndex + 35][j] = String.valueOf(ved.getProfit(j));
			dataValues[startIndex + 36][j] = String.valueOf(ved.getNetProfit(j));
			dataValues[startIndex + 37][j] = String.valueOf(ved.getLoss(j));
			// for (int j = 2; j < Main.ModelLenght + 2; j++)
			// dataValues[startIndex + 38][j] =
			// String.valueOf(ved.getTotalTax(j));// ??
			// for (int j = 2; j < Main.ModelLenght + 2; j++)
			// dataValues[startIndex + 39][j] = String.valueOf(ved
			// .getAddedValueTax(j));// ??
			// произв.-фин. показатели
			dataValues[startIndex + 40][j] = String.valueOf(ved.getReturnOnSales(j));
			dataValues[startIndex + 41][j] = String.valueOf(ved.getReturnOnMargin(j));
			dataValues[startIndex + 42][j] = String.valueOf(ved.getCosts(j));
			dataValues[startIndex + 43][j] = String.valueOf(ved.getBreakEvenPoint(j));
			dataValues[startIndex + 44][j] = String.valueOf(ved.getBreakEvenPointMoney(j));
			dataValues[startIndex + 45][j] = String.valueOf(ved.getFinSafetyMargin(j));
			dataValues[startIndex + 46][j] = String.valueOf(ved.getFinSafetyMarginRe(j));
			dataValues[startIndex + 47][j] = String.valueOf(ved.getWorkforceProdFin(j));
			dataValues[startIndex + 48][j] = String.valueOf(ved.getLaborContentFin(j));
			dataValues[startIndex + 49][j] = String.valueOf(ved.getTurnoverFin(j));
			dataValues[startIndex + 50][j] = String.valueOf(ved.getCapitalIntensityFin(j));
			dataValues[startIndex + 51][j] = String.valueOf(ved.getCapitalLaborRatio(j));
		}

		// // Кадры
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 2][j] = String.valueOf(ved.getStaff(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 3][j] =
		// String.valueOf(ved.getStaffIncome(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 4][j] =
		// String.valueOf(ved.getStaffOutcome(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 5][j] = String.valueOf(ved.getEduAbility(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 6][j] =
		// String.valueOf(ved.getStaffDemand(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 7][j] =
		// String.valueOf(ved.getStaffDeficit(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 8][j] =
		// String.valueOf(ved.getStaffExcess(j));
		// // основные фонды
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 10][j] = String.valueOf(ved.getAssets(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 11][j] =
		// String.valueOf(ved.getAssetsIncome(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 12][j] =
		// String.valueOf(ved.getAssetsOutcome(j));
		// // оборотные средства
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 14][j] = String.valueOf(ved.getWc(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 15][j] = String.valueOf(ved.getWcIncome(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 16][j] = String.valueOf(ved.getWcOutcome(j));
		// // нематер. активы
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 18][j] =
		// String.valueOf(ved.getIntangibles(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 19][j] =
		// String.valueOf(ved.getIntangiblesIncome(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 20][j] =
		// String.valueOf(ved.getIntangiblesOutcome(j));
		// // производство(выпуск продукции)
		// FieldsInteraction fi =
		// ModelObjectsContainer.getInstance().getFieldsInteraction();
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 22][j] = String.valueOf(ved.getPotential(j));
		// // for (int j = 2; j < Main.ModelLenght + 2; j++)
		// // dataValues[startIndex + 23][j] = String
		// // .valueOf(fi.getDemand(j, ved.getIndexInFieldsInteraction()));
		// // for(int
		// //
		// j=2;j<Main.ModelLenght+2;j++)dataValues[startIndex+24][j]=String.valueOf(fieldsInteraction.getOutcome(j));
		// // for(int
		// //
		// j=2;j<Main.ModelLenght+2;j++)dataValues[startIndex+25][j]=String.valueOf(fieldsInteraction.getOutcome(j)
		// // / ved.getProdPriceAt(j));
		// // for(int
		// //
		// j=2;j<Main.ModelLenght+2;j++)dataValues[startIndex+26][j]=String.valueOf(fi.getDemand(j,
		// // ved.getIndexInFieldsInteraction()) -
		// // fieldsInteraction.getOutcome(j));//валовый спрос на инню - объем
		// // выпуска в денежном
		// // for(int
		// //
		// j=2;j<Main.ModelLenght+2;j++)dataValues[startIndex+27][j]=String.valueOf(fieldsInteraction.getImport(j));
		// // for(int
		// //
		// j=2;j<Main.ModelLenght+2;j++)dataValues[startIndex+28][j]=String.valueOf(fieldsInteraction.getImportVed(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 29][j] = String.valueOf(ved.getTotalCost(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 30][j] = String.valueOf(ved.getVarCost(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 31][j] = String.valueOf(ved.getConstCost(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 32][j] = String.valueOf(ved.getSalary(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 33][j] = String.valueOf(ved.getSalary2(j));
		// // for (int j = 2; j < Main.ModelLenght + 2; j++)
		// // dataValues[startIndex + 34][j] = String.valueOf(ved
		// // .getTotalAddedValue(j));// ??
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 35][j] = String.valueOf(ved.getProfit(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 36][j] = String.valueOf(ved.getNetProfit(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 37][j] = String.valueOf(ved.getLoss(j));
		// // for (int j = 2; j < Main.ModelLenght + 2; j++)
		// // dataValues[startIndex + 38][j] =
		// // String.valueOf(ved.getTotalTax(j));// ??
		// // for (int j = 2; j < Main.ModelLenght + 2; j++)
		// // dataValues[startIndex + 39][j] = String.valueOf(ved
		// // .getAddedValueTax(j));// ??
		// // произв.-фин. показатели
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 40][j] =
		// String.valueOf(ved.getReturnOnSales(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 41][j] =
		// String.valueOf(ved.getReturnOnMargin(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 42][j] = String.valueOf(ved.getCosts(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 43][j] =
		// String.valueOf(ved.getBreakEvenPoint(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 44][j] =
		// String.valueOf(ved.getBreakEvenPointMoney(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 45][j] =
		// String.valueOf(ved.getFinSafetyMargin(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 46][j] =
		// String.valueOf(ved.getFinSafetyMarginRe(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 47][j] =
		// String.valueOf(ved.getWorkforceProdFin(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 48][j] =
		// String.valueOf(ved.getLaborContentFin(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 49][j] =
		// String.valueOf(ved.getTurnoverFin(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 50][j] =
		// String.valueOf(ved.getCapitalIntensityFin(j));
		// for (int j = 2; j < Main.ModelLenght + 2; j++)
		// dataValues[startIndex + 51][j] =
		// String.valueOf(ved.getCapitalLaborRatio(j));
		return dataValues;
	}
}
