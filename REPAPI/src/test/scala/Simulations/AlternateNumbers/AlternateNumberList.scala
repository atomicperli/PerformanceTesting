package Simulations.AlternateNumbers

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import CommonConfig.Constants

class AlternateNumberList extends Simulation {
  
/*
* This class contains Performance testing for Alternate Numbers API -
* REQUEST METHOD - GET
* REQUEST URL - https://<TEST_VENUE>/enterpriseportalapi/v1.0/customers/{customerId}/sites/{siteId}/features/alternatenumbers
*/
  
  // Requests
  val AlternateNumberList = http("Get the List of all Alternate numbers")
    .get("/customers/" + Constants.CustID + "/sites/" + Constants.SiteID + "/features/alternatenumbers")
    .check(status is 200)
    .check(jsonPath("$..total").ofType[Int])

  // Scenario
  val AlternateNumberListScenario = scenario("Get the List of all Alternate numbers scenario")
  .exec(AlternateNumberList)
          
  // Setup
  setUp(
    AlternateNumberListScenario.inject(atOnceUsers(Constants.usercount)))
    .protocols(Constants.httpProtocol)
        
}
