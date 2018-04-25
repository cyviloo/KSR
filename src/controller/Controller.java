package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBException;

import engine.datainput.DataInputElement.EtiquetteType;
import engine.datainput.EtiquetteMap;
import engine.datainput.reuters.ReutersPlacesMap;
import engine.datainput.reuters.ReutersTopicsMap;
import engine.datainput.reuters.XmlReutersContainer;
import engine.datainput.reuters.XmlReutersUnmarshaller;
import engine.similarity.BinaryMeasurer;
import engine.similarity.ISimilarityMeasurer;
import engine.similarity.ISimilarityMeasurer.SimilarityMethod;
import engine.similarity.LevenshteinMeasurer;
import engine.similarity.NGramMeasurer;
import engine.stemmer.IStemmer;
import engine.stemmer.IStemmer.StemMethod;
import engine.stemmer.edu.stanford.nlp.process.Stemmer;

public class Controller {

	public Controller(ExperimentParameters parameters) {
		setExperimentParameters(parameters);
	}

	public void setExperimentParameters(ExperimentParameters parameters) {
		params = parameters;
	}

	public String getWordStoplistAsString() throws IOException {
		File f = new File(params.wordStopListFilename);
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String result = "";
		String line = "";
		while(line != null) {
			result += "\n" + line;
			line = reader.readLine();
		}
		reader.close();
		if(result.length() == 0)
			return null;
		return result.substring(2);
	}

	public Set<String> getWordStopListAsSet() throws IOException {
		File f = new File(params.wordStopListFilename);
		BufferedReader reader = new BufferedReader(new FileReader(f));
		Set<String> result = new HashSet<>();
		String line = reader.readLine();
		while(line != null) {
			result.add(line);
			line = reader.readLine();
		}
		reader.close();
		if(result.isEmpty())
			return null;
		return result;
	}

	public void runExperiment() throws JAXBException, IOException {
		XmlReutersUnmarshaller um;	// TODO add some new if there is going to be one
		XmlReutersContainer data;	// TODO same here
		um = new XmlReutersUnmarshaller(new File(params.xmlDataFilename));
		data = um.fetchData();

		Experimenter experimenter = new Experimenter(data, resolveEtiquetteMap(),
				params.trainingSetPctSize, params.minAcceptableSimilarity, resolveStemmer(),
				resolveSimilarityMeasurer(), params.metric, params.kNeighbours,
				getWordStopListAsSet(), params.numbersCleaning);

		ExperimentResults results = experimenter.run();
	}

	private EtiquetteMap resolveEtiquetteMap() {
		if(params.etiquette == EtiquetteType.reuters_places)
			return new ReutersPlacesMap();
		else if(params.etiquette == EtiquetteType.reuters_topics)
			return new ReutersTopicsMap();
		else
			return null;	// TODO add
	}

	private IStemmer resolveStemmer() {
		if(params.stemming == StemMethod.no_stemmer)
			return null;
		else if(params.stemming == StemMethod.porter_stemmer)
			return new Stemmer();
		else
			return null;	// if there comes some new stemmer, add it here
	}

	private ISimilarityMeasurer resolveSimilarityMeasurer() {
		if(params.similarity == SimilarityMethod.binary)
			return new BinaryMeasurer();
		else if(params.similarity == SimilarityMethod.levenshtein)
			return new LevenshteinMeasurer();
		else if(params.similarity == SimilarityMethod.ngram)
			return new NGramMeasurer(params.N);
		else
			return null;
	}

	private ExperimentParameters params;
}
