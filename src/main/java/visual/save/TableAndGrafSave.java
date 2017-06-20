package visual.save;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class TableAndGrafSave implements Serializable {

	private static final long serialVersionUID = 2881765930833648006L;
	private static Logger LOG = Logger.getLogger(TableAndGrafSave.class);
	double[][] nir;
	double[][] okr;
	double[][] inn;
	double[][] ord;

	public double[][] getNir() {
		return nir;
	}

	public void setNir(double[][] nir) {
		this.nir = nir.clone();
	}

	public double[][] getOkr() {
		return okr;
	}

	public void setOkr(double[][] okr) {
		this.okr = okr.clone();
	}

	public double[][] getInn() {
		return inn;
	}

	public void setInn(double[][] inn) {
		this.inn = inn.clone();
	}

	public double[][] getOrd() {
		return ord;
	}

	public void setOrd(double[][] ord) {
		this.ord = ord.clone();
	}
}
