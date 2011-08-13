package org.openmeetings.test.init;

import java.util.List;

import junit.framework.TestCase;

import org.openmeetings.app.data.basic.Fieldmanagment;
import org.springframework.beans.factory.annotation.Autowired;

public class getDefaultFields extends TestCase {

	@Autowired
	private Fieldmanagment fieldmanagment;

	public getDefaultFields(String testname) {
		super(testname);
	}

	public void testGetDefaultField() {

		List ll = fieldmanagment.getAllFieldsByLanguage(new Long(1));
		//
		// System.out.println("testGetDefaultField: "+ll.size());
		//
		// for (Iterator it2 = ll.iterator(); it2.hasNext();) {
		// Fieldvalues f = (Fieldvalues) it2.next();
		// System.out.println("Fieldvalues: "+f.getName());
		// System.out.println("Fieldvalues size: "+f.getFieldlanguagesvalues().size());
		// }
		//
		// List ll2 = fieldmanagment.getAllFieldsByLanguage(1);
		//
		// for (Iterator it1 = ll2.iterator(); it1.hasNext();) {
		// Fieldlanguagesvalues f = (Fieldlanguagesvalues) it1.next();
		// System.out.println("Fieldlanguagesvalues: "+f.getValue());
		// }
		//
		// Fieldlanguagesvalues fl = fieldmanagment.getFieldByIdAndLanguage(1,
		// 1);
		// System.out.println("Fieldlanguagesvalues single: "+fl.getValue());
		//
		// MainService mService = new MainService();
		//
		// List l = mService.getLanguages();

	}

}
