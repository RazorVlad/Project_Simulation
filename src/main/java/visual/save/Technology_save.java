package visual.save;

import java.io.Serializable;

public class Technology_save implements Serializable {

	private static final long serialVersionUID = 1L;
	double[][] coefAx;
	double[][] coefAi;
	double[][] ax;
	double[][] ai;
	double[][] bx;
	double[][] bi;
	double[][] bsum;

	public double[][] getBx() {
		return bx;
	}

	public void setBx(double[][] bx) {
		this.bx = bx.clone();
	}

	public double[][] getBi() {
		return bi;
	}

	public void setBi(double[][] bi) {
		this.bi = bi.clone();
	}

	public double[][] getBsum() {
		return bsum;
	}

	public void setBsum(double[][] bsum) {
		this.bsum = bsum.clone();
	}

	public double[][] getCoefAx() {
		return coefAx;
	}

	public void setCoefAx(double[][] coefAx) {
		this.coefAx = coefAx.clone();
	}

	public double[][] getCoefAi() {
		return coefAi;
	}

	public void setCoefAi(double[][] coefAi) {
		this.coefAi = coefAi.clone();
	}

	public double[][] getAx() {
		return ax;
	}

	public void setAx(double[][] ax) {
		this.ax = ax.clone();
	}

	public double[][] getAi() {
		return ai;
	}

	public void setAi(double[][] ai) {
		this.ai = ai.clone();
	}

}
