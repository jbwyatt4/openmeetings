package org.openmeetings.utils.mail;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

import javax.activation.*;

import org.slf4j.Logger;
import org.red5.logging.Red5LoggerFactory;
import org.openmeetings.app.data.basic.Configurationmanagement;

/**
 * 
 * @author swagner
 * 
 */
public class MailHandler {

	private static final Logger log = Red5LoggerFactory.getLogger(MailHandler.class, "openmeetings");

	public MailHandler() {
	}

	/**
	 * send mail to address
	 * 
	 * @param toEmail
	 * @param subj
	 * @param message
	 * @return
	 */
	public static String sendMail(String toEmail, String subj, String message) {
		try {

			// String smtpServer="smtp.xmlcrm.org";
			String smtpServer = Configurationmanagement.getInstance().getConfKey(3, "smtp_server").getConf_value();
			String smtpPort = Configurationmanagement.getInstance().getConfKey(3, "smtp_port").getConf_value();
			String to = toEmail;
			// String from = "openmeetings@xmlcrm.org";
			String from = Configurationmanagement.getInstance().getConfKey(3,"system_email_addr").getConf_value();
			String subject = subj;
			String body = message;
			
			String emailUsername = Configurationmanagement.getInstance().getConfKey(3, "email_username").getConf_value();
			String emailUserpass = Configurationmanagement.getInstance().getConfKey(3, "email_userpass").getConf_value();

			//return send(smtpServer, smtpPort, to, from, subject, body);
			return send(smtpServer, smtpPort, to, from, subject, body, emailUsername, emailUserpass);
		} catch (Exception ex) {
			log.error("[sendMail] " ,ex);
			return "Error: " + ex;
		}
	}

//	/**
//	 * Sending a mail with given values
//	 * 
//	 * @param smtpServer
//	 * @param to
//	 * @param from
//	 * @param subject
//	 * @param body
//	 * @return
//	 */
//	public static String send(String smtpServer, String smtpPort, String to, String from,
//			String subject, String body) {
//		try {
//
//			log.debug("Message sending in progress");
//
//			Properties props = System.getProperties();
//
//			// -- Attaching to default Session, or we could start a new one --
//			//smtpPort 25 or 587
//			props.put("mail.smtp.host", smtpServer);
//			props.put("mail.smtp.port", smtpPort);
//			props.put("mail.smtp.auth", "true");
//
//			Session session = Session.getDefaultInstance(props,
//					new SmtpAuthenticator());
//
//			// -- Create a new message --
//			Message msg = new MimeMessage(session);
//
//			// -- Set the FROM and TO fields --
//			msg.setFrom(new InternetAddress(from));
//			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
//					to, false));
//
//			// -- We could include CC recipients too --
//			// if (cc != null)
//			// msg.setRecipients(Message.RecipientType.CC
//			// ,InternetAddress.parse(cc, false));
//
//			// -- Set the subject and body text --
//			msg.setSubject(subject);
//			msg.setDataHandler(new DataHandler(new ByteArrayDataSource(body
//					.toString(), "text/html")));
//			// msg.setContent(body, "text/html");
//
//			// -- Set some other header information --
//			msg.setHeader("X-Mailer", "XML-Mail");
//			msg.setSentDate(new Date());
//
//			// -- Send the message --
//			Transport.send(msg);
//
//			return "success";
//		} catch (Exception ex) {
//			log.error("[mail send] " ,ex);
//			return "Error" + ex;
//		}
//	}
	
	/**
	 * Sending a mail with given values.<br>
	 * If the parameter "emailUsername" and "emailUserpass" is exist, use SMTP Authentication.
	 * 
	 * @param smtpServer
	 * @param to
	 * @param from
	 * @param subject
	 * @param body
	 * @param emailUsername
	 * @param emailUserpass
	 * @return
	 */
	public static String send(String smtpServer, String smtpPort, String to, String from,
			String subject, String body, String emailUsername, String emailUserpass) {
		try {

			log.debug("Message sending in progress");

			Properties props = System.getProperties();

			// -- Attaching to default Session, or we could start a new one --
			//smtpPort 25 or 587
			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.port", smtpPort);

			Session session = null;
			if (emailUsername != null && emailUsername.length() > 0
					&& emailUserpass != null && emailUserpass.length() > 0) {
				//use SMTP Authentication
				props.put("mail.smtp.auth", "true");
				session = Session.getDefaultInstance(props,
						new SmtpAuthenticator());
			}else{
				//not use SMTP Authentication
				session = Session.getDefaultInstance(props, null);
			}


			// -- Create a new message --
			Message msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
					to, false));

			// -- We could include CC recipients too --
			// if (cc != null)
			// msg.setRecipients(Message.RecipientType.CC
			// ,InternetAddress.parse(cc, false));

			// -- Set the subject and body text --
			msg.setSubject(subject);
			msg.setDataHandler(new DataHandler(new ByteArrayDataSource(body
					.toString(), "text/html")));
			// msg.setContent(body, "text/html");

			// -- Set some other header information --
			msg.setHeader("X-Mailer", "XML-Mail");
			msg.setSentDate(new Date());

			// -- Send the message --
			Transport.send(msg);

			return "success";
		} catch (Exception ex) {
			log.error("[mail send] " ,ex);
			return "Error" + ex;
		}
	}
	
}
