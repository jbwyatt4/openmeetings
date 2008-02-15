package org.xmlcrm.app.data.conference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.templates.FeedbackTemplate;
import org.xmlcrm.utils.mail.MailHandler;

public class Feedbackmanagement {
	private static final Log log = LogFactory.getLog(Feedbackmanagement.class);

	private static Feedbackmanagement instance;

	private Feedbackmanagement() {}

	public static synchronized Feedbackmanagement getInstance() {
		if (instance == null) {
			instance = new Feedbackmanagement();
		}
		return instance;
	}
	
	public String sendFeedback (String username, String email, String message){
		try {
				
			String template = FeedbackTemplate.getInstance().getFeedBackTemplate(username, email, message);
		
			return MailHandler.sendMail("openmeetings-user@googlegroups.com", "Feedback for OpenMeetings", template);

		} catch (Exception err){
			log.error("sendInvitationLink",err);
		}
		return null;
	}
}
