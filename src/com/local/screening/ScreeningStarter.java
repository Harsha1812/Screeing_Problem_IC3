package com.local.screening;

import java.util.List;

import com.local.screening.model.ProjectDetails;
import com.local.screening.processor.Processor;
import com.local.screening.utility.Utility;

public class ScreeningStarter {

	public static void main(String[] args) {
		String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\r\n"
				+ "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\r\n"
				+ "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\r\n"
				+ "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\r\n"
				+ "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122S";

		Utility util = new Utility();
		// Getting a list of ProjectDetails object from the given input String
		List<ProjectDetails> projectDetailsList = util.getProjectDetailsList(inputString);
		Processor processor = new Processor();
		// Calling the method to get the required report
		processor.getProjectReport(projectDetailsList);
	}

}
