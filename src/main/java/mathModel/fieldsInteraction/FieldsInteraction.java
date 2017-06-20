package mathModel.fieldsInteraction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mathModel.ModelObjectsContainer;
import mathModel.Statistics;
import mathModel.manFun.gaussSolver.GaussSolver;
import mathModel.manufacturing.Manufacturing;

import org.apache.log4j.Logger;

import visual.frames.main.Main;

public class FieldsInteraction implements Serializable {

	private static final long serialVersionUID = 1L;

	private double[][] coefsAx;// 26x26
	private double[][] coefsAi;// 26x26
	private double[][] inputAx;// 38x38
	private double[][] inputAi;// 38x38

	private double[] lastColumnAx;
	private double[] lastColumnAi;

	private double[] YxOrd;
	private double[] YxInn;
	private double[] YiOrd;
	private double[] YiInn;

	private double[] Yx3inn;
	private double[] Yx3Ord;

	private double[][] resultAx;// 26x26 after agreg and disagreg
	private double[][] resultAi;// 26x26 after agreg and disagreg
	private double[][] resultSum;// 26x26 after agreg and disagreg

	private Statistics[] prodDemandInn;// output for ved
	private Statistics[] prodDemandOrd;
	private double[] columnSums;

	private Statistics[] importInnTotal = new Statistics[26];
	private Statistics[] importOrdTotal = new Statistics[26];

	private Statistics[] importInnPart = new Statistics[26];
	private Statistics[] importOrdPart = new Statistics[26];

	private Statistics[] amountRealInn = new Statistics[26];
	private Statistics[] amountRealOrd = new Statistics[26];

	private int modelLength;
	private int startYear;

	private List<Integer> activeVedIndexes = new ArrayList<Integer>();
	private HashMap<Integer, Statistics> potentialsOrd = new HashMap<Integer, Statistics>();
	private HashMap<Integer, Statistics> potentialsInn = new HashMap<Integer, Statistics>();
	private static Logger LOG = Logger.getLogger(FieldsInteraction.class);

	public FieldsInteraction() {
		this.modelLength = Main.ModelLenght;
		this.startYear = Main.Year;
	}

	public void setYx3inn(double[] yx3inn) {
		Yx3inn = yx3inn.clone();
	}

	public void setYx3Ord(double[] yx3Ord) {
		Yx3Ord = yx3Ord.clone();
	}

	public void setCoefsAx(double[][] coefsAx) {
		this.coefsAx = coefsAx.clone();
	}

	public void setCoefsAi(double[][] coefsAi) {
		this.coefsAi = coefsAi.clone();
	}

	public void setAx(double[][] ax) {
		inputAx = ax.clone();
	}

	public void setAi(double[][] ai) {
		inputAi = ai.clone();
	}

	public void setYxInn(double[] yxInn) {
		this.YxInn = yxInn.clone();
	}

	public void setYxOrd(double[] yxOrd) {
		this.YxOrd = yxOrd.clone();
	}

	public void setYiOrd(double[] yiOrd) {
		this.YiOrd = yiOrd.clone();
	}

	public void setYiInn(double[] yiInn) {
		this.YiInn = yiInn.clone();
	}

	public void setLastColumnAi(double[] lastColumnAi) {
		this.lastColumnAi = lastColumnAi.clone();
	}

	public void setLastColumnAx(double[] lastColumnAx) {
		this.lastColumnAx = lastColumnAx.clone();
	}

