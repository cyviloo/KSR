package engine;

import java.util.ArrayList;
import java.util.Set;

import engine.stemmer.IStemmer;
import engine.stemmer.TextCleaner;
import engine.stemmer.TextCleaner.Mode;
import similarity.ISimilarityMeasurer;

public class FeatureExtractor {

	public FeatureExtractor(
			Set<String> wordsStopList, IStemmer stemmer, boolean cleanWordsWithNumbers,
			Features baseFeatures, ISimilarityMeasurer similarityMeasurer) {
		cleaner = new TextCleaner(
				cleanWordsWithNumbers ? Mode.forbid_any_numbers : Mode.allow_all_numbers,
						wordsStopList);
		this.stemmer = stemmer;
		this.baseFeatures = baseFeatures;
		measurer = similarityMeasurer;
	}

	public Features extractFeatures(String text) {
		ArrayList<String> words;
		text = step1(text);
		words = step2(text);
		words = step3(words);
		Features f = new Features(words);
		fillFeatures(f, words);
		return f;
	}

	/*
	 * Unifies text's lettercase.
	 */
	private String step1(String text) {
		return text.toLowerCase();
	}

	/*
	 * Cleans text from interpunction, whitespaces, numbers (if needed).
	 */
	private ArrayList<String> step2(String text) {
		return cleaner.clean(text);
	}

	/*
	 * If given a stemmer, stemmization is performed.
	 */
	private ArrayList<String> step3(ArrayList<String> words) {
		if(stemmer == null)
			return words;
		
		ArrayList<String> result = new ArrayList<>();
		for(String s : words)
				result.add(stemmer.stem(s));
		return result;
	}

	private void fillFeatures(Features f, ArrayList<String> words) {
		if(baseFeatures == null) {
			for(String s : words) {
				double val = f.features.get(s);
				f.features.put(s, val + 1);
			}
		}
	}
	
	private TextCleaner cleaner;
	private IStemmer stemmer;
	private Features baseFeatures;
	private ISimilarityMeasurer measurer;
}