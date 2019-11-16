package nl.infrabim.visi.graphql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import nl.infrabim.visi.translator.PropertyTypeRestriction;

@Component
public class ElementMetaTypeResolver implements GraphQLResolver<ElementMetaType> {

	public List<String> getProperties(ElementMetaType elementMetaType) {
		return nl.infrabim.visi.translator.ElementMetaType.get(elementMetaType.getId()).getPropertyNames();
	}

	public Restriction getRestriction(ElementMetaType elementMetaType, String propertyId) {
		PropertyTypeRestriction restriction = getElementType(elementMetaType).getPropertyTypeRestriction(propertyId);
		if (restriction != null) {
			String id = restriction.getOnProperty().getId();
			return new Restriction(new PropertyMetaType(id, getPropertyType(id).getRange()),
					restriction.getMinCardinality(), restriction.getMaxCardinality(), restriction.isMandatory(),
					restriction.isMultiple());
		} else {
			return null;
		}
	}

	public List<Restriction> getRestrictions(ElementMetaType elementMetaType) {
		List<Restriction> restrictions = null;
		List<String> properties = getProperties(elementMetaType);
		if (properties != null) {
			restrictions = new ArrayList<>();
			for (String id : getProperties(elementMetaType)) {
				restrictions.add(getRestriction(elementMetaType, id));
			}
		}
		return restrictions;
	}

	private nl.infrabim.visi.translator.ElementMetaType getElementType(ElementMetaType elementMetaType) {
		return nl.infrabim.visi.translator.ElementMetaType.get(elementMetaType.getId());
	}

	private nl.infrabim.visi.translator.PropertyMetaType getPropertyType(String propertyId) {
		return nl.infrabim.visi.translator.PropertyMetaType.get(propertyId);
	}

}
