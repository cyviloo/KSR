package engine.knn;

class EuclideanDistance implements SampleDistance {

	@Override
	public double distance(Sample s1, Sample s2) {
		double result = 0;
		int size = s1.numberOfFeatures();
		for(int i = 0; i < size; ++i) {
			double iDist = (s1.features.get(i) - s2.features.get(i));
			result += iDist * iDist;
		}
		return Math.sqrt(result);
	}

}
