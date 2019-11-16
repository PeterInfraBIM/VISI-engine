package nl.infrabim.visi.graphql;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import nl.infrabim.visi.translator.PropertyMetaType;
// import nl.infrabim.visi.translator.ElementMetaType;

@Component
public class PropertyTypeResolver implements GraphQLResolver<PropertyType> {

	public String getRange(PropertyType propertyType) {
		return PropertyMetaType.get(propertyType.getId()).getRange();
	}

	public Integer getMinCardinality(PropertyType propertyType) {
		return getElementType(propertyType.getOwner()).getPropertyTypeRestriction(propertyType.getId())
				.getMinCardinality();
	}

	public Integer getMaxCardinality(PropertyType propertyType) {
		return getElementType(propertyType.getOwner()).getPropertyTypeRestriction(propertyType.getId())
				.getMaxCardinality();
	}

	public Boolean getMandatory(PropertyType propertyType) {
		return getElementType(propertyType.getOwner()).getPropertyTypeRestriction(propertyType.getId()).isMandatory();
	}

	public Boolean getMultiple(PropertyType propertyType) {
		return getElementType(propertyType.getOwner()).getPropertyTypeRestriction(propertyType.getId()).isMultiple();
	}

	public Boolean getLiteral(PropertyType propertyType) {
		return getElementType(propertyType.getOwner()).getPropertyTypeRestriction(propertyType.getId()).getOnProperty()
				.isDatatypeProperty();
	}

	private nl.infrabim.visi.translator.ElementMetaType getElementType(ElementMetaType elementMetaType) {
		return nl.infrabim.visi.translator.ElementMetaType.get(elementMetaType.getId());
	}
}
