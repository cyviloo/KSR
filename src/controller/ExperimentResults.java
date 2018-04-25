package controller;

import java.util.HashMap;

public class ExperimentResults {

	/*
	 * Total number of performed KNN tests.
	 */
	public int totalTests;

	/*
	 * Percent of accuracy.
	 */
	public double accuracyPercent;

	/*
	 * All the labels guessed correctly (together with numbers of every label guessed correctly).
	 */
	public HashMap<String, Integer> labelsGuessed = new HashMap<>(app.Defaults.INIT_ARR_CAP);

	/*
	 * All the labels guessed incorrectly (together with numbers of every label guessed incorrectly).
	 */
	public HashMap<String, Integer> labelsNotGuessed = new HashMap<>(app.Defaults.INIT_ARR_CAP);
}
