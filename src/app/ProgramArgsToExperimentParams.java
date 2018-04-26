package app;

import controller.ExperimentParameters;
import engine.datainput.DataInputElement.EtiquetteType;
import engine.knn.KnnAlgorithm.DistanceMethod;
import engine.similarity.ISimilarityMeasurer.SimilarityMethod;
import engine.stemmer.IStemmer.StemMethod;

class ProgramArgsToExperimentParams {

	static ExperimentParameters prepareParamsFromArgs(String[] args) {
		ExperimentParameters result = new ExperimentParameters();
		result.xmlDataFilename = args[0];
		result.wordStopListFilename = args[1].equals("null") ? null : args[1];
		result.etiquette = EtiquetteType.valueOf(args[2]);
		result.trainingSetPctSize = Integer.parseInt(args[3]);
		result.similarity = SimilarityMethod.valueOf(args[4]);
		result.N = Integer.parseInt(args[5]);
		result.minAcceptableSimilarity = Double.parseDouble(args[6]);
		result.stemming = StemMethod.valueOf(args[7]);
		result.metric = DistanceMethod.valueOf(args[8]);
		result.kNeighbours = Integer.parseInt(args[9]);
		result.numbersCleaning = args[10].equals("true") ? true : false;
		return result;
	}

}
