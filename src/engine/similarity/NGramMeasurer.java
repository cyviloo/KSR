package engine.similarity;

public class NGramMeasurer implements ISimilarityMeasurer {

	public NGramMeasurer(int N) {
		n = N;
	}

	@Override
	public double getSimilarity(String base, String examined) {
		if(base.length() > examined.length()) {
			String tmp = base;
			base = examined;
			examined = tmp;
		}
		
		int lastIndex = examined.length() - n;
		int occurrences = lastIndex + 1;
		int occur = 0;
		for(int i = 0; i <= lastIndex; ++i) {
			String ngram = examined.substring(i, i + n);
			if(base.matches(".*" + ngram + ".*"))
				occur++;
		}
		return (1.0 / occurrences) * occur;
	}

	private int n;
}
