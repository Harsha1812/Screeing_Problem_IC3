package com.local.screening.model;

public class Builder {
	private Long customerId;
	private Integer contractId;
	private String geoZone;
	private String teamCode;
	private String projectCode;
	private Long buildDuration;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public String getGeoZone() {
		return geoZone;
	}

	public void setGeoZone(String geoZone) {
		this.geoZone = geoZone;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Long getBuildDuration() {
		return buildDuration;
	}

	public void setBuildDuration(Long buildDuration) {
		this.buildDuration = buildDuration;
	}

	public Builder customerId(Long value) {
		this.customerId = value;
		return this;
	}

	public Builder contractId(Integer value) {
		this.contractId = value;
		return this;
	}

	public Builder geozone(String value) {
		this.geoZone = value;
		return this;
	}

	public Builder teamcode(String value) {
		this.teamCode = value;
		return this;
	}

	public Builder projectcode(String value) {
		this.projectCode = value;
		return this;
	}

	public Builder buildduration(Long value) {
		this.buildDuration = value;
		return this;
	}

	public ProjectDetails build() {
		return new ProjectDetails(this);
	}

}
