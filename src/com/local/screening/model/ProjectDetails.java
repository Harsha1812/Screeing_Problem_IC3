package com.local.screening.model;

public class ProjectDetails {

	private Long customerId;
	private Integer contractId;
	private String geoZone;
	private String teamCode;
	private String projectCode;
	private Long buildDuration;

	public ProjectDetails(Builder builder) {
		this.customerId = builder.getCustomerId();
		this.contractId = builder.getContractId();
		this.geoZone = builder.getGeoZone();
		this.teamCode = builder.getTeamCode();
		this.projectCode = builder.getProjectCode();
		this.buildDuration = builder.getBuildDuration();
	}

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

	@Override
	public String toString() {
		return "ProjectDetails [customerId=" + customerId + ", contractId=" + contractId + ", geozone=" + geoZone
				+ ", teamcode=" + teamCode + ", projectcode=" + projectCode + ", buildduration=" + buildDuration + "]";
	}

}
