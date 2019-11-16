package nl.infrabim.visi.translator;

public class PropertyTypeRestriction {
	private PropertyMetaType onProperty;
	private Integer minCardinality;
	private Integer maxCardinality;

	public PropertyTypeRestriction() {
	}

	public PropertyTypeRestriction(PropertyMetaType onProperty, Integer minCardinality, Integer maxCardinality) {
		this.onProperty = onProperty;
		this.minCardinality = minCardinality;
		this.maxCardinality = maxCardinality;
	}

	public PropertyMetaType getOnProperty() {
		return onProperty;
	}

	public Integer getMinCardinality() {
		return minCardinality;
	}

	public Integer getMaxCardinality() {
		return maxCardinality;
	}

	public boolean isMandatory() {
		return getMinCardinality() != null && getMinCardinality() > 0;
	}

	public boolean isMultiple() {
		return getMaxCardinality() == null || (getMaxCardinality() != null && getMaxCardinality() > 1);
	}

}
