package engine;

import engine.datainput.EtiquetteMap;
import engine.datainput.reuters.DataInputElement;

/*
 * This class holds information: { etiquette, features[] }
 */
public class Observation {

	public Observation(EtiquetteMap map,
			DataInputElement dataInputElement, Features features) {
		this.map = map;
		etiquette = map.get(dataInputElement.getEtiquette1());
		setFeatures(features);
	}

	public Observation(EtiquetteMap map, DataInputElement dataInputElement) {
		this(map, dataInputElement, null);
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
