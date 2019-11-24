package nl.infrabim.visi.graphql;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class PersonTypeResolver extends ElementTypeResolver implements GraphQLResolver<PersonType> {

	public ElementMetaType getMetaType(PersonType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(PersonType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public String getDescription(PersonType elementType) {
		return super.getDescription(elementType);
	}

	public OffsetDateTime getStartDate(PersonType elementType) throws ParseException {
		return super.getStartDate(elementType);
	}

	public OffsetDateTime getEndDate(PersonType elementType) throws ParseException {
		return super.getEndDate(elementType);
	}

	public String getState(PersonType elementType) {
		return super.getState(elementType);
	}

	public OffsetDateTime getDateLaMu(PersonType elementType) {
		return super.getDateLaMu(elementType);
	}

	public String getUserLaMu(PersonType elementType) {
		return super.getUserLaMu(elementType);
	}

	public String getLanguage(PersonType elementType) {
		return super.getLanguage(elementType);
	}

	public String getCategory(PersonType elementType) {
		return super.getCategory(elementType);
	}

	public String getHelpInfo(PersonType elementType) {
		return super.getHelpInfo(elementType);
	}

	public String getCode(PersonType elementType) {
		return super.getCode(elementType);
	}

	public List<ComplexElementType> getComplexElements(PersonType elementType) {
		return super.getComplexElements(elementType);
	}

}
