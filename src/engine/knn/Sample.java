package engine.knn;

import java.util.ArrayList;

import app.Defaults;

/*
 * Holds a single sample for KNN algorithm.
 */

public class Sample {
	
	public Sample() {
		this(0);
	}

	public Sample(int label) {
		this.label = label;
		features = new ArrayList<>(Defaults.INIT_ARR_CAP);
	}

	public ArrayList<Double> features;

	public int label;


	public int numberOfFeatures() {
		return features.size();
	}
	
}
