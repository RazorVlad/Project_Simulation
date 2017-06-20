package mathModel.manFun;

import org.apache.log4j.Logger;

import mathModel.Statistics;
import mathModel.manFun.gaussSolver.GaussSolver;

public class GausSolverPrep {
	private static Logger LOG = Logger.getLogger(GausSolverPrep.class);

	static double[] prepAndRunGausSolver(Statistics poss, Statistics staff, Statistics assets, Statistics real) {
		double v = 0., l = 0., k = 0., c = 0., ll = 0., kk = 0., cc = 0., lk = 0., kc = 0., lc = 0., lv = 0., kv = 0., cv = 0.;
		LOG.debug("v=0; l=0; k=0; c=0; ll=0; kk=0; cc=0; lk=0; kc=0; lc=0; lv=0; kv=0; cv=0;");
		double possLog;
		double staffLog;
		double assetsLog;
		double realLog;
		for (int j = 0; j < poss.size(); j++) {
			LOG.debug("j = " + j);

			possLog = Math.log(poss.getValue(j));
			LOG.debug("posslog=Math.log(" + poss.getValue(j) + ")=" + possLog);
			staffLog = Math.log(staff.getValue(j));
			LOG.debug("staffLog= Math.log(" + staff.getValue(j) + ")=" + staffLog);
			assetsLog = Math.log(assets.getValue(j));
			LOG.debug("assetsLog= Math.log(" + assets.getValue(j) + ")=" + assetsLog);
			realLog = Math.log(real.getValue(j));
			LOG.debug("realLog=Math.log(" + real.getValue(j) + ")=" + realLog);

			v += possLog;
			LOG.debug("v = v+possLog = v+" + possLog + " = " + v);
			l += staffLog;
			LOG.debug("l = l+staffLog = l+" + staffLog + " = " + l);
			k += assetsLog;
			LOG.debug("k = k+assetsLog=k+" + assetsLog + " = " + k);
			c += realLog;
			LOG.debug("c = c+realLog=c+" + realLog + " = " + c);
			ll += staffLog * staffLog;
			LOG.debug("ll = ll+staffLog*staffLog = ll+" + staffLog + "*" + staffLog + "=" + ll);
			kk += assetsLog * assetsLog;
			LOG.debug("kk = kk+assetsLog*assetsLog = kk+" + assetsLog + "*" + assetsLog + "=" + kk);
			cc += realLog * realLog;
			LOG.debug("cc = cc+realLog*realLog = cc+" + realLog + "*" + realLog + "=" + cc);
			lk += staffLog * assetsLog;
			LOG.debug("lk = lk+staffLog*assetsLog = lk+" + staffLog + "*" + assetsLog + "=" + lk);
			kc += realLog * assetsLog;
			LOG.debug("kc = kc+realLog*assetsLog = kc+" + realLog + "*" + assetsLog + "=" + kc);
			lc += staffLog * realLog;
			LOG.debug("lc = lc+staffLog*realLog = lc+" + staffLog + "*" + realLog + "=" + lc);
			lv += staffLog * possLog;
			LOG.debug("lv = lv+staffLog*possLog = lv+" + staffLog + "*" + possLog + "=" + lv);
			kv += possLog * assetsLog;
			LOG.debug("kv = kv+possLog*assetsLog = kv+" + possLog + "*" + assetsLog + "=" + kv);
			cv += realLog * possLog;
			LOG.debug("cv = cv+realLog*possLog = cv+" + realLog + "*" + possLog + "=" + cv);
		}
		double[] res = GaussSolver.solve(new double[][] { { poss.size(), l, k, c }, { l, ll, lk, lc },
				{ k, lk, kk, kc }, { c, lc, kc, cc } }, new double[] { v, lv, kv, cv });
		LOG.debug("double[] res = GaussSolver.solve(new double[][] { { poss.size(), l, k, c }, { l, ll, lk, lc },{ k, lk, kk, kc }, { c, lc, kc, cc } }, new double[] { v, lv, kv, cv })");
		return res;
	}
}
