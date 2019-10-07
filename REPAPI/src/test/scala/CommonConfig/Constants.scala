package CommonConfig

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import io.gatling.core.protocol.Protocol
import com.typesafe.config.ConfigFactory
import scala.io.Source.fromURL

object Constants {

  //Select Data
  val TestModel = System.getProperty("TestModel", "Micro")
  val usercount = Integer.getInteger("numberOfUsers", 1).toInt
  val envtype = System.getProperty("envType", "QA")

  val CustomerType = System.getProperty("CustomerType", "NonFlex")
  val Token_Value = "Mjk0ZDI0ZDYtMDFkNi00ZDcyLWJmNmItNzViMTQyMWY1NjNhM2EyMWE3YzgtNGNk_A52D_dfbc2a5a-8552-4903-bcca-5dec35af54d2"
  val Bearertoken = "Bearer " + Token_Value
  
  val UsernameBuilder = (TestModel.concat(".").concat("Username.value")).toString()
  val PasswordBuilder = (TestModel.concat(".").concat("Password.value")).toString()
  val CustIDBuilder = (TestModel.concat(".").concat("CustID.value")).toString()
  val SiteIDBuilder = (TestModel.concat(".").concat("SiteID.value")).toString()
  val CCUserIDBuilder = (TestModel.concat(".").concat("CCUserID.value")).toString()
  val FirstNameBuilder = (TestModel.concat(".").concat("FirstName.value")).toString()
  val OrderIDBuilder = (TestModel.concat(".").concat("OrderID.value")).toString()
  val CustomerAdminPartyIDBuilder = (TestModel.concat(".").concat("CustomerAdminPartyID.value")).toString()
  
