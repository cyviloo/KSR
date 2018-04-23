package test;

import similarity.ISimilarityMeasurer;
import similarity.NGramMeasurer;

public class NGramMeasurerTest implements ITest {

	private static final int N = 2;

	@Override
	public boolean run() {
		ISimilarityMeasurer measurer = new NGramMeasurer(N);
		String s1 = "play";
		String s2 = "Play";
		String s3 = "plays";
		String s4 = "plai";
		String s5 = "play";
		String s6 = "at";

		if(measurer.getSimilarity(s1, s2) != 0.6666666666666666)
			return false;
		if(measurer.getSimilarity(s1, s3) != 0.75)
			return false;
		if(measurer.getSimilarity(s1, s4) != 0.6666666666666666)
			return false;
		if(measurer.getSimilarity(s1, s5) != 1)
			return false;
		if(measurer.getSimilarity(s1, s6) != 0)
			return false;

		return true;
	}

	@Override
	public String getName() {
		return "N-Gram Based Measurer of Similarity";
	}

}
