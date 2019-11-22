package nl.infrabim.visi.graphql;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class MessageTypeResolver extends ElementTypeResolver implements GraphQLResolver<MessageType> {

	public ElementMetaType getMetaType(MessageType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(MessageType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}
	
	public String getDescription(MessageType elementType) {
		return super.getDescription(elementType);
	}
	
	public Boolean getAppendixMandatory(MessageType elementType) {
		Object appendixMandatory = visiXmlRdfTranslator.getElementType(elementType.getId()).getPropertyValue("appendixMandatory");
		return appendixMandatory != null ? Boolean.parseBoolean((String) appendixMandatory) : false;
	}


}
