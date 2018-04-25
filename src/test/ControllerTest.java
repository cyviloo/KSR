package test;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import controller.Controller;
import controller.ExperimentParameters;
import engine.datainput.DataInputElement.EtiquetteType;
import engine.knn.KnnAlgorithm.DistanceMethod;
import engine.similarity.ISimilarityMeasurer.SimilarityMethod;
import engine.stemmer.IStemmer.StemMethod;

public class ControllerTest implements ITest {

	@Override
	public boolean run() {
		ExperimentParameters params = new ExperimentParameters();
		params.etiquette = EtiquetteType.reuters_places;
		params.kNeighbours = 4;
		params.metric = DistanceMethod.taxi;
		params.minAcceptableSimilarity = 0.9;
		params.N = 0;
		params.numbersCleaning = true;
		params.similarity = SimilarityMethod.binary;
		params.stemming = StemMethod.porter_stemmer;
		params.trainingSetPctSize = 65;
		params.wordStopListFilename = GetStopListFromFileTest.STOPLIST_FILE;
		params.xmlDataFilename = ExperimenterTest.xmlFileName;

		Controller ctrl = new Controller(params);
		try {
			ctrl.runExperiment();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getName() {
		return "Controller";
	}

}
