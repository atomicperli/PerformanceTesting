package CommonConfig

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

object Constants {

  //Select Data
  val TestModel = System.getProperty("TestModel", "Micro")
  val usercount = Integer.getInteger("numberOfUsers", 10).toInt

  //choose customer username
  def UsernameModel(TestModel: Any): Any = TestModel match {
    case "Micro" => "AutoMicroCustomer2-PerformanceTesting"
    case "MediumB" => "AutoMediumCustomer1-PerformanceTesting"
    case "MediumA" => "AutoLargeCustomer3SingleSite-PerformanceTesting"
    case "Small" => "AutoSmallCustomer2-PerformanceTesting"
    case "LargeFlex" => "573d3504-6669-4dc1-b695-0be860e2f2d4"
    case _ => "RialtoAutomationCustomer"
  }
  //choose customer ID
  def CustIDModel(TestModel: Any): Any = TestModel match {
    case "Micro" => "578473"
    case "MediumB" => "572901"
    case "MediumA" => "579915"
    case "Small" => "578510"
    case "LargeFlex" => "599945"
    case _ => "32760"
  }
  //choose customer Specific site ID
  def SiteIDModel(TestModel: Any): Any = TestModel match {
    case "Micro" => "578474"
    case "MediumB" => "588689"
    case "MediumA" => "579916"
    case "Small" => "578563"
    case "LargeFlex" => "600164"
    case _ => "34249"
  }
  //choose customer Specific site ID
  def PasswordModel(TestModel: Any): Any = TestModel match {
    case "Micro" => "Cisco!234567"
    case "MediumB" => "Cisco!234567"
    case "MediumA" => "Cisco!234567"
    case "Small" => "Cisco!2345678"
    case "LargeFlex" => "Cisco!2345"
    case _ => "Broad!2345"
  }
  
  def UserIDModel(TestModel: Any): Any = TestModel match {
    case "Micro" => "zf07xdcafz@sboxqa.adpt-tech.com"
    case "Small" => "xft5wrclv0@sboxqa.adpt-tech.com"
    case "MediumA" => "absh9ks9ub@sboxqa.adpt-tech.com"
    case "MediumB" => "t0no52fpqb@sboxqa.adpt-tech.com"
    case "LargeFlex" => "cew40yww8l@sboxqa.adpt-tech.com"
    case _ => "2514606019@sboxqa.adpt-tech.com"
  }

  //Data
  val Username = UsernameModel(TestModel).toString()
  val Password = PasswordModel(TestModel).toString()
  val CustID = CustIDModel(TestModel).toString()
  val SiteID = SiteIDModel(TestModel).toString()
  val CCUserID = UserIDModel(TestModel).toString()

  //  Configs
  
  val httpProtocol = http
    .baseUrl("https://proxy-qa.broadcloudpbx.net/enterpriseportalapi/v1.0")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
    }
