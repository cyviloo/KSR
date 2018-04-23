package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import engine.stemmer.TextCleaner;
import engine.stemmer.TextCleaner.Mode;

public class TextCleanerTest implements ITest {

	public TextCleanerTest() {
		txt1_allowAll = new ArrayList<>();
		txt1_cutOut = new ArrayList<>();
		txt1_forbid = new ArrayList<>();
		txt1_cutOut_stopList = new ArrayList<>();
		stopList = new HashSet<String>();

		txt1_allowAll.add("All");
		txt1_cutOut.add("All");
		txt1_forbid.add("All");
		txt1_cutOut_stopList.add("All");

		txt1_allowAll.add("you");
		txt1_cutOut.add("you");
		txt1_forbid.add("you");

		txt1_allowAll.add("4");

		txt1_allowAll.add("playful");
		txt1_cutOut.add("playful");
		txt1_forbid.add("playful");
		txt1_cutOut_stopList.add("playful");

		txt1_allowAll.add("joinable");
		txt1_cutOut.add("joinable");
		txt1_forbid.add("joinable");

		txt1_allowAll.add("thre1ads");
		txt1_cutOut.add("threads");
		txt1_cutOut_stopList.add("threads");

		stopList.add("you");
		stopList.add("joinable");
	}

	@Override
	public boolean run() {
		TextCleaner cleaner = new TextCleaner(Mode.allow_all_numbers, null);

		ArrayList<String> result = cleaner.clean(txt1);
		int counter = 0;
		for(String s : result) {
			if(!s.equals(txt1_allowAll.get(counter++)))
				return false;
		}

		cleaner = new TextCleaner(Mode.cut_out_numbers, null);
		counter = 0;
		result = cleaner.clean(txt1);
		for(String s : result) {
			if(!s.equals(txt1_cutOut.get(counter++)))
				return false;
		}

		cleaner = new TextCleaner(Mode.forbid_any_numbers, null);
		counter = 0;
		result = cleaner.clean(txt1);
		for(String s : result) {
			if(!s.equals(txt1_forbid.get(counter++)))
				return false;
		}

		cleaner = new TextCleaner(Mode.cut_out_numbers, stopList);
		counter = 0;
		result = cleaner.clean(txt1);
		for(String s : result) {
			if(!s.equals(txt1_cutOut_stopList.get(counter++)))
				return false;
		}

		return true;
	}

	@Override
	public String getName() {
		return "Text cleaner";
	}

	private static final String txt1 =
			"All you 4 playful, joinable thre1ads!";
	private final ArrayList<String> txt1_allowAll;
	private final ArrayList<String> txt1_cutOut;
	private final ArrayList<String> txt1_forbid;
	private final ArrayList<String> txt1_cutOut_stopList;
	private final Set<String> stopList;
}
