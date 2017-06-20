package mathModel;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class InvestmentsManuf implements Serializable {

	private static final long serialVersionUID = -8207954926562189060L;
	private static Logger LOG = Logger.getLogger(InvestmentsManuf.class);
	private Statistics innPrivInv;
	private Statistics innGovInv;
	private Statistics ordPrivInv;
	private Statistics ordGovInv;

	public InvestmentsManuf(Statistics innPrivInv, Statistics innGovInv, Statistics ordPrivInv,
			Statistics ordGovInv) {
		super();
		this.innPrivInv = innPrivInv;
		this.innGovInv = innGovInv;
		this.ordPrivInv = ordPrivInv;
		this.ordGovInv = ordGovInv;
	}

	public Statistics getPrivInv(boolean inn) {
		if (inn) {
			return innPrivInv;
		} else {
			return ordPrivInv;
		}
	}

	public Statistics getGovInv(boolean inn) {
		if (inn) {
			return innGovInv;
		} else {
			return ordGovInv;
		}
	}

}
