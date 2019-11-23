package nl.infrabim.visi.graphql;

import java.text.ParseException;
import java.time.OffsetDateTime;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class GroupTypeResolver extends ElementTypeResolver implements GraphQLResolver<GroupType> {

	public ElementMetaType getMetaType(GroupType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(GroupType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public String getDescription(GroupType elementType) {
		return super.getDescription(elementType);
	}

	public OffsetDateTime getStartDate(GroupType elementType) throws ParseException {
		return super.getStartDate(elementType);
	}

	public OffsetDateTime getEndDate(GroupType elementType) throws ParseException {
		return super.getEndDate(elementType);
	}

	public String getState(GroupType elementType) {
		return super.getState(elementType);
	}

	public OffsetDateTime getDateLaMu(GroupType elementType) {
		return super.getDateLaMu(elementType);
	}

	public String getUserLaMu(GroupType elementType) {
		return super.getUserLaMu(elementType);
	}

	public String getLanguage(GroupType elementType) {
		return super.getLanguage(elementType);
	}

	public String getCategory(GroupType elementType) {
		return super.getCategory(elementType);
	}

	public String getHelpInfo(GroupType elementType) {
		return super.getHelpInfo(elementType);
	}

}
