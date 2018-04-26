package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.JAXBException;

import app.Defaults;
import engine.datainput.DataInputElement;
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
		etiquetteMap = resolveEtiquetteMap();
	}

	public void setExperimentParameters(ExperimentParameters parameters) {
		params = parameters;
	}

	public String getWordStoplistAsString() throws IOException {
		if(params.wordStopListFilename == null) {
			if(params.stopList == null)
				return null;
			String result = "";
			for(String s : params.stopList)
				result += "\n" + s;
			return result.substring(2);
		}
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
		if(params.wordStopListFilename == null) {
			return params.stopList;
		}
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
		date = new Date();
		XmlReutersUnmarshaller um;	// TODO add some new if there is going to be one
		XmlReutersContainer data;	// TODO same here
		um = new XmlReutersUnmarshaller(new File(params.xmlDataFilename));
		data = um.fetchData();

		Experimenter experimenter = new Experimenter(data, etiquetteMap,
				params.trainingSetPctSize, params.minAcceptableSimilarity, resolveStemmer(),
				resolveSimilarityMeasurer(), params.metric, params.kNeighbours,
				getWordStopListAsSet(), params.numbersCleaning);

		ExperimentResults results = experimenter.run();

		File output = new File(Defaults.EXPERIMENT_OUTPUT_FILENAME);
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));
		writer.write("Experiment from " + date);
		writer.newLine();
		writer.newLine();
		writer.write(" - - - CONDITIONS - - - ");
		writer.newLine();
		writer.write("Input dataset:");
		writer.newLine();
		int totalInput = 0;
		for(Map.Entry<String, Integer> entry :
			getInputDataQuantities(experimenter.getFilteredData()).entrySet()) {
			writer.write("\t" + entry.getKey() + " - " + entry.getValue());
			totalInput += entry.getValue();
			writer.newLine();
		}
		writer.write("\tTotal: " + totalInput);
		writer.newLine();
		writer.write("Etiquette: " + etiquetteMap.getName());
		writer.newLine();
		writer.write("Training set size: " + params.trainingSetPctSize + " %");
		writer.newLine();
		writer.write("Similarity method: " + params.similarity);
		writer.newLine();
		if(params.similarity != SimilarityMethod.binary) {
			writer.write("Minimal acceptable similarity: " + params.minAcceptableSimilarity);
			writer.newLine();
			if(params.similarity == SimilarityMethod.ngram) {
				writer.write("N-Gram number: " + params.N);
				writer.newLine();
			}
		}
		if(params.stemming == StemMethod.porter_stemmer)
			writer.write("Stemming: Porter's Stemming");
		else
			writer.write("Stemming: none");
		writer.newLine();
		if(params.numbersCleaning)
			writer.write("Numbers cleaning: applied");
		else
			writer.write("Numbers cleaning: not applied");
		writer.newLine();
		if(params.wordStopListFilename != null)
			writer.write("Word stop list: applied");
		else
			writer.write("Word stop list: not applied");
		writer.newLine();
		writer.write("K number of neighbours: " + params.kNeighbours);
		writer.newLine();
		writer.write("Distance metric: " + params.metric);
		writer.newLine();

		writer.newLine();
		writer.write(" - - - RESULTS - - - ");
		writer.newLine();
		writer.write("Total tests: " + results.totalTests);
		writer.newLine();
		writer.write("Accuracy: " + results.accuracyPercent + " %");
		writer.newLine();
		writer.write("Statistics per label:");
		writer.newLine();
		for(String s : results.labelsGuessed.keySet()) {
			int guessed;
			int notGuessed;
			try { guessed = results.labelsGuessed.get(s); }
			catch (NullPointerException e) { guessed = 0; }
			try { notGuessed = results.labelsNotGuessed.get(s); }
			catch (NullPointerException e) { notGuessed = 0; }

			writer.write("\t" + s + " - guessed: " + guessed);
			writer.newLine();
			writer.write("\t" + s + " - not guessed: " + notGuessed);
			writer.newLine();
			writer.write("\t" + s + " - accuracy: " +
					(double)guessed * 100 / (guessed + notGuessed) + " %");
			writer.newLine();
		}
		writer.write("Labels not guessed at all: ");
		for(String s : results.labelsNotGuessed.keySet())
			if(results.labelsGuessed.get(s) == null)
				writer.write(s + " ");
		writer.newLine();
		writer.close();
	}

	public Date getExperimentDate() {
		return date;
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

	private TreeMap<String, Integer> getInputDataQuantities(ArrayList<DataInputElement> data) {
		TreeMap<String, Integer> quantities = new TreeMap<>();
		for(DataInputElement el : data) {
			int q;
			String etiq = etiquetteMap.getInputElementProperEtiquette(el);
			
			try {
				q = quantities.get(etiq);
			} catch (NullPointerException e) {
				q = 0;
			}
			quantities.put(etiq, ++q);
		}
		return quantities;
	}

	private ExperimentParameters params;
	private EtiquetteMap etiquetteMap;
	private Date date;
}
