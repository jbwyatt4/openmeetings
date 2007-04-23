package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.xmlcrm.app.data.basic.Fieldmanagment;

public class AddDefaultfield extends TestCase {
	
	public AddDefaultfield (String testname){
		super(testname);
	}
	
	public void testaddDefaultField(){
		
//		Fieldmanagment.getInstance().addFourFieldValues("organisationtablelist_idrow", 164, "Organisations-ID", "Organisation-ID", "Organisation-ID", "Organisation-ID");
//		Fieldmanagment.getInstance().addFourFieldValues("organisationtablelist_namerow", 165, "Name", "name", "name", "name");
//		Fieldmanagment.getInstance().addFourFieldValues("uservalue_levelid1", 166, "Benutzer", "user", "user", "user");
//		Fieldmanagment.getInstance().addFourFieldValues("uservalue_levelid2", 167, "Moderator", "mod", "mod", "mod");
//		Fieldmanagment.getInstance().addFourFieldValues("uservalue_levelid3", 168, "Admin", "admin", "admin", "admin");
//		Fieldmanagment.getInstance().addFourFieldValues("uservalue_levellabel", 169, "Benuterrolle", "userlevel", "userlevel", "userlevel");
//		Fieldmanagment.getInstance().addFourFieldValues("orgvalue_header", 170, "Organisation", "organisation", "organisation", "organisation");
//		Fieldmanagment.getInstance().addFourFieldValues("orgvalue_orgname", 171, "Name", "name", "name", "name");
//		Fieldmanagment.getInstance().addFourFieldValues("orgvalue_orgname", 172, "Organisation hinzufügen", "add organisation", "add organisation", "add organisation");
//		Fieldmanagment.getInstance().addFourFieldValues("orgvalue_orgname", 173, "Organisation hinzufügen", "add organisation", "add organisation", "add organisation");
		Fieldmanagment.getInstance().addFourFieldValues("orgvalue_userwin", 174, "abbrechen", "cancel", "cancel", "cancel");
		Fieldmanagment.getInstance().addFourFieldValues("orgvalue_userwin", 175, "hinzufügen", "add", "add", "add");

	}

}
