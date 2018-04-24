package engine;

import java.util.SortedSet;

import engine.knn.Sample;
import engine.similarity.ISimilarityMeasurer;

/*
 * This class gets an observation (which is the subject of the experiment)
 * and is able to transform other observations into samples (knn.Sample).
 * Doing that, it can drop insignificant features of other observations,
 * unify similar features of two observations (using its similarity measurer)
 * into one feature and 
 */
public class FeatureCalculator {

	public FeatureCalculator(Observation baselineObservation,
			ISimilarityMeasurer measurer, double minAcceptableSimilarity) {
		base = baselineObservation;
		this.measurer = measurer;
		this.minAcceptableSimilarity = minAcceptableSimilarity;
	}

	public FeatureCalculator(Observation baselineObservation, ISimilarityMeasurer measurer) {
		this(baselineObservation, measurer, MAX);
	}

	public Sample getBaselineAsSample() {
		Sample result = new Sample(base.getEtiquette());
		SortedSet<String> keys = (SortedSet<String>) base.getFeatures().keySet();
		for(String s : keys) {
			result.features.add(base.getFeatures().get(s));
		}
		return result;
	}

	public Sample getOtherAsSample(Observation toAnalyse) {
		Observation observation = new Observation(toAnalyse);
		Sample result = new Sample(observation.getEtiquette());
		
		SortedSet<String> keys = (SortedSet<String>) base.getFeatures().keySet();
		SortedSet<String> otherKeys = (SortedSet<String>) observation.getFeatures().keySet();
		for(String s : keys) {
			String maxString = null;
			double maxSimilarity = 0;
			for(String os : otherKeys) {
				double similarity = measurer.getSimilarity(s, os);
				if(similarity > maxSimilarity) {
					maxString = os;
					maxSimilarity = similarity;
				}
			}
			if(maxSimilarity >= minAcceptableSimilarity) {
				double newValue = observation.getFeatures().get(maxString) * maxSimilarity;
				result.features.add(newValue);
			}
			else
				result.features.add(0.0);
			if(maxString != null)
				observation.getFeatures().remove(maxString);
		}
		return result;
	}

	private Observation base;
	private ISimilarityMeasurer measurer;
	private double minAcceptableSimilarity;
	private static final double MAX = 1.0;
}
