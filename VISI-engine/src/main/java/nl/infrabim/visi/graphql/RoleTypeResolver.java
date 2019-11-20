package nl.infrabim.visi.graphql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class RoleTypeResolver extends ElementTypeResolver implements GraphQLResolver<RoleType> {
	
	public ElementMetaType getMetaType(RoleType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(RoleType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}
	
	public List<TransactionType> getTransactions(RoleType roleType) {
		List<TransactionType> transactions = new ArrayList<>();
		visiXmlRdfTranslator.getElementTypes().forEach(e -> {
			if (e.getType().equals("TransactionType")) {
				if (e.getPropertyValue("initiator").equals(roleType.getId())
						|| e.getPropertyValue("executor").equals(roleType.getId())) {
					transactions.add(new TransactionType(e.getId()));
				}
			}
		});
		return transactions;
	}

}
