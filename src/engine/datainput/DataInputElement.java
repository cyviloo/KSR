package engine.datainput;

/*
 * Classes implementing this interface must be able to provide their:
 *  - Etiquette
 *  - TextValue
 */
public interface DataInputElement {

	public String getEtiquette1();

	public String getEtiquette2();

	public String getTextValue();

	public static enum EtiquetteType {
		reuters_places,
		reuters_topics,
		custom1,
		custom2
	}
}
