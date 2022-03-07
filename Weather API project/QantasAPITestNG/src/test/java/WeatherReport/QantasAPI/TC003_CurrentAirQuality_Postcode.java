package WeatherReport.QantasAPI;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_CurrentAirQuality_Postcode

{

	    @Test(dataProvider = "Postcode")
	    public void getCurrentAirQuality(String postcode, String name) {
		// Specify base URI
		RestAssured.baseURI = "https://api.weatherbit.io/v2.0";

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object

		Response response = httpRequest.request(Method.GET,
		"/current/airquality?postal_code=" + postcode + "&key=5ee590f9b7964519bc26083918d8a190");

		// print response in console window

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);
		Assert.assertEquals(responseBody.contains(name), true);

		// status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);

	}

	   @DataProvider(name = "Postcode")
	   public Object[][] Getdata() {
	   return new Object[][] { { "242001", "Shahjahanpur" }, { "713216", "Bardhaman" } };
	}
}
