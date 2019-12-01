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
		List<nl.infrabim.visi.translator.ElementType> elements = visiXmlRdfTranslator.getElementTypes();
		if (elements != null) {
			elements.forEach((e) -> {
				if (metaType.isPresent()) {
					if (e.getMetaType().getId().equals(metaType.get())) {
						elementTypes.add(ElementTypes.valueOf(metaType.get()).createInstance(e.getId()));
					}
				} else {
					elementTypes.add(ElementTypes.valueOf(e.getMetaType().getId()).createInstance(e.getId()));
				}
			});
		}
		return elementTypes;
	}

	public AppendixType getAppendixType(Framework framework, String id) {
		return new AppendixType(id);
	}

	@SuppressWarnings("unchecked")
	public List<AppendixType> getAppendixTypes(Framework framework) {
		return (List<AppendixType>) getElementTypes(framework, Optional.of("AppendixType"));
	}

	public ComplexElementType getComplexElementType(Framework framework, String id) {
		return new ComplexElementType(id);
	}

	@SuppressWarnings("unchecked")
	public List<ComplexElementType> getComplexElementTypes(Framework framework) {
		return (List<ComplexElementType>) getElementTypes(framework, Optional.of("ComplexElementType"));
	}

	public ElementCondition getElementCondition(Framework framework, String id) {
		return new ElementCondition(id);
	}

	@SuppressWarnings("unchecked")
	public List<ElementCondition> getElementConditions(Framework framework, Optional<String> mitt, Optional<String> ce1,
			Optional<String> ce2, Optional<String> se) {
		List<ElementCondition> filteredElementConditions = new ArrayList<>();
		List<ElementCondition> elementConditions = (List<ElementCondition>) getElementTypes(framework,
				Optional.of("ElementCondition"));
		for (ElementCondition ec : elementConditions) {
			ElementConditionResolver elementConditionResolver = new ElementConditionResolver(visiXmlRdfTranslator);
			if (mitt.isPresent()) {
				MessageInTransactionType messageInTransaction = elementConditionResolver.getMessageInTransaction(ec);
				if (messageInTransaction == null) {
					if (!mitt.get().equals("null"))
						continue;

				} else {
					if (!messageInTransaction.getId().equals(mitt.get()))
						continue;
				}
			}
			if (ce1.isPresent()) {
				ComplexElementType parentComplexElement = elementConditionResolver.getParentComplexElement(ec);
				if (parentComplexElement == null) {
					if (!ce1.get().equals("null"))
						continue;

				} else {
					if (!parentComplexElement.getId().equals(ce1.get()))
						continue;
				}
			}
			if (ce2.isPresent()) {
				ComplexElementType childComplexElement = elementConditionResolver.getChildComplexElement(ec);
				if (childComplexElement == null) {
					if (!ce2.get().equals("null"))
						continue;

				} else {
					if (!childComplexElement.getId().equals(ce2.get()))
						continue;
				}
			}
			if (se.isPresent()) {
				SimpleElementType simpleElement = elementConditionResolver.getSimpleElement(ec);
				if (simpleElement == null) {
					if (!se.get().equals("null"))
						continue;

				} else {
					if (!simpleElement.getId().equals(se.get()))
						continue;
				}
			}
			filteredElementConditions.add(ec);
		}
		return filteredElementConditions;
	}

	public List<ElementCondition> getAllElementConditions(Framework framework, String mitt, String ce1,
			Optional<String> ce2, String se) {
		List<ElementCondition> elementConditions = new ArrayList<>();
		elementConditions
				.addAll(getElementConditions(framework, Optional.of(mitt), Optional.of(ce1), ce2, Optional.of(se)));
		elementConditions
				.addAll(getElementConditions(framework, Optional.of(mitt), Optional.of(ce1), ce2, Optional.of("null")));
		elementConditions.addAll(getElementConditions(framework, Optional.of(mitt), Optional.of(ce1),
				Optional.of("null"), Optional.of(se)));
		elementConditions.addAll(getElementConditions(framework, Optional.of(mitt), Optional.of(ce1),
				Optional.of("null"), Optional.of("null")));
		if (ce2.isPresent()) {
			elementConditions.addAll(
					getElementConditions(framework, Optional.of(mitt), ce2, Optional.of("null"), Optional.of(se)));
			elementConditions.addAll(
					getElementConditions(framework, Optional.of(mitt), ce2, Optional.of("null"), Optional.of("null")));
		}
		elementConditions.addAll(getElementConditions(framework, Optional.of(mitt), Optional.of("null"),
				Optional.of("null"), Optional.of(se)));
		elementConditions.addAll(getElementConditions(framework, Optional.of(mitt), Optional.of("null"),
				Optional.of("null"), Optional.of("null")));
		elementConditions
				.addAll(getElementConditions(framework, Optional.of("null"), Optional.of(ce1), ce2, Optional.of(se)));
		elementConditions
				.addAll(getElementConditions(framework, Optional.of("null"), Optional.of(ce1), ce2, Optional.of("null")));
		elementConditions.addAll(getElementConditions(framework, Optional.of("null"), Optional.of(ce1),
				Optional.of("null"), Optional.of(se)));
		elementConditions.addAll(getElementConditions(framework, Optional.of("null"), Optional.of(ce1),
				Optional.of("null"), Optional.of("null")));
		if (ce2.isPresent()) {
			elementConditions.addAll(
					getElementConditions(framework, Optional.of("null"), ce2, Optional.of("null"), Optional.of(se)));
			elementConditions.addAll(
					getElementConditions(framework, Optional.of("null"), ce2, Optional.of("null"), Optional.of("null")));
		}
		elementConditions.addAll(getElementConditions(framework, Optional.of("null"), Optional.of("null"),
				Optional.of("null"), Optional.of(se)));
		elementConditions.addAll(getElementConditions(framework, Optional.of("null"), Optional.of("null"),
				Optional.of("null"), Optional.of("null")));

		return elementConditions;
	}

	public GroupType getGroupType(Framework framework, String id) {
		return new GroupType(id);
	}

	@SuppressWarnings("unchecked")
	public List<GroupType> getGroupTypes(Framework framework) {
		return (List<GroupType>) getElementTypes(framework, Optional.of("GroupType"));
	}

	public MessageInTransactionType getMessageInTransactionType(Framework framework, String id) {
		return new MessageInTransactionType(id);
	}

	@SuppressWarnings("unchecked")
	public List<MessageInTransactionType> getMessageInTransactionTypes(Framework framework) {
		return (List<MessageInTransactionType>) getElementTypes(framework, Optional.of("MessageInTransactionType"));
	}

	public MessageInTransactionTypeCondition getMessageInTransactionTypeCondition(Framework framework, String id) {
		return new MessageInTransactionTypeCondition(id);
	}

	@SuppressWarnings("unchecked")
	public List<MessageInTransactionTypeCondition> getMessageInTransactionTypeConditions(Framework framework) {
		return (List<MessageInTransactionTypeCondition>) getElementTypes(framework,
				Optional.of("MessageInTransactionTypeCondition"));
	}

	public MessageType getMessageType(Framework framework, String id) {
		return new MessageType(id);
	}

	@SuppressWarnings("unchecked")
	public List<MessageType> getMessageTypes(Framework framework) {
		return (List<MessageType>) getElementTypes(framework, Optional.of("MessageType"));
	}

	public OrganisationType getOrganisationType(Framework framework, String id) {
		return new OrganisationType(id);
	}

	@SuppressWarnings("unchecked")
	public List<OrganisationType> getOrganisationTypes(Framework framework) {
		return (List<OrganisationType>) getElementTypes(framework, Optional.of("OrganisationType"));
	}

	public PersonType getPersonType(Framework framework, String id) {
		return new PersonType(id);
	}

	@SuppressWarnings("unchecked")
	public List<PersonType> getPersonTypes(Framework framework) {
		return (List<PersonType>) getElementTypes(framework, Optional.of("PersonType"));
	}

	public ProjectType getProjectType(Framework framework, String id) {
		return new ProjectType(id);
	}

	@SuppressWarnings("unchecked")
	public List<ProjectType> getProjectTypes(Framework framework) {
		return (List<ProjectType>) getElementTypes(framework, Optional.of("ProjectType"));
	}

	public RoleType getRoleType(Framework framework, String id) {
		return new RoleType(id);
	}

	@SuppressWarnings("unchecked")
	public List<RoleType> getRoleTypes(Framework framework) {
		return (List<RoleType>) getElementTypes(framework, Optional.of("RoleType"));
	}

	public SimpleElementType getSimpleElementType(Framework framework, String id) {
		return new SimpleElementType(id);
	}

	@SuppressWarnings("unchecked")
	public List<SimpleElementType> getSimpleElementTypes(Framework framework) {
		return (List<SimpleElementType>) getElementTypes(framework, Optional.of("SimpleElementType"));
	}

	public TransactionPhaseType getTransactionPhaseType(Framework framework, String id) {
		return new TransactionPhaseType(id);
	}

	@SuppressWarnings("unchecked")
	public List<TransactionPhaseType> getTransactionPhaseTypes(Framework framework) {
		return (List<TransactionPhaseType>) getElementTypes(framework, Optional.of("TransactionPhaseType"));
	}

	public TransactionType getTransactionType(Framework framework, String id) {
		return new TransactionType(id);
	}

	@SuppressWarnings("unchecked")
	public List<TransactionType> getTransactionTypes(Framework framework) {
		return (List<TransactionType>) getElementTypes(framework, Optional.of("TransactionType"));
	}

	public UserDefinedType getUserDefinedType(Framework framework, String id) {
		return new UserDefinedType(id);
	}

	@SuppressWarnings("unchecked")
	public List<UserDefinedType> getUserDefinedTypes(Framework framework) {
		return (List<UserDefinedType>) getElementTypes(framework, Optional.of("UserDefinedType"));
	}
}
