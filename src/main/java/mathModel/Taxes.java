package mathModel;

import java.io.Serializable;

public class Taxes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2554793208490262022L;
	private Statistics coefTax;
	private Statistics salaryTax;
	private Statistics addedValueTax;
//	private static Logger LOG = Logger.getLogger(Taxes.class);
	
	public Taxes(Statistics coefTax, Statistics salaryTax, Statistics addedValueTax) {
		this.coefTax = coefTax;
		this.addedValueTax = addedValueTax;
		this.salaryTax = salaryTax;
	}

	public Statistics getCoefTax() {
		return coefTax;
	}
	
	public Statistics getSalaryTax() {
		return salaryTax;
	}
	
	public Statistics getAddedValueTax() {
		return addedValueTax;
	}

}
