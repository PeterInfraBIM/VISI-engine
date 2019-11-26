package nl.infrabim.visi.graphql;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class MessageTypeResolver extends ElementTypeResolver implements GraphQLResolver<MessageType> {

	public ElementMetaType getMetaType(MessageType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(MessageType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public String getDescription(MessageType elementType) {
		return super.getDescription(elementType);
	}

	public OffsetDateTime getStartDate(MessageType elementType) throws ParseException {
		return super.getStartDate(elementType);
	}

	public OffsetDateTime getEndDate(MessageType elementType) throws ParseException {
		return super.getEndDate(elementType);
	}

	public String getState(MessageType elementType) {
		return super.getState(elementType);
	}

	public OffsetDateTime getDateLaMu(MessageType elementType) {
		return super.getDateLaMu(elementType);
	}

	public String getUserLaMu(MessageType elementType) {
		return super.getUserLaMu(elementType);
	}

	public String getLanguage(MessageType elementType) {
		return super.getLanguage(elementType);
	}

	public String getCategory(MessageType elementType) {
		return super.getCategory(elementType);
	}

	public String getHelpInfo(MessageType elementType) {
		return super.getHelpInfo(elementType);
	}

	public String getCode(MessageType elementType) {
		return super.getCode(elementType);
	}

	public Boolean getAppendixMandatory(MessageType elementType) {
		String appendixMandatory = getPropertyValue(elementType, "appendixMandatory");
		return appendixMandatory != null ? Boolean.parseBoolean((String) appendixMandatory) : false;
	}

	public List<ComplexElementType> getComplexElements(MessageType elementType) {
		return super.getComplexElements(elementType);
	}

	public List<AppendixType> getAppendixTypes(MessageType elementType) {
		return super.getAppendixTypes(elementType);
	}

	public List<MessageInTransactionType> getInvMessages(MessageType elementType) {
		return getInverses(elementType, MessageInTransactionType.class, "message");
	}

}
