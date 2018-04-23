package test;

import engine.stemmer.edu.stanford.nlp.process.Stemmer;

public class StemmerTest implements ITest {

	@Override
	public boolean run() {
		if(!stemmer.stem("ability").equals("abil"))
			return false;
		if(!stemmer.stem("roses").equals("rose"))
			return false;
		if(!stemmer.stem("enjoys").equals("enjoi"))
			return false;
		if(!stemmer.stem("playful").equals("play"))
			return false;
		System.out.println(stemmer.stem("39z"));
		return true;
	}

	@Override
	public String getName() {
		return "Porter Stemming Algorithm";
	}

	private static Stemmer stemmer = new Stemmer();
}
