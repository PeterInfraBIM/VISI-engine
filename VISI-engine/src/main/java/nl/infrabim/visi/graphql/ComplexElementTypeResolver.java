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

	public String getHelpInfo(ComplexElementType elementType) {
		return super.getHelpInfo(elementType);
	}

	public List<ComplexElementType> getComplexElements(ComplexElementType elementType) {
		return super.getComplexElements(elementType);
	}

	public List<SimpleElementType> getSimpleElements(ComplexElementType elementType) {
		return super.getSimpleElements(elementType);
	}

}
