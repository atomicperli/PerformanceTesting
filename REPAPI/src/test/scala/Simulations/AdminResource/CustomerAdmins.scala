package Simulations.AdminResource

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import CommonConfig.Constants

class CustomerAdmins extends Simulation{
  
/*
* This class contains Performance testing for Admin Resource API -
* REQUEST METHOD - GET
* REQUEST URL - https://<TEST_VENUE>/enterpriseportalapi/v1.0/customers/{customerId}/admin
*/
  
  // Requests
  val getCustomerAdmins = http("Get the List of all Customer Admins")
    .get("/customers/" + Constants.CustID + "/admin")
    .check(status is 200)
    .check(jsonPath("$..total").ofType[Int])

  // Scenario
  val getCustomerAdminsScenario = scenario("Get the List of all Customer Admins Scenario")
  .exec(getCustomerAdmins)
          
  // Setup
  setUp(
    getCustomerAdminsScenario.inject(atOnceUsers(Constants.usercount)))
    .protocols(Constants.httpProtocol)
        
}