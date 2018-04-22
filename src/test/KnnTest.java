package test;

import java.util.ArrayList;
import java.util.Random;

import engine.knn.KnnAlgorithm;
import engine.knn.Sample;

public class KnnTest implements ITest {

	public KnnTest() {
		samples = new ArrayList<>();
		prepareSamples();
		knn = new KnnAlgorithm(samples);
	}

	@Override
	public boolean run() {
		int k = 3;
		Sample pattern = new Sample();
		pattern.label = 3;
		pattern.features.add(21.335);
		pattern.features.add(41.334);
		pattern.features.add(58.323);
		pattern.features.add(79.133);
		pattern.features.add(104.383);

		KnnAlgorithm.DistanceMethod metric = KnnAlgorithm.DistanceMethod.chebyshev;
		int result = knn.judge(pattern, k, metric);
		if(result != pattern.label) {
			logJudgedVsReality(result, pattern.label, metric);
			return false;
		}
		metric = KnnAlgorithm.DistanceMethod.euclidean;
		result = knn.judge(pattern, k, metric);
		if(result != pattern.label) {
			logJudgedVsReality(result, pattern.label, metric);
			return false;
		}
		metric = KnnAlgorithm.DistanceMethod.taxi;
		result = knn.judge(pattern, k, metric);
		if(result != pattern.label) {
			logJudgedVsReality(result, pattern.label, metric);
			return false;
		}

		k = 5;
		pattern = new Sample();
		pattern.label = 2;
		pattern.features.add(1.335);
		pattern.features.add(5.334);
		pattern.features.add(3.323);
		pattern.features.add(34.133);
		pattern.features.add(50.383);
		metric = KnnAlgorithm.DistanceMethod.chebyshev;
		result = knn.judge(pattern, k, metric);
		if(result != pattern.label) {
			logJudgedVsReality(result, pattern.label, metric);
			return false;
		}
		metric = KnnAlgorithm.DistanceMethod.euclidean;
		result = knn.judge(pattern, k, metric);
		if(result != pattern.label) {
			logJudgedVsReality(result, pattern.label, metric);
			return false;
		}
		metric = KnnAlgorithm.DistanceMethod.taxi;
		result = knn.judge(pattern, k, metric);
		if(result != pattern.label) {
			logJudgedVsReality(result, pattern.label, metric);
			return false;
		}

		k = 12;
		pattern = new Sample();
		pattern.label = 1;
		pattern.features.add(0.335);
		pattern.features.add(-5.334);
		pattern.features.add(3.323);
		pattern.features.add(34.133);
		pattern.features.add(-50.383);
		metric = KnnAlgorithm.DistanceMethod.chebyshev;
		result = knn.judge(pattern, k, metric);
		if(result != pattern.label) {
			logJudgedVsReality(result, pattern.label, metric);
			return false;
		}
		metric = KnnAlgorithm.DistanceMethod.euclidean;
		result = knn.judge(pattern, k, metric);
		if(result != pattern.label) {
			logJudgedVsReality(result, pattern.label, metric);
			return false;
		}
		metric = KnnAlgorithm.DistanceMethod.taxi;
		result = knn.judge(pattern, k, metric);
		if(result != pattern.label) {
			logJudgedVsReality(result, pattern.label, metric);
			return false;
		}

		k = 25;
		pattern = new Sample();
		pattern.label = 4;
		pattern.features.add(31.335);
		pattern.features.add(55.334);
		pattern.features.add(86.323);
		pattern.features.add(104.133);
		pattern.features.add(130.383);
		metric = KnnAlgorithm.DistanceMethod.chebyshev;
		result = knn.judge(pattern, k, metric);
		if(result != pattern.label) {
			logJudgedVsReality(result, pattern.label, metric);
			return false;
		}
		metric = KnnAlgorithm.DistanceMethod.euclidean;
		result = knn.judge(pattern, k, metric);
		if(result != pattern.label) {
			logJudgedVsReality(result, pattern.label, metric);
			return false;
		}
		metric = KnnAlgorithm.DistanceMethod.taxi;
		result = knn.judge(pattern, k, metric);
		if(result != pattern.label) {
			logJudgedVsReality(result, pattern.label, metric);
			return false;
		}

		return true;
	}

	@Override
	public String getName() {
		return "KNN Algorithm";
	}

	private void prepareSamples() {
		Random rand = new Random();
		for(int i = 0; i < NUMBER_OF_SAMPLES; ++i) {
			Sample s = new Sample();
			/*
			 * s.label = ...
			 * 1 for first 50 samples
			 * 2 for second 50 samples
			 * etc.
			 */
			s.label = i / (NUMBER_OF_SAMPLES / NUMBER_OF_LABELS) + 1;
			for(int f = 1; f <= NUMBER_OF_FEATURES; ++f) {
				double value = (double)
						(10 * f * (i / (NUMBER_OF_SAMPLES / NUMBER_OF_LABELS)) +
						rand.nextDouble());
				s.features.add(value);
//				System.out.println("smpl " + i + " f " + f + " val " + value);
			}
			samples.add(s);
		}
	}

	private void logJudgedVsReality(int judged, int reality, KnnAlgorithm.DistanceMethod method) {
		System.out.println("Pattern's label was: " + reality + ", judged: " + judged
				+ ", Distance metric: " + method);
	}

	private static final int NUMBER_OF_LABELS = 4;
	private static final int NUMBER_OF_FEATURES = 5;
	private static final int NUMBER_OF_SAMPLES = 200;
	private KnnAlgorithm knn;
	private ArrayList<Sample> samples;
}
