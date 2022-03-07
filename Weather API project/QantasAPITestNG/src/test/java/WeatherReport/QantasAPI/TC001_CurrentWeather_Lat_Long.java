package WeatherReport.QantasAPI;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC001_CurrentWeather_Lat_Long {

	@Test(dataProvider = "params")
	public void getCurrentWeatherReport(float Lat, float Long) {
		// Specify base URI
		RestAssured.baseURI = "https://api.weatherbit.io/v2.0";

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object

		Response response = httpRequest.request(Method.GET,
				"/current?lat=" + Lat + "&lon=" + Long + "&Key=5ee590f9b7964519bc26083918d8a190");

		// print response in console window

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);

		// status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
		String roundlat = String.format("%.2f", Lat);
		String roundlong = String.format("%.2f", Long);
		Assert.assertEquals(responseBody.contains(roundlat), true);
		Assert.assertEquals(responseBody.contains(roundlong), true);
	}

	@DataProvider(name = "params")
	public Object[][] getData() {
		return new Object[][] { { 27.8296f, 79.8297f }, { 23.5204f, 87.3119f } };
	}

}
