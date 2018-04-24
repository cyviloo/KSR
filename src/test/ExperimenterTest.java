package test;

import java.io.File;

import javax.xml.bind.JAXBException;

import controller.Experimenter;
import engine.datainput.reuters.ReutersPlacesMap;
import engine.datainput.reuters.XmlReutersUnmarshaller;
import engine.knn.KnnAlgorithm.DistanceMethod;
import engine.similarity.LevenshteinMeasurer;
import engine.stemmer.edu.stanford.nlp.process.Stemmer;

public class ExperimenterTest implements ITest {

	public ExperimenterTest() {
		try {
			XmlReutersUnmarshaller um =
					new XmlReutersUnmarshaller(new File("/home/hcooh/workspace/ksr/data/reuters.xml"));
			experimenter = new Experimenter(um.fetchData(),
					new ReutersPlacesMap(), TRAINING_PCT, new Stemmer(),
					new LevenshteinMeasurer(), DistanceMethod.euclidean, 10,
					null, true);
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public boolean run() {
		experimenter.run();
		return false;
	}

	@Override
	public String getName() {
		return "Experimenter";
	}

	private Experimenter experimenter;
	private static final double TRAINING_PCT = 60;
}
