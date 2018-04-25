package engine.datainput.reuters;

import engine.datainput.EtiquetteMap;

public class ReutersTopicsMap extends EtiquetteMap {

	public ReutersTopicsMap() {
		super(TOPICS, ETIQUETTES);
	}

	public final static String[] TOPICS = {
			"?", "?!", "xxx", "XX", "x", "ABC"	// TODO check and edit
	};

	private final static int[] ETIQUETTES = {
			1, 2, 3, 4, 5, 6
	};	

	private static final long serialVersionUID = 1L;
}
