package org.openmeetings.axis.services;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;

import org.openmeetings.app.data.basic.Sessionmanagement;

import javax.xml.stream.XMLStreamException;

public class FileService {
	
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
