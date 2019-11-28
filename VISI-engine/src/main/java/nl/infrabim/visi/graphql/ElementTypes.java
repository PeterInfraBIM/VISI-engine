package nl.infrabim.visi.graphql;

public enum ElementTypes {
	AppendixType, //
	ComplexElementType, //
	ElementCondition, //
	GroupType, //
	MessageInTransactionType, //
	MessageInTransactionTypeCondition, //
	MessageType, //
	OrganisationType, //
	PersonType, //
	ProjectType, //
	RoleType, //
	SimpleElementType, //
	TransactionPhaseType, //
	TransactionType, //
	UserDefinedType; //

	public ElementType createInstance(String id) {
		switch (this) {
		case AppendixType:
			return new AppendixType(id);
		case ComplexElementType:
			return new ComplexElementType(id);
		case ElementCondition:
			return new ElementCondition(id);
		case GroupType:
			return new GroupType(id);
		case MessageInTransactionType:
			return new MessageInTransactionType(id);
		case MessageInTransactionTypeCondition:
			return new MessageInTransactionTypeCondition(id);
		case MessageType:
			return new MessageType(id);
		case OrganisationType:
			return new OrganisationType(id);
		case PersonType:
			return new PersonType(id);
		case ProjectType:
			return new ProjectType(id);
		case RoleType:
			return new RoleType(id);
		case SimpleElementType:
			return new SimpleElementType(id);
		case TransactionPhaseType:
			return new TransactionPhaseType(id);
		case TransactionType:
			return new TransactionType(id);
		case UserDefinedType:
			return new UserDefinedType(id);
		default:
			break;
		}
		return null;
	}

}
