package test;

import engine.FeatureCalculator;
import engine.FeatureExtractor;
import engine.Features;
import engine.Observation;
import engine.datainput.DataInputElement;
import engine.datainput.reuters.ReutersPlacesMap;
import engine.knn.Sample;
import engine.similarity.LevenshteinMeasurer;

public class FeatureCalculatorTest implements ITest {

	public FeatureCalculatorTest() {
		FeatureExtractor extractor = new FeatureExtractor(null, null, true);
		DataInputElement element = new DataInputElementStub();
		Features f = extractor.extractFeatures(element.getTextValue());
		base = new Observation(new ReutersPlacesMap(), element, f);

		element = new DataInputElementStubOther();
		f = extractor.extractFeatures(element.getTextValue());
		other = new Observation(new ReutersPlacesMap(), element, f);
		calc = new FeatureCalculator(base, new LevenshteinMeasurer(), 0.7);
	}

	@Override
	public boolean run() {
		Sample b = calc.getBaselineAsSample();
		Sample o = calc.getOtherAsSample(other);

		if(b.label != 2)
			return false;
		if(o.label != 2)
			return false;

		if(b.features.get(0) != 2)
			return false;
		if(b.features.get(1) != 1)
			return false;
		if(b.features.get(2) != 1)
			return false;
		if(b.features.get(3) != 1)
			return false;

		if(o.features.get(0) != 3.2683281572999747)
			return false;
		if(o.features.get(1) != 0.7470177871865296)
			return false;
		if(o.features.get(2) != 0.9105572809000084)
			return false;
		if(o.features.get(3) != 1)
			return false;

		return true;
	}

	@Override
	public String getName() {
		return "Feature Calculator";
	}

	private FeatureCalculator calc;
	private Observation base, other;


	private static class DataInputElementStub implements DataInputElement {

		@Override
		public String getEtiquette1() {
			return "usa";
		}

		@Override
		public String getEtiquette2() {
			return "";
		}

		@Override
		public String getTextValue() {
			return "This article Article contains play";
		}

	}

	private static class DataInputElementStubOther extends DataInputElementStub {
		public String getTextValue() {
			return "this ArTicles articles aRticles contained playt";
		}
	}
}