  val Username = ConfigFactory.load(envtype).getString(UsernameBuilder)
  val Password = ConfigFactory.load(envtype).getString(PasswordBuilder)
  val CustID = ConfigFactory.load(envtype).getString(CustIDBuilder)
  val SiteID = ConfigFactory.load(envtype).getString(SiteIDBuilder)
  val FirstName = ConfigFactory.load(envtype).getString(FirstNameBuilder)
  val CCUserID = ConfigFactory.load(envtype).getString(CCUserIDBuilder)
  val OrderID = ConfigFactory.load(envtype).getString(OrderIDBuilder)
  val CustomerAdminPartyID = ConfigFactory.load(envtype).getString(CustomerAdminPartyIDBuilder)
  val httpProtocol = envtype match {
      case "QA" => CustomerType match 
      {
       case "Flex" => http
      .baseUrl("https://flexqa.broadcloudpbx.net/enterpriseportalapi/v1.0")
      .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
      .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
      .headers(Map("Content-Type" -> "application/json", "Authorization" -> Bearertoken))
      .disableWarmUp
      
            
      case "NonFlex" => http
      .baseUrl("https://proxy-qa.broadcloudpbx.net/enterpriseportalapi/v1.0")
      .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
      .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
      .basicAuth(Username, Password)
      .disableWarmUp
      }
    case "INT" => CustomerType match 
      {
       case "Flex" => http
      .baseUrl("https://flexqa.broadcloudpbx.net/enterpriseportalapi/v1.0")
      .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
      .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
      .headers(Map("Content-Type" -> "application/json", "Authorization" -> Bearertoken))
      .disableWarmUp
      
            
      case "NonFlex" => http
      .baseUrl("https://proxy-qa.broadcloudpbx.net/enterpriseportalapi/v1.0")
      .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
      .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
      .basicAuth(Username, Password)
      .disableWarmUp
    }

    }
   
//  //choose customer username
//  def UsernameModel(TestModel: Any): Any = TestModel match {
//    case "Micro" => "AutoMicroCustomer2-PerformanceTesting"
//    case "MediumB" => "AutoMediumCustomer1-PerformanceTesting"
//    case "MediumA" => "AutoLargeCustomer3SingleSite-PerformanceTesting"
//    case "SmallA" => "AutoSmallCustomer2-PerformanceTesting"
//    case "LargeA" => "AutoLargeCustomerA-PerformanceTesting"
//    case "LargeFlex" => "573d3504-6669-4dc1-b695-0be860e2f2d4"
//    case _ => "RialtoAutomationCustomer"
//  }
//  //choose customer ID
//  def CustIDModel(TestModel: Any): Any = TestModel match {
//    case "Micro" => "578473"
//    case "MediumB" => "572901"
//    case "MediumA" => "579915"
//    case "SmallA" => "578510"
//    case "LargeA" => "597131"
//    case "LargeFlex" => "599945"
//    case _ => "32760"
//  }
//  //choose customer Specific site ID
//  def SiteIDModel(TestModel: Any): Any = TestModel match {
//    case "Micro" => "578474"
//    case "MediumB" => "588689"
//    case "MediumA" => "579916"
//    case "SmallA" => "578563"
//    case "LargeA" => "597132"
//    case "LargeFlex" => "600164"
//    case _ => "34249"
//  }
//  //choose customer Specific site ID
//  def PasswordModel(TestModel: Any): Any = TestModel match {
//    case "Micro" => "Cisco!234567"
//    case "MediumB" => "Cisco!234567"
//    case "MediumA" => "Cisco!234567"
//    case "SmallA" => "Cisco!2345678"
//    case "LargeA" => "Cisco!23456"
//    case "LargeFlex" => "Cisco!2345"
//    case _ => "Broad!2345"
//  }
//
//  def UserIDModel(TestModel: Any): Any = TestModel match {
//    case "Micro" => "zf07xdcafz@sboxqa.adpt-tech.com"
//    case "SmallA" => "xft5wrclv0@sboxqa.adpt-tech.com"
//    case "MediumA" => "absh9ks9ub@sboxqa.adpt-tech.com"
//    case "MediumB" => "ai97hulh9p@sboxqa.adpt-tech.com"
//    case "LargeA" => "rhabmf6azz@sboxqa.adpt-tech.com"
//    case "LargeFlex" => "cew40yww8l@sboxqa.adpt-tech.com"
//    case _ => "2514606019@sboxqa.adpt-tech.com"
//  }
// 
//  //choose first name
//  def FirstNameModel(TestModel: Any): Any = TestModel match {
//    case "Micro" => "Micro"
//    case "MediumB" => "L3SS1"
//    case "MediumA" => "L3SS1User"
//    case "SmallA" => "Small2Site"
//    case "LargeA" => "L3SS1"
//    case "LargeFlex" => "WS"
//    case _ => "Automa"
//  }
//  
//    //choose OrderID
//  def OrderIDModel(TestModel: Any): Any = TestModel match {
//    case "Micro" => "WS236536"
//    case "MediumB" => "WS241053"
//    case "MediumA" => "WS236974"
//    case "SmallA" => "WS236566"
//    case "LargeA" => "WS244880"
//    case "LargeFlex" => "WS246172"
//    case _ => "Automa"
//  }
//  
//      //choose OrderID
//  def CustomerAdminPartyIDModel(TestModel: Any): Any = TestModel match {
//    case "Micro" => "618947"
//    case "MediumB" => "618938"
//    case "MediumA" => "618933"
//    case "SmallA" => "618950"
//    case "LargeA" => "618949"
//    case _ => "Automa"
//  }
//  
//    //choose SiteName
//    def SiteNameModel(TestModel: Any): Any = TestModel match {
//    case "Micro" => "AutoMicroCust2Site1HPBX-PFT"
//    case "MediumB" => "AutoMicroCust2Site1HPBX-PFT"
//    case "MediumA" => "AutoMicroCust2Site1HPBX-PFT"
//    case "SmallA" => "AutoMicroCust2Site1HPBX-PFT"
//    case "LargeA" => "AutoMicroCust2Site1HPBX-PFT"
//    case _ => "Automa"
//  }

  //Data
//  val Username = UsernameModel(TestModel).toString()
//  val Password = PasswordModel(TestModel).toString()
//  val CustID = CustIDModel(TestModel).toString()
//  val SiteID = SiteIDModel(TestModel).toString()
//  val CCUserID = UserIDModel(TestModel).toString()
//  val FirstName = FirstNameModel(TestModel).toString()
//  val OrderID = OrderIDModel(TestModel).toString()
//  val CustomerAdminPartyID = CustomerAdminPartyIDModel(TestModel).toString()
//  val SiteName = SiteNameModel(TestModel).toString()
    
}
