package nl.infrabim.visi.graphql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import nl.infrabim.visi.translator.VisiXmlRdfTranslator;

@Component
public class FrameworkResolver implements GraphQLResolver<Framework> {
	@Autowired
	private VisiXmlRdfTranslator visiXmlRdfTranslator;

	public List<ElementType> getElementTypes(Framework framework) {
		List<ElementType> elementTypes = new ArrayList<>();
		visiXmlRdfTranslator.getElementTypes().forEach((e) -> {
			elementTypes.add(new ElementType(e.getId()));
		});
		return elementTypes;
	}
}
