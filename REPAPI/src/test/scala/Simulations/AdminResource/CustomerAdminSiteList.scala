package Simulations.AdminResource

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import CommonConfig.Constants

class CustomerAdminSiteList extends Simulation{
  
/*
* This class contains Performance testing for Admin Resource API -
* REQUEST METHOD - GET
* REQUEST URL - https://<TEST_VENUE>/enterpriseportalapi/v1.0/customers/{customerId}/admin/sitelist/{partyId}
*/
  
  // Requests
  val getCustomerAdminSiteList = http("Get the Customer Admin's Site List")
    .get("/customers/" + Constants.CustID + "/admin/sitelist")
    .check(status is 200)
    .check(bodyBytes.exists)

  // Scenario
  val getCustomerAdminSiteListScenario = scenario("Get the Customer Admin's Site List Scenario")
  .exec(getCustomerAdminSiteList)
          
  // Setup
  setUp(
    getCustomerAdminSiteListScenario.inject(atOnceUsers(Constants.usercount)))
    .protocols(Constants.httpProtocol)
        
}