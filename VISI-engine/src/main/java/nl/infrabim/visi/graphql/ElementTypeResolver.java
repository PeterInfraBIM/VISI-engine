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

}
