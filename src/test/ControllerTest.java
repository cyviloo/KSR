package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.Iterator;

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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File f = new File(app.Defaults.EXPERIMENT_OUTPUT_FILENAME);
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String firstLine = br.readLine();
			String date = ctrl.getExperimentDate().toString();
			if(firstLine.matches(".*" + date + ".*")) {
				br.close();
				return true;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getName() {
		return "Controller";
	}

}
