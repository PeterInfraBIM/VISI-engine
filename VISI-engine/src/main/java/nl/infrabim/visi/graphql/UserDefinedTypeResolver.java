package nl.infrabim.visi.graphql;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class UserDefinedTypeResolver extends ElementTypeResolver implements GraphQLResolver<UserDefinedType> {

	public ElementMetaType getMetaType(UserDefinedType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(UserDefinedType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public String getDescription(UserDefinedType elementType) {
		return super.getDescription(elementType);
	}

	public String getState(UserDefinedType elementType) {
		return super.getState(elementType);
	}

	public OffsetDateTime getDateLaMu(UserDefinedType elementType) {
		return super.getDateLaMu(elementType);
	}

	public String getUserLaMu(UserDefinedType elementType) {
		return super.getUserLaMu(elementType);
	}

	public String getBaseType(UserDefinedType elementType) {
		return getPropertyValue(elementType, "baseType");
	}

	public String getXsdRestriction(UserDefinedType elementType) {
		return getPropertyValue(elementType, "xsdRestriction");
	}

	public String getLanguage(UserDefinedType elementType) {
		return super.getLanguage(elementType);
	}

	public String getHelpInfo(UserDefinedType elementType) {
		return super.getHelpInfo(elementType);
	}

	public List<SimpleElementType> getInvSimpleElements(UserDefinedType elementType) {
		return getInverses(elementType, SimpleElementType.class, "userDefinedType");
	}

}
