package nl.infrabim.visi.translator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ElementType {

	private String type;
	private String id;
	private MapService<PropertyType> propertySrvc;

	public ElementType() {
	}

	public ElementType(String type, String id) {
		this.type = type;
		this.id = id;
		this.propertySrvc = new MapService<>();
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public Object getPropertyValue(String propertyId) {
		PropertyType propertyType = propertySrvc.get(propertyId);
		return propertyType != null ? propertyType.getValue() : null;
	}

	public void putPropertyValue(String propertyId, Object propertyValue) {
		PropertyType propertyType = propertySrvc.get(propertyId);
		if (propertyType == null) {
			propertyType = new PropertyType(propertyId);
		}

		PropertyMetaType propertyMetaType = PropertyMetaType.get(propertyId);
		if (propertyMetaType.isDatatypeProperty()) {
			propertyType.setValue(propertyValue);
		} else {
			ElementMetaType metaType = getMetaType();
			PropertyTypeRestriction restriction = metaType.getPropertyTypeRestriction(propertyId);
			if (restriction.isMultiple()) {
				Object value = propertyType.getValue();
				if (value == null) {
					propertyType.setValue(new ArrayList<String>());
					value = propertyType.getValue();
				}
				if (value instanceof List) {
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) value;
					if (!list.contains((String) propertyValue)) {
						list.add((String) propertyValue);
					}
				}
			} else {
				propertyType.setValue(propertyValue);
			}
		}
		propertySrvc.put(propertyId, propertyType);
	}

	public ElementMetaType getMetaType() {
		return type != null ? ElementMetaType.get(type) : null;
	}

	public List<String> getPropertyTypes() {
		ElementMetaType metaType = getMetaType();
		return metaType != null ? metaType.getPropertyNames() : null;
	}

	public void print() {
		System.out.println(getType() + ": " + getId());
		ElementMetaType metaType = getMetaType();
		for (String propertyId : metaType.getPropertyNames()) {
			PropertyMetaType propertyMetaType = PropertyMetaType.get(propertyId);
			Object propertyValue = getPropertyValue(propertyId);
			if (propertyValue != null) {
				System.out.print("\t" + propertyId + ": ");
				if (propertyMetaType.isDatatypeProperty()) {
					switch (propertyMetaType.getRange()) {
					case "string":
						System.out.println("\"" + (String) propertyValue + "\"");
						break;
					case "integer":
						System.out.println(Integer.parseInt((String) propertyValue));
						break;
					case "boolean":
						System.out.println(Boolean.parseBoolean((String) propertyValue));
						break;
					default:
						System.err.println(propertyMetaType.getRange());
						break;
					}
				} else {
					if (metaType.getPropertyTypeRestriction(propertyId).isMultiple()) {
						@SuppressWarnings("unchecked")
						List<String> list = (List<String>) propertyValue;
						boolean firstItem = true;
						for (String item : list) {
							if (firstItem) {
								System.out.print(item);
								firstItem = false;
							} else {
								System.out.print(", " + item);
							}
						}
						System.out.println();
					} else {
						System.out.println((String) propertyValue);
					}
				}
			}
		}
		System.out.println();
	}

	public Element createElement(Document framework) {
		Element element = framework.createElement(getType());
		element.setAttribute("id", getId());
		for (String propertyName : propertySrvc.getNames()) {
			Object propertyValue = getPropertyValue(propertyName);
			if (propertyValue != null) {
				PropertyMetaType propertyMetaType = PropertyMetaType.get(propertyName);
				if (propertyMetaType.isDatatypeProperty()) {
					Element propertyElement = framework.createElement(propertyName);
					element.appendChild(propertyElement);
					propertyElement.setTextContent((String) propertyValue);
				} else {
					Element propertyElement = framework.createElement(propertyName);
					element.appendChild(propertyElement);
					PropertyTypeRestriction propertyTypeRestriction = getMetaType()
							.getPropertyTypeRestriction(propertyName);
					if (propertyTypeRestriction.isMultiple()) {
						@SuppressWarnings("unchecked")
						List<String> valueList = (List<String>) propertyValue;
						Collections.sort(valueList);
						for (String value : valueList) {
							Element typeRefElement = framework.createElement(propertyMetaType.getRange() + "Ref");
							propertyElement.appendChild(typeRefElement);
							typeRefElement.setAttribute("idref", value);
						}
					} else {
						Element typeRefElement = framework.createElement(propertyMetaType.getRange() + "Ref");
						propertyElement.appendChild(typeRefElement);
						typeRefElement.setAttribute("idref", (String) propertyValue);
					}
				}
			}
		}
		return element;
	}

}
