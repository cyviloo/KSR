package engine.datainput.reuters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import engine.datainput.DataInputElement;

@XmlRootElement(name = "REUTERS")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlReutersElement implements DataInputElement {

	public XmlReutersElement() {
	}

	@Override
	public String getEtiquette1() {
		return getCountry();
	}

	@Override
	public String getEtiquette2() {
		return getTopic();
	}

	@Override
	public String getTextValue() {
		return getText();
	}

	protected int getOldID() {
		return oldID;
	}

	protected int getNewID() {
		return newID;
	}

	protected String getCountry() {
		return places;
	}

	protected String getTopic() {
		return topic;
	}

	protected String getText() {
		return text;
	}

	@XmlAttribute(name = "OLDID")
	private int oldID;

	@XmlAttribute(name = "NEWID")
	private int newID;

	@XmlElement(name = "PLACES")
	private String places;

	@XmlElement(name = "TOPICS")
	private String topic;

	@XmlElement(name = "BODY")
	private String text;
}
