package org.xmlcrm.utils.mail;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

/**
  * A simple email sender class.
  */
public class MailHandler
{
	public MailHandler(){
		
	}
  /**
    * method to send a message
    */
  public String sendMail(String toEmail,String subj, String message) {
	  String retWert = "";
    try
    {
      String smtpServer="smtp.laszlo-forum.de";
      String to=toEmail;
      String from="admin@laszlo-forum.de";
      String subject=subj;
      String body=message;
      
      retWert = send(smtpServer, to, from, subject, body);
    }
    catch (Exception ex)
    {
    	retWert = "Error: "+ex;
    }
    return retWert;

  }
  public static String send(String smtpServer, String to, String from, String subject, String body)  {
	  String retWert = "success";
    try {
    	System.out.println("Message sending in progress");
      Properties props = System.getProperties();

      // -- Attaching to default Session, or we could start a new one --

      props.put("mail.smtp.host", smtpServer);
      Session session = Session.getDefaultInstance(props, null);

      // -- Create a new message --
      Message msg = new MimeMessage(session);

      // -- Set the FROM and TO fields --
      msg.setFrom(new InternetAddress(from));
      msg.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(to, false));

      // -- We could include CC recipients too --
      // if (cc != null)
      // msg.setRecipients(Message.RecipientType.CC
      // ,InternetAddress.parse(cc, false));

      // -- Set the subject and body text --
      msg.setSubject(subject);
      msg.setContent(body, "text/html");

      // -- Set some other header information --
      msg.setHeader("X-Mailer", "XMLShop Service-Mail");
      msg.setSentDate(new Date());

      // -- Send the message --
      Transport.send(msg);

      
    } catch (Exception ex) {
    	retWert = "Error"+ex;
    	System.out.println("Error"+ex);
    }
    return retWert;
  }
}  