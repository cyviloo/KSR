package engine.stemmer;

public interface IStemmer {

	/*
	 * Stemmer must transform a word into its basic form.
	 */
	public String stem(String s);
}
