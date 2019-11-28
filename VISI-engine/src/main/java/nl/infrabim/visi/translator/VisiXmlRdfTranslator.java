package nl.infrabim.visi.translator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Service
public class VisiXmlRdfTranslator {
	public final static String VISI_RDF_FILE = "C:\\Users\\peter\\Documents\\TBCworkspace\\VISI\\visi16.ttl";
	public final static String VISI_RDF_URI = "http://infrabim.nl/visi/visi16";
	public final static String VISI_NS = "http://www.visi.nl/schemas/20160331";

	private String currentFrameworkFilePath;
	private Map<String, ElementType> elementTypeMap;
	private DocumentInfo documentInfo;

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
			TransformerFactoryConfigurationError, TransformerException {
		if (args.length == 1) {
			new VisiXmlRdfTranslator(args[0]);
		} else
			System.out.println("Usage: VisiXmlRdfTranslator {VISI framework XML file path}");
	}

	public VisiXmlRdfTranslator() throws ParserConfigurationException, SAXException, IOException,
			TransformerFactoryConfigurationError, TransformerException {
		initialize();
	}

	public VisiXmlRdfTranslator(String filePath) throws ParserConfigurationException, SAXException, IOException,
			TransformerFactoryConfigurationError, TransformerException {
		System.out.println("Translating " + filePath + " ...");

		initialize();
		translate(filePath);
		save("output.xml");

		System.out.println("Translating " + filePath + " finished");
	}

	private void initialize() throws IOException {
		ElementMetaType.initialize();

		for (String id : ElementMetaType.getNames()) {
			ElementMetaType.get(id).print();
		}

		elementTypeMap = new HashMap<>();
	}

	public void translate(String visiXmlFilePath) throws ParserConfigurationException, SAXException, IOException {
		if (visiXmlFilePath.equals(currentFrameworkFilePath))
			return;
		currentFrameworkFilePath = visiXmlFilePath;
		elementTypeMap.clear();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document framework = dBuilder.parse(visiXmlFilePath);
		Element documentElement = framework.getDocumentElement();
		documentInfo = new DocumentInfo(documentElement);
		for (String elementTypeName : ElementMetaType.getNames()) {
			System.out.println("\n------------------\n" + elementTypeName + "\n------------------");
			ElementMetaType elementMetaType = ElementMetaType.get(elementTypeName);
			List<Element> elementTypes = getElements(documentElement, elementTypeName);
			for (Element elementInstance : elementTypes) {
				String id = elementInstance.getAttribute("id");
				ElementType elementType = new ElementType(elementTypeName, id);
				elementTypeMap.put(id, elementType);
				List<String> propertyNames = elementMetaType.getPropertyNames();
				for (String propertyName : propertyNames) {
					PropertyMetaType propertyMetaType = PropertyMetaType.get(propertyName);
					List<Element> propertyTypes = getElements(elementInstance, propertyName);
					for (Element propertyInstance : propertyTypes) {
						if (propertyMetaType.isDatatypeProperty()) {
							elementType.putPropertyValue(propertyName, propertyInstance.getTextContent());
						} else {
							List<Element> refElements = getElements(propertyInstance, null);
							for (Element element : refElements) {
								elementType.putPropertyValue(propertyName, element.getAttribute("idref"));
							}
						}
					}
				}
				elementType.print();
			}
		}
	}

	public void save(String outputFilePath) throws ParserConfigurationException, TransformerFactoryConfigurationError,
			FileNotFoundException, TransformerException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document framework = dBuilder.newDocument();
		Element documentElement = framework.createElement(documentInfo.getTagName());
		documentInfo.setElementAttributes(documentElement);
		framework.appendChild(documentElement);

		ArrayList<ElementType> elementTypes = new ArrayList<ElementType>(elementTypeMap.values());
		elementTypes.sort(new Comparator<ElementType>() {
			@Override
			public int compare(ElementType o1, ElementType o2) {
				int result = o1.getType().compareTo(o2.getType());
				if (result == 0) {
					result = o1.getId().compareTo(o2.getId());
				}
				return result;
			}
		});
		for (ElementType elementType : elementTypes) {
			Element elementTypeElement = elementType.createElement(framework);
			documentElement.appendChild(elementTypeElement);
		}

		Transformer tr = TransformerFactory.newInstance().newTransformer();
		tr.setOutputProperty(OutputKeys.INDENT, "yes");
		tr.setOutputProperty(OutputKeys.METHOD, "xml");
		tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tr.transform(new DOMSource(framework), new StreamResult(new FileOutputStream(outputFilePath)));
	}

	private List<Element> getElements(Element rootElement, String tagName) {
		List<Element> elementList = new ArrayList<>();
		NodeList nodeList = null;
		if (tagName != null) {
			nodeList = rootElement.getElementsByTagName(tagName);
		} else {
			nodeList = rootElement.getChildNodes();
		}
		for (int index = 0; index < nodeList.getLength(); index++) {
			Node item = nodeList.item(index);
			if (item instanceof Element) {
				elementList.add((Element) item);
			}
		}
		return elementList;
	}

	public List<ElementType> getElementTypes() {
		List<ElementType> elementTypes = new ArrayList<>(elementTypeMap.values());
		Collections.sort(elementTypes, new Comparator<ElementType>() {
			@Override
			public int compare(ElementType o1, ElementType o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return elementTypes;
	}

	public ElementType getElementType(String elementId) {
		return elementTypeMap.get(elementId);
	}

	public void createFramework(String file) throws ParserConfigurationException, FileNotFoundException,
			TransformerFactoryConfigurationError, TransformerException {
		elementTypeMap.clear();
		documentInfo = new DocumentInfo();
		save(file);
	}

	public void createElementType(String type, String id) {
		ElementType elementType = new ElementType(type, id);
		elementTypeMap.put(id, elementType);
		PropertyTypeRestriction description = elementType.getMetaType().getPropertyTypeRestriction("description");
		if (description != null) {
			elementType.putPropertyValue(description.getOnProperty().getId(), id);
		}
	}

}
