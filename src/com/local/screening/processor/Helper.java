package com.local.screening.processor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.local.screening.model.ProjectDetails;
import com.local.screening.utility.Utility;

public class Helper {

	private Utility util = new Utility();

	public List<ProjectDetails> getProjectDetailsList(String inputString) {

		return util.getProjectDetailsList(inputString);
	}

	public Map<Integer, Long> getUniqueCustomerIdsCountByContractId(List<ProjectDetails> teamDetails) {

		Map<Integer, Long> distinctCustIdsByCntrctId = teamDetails.stream()
				.filter(util.distinctByKey(p -> p.getCustomerId()))
				.collect(Collectors.groupingBy(ProjectDetails::getContractId, Collectors.counting()));
		return distinctCustIdsByCntrctId;
	}

	public Map<String, Long> getUniqueCustomerIdsCountByGeoZone(List<ProjectDetails> teamDetails) {
		Map<String, Long> disctinctCustIdsByGeoZone = teamDetails.stream()
				.filter(util.distinctByKey(p -> p.getCustomerId()))
				.collect(Collectors.groupingBy(ProjectDetails::getGeoZone, Collectors.counting()));
		return disctinctCustIdsByGeoZone;
	}

	public Map<String, Double> getAverageBuildTimeByGeoZone(List<ProjectDetails> teamDetails) {
		Map<String, Double> avgBuildTimeByGeoZone = teamDetails.stream().collect(Collectors
				.groupingBy(ProjectDetails::getGeoZone, Collectors.averagingDouble(ProjectDetails::getBuildDuration)));
		return avgBuildTimeByGeoZone;
	}

	public Map<String, List<Long>> getCustomerIdsListByGeoZone(List<ProjectDetails> teamDetails) {
		Map<String, List<Long>> onlyCustIdGeoIdList = teamDetails.stream()
				.filter(util.distinctByKey(p -> p.getCustomerId()))
				.collect(Collectors.groupingBy(ProjectDetails::getGeoZone,
						Collectors.mapping(ProjectDetails::getCustomerId, Collectors.toList())));
		return onlyCustIdGeoIdList;
	}

}
