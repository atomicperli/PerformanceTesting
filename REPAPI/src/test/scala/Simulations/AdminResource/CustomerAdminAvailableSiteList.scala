package Simulations.AdminResource

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import CommonConfig.Constants

class CustomerAdminAvailableSiteList extends Simulation{

/*
* This class contains Performance testing for Admin Resource API -
* REQUEST METHOD - GET
* REQUEST URL - https://<TEST_VENUE>/enterpriseportalapi/v1.0/customers/{customerId}/admin/sitelist
*/
  
  // Requests
  val getCustomerAdminAvailableSiteListt = http("Get the Customer Admin's Available Site List")
    .get("/customers/" + Constants.CustID + "/admin/sitelist/" + Constants.CustomerAdminPartyID)
    .check(status is 200)
    .check(jsonPath("$..total").ofType[Int])

  // Scenario
  val getCustomerAdminAvailableSiteListScenario = scenario("Get the Customer Admin's Available Site List Scenario")
  .exec(getCustomerAdminAvailableSiteListt)
          
  // Setup
  setUp(
    getCustomerAdminAvailableSiteListScenario.inject(atOnceUsers(Constants.usercount)))
    .protocols(Constants.httpProtocol)
        
}