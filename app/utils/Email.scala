package utils

import akka.io.Tcp.Message
import com.sendgrid
import java.io.IOException
import javax.inject.{Inject, Singleton}
import javax.security.auth.Subject
import play.api.libs.mailer._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class Email @Inject()(mailerClient: MailerClient, config: Config)(implicit ec: ExecutionContext) {

  def sendEmail(to: String, subject: String, message: String, messageHtml: String): Unit = {
    println(to)
      val email = Email(
        subject,
        s"The Titanic App <hello@titanicapp.co>",
        Seq(s"Hey <$to>"),
        // adds attachment
        attachments = Seq(
          // adds inline attachment from byte array
          // adds cid attachment
        ),
        // sends text, HTML or both...
        bodyText = Some(message),
        bodyHtml = Some(messageHtml)
      )
    try {
      println(mailerClient.send(email))
    } catch {
      case e: Exception => println(e)
    }
  }

  /*def send(email: Seq[String], subject: String, message: String, messageHtml: String) = {
    val send = Email(
      subject,
      "ping@takeouropinion.com",
      email,
      bodyText = Some(message),
      bodyHtml = Some(messageHtml))
    Future{
      if (config.isMailSmtpMode) {
        val msgId = mailerClient.send(send)
        println("Sent mail is", msgId)
      }
      else
        email.foreach(sendGrid(_,subject, message, messageHtml))
    }
  }*/

}
