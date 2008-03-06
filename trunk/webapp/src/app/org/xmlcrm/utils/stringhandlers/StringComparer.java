package org.xmlcrm.utils.stringhandlers;

public class StringComparer {
	
	private static StringComparer instance = null;
	
	private StringComparer() {}
	
	public static synchronized StringComparer getInstance(){
		if (instance == null){
			instance = new StringComparer();
		}
		return instance;
	}
	
	public String compareForRealPaths(String inputString) throws Exception{

		String t = "";
		for (int i=0;i<inputString.length();i++){
			char c = inputString.charAt(i);
			if (compareChars(c)) {
				t += c;
			} else {
				t += "_";
			}
			
		}		
		return t;
	}
	
	private boolean compareChars(char inputChar){
		
		//char[] allowedChars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
		String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
		for (int i=0;i<allowedChars.length();i++)
			if (allowedChars.charAt(i)==inputChar) return true;
		
		return false;
	}

}
