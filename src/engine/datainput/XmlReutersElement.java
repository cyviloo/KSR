package engine.datainput;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "REUTERS")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlReutersElement {

	public XmlReutersElement() {
	}

	public int getOldID() {
		return oldID;
	}

	public int getNewID() {
		return newID;
	}

	public String getCountry() {
		return places;
	}

	public String getText() {
		return text;
	}

	@XmlAttribute(name = "OLDID")
	private int oldID;

	@XmlAttribute(name = "NEWID")
	private int newID;

	@XmlElement(name = "PLACES")
	private String places;

	@XmlElement(name = "BODY")
	private String text;
}