	public void proceed() {
		ModelObjectsContainer model = ModelObjectsContainer.getInstance();
		for (Manufacturing ved : model.getAllVedOrd()) {
			activeVedIndexes.add(ved.getIndexInFieldsInteraction());
			potentialsOrd.put(ved.getIndexInFieldsInteraction(), ved.getPotential());
		}
		for (Manufacturing ved : model.getAllVedInn()) {
			potentialsInn.put(ved.getIndexInFieldsInteraction(), ved.getPotential());
		}

		for (int ved = 0; ved < 26; ved++) {
			if (!activeVedIndexes.contains(ved)) {
				potentialsInn.put(ved, getProdDemand(ved, true));
				potentialsOrd.put(ved, getProdDemand(ved, false));
			}
		}

		for (int t = startYear; t <= startYear + modelLength; t++) {
			List<Integer> unsatisfiedInn = getUnsatisfiedDemandIndexes(t, true);
			List<Integer> unsatisfiedOrd = getUnsatisfiedDemandIndexes(t, false);
			// =================================================================
			if (unsatisfiedInn.isEmpty()) {
				LOG.debug("спрос равен или меньше производственных мощностей (Inn)");
				// amountReal равен спросу, спрос меньше пр-венных мощностей
				for (Integer ved : activeVedIndexes) {
					amountRealInn[ved] = getProdDemand(ved, true);
				}
			} else {
				LOG.debug("Спрос больше производственных мощностей (Inn)");
				// спрос больше пр-венных мощностей, мучаемся
				double[] result = solve(unsatisfiedInn, true, t);
				for (int i = 0; i < 26; i++) {
					if (unsatisfiedInn.contains(i)) {
						amountRealInn[i].setValueAt(t, potentialsInn.get(i).getValueAt(t));
					} else {
						amountRealInn[i].setValueAt(t, result[i]);
					}
				}
				List<Integer> negativeYInn = new ArrayList<Integer>();
				for (Integer index : unsatisfiedInn) {
					if (result[index] < 0.) {
						negativeYInn.add(index);
					}
				}
				if (negativeYInn.isEmpty()) {
					calculateImportInn(amountRealInn, t);
				} else {
					List<Double> undem = new ArrayList<Double>();
					for (Integer j : negativeYInn) {
						undem.add(prodDemandInn[j].getValue(0) - result[j]);
					}
					// unDemX
					List<Double> undemX = new ArrayList<Double>();
					double[] sum = GaussSolver.vectorMatrixMultiplication(getBx(), result);
					for (Integer j : negativeYInn) {
						undemX.add((sum[j] / (sum[j] + Yx3inn[j])) * undem.get(j));
					}
					// unDemY
					List<Double> undemY = new ArrayList<Double>();
					for (Integer j : negativeYInn) {
						undemX.add((Yx3inn[j] / (sum[j] + Yx3inn[j])) * undem.get(j));
					}
					// addedImport
					double[][] addedImport = new double[negativeYInn.size()][26];
					for (Integer j : negativeYInn) {
						for (int i = 0; i < 26; i++) {
							addedImport[j][i] = undemX.get(j) * ((getBx()[j][i] * result[j]) / sum[j]);
						}
					}
					// формула 17
					calculateImportInnPart(amountRealInn, t);
					for (Integer index : negativeYInn) {
						double oldValue = importInnPart[index].getValueAt(t);
						importInnPart[index].setValueAt(t, oldValue + columnSum(addedImport, index));
						LOG.debug("importInnPart[" + index + "] = importInnPart[" + index
								+ "] + columnSum(addedImport, index)) = " + oldValue + " + "
								+ columnSum(addedImport, index) + " = " + importInnPart[index].getValueAt(t));
					}
					// формула 16
					calculateImportInnTotal(amountRealInn, t);
					for (Integer index : negativeYInn) {
						double oldValue = importInnTotal[index].getValueAt(t);
						importInnTotal[index].setValueAt(t, oldValue + undemX.get(index) + undemY.get(index));
					}
				}
			}
			// =================================================================
			if (unsatisfiedOrd.isEmpty()) {
				LOG.debug("спрос равен или меньше производственных мощностей (Ord)");
				// amountReal равен спросу, спрос меньше првенных мощностей
				for (Integer ved : activeVedIndexes) {
					amountRealOrd[ved] = getProdDemand(ved, false);
				}
			} else {
				LOG.debug("Спрос больше производственных мощностей (Ord)");
				// спрос больше првенных мощностей, мучаемся
				double[] result = solve(unsatisfiedOrd, false, t);
				for (int i = 0; i < 26; i++) {
					if (unsatisfiedInn.contains(i)) {
						amountRealOrd[i].setValueAt(t, potentialsOrd.get(i).getValueAt(t));
					} else {
						amountRealOrd[i].setValueAt(t, result[i]);
					}
				}
				List<Integer> negativeYOrd = new ArrayList<Integer>();
				for (Integer index : unsatisfiedOrd) {
					if (result[index] < 0.) {
						negativeYOrd.add(index);
					}
				}
				if (negativeYOrd.isEmpty()) {
					calculateImportOrd(amountRealOrd, t);
				} else {
					List<Double> undem = new ArrayList<Double>();
					for (Integer j : negativeYOrd) {
						undem.add(prodDemandOrd[j].getValue(0) - result[j]);
					}
					// unDemX
					List<Double> undemX = new ArrayList<Double>();
					double[] sum = GaussSolver.vectorMatrixMultiplication(getBx(), result);
					for (Integer j : negativeYOrd) {
						undemX.add((sum[j] / (sum[j] + Yx3Ord[j])) * undem.get(j));
					}
					// unDemY
					List<Double> undemY = new ArrayList<Double>();
					for (Integer j : negativeYOrd) {
						undemX.add((Yx3Ord[j] / (sum[j] + Yx3Ord[j])) * undem.get(j));
					}
					// addedImport
					double[][] addedImport = new double[negativeYOrd.size()][26];
					for (Integer j : negativeYOrd) {
						for (int i = 0; i < 26; i++) {
							addedImport[j][i] = undemX.get(j) * ((getBx()[j][i] * result[j]) / sum[j]);
						}
					}
					// формула 17
					calculateImportOrdPart(amountRealOrd, t);
					for (Integer index : negativeYOrd) {
						double oldValue = importOrdPart[index].getValueAt(t);
						importOrdPart[index].setValueAt(t, oldValue + columnSum(addedImport, index));
					}
					// формула 16
					calculateImportOrdTotal(amountRealOrd, t);
					for (Integer index : negativeYOrd) {
						double oldValue = importOrdTotal[index].getValueAt(t);
						importOrdTotal[index].setValueAt(t, oldValue + undemX.get(index) + undemY.get(index));
					}
				}
			}
			// =================================================================
		}
	}

