package nl.infrabim.visi.graphql;

import java.time.OffsetDateTime;
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

	public Integer getRequiredNotify(MessageInTransactionType elementType) {
		String requiredNotify = getPropertyValue(elementType, "requiredNotify");
		return requiredNotify != null ? Integer.valueOf(requiredNotify) : null;
	}

	public OffsetDateTime getDateLaMu(MessageInTransactionType elementType) {
		return super.getDateLaMu(elementType);
	}

	public String getUserLaMu(MessageInTransactionType elementType) {
		return super.getUserLaMu(elementType);
	}

	public Boolean getReceived(MessageInTransactionType elementType) {
		String received = getPropertyValue(elementType, "received");
		return received != null ? Boolean.valueOf(received) : null;
	}

	public Boolean getSend(MessageInTransactionType elementType) {
		String send = getPropertyValue(elementType, "send");
		return send != null ? Boolean.valueOf(send) : null;
	}

	public String getState(MessageInTransactionType elementType) {
		return super.getState(elementType);
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

	public Boolean getFirstMessage(MessageInTransactionType elementType) {
		Object firstMessage = visiXmlRdfTranslator.getElementType(elementType.getId()).getPropertyValue("firstMessage");
		return firstMessage != null ? Boolean.parseBoolean((String) firstMessage) : false;
	}

	public MessageType getMessage(MessageInTransactionType elementType) {
		Object message = visiXmlRdfTranslator.getElementType(elementType.getId()).getPropertyValue("message");
		return message != null ? new MessageType((String) message) : null;
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

	public TransactionType getTransaction(MessageInTransactionType elementType) {
		Object transaction = visiXmlRdfTranslator.getElementType(elementType.getId()).getPropertyValue("transaction");
		return transaction != null ? new TransactionType((String) transaction) : null;
	}

	public TransactionPhaseType getTransactionPhase(MessageInTransactionType elementType) {
		String transactionPhase = getPropertyValue(elementType, "transactionPhase");
		return transactionPhase != null ? new TransactionPhaseType(transactionPhase) : null;
	}

	public GroupType getGroup(MessageInTransactionType elementType) {
		String group = getPropertyValue(elementType, "group");
		return group != null ? new GroupType(group) : null;
	}

	public List<AppendixType> getAppendixTypes(MessageInTransactionType elementType) {
		return super.getAppendixTypes(elementType);
	}

	public List<MessageInTransactionTypeCondition> getConditions(MessageInTransactionType elementType) {
		Object conditionsValue = visiXmlRdfTranslator.getElementType(elementType.getId())
				.getPropertyValue("conditions");
		if (conditionsValue != null) {
			List<MessageInTransactionTypeCondition> conditionsList = new ArrayList<>();
			@SuppressWarnings("unchecked")
			List<String> conditions = (List<String>) conditionsValue;
			conditions.forEach(c -> {
				conditionsList.add(new MessageInTransactionTypeCondition(c));
			});
			return conditionsList;
		}
		return null;
	}

	public List<MessageInTransactionType> getNext(MessageInTransactionType elementType) {
		List<MessageInTransactionType> nextMitts = new ArrayList<>();
		visiXmlRdfTranslator.getElementTypes().forEach(m -> {
			if (m.getType().equals("MessageInTransactionType")) {
				Object previous = m.getPropertyValue("previous");
				if (previous != null) {
					@SuppressWarnings("unchecked")
					List<String> previousList = (List<String>) previous;
					if (previousList.contains(elementType.getId())) {
						nextMitts.add(new MessageInTransactionType(m.getId()));
					}
				}
			}
		});
		return nextMitts;
	}

}
