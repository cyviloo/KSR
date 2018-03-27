package engine.knn;

class TaxiDistance implements SampleDistance {

	@Override
	public double distance(Sample s1, Sample s2) {
		double result = 0;
		int size = s1.numberOfFeatures();
		for(int i = 0; i < size; ++i) {
			result += Math.abs(s1.features.get(i) - s2.features.get(i));
		}
		return result;
	}

}
