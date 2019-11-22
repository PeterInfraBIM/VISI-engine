package nl.infrabim.visi.graphql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class TransactionTypeResolver extends ElementTypeResolver implements GraphQLResolver<TransactionType> {

	public ElementMetaType getMetaType(TransactionType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(TransactionType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public String getDescription(TransactionType elementType) {
		return super.getDescription(elementType);
	}
	
	public RoleType getInitiator(TransactionType transactionType) {
		Object initiator = visiXmlRdfTranslator.getElementType(transactionType.getId()).getPropertyValue("initiator");
		return initiator != null ? new RoleType(initiator.toString()) : null;
	}

	public RoleType getExecutor(TransactionType transactionType) {
		Object executor = visiXmlRdfTranslator.getElementType(transactionType.getId()).getPropertyValue("executor");
		return executor != null ? new RoleType(executor.toString()) : null;
	}

	public List<MessageInTransactionType> getMessageInTransactionTypes(TransactionType transactionType) {
		List<MessageInTransactionType> mitts = new ArrayList<>();
		visiXmlRdfTranslator.getElementTypes().forEach((e) -> {
			if (e.getType().equals("MessageInTransactionType")) {
				String transaction = (String) e.getPropertyValue("transaction");
				if (transaction != null && transaction.equals(transactionType.getId())) {
					mitts.add(new MessageInTransactionType(e.getId()));
				}
			}
		});
		return mitts;
	}

}
