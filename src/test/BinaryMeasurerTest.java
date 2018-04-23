package test;

import similarity.BinaryMeasurer;
import similarity.ISimilarityMeasurer;

public class BinaryMeasurerTest implements ITest {

	@Override
	public boolean run() {
		ISimilarityMeasurer measurer = new BinaryMeasurer();
		String s1 = "play";
		String s2 = "Play";
		String s3 = "plays";
		String s4 = "plai";
		String s5 = "play";
		if(measurer.getSimilarity(s1, s2) != 0)
			return false;
		if(measurer.getSimilarity(s1, s3) != 0)
			return false;
		if(measurer.getSimilarity(s1, s4) != 0)
			return false;
		if(measurer.getSimilarity(s1, s5) != 1)
			return false;
		
		return true;
	}

	@Override
	public String getName() {
		return "Binary Measurer of Similarity";
	}

}
