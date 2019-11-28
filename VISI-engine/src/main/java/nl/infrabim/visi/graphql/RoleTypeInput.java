package nl.infrabim.visi.graphql;

import java.time.OffsetDateTime;

public class RoleTypeInput {
	/*
	 * id: ID! description: String startDate: DateTime endDate: DateTime state:
	 * String dateLaMu: DateTime userLaMu: String language: String category: String
	 * helpInfo: String code: String responsibilityScope: String responsibilityTask:
	 * String responsibilitySupportTask: String responsibilityFeedback: String
	 */

	private String id;
	private String description;
	private OffsetDateTime startDate;
	private OffsetDateTime endDate;
	private String state;
	private OffsetDateTime dateLaMu;
	private String userLaMu;
	private String language;
	private String category;
	private String helpInfo;
	private String code;
	private String responsibilityScope;
	private String responsibilityTask;
	private String responsibilitySupportTask;
	private String responsibilityFeedback;

	public RoleTypeInput() {
	}

	public RoleTypeInput(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OffsetDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(OffsetDateTime startDate) {
		this.startDate = startDate;
	}

	public OffsetDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public OffsetDateTime getDateLaMu() {
		return dateLaMu;
	}

	public void setDateLaMu(OffsetDateTime dateLaMu) {
		this.dateLaMu = dateLaMu;
	}

	public String getUserLaMu() {
		return userLaMu;
	}

	public void setUserLaMu(String userLaMu) {
		this.userLaMu = userLaMu;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getHelpInfo() {
		return helpInfo;
	}

	public void setHelpInfo(String helpInfo) {
		this.helpInfo = helpInfo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResponsibilityScope() {
		return responsibilityScope;
	}

	public void setResponsibilityScope(String responsibilityScope) {
		this.responsibilityScope = responsibilityScope;
	}

	public String getResponsibilityTask() {
		return responsibilityTask;
	}

	public void setResponsibilityTask(String responsibilityTask) {
		this.responsibilityTask = responsibilityTask;
	}

	public String getResponsibilitySupportTask() {
		return responsibilitySupportTask;
	}

	public void setResponsibilitySupportTask(String responsibilitySupportTask) {
		this.responsibilitySupportTask = responsibilitySupportTask;
	}

	public String getResponsibilityFeedback() {
		return responsibilityFeedback;
	}

	public void setResponsibilityFeedback(String responsibilityFeedback) {
		this.responsibilityFeedback = responsibilityFeedback;
	}

}
