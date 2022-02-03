Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if Place is being succesfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" https request
	Then the API call got success with status code 200
	And "status" on response body is "OK"
	And "scope" on response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name |language|address|
	|Rahul|Hindi   |India  |
#	|DD   |Spanish |Spain  |
	
	@DeletePlace
	Scenario: Verify if Delete Place functionality is working
	
		Given DeletePlace Payload
		When user calls "deletePlaceAPI" with "POST" https request
		Then the APU call got success with status code 200
		And "status" in response body is "OK"