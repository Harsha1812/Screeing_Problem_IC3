package com.local;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.local.screening.utility.Utility;

public class UtilityTest {

	private Utility utility = new Utility();
	String validInputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\r\n"
			+ "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\r\n"
			+ "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\r\n"
			+ "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\r\n"
			+ "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122S";
	String inValidInputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\r\n"
			+ "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\r\n"
			+ "3244332s,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\r\n"
			+ "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\r\n"
			+ "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122S";

	String validInputStringRows = "2343225,2345,us_east,RedTeam,ProjectApple,3445s";

	String inValidInputStringRows = "2343225,2345,RedTeam,ProjectApple,3445s";

	@Test
	public void ignoreRowsThatAreHavingCompleteProperties() throws Exception {
		List<String> projectDetails = Arrays.asList(validInputStringRows.split(","));
		
		assertFalse(utility.checkForAllProperties((projectDetails)));
	}

	@Test
	public void ignoreRowsThatAreHavingIncompleteProperties() throws Exception {
		List<String> projectDetails = Arrays.asList(inValidInputStringRows.split(","));
		assertTrue(utility.checkForAllProperties((projectDetails)));
	}

	@Test
	public void addAllRecordsFromTheStringList() throws Exception {
		assertEquals(5, utility.getProjectDetailsList(validInputString).size());
	}

	@Test
	public void reomveBadRecordsFromTheGivenString() throws Exception {
		assertEquals(4, utility.getProjectDetailsList(inValidInputString).size());
	}

}
