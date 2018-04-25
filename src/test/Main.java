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
		TESTS.add(new FeatureExtractorTest());
		TESTS.add(new BinaryMeasurerTest());
		TESTS.add(new LevenshteinMeasurerTest());
		TESTS.add(new NGramMeasurerTest());
		TESTS.add(new XmlFeaturesTest());
		TESTS.add(new FeatureCalculatorTest());
		TESTS.add(new ExperimenterTest());
		TESTS.add(new GetStopListFromFileTest());

		/*
		 * And then we run the tests.
		 */
		if(runAllTests()) {
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
		boolean finalResult = true;
		int counter = 1;
		for(ITest test : TESTS) {
			System.out.println(adjustLog(counter) + " - Running test: " + test.getName());
			result = test.run();
			if(!result) {
				System.out.println(adjustLog(counter) + " -  Test FAILED: " + test.getName() + " !");
				finalResult = result;
			}
			else
				System.out.println(adjustLog(counter) + " -  Test passed: " + test.getName());
			counter++;
		}
		System.out.println("");
		return finalResult;
	}

	private static String adjustLog(int counter) {
		return (counter < 10) ? "TEST  " + counter : "TEST " + counter;
	}

	private static ArrayList<ITest> TESTS = new ArrayList<>();
}
