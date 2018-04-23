package engine.stemmer;

import java.util.ArrayList;
import java.util.Set;

public class TextCleaner {

	public enum Mode {
		allow_all_numbers,
		cut_out_numbers,
		forbid_any_numbers
	}

	/*
	 * mode: do we (and how) remove the numbers from the text
	 * wordsStopList: the list we ignore when extracting features
	 */
	public TextCleaner(Mode mode, Set<String> wordsStopList) {
		this.mode = mode;
		stopList = wordsStopList;
	}

	public ArrayList<String> clean(String text) {
		step1(text);
		return step2();
	}

	/*
	 * Step 1: cuts the text using tokens like white spaces and
	 * interpunction marks.
	 */
	private void step1(String text) {
		words = text.split(INTERPUNCT_SPLIT_REGEX);
	}

	/*
	 * Step 2: gets rid of the numbers (if decided)
	 */
	private ArrayList<String> step2() {
		ArrayList<String> ws = new ArrayList<>();
		if(mode == Mode.allow_all_numbers) {
			if(stopList == null) {
				for(int i = 0; i < words.length; ++i)
					ws.add(words[i]);
				return ws;
			}
			else {
				for(int i = 0; i < words.length; ++i)
					if(!stopList.contains(words[i]))
						ws.add(words[i]);
				return ws;
			}
		}
		else {
			if(stopList == null) {
				for(int i = 0; i < words.length; ++i) {
					try {
						Integer.parseInt(words[i]);
						Double.parseDouble(words[i]);
					} catch (NumberFormatException e) {
						ws.add(words[i]);
					}
				}
			}
			else {
				for(int i = 0; i < words.length; ++i) {
					try {
						Integer.parseInt(words[i]);
						Double.parseDouble(words[i]);
					} catch (NumberFormatException e) {
						if(!stopList.contains(words[i]))
							ws.add(words[i]);
					}
				}
			}
		}
		ArrayList<String> res = new ArrayList<>();
		if(mode == Mode.forbid_any_numbers) {
			if(stopList == null) {
				for(String s : ws) {
					if(!s.matches(".*[^A-Za-z].*"))
						res.add(s);
				}
			}
			else {
				for(String s : ws) {
					if(!s.matches(".*[^A-Za-z].*"))
						if(!stopList.contains(s))
							res.add(s);
				}
			}
		}
		else if(mode == Mode.cut_out_numbers) {
			if(stopList == null) {
				for(String s : ws) {
					s = s.replaceAll("[^A-Za-z]","");
				}
			}
			else {
				ArrayList<String> result = new ArrayList<>();
				for(String s : ws) {
					s = s.replaceAll("[^A-Za-z]","");
					if(!stopList.contains(s))
						result.add(s);
				}
				return result;
			}
		}
		return res;
	}

	private Mode mode;
	private String[] words;
	private Set<String> stopList;

	private static final String INTERPUNCT_SPLIT_REGEX = "[\\p{Punct}\\s]+";
}
