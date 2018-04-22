package engine.knn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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
	 * This method estimates (judges) the label of the given candidate.
	 * 
	 * It returns the numeric label of the most
	 * of the k nearest neighbours of the candidate sample,
	 * basing on the certain metric of distance (euclidean, taxi, Chebyshev).
	 */
	public int judge(Sample candidate, int kNeighbours, DistanceMethod method) {
		// sort array basing on the candidate and the comparator
		samples.sort(createSampleComparator(candidate, method));

		// a map for mappings: label -> no of occurrences
		HashMap<Integer, Integer> neighbours = new HashMap<>();

		// set label values of those k nearest neighbours
		for(int i = 0; i < kNeighbours; ++i) {
			Sample neighbour = samples.get(i);
			neighbours.put(neighbour.label, 0);
		}

		// increment label values that occurred and look for the winner
		int max = 0;
		for(int i = 0; i < kNeighbours; ++i) {
			Sample neighbour = samples.get(i);
			int value = neighbours.get(neighbour.label);
			++value;
			if(value > max)
				max = value;
			neighbours.put(neighbour.label, value);
		}

		for(int label : neighbours.keySet())
			if(neighbours.get(label) == max)
				return label;

		return -1;
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
