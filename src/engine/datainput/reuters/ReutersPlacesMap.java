package engine.datainput.reuters;

import engine.datainput.EtiquetteMap;

public class ReutersPlacesMap extends EtiquetteMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3089117510182851884L;

	public ReutersPlacesMap() {
		super(PLACES, ETIQUETTES);
	}

	public static String[] PLACES = {
			"west-germany", "usa", "france", "uk", "canada", "japan" 
	};

	private final static int[] ETIQUETTES = {
			1, 2, 3, 4, 5, 6
	};
}
