package org.openmeetings.servlet.outputhandler;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.openmeetings.app.batik.beans.PrintBean;
import org.openmeetings.app.batik.serlvets.AbstractBatikServlet;
import org.openmeetings.app.data.basic.Sessionmanagement;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.remote.Application;
import org.openmeetings.utils.math.CalendarPatterns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ExportToImage extends AbstractBatikServlet {
	 
	private static final Logger log = LoggerFactory.getLogger(ExportToImage.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException {

		try {
			
			
			String sid = httpServletRequest.getParameter("sid");
			if (sid == null) {
				sid = "default";
			}
			log.debug("sid: " + sid);
			
			String hash = httpServletRequest.getParameter("hash");
			if (hash == null) {
				hash = "";
			}
			log.debug("hash: " + hash);
			
			String fileName = httpServletRequest.getParameter("fileName");
			if (fileName == null) {
				fileName = "file_xyz";
			}
			
			String exportType = httpServletRequest.getParameter("exportType");
			if (exportType == null) {
				exportType = "svg";
			}

			Long users_id = Sessionmanagement.getInstance().checkSession(sid);
			Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);

			log.debug("users_id: "+users_id);
			log.debug("user_level: "+user_level);
			
			if (user_level!=null && user_level > 0 && hash != "") {
				
				
				// Get a DOMImplementation.
		        DOMImplementation domImpl =
		            GenericDOMImplementation.getDOMImplementation();

		        // Create an instance of org.w3c.dom.Document.
		        //String svgNS = "http://www.w3.org/2000/svg";
		        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;

		        Document document = domImpl.createDocument(svgNS, "svg", null);
		        
		        // Get the root element (the 'svg' element).
		        Element svgRoot = document.getDocumentElement();
		        
		        PrintBean pBean = Application.getPrintItemByHash(hash);

		        
		        // Set the width and height attributes on the root 'svg' element.
		        svgRoot.setAttributeNS(null, "width", ""+pBean.getWidth());
		        svgRoot.setAttributeNS(null, "height", ""+pBean.getHeight());
		        

		        // Create an instance of the SVG Generator.
		        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

		        SVGGraphics2D svgGenerator8 = new SVGGraphics2D(svgGenerator);
		        //SVGGraphics2D svgGenerator2 = new SVGGraphics2D(document);
		        
		        
		        Map whiteBoardMap = pBean.getMap();
		        
		        for (Iterator iter = whiteBoardMap.keySet().iterator();iter.hasNext();) {
		        	Map graphObject = (Map) whiteBoardMap.get(iter.next());
		        	
		        	String graphType = graphObject.get(0).toString();
		        	
		        	if (graphType.equals("paint")) {
		        		
		        		Map pointsList = (Map) graphObject.get(1);
		        		
		        		Integer stroke = Integer.valueOf(graphObject.get(3).toString()).intValue();
		        		Integer col = Integer.valueOf(graphObject.get(4).toString()).intValue();
		        		
		        		Double x = Double.valueOf(graphObject.get(graphObject.size()-5).toString()).doubleValue();
		        		Double y = Double.valueOf(graphObject.get(graphObject.size()-4).toString()).doubleValue();
		        		Double width = Double.valueOf(graphObject.get(graphObject.size()-3).toString()).doubleValue();
		        		Double height = Double.valueOf(graphObject.get(graphObject.size()-2).toString()).doubleValue();
		        		
		        		//Draw a Painting
		    	        SVGGraphics2D svgGenerator_temp = new SVGGraphics2D(svgGenerator);
		    	        //SVGGraphics2D svgGenerator2 = new SVGGraphics2D(document);
		    	        
		    	        this.drawPointsObject(svgGenerator_temp, pointsList, new Color(col), stroke, x, y);
		    	        
		        	}
		        }
		        
		        // Finally, stream out SVG to the standard output using
		        // UTF-8 encoding.
		        boolean useCSS = true; // we want to use CSS style attributes
		        //Writer out = new OutputStreamWriter(System.out, "UTF-8");
		        
		        
		        String requestedFile = fileName+"_"+CalendarPatterns.getTimeForStreamId(new Date())+".svg";
		        
		        if (exportType.equals("svg")) {
			        //OutputStream out = httpServletResponse.getOutputStream();
					//httpServletResponse.setContentType("APPLICATION/OCTET-STREAM");
					//httpServletResponse.setHeader("Content-Disposition","attachment; filename=\"" + requestedFile + "\"");
			        Writer out = httpServletResponse.getWriter();
			        
			        svgGenerator.stream(out, useCSS);
		        }
				
			}
			
			
		} catch (Exception er) {
			log.error("ERROR ", er);
			System.out.println("Error exporting: " + er);
			er.printStackTrace();
		}
	}
}
