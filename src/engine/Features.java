package engine;

import java.util.List;
import java.util.TreeMap;


/*
 * The map that holds pairs:
 *  - the word itself
 *  - its value in the text
 */

public class Features extends TreeMap<String, Double> {

	public Features(List<String> words) {
		for(String s : words)
			put(s, 0.0);
	}

	public Features() {
		super();
	}

	private static final long serialVersionUID = 1L;
}
