package engine.datainput.reuters;

/*
 * Classes implementing this interface must be able to provide their:
 *  - Etiquette
 *  - TextValue
 */
public interface DataInputElement {

	public String getEtiquette1();

	public String getEtiquette2();

	public String getTextValue();

}
