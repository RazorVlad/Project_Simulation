package mathModel;

import java.io.Serializable;

public class InvestmentsRnD implements Serializable {

	private static final long serialVersionUID = 1L;
//	private static Logger LOG = Logger.getLogger(InvestmentsRnD.class);
	private Statistics privInvD;
	private Statistics govInvD;
	private Statistics privInvR;
	private Statistics govInvR;

	public InvestmentsRnD(Statistics privInvD, Statistics govInvD,
			Statistics privInvR, Statistics govInvR) {
		super();
		this.privInvD = privInvD;
		this.govInvD = govInvD;
		this.privInvR = privInvR;
		this.govInvR = govInvR;
	}

	public Statistics getPrivInv(boolean okr) {
		if (okr) {
			return privInvD;
		} else {
			return privInvR;
		}
	}

	public Statistics getGovInv(boolean okr) {
		if (okr) {
			return govInvD;
		} else {
			return govInvR;
		}
	}

}
