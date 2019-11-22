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
					case "ComplexElementType":
						elementTypes.add(new ComplexElementType(e.getId()));
						break;
					case "MessageInTransactioType":
						elementTypes.add(new MessageInTransactionType(e.getId()));
						break;
					case "MessageType":
						elementTypes.add(new MessageType(e.getId()));
						break;
					case "RoleType":
						elementTypes.add(new RoleType(e.getId()));
						break;
					case "SimpleElementType":
						elementTypes.add(new SimpleElementType(e.getId()));
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
	public List<ComplexElementType> getComplexElementTypes(Framework framework) {
		return (List<ComplexElementType>) getElementTypes(framework, Optional.of("ComplexElementType"));
	}

	@SuppressWarnings("unchecked")
	public List<MessageType> getMessageTypes(Framework framework) {
		return (List<MessageType>) getElementTypes(framework, Optional.of("MessageType"));
	}

	@SuppressWarnings("unchecked")
	public List<RoleType> getRoleTypes(Framework framework) {
		return (List<RoleType>) getElementTypes(framework, Optional.of("RoleType"));
	}

	@SuppressWarnings("unchecked")
	public List<SimpleElementType> getSimpleElementTypes(Framework framework) {
		return (List<SimpleElementType>) getElementTypes(framework, Optional.of("SimpleElementType"));
	}
	
	@SuppressWarnings("unchecked")
	public List<TransactionType> getTransactionTypes(Framework framework) {
		return (List<TransactionType>) getElementTypes(framework, Optional.of("TransactionType"));
	}
}
