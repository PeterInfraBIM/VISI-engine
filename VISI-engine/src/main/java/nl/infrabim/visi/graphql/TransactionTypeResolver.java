package nl.infrabim.visi.graphql;

import java.text.ParseException;
import java.time.OffsetDateTime;
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

	public OffsetDateTime getStartDate(TransactionType elementType) throws ParseException {
		return super.getStartDate(elementType);
	}

	public OffsetDateTime getEndDate(TransactionType elementType) throws ParseException {
		return super.getEndDate(elementType);
	}

	public String getState(TransactionType elementType) {
		return super.getState(elementType);
	}

	public OffsetDateTime getDateLaMu(TransactionType elementType) {
		return super.getDateLaMu(elementType);
	}

	public String getUserLaMu(TransactionType elementType) {
		return super.getUserLaMu(elementType);
	}

	public String getLanguage(TransactionType elementType) {
		return super.getLanguage(elementType);
	}

	public String getCategory(TransactionType elementType) {
		return super.getCategory(elementType);
	}

	public String getHelpInfo(TransactionType elementType) {
		return super.getHelpInfo(elementType);
	}

	public String getCode(TransactionType elementType) {
		return super.getCode(elementType);
	}
	
	public String getResult(TransactionType elementType) {
		return getPropertyValue(elementType, "result");
	}

	public RoleType getInitiator(TransactionType transactionType) {
		Object initiator = visiXmlRdfTranslator.getElementType(transactionType.getId()).getPropertyValue("initiator");
		return initiator != null ? new RoleType(initiator.toString()) : null;
	}

	public RoleType getExecutor(TransactionType transactionType) {
		Object executor = visiXmlRdfTranslator.getElementType(transactionType.getId()).getPropertyValue("executor");
		return executor != null ? new RoleType(executor.toString()) : null;
	}
	
	public List<AppendixType> getAppendixTypes(TransactionType elementType) {
		return super.getAppendixTypes(elementType);
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
