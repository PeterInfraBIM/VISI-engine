package nl.infrabim.visi.translator;

import java.util.List;

public class PropertyMetaType extends MetaType {
	private static MapService<PropertyMetaType> mapservice = new MapService<>();

	private String range;
	private List<String> domain;

	private PropertyMetaType() {
		super();
	}

	private PropertyMetaType(String id, String range, List<String> domain) {
		super(id);
		this.range = range;
		this.domain = domain;
	}

	public String getRange() {
		return this.range;
	}

	public boolean isObjectProperty() {
		return ElementMetaType.get(range) != null;
	}
	
	public boolean isDatatypeProperty() {
		return !isObjectProperty();
	}

	public List<String> getDomain() {
		return this.domain;
	}

	public static PropertyMetaType put(String id, String range, List<String> domain) {
		return mapservice.put(id, new PropertyMetaType(id, range, domain));
	}

	public static PropertyMetaType get(String id) {
		return mapservice.get(id);
	}

	public static List<String> getNames() {
		return mapservice.getNames();
	}

	@Override
	public void print() {
		System.out.print("id=\"" + getId() + "\" range=\"" + getRange() + "\"");
		List<String> domainList = getDomain();
		System.out.print(" domain=");
		if (domainList != null) {
			for (String domain : domainList) {
				System.out.print("\"" + domain + "\" ");
			}
		} else {
			System.out.print("null ");
		}
	}

}
