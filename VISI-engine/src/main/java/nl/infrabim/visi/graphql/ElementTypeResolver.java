package nl.infrabim.visi.graphql;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.infrabim.visi.translator.VisiXmlRdfTranslator;

@Component
public class ElementTypeResolver {
	@Autowired
	protected VisiXmlRdfTranslator visiXmlRdfTranslator;

	protected ElementMetaType getMetaType(ElementType elementType) {
		return new ElementMetaType(visiXmlRdfTranslator.getElementType(elementType.getId()).getMetaType().getId());
	}

	protected String getPropertyValue(ElementType elementType, String propertyName) {
		Object propertyValue = visiXmlRdfTranslator.getElementType(elementType.getId()).getPropertyValue(propertyName);
		return propertyValue != null ? propertyValue.toString() : null;
	}

	protected String getDescription(ElementType elementType) {
		return getPropertyValue(elementType, "description");
	}

	protected OffsetDateTime getStartDate(ElementType elementType) {
		String startDate = getPropertyValue(elementType, "startDate");
		if (startDate != null) {
			return OffsetDateTime.parse(startDate);
		}
		return null;
	}

	protected OffsetDateTime getEndDate(ElementType elementType) {
		String endDate = getPropertyValue(elementType, "endDate");
		if (endDate != null) {
			return OffsetDateTime.parse(endDate);
		}
		return null;
	}

	protected String getState(ElementType elementType) {
		return getPropertyValue(elementType, "state");
	}

	protected OffsetDateTime getDateLaMu(ElementType elementType) {
		String dateLaMu = getPropertyValue(elementType, "dateLaMu");
		if (dateLaMu != null) {
			return OffsetDateTime.parse(dateLaMu);
		}
		return null;
	}

	protected String getUserLaMu(ElementType elementType) {
		return getPropertyValue(elementType, "userLaMu");
	}

	protected String getLanguage(ElementType elementType) {
		return getPropertyValue(elementType, "language");
	}

	protected String getCategory(ElementType elementType) {
		return getPropertyValue(elementType, "category");
	}

	protected String getHelpInfo(ElementType elementType) {
		return getPropertyValue(elementType, "helpInfo");
	}

	protected String getCode(ElementType elementType) {
		return getPropertyValue(elementType, "code");
	}

	protected List<AppendixType> getAppendixTypes(ElementType elementType) {
		Object appendixTypes = visiXmlRdfTranslator.getElementType(elementType.getId())
				.getPropertyValue("appendixTypes");
		if (appendixTypes != null) {
			List<AppendixType> appendixTypesList = new ArrayList<>();
			@SuppressWarnings("unchecked")
			List<String> apLst = (List<String>) appendixTypes;
			for (String ap : apLst) {
				appendixTypesList.add(new AppendixType(ap));
			}
			return appendixTypesList;
		}
		return null;
	}

	protected List<ComplexElementType> getComplexElements(ElementType elementType) {
		Object complexElements = visiXmlRdfTranslator.getElementType(elementType.getId())
				.getPropertyValue("complexElements");
		if (complexElements != null) {
			List<ComplexElementType> complexElementsList = new ArrayList<>();
			@SuppressWarnings("unchecked")
			List<String> ceLst = (List<String>) complexElements;
			for (String ce : ceLst) {
				complexElementsList.add(new ComplexElementType(ce));
			}
			return complexElementsList;
		}
		return null;
	}

	protected List<SimpleElementType> getSimpleElements(ElementType elementType) {
		Object simpleElements = visiXmlRdfTranslator.getElementType(elementType.getId())
				.getPropertyValue("simpleElements");
		if (simpleElements != null) {
			List<SimpleElementType> simpleElementsList = new ArrayList<>();
			@SuppressWarnings("unchecked")
			List<String> seLst = (List<String>) simpleElements;
			for (String se : seLst) {
				simpleElementsList.add(new SimpleElementType(se));
			}
			return simpleElementsList;
		}
		return null;
	}

