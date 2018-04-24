package engine.knn;

interface ISampleDistance {

	/*
	 * Returns the distance between samples.
	 */
	public double distance(Sample s1, Sample s2);
}
