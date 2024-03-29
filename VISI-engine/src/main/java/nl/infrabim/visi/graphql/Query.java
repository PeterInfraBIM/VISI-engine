package nl.infrabim.visi.graphql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import nl.infrabim.visi.translator.VisiXmlRdfTranslator;

@Component
public class Query implements GraphQLQueryResolver {
	static final String UPLOAD_FOLDER_PATH = "src/main/Resources/static/upload";
	private final VisiXmlRdfTranslator visiXmlRdfTranslator;

	public Query(VisiXmlRdfTranslator visiXmlRdfTranslator) {
		this.visiXmlRdfTranslator = visiXmlRdfTranslator;
	}

	public String test() {
		return "Test response.";
	}

	public List<ElementMetaType> getElementMetaTypes() {
		List<ElementMetaType> elementMetaTypes = null;
		List<String> names = nl.infrabim.visi.translator.ElementMetaType.getNames();
		if (names != null) {
			elementMetaTypes = new ArrayList<>();
			for (String id : names) {
				elementMetaTypes.add(new ElementMetaType(id));
			}
		}
		return elementMetaTypes;
	}

	public List<String> getFrameworkFiles() throws IOException {
		return getStoredFrameworkFiles();
	}

	static List<String> getStoredFrameworkFiles() throws IOException {
		List<String> frameworkFiles = new ArrayList<>();
		Path path = Paths.get(UPLOAD_FOLDER_PATH);
		Files.list(path).forEach((f) -> {
			frameworkFiles.add(f.getFileName().toString());
		});
		return frameworkFiles;
	}

	public Framework loadFrameworkFile(String visiXmlFile)
			throws ParserConfigurationException, SAXException, IOException {
		String filePath = UPLOAD_FOLDER_PATH + "/" + visiXmlFile;
		visiXmlRdfTranslator.translate(filePath);
		return new Framework(visiXmlFile);
	}

}
