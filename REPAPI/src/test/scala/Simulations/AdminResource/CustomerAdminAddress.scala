package Simulations.AdminResource

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import CommonConfig.Constants
import com.typesafe.config.ConfigFactory

class CustomerAdminAddress extends Simulation{

/*
* This class contains Performance testing for Admin Resource API -
* REQUEST METHOD - GET
* REQUEST URL - https://<TEST_VENUE>/enterpriseportalapi/v1.0/customers/{customerId}/admin/address/{partyId}
*/

  
  // Requests
  val getCustomerAdminAddress = http("Get the Customer Admin's Address")
    .get("/customers/" + Constants.CustID + "/admin/address/" + Constants.CustomerAdminPartyID)
    .check(status is 200)
    .check(jsonPath("$..address1").ofType[String])

  // Scenario
  val getCustomerAdminAddressScenario = scenario("Get the Customer Admin's Address Scenario")
  .exec(getCustomerAdminAddress)
          
  // Setup
  setUp(
    getCustomerAdminAddressScenario.inject(atOnceUsers(Constants.usercount)))
    .protocols(Constants.httpProtocol)
        
}