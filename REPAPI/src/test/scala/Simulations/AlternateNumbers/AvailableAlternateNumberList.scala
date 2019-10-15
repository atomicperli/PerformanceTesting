package Simulations.AlternateNumbers

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import CommonConfig.Constants

class AvailableAlternateNumberList extends Simulation {
  
/*
* This class contains Performance testing for Available Alternate Numbers API -
* REQUEST METHOD - GET
* REQUEST URL - https://<TEST_VENUE>/enterpriseportalapi/v1.0/customers/{customerId}/sites/{siteId}/features/alternatenumbers/available
*/
  
  // Requests
  val AvailableAlternateNumberList = http("Get the List of all Available Alternate numbers")
    .get("/customers/" + Constants.CustID + "/sites/" + Constants.SiteID + "/features/alternatenumbers/available")
    .check(status is 200)

  // Scenario
  val AvailableAlternateNumberListScenario = scenario("Get the List of all Available Alternate numbers")
    .exec(AvailableAlternateNumberList)

  // Setup
  setUp(
    AvailableAlternateNumberListScenario.inject(atOnceUsers(Constants.usercount)))
    .protocols(Constants.httpProtocol)
  
}
