package engine.knn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class KnnAlgorithm {

	public KnnAlgorithm() {
		samples = new ArrayList<>();
	}

	public enum DistanceMethod {
		euclidean,
		taxi,
		chebyshev
	}

	public void addSample(Sample sample) {
		samples.add(sample);
	}

	public void addSamples(Collection<Sample> samples) {
		samples.addAll(samples);
	}

	public void addSamples(Sample[] samples) {
		for(int i = 0; i < samples.length; ++i)
			this.samples.add(samples[i]);
	}

	/*
	 * This methode estimates (judges) the label of the given candidate.
	 * 
	 * It returns an array list of numeric labels
	 * (which by the way is to contain only one element for a single-labeled data)
	 * of the k nearest neighbours of the candidate sample,
	 * basing on the certain metric of distance (euclidean, taxi, Chebyshev).
	 */
	public ArrayList<Integer> judge(
			Sample candidate, int kNeighbours, DistanceMethod method) {
		System.out.println("AAA");
		int numberOfLabels = candidate.numberOfLabels();
		ArrayList<TreeMap<Integer, Double>> scores = new ArrayList<>(numberOfLabels);
		for(int i = 0; i < numberOfLabels; ++i)
			scores.add(new TreeMap<>());

		// sort array basing on the candidate and the comparator
		samples.sort(createSampleComparator(candidate, method));

		for(int i = 0; i < kNeighbours; ++i) {
			Sample neighbour = samples.get(i);
			
		}

		return new ArrayList<Integer>();
	}

	private SampleComparator createSampleComparator(Sample model, DistanceMethod method) {
		switch(method) {
		case euclidean:
			return new SampleComparator(model, new EuclideanDistance());
		case taxi:
			return new SampleComparator(model, new TaxiDistance());
		case chebyshev:
			return new SampleComparator(model, new ChebyshevDistance());
		}
		return null;
	}

	private ArrayList<Sample> samples;
}
