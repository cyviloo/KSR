package test;

import engine.similarity.ISimilarityMeasurer;
import engine.similarity.LevenshteinMeasurer;

public class LevenshteinMeasurerTest implements ITest {

	@Override
	public boolean run() {
		ISimilarityMeasurer measurer = new LevenshteinMeasurer();
		String s1 = "play";
		String s2 = "Play";
		String s3 = "plays";
		String s4 = "plai";
		String s5 = "play";
		String s6 = "at";
		if(measurer.getSimilarity(s1, s2) != 0.9105572809000084)
			return false;
		if(measurer.getSimilarity(s1, s3) != 0.9105572809000084)
			return false;
		if(measurer.getSimilarity(s1, s4) != 0.9105572809000084)
			return false;
		if(measurer.getSimilarity(s1, s5) != 1)
			return false;
		if(measurer.getSimilarity(s1, s6) != 0.53524199845511)
			return false;
		return true;
	}

	@Override
	public String getName() {
		return "Levenshtein Distance based Measurer of Similarity";
	}

}