	protected List<ElementCondition> getElementConditions(ElementType elementType) {
		List<ElementCondition> elementConditions = new ArrayList<>();
		List<String> elementConditionIds = new ArrayList<>();
		visiXmlRdfTranslator.getElementTypes().forEach(e -> {
			if (e.getType().equals("ElementCondition")) {
				elementConditionIds.add(e.getId());
			}
		});
		String type = visiXmlRdfTranslator.getElementType(elementType.getId()).getType();
		for (String ecId : elementConditionIds) {
			switch (type) {
			case "ComplexElementType":
				Object complexElementsValue = visiXmlRdfTranslator.getElementType(ecId)
						.getPropertyValue("complexElements");
				if (complexElementsValue != null) {
					@SuppressWarnings("unchecked")
					List<String> ceList = (List<String>) complexElementsValue;
					if (ceList.contains(elementType.getId())) {
						elementConditions.add(new ElementCondition(ecId));
					}
				}
				break;
			case "SimpleElementType":
				String simpleElementValue = (String) visiXmlRdfTranslator.getElementType(ecId)
						.getPropertyValue("simpleElement");
				if (simpleElementValue != null) {
					if (simpleElementValue.equals(elementType.getId())) {
						elementConditions.add(new ElementCondition(ecId));
					}
				}
				break;
			case "MessageInTransactionType":
				String messageInTransactionValue = (String) visiXmlRdfTranslator.getElementType(ecId)
						.getPropertyValue("messageInTransaction");
				if (messageInTransactionValue != null) {
					if (messageInTransactionValue.equals(elementType.getId())) {
						elementConditions.add(new ElementCondition(ecId));
					}
				}
				break;
			default:
				break;
			}
		}
		return elementConditions;
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> getInverses(ElementType elementType, Class<T> t, String invPropertyId) {
		String simpleClassName = t.getSimpleName();
		List<T> inverses = new ArrayList<>();
		visiXmlRdfTranslator.getElementTypes().forEach(s -> {
			if (s.getType().equals(simpleClassName)) {
				Object invPropertyValue = s.getPropertyValue(invPropertyId);
				if (invPropertyValue != null) {
					if (invPropertyValue instanceof String) {
						if (((String) invPropertyValue).equals(elementType.getId())) {
							instantiate(simpleClassName, inverses, s);							
						}
					} else {
						List<String> valueList = (List<String>) invPropertyValue;
						for (String value : valueList) {
							if (value.equals(elementType.getId())) {
								instantiate(simpleClassName, inverses, s);
							}
						}
					}
				}
			}
		});
		return inverses.size() > 0 ? inverses : null;
	}

	@SuppressWarnings("unchecked")
	private <T> void instantiate(String simpleClassName, List<T> inverses, nl.infrabim.visi.translator.ElementType s) {
		switch (simpleClassName) {
		case "ComplexElementType":
			inverses.add((T) new ComplexElementType(s.getId()));
			break;
		case "ElementCondition":
			inverses.add((T) new ElementCondition(s.getId()));
			break;
		case "MessageInTransactionType":
			inverses.add((T) new MessageInTransactionType(s.getId()));
			break;
		case "MessageInTransactionTypeCondition":
			inverses.add((T) new MessageInTransactionTypeCondition(s.getId()));
			break;
		case "MessageType":
			inverses.add((T) new MessageType(s.getId()));
			break;
		case "OrganisationType":
			inverses.add((T) new OrganisationType(s.getId()));
			break;
		case "PersonType":
			inverses.add((T) new ProjectType(s.getId()));
			break;
		case "ProjectType":
			inverses.add((T) new ProjectType(s.getId()));
			break;
		case "TransactionType":
			inverses.add((T) new TransactionType(s.getId()));
			break;
		default:
			System.err.println("Type " + simpleClassName + " not implemented");
			break;
		}
	}

}
