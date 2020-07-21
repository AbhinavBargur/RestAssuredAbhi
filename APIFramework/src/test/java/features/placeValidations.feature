Feature: Validating Place APIs


@AddPlace
Scenario Outline: Verify if place is added successfully into AddPlaceAPI
     Given Add place payload with "<name>" "<language>" "<address>"
     When User calls "AddPlaceAPI" with "POST" http request
     Then Response successful with status code 200
     And validate "status" in the response body is "OK"
     And validate "scope" in the response body is "APP"
     And verify place_id created matches with "<name>" using "GetPlaceAPI"
     
    
Examples:
	|name	 	 |language|address			 |
	|NewHouse	 |English |World cross center|
#	|Second House|Spanish |Sea Cross		 |

@DeletePlace
Scenario: Verify delete place

Given DeletePlace payload
When User calls "DeletePlaceAPI" with "POST" http request
Then Response successful with status code 200
And validate "status" in the response body is "OK"
