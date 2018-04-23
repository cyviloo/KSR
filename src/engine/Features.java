package engine;

import java.util.HashMap;
import java.util.List;

public class Features {

	public Features() {
		features = new HashMap<>();
	}

	public Features(List<String> words) {
		this();
		for(String s : words)
			features.put(s, 0.0);
	}

	/*
	 * The map that holds pairs:
	 *  - the word itself
	 *  - its value in the text
	 */
	public HashMap<String, Double> features;
}
