package nl.infrabim.visi.graphql;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class ProjectTypeResolver extends ElementTypeResolver implements GraphQLResolver<ProjectType> {

	public ElementMetaType getMetaType(ProjectType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(ProjectType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public String getNamespace(ProjectType elementType) {
		return getPropertyValue(elementType, "namespace");
	}

	public String getDescription(ProjectType elementType) {
		return super.getDescription(elementType);
	}

	public OffsetDateTime getStartDate(ProjectType elementType) throws ParseException {
		return super.getStartDate(elementType);
	}

	public OffsetDateTime getEndDate(ProjectType elementType) throws ParseException {
		return super.getEndDate(elementType);
	}

	public String getState(ProjectType elementType) {
		return super.getState(elementType);
	}

	public OffsetDateTime getDateLaMu(ProjectType elementType) {
		return super.getDateLaMu(elementType);
	}

	public String getUserLaMu(ProjectType elementType) {
		return super.getUserLaMu(elementType);
	}

	public String getLanguage(ProjectType elementType) {
		return super.getLanguage(elementType);
	}

	public String getCategory(ProjectType elementType) {
		return super.getCategory(elementType);
	}

	public String getHelpInfo(ProjectType elementType) {
		return super.getHelpInfo(elementType);
	}

	public String getCode(ProjectType elementType) {
		return super.getCode(elementType);
	}

	public List<ComplexElementType> getComplexElements(ProjectType elementType) {
		return super.getComplexElements(elementType);
	}

}
