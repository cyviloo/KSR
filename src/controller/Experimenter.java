package controller;

import java.util.ArrayList;
import java.util.Set;

import engine.FeatureCalculator;
import engine.FeatureExtractor;
import engine.Features;
import engine.Observation;
import engine.datainput.DataInputElement;
import engine.datainput.EtiquetteMap;
import engine.datainput.reuters.XmlReutersContainer;
import engine.knn.KnnAlgorithm;
import engine.knn.KnnAlgorithm.DistanceMethod;
import engine.knn.Sample;
import engine.similarity.ISimilarityMeasurer;
import engine.stemmer.IStemmer;

/*
 * This class:
 *  - prepares training and validating sets
 *  - sets up experiment conditions
 *  - tests particular samples against the training set with KNN and returns results
 *  
 *  TODO: make this class (and its dependencies) more object oriented (mainly in terms of DataInput*)
 */
public class Experimenter {

	public Experimenter(XmlReutersContainer data, EtiquetteMap etiquetteMap,
			double trainingSetPercent, double minAcceptableSimilarity,
			IStemmer stemmer, ISimilarityMeasurer similarityMeasurer,
			DistanceMethod distanceMetric, int kNeighbors, Set<String> wordsStopList,
			boolean cleanWordsWithNumbers) {
		this.etiquetteMap = etiquetteMap;
		if(trainingSetPercent > MAX_TRAINING_SET_PERCENT)
			trainingSetPercent = MAX_TRAINING_SET_PERCENT;
		trainingSet = new ArrayList<>(1000);
		validatingSet = new ArrayList<>(1000);
		this.minAcceptableSimilarity = minAcceptableSimilarity;

		knn = new KnnAlgorithm();
		this.measurer = similarityMeasurer;
		this.distanceMetric = distanceMetric;
		this.kNeighbors = kNeighbors;

		extractor = new FeatureExtractor(wordsStopList, stemmer, cleanWordsWithNumbers);
		filteredData = filterUnwantedData(data);
		inputSetSize = filteredData.size();
		trainingSetSize = (int)((double)inputSetSize * trainingSetPercent / 100);
		splitInputSetIntoObservations(filteredData);
	}

	public ExperimentResults run() {
		ExperimentResults results = new ExperimentResults();
		int ok = 0;
		int nok = 0;
		for(Observation o : validatingSet) {
			calculator = new FeatureCalculator(o, measurer, minAcceptableSimilarity);
			Sample base = calculator.getBaselineAsSample();
			knn = new KnnAlgorithm();
			for(Observation other : trainingSet) {
				knn.addSample(calculator.getOtherAsSample(other));
			}
			int result = knn.judge(base, kNeighbors, distanceMetric);
			String etiq = o.getStringEtiquette();
			if(result == o.getEtiquette()) {
				ok++;
				int thisLabelOk;
				try { thisLabelOk = results.labelsGuessed.get(etiq); }
				catch (NullPointerException e) { thisLabelOk = 0; }
				results.labelsGuessed.put(etiq, ++thisLabelOk);
			}
			else {
				nok++;
				int thisLabelNok;
				try { thisLabelNok = results.labelsNotGuessed.get(etiq); }
				catch (NullPointerException e) { thisLabelNok = 0; }
				results.labelsNotGuessed.put(etiq, ++thisLabelNok);
			}
		}
		int total = ok + nok;
		results.totalTests = total;
		results.accuracyPercent = (double)ok * 100 / total;
		return results;
	}

	public ArrayList<DataInputElement> getFilteredData() {
		return filteredData;
	}

	private void splitInputSetIntoObservations(ArrayList<DataInputElement> data) {
		int i = 0;
		for(DataInputElement element : data) {
			Features features = extractor.extractFeatures(element.getTextValue());
			Observation o = new Observation(etiquetteMap, element, features);
			if(i < trainingSetSize)
				trainingSet.add(o);
			else
				validatingSet.add(o);
			i++;
		}
	}

	private ArrayList<DataInputElement> filterUnwantedData(XmlReutersContainer data) {
		ArrayList<DataInputElement> result = new ArrayList<>();
		int mapSize = etiquetteMap.size();
		for(DataInputElement el : data.getElements()) {
			for(int i = 0; i < mapSize; ++i) {
				if(etiquetteMap.getInputElementProperEtiquette(el).
						equals(etiquetteMap.names[i])) {
					result.add(el);
					break;
				}
			}
		}
		return result;
	}

	private ArrayList<DataInputElement> filteredData;
	private final int inputSetSize, trainingSetSize;
	private double minAcceptableSimilarity;
	private EtiquetteMap etiquetteMap;
	private ArrayList<Observation> trainingSet, validatingSet;
	private KnnAlgorithm knn;
	private ISimilarityMeasurer measurer;
	private DistanceMethod distanceMetric;
	private int kNeighbors;
	private FeatureCalculator calculator;
	private FeatureExtractor extractor;
	private static final double MAX_TRAINING_SET_PERCENT = 99.5;
}
