package com.local;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.local.screening.model.ProjectDetails;
import com.local.screening.processor.Helper;
import com.local.screening.utility.Utility;

public class ProcessorTest {

	private Helper processor = new Helper();
	private Utility util = new Utility();
	String validInputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\r\n"
			+ "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\r\n"
			+ "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\r\n"
			+ "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\r\n"
			+ "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122S";

	String duplicateCustIdInputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\r\n"
			+ "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\r\n"
			+ "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\r\n"
			+ "1223456,2345,us_west,BlueTeam,ProjectDate,2221s\r\n"
			+ "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122S";
	String inValidInputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\r\n"
			+ "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\r\n"
			+ "3244332s,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\r\n"
			+ "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\r\n"
			+ "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122S";

	@Test
	public void checkForValidCountByContractId() throws Exception {

		List<ProjectDetails> projectDetails = util.getProjectDetailsList(validInputString);
		assertEquals(3, processor.getUniqueCustomerIdsCountByContarctId(projectDetails).get(2345).longValue());
	}

	@Test
	public void checkForInValidCountByContractId() throws Exception {

		List<ProjectDetails> projectDetails = util.getProjectDetailsList(inValidInputString);
		assertEquals(1, processor.getUniqueCustomerIdsCountByContarctId(projectDetails).get(2346).longValue());
	}

	@Test
	public void checkForValidCountByGeoZone() throws Exception {

		List<ProjectDetails> projectDetails = util.getProjectDetailsList(validInputString);
		assertEquals(2, processor.getUniqueCustomerIdsCountByGeoZone(projectDetails).get("us_west").longValue());
	}

	@Test()
	public void checkForInValidCountByGeoZone() throws Exception {

		List<ProjectDetails> projectDetails = util.getProjectDetailsList(inValidInputString);

		assertEquals(null, processor.getUniqueCustomerIdsCountByGeoZone(projectDetails).get("us_easr"));

	}

	@Test
	public void checkForValidBuildDurationByZone() throws Exception {

		List<ProjectDetails> projectDetails = util.getProjectDetailsList(validInputString);
		assertEquals(2216, processor.getAverageBuildTimeByGeoZone(projectDetails).get("us_west").longValue());
	}

	@Test()
	public void checkForInValidBuildDurationByZone() throws Exception {

		List<ProjectDetails> projectDetails = util.getProjectDetailsList(inValidInputString);

		assertEquals(null, processor.getUniqueCustomerIdsCountByGeoZone(projectDetails).get("us_easr"));

	}

	@Test
	public void checkForValidUniqueCustIdByGeoZone() throws Exception {

		List<ProjectDetails> projectDetails = util.getProjectDetailsList(validInputString);
		assertEquals(new ArrayList<>(Arrays.asList(1223456L, 1233456L)),
				processor.getCustomerIdsListByGeoZone(projectDetails).get("us_west"));
	}

	@Test()
	public void checkForDuplicateCustIdByGeoZone() throws Exception {

		List<ProjectDetails> projectDetails = util.getProjectDetailsList(duplicateCustIdInputString);
		assertEquals(new ArrayList<>(Arrays.asList(1223456L)),
				processor.getCustomerIdsListByGeoZone(projectDetails).get("us_west"));

	}

}
