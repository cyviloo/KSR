package engine.datainput.reuters;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DOCUMENT")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlReutersContainer {

	public XmlReutersContainer() {
		 elements = new ArrayList<>();
	}

	public ArrayList<XmlReutersElement> getElements() {
		return elements;
	}

	@XmlElement(name = "REUTERS")
	private ArrayList<XmlReutersElement> elements;
}
