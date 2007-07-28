package org.xmlcrm.app.installation;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.io.SAXReader;
import org.dom4j.Element;
import org.dom4j.Document;
import org.xmlcrm.app.data.basic.Configurationmanagement;
import org.xmlcrm.app.data.basic.Fieldmanagment;
import org.xmlcrm.app.data.basic.Languagemanagement;
import org.xmlcrm.app.data.basic.Navimanagement;
import org.xmlcrm.app.data.conference.Roommanagement;
import org.xmlcrm.app.data.user.Organisationmanagement;
import org.xmlcrm.app.data.user.Salutationmanagement;
import org.xmlcrm.app.data.user.Statemanagement;
import org.xmlcrm.app.data.user.Usermanagement;

public class ImportInitvalues {
	
	private static final Log log = LogFactory.getLog(ImportInitvalues.class);
	
	public static final String languageFolderName = "languages/";
	
	private static final String nameOfLanguageFile = "languages.xml";
	
	private static final String nameOfCountriesFile = "countries.xml";
	
	private static ImportInitvalues instance;

	private ImportInitvalues() {}

	public static synchronized ImportInitvalues getInstance() {
		if (instance == null) {
			instance = new ImportInitvalues();
		}
		return instance;
	}	
	
	public void loadMainMenu(){
		Usermanagement.getInstance().addUserLevel("User", 1);
		Usermanagement.getInstance().addUserLevel("Moderator", 2);
		Usermanagement.getInstance().addUserLevel("Admin", 3);	
		
		Navimanagement.getInstance().addGlobalStructure("home", 1, 124, true, true, 1, "home");
		
		Navimanagement.getInstance().addGlobalStructure("conf", 2, 1, false, true, 1, "conference");
		Navimanagement.getInstance().addMainStructure("conf1", 1, 2, true, true, 1, "meeting", 2);
		Navimanagement.getInstance().addMainStructure("conf2", 2, 3, true, true, 1, "classroom", 2);
		
		Navimanagement.getInstance().addGlobalStructure("settings", 3, 4, false, true, 1, "setings");
		Navimanagement.getInstance().addMainStructure("userself", 1, 5, true, false, 1, "userself",3);
		Navimanagement.getInstance().addMainStructure("roomconfiguremod", 2, 192, true, false, 1, "roomconfiguremod",3);
		
		Navimanagement.getInstance().addGlobalStructure("admin", 4, 6, false, true, 2, "admin");
		Navimanagement.getInstance().addMainStructure("useradmin", 1, 125, true, false, 2, "useradmin",4);
		Navimanagement.getInstance().addMainStructure("groupadmin", 1, 126, true, false, 2, "groupadmin",4);
		Navimanagement.getInstance().addMainStructure("orgadmin", 1, 127, true, false, 3, "orgadmin",4);
		Navimanagement.getInstance().addMainStructure("roomadmin", 1, 186, true, false, 3, "roomadmin",4);
			
	}
	
	public void loadSalutations(){
		
		Salutationmanagement.getInstance().addUserSalutation("Mister",261);
		Salutationmanagement.getInstance().addUserSalutation("Miss",262);
		
	}
	
	public void loadConfiguration(String allowfrontendRegister, String smtpServer, String smtpPort, 
			String referer, String mailauthname, String mailauthpass, String default_lang, 
			String swf_path, String im_path){
		
		//"1"
		Configurationmanagement.getInstance().addConfByKey(3, "allow_frontend_register", allowfrontendRegister, null, "");
		
		Configurationmanagement.getInstance().addConfByKey(3, "default_group_id", "1", null, "");
		
		//this domain_id is the Organisation of users who register through the frontend
		Configurationmanagement.getInstance().addConfByKey(3, "default_domain_id", "1", null, "");
		
		//"smtp.xmlcrm.org"
		Configurationmanagement.getInstance().addConfByKey(3, "smtp_server", smtpServer, null, "this is the smtp server to send messages");
		//25
		Configurationmanagement.getInstance().addConfByKey(3, "smtp_port", smtpPort, null, "this is the smtp server port normally 25");
		//"openmeetings@xmlcrm.org"
		Configurationmanagement.getInstance().addConfByKey(3, "system_email_addr", referer, null, "all send EMails by the system will have this address");
		//"openmeetings@xmlcrm.org"
		Configurationmanagement.getInstance().addConfByKey(3, "email_username", mailauthname, null, "System auth email username");
		//
		Configurationmanagement.getInstance().addConfByKey(3, "email_userpass", mailauthpass, null, "System auth email password");		
		//"EN"
		Configurationmanagement.getInstance().addConfByKey(3, "default_lang", default_lang, null, "Default System Language for tamplates");
		
		Configurationmanagement.getInstance().addConfByKey(3, "register_mail_subject", "SignUp", null, "The Subject for Mails sended at registration");
					
		Configurationmanagement.getInstance().addConfByKey(3, "swftools_path", swf_path, null, "Path To SWF-Tools");
		
		Configurationmanagement.getInstance().addConfByKey(3, "imagemagick_path", im_path, null, "Path to ImageMagick tools");
		
	}
	
