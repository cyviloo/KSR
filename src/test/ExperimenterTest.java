package test;

import java.io.File;

import javax.xml.bind.JAXBException;

import controller.Experimenter;
import engine.datainput.reuters.ReutersPlacesMap;
import engine.datainput.reuters.XmlReutersUnmarshaller;
import engine.knn.KnnAlgorithm.DistanceMethod;
import engine.similarity.BinaryMeasurer;
import engine.stemmer.edu.stanford.nlp.process.Stemmer;

public class ExperimenterTest implements ITest {

	public ExperimenterTest() {
		try {
			XmlReutersUnmarshaller um =
					new XmlReutersUnmarshaller(new File(xmlFileName));
			experimenter = new Experimenter(um.fetchData(),
					new ReutersPlacesMap(), TRAINING_PCT, 1, new Stemmer(),
					new BinaryMeasurer(), DistanceMethod.euclidean, 3,
					null, true);
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public boolean run() {
		if(experimenter.run() > 0.8)
			return true;
		else
			return false;
	}

	@Override
	public String getName() {
		return "Experimenter";
	}

	private Experimenter experimenter;
	private static final double TRAINING_PCT = 60;
	static final String xmlFileName = "data/test2.xml";
}
