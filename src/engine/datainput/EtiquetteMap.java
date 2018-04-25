package engine.datainput;

import java.util.HashMap;

public class EtiquetteMap extends HashMap<String, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EtiquetteMap(String[] names, int[] etiquettes) {
		super(app.Defaults.INIT_ARR_CAP);
		final int psize = names.length;
		final int esize = names.length;
		this.names = names;
		this.etiquettes = etiquettes;
		if(psize != esize)
			throw new ArrayIndexOutOfBoundsException(
					"NAMES and ETIQUETTES must have the same lengths!");
		for(int i = 0; i < psize; ++i)
			put(names[i], etiquettes[i]);
	}

	public String getStringByEtiquette(int etiquette) {
		for(String s : keySet())
			if(get(s).equals(etiquette))
				return s;
		return null;
	}

	public String getName() {
		return "no name";
	}

	public String names[];
	public int etiquettes[];
}
