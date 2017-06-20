package tests;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class FieldsInteractionTests extends TestCase {

	public FieldsInteractionTests(String testName) {
		super(testName);
	}

	public void testFI() {
		assertTrue(FuncForTest.equals(null,null));
	}

	public static void main(String[] args) {
		TestRunner runner = new TestRunner();
		TestSuite suite = new TestSuite();
		suite.addTest(new PopulationTests("testFI"));
		runner.doRun(suite);
	}
}
