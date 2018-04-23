package test;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		/*
		 * Here we add new tests to be run.
		 */
		TESTS.add(new KnnTest());
		TESTS.add(new StemmerTest());
		TESTS.add(new TextCleanerTest());

		/*
		 * And then we run the tests.
		 */
		boolean result = runAllTests();
		if(result) {
			System.out.println("All tests passed!");
		}
		else {
			System.out.println("Some tests FAILED!");
		}
	}


	/*
	 * Private stuff...
	 */

	private static boolean runAllTests() {
		boolean result = true;
		int counter = 1;
		for(ITest test : TESTS) {
			System.out.println("TEST " + counter + " - Running test: " + test.getName());
			result = test.run();
			if(!result)
				System.out.println("TEST " + counter + " -  Test FAILED: " + test.getName() + " !");
			else
				System.out.println("TEST " + counter + " -  Test passed: " + test.getName());
		}
		System.out.println("");
		return result;
	}

	private static ArrayList<ITest> TESTS = new ArrayList<>();
}
