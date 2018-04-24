package engine.datainput.reuters;

import engine.datainput.EtiquetteMap;

public class ReutersPlacesMap extends EtiquetteMap {

	public ReutersPlacesMap() {
		final int psize = PLACES.length;
		final int esize = PLACES.length;
		if(psize != esize)
			throw new ArrayIndexOutOfBoundsException(
					"PLACES and ETIQUETTES must have the same lengths!");
		for(int i = 0; i < psize; ++i)
			put(PLACES[i], ETIQUETTES[i]);
	}

	private static final String[] PLACES = {
			"west-germany", "usa", "france", "uk", "canada", "japan" 
	};

	private static final int[] ETIQUETTES = {
			1, 2, 3, 4, 5, 6
	};

	private static final long serialVersionUID = 1L;
}
