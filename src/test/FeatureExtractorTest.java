package test;

import java.util.HashSet;
import java.util.Set;

import engine.FeatureExtractor;
import engine.Features;
import engine.stemmer.edu.stanford.nlp.process.Stemmer;

public class FeatureExtractorTest implements ITest {

	@Override
	public boolean run() {
		Set<String> stopList = new HashSet<>();
		stopList.add("though");

		FeatureExtractor extractor = new FeatureExtractor(stopList, new Stemmer(), true);
		Features features = extractor.extractFeatures(txt1);
		if(features.get("play") != 1.0)
			return false;
		if(features.get("plai") != 4.0)
			return false;
		if(features.get("piano") != 2.0)
			return false;
		if(features.get("color") != 4.0)
			return false;
		if(features.get("player") != 1.0)
			return false;
		if(features.get("though") != null)
			return false;

		return true;
	}

	@Override
	public String getName() {
		return "Feature Extractor";
	}

	private static final String txt1 =
			"Playful ;'play Colorful Colors' colored % playing piano player, " +
			"colorful?   playing plays pianoes, though.";
}
