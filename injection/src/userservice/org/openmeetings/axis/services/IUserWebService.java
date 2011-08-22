package org.openmeetings.axis.services;

import org.apache.axis2.AxisFault;
import org.openmeetings.app.data.beans.basic.ErrorResult;
import org.openmeetings.app.data.beans.basic.SearchResult;
import org.openmeetings.app.persistence.beans.basic.Sessiondata;

public interface IUserWebService {

	/**
	 * load this session id before doing anything else
	 * 
	 * @return Sessiondata-Object
	 */
	public abstract Sessiondata getSession();

	/**
	 * auth function, use the SID you get by getSession
	 * 
	 * @param SID
	 * @param Username
	 * @param Userpass
	 * @return positive means Loggedin, if negativ its an ErrorCode, you have to
	 *         invoke the Method getErrorByCode to get the Text-Description of
	 *         that ErrorCode
	 */
	public abstract Long loginUser(String SID, String username, String userpass);

	/**
	 * Gets the Error-Object
	 * 
	 * @param SID
	 * @param errorid
	 * @param language_id
	 * @return
	 */
	public abstract ErrorResult getErrorByCode(String SID, Long errorid,
			Long language_id);

	public abstract Long addNewUser(String SID, String username,
			String userpass, String lastname, String firstname, String email,
			String additionalname, String street, String zip, String fax,
			long states_id, String town, long language_id, String baseURL)
			throws AxisFault;

	public abstract Long addNewUserWithTimeZone(String SID, String username,
			String userpass, String lastname, String firstname, String email,
			String additionalname, String street, String zip, String fax,
			long states_id, String town, long language_id, String baseURL,
			String jNameTimeZone) throws AxisFault;

	/**
	 * 
	 * Adds a user with an externalUserId and type, but checks if the user/type
	 * does already exist
	 * 
	 * @param SID
	 * @param username
	 * @param userpass
	 * @param lastname
	 * @param firstname
	 * @param email
	 * @param additionalname
	 * @param street
	 * @param zip
	 * @param fax
	 * @param states_id
	 * @param town
	 * @param language_id
	 * @param jNameTimeZone
	 * @param externalUserId
	 * @param externalUserType
	 * @return
	 * @throws AxisFault
	 */
	public abstract Long addNewUserWithExternalType(String SID,
			String username, String userpass, String lastname,
			String firstname, String email, String additionalname,
			String street, String zip, String fax, long states_id, String town,
			long language_id, String jNameTimeZone, Long externalUserId,
			String externalUserType) throws AxisFault;

	/**
	 * 
	 * delete a user by its id
	 * 
	 * @param SID
	 * @param userId
	 * @return
	 * @throws AxisFault
	 */
	public abstract Long deleteUserById(String SID, Long userId)
			throws AxisFault;

	/**
	 * 
	 * delete a user by its external user id and type
	 * 
	 * @param SID
	 * @param externalUserId
	 * @param externalUserType
	 * @return
	 * @throws AxisFault
	 */
	public abstract Long deleteUserByExternalUserIdAndType(String SID,
			Long externalUserId, String externalUserType) throws AxisFault;

	/**
	 * 
	 * @param SID
	 * @param firstname
	 * @param lastname
	 * @param profilePictureUrl
	 * @param email
	 * @return
	 * @throws AxisFault
	 */
	public abstract Long setUserObject(String SID, String username,
			String firstname, String lastname, String profilePictureUrl,
			String email) throws AxisFault;

	/**
	 * This is the advanced technique to set the User Object + simulate a User
	 * from the external system, this is needed cause you can that always
	 * simulate to same user in openmeetings
	 * 
	 * @param SID
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param profilePictureUrl
	 * @param email
	 * @param externalUserId
	 *            the User Id of the external System
	 * @param externalUserType
	 *            the Name of the external system, for example you can run
	 *            several external system and one meeting server
	 * @return
	 * @throws AxisFault
	 */
	public abstract Long setUserObjectWithExternalUser(String SID,
			String username, String firstname, String lastname,
			String profilePictureUrl, String email, Long externalUserId,
			String externalUserType) throws AxisFault;

	public abstract String setUserObjectAndGenerateRoomHash(String SID,
			String username, String firstname, String lastname,
			String profilePictureUrl, String email, Long externalUserId,
			String externalUserType, Long room_id, int becomeModeratorAsInt,
			int showAudioVideoTestAsInt) throws AxisFault;

	public abstract String setUserObjectAndGenerateRoomHashByURL(String SID,
			String username, String firstname, String lastname,
			String profilePictureUrl, String email, Long externalUserId,
			String externalUserType, Long room_id, int becomeModeratorAsInt,
			int showAudioVideoTestAsInt);

	public abstract String setUserObjectAndGenerateRoomHashByURLAndRecFlag(
			String SID, String username, String firstname, String lastname,
			String profilePictureUrl, String email, Long externalUserId,
			String externalUserType, Long room_id, int becomeModeratorAsInt,
			int showAudioVideoTestAsInt, int allowRecording);

	public abstract String setUserObjectMainLandingZone(String SID,
			String username, String firstname, String lastname,
			String profilePictureUrl, String email, Long externalUserId,
			String externalUserType);

	public abstract String setUserAndNickName(String SID, String username,
			String firstname, String lastname, String profilePictureUrl,
			String email, Long externalUserId, String externalUserType,
			Long room_id, int becomeModeratorAsInt,
			int showAudioVideoTestAsInt, int showNickNameDialogAsInt);

	public abstract String setUserObjectAndGenerateRecordingHashByURL(
			String SID, String username, String firstname, String lastname,
			Long externalUserId, String externalUserType, Long recording_id);

	public abstract Long addUserToOrganisation(String SID, Long user_id,
			Long organisation_id, Long insertedby, String comment);

	public abstract SearchResult getUsersByOrganisation(String SID,
			long organisation_id, int start, int max, String orderby,
			boolean asc);

	public abstract Boolean kickUserByPublicSID(String SID, String publicSID);

}