package WeatherReport.QantasAPI;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_CurrentWeather_MultipleCities {

	

		@Test(dataProvider = "citiescode")
		public void getCurrentWeatherReport(String cities) {
			// Specify base URI
			RestAssured.baseURI = "https://api.weatherbit.io/v2.0";

			// Request object
			RequestSpecification httpRequest = RestAssured.given();

			// Response object

			Response response = httpRequest.request(Method.GET,
					"/current?cities=" + cities + "&Key=5ee590f9b7964519bc26083918d8a190");

			// print response in console window

			String responseBody = response.getBody().asString();
			System.out.println("Response Body is:" + responseBody);

			// status code validation
			int statusCode = response.getStatusCode();
			System.out.println("Status code is: " + statusCode);
		    Assert.assertEquals(statusCode, 200);
		    Assert.assertEquals(responseBody.contains(cities), true);
		
		}

		@DataProvider(name = "citiescode")
		public Object[][] getData() {
			return new Object[][] { { "4487042,4494942" },{"242001,713216"} };
		}

	}

