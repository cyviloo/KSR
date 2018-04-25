package engine;

import engine.datainput.EtiquetteMap;
import engine.datainput.reuters.DataInputElement;
import engine.datainput.reuters.ReutersPlacesMap;

/*
 * This class holds information: { etiquette, features[] }
 */
public class Observation {

	public Observation(EtiquetteMap map,
			DataInputElement dataInputElement, Features features) {
		this.map = map;
		if(map instanceof ReutersPlacesMap)
			etiquette = map.get(dataInputElement.getEtiquette1());
		else
			etiquette = map.get(dataInputElement.getEtiquette2());
		setFeatures(features);
	}

	public Observation(Observation other) {
		map = other.map;
		etiquette = other.etiquette;
		features = new Features();
		for(String s : other.features.keySet()) {
			features.put(new String(s), other.features.get(s));
		}
	}

	public int getEtiquette() {
		return etiquette;
	}

	public String getStringEtiquette() {
		return map.getStringByEtiquette(getEtiquette());
	}

	public Features getFeatures() {
		return features;
	}

	public void setFeatures(Features features) {
		this.features = features;
	}

	private int etiquette;
	private Features features;
	private final EtiquetteMap map;
}
