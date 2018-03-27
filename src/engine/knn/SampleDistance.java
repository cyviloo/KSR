package engine.knn;

interface SampleDistance {

	/*
	 * Returns the distance between samples.
	 */
	public double distance(Sample s1, Sample s2);
}
