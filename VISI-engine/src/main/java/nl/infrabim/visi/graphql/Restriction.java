package nl.infrabim.visi.graphql;

import org.springframework.stereotype.Component;

@Component
public class Restriction {
	private PropertyMetaType onProperty;
	private Integer minCardinality;
	private Integer maxCardinality;
	private Boolean mandatory;
	private Boolean multiple;

	public Restriction() {
	}

	public Restriction(PropertyMetaType onProperty, Integer minCardinality, Integer maxCardinality, Boolean mandatory,
			Boolean multiple) {
		this.onProperty = onProperty;
		this.minCardinality = minCardinality;
		this.maxCardinality = maxCardinality;
		this.mandatory = mandatory;
		this.multiple = multiple;
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

	public Boolean getMandatory() {
		return mandatory;
	}

	public Boolean getMultiple() {
		return multiple;
	}
}
