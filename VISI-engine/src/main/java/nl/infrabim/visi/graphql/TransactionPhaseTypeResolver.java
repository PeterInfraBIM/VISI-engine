package nl.infrabim.visi.graphql;

import java.text.ParseException;
import java.time.OffsetDateTime;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class TransactionPhaseTypeResolver extends ElementTypeResolver implements GraphQLResolver<TransactionPhaseType> {

	public ElementMetaType getMetaType(TransactionPhaseType elementType) {
		return super.getMetaType(elementType);
	}

	public String getPropertyValue(TransactionPhaseType elementType, String propertyName) {
		return super.getPropertyValue(elementType, propertyName);
	}

	public String getDescription(TransactionPhaseType elementType) {
		return super.getDescription(elementType);
	}

	public OffsetDateTime getStartDate(TransactionPhaseType elementType) throws ParseException {
		return super.getStartDate(elementType);
	}

	public OffsetDateTime getEndDate(TransactionPhaseType elementType) throws ParseException {
		return super.getEndDate(elementType);
	}

	public String getState(TransactionPhaseType elementType) {
		return super.getState(elementType);
	}

	public OffsetDateTime getDateLaMu(TransactionPhaseType elementType) {
		return super.getDateLaMu(elementType);
	}

	public String getUserLaMu(TransactionPhaseType elementType) {
		return super.getUserLaMu(elementType);
	}

	public String getLanguage(TransactionPhaseType elementType) {
		return super.getLanguage(elementType);
	}

	public String getCategory(TransactionPhaseType elementType) {
		return super.getCategory(elementType);
	}

	public String getHelpInfo(TransactionPhaseType elementType) {
		return super.getHelpInfo(elementType);
	}

	public String getCode(TransactionPhaseType elementType) {
		return super.getCode(elementType);
	}

}
