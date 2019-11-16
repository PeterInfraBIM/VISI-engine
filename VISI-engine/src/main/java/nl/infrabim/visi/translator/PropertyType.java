package nl.infrabim.visi.translator;

public class PropertyType {
	private String id;
	private Object value;

	public PropertyType() {
	}

	public PropertyType(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public PropertyMetaType getMetaType() {
		return PropertyMetaType.get(id);
	}
}
