package com.iss.satellite.testsatellites;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.iss.satellite.testutils.MyExcelUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TlesTest {
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://api.wheretheiss.at/v1";
	}

	@Test(dataProvider = "DataSet1")
	public void testMethod_DataSet1(Map<String, String> data) { // System.err.println(data);
		if ((data.get("SKIP")).equals("NO")) {
			Response response = given().contentType(ContentType.JSON).log().body().log().all().when()
					.get(data.get("Data")).then().log().all().extract().response();
			System.out.println(data.get("Test_Case_Name"));
			System.out.println("Debug 1:" + response.asString());
			int responseCode = Integer.parseInt(data.get("Http_Response_Code"));
			Assert.assertEquals(response.statusCode(), responseCode);
			Assert.assertEquals(response.asString().contains(data.get("Expected_Data")), true);
		}
	}

	@DataProvider(name = "DataSet1")
	public Object[][] test_DataSet1() throws Exception {
		Object[][] testData = MyExcelUtil.readData("Test_Data", "TlesTest");
		return testData;
	}

	@Test(dataProvider = "DataSet2")
	public void testMethod_DataSet2(Map<String, String> data) { 
		System.err.println(data); 
		Response response =
		given().contentType(ContentType.JSON).log().body().log().all().when().get(data.get("Data")).then().log().all()
				.extract().response();
		System.out.println(data.get("Test_Case_Name"));
		System.out.println("Response Body:" + response.asString());
		int responseCode = Integer.parseInt(data.get("Http_Response_Code"));
		Assert.assertEquals(response.statusCode(), responseCode);
		Assert.assertEquals(response.contentType(), (data.get("Expected_Data")));
		//Assert.assertEquals(response.asString().contains(data.get("Http_Response_Message")), true);
	}

	@DataProvider(name = "DataSet2")
	public Object[][] test_DataSet2() throws Exception {
		Object[][] results = new Object[1][1];
		Map<String, String> interimResult = MyExcelUtil.readDataWithTestCase("Test_Data",
				"Get_Satellite_Positions_Valid_TLES_JSON");
		results[0] = new Object[] { interimResult };
		return results;
	}

	@Test(dataProvider = "DataSet3")
	public void testMethod_DataSet3(Map<String, String> data) {
		// System.err.println(data);
		Response response = given().contentType(ContentType.JSON).log().body().log().all().when().get(data.get("Data"))
				.then().log().all().extract().response();
		System.out.println(data.get("Test_Case_Name"));
		System.out.println("Response Body:" + response.asString());
		int responseCode = Integer.parseInt(data.get("Http_Response_Code"));
		Assert.assertEquals(response.statusCode(), responseCode);
		Assert.assertEquals(response.contentType(), (data.get("Expected_Data")));
	}

	@DataProvider(name = "DataSet3")
	public Object[][] test_DataSet3() throws Exception {
		Object[][] results = new Object[1][1];
		Map<String, String> interimResult = MyExcelUtil.readDataWithTestCase("Test_Data",
				"Get_Satellite_Positions_Valid_TLES_Text");
		results[0] = new Object[] { interimResult };
		return results;
	}

}
