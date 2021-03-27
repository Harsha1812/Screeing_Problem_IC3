package com.local.screening.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;

import com.local.screening.model.ProjectDetails;

public class Utility {
	Logger LOG = Logger.getLogger(Utility.class.getName());

	public List<ProjectDetails> getProjectDetailsList(String inputString) {

		// Getting each line as a string and adding to a list
		List<String> projectsList = getListFromInputString(inputString);

		List<ProjectDetails> projectDetailsList = new ArrayList<ProjectDetails>();

		// Splitting the comma separated values and adding to each ProjectDetails object
		// to form a list.
		for (int i = 0; i < projectsList.size(); i++) {
			List<String> projectDetails = Arrays.asList(projectsList.get(i).split(","));

			// Checking for the columns count. Adding to the list only if columns count is
			// 6.
			if (projectDetails.size() != 6) {
				LOG.info(
						"Not considering or adding this row to the project list due to the missing of one or more properties : "
								+ projectDetails.toString());
				continue;
			}
			try {
				ProjectDetails details = new ProjectDetails(Long.parseLong(projectDetails.get(0)),
						Integer.parseInt(projectDetails.get(1)), projectDetails.get(2), projectDetails.get(3),
						projectDetails.get(4),
						Long.parseLong(projectDetails.get(5).replaceAll(Constants.REGEX_FOR_SECONDS, "")));
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

	private List<String> getListFromInputString(String inputString) {

		// Splitting the inputText based on the new line regular expression.
		List<String> projectDetailsList = Arrays.asList(inputString.split(Constants.REGEX_FOR_NEWLINE));
		return projectDetailsList;

	}

	// This method is to get the unique records based on a property in the Model
	// Object
	public <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

}
