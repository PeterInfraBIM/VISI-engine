package nl.infrabim.visi.graphql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {
	public Query() {
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
}
