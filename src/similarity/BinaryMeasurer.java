package similarity;

public class BinaryMeasurer implements ISimilarityMeasurer {

	@Override
	public double getSimilarity(String base, String examined) {
		if(base.equals(examined))
			return 1;
		else
			return 0;
	}

}
