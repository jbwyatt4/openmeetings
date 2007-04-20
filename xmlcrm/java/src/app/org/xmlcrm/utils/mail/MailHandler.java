package org.xmlcrm.utils.mail;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

import javax.activation.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.basic.Configurationmanagement;

/**
 * 
 * @author swagner
 * 
 */
public class MailHandler {

	private static final Log log = LogFactory.getLog(MailHandler.class);

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
			String to = toEmail;
			// String from = "openmeetings@xmlcrm.org";
			String from = Configurationmanagement.getInstance().getConfKey(3,"system_email_addr").getConf_value();
			String subject = subj;
			String body = message;

			return send(smtpServer, to, from, subject, body);
		} catch (Exception ex) {
			log.error("[sendMail] " ,ex);
			return "Error: " + ex;
		}
	}

	/**
	 * Sending a mail with given values
	 * 
	 * @param smtpServer
	 * @param to
	 * @param from
	 * @param subject
	 * @param body
	 * @return
	 */
	public static String send(String smtpServer, String to, String from,
			String subject, String body) {
		try {

			System.out.println("Message sending in progress");

			Properties props = System.getProperties();

			// -- Attaching to default Session, or we could start a new one --

			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props,
					new SmtpAuthenticator());

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
			msg.setHeader("X-Mailer", "XMLShop Service-Mail");
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