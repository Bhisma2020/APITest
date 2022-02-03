package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeSceanrio() throws IOException
	{
		//write a code that will give you place id
		//execute this code only when place id is null
		
		StepDefinition m = new StepDefinition();
		
		if (StepDefinition.place_id==null)
		{
		m.add_Place_Payload_with("Shetty", "French", "Asia");
		m.user_calls_with_Post_https_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");

		}
		}
}
