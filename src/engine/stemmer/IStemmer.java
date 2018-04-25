package engine.stemmer;

public interface IStemmer {

	public static enum StemMethod {
		no_stemmer,
		porter_stemmer
	}

	/*
	 * Stemmer must transform a word into its basic form.
	 */
	public String stem(String s);
}
