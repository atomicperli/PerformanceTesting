package Simulations.AdminResource

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import CommonConfig.Constants

class CustomerAdminMainContact extends Simulation{
 
/*
* This class contains Performance testing for Admin Resource API -
* REQUEST METHOD - GET
* REQUEST URL - https://<TEST_VENUE>/enterpriseportalapi/v1.0/customers/{customerId}/admin/maincontact/{partyId}
*/
  
  // Requests
  val getCustomerAdminMainContact = http("Get the Customer Admin's Main Contact")
    .get("/customers/" + Constants.CustID + "/admin/maincontact/" + Constants.CustomerAdminPartyID)
    .check(status is 200)
    .check(jsonPath("$..firstName").ofType[String])

  // Scenario
  val getCustomerAdminMainContactScenario = scenario("Get the Customer Admin's Main Contact Scenario")
  .exec(getCustomerAdminMainContact)
          
  // Setup
  setUp(
    getCustomerAdminMainContactScenario.inject(atOnceUsers(Constants.usercount)))
    .protocols(Constants.httpProtocol)
        
}