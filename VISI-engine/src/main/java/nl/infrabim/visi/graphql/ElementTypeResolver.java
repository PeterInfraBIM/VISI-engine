package nl.infrabim.visi.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.infrabim.visi.translator.VisiXmlRdfTranslator;

@Component
public class ElementTypeResolver {
	@Autowired
	protected VisiXmlRdfTranslator visiXmlRdfTranslator;

	public ElementMetaType getMetaType(ElementType elementType) {
		return new ElementMetaType(visiXmlRdfTranslator.getElementType(elementType.getId()).getMetaType().getId());
	}

	public String getPropertyValue(ElementType elementType, String propertyName) {
		Object propertyValue = visiXmlRdfTranslator.getElementType(elementType.getId()).getPropertyValue(propertyName);
		return propertyValue != null ? propertyValue.toString() : null;
	}
}
