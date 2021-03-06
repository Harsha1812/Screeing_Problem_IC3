package com.local.screening.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;

import com.local.screening.model.Builder;
import com.local.screening.model.ProjectDetails;

public class Utility {
	Logger LOG = Logger.getLogger(Utility.class.getName());

	public List<ProjectDetails> getProjectDetailsList(String inputString) {

		List<String> projectsList = getListFromInputString(inputString);

		List<ProjectDetails> projectDetailsList = new ArrayList<ProjectDetails>();

		for (int i = 0; i < projectsList.size(); i++) {
			List<String> projectDetails = Arrays.asList(projectsList.get(i).split(","));

			if (checkForAllProperties(projectDetails)) {
				LOG.info(
						"Not considering or adding this row to the project list due to the missing of one or more properties : "
								+ projectDetails.toString());
				continue;
			}
			try {
				ProjectDetails details = new Builder().customerId(Long.parseLong(projectDetails.get(0)))
						.contractId(Integer.parseInt(projectDetails.get(1))).geozone(projectDetails.get(2))
						.projectcode(projectDetails.get(3)).projectcode(projectDetails.get(4))
						.buildduration(
								Long.parseLong(projectDetails.get(5).replaceAll(Constants.REGEX_FOR_SECONDS, "")))
						.build();

				projectDetailsList.add(details);

			} catch (Exception e) {

				LOG.info("Not considering or adding this row to the project list  : " + projectDetails.toString()
						+ " due to the error " + e.getLocalizedMessage());

				e.printStackTrace();
			}

		}

		LOG.info("Total records that are considered to generate the report : " + projectDetailsList.size());

		return projectDetailsList;
	}

	public boolean checkForAllProperties(List<String> projectDetails) {
		return projectDetails.size() != 6;
	}

	private List<String> getListFromInputString(String inputString) {

		List<String> projectDetailsList = Arrays.asList(inputString.split(Constants.REGEX_FOR_NEWLINE));
		return projectDetailsList;

	}

	public <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

}
