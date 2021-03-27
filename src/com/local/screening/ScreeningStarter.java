package com.local.screening;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.local.screening.model.ProjectDetails;
import com.local.screening.utility.Constants;

public class ScreeningStarter {

	public static void main(String[] args) {
		String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\r\n"
				+ "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\r\n"
				+ "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\r\n"
				+ "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\r\n"
				+ "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122S";

		List<ProjectDetails> teamDetails = getProjectDetailsList(inputString);
		getProjectReport(teamDetails);
	}

	private static void getProjectReport(List<ProjectDetails> teamDetails) {

		Map<Integer, Long> custIdConId = teamDetails.stream().filter(distinctByKey(p -> p.getCustomerId()))
				.collect(Collectors.groupingBy(ProjectDetails::getContractId, Collectors.counting()));

		for (Entry<Integer, Long> entry : custIdConId.entrySet())
			System.out.println(
					"The number of unique customerIds for contractId " + entry.getKey() + ": " + entry.getValue());

		Map<String, Long> custIdGeoId = teamDetails.stream().filter(distinctByKey(p -> p.getCustomerId()))
				.collect(Collectors.groupingBy(ProjectDetails::getGeozone, Collectors.counting()));

		for (Entry<String, Long> entry : custIdGeoId.entrySet())
			System.out.println(
					"The number of unique customerIds for geozone " + entry.getKey() + ": " + entry.getValue());
		Map<String, Double> avgBuildTimeByGeoZone = teamDetails.stream().collect(Collectors
				.groupingBy(ProjectDetails::getGeozone, Collectors.averagingDouble(ProjectDetails::getBuildduration)));

		for (Entry<String, Double> entry : avgBuildTimeByGeoZone.entrySet())
			System.out.println("The average buildduration for geozone " + entry.getKey() + ": " + entry.getValue());

		Map<String, List<Long>> onlyCustIdGeoIdList = teamDetails.stream().filter(distinctByKey(p -> p.getCustomerId()))
				.collect(Collectors.groupingBy(ProjectDetails::getGeozone,
						Collectors.mapping(ProjectDetails::getCustomerId, Collectors.toList())));

		for (Entry<String, List<Long>> entry : onlyCustIdGeoIdList.entrySet())
			System.out.println("The Unique Customer Ids under geozone " + entry.getKey() + ": " + entry.getValue());

	}

	public static List<ProjectDetails> getProjectDetailsList(String inputString) {

		List<String> projectDetailsList = getListOfInputString(inputString);

		List<ProjectDetails> teamDetails = new ArrayList<ProjectDetails>();

		for (int i = 0; i < projectDetailsList.size(); i++) {
			List<String> projectDetails = Arrays.asList(projectDetailsList.get(i).split(","));

			ProjectDetails details = new ProjectDetails(Long.parseLong(projectDetails.get(0)),
					Integer.parseInt(projectDetails.get(1)), projectDetails.get(2), projectDetails.get(3),
					projectDetails.get(4),
					Long.parseLong(projectDetails.get(5).replaceAll(Constants.REGEX_FOR_SECONDS, "")));
			teamDetails.add(details);
		}

		return teamDetails;
	}

	private static List<String> getListOfInputString(String inputString) {

		List<String> projectDetailsList = Arrays.asList(inputString.split(Constants.REGEX_FOR_NEWLINE));
		return projectDetailsList;

	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

}
