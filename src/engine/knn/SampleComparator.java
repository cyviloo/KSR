package engine.knn;

import java.util.Comparator;

public class SampleComparator implements Comparator<Sample> {

	public SampleComparator(Sample model, SampleDistance distance) {
		this.model = model;
		this.distance = distance;
	} 

	@Override
	public int compare(Sample arg0, Sample arg1) {
		double dArg0 = distance.distance(model, arg0);
		double dArg1 = distance.distance(model, arg1);
		if(dArg0 > dArg1)
			return 1;
		else if(dArg0 < dArg1)
			return -1;
		else
			return 0;
	}

	private Sample model;
	private SampleDistance distance;
}
