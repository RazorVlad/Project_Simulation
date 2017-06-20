package tests;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import mathModel.Statistics;
import mathModel.manFun.ManFun;

public class ManFunTests extends TestCase {

	public ManFunTests(String testName) {
		super(testName);
	}

	public void testFor3Param() {
		ManFun mf = new ManFun();

		Statistics poss = new Statistics(2000, new double[] { 1500, 2000, 3000, 1800, 4000 });
		Statistics staff = new Statistics(2000, new double[] { 300, 4200, 3000, 2700, 4200 });
		Statistics assets = new Statistics(2000, new double[] { 2100, 3800, 2100, 4300, 1900 });

		mf.proceed(poss, staff, assets);

		double ADat = mf.getA().getValue(0);
		double alfaDat = mf.getAlfa().getValue(0);
		double betaDat = mf.getBeta().getValue(0);

		double testADat = 403732.4;
		double testAlfaDat = 0.34;
		double testBetaDat = 0.98;

		assertTrue((ADat - testADat) < 0.001);
		assertTrue(alfaDat - testAlfaDat < 0.001);
		assertTrue(betaDat - testBetaDat < 0.001);

	}

	public void testFor4Param() {
		ManFun mf = new ManFun();

		Statistics poss = new Statistics(2000, new double[] { 1500, 2000, 3000, 1800, 4000 });
		Statistics staff = new Statistics(2000, new double[] { 300, 4200, 3000, 2700, 4200 });
		Statistics assets = new Statistics(2000, new double[] { 2100, 3800, 2100, 4300, 1900 });
		Statistics real = new Statistics(2000, new double[] { 1500, 2000, 3000, 1800, 4000 });

		mf.proceed(poss, staff, assets, real);

		double ADat = mf.getA().getValue(0);
		double alfaDat = mf.getAlfa().getValue(0);
		double betaDat = mf.getBeta().getValue(0);
		double gammaDat = mf.getGamma().getValue(0);

		double testADat = 1;
		double testAlfaDat = 0;
		double testBetaDat = 0;
		double testGammaDat = 1;

		assertTrue((ADat - testADat) < 0.001);
		assertTrue(alfaDat - testAlfaDat < 0.001);
		assertTrue(betaDat - testBetaDat < 0.001);
		assertTrue(gammaDat - testGammaDat < 0.001);

	}

	public static void main(String[] args) {
		TestRunner runner = new TestRunner();
		TestSuite suite = new TestSuite();
		suite.addTest(new ManFunTests("testFor3Param"));
		suite.addTest(new ManFunTests("testFor4Param"));
		runner.doRun(suite);
	}
}
