package nl.infrabim.visi.graphql;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class SimpleElementTypeResolver extends ElementTypeResolver implements GraphQLResolver<SimpleElementType> {

	public ElementMetaType getMetaType(SimpleElementType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(SimpleElementType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public String getDescription(SimpleElementType elementType) {
		return super.getDescription(elementType);
	}
	
	public String getInterfaceType(SimpleElementType elementType) {
		return getPropertyValue(elementType, "interfaceType");
	}
	
	public String getState(SimpleElementType elementType) {
		return super.getState(elementType);
	}

	public String getHelpInfo(SimpleElementType elementType) {
		return super.getHelpInfo(elementType);
	}

}
