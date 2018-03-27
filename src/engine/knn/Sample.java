package engine.knn;

import java.util.ArrayList;

/*
 * Holds a single sample for KNN algorithm.
 */

public class Sample {
	
	public Sample() {
		features = new ArrayList<>();
		labels = new ArrayList<>();
	}

	public ArrayList<Double> features;

	public ArrayList<Integer> labels;


	public int numberOfFeatures() {
		return features.size();
	}

	public int numberOfLabels() {
		return labels.size();
	}
}
