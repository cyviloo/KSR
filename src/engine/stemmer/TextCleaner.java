package engine.stemmer;

import java.util.ArrayList;

public class TextCleaner {

	public enum Mode {
		allow_all_numbers,
		cut_out_numbers,
		forbid_any_numbers
	}

	public TextCleaner(Mode mode) {
		this.mode = mode;
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
			for(int i = 0; i < words.length; ++i)
				ws.add(words[i]);
			return ws;
		} else {
			for(int i = 0; i < words.length; ++i) {
				try {
					Integer.parseInt(words[i]);
					Double.parseDouble(words[i]);
				} catch (NumberFormatException e) {
					ws.add(words[i]);
				}
			}
		}
		ArrayList<String> res = new ArrayList<>();
		if(mode == Mode.forbid_any_numbers) {
			for(String s : ws) {
				if(!s.matches(".*[^A-Za-z].*"))
					res.add(s);
			}
		} else if(mode == Mode.cut_out_numbers) {
			for(String s : ws) {
				s.replaceAll("[^A-Za-z]","");
			}
		}
		return res;
	}

	private Mode mode;
	private String[] words;

	private static final String INTERPUNCT_SPLIT_REGEX = "[\\p{Punct}\\s]+";
}
