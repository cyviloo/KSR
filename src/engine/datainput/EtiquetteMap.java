package engine.datainput;

import java.util.HashMap;

public class EtiquetteMap extends HashMap<String, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EtiquetteMap() {
		super(app.Defaults.INIT_ARR_CAP);
	}

	public String getStringByEtiquette(int etiquette) {
		for(String s : keySet())
			if(get(s).equals(etiquette))
				return s;
		return null;
	}

}
