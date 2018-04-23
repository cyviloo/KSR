package engine.similarity;

public interface ISimilarityMeasurer {

	/*
	 * Any similarity measurer should take a word to examine and the base word
	 * and return the value of the similarity.
	 */
	public double getSimilarity(String base, String examined);
}
