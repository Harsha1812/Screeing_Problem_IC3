package com.local.screening.processor;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.local.screening.model.ProjectDetails;
import com.local.screening.utility.Utility;

public class Processor {
	Logger LOG = Logger.getLogger(Processor.class.getName());

	public void getProjectReport(List<ProjectDetails> teamDetails) {

		Utility util = new Utility();

		Map<Integer, Long> distinctCustIdsCountByCntrctId = getUniqueCustomerIdsCountByContarctId(teamDetails, util);

		LOG.info("Printing the number of unique customerId for each contractId. ");
		for (Entry<Integer, Long> entry : distinctCustIdsCountByCntrctId.entrySet())
			System.out.println(
					"The number of unique customerIds for contractId " + entry.getKey() + ": " + entry.getValue());

		Map<String, Long> disctinctCustIdsCountByGeoZone = getUniqueCustomerIdsCountByGeoZone(teamDetails, util);

		LOG.info("Printing the number of unique customerId for each geozone.");

		for (Entry<String, Long> entry : disctinctCustIdsCountByGeoZone.entrySet())
			System.out.println(
					"The number of unique customerIds for geozone " + entry.getKey() + ": " + entry.getValue());

		Map<String, Double> avgBuildTimeByGeoZone = getAverageBuildTimeByGeoZone(teamDetails);

		LOG.info("Printing the average buildduration for each geozone.");

		for (Entry<String, Double> entry : avgBuildTimeByGeoZone.entrySet())
			System.out.println("The average buildduration for geozone " + entry.getKey() + ": " + entry.getValue());

		Map<String, List<Long>> onlyCustIdGeoIdList = getCustomerIdsListByGeoZone(teamDetails, util);
		LOG.info("Printing the list of unique customerId for each geozone.");

		for (Entry<String, List<Long>> entry : onlyCustIdGeoIdList.entrySet())
			System.out.println("The Unique Customer Ids under geozone " + entry.getKey() + ": " + entry.getValue());

	}

	public Map<String, List<Long>> getCustomerIdsListByGeoZone(List<ProjectDetails> teamDetails, Utility util) {
		Map<String, List<Long>> onlyCustIdGeoIdList = teamDetails.stream()
				.filter(util.distinctByKey(p -> p.getCustomerId()))
				.collect(Collectors.groupingBy(ProjectDetails::getGeozone,
						Collectors.mapping(ProjectDetails::getCustomerId, Collectors.toList())));
		return onlyCustIdGeoIdList;
	}

	public Map<String, Double> getAverageBuildTimeByGeoZone(List<ProjectDetails> teamDetails) {
		Map<String, Double> avgBuildTimeByGeoZone = teamDetails.stream().collect(Collectors
				.groupingBy(ProjectDetails::getGeozone, Collectors.averagingDouble(ProjectDetails::getBuildduration)));
		return avgBuildTimeByGeoZone;
	}

	public Map<String, Long> getUniqueCustomerIdsCountByGeoZone(List<ProjectDetails> teamDetails, Utility util) {
		Map<String, Long> disctinctCustIdsByGeoZone = teamDetails.stream()
				.filter(util.distinctByKey(p -> p.getCustomerId()))
				.collect(Collectors.groupingBy(ProjectDetails::getGeozone, Collectors.counting()));
		return disctinctCustIdsByGeoZone;
	}

	public Map<Integer, Long> getUniqueCustomerIdsCountByContarctId(List<ProjectDetails> teamDetails, Utility util) {

		Map<Integer, Long> distinctCustIdsByCntrctId = teamDetails.stream()
				.filter(util.distinctByKey(p -> p.getCustomerId()))
				.collect(Collectors.groupingBy(ProjectDetails::getContractId, Collectors.counting()));
		return distinctCustIdsByCntrctId;
	}
}
