package nl.infrabim.visi.graphql;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import nl.infrabim.visi.translator.ElementType;
import nl.infrabim.visi.translator.VisiXmlRdfTranslator;

@Component
public class ElementConditionResolver extends ElementTypeResolver implements GraphQLResolver<ElementCondition> {

	public ElementConditionResolver(VisiXmlRdfTranslator visiXmlRdfTranslator) {
		this.visiXmlRdfTranslator = visiXmlRdfTranslator;
	}

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

	public ComplexElementType getParentComplexElement(ElementCondition elementType) {
		Object complexElementsValue = visiXmlRdfTranslator.getElementType(elementType.getId())
				.getPropertyValue("complexElements");
		if (complexElementsValue != null) {
			@SuppressWarnings("unchecked")
			List<String> complexElementsList = (List<String>) complexElementsValue;
			return complexElementsList.size() > 0 ? new ComplexElementType(complexElementsList.get(0)) : null;
		}
		return null;
	}

	public ComplexElementType getChildComplexElement(ElementCondition elementType) {
		Object complexElementsValue = visiXmlRdfTranslator.getElementType(elementType.getId())
				.getPropertyValue("complexElements");
		if (complexElementsValue != null) {
			@SuppressWarnings("unchecked")
			List<String> complexElementsList = (List<String>) complexElementsValue;
			return complexElementsList.size() > 1 ? new ComplexElementType(complexElementsList.get(1)) : null;
		}
		return null;
	}

	public Integer getPriority(ElementCondition elementType) {
		int priority = 0;
		ElementType elementCondition = visiXmlRdfTranslator.getElementType(elementType.getId());
		if (elementCondition.getPropertyValue("messageInTransaction") != null)
			priority += 8;
		if (elementCondition.getPropertyValue("complexElements") != null) {
			priority += 2;
			@SuppressWarnings("unchecked")
			List<String> propertyList = (List<String>)elementCondition.getPropertyValue("complexElements");
			priority *= propertyList.size();
		}
		if (elementCondition.getPropertyValue("simpleElement") != null)
			priority += 1;
		return priority;
	}

}
