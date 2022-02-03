package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import Pojo.AddPlace;
import Pojo.Location;
import Resources.APIResources;
import Resources.testDataBuild;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition extends Utils 

{

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	testDataBuild data = new testDataBuild();
	JsonPath js;
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
		
		
		//Response res = given().log().all().queryParam("key", "qaclick123")
		 
		 
		 res = given().spec(requestSpecification())
		 .body(data.addPlacePayLoad(name,language,address));
		
	}

	@When("user calls {string} with {string} https request")
	public void user_calls_with_Post_https_request(String resource,String method) {
		//constructor will be called with the value of resource which you pass
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		//response = res.when().post("/maps/api/place/add/json").
		
		if(method.equalsIgnoreCase("POST"))
		
		response = res.when().post(resourceAPI.getResource());
		
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
				// then().spec(resspec).extract().response();
		
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	  assertEquals( response.getStatusCode(),200);
	 System.out.println(response.getStatusCode());
	}

	@Then("{string} on response body is {string}")
	public void on_response_body_is(String key, String Expectedvalue) {
		
		//String respon = response.asString();
		//String resp=response.asString();
		//JsonPath js=new JsonPath(respon);
		assertEquals(getJsonPath(response,key),Expectedvalue);
		
		
		//System.out.println(respon);
	}


@Then("verify place_id created maps to {string} using {string}")
public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
    // Write code here that turns the phrase above into concrete actions
    
	//requestPec
	
	place_id = getJsonPath(response,"place_id");
	res = given().spec(requestSpecification().queryParam("place_id", place_id));
	user_calls_with_Post_https_request(resource,"GET");
	String actualName =getJsonPath(response,"name");
	assertEquals(actualName,expectedName);
}

//Second scenario

@Given("DeletePlace Payload")
public void deleteplace_payload() throws IOException {
    // Write code here that turns the phrase above into concrete actions
    
    
   res = given().spec(requestSpecification()).body(data.deletePlacePlayLoad(place_id));
}

@Then("the APU call got success with status code {int}")
public void the_APU_call_got_success_with_status_code(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    
  
}

@Then("{string} in response body is {string}")
public void in_response_body_is(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
  
}


}
