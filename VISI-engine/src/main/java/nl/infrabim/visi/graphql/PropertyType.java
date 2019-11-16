package nl.infrabim.visi.graphql;

import org.springframework.stereotype.Component;

@Component
public class PropertyType {
	private String id;
	private ElementMetaType owner;

	public PropertyType() {
	}

	public PropertyType(String id, ElementMetaType owner) {
		this.id = id;
		this.owner = owner;
	}

	public String getId() {
		return id;
	}

	public ElementMetaType getOwner() {
		return owner;
	}
}
