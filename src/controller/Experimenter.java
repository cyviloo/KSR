package controller;

import java.util.ArrayList;
import java.util.Set;

import engine.FeatureCalculator;
import engine.FeatureExtractor;
import engine.Features;
import engine.Observation;
import engine.datainput.EtiquetteMap;
import engine.datainput.reuters.ReutersPlacesMap;
import engine.datainput.reuters.XmlReutersContainer;
import engine.datainput.reuters.XmlReutersElement;
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
		ArrayList<XmlReutersElement> filteredData = filterUnwantedData(data);
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
			if(result == o.getEtiquette()) {
				ok++;
				String okEtiquette = o.getStringEtiquette();
				Integer thisLabelOk = results.labelsGuessed.get(okEtiquette);
				if(thisLabelOk == null)
					thisLabelOk = 0;
				results.labelsGuessed.put(okEtiquette, ++thisLabelOk);
			}
			else {
				nok++;
				String nokEtiquette = o.getStringEtiquette();
				Integer thisLabelNok = results.labelsNotGuessed.get(nokEtiquette);
				if(thisLabelNok == null)
					thisLabelNok = 0;
				results.labelsGuessed.put(nokEtiquette, ++thisLabelNok);
			}
		}
		int total = ok + nok;
		results.totalTests = total;
		results.accuracyPercent = (double)ok / total;
		return results;
	}

	private void splitInputSetIntoObservations(ArrayList<XmlReutersElement> data) {
		int i = 0;
		for(XmlReutersElement element : data) {
			Features features = extractor.extractFeatures(element.getTextValue());
			Observation o = new Observation(etiquetteMap, element, features);
			if(i < trainingSetSize)
				trainingSet.add(o);
			else
				validatingSet.add(o);
			i++;
		}
	}

	private ArrayList<XmlReutersElement> filterUnwantedData(XmlReutersContainer data) {
		ArrayList<XmlReutersElement> result = new ArrayList<>();
		for(XmlReutersElement el : data.getElements()) {
			for(int i = 0; i < ReutersPlacesMap.PLACES.length; ++i) {
				if(el.getEtiquette1().equals(ReutersPlacesMap.PLACES[i])) {
					result.add(el);
					break;
				}
			}
		}
		return result;
	}

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
