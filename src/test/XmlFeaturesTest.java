package test;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import engine.FeatureExtractor;
import engine.Features;
import engine.Observation;
import engine.datainput.DataInputElement;
import engine.datainput.XmlReutersContainer;
import engine.datainput.XmlReutersUnmarshaller;
import engine.stemmer.edu.stanford.nlp.process.Stemmer;

public class XmlFeaturesTest implements ITest {

	public XmlFeaturesTest() {
		try {
			XmlReutersUnmarshaller um =
					new XmlReutersUnmarshaller(new File(xmlFileName));
			data = um.fetchData();
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public boolean run() {
		ArrayList<Observation> observations = new ArrayList<>();
		FeatureExtractor extractor = new FeatureExtractor(null, new Stemmer(), true);
		for(DataInputElement e : data.getElements()) {
			Features features = extractor.extractFeatures(e.getTextValue());
			observations.add(new Observation(e, features));
		}

		if(observations.get(0).getEtiquette() != 2)
			return false;
		if(observations.get(1).getEtiquette() != 2)
			return false;
		if(observations.get(2).getEtiquette() != 4)
			return false;
		if(observations.get(3).getEtiquette() != 3)
			return false;

		try {
			if(observations.get(0).getFeatures().get("oil") != 3)
				return false;
			if(observations.get(1).getFeatures().get("network") != 2)
				return false;
			if(observations.get(2).getFeatures().get("that") != 5)
				return false;
			if(observations.get(3).getFeatures().get("time") != 7)
				return false;
		} catch (NullPointerException e) {
			System.out.println("NullPointerException when getting feature values!");
			return false;
		}

		return true;
	}

	@Override
	public String getName() {
		return "Feature extraction from XML file";
	}

	private static final String xmlFileName = "data/test.xml";
	private XmlReutersContainer data = null;
}
