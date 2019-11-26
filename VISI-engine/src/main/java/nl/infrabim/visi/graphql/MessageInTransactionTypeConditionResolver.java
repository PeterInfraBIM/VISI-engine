package nl.infrabim.visi.graphql;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class MessageInTransactionTypeConditionResolver extends ElementTypeResolver
		implements GraphQLResolver<MessageInTransactionTypeCondition> {

	public ElementMetaType getMetaType(MessageInTransactionTypeCondition elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(MessageInTransactionTypeCondition elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public List<MessageInTransactionType> getSendAfter(MessageInTransactionTypeCondition elementType) {
		Object sendAfterValue = visiXmlRdfTranslator.getElementType(elementType.getId()).getPropertyValue("sendAfter");
		if (sendAfterValue != null) {
			List<MessageInTransactionType> sendAfterList = new ArrayList<>();
			@SuppressWarnings("unchecked")
			List<String> sendAfter = (List<String>) sendAfterValue;
			sendAfter.forEach(s -> {
				sendAfterList.add(new MessageInTransactionType(s));
			});
			return sendAfterList;
		}
		return null;
	}

	public List<MessageInTransactionType> getSendBefore(MessageInTransactionTypeCondition elementType) {
		Object sendBeforeValue = visiXmlRdfTranslator.getElementType(elementType.getId())
				.getPropertyValue("sendBefore");
		if (sendBeforeValue != null) {
			List<MessageInTransactionType> sendBeforeList = new ArrayList<>();
			@SuppressWarnings("unchecked")
			List<String> sendAfter = (List<String>) sendBeforeValue;
			sendAfter.forEach(s -> {
				sendBeforeList.add(new MessageInTransactionType(s));
			});
			return sendBeforeList;
		}
		return null;
	}

	public String getState(MessageInTransactionTypeCondition elementType) {
		return super.getState(elementType);
	}

	public OffsetDateTime getDateLaMu(MessageInTransactionTypeCondition elementType) {
		return super.getDateLaMu(elementType);
	}

	public String getUserLaMu(MessageInTransactionTypeCondition elementType) {
		return super.getUserLaMu(elementType);
	}

	public String getHelpInfo(MessageInTransactionTypeCondition elementType) {
		return super.getHelpInfo(elementType);
	}

	public List<MessageInTransactionType> getInvConditions(MessageInTransactionTypeCondition elementType) {
		return getInverses(elementType, MessageInTransactionType.class, "conditions");
	}
}
