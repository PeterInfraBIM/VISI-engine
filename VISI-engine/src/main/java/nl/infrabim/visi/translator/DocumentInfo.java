package nl.infrabim.visi.translator;

import org.w3c.dom.Element;

public class DocumentInfo {
	private String tagName;
	private String xmlns;
	private String xsi;
	private String schemaLocation;

	public DocumentInfo() {
	}

	public DocumentInfo(Element documentElement) {
		this.tagName = documentElement.getTagName();
		this.xmlns = documentElement.getAttribute("xmlns");
		this.xsi = documentElement.getAttribute("xmlns:xsi");
		this.schemaLocation = documentElement.getAttribute("xsi:schemaLocation");
	}

	public String getTagName() {
		return tagName;
	}

	public String getXmlns() {
		return xmlns;
	}

	public String getXsi() {
		return xsi;
	}

	public String getSchemaLocation() {
		return schemaLocation;
	}

	public void setElementAttributes(Element documentElement) {
		documentElement.setAttribute("xmlns", getXmlns());
		documentElement.setAttribute("xmlns:xsi", getXsi());
		documentElement.setAttribute("xsi:schemaLocation", getSchemaLocation());
	}

}
