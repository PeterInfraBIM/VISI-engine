package nl.infrabim.visi.graphql;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class OrganisationTypeResolver extends ElementTypeResolver implements GraphQLResolver<OrganisationType> {

	public ElementMetaType getMetaType(OrganisationType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(OrganisationType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public String getDescription(OrganisationType elementType) {
		return super.getDescription(elementType);
	}

	public OffsetDateTime getStartDate(OrganisationType elementType) throws ParseException {
		return super.getStartDate(elementType);
	}

	public OffsetDateTime getEndDate(OrganisationType elementType) throws ParseException {
		return super.getEndDate(elementType);
	}

	public String getState(OrganisationType elementType) {
		return super.getState(elementType);
	}

	public OffsetDateTime getDateLaMu(OrganisationType elementType) {
		return super.getDateLaMu(elementType);
	}

	public String getUserLaMu(OrganisationType elementType) {
		return super.getUserLaMu(elementType);
	}

	public String getLanguage(OrganisationType elementType) {
		return super.getLanguage(elementType);
	}

	public String getCategory(OrganisationType elementType) {
		return super.getCategory(elementType);
	}

	public String getHelpInfo(OrganisationType elementType) {
		return super.getHelpInfo(elementType);
	}

	public String getCode(OrganisationType elementType) {
		return super.getCode(elementType);
	}

	public List<ComplexElementType> getComplexElements(OrganisationType elementType) {
		return super.getComplexElements(elementType);
	}

}
