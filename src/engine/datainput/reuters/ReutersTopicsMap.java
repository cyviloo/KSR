package engine.datainput.reuters;

import engine.datainput.EtiquetteMap;

public class ReutersTopicsMap extends EtiquetteMap {

	public ReutersTopicsMap() {
		super(TOPICS, ETIQUETTES);
	}

	@Override
	public String getName() {
		return "Reuters - TOPICS";
	}

	public final static String[] TOPICS = {
			"earn", "acq", "crude", "trade", "money-fx", "interest"
	};

	private final static int[] ETIQUETTES = {
			1, 2, 3, 4, 5, 6
	};	

	private static final long serialVersionUID = 1L;
}
