package engine.datainput.reuters;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlReutersUnmarshaller {

	public XmlReutersUnmarshaller(File xmlFile) throws JAXBException {
		this.xmlFile = xmlFile;
		container = new XmlReutersContainer();
		JAXBContext jaxbContext = JAXBContext.newInstance(XmlReutersContainer.class);
		jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	}

	public XmlReutersContainer fetchData() throws JAXBException {
		container = (XmlReutersContainer) jaxbUnmarshaller.unmarshal(xmlFile);
		fetched = true;
		return container;
	}

	public XmlReutersContainer getData() {
		if(fetched)
			return container;
		else
			return null;
	}

	private File xmlFile;
	private XmlReutersContainer container;
	private Unmarshaller jaxbUnmarshaller;
	private boolean fetched = false;
}
