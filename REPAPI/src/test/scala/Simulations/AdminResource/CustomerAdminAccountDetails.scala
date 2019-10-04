package Simulations.AdminResource

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import CommonConfig.Constants

class CustomerAdminAccountDetails extends Simulation {

/*
* This class contains Performance testing for Admin Resource API -
* REQUEST METHOD - GET
* REQUEST URL - https://<TEST_VENUE>/enterpriseportalapi/v1.0/customers/{customerId}/admin/accountdetails/{partyId}
*/
  
  // Requests
  val getCustomerAdminAccountDetails = http("Get the Customer Admin's Account details")
    .get("/customers/" + Constants.CustID + "/admin/accountdetails/" + Constants.CustomerAdminPartyID)
    .check(status is 200)
    .check(jsonPath("$..id").ofType[String])

  // Scenario
  val getCustomerAdminAccountDetailsScenario = scenario("Get the Customer Admin's Account details Scenario")
  .exec(getCustomerAdminAccountDetails)
          
  // Setup
  setUp(
    getCustomerAdminAccountDetailsScenario.inject(atOnceUsers(Constants.usercount)))
    .protocols(Constants.httpProtocol)
        
}