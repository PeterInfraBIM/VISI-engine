package nl.infrabim.visi.translator;

import org.w3c.dom.Element;

public class DocumentInfo {
	private String tagName;
	private String xmlns;
	private String xsi;
	private String schemaLocation;

	public DocumentInfo() {
		this.tagName = "visiXML_VISI_Systematics";
		this.xmlns = "http://www.visi.nl/schemas/20160331";
		this.xsi = "http://www.w3.org/2001/XMLSchema-instance";
		this.schemaLocation = "http://www.visi.nl/schemas/20160331 C:\20160331.xsd";
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
