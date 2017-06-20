package mathModel.manFun;

import java.io.Serializable;

import mathModel.Statistics;
import mathModel.manFun.gaussSolver.GaussSolver;

import org.apache.log4j.Logger;

import visual.frames.main.Main;

public class ManFun implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(ManFun.class);
	private Statistics alfa;
	private Statistics beta;
	private Statistics gamma;
	private Statistics a;
	private double mape;

	private double r;
	private Statistics y = new Statistics();

	public ManFun() {
	}

	public Statistics getAlfa() {
		return alfa;
	}

	public Statistics getBeta() {
		return beta;
	}

	public Statistics getGamma() {
		return gamma;
	}

	public Statistics getA() {
		return a;
	}

	public void setA(Statistics a) {
		this.a = a;
	}

	public void setAlfa(Statistics alfa) {
		this.alfa = alfa;
	}

	public void setBeta(Statistics beta) {
		this.beta = beta;
	}

	public void setGamma(Statistics gamma) {
		this.gamma = gamma;
	}

	public void setMape(double mape) {
		this.mape = mape;
	}

	public void setR(double r) {
		this.r = r;
	}

	public void proceed(Statistics poss, Statistics staff, Statistics assets) {
		// System.out.println("possInput: " + possInput);
		// System.out.println("staffInput: " + staffInput);
		// System.out.println("assetsInput: " + assetsInput);
		//
		// Statistics poss = normalize(possInput);
		// Statistics staff = normalize(staffInput);
		// Statistics assets = normalize(assetsInput);

		double v = 0., l = 0., k = 0., ll = 0., kk = 0., lv = 0., kv = 0., lk = 0.;
		LOG.debug("v=0; l=0; k=0; c=0; ll=0; kk=0; cc=0; lk=0; kc=0; lc=0; lv=0; kv=0; cv=0;");
		for (int j = 0; j < poss.size(); j++) {
			v += Math.log(poss.getValue(j));
			l += Math.log(staff.getValue(j));
			k += Math.log(assets.getValue(j));
			ll += Math.log(staff.getValue(j)) * Math.log(staff.getValue(j));
			kk += Math.log(assets.getValue(j)) * Math.log(assets.getValue(j));
			lv += Math.log(staff.getValue(j)) * Math.log(poss.getValue(j));
			kv += Math.log(poss.getValue(j)) * Math.log(assets.getValue(j));
			lk += Math.log(staff.getValue(j)) * Math.log(assets.getValue(j));
		}
		LOG.debug("ln poss: " + v);
		LOG.debug("ln staff: " + l);
		LOG.debug("ln assets: " + k);
		// beta = new Statistics(Main.Year, ((poss.size() - 1) * v * k *
		// (poss.size() * ll - l * l))
		// / ((kk - k * k) * (poss.size() * ll - l * l - (poss.size() - 1) *
		// (poss.size() - 1) * k * k
		// * l * l)), Main.ModelLenght);
		// alfa = new Statistics(Main.Year, ((poss.size() - 1) * (v * l -
		// beta.getValue(0) * l * k))
		// / (poss.size() * ll - l * l), Main.ModelLenght);
		// a = new Statistics(Main.Year, Math.exp((v - alfa.getValue(0) * l -
		// beta.getValue(0) * k)
		// / poss.size()), Main.ModelLenght);

		double[] res = GaussSolver.solve(
				new double[][] { { poss.size(), l, k }, { l, ll, lk }, { k, lk, kk } }, new double[] { v, lv,
						kv });

		a = new Statistics(Main.Year, Math.exp(res[0]), Main.ModelLenght);
		alfa = new Statistics(Main.Year, res[1], Main.ModelLenght);
		beta = new Statistics(Main.Year, res[2], Main.ModelLenght);

		double upper = 0.;
		double lower = 0.;
		double mid = 0.;
		for (int t = 0; t < poss.size(); t++) {
			mid += poss.getValue(t);
		}
		mid /= poss.size();
		for (int t = 0; t < poss.size(); t++) {
			double y = a.getValue(0) * Math.pow(staff.getValue(t), alfa.getValue(0))
					* Math.pow(assets.getValue(t), beta.getValue(0));
			this.y.add(poss.getYear(t), y);
			mape += Math.abs((poss.getValue(t) - y) / poss.getValue(t));
			upper += (y - mid) * (y - mid);
			lower += (poss.getValue(t) - mid) * (poss.getValue(t) - mid);
		}
		mape /= poss.size();
		r = upper / lower;
	}

	public double getMape() {
		return mape;
	}

	public double getR() {
		if (r > 1.) {
			return 1.;
		}
		return r;
	}

	public Statistics getY() {
		return y;
	}

	public void proceed(Statistics poss, Statistics staff, Statistics assets, Statistics real) {
		// System.out.println("possInput: " + possInput);
		// System.out.println("staffInput: " + staffInput);
		// System.out.println("assetsInput: " + assetsInput);
		// System.out.println("realInput: " + realInput);
		//
		// Statistics poss = normalize(possInput);
		// Statistics staff = normalize(staffInput);
		// Statistics assets = normalize(assetsInput);
		// Statistics real = normalize(realInput);
		
		double[] gausSolverResult = GausSolverPrep.prepAndRunGausSolver(poss, staff, assets, real);
		// a = Math.exp(res[0]);
		a = new Statistics(Main.Year, Math.exp(gausSolverResult[0]), Main.ModelLenght);
		LOG.debug("a[" + Main.Year + "] = Math.exp(res[0]) = Math.exp(" + gausSolverResult[0] + ") = " + Math.exp(gausSolverResult[0]));
		// alfa = res[1];
		alfa = new Statistics(Main.Year, gausSolverResult[1], Main.ModelLenght);
		LOG.debug("alfa[" + Main.Year + "]=res[1]=" + gausSolverResult[1]);
		// beta = res[2];
		beta = new Statistics(Main.Year, gausSolverResult[2], Main.ModelLenght);
		LOG.debug("beta[" + Main.Year + "]=res[2]=" + gausSolverResult[2]);
		// gamma = res[3];
		gamma = new Statistics(Main.Year, gausSolverResult[3], Main.ModelLenght);
		LOG.debug("gamma[" + Main.Year + "]=res[3]=" + gausSolverResult[3]);

		double upper = 0.;
		double lower = 0.;
		double mid = 0.;

		LOG.debug("upper = 0");
		LOG.debug("lower = 0");
		LOG.debug("mid = 0");

		for (int t = 0; t < poss.size(); t++) {
			mid += poss.getValue(t);
			LOG.debug("mid = mid+poss.getValue(" + t + ")=mid+" + poss.getValue(t) + "=" + mid);
		}
		mid /= poss.size();
		LOG.debug("mid = mid/poss.size()=mid/" + poss.size() + "=" + mid);
		for (int t = 0; t < poss.size(); t++) {
			double y = a.getValue(0) * Math.pow(staff.getValue(t), alfa.getValue(0))
					* Math.pow(assets.getValue(t), beta.getValue(0))
					* Math.pow(real.getValue(t), gamma.getValue(0));
			LOG.debug("y=a*(staff.getValue(" + t + ")^alfa)*(assets.getValue(" + t
					+ ")^beta)*(real.getValue(" + t + ")^gamma)=" + a.getValue(0) + "*(" + staff.getValue(t)
					+ "^" + alfa.getValue(0) + ")*(" + assets.getValue(t) + "^" + beta.getValue(0) + ")*("
					+ real.getValue(t) + "^" + gamma.getValue(0) + ")=" + y);
			this.y.add(poss.getYear(t), y);

			mape += Math.abs((poss.getValue(t) - y) / poss.getValue(t));
			LOG.debug("mape = mape + |(poss.getValue(" + t + ") - y) / poss.getValue(" + t + ")| = mape +|("
					+ poss.getValue(t) + " - " + y + ") / " + poss.getValue(t) + "|=" + mape);

			upper += (y - mid) * (y - mid);
			LOG.debug("upper = upper + (y - mid) * (y - mid)=upper + (" + (y - mid) + ") * (" + (y - mid)
					+ ") = " + upper);

			lower += (poss.getValue(t) - mid) * (poss.getValue(t) - mid);
			LOG.debug("lower=lower+(poss.getValue(" + t + ") - mid)*(poss.getValue(t) - mid)=lower+("
					+ (poss.getValue(t) - mid) + ")*(" + (poss.getValue(t) - mid) + ")=" + lower);
		}
		mape /= poss.size();
		LOG.debug("mape = mape / poss.size() = mape / " + poss.size() + " = " + mape);
		r = upper / lower;
		LOG.debug("r = upper / lower = " + upper + " / " + lower + " = " + r);
	}

	// private Statistics normalize(Statistics arg) {
	// double avg = avg(arg);
	// double s = deviation(avg, arg);
	// Statistics result = new Statistics();
	// for (int i = 0; i < arg.size(); i++) {
	// result.add(arg.getYear(i), (arg.getValue(i) - avg) / s);
	// }
	// System.out.println("normalized smth == " + result);
	// return result;
	// }

	// private double avg(Statistics arg) {
	// double result = 0.;
	// for (int i = 0; i < arg.size(); i++) {
	// result += arg.getValue(i);
	// }
	// return result / arg.size();
	// }

	// private double deviation(double avg, Statistics arg) {
	// double sum = 0.;
	// for (int i = 0; i < arg.size(); i++) {
	// sum += (arg.getValue(i) - avg) * (arg.getValue(i) - avg);
	// }
	// return Math.sqrt(sum / arg.size());
	// }

}
