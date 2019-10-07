package Simulations.BargeIn

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import CommonConfig.Constants

class BargeInListByCCUserID extends Simulation{
  
/*
* This class contains Performance testing for BargeIn API -
* REQUEST METHOD - GET
* REQUEST URL - https://<TEST_VENUE>/enterpriseportalapi/v1.0/customers/{customerId}/sites/{siteId}/features/bargein/{ccUserId}
*/
  
  // Requests
  val getBargeInListByCCUserID = http("Get the BargeIn Details for the User")
    .get("/customers/" + Constants.CustID + "/sites/" + Constants.SiteID + "/features/bargein/" + Constants.CCUserID)
    .check(status is 200)
    .check(bodyBytes.exists)

  // Scenario
  val getBargeInListByCCUserIDScenario = scenario("Get the BargeIn Details for the User Scenario")
  .exec(getBargeInListByCCUserID)
          
  // Setup
  setUp(
    getBargeInListByCCUserIDScenario.inject(atOnceUsers(Constants.usercount)))
    .protocols(Constants.httpProtocol)
        
}
