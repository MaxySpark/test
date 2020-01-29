package utils

import javax.inject.{Inject, Provider, Singleton}
import play.api.{Application, Configuration}

import scala.concurrent.duration.{FiniteDuration, _}
import scala.language.postfixOps


@Singleton
class Config @Inject()(configuration: Configuration, app: Provider[Application]) {
  /*implicit def current():Application = {
    app.get()
  }*/
  
  implicit class ConfigStr(s: String) {
    def configOrElse(default: FiniteDuration): FiniteDuration =
      configuration.getMillis(s).minutes

    def configOrElse(default: Long): Long =
      configuration.getMillis(s) //.getOrElse(default)

    def configOrElse(default: Double)(implicit app: Application): Double =
      configuration.get[Double](s) //.getOrElse(default)
    //(implicit app: Application)
    def configOrElse(default: String): String =
      configuration.get[String](s) // .getOrElse(default)

    def configOrElse(default: Boolean): Boolean =
      configuration.get[Boolean](s) // .getOrElse(default)

    def configOrElse(default: Seq[String]): Seq[String]  =
      configuration.get[Seq[String]](s) //.getOrElse(default)
  }


  // ------Application Details

  /**
    * Json Web Token Account Verification Secret
    */
    // (implicit app: Application)
  // def jwtAVsecret: String = "application.jwtToken.accountVerification" configOrElse "sdfsdfef3233e2"

  // def jwtAuthenticationSecret: String = "application.jwtToken.authentication" configOrElse "sdfsdyfreaf3233e23d"
  // def jwtAdminAuthenticationSecret: String = "application.admin.jwtToken.authentication" configOrElse "sdfsdyfreaf3kkhkjhjkh233e23d"

  // def emailVerificationResend: String = "application.jwtToken.emailVerificationResend" configOrElse "sdssderfeyffErfef3d"

  def perOpinionFees:Double = 99.00
  def paytmMode: String =  "application.payment.paytm.mode" configOrElse "TEST"
  def paytmWebsite: String =  "application.payment.paytm.website" configOrElse "WEBSTAGING"
  def paytmMid: String =  "application.payment.paytm.mid" configOrElse "n/a"
  def paytmIndustryType: String =  "application.payment.paytm.industryType" configOrElse "Retail"
  def paytmMerchantKey: String = "application.payment.paytm.merchantKey" configOrElse ""

  def apiHost: String = "application.apiHost" configOrElse "localhost"
  def apiHostProtocol: String = "application.apiHostProtocol" configOrElse "http://"
  def apiHostPort: String = "application.apiHostPort" configOrElse "9000"

  def operatingEntity: String = "application.operatingEntity" configOrElse apiHost

  def website: String = "application.website" configOrElse("http://" concat apiHost)
  def atomTechReqHashKey = "application.payment.atomtech.reqHashKey" configOrElse("")
  def atomTechReqsponseHashKey = "application.payment.atomtech.responseHashKey" configOrElse("")
  def atomTechUserId = "application.payment.atomtech.userId" configOrElse("")
  def atomTechPassword = "application.payment.atomtech.password" configOrElse("")
  def atomTechProductId = "application.payment.atomtech.productId" configOrElse("")

  def croneInterval: FiniteDuration = "application.croneInterval" configOrElse(5 minutes)

  def admins: List[String] = ("application.admins" configOrElse "jainpiyush124@hotmail.com,ravinderpayalspcl@gmail.com,mayank91.agarwal@gmail.com").split(",").toList
  def getSendgridApiKey: String = "application.mail.sendgrid.apiKey" configOrElse("")
  def isMailSmtpMode: Boolean = "application.mail.smtp" configOrElse(false)


  // def openTokApiKey: Long = "application.openTok.apiKey" configOrElse 0
  // def openTokApiSecret: String = "application.openTok.apiSecret" configOrElse "not_available"
}
