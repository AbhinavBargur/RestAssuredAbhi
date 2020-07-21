package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		StepDefinition sd = new StepDefinition();// Use hooks when you have to execute a method independently when it is dependent. And create object of step definition to access class.
		sd.add_place_payload_with("bargur", "Spanish", "North");
		sd.user_calls_with_http_request("AddPlaceAPI", "POST");
		sd.verify_place_id_created_matches_with_using("bargur", "GET");
	}

}
