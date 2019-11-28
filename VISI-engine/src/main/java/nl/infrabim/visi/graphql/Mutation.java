package nl.infrabim.visi.graphql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import nl.infrabim.visi.translator.VisiXmlRdfTranslator;

@Component
public class Mutation implements GraphQLMutationResolver {
	private final VisiXmlRdfTranslator visiXmlRdfTranslator;

	public Mutation(VisiXmlRdfTranslator visiXmlRdfTranslator) {
		this.visiXmlRdfTranslator = visiXmlRdfTranslator;
	}

	public Framework createFramework(String file)
			throws IOException, FileNameNotUnique, ParserConfigurationException, Error, TransformerException {
		if (!file.endsWith(".xml")) {
			file += ".xml";
		}
		List<String> storedFrameworkFiles = Query.getStoredFrameworkFiles();
		if (storedFrameworkFiles.contains(file))
			throw new FileNameNotUnique("File name not unique");
		visiXmlRdfTranslator.createFramework(Query.UPLOAD_FOLDER_PATH + "/" + file);
		return new Framework(file);
	}

	public Framework saveFramework(String file) throws FileNotFoundException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		visiXmlRdfTranslator.save(Query.UPLOAD_FOLDER_PATH + "/" + file);
		return new Framework(file);
	}

	public ElementType createElementType(String type, String id, Optional<String> file)
			throws ParserConfigurationException, SAXException, IOException {
		if (file.isPresent()) {
			visiXmlRdfTranslator.translate(Query.UPLOAD_FOLDER_PATH + "/" + file);
		}
		visiXmlRdfTranslator.createElementType(type, id);
		return ElementTypes.valueOf(type).createInstance(id);
	}

	// updateRoleType(input: RoleTypeInput): RoleType!
	public RoleType updateRoleType(RoleTypeInput input) {
		if (input.getDescription() != null) {
			visiXmlRdfTranslator.getElementType(input.getId()).putPropertyValue("description", input.getDescription());
		}
		return new RoleType(input.getId());
	}
}
