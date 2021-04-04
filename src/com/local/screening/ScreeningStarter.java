package com.local.screening;

import com.local.screening.processor.Processor;

public class ScreeningStarter {

	static Processor processor = new Processor();

	public static void main(String[] args) {
		String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\r\n"
				+ "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\r\n"
				+ "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\r\n"
				+ "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\r\n"
				+ "3244132,2346,eu_west,YellowTeam3,ProjectEgg,04S";

		processor.getProjectReport(inputString);

	}

}
