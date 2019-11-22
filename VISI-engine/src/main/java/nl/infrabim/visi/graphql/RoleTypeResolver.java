package nl.infrabim.visi.graphql;

import java.text.ParseException;
import java.time.OffsetDateTime;
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

	public String getDescription(RoleType elementType) {
		return super.getDescription(elementType);
	}
	
	public OffsetDateTime getStartDate(RoleType elementType) throws ParseException {
		return super.getStartDate(elementType);
	}

	public OffsetDateTime getEndDate(RoleType elementType) throws ParseException {
		return super.getEndDate(elementType);
	}

	public List<TransactionType> getTransactionTypes(RoleType roleType) {
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
