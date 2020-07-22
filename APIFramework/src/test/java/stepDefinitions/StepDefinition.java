package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import io.cucumber.java.en.And;
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
import pojo.GetMaps;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {  //inheritance - Utils is parent class
	
	RequestSpecification req;
	ResponseSpecification specres;
	Response response;
	TestDataBuild data= new TestDataBuild();
	static String place_id;  // refers to same variable and it wont set to null. Will be shared among test cases
	
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException{
		 
		 req= given().spec(requestSpecification())// use method name directly as it is inherited using extends
					.body(data.addPlacePayload(name, language, address));// objectname.methodname
	}
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {//From constructor in APIResources and AddPlaceAPI word will be stored in resource
	  
	APIResources resourceAPI =APIResources.valueOf(resource);//value gets loaded
	System.out.println(resourceAPI.getResource());
	
		specres= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		response= req.when().post(resourceAPI.getResource());
		
		else if(method.equalsIgnoreCase("GET"))
			response=req.when().get(resourceAPI.getResource());
			
	}

	@Then("Response successful with status code {int}")
	public void response_successful_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals(response.getStatusCode(),200);
	    
	}

	@Then("validate {string} in the response body is {string}")
	public void validate_in_the_response_body_is(String Valuekey, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals(getJsonPath(response,Valuekey).toString(),expectedValue);
	}
	
	@Then("verify place_id created matches with {string} using {string}")
	public void verify_place_id_created_matches_with_using(String expectedName, String resourcename) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		place_id =getJsonPath(response,"place_id");
		req= given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		user_calls_with_http_request(resourcename,"GET"); //reuse when method to call GET as well
		String name =getJsonPath(response,"name");
		
		assertEquals(expectedName,name);
		
	    
	}
	
	@Given("DeletePlace payload")
	public void deleteplace_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	  
     req=given().spec(requestSpecification()).body(data.deletePayload(place_id));
     
        }
}
