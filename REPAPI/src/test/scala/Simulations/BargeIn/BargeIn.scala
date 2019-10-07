package Simulations.BargeIn

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import CommonConfig.Constants

class BargeIn extends Simulation{
  
/*
* This class contains Performance testing for BargeIn API -
* REQUEST METHOD - GET
* REQUEST URL - https://<TEST_VENUE>/enterpriseportalapi/v1.0/customers/{customerId}/sites/{siteId}/features/bargein
*/
  
  // Requests
  val getBargeIn = http("Get the Users with BargeIn Feature")
    .get("/customers/" + Constants.CustID + "/sites/" + Constants.SiteID + "/features/bargein")
    .check(status is 200)
    .check(bodyBytes.exists)

  // Scenario
  val getBargeInScenario = scenario("Get the Users with BargeIn Feature Scenario")
  .exec(getBargeIn)
          
  // Setup
  setUp(
    getBargeInScenario.inject(atOnceUsers(Constants.usercount)))
    .protocols(Constants.httpProtocol)
        
}
