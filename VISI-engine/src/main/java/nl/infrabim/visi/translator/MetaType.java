package nl.infrabim.visi.translator;

public abstract class MetaType {
	private String id;

	protected MetaType() {
	}

	protected MetaType(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public abstract void print();

}
