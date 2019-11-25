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
					case "AppendixType":
						elementTypes.add(new AppendixType(e.getId()));
						break;
					case "ComplexElementType":
						elementTypes.add(new ComplexElementType(e.getId()));
						break;
					case "ElementCondition":
						elementTypes.add(new ElementCondition(e.getId()));
						break;
					case "GroupType":
						elementTypes.add(new GroupType(e.getId()));
						break;
					case "MessageInTransactionType":
						elementTypes.add(new MessageInTransactionType(e.getId()));
						break;
					case "MessageInTransactionTypeCondition":
						elementTypes.add(new MessageInTransactionTypeCondition(e.getId()));
						break;
					case "MessageType":
						elementTypes.add(new MessageType(e.getId()));
						break;
					case "OrganisationType":
						elementTypes.add(new OrganisationType(e.getId()));
						break;
					case "PersonType":
						elementTypes.add(new PersonType(e.getId()));
						break;
					case "ProjectType":
						elementTypes.add(new ProjectType(e.getId()));
						break;
					case "RoleType":
						elementTypes.add(new RoleType(e.getId()));
						break;
					case "SimpleElementType":
						elementTypes.add(new SimpleElementType(e.getId()));
						break;
					case "TransactionPhaseType":
						elementTypes.add(new TransactionPhaseType(e.getId()));
						break;
					case "TransactionType":
						elementTypes.add(new TransactionType(e.getId()));
						break;
					case "UserDefinedType":
						elementTypes.add(new UserDefinedType(e.getId()));
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
	public List<ElementCondition> getElementConditions(Framework framework) {
		return (List<ElementCondition>) getElementTypes(framework, Optional.of("ElementCondition"));
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
