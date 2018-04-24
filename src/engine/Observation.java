package engine;

import engine.datainput.DataInputElement;
import engine.datainput.PlacesMap;

/*
 * This class holds information: { etiquette, features[] }
 */
public class Observation {

	public Observation(DataInputElement dataInputElement, Features features) {
		// for now: only <PLACES> etiquette
		etiquette = placesMap.get(dataInputElement.getEtiquette1());
		setFeatures(features);
	}

	public Observation(DataInputElement dataInputElement) {
		this(dataInputElement, null);
	}

	public int getEtiquette() {
		return etiquette;
	}

	public Features getFeatures() {
		return features;
	}

	public void setFeatures(Features features) {
		this.features = features;
	}

	private int etiquette;
	private Features features;
	private static final PlacesMap placesMap = new PlacesMap();
}
