package engine.knn;

public interface SampleDistance {

	/*
	 * Returns the distance between samples.
	 */
	public double distance(Sample s1, Sample s2);
}
