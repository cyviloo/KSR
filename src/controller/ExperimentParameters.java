package controller;

import engine.datainput.DataInputElement.EtiquetteType;
import engine.knn.KnnAlgorithm.DistanceMethod;
import engine.similarity.ISimilarityMeasurer.SimilarityMethod;
import engine.stemmer.IStemmer.StemMethod;

public class ExperimentParameters {

	/*
	 * The name of the XML file with experiment data.
	 */
	public String xmlDataFilename;

	/*
	 * The type of the data etiquette.
	 */
	public EtiquetteType etiquette;

	/*
	 * Percent of training set size.
	 */
	public int trainingSetPctSize;

	/*
	 * Which method of similarity measurment.
	 */
	public SimilarityMethod similarity;

	/*
	 * N parameter for N-Gram similarity measurment method.
	 */
	public int N;

	/*
	 * Minimal acceptable similarity.
	 */
	public double minAcceptableSimilarity;

	/*
	 * Which method of words stemming.
	 */
	public StemMethod stemming;

	/*
	 * Which metric of distance between samples.
	 */
	public DistanceMethod metric;

	/*
	 * Number of K (for KNN Algorithm).
	 */
	public int kNeighbours;

	/*
	 * Should number cleaning be performed?
	 */
	public boolean numbersCleaning;

	/*
	 * Name of the file containing word stoplist.
	 */
	public String wordStopListFilename;
}
