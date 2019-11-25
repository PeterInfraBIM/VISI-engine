package nl.infrabim.visi.graphql;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class ComplexElementTypeResolver extends ElementTypeResolver implements GraphQLResolver<ComplexElementType> {

	public ElementMetaType getMetaType(ComplexElementType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(ComplexElementType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public String getDescription(ComplexElementType elementType) {
		return super.getDescription(elementType);
	}

	public OffsetDateTime getStartDate(ComplexElementType elementType) throws ParseException {
		return super.getStartDate(elementType);
	}

	public OffsetDateTime getEndDate(ComplexElementType elementType) throws ParseException {
		return super.getEndDate(elementType);
	}

	public String getState(ComplexElementType elementType) {
		return super.getState(elementType);
	}

	public OffsetDateTime getDateLaMu(ComplexElementType elementType) {
		return super.getDateLaMu(elementType);
	}

	public String getUserLaMu(ComplexElementType elementType) {
		return super.getUserLaMu(elementType);
	}

	public String getLanguage(ComplexElementType elementType) {
		return super.getLanguage(elementType);
	}

	public String getCategory(ComplexElementType elementType) {
		return super.getCategory(elementType);
	}

	public String getHelpInfo(ComplexElementType elementType) {
		return super.getHelpInfo(elementType);
	}

	public List<ComplexElementType> getComplexElements(ComplexElementType elementType) {
		return super.getComplexElements(elementType);
	}

	public List<SimpleElementType> getSimpleElements(ComplexElementType elementType) {
		return super.getSimpleElements(elementType);
	}

	public Integer getMinOccurs(ComplexElementType elementType) {
		String minOccursStr = getPropertyValue(elementType, "minOccurs");
		return minOccursStr != null ? Integer.parseInt(minOccursStr) : null;
	}

	public Integer getMaxOccurs(ComplexElementType elementType) {
		String maxOccursStr = getPropertyValue(elementType, "maxOccurs");
		return maxOccursStr != null ? Integer.parseInt(maxOccursStr) : null;
	}

	public List<ComplexElementType> getInvComplexElements(ComplexElementType elementType) {
		return super.getInverses(elementType, ComplexElementType.class, "complexElements");
	}

	public List<ElementCondition> getInvElementConditions(ComplexElementType elementType) {
		return super.getInverses(elementType, ElementCondition.class, "complexElements");
	}

	public List<MessageType> getInvMessages(ComplexElementType elementType) {
		return super.getInverses(elementType, MessageType.class, "complexElements");
	}

	public List<OrganisationType> getInvOrganisations(ComplexElementType elementType) {
		return super.getInverses(elementType, OrganisationType.class, "complexElements");
	}

	public List<PersonType> getInvPersons(ComplexElementType elementType) {
		return super.getInverses(elementType, PersonType.class, "complexElements");
	}

	public List<ProjectType> getInvProjects(ComplexElementType elementType) {
		return super.getInverses(elementType, ProjectType.class, "complexElements");
	}

}