	private double[] solve(List<Integer> unsatisfied, boolean innovational, int t) {
		double[][] m = new double[26][26];
		double[] q = new double[26];
		// init q ------------------------------------------
		for (int i = 0; i < 26; i++) {
			if (unsatisfied.contains(i)) {
				if (innovational) {
					q[i] = potentialsInn.get(i).getValueAt(t);
				} else {
					q[i] = potentialsOrd.get(i).getValueAt(t);
				}
			} else {
				if (innovational) {
					q[i] = -YiInn[i];
				} else {
					q[i] = -YiOrd[i];
				}
			}
			for (Integer ved : unsatisfied) {
				if (innovational) {
					q[i] -= getBx()[i][ved] * potentialsInn.get(ved).getValueAt(t);
				} else {
					q[i] -= getBx()[i][ved] * potentialsOrd.get(ved).getValueAt(t);
				}
			}
		}

		// init m -----------------------------------------
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				if (i == j) {
					if (unsatisfied.contains(i)) {
						m[i][j] = 1;
					} else {
						m[i][j] = getBx()[i][j] - 1;
					}
				} else {
					if (unsatisfied.contains(j)) {
						m[i][j] = 0;
					} else {
						m[i][j] = getBx()[i][j];
					}
				}
			}
		}
		// --------------------------------------------------

		return GaussSolver.solve(m, q);
	}

	// private double[] calcImport(double[][] Ai, int time) {
	// return GaussSolver.addVectors(Yi,
	// GaussSolver.vectorMatrixMultiplication(Ai, demand.get(time)));
	// }

	private List<Integer> getUnsatisfiedDemandIndexes(int t, boolean innovational) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (innovational) {
			for (Integer vedIndex : activeVedIndexes) {
				if (potentialsInn.get(vedIndex).getValue(t) < amountRealInn[vedIndex].getValue(t)) {
					res.add(vedIndex);
				}
			}
		} else {
			for (Integer vedIndex : activeVedIndexes) {
				if (potentialsOrd.get(vedIndex).getValue(t) < amountRealOrd[vedIndex].getValue(t)) {
					res.add(vedIndex);
				}
			}
		}
		return res;
	}

	public double[][] getBx() {
		double[][] agrAx = Agregation.agregate(inputAx, lastColumnAx);
		resultAx = Disagregation.disagregate(agrAx, coefsAx);
		return resultAx;
	}

	public double[][] getBi() {
		double[][] agrAi = Agregation.agregate(inputAi, lastColumnAi);
		resultAi = Disagregation.disagregate(agrAi, coefsAi);
		return resultAi;
	}

	public double[][] getBSum() {
		double[][] Bx = resultAx;
		double[][] Bi = resultAi;
		resultSum = new double[Bx.length][Bx.length];
		for (int i = 0; i < Bx.length; i++) {
			for (int j = 0; j < Bx.length; j++) {
				resultSum[i][j] = Bx[i][j] + Bi[i][j];
			}
		}
		return resultSum;
	}

	public Statistics getAmountReal(int vedIndex, boolean innovational) {
		if (innovational) {
			return amountRealInn[vedIndex];
		} else {
			return amountRealOrd[vedIndex];
		}
	}

	public double getColumnSum(int vedIndex) {
		// if (columnSums == null) {
		columnSums = new double[26];
		for (int ved = 0; ved < 26; ved++) {
			for (int j = 0; j < 26; j++) {
				columnSums[ved] += getBSum()[j][ved];
			}
		}
		// }
		return columnSums[vedIndex];
	}

	public Statistics getProdDemand(int vedIndex, boolean innovational) {
		// if (prodDemandInn == null || prodDemandOrd == null) {
		double[][] toRevert = new double[26][26];
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				if (i == j) {
					toRevert[i][j] = 1 - getBx()[i][j];
				} else {
					toRevert[i][j] = -getBx()[i][j];
				}
			}
		}
		double[][] toMultiply = GaussSolver.reverseMatrix(toRevert);
		double[] resInn = GaussSolver.vectorMatrixMultiplication(toMultiply, YxInn);
		prodDemandInn = new Statistics[25];
		double[] resOrd = GaussSolver.vectorMatrixMultiplication(toMultiply, YxOrd);
		prodDemandOrd = new Statistics[25];
		for (int ved = 0; ved < 26; ved++) {
			prodDemandInn[ved] = new Statistics(startYear, resInn[ved], modelLength);
			prodDemandOrd[ved] = new Statistics(startYear, resOrd[ved], modelLength);
		}
		for (int ved = 0; ved < 26; ved++) {
			amountRealInn[ved] = prodDemandInn[ved];
			amountRealOrd[ved] = prodDemandOrd[ved];
		}
		for (int t = startYear; t < startYear + modelLength; t++) {
			calculateImport(prodDemandInn, prodDemandOrd, t);
		}
		// }
		if (innovational) {
			return prodDemandInn[vedIndex];
		} else {
			return prodDemandOrd[vedIndex];
		}
	}

	private void calculateImport(Statistics[] demandOrRealInn, Statistics[] demandOrRealOrd, int t) {
		calculateImportInn(demandOrRealInn, t);
		calculateImportOrd(demandOrRealOrd, t);
	}

	private void calculateImportInn(Statistics[] demandOrRealInn, int t) {
		calculateImportInnPart(demandOrRealInn, t);
		calculateImportInnTotal(demandOrRealInn, t);
	}

	private void calculateImportOrd(Statistics[] demandOrRealOrd, int t) {
		calculateImportOrdPart(demandOrRealOrd, t);
		calculateImportOrdTotal(demandOrRealOrd, t);
	}

	private void calculateImportInnPart(Statistics[] demandOrRealInn, int t) {
		for (int ved = 0; ved < 26; ved++) {
			double toAddInn = 0;
			for (int i = 0; i < 26; i++) {
				toAddInn += demandOrRealInn[ved].getValueAt(t) * getBi()[ved][i];
			}
			if (importInnPart[ved] == null) {
				importInnPart[ved] = new Statistics(startYear, toAddInn, modelLength);
			} else {
				importInnPart[ved].setValueAt(t, toAddInn);
			}
		}
	}

	private void calculateImportOrdPart(Statistics[] demandOrRealOrd, int t) {
		for (int ved = 0; ved < 26; ved++) {
			double toAddOrd = 0;
			for (int i = 0; i < 26; i++) {
				toAddOrd += demandOrRealOrd[ved].getValueAt(t) * getBi()[ved][i];
			}
			if (importOrdPart[ved] == null) {
				importOrdPart[ved] = new Statistics(startYear, toAddOrd, modelLength);
			} else {
				importOrdPart[ved].setValueAt(t, toAddOrd);
			}
		}
	}

	private void calculateImportInnTotal(Statistics[] demandOrRealInn, int t) {
		for (int ved = 0; ved < 26; ved++) {
			double toAddInn = 0;
			for (int i = 0; i < 26; i++) {
				toAddInn += demandOrRealInn[ved].getValueAt(t) * getBi()[ved][i];
			}
			if (importInnTotal[ved] == null) {
				importInnTotal[ved] = new Statistics(startYear, toAddInn + YiInn[ved], modelLength);
			} else {
				importInnTotal[ved].setValueAt(t, toAddInn + YiInn[ved]);
			}
		}
	}

	private void calculateImportOrdTotal(Statistics[] demandOrRealOrd, int t) {
		for (int ved = 0; ved < 26; ved++) {
			double toAddOrd = 0;
			for (int i = 0; i < 26; i++) {
				toAddOrd += demandOrRealOrd[ved].getValueAt(t) * getBi()[ved][i];
			}
			if (importOrdTotal[ved] == null) {
				importOrdTotal[ved] = new Statistics(startYear, toAddOrd + YiOrd[ved], modelLength);
			} else {
				importOrdTotal[ved].setValueAt(t, toAddOrd + YiOrd[ved]);
			}
		}
	}

	private double columnSum(double[][] arg, int column) {
		double result = 0.;
		for (double[] anArg : arg) {
			result += anArg[column];
		}
		return result;
	}

}
