package org.openmeetings.servlet.outputhandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import javax.imageio.ImageIO;
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
import org.openmeetings.app.documents.GenerateImage;
import org.openmeetings.app.remote.Application;
import org.openmeetings.utils.geom.GeomPoint;
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

		        //Whiteboard Objects
		        Map whiteBoardMap = pBean.getMap();
		        
		        for (Iterator iter = whiteBoardMap.keySet().iterator();iter.hasNext();) {
		        	Map graphObject = (Map) whiteBoardMap.get(iter.next());
		        	
		        	String graphType = graphObject.get(0).toString();
		        	
		        	if (graphType.equals("paint")) {
		        		
		        		Map pointsList = (Map) graphObject.get(1);
		        		
		        		Integer lineWidth = Integer.valueOf(graphObject.get(3).toString()).intValue();
		        		Integer col = Integer.valueOf(graphObject.get(4).toString()).intValue();
		        		
		        		Float alpha = Float.valueOf(graphObject.get(5).toString()).floatValue();
		        		
		        		Double x = Double.valueOf(graphObject.get(graphObject.size()-5).toString()).doubleValue();
		        		Double y = Double.valueOf(graphObject.get(graphObject.size()-4).toString()).doubleValue();
		        		Double width = Double.valueOf(graphObject.get(graphObject.size()-3).toString()).doubleValue();
		        		Double height = Double.valueOf(graphObject.get(graphObject.size()-2).toString()).doubleValue();
		        		
		        		//Draw a Painting
		    	        SVGGraphics2D svgGenerator_temp = new SVGGraphics2D(svgGenerator);
		    	        //SVGGraphics2D svgGenerator2 = new SVGGraphics2D(document);
		    	        
		    	        this.drawPointsObject(svgGenerator_temp, pointsList, new Color(col), lineWidth, x, y, alpha);
		    	        
		        	} else if (graphType.equals("rectangle")) {
		        		
		        		/*actionObject[0] = 'rectangle';
				        actionObject[1] = stroke;
				        actionObject[2] = line;
				        actionObject[3] = fill;
				        actionObject[4] = strokeDis;
				        actionObject[5] = fillDis;
				        actionObject[6] = this.currentrectangleOpacity;*/
		        		
		        		Integer lineWidth = Integer.valueOf(graphObject.get(2).toString()).intValue();
		        		
		        		Integer stroke = Integer.valueOf(graphObject.get(1).toString()).intValue();
		        		Integer strokeDis= Integer.valueOf(graphObject.get(4).toString()).intValue();
		        		
		        		Color strokeColor = null;
		        		if (strokeDis != -1) {
		        			strokeColor = new Color(stroke);
		        		}
		        		
		        		Integer fill = Integer.valueOf(graphObject.get(3).toString()).intValue();
		        		Integer fillDis= Integer.valueOf(graphObject.get(5).toString()).intValue();
		        		
		        		Color fillColor = null;
		        		if (fillDis != -1) {
		        			fillColor = new Color(fill);
		        		}
		        		
		        		Float alpha = Float.valueOf(graphObject.get(6).toString()).floatValue();
		        		
		        		Double x = Double.valueOf(graphObject.get(graphObject.size()-5).toString()).doubleValue();
		        		Double y = Double.valueOf(graphObject.get(graphObject.size()-4).toString()).doubleValue();
		        		Double width = Double.valueOf(graphObject.get(graphObject.size()-3).toString()).doubleValue();
		        		Double height = Double.valueOf(graphObject.get(graphObject.size()-2).toString()).doubleValue();
		        	
		        		SVGGraphics2D svgGenerator_temp = new SVGGraphics2D(svgGenerator);
		        		this.paintRect2D(svgGenerator_temp, x, y, width, height, strokeColor, lineWidth, fillColor, alpha);
		        		
		        	} else if (graphType.equals("ellipse")) {
		        		
		        		Integer lineWidth = Integer.valueOf(graphObject.get(2).toString()).intValue();
		        		
		        		Integer stroke = Integer.valueOf(graphObject.get(1).toString()).intValue();
		        		Integer strokeDis= Integer.valueOf(graphObject.get(4).toString()).intValue();
		        		
		        		Color strokeColor = null;
		        		if (strokeDis != -1) {
		        			strokeColor = new Color(stroke);
		        		}
		        		
		        		Integer fill = Integer.valueOf(graphObject.get(3).toString()).intValue();
		        		Integer fillDis= Integer.valueOf(graphObject.get(5).toString()).intValue();
		        		
		        		Color fillColor = null;
		        		if (fillDis != -1) {
		        			fillColor = new Color(fill);
		        		}
		        		
		        		Float alpha = Float.valueOf(graphObject.get(6).toString()).floatValue();
		        		
		        		Double x = Double.valueOf(graphObject.get(graphObject.size()-5).toString()).doubleValue();
		        		Double y = Double.valueOf(graphObject.get(graphObject.size()-4).toString()).doubleValue();
		        		Double width = Double.valueOf(graphObject.get(graphObject.size()-3).toString()).doubleValue();
		        		Double height = Double.valueOf(graphObject.get(graphObject.size()-2).toString()).doubleValue();
		        	
		        		SVGGraphics2D svgGenerator_temp = new SVGGraphics2D(svgGenerator);
		        		this.paintEllipse2D(svgGenerator_temp, x, y, width, height, strokeColor, lineWidth, fillColor, alpha);
		        		
		        	} else if (graphType.equals("letter")) {
		        		
		        		String text = graphObject.get(1).toString();
		        		Color fontColor = new Color (Integer.valueOf(graphObject.get(2).toString()).intValue());
		        		Integer fontSize = Integer.valueOf(graphObject.get(3).toString()).intValue();
		        		
		        		String fontStyle = graphObject.get(4).toString();
		        		Integer style = null;
		        		if (fontStyle.equals("plain")) {
		        			style = Font.PLAIN;
		        		} else if (fontStyle.equals("bold")) {
		        			style = Font.BOLD;
		        		} else if (fontStyle.equals("italic")) {
		        			style = Font.ITALIC;
		        		} else if (fontStyle.equals("bolditalic")) {
		        			style = Font.ITALIC+Font.BOLD;
		        		}
		        		
		        		log.debug("fontStyle,style "+style+" fs: "+fontStyle);
		        		
		        		Double x = Double.valueOf(graphObject.get(graphObject.size()-5).toString()).doubleValue();
		        		Double y = Double.valueOf(graphObject.get(graphObject.size()-4).toString()).doubleValue();
		        		Double width = Double.valueOf(graphObject.get(graphObject.size()-3).toString()).doubleValue();
		        		Double height = Double.valueOf(graphObject.get(graphObject.size()-2).toString()).doubleValue();
		        		
		        		SVGGraphics2D svgGenerator_temp = new SVGGraphics2D(svgGenerator);
		        		this.paintTextByWidthHeight(svgGenerator_temp, (int) Math.round(x), (int) Math.round(y), (int) Math.round(width), 
		        					(int) Math.round(height), text, style, fontSize, fontColor);
		        		
		        	} else if (graphType.equals("drawarrow")) {
		        		
		        		Integer thickness = Integer.valueOf(graphObject.get(2).toString()).intValue();
		        		
		        		Integer stroke = Integer.valueOf(graphObject.get(1).toString()).intValue();
		        		Integer strokeDis= Integer.valueOf(graphObject.get(4).toString()).intValue();
		        		
		        		Color strokeColor = null;
		        		if (strokeDis != -1) {
		        			strokeColor = new Color(stroke);
		        		}
		        		
		        		Integer fill = Integer.valueOf(graphObject.get(3).toString()).intValue();
		        		Integer fillDis= Integer.valueOf(graphObject.get(5).toString()).intValue();
		        		
		        		Color fillColor = null;
		        		if (fillDis != -1) {
		        			fillColor = new Color(fill);
		        		}
		        		
		        		Float alpha = Float.valueOf(graphObject.get(6).toString()).floatValue();
		        		
		        		Double x = Double.valueOf(graphObject.get(graphObject.size()-5).toString()).doubleValue();
		        		Double y = Double.valueOf(graphObject.get(graphObject.size()-4).toString()).doubleValue();
		        		//Double width = Double.valueOf(graphObject.get(graphObject.size()-3).toString()).doubleValue();
		        		//Double height = Double.valueOf(graphObject.get(graphObject.size()-2).toString()).doubleValue();
		        		
		        		Double x1 = Double.valueOf(graphObject.get(7).toString()).doubleValue();
		        		Double y1 = Double.valueOf(graphObject.get(8).toString()).doubleValue();
		        		Double x2 = Double.valueOf(graphObject.get(9).toString()).doubleValue();
		        		Double y2 = Double.valueOf(graphObject.get(10).toString()).doubleValue();
		        		
		        		GeomPoint start = new GeomPoint();
		    	        start.setLocation(x+x1,y+y1);
		    	        GeomPoint end = new GeomPoint();
		    	        end.setLocation(x+x2,y+y2);
		    	        
		    	        SVGGraphics2D svgGenerator_temp = new SVGGraphics2D(svgGenerator);
		    	        this.drawArrow(svgGenerator_temp, start, end, thickness, alpha, strokeColor,fillColor);
		        	} else if (graphType.equals("image")) {
		        		
		        		//log.debug("graphObject image "+graphObject);
		        		//log.debug("",graphObject);
		        		
		        		String room = graphObject.get(6).toString();
		        		String parentPath = graphObject.get(5).toString();
		        		String fileItemName = graphObject.get(3).toString();
		        		
		        		String imageFilePath = Application.webAppPath + File.separatorChar +
		        								"upload" + File.separatorChar + room + File.separatorChar;
		        		
		        		if (parentPath.length() > 1) {
		        			imageFilePath += parentPath + File.separatorChar;
		        		}
		        		
		        		//log.debug("fileItemName: "+fileItemName);
		        		
		        		String full_path = imageFilePath + fileItemName;
		        		File myFile = new File(full_path);
		        		
		        		if (myFile.exists() && myFile.canRead()) {
		        			
		        			Image myImage = ImageIO.read(myFile);
		        			
		        			int x = (int) Math.round(Double.valueOf(graphObject.get(graphObject.size()-5).toString()).doubleValue());
		        			int y = (int) Math.round(Double.valueOf(graphObject.get(graphObject.size()-4).toString()).doubleValue());
		        			int width = (int) Math.round(Double.valueOf(graphObject.get(graphObject.size()-3).toString()).doubleValue());
		        			int height = (int) Math.round(Double.valueOf(graphObject.get(graphObject.size()-2).toString()).doubleValue());
			        		
			        		SVGGraphics2D svgGenerator_temp = new SVGGraphics2D(svgGenerator);
			        		svgGenerator_temp.drawImage(myImage, x, y, width, height, null);
		        			
		        		} else {
		        			log.error("tried to inculde a non existing File into SVG/Image Export Path: "+full_path);
		        		}
		        		
		        	} else if(graphType.equals("line") || graphType.equals("uline")) {
		        		
//		        		actionObject[0] = 'line';
//		                actionObject[1] = stroke;
//		                actionObject[2] = line;
//		                actionObject[3] = opacity;
//		                actionObject[4] = x1  
//		                actionObject[5] = y1;  
//		                actionObject[6] = x2;    
//		                actionObject[7] = y2;    
//		                actionObject[8] = this.counter; 
//		                actionObject[9] = x;
//		                actionObject[10] = y;
//		                actionObject[11] = width;
//		                actionObject[12] = height;  
//		                actionObject[13] = newName;
		        		
	        			Integer lineWidth = Integer.valueOf(graphObject.get(2).toString()).intValue();
		        		
		        		Integer stroke = Integer.valueOf(graphObject.get(1).toString()).intValue();
		        		Color strokeColor = new Color(stroke);
		        		
		        		Float alpha = Float.valueOf(graphObject.get(3).toString()).floatValue();
		        		
		        		Double x = Double.valueOf(graphObject.get(graphObject.size()-5).toString()).doubleValue();
		        		Double y = Double.valueOf(graphObject.get(graphObject.size()-4).toString()).doubleValue();
		        		//Double width = Double.valueOf(graphObject.get(graphObject.size()-3).toString()).doubleValue();
		        		//Double height = Double.valueOf(graphObject.get(graphObject.size()-2).toString()).doubleValue();

		        		Double x1 = Double.valueOf(graphObject.get(4).toString()).doubleValue();
		        		Double y1 = Double.valueOf(graphObject.get(5).toString()).doubleValue();
		        		Double x2 = Double.valueOf(graphObject.get(6).toString()).doubleValue();
		        		Double y2 = Double.valueOf(graphObject.get(7).toString()).doubleValue();
		        		
		        		SVGGraphics2D svgGenerator_temp = new SVGGraphics2D(svgGenerator);
		        		this.drawLine(svgGenerator_temp, x1, y1, x2, y2, strokeColor, lineWidth, x, y, alpha);
		        		
		        	} else {
		        		log.error("tried to include a non supported Graph-Object graphType: "+graphType);
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
			        
			        
		        } else if (exportType.equals("png") || exportType.equals("jpg") 
		        		|| exportType.equals("gif") || exportType.equals("tif")
		        		|| exportType.equals("pdf")){
		        	
		        	String current_dir = getServletContext().getRealPath("/");
		        	String working_dir = current_dir + "uploadtemp" + File.separatorChar;
		        	
		        	String requestedFileSVG = fileName+"_"+CalendarPatterns.getTimeForStreamId(new Date())+".svg";
		        	String resultFileName = fileName+"_"+CalendarPatterns.getTimeForStreamId(new Date())+"."+exportType;
		        	
		        	log.debug("current_dir: "+current_dir);
		        	log.debug("working_dir: "+working_dir);
		        	log.debug("requestedFileSVG: "+requestedFileSVG);
		        	log.debug("resultFileName: "+resultFileName);
		        	
		        	File svgFile = new File(working_dir + requestedFileSVG);
		        	File resultFile = new File(working_dir + resultFileName);
		        	
		        	log.debug("svgFile: "+svgFile.getAbsolutePath());
		        	log.debug("resultFile: "+resultFile.getAbsolutePath());
		        	log.debug("svgFile P: "+svgFile.getPath());
		        	log.debug("resultFile P: "+resultFile.getPath());
		        	
		        	FileWriter out = new FileWriter(svgFile);
		        	svgGenerator.stream(out, useCSS);
		        	
		        	HashMap<String,Object> returnError = GenerateImage.getInstance().convertSingleImageByTypeAndSize(
		        			svgFile.getAbsolutePath(), resultFile.getAbsolutePath(), 
		        			pBean.getWidth(), pBean.getHeight());
		        	
		        	//Get file and handle download
					RandomAccessFile rf = new RandomAccessFile(resultFile.getAbsoluteFile(), "r");

					httpServletResponse.reset();
					httpServletResponse.resetBuffer();
					OutputStream outStream = httpServletResponse.getOutputStream();
					httpServletResponse.setContentType("APPLICATION/OCTET-STREAM");
					httpServletResponse.setHeader("Content-Disposition","attachment; filename=\"" + resultFileName + "\"");
					httpServletResponse.setHeader("Content-Length", ""+ rf.length());

					byte[] buffer = new byte[1024];
					int readed = -1;

					while ((readed = rf.read(buffer, 0, buffer.length)) > -1) {
						outStream.write(buffer, 0, readed);
					}

					rf.close();

					out.flush();
					out.close();
		        	
		        }
				
			}
			
			
		} catch (Exception er) {
			log.error("ERROR ", er);
			System.out.println("Error exporting: " + er);
			er.printStackTrace();
		}
	}
}
