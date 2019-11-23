package nl.infrabim.visi.graphql;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class AppendixTypeResolver extends ElementTypeResolver implements GraphQLResolver<AppendixType> {

	public ElementMetaType getMetaType(AppendixType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(AppendixType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public String getDescription(AppendixType elementType) {
		return super.getDescription(elementType);
	}

	public OffsetDateTime getStartDate(AppendixType elementType) throws ParseException {
		return super.getStartDate(elementType);
	}

	public OffsetDateTime getEndDate(AppendixType elementType) throws ParseException {
		return super.getEndDate(elementType);
	}

	public String getState(AppendixType elementType) {
		return super.getState(elementType);
	}

	public OffsetDateTime getDateLaMu(AppendixType elementType) {
		return super.getDateLaMu(elementType);
	}

	public String getUserLaMu(AppendixType elementType) {
		return super.getUserLaMu(elementType);
	}

	public String getLanguage(AppendixType elementType) {
		return super.getLanguage(elementType);
	}

	public String getCategory(AppendixType elementType) {
		return super.getCategory(elementType);
	}

	public String getHelpInfo(AppendixType elementType) {
		return super.getHelpInfo(elementType);
	}

	public String getCode(AppendixType elementType) {
		return super.getCode(elementType);
	}

	public List<ComplexElementType> getComplexElements(AppendixType elementType) {
		return super.getComplexElements(elementType);
	}
}
