package nl.infrabim.visi.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import nl.infrabim.visi.translator.VisiXmlRdfTranslator;

@Component
public class ElementTypeResolver implements GraphQLResolver<ElementType> {
	@Autowired
	private VisiXmlRdfTranslator visiXmlRdfTranslator;

	public ElementMetaType getMetaType(ElementType elementType) {
		return new ElementMetaType(visiXmlRdfTranslator.getElementType(elementType.getId()).getMetaType().getId());
	}

}
