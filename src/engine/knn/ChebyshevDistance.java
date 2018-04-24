package engine.knn;

class ChebyshevDistance implements ISampleDistance {

	@Override
	public double distance(Sample s1, Sample s2) {
		int size = s1.numberOfFeatures();
		double[] results = new double[size];
		for(int i = 0; i < size; ++i) {
			results[i] += Math.abs(s1.features.get(i) - s2.features.get(i));
		}
		double result = results[0];
		for(int i = 1; i < size; ++i)
			if(results[i] > result)
				result = results[i];
		return result;
	}

}
