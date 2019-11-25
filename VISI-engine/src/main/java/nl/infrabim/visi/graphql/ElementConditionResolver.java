package nl.infrabim.visi.graphql;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class ElementConditionResolver extends ElementTypeResolver implements GraphQLResolver<ElementCondition> {

	public ElementMetaType getMetaType(ElementCondition elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(ElementCondition elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public String getDescription(ElementCondition elementType) {
		return super.getDescription(elementType);
	}

	public String getCondition(ElementCondition elementType) {
		return getPropertyValue(elementType, "condition");
	}

	public String getHelpInfo(ElementCondition elementType) {
		return super.getHelpInfo(elementType);
	}

	public List<ComplexElementType> getComplexElements(ElementCondition elementType) {
		return super.getComplexElements(elementType);
	}

	public SimpleElementType getSimpleElement(ElementCondition elementType) {
		List<SimpleElementType> simpleElements = super.getSimpleElements(elementType);
		return simpleElements != null && simpleElements.size() == 1 ? simpleElements.get(0) : null;
	}

	public MessageInTransactionType getMessageInTransaction(ElementCondition elementType) {
		Object messageInTransactionTypeValue = visiXmlRdfTranslator.getElementType(elementType.getId())
				.getPropertyValue("messageInTransaction");
		return messageInTransactionTypeValue != null
				? new MessageInTransactionType((String) messageInTransactionTypeValue)
				: null;
	}

}
