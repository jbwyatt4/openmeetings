package org.xmlcrm.app.remote;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.red5.server.api.service.IPendingServiceCall;
import org.red5.server.api.service.IPendingServiceCallback;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.conference.Invitationmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.hibernate.beans.invitation.Invitations;
import org.xmlcrm.utils.math.CalendarPatterns;

public class InvitationService implements IPendingServiceCallback {
	
	private static final Log log = LogFactory.getLog(InvitationService.class);

	public void resultReceived(IPendingServiceCall arg0) {
		// TODO Auto-generated method stub
		log.debug("InvitationService resultReceived"+arg0);
	}

    /**
     * send an invitation to another user by Mail
     * @param SID
     * @param username
     * @param message
     * @param baseurl
     * @param email
     * @param subject
     * @param room_id
     * @param conferencedomain
     * @param isPasswordProtected
     * @param invitationpass
     * @param valid
     * @param validFromDate
     * @param validFromTime
     * @param validToDate
     * @param validToTime
     * @return
     */
	public String sendInvitationHash(String SID, String username, String message, 
			String baseurl, String email, String subject, Long room_id,String conferencedomain, 
    		Boolean isPasswordProtected, String invitationpass, Integer valid, 
    		Date validFromDate, String validFromTime, Date validToDate, String validToTime){
    	
    	log.debug("sendInvitationHash: ");
    	
    	Integer validFromHour = Integer.valueOf(validFromTime.substring(0, 2)).intValue();
    	Integer validFromMinute = Integer.valueOf(validFromTime.substring(3, 5)).intValue();
    	
    	Integer validToHour = Integer.valueOf(validToTime.substring(0, 2)).intValue();
    	Integer validToMinute = Integer.valueOf(validToTime.substring(3, 5)).intValue();
    	
    	log.info("validFromHour: "+validFromHour);
    	log.info("validFromMinute: "+validFromMinute);
    	
    	//TODO: Remove deprecated Java-Date handlers
    	Calendar calFrom = Calendar.getInstance();
    	int year = validFromDate.getYear()+1900;
    	int month = validFromDate.getMonth();
    	int date = validFromDate.getDate();
    	calFrom.set(year, month, date, validFromHour, validFromMinute, 0);
		
		
		Calendar calTo= Calendar.getInstance();
    	int yearTo = validToDate.getYear()+1900;
    	int monthTo = validToDate.getMonth();
    	int dateTo = validToDate.getDate();
    	calTo.set(yearTo, monthTo, dateTo, validToHour, validToMinute, 0);
    	
    	Date dFrom = calFrom.getTime();
    	Date dTo = calTo.getTime();
    	
    	log.info("validFromDate: "+CalendarPatterns.getDateWithTimeByMiliSeconds(dFrom));
    	log.info("validToDate: "+CalendarPatterns.getDateWithTimeByMiliSeconds(validToDate));
    	
    	Long users_id = Sessionmanagement.getInstance().checkSession(SID);
    	Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
    	
    	return Invitationmanagement.getInstance().addInvitationLink(user_level, username, message, 
    			baseurl, email, subject, room_id, conferencedomain,
    			isPasswordProtected, invitationpass, 
    			valid, dFrom, dTo, users_id);
    	//return Invitationmanagement.getInstance().sendInvitionLink(user_level, username, message, domain, room, roomtype, baseurl, email, subject, room_id);
    }    
    
	public Object getInvitationByHash(String hashCode) {
		return Invitationmanagement.getInstance().getInvitationByHashCode(hashCode,true);
	}
	
	public Object checkInvitationPass(String hashCode, String pass) {
		return Invitationmanagement.getInstance().checkInvitationPass(hashCode,pass);
	}
}
