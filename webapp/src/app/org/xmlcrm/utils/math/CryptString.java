package org.xmlcrm.utils.math;

public interface CryptString {
	
	public String createPassPhrase(String userGivenPass);
	
	public boolean verifyPassword(String userGiven, String passwdFromDb);

}
