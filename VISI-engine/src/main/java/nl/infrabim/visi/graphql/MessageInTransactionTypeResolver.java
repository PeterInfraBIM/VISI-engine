package nl.infrabim.visi.graphql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class MessageInTransactionTypeResolver extends ElementTypeResolver
		implements GraphQLResolver<MessageInTransactionType> {

	public ElementMetaType getMetaType(MessageInTransactionType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(MessageInTransactionType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public Boolean getFirstMessage(MessageInTransactionType elementType) {
		Object firstMessage = visiXmlRdfTranslator.getElementType(elementType.getId()).getPropertyValue("firstMessage");
		return firstMessage != null ? Boolean.parseBoolean((String) firstMessage) : false;
	}

	public Boolean getInitiatorToExecutor(MessageInTransactionType elementType) {
		Object initiatorToExecutor = visiXmlRdfTranslator.getElementType(elementType.getId())
				.getPropertyValue("initiatorToExecutor");
		return initiatorToExecutor != null ? Boolean.parseBoolean((String) initiatorToExecutor) : false;
	}

	public Boolean getOpenSecondaryTransactionsAllowed(MessageInTransactionType elementType) {
		Object openSecondaryTransactionsAllowed = visiXmlRdfTranslator.getElementType(elementType.getId())
				.getPropertyValue("openSecondaryTransactionsAllowed");
		return openSecondaryTransactionsAllowed != null
				? Boolean.parseBoolean((String) openSecondaryTransactionsAllowed)
				: false;
	}

	public MessageType getMessage(MessageInTransactionType elementType) {
		Object message = visiXmlRdfTranslator.getElementType(elementType.getId()).getPropertyValue("message");
		return message != null ? new MessageType((String) message) : null;
	}

	public TransactionType getTransaction(MessageInTransactionType elementType) {
		Object transaction = visiXmlRdfTranslator.getElementType(elementType.getId()).getPropertyValue("transaction");
		return transaction != null ? new TransactionType((String) transaction) : null;
	}

	public List<MessageInTransactionType> getPrevious(MessageInTransactionType elementType) {
		List<MessageInTransactionType> previousMitts = new ArrayList<>();
		Object previous = visiXmlRdfTranslator.getElementType(elementType.getId()).getPropertyValue("previous");
		if (previous != null) {
			@SuppressWarnings("unchecked")
			List<String> prevs = (List<String>) previous;
			for (String prev : prevs) {
				previousMitts.add(new MessageInTransactionType(prev));
			}
		}
		return previousMitts;
	}

}
