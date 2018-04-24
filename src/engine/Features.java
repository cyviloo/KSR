package engine;

import java.util.HashMap;
import java.util.List;


/*
 * The map that holds pairs:
 *  - the word itself
 *  - its value in the text
 */

public class Features extends HashMap<String, Double> {

	public Features(List<String> words) {
		for(String s : words)
			put(s, 0.0);
	}

}
