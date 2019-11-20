package nl.infrabim.visi.graphql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import nl.infrabim.visi.translator.VisiXmlRdfTranslator;

@Component
public class FrameworkResolver implements GraphQLResolver<Framework> {
	@Autowired
	private VisiXmlRdfTranslator visiXmlRdfTranslator;

	public List<? extends ElementType> getElementTypes(Framework framework, Optional<String> metaType) {
		List<ElementType> elementTypes = new ArrayList<>();
		visiXmlRdfTranslator.getElementTypes().forEach((e) -> {
			if (metaType.isPresent()) {
				if (e.getMetaType().getId().equals(metaType.get())) {
					switch (metaType.get()) {
					case "RoleType":
						elementTypes.add(new RoleType(e.getId()));
						break;
					case "TransactionType":
						elementTypes.add(new TransactionType(e.getId()));
						break;
					default:
						elementTypes.add(new ElementType(e.getId()));
						break;
					}
				}
			} else {
				e.getMetaType().getId();
				elementTypes.add(new ElementType(e.getId()));
			}
		});
		return elementTypes;
	}

	@SuppressWarnings("unchecked")
	public List<RoleType> getRoleTypes(Framework framework) {
		return (List<RoleType>) getElementTypes(framework, Optional.of("RoleType"));
	}
	
	@SuppressWarnings("unchecked")
	public List<TransactionType> getTransactionTypes(Framework framework) {
		return (List<TransactionType>) getElementTypes(framework, Optional.of("TransactionType"));
	}
}