	public void loadDefaultRooms(){
		
		long conference_Id = Roommanagement.getInstance().addRoomType("conference");
		log.error("conference_Id: "+conference_Id);
		long audience_Id = Roommanagement.getInstance().addRoomType("audience");
		log.error("audience_Id: "+audience_Id);
		
		Roommanagement.getInstance().addRoom(3,"public Conference Room", 1,"", true,null);
		
		long room2 = Roommanagement.getInstance().addRoom(3,"private Conference Room", 1,"", false, null);
		Roommanagement.getInstance().addRoomToOrganisation(3,room2, 1);
		
		Roommanagement.getInstance().addRoom(3,"public Audience Room", 2,"", true, null);
		
		long room4 = Roommanagement.getInstance().addRoom(3,"private Audience Room", 2,"", false, null);
		Roommanagement.getInstance().addRoomToOrganisation(3,room4, 1);
		
	}
	
	public void loadInitUserAndOrganisation(String username, String userpass, String email, String defaultOrganisationName) {
		//Add user
		long user_id = Usermanagement.getInstance().registerUserInit(new Long(3),3, 1, 1, 
				username, userpass, "lastname", "firstname", 
				email, new java.util.Date(), "street", "no", "fax", 
				"zip", 1, "town", 0, false, null);
		
		//Add default group
		long organisation_id = Organisationmanagement.getInstance().addOrganisation(defaultOrganisationName, user_id);
		
		//Add user to default group
		Organisationmanagement.getInstance().addUserToOrganisation(1, organisation_id, 1,"");		
	}
	
	/**
	 * import all language Names from the xml file
	 * @param filePath
	 * @throws Exception
	 */
	private void loadCountriesFiles(String filePath) throws Exception{
		
        SAXReader reader = new SAXReader();
        Document document = reader.read(filePath+ImportInitvalues.nameOfCountriesFile);
        
        Element root = document.getRootElement();
        
        for ( Iterator it = root.elementIterator( "country" ); it.hasNext(); ) {
        	
        	Element item = (Element) it.next();
        	String country = item.attributeValue("name");
        	
        	Statemanagement.getInstance().addState(country);
        	
        }
        
		
	}	
	
	/**
	 * load all availible languages File names and language name's from the config file
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private List<String> getLanguageFiles(String filePath) throws Exception{
		
		List<String> languages = new LinkedList<String>();
		
        SAXReader reader = new SAXReader();
        Document document = reader.read(filePath+ImportInitvalues.nameOfLanguageFile);
        
        Element root = document.getRootElement();
        
        for ( Iterator it = root.elementIterator( "lang" ); it.hasNext(); ) {
        	
        	Element item = (Element) it.next();
        	String country = item.getText();
        	
        	//log.error("getLanguageFiles "+country);
        	languages.add(country);
        	
        }
		
        return languages;
		
	}
	
	public void loadInitLanguages(String filePath) throws Exception{
		
		this.loadCountriesFiles(filePath);	
		
		//String listLanguages[] = {"deutsch", "english", "french", "spanish"};
		
		List<String> listlanguages = this.getLanguageFiles(filePath);
		
		// TODO empty tables before launch
		//Languagemanagement.getInstance().emptyFieldLanguage();
		
		boolean langFieldIdIsInited = false;
		
		/** Read all languages files */
		for (Iterator<String> itLang = listlanguages.iterator();itLang.hasNext();)
		{
			String lang = itLang.next();
			
			//log.error("loadInitLanguages lang: "+lang);
			
			Long languages_id = Languagemanagement.getInstance().addLanguage(lang);
			
	        SAXReader reader = new SAXReader();
	        Document document = reader.read(filePath+lang+".xml");
	        
	        Element root = document.getRootElement();
	        
	        for ( Iterator it = root.elementIterator( "string" ); it.hasNext(); ) {
	            Element item = (Element) it.next();
	            log.error(item.getName());
	            
	            Long id = Long.valueOf(item.attributeValue("id")).longValue();
	            String name = item.attributeValue("name");
	            String value = "";
	        	
	        	for ( Iterator t2 = item.elementIterator( "value" ); t2.hasNext(); ) {
	        		Element val = (Element) t2.next();
	        		value = val.getText();
	        	}
	        	
	        	//log.error("result: "+langFieldIdIsInited+" "+id+" "+name+" "+value);
	        	
	        	//Only do that for the first field-set
	        	if (!langFieldIdIsInited) Fieldmanagment.getInstance().addField(name);
	        	
	        	Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(id,languages_id,value);
	        	
	        }
	        
	        if(!langFieldIdIsInited) langFieldIdIsInited=true;
		}	
		
	}
	
}
