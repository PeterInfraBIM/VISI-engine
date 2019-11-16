package nl.infrabim.visi.graphql;

public class PropertyMetaType {

	private String id;
	private String range;

	public PropertyMetaType() {
	}

	public PropertyMetaType(String id, String range) {
		this.id = id;
		this.range = range;
	}

	public String getId() {
		return id;
	}

	public String getRange() {
		return range;
	}

}
