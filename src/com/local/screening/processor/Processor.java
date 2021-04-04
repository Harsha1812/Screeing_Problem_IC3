package com.local.screening.processor;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.local.screening.model.ProjectDetails;

public class Processor {
	Logger LOG = Logger.getLogger(Processor.class.getName());

	Helper helper = new Helper();

	public void getProjectReport(String inputString) {
		List<ProjectDetails> teamDetails = helper.getProjectDetailsList(inputString);

		Map<Integer, Long> distinctCustIdsCountByCntrctId = helper.getUniqueCustomerIdsCountByContarctId(teamDetails);

		LOG.info("Printing the number of unique customerId for each contractId. ");
		for (Entry<Integer, Long> entry : distinctCustIdsCountByCntrctId.entrySet())
			System.out.println(
					"The number of unique customerIds for contractId " + entry.getKey() + ": " + entry.getValue());

		Map<String, Long> disctinctCustIdsCountByGeoZone = helper.getUniqueCustomerIdsCountByGeoZone(teamDetails);

		LOG.info("Printing the number of unique customerId for each geozone.");

		for (Entry<String, Long> entry : disctinctCustIdsCountByGeoZone.entrySet())
			System.out.println(
					"The number of unique customerIds for geozone " + entry.getKey() + ": " + entry.getValue());

		Map<String, Double> avgBuildTimeByGeoZone = helper.getAverageBuildTimeByGeoZone(teamDetails);

		LOG.info("Printing the average buildduration for each geozone.");

		for (Entry<String, Double> entry : avgBuildTimeByGeoZone.entrySet())
			System.out.println("The average buildduration for geozone " + entry.getKey() + ": " + entry.getValue());

		Map<String, List<Long>> onlyCustIdGeoIdList = helper.getCustomerIdsListByGeoZone(teamDetails);
		LOG.info("Printing the list of unique customerId for each geozone.");

		for (Entry<String, List<Long>> entry : onlyCustIdGeoIdList.entrySet())
			System.out.println("The Unique Customer Ids under geozone " + entry.getKey() + ": " + entry.getValue());

	}

}
