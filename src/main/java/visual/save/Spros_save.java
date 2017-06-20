package visual.save;

import java.io.Serializable;

public class Spros_save implements Serializable {

	private static final long serialVersionUID = 1L;

	private double[][] spros;

	public double[][] getSpros() {
		return spros;
	}

	private String ue;

	public String getUe() {
		return ue;
	}

	public void setUe(String ue) {
		this.ue = ue;
	}

	public void setSpros(double[][] spros) {
		this.spros = spros.clone();
	}
}
