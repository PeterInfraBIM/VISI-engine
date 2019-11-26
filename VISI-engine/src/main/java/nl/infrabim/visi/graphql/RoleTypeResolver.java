package nl.infrabim.visi.graphql;

import java.text.ParseException;
import java.time.OffsetDateTime;
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

	public String getState(RoleType elementType) {
		return super.getState(elementType);
	}

	public OffsetDateTime getDateLaMu(RoleType elementType) {
		return super.getDateLaMu(elementType);
	}

	public String getUserLaMu(RoleType elementType) {
		return super.getUserLaMu(elementType);
	}

	public String getLanguage(RoleType elementType) {
		return super.getLanguage(elementType);
	}

	public String getCategory(RoleType elementType) {
		return super.getCategory(elementType);
	}

	public String getHelpInfo(RoleType elementType) {
		return super.getHelpInfo(elementType);
	}

	public String getCode(RoleType elementType) {
		return super.getCode(elementType);
	}

	public String getResponsibilityScope(RoleType elementType) {
		return getPropertyValue(elementType, "responsibilityScope");
	}

	public String getResponsibilityTask(RoleType elementType) {
		return getPropertyValue(elementType, "responsibilityTask");
	}

	public String getResponsibilitySupportTask(RoleType elementType) {
		return getPropertyValue(elementType, "responsibilitySupportTask");
	}

	public String getResponsibilityFeedback(RoleType elementType) {
		return getPropertyValue(elementType, "responsibilityFeedback");
	}

	public List<TransactionType> getInvExecutors(RoleType roleType) {
		return getInverses(roleType, TransactionType.class, "executor");
	}

	public List<TransactionType> getInvInitiators(RoleType roleType) {
		return getInverses(roleType, TransactionType.class, "initiator");
	}

}
