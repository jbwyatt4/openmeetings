package org.openmeetings.axis.services;

import java.util.LinkedList;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.openmeetings.app.data.basic.Sessionmanagement;
import org.openmeetings.app.data.basic.files.*;
import org.openmeetings.app.remote.ConferenceLibrary;
import org.openmeetings.app.remote.Application;
import javax.xml.stream.XMLStreamException;

public class FileService {
	
	private static final Log log = LogFactory.getLog(FileService.class);
	
	/**
	 * this Method does not work yet,
	 * as the Result has to be rewritten in Objects instead
	 * of a LinekdHashMap
	 * @param SID
	 * @param moduleName
	 * @param parentFolder
	 * @param room
	 * @param domain
	 * @return
	 */
	public LiberaryObject getListOfFiles(String SID, String moduleName,
			String parentFolder, String room, String domain ) {
		try {
			log.debug("#############current_dir : "+"");
			
			String current_dir = Application.webAppPath+"/upload";
			
			
			log.debug("#############SID : "+SID);
	        log.debug("#############moduleName : "+moduleName);
	        log.debug("#############parentFolder : "+parentFolder);
	        log.debug("#############room : "+room);
	        log.debug("#############domain : "+domain);
	        log.debug("#############current_dir : "+current_dir);
	        
	        return ConferenceLibrary.getInstance().getListOfFilesObjectByAbsolutePath(SID, moduleName, parentFolder, room, domain, current_dir);
		
	        
		} catch (Exception err) {
			log.error("[getListOfFiles]",err);
		}
		return null;
	}
	
	public TestObject getTestObject(){
		TestObject textO = new TestObject();
		textO.setList1(new LinkedList<String>());
		textO.setList2(new LinkedList<String>());
		return new TestObject();
	}
	
    public OMElement echo(OMElement element) throws XMLStreamException {
        //Praparing the OMElement so that it can be attached to another OM Tree.
        //First the OMElement should be completely build in case it is not fully built and still
        //some of the xml is in the stream.
        element.build();
        //Secondly the OMElement should be detached from the current OMTree so that it can be attached
        //some other OM Tree. Once detached the OmTree will remove its connections to this OMElement.
        element.detach();
        return element;
    }

    public void ping(OMElement element) throws XMLStreamException {
        //Do some processing
    	System.out.println("PING PING 1");
    	Long ch = Sessionmanagement.getInstance().checkSession("12312312");
    	System.out.println("PING PING 1 ch: "+ch);
    }
    
    public void pingF(OMElement element) throws AxisFault{
        throw new AxisFault("Fault being thrown");
    }
    
}
