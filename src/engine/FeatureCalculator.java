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
				if(maxString != null)
					if(observation.getFeatures().get(maxString) >
							base.getFeatures().get(s))
						maxSimilarity = 1 / maxSimilarity; // explanation to this line is below
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

	/*
	 * Explanation to the line above:
	 * Let us consider and example. Our base observation possesses a feature f1 which has been
	 * calculated to (say) 5. In other words, 5 is the number of occurrences of some word W1 in
	 * the base text. If we then take another observation and find for it a corresponding
	 * feature (let us call it f1') which is equal to 4 (4 occurrences of a corresponding word W2)
	 * we calculate similarity between W1 and W2. If W1 is not the same as W2 the similarity is
	 * less than 1. We should decrease value of f1' slightly to emphasize that the difference
	 * between W1 and W2 consists not only in the difference of the occurrences number but also
	 * in the fact that W1 is not exactly the same as W2.
	 * But... what if f1 = 4 and f1' = 5 ?
	 * Then (without the line of implementation above), decreasing the similarity factor of f1'
	 * would result in f1' approaching towards f1. That is opposite to what we want. By dividing
	 * one over maxSimilarity we increase f1' in fact. Thus in our example f1' should be slightly
	 * above 5, now.
	 */

	private Observation base;
	private ISimilarityMeasurer measurer;
	private double minAcceptableSimilarity;
	private static final double MAX = 1.0;
}
