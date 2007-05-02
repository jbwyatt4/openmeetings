package org.xmlcrm.app.documents;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class LibraryDocumentConverter {
	
	private static final Log log = LogFactory.getLog(LibraryDocumentConverter.class);

	private static LibraryDocumentConverter instance;

	private LibraryDocumentConverter() {}

	public static synchronized LibraryDocumentConverter getInstance() {
		if (instance == null) {
			instance = new LibraryDocumentConverter();
		}
		return instance;
	}
	
	public String writeToLocalFolder(String fileName, LinkedHashMap objList) {
		try {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder construct = factory.newDocumentBuilder();
		
		Document document = construct.newDocument();
		
		Element root = document.createElement("whiteboard");
		document.appendChild(root);
		
		for (Iterator it = objList.keySet().iterator();it.hasNext();){
			
			Integer key = (Integer) it.next();
			LinkedHashMap innerlMap = (LinkedHashMap) objList.get(key);
			String baseItem = (String) innerlMap.get(0);
			log.error("baseItem: "+baseItem);
			
			Element baseElement = document.createElement("item");
			root.appendChild(baseElement);
			
			Element elementName = document.createElement("itemname");
			baseElement.appendChild(elementName);
			Text mytxt = document.createTextNode(baseItem);
			elementName.appendChild(mytxt);
			
			if (baseItem.equals("paint")){
				this.createNodesByPaint(document, baseElement, innerlMap);
			} else if (baseItem.equals("letter")){
				this.createNodesByLetter(document, baseElement, innerlMap);
			} else if (baseItem.equals("image")){
				this.createNodesByImage(document, baseElement, innerlMap);
			} else if (baseItem.equals("line") || baseItem.equals("uline") || baseItem.equals("drawarrow")){
				this.createNodesByObject(document, baseElement, innerlMap);
			} else if (baseItem.equals("rectangle") || baseItem.equals("ellipse")){
				this.createNodesByRectAndEllipse(document, baseElement, innerlMap);
			}
			
		}
		
		log.error("writeToLocalFolder XML " + document);
		log.error("writeToLocalFolder XML " + document.toString());
		
		StringWriter stringOut = new StringWriter();
		
		// format
		OutputFormat format = new OutputFormat(document);
		format.setIndenting(true);
		format.setPreserveSpace(false);

		// serialize
		XMLSerializer serial = new XMLSerializer(stringOut, format);
		serial.asDOMSerializer();
		serial.serialize(document);
		String docString = stringOut.toString();
		
		log.error("docString "+docString);
		
		} catch (Exception err){
			log.error("writeToLocalFolder",err);
		}
		
		return null;
		
	}
	
	private void createNodesByPaint(Document document, Element baseElement, LinkedHashMap paint){
		try {
			
			Element pointsName = document.createElement("points");
			baseElement.appendChild(pointsName);
			LinkedHashMap pointsMap = (LinkedHashMap) paint.get(1);
			for (Iterator it = pointsMap.keySet().iterator();it.hasNext();){
				Integer key = (Integer) it.next();
				LinkedHashMap point = (LinkedHashMap) pointsMap.get(key);
				Element pointElement = document.createElement(point.get(0).toString());
				pointElement.setAttribute("val1", point.get(1).toString());
				pointElement.setAttribute("val2", point.get(2).toString());
				pointElement.setAttribute("val3", point.get(3).toString());
				pointElement.setAttribute("val4", point.get(4).toString());
				pointsName.appendChild(pointElement);
			}
			
			Element fillstyle = document.createElement("fillstyle");
			baseElement.appendChild(fillstyle);
			Text fillstyleT = document.createTextNode(paint.get(2).toString());
			fillstyle.appendChild(fillstyleT);
			
			Element linewidth = document.createElement("linewidth");
			baseElement.appendChild(linewidth);
			Text linewidthT = document.createTextNode(paint.get(3).toString());
			linewidth.appendChild(linewidthT);
			
			Element strokestyle = document.createElement("strokestyle");
			baseElement.appendChild(strokestyle);
			Text strokestyleT = document.createTextNode(paint.get(4).toString());
			strokestyle.appendChild(strokestyleT);
			
			Element x = document.createElement("x");
			baseElement.appendChild(x);
			Text xT = document.createTextNode(paint.get(5).toString());
			x.appendChild(xT);
			
			Element y = document.createElement("y");
			baseElement.appendChild(y);
			Text yT = document.createTextNode(paint.get(6).toString());
			y.appendChild(yT);
			
			Element width = document.createElement("width");
			baseElement.appendChild(width);
			Text widthT = document.createTextNode(paint.get(7).toString());
			width.appendChild(widthT);
			
			Element height = document.createElement("height");
			baseElement.appendChild(height);
			Text heightT = document.createTextNode(paint.get(8).toString());
			height.appendChild(heightT);
			
			Element layername = document.createElement("layername");
			baseElement.appendChild(layername);
			Text layernameT = document.createTextNode(paint.get(9).toString());
			layername.appendChild(layernameT);
			
		} catch (Exception err){
			log.error("createNodesByPaint",err);
		}
	}
	
	private void createNodesByLetter(Document document, Element baseElement, LinkedHashMap letter){
		try {
			
			Element textvalue = document.createElement("textforfield");
			baseElement.appendChild(textvalue);
			Text textvalueT = document.createTextNode(letter.get(1).toString());
			textvalue.appendChild(textvalueT);
			
			Element fgcolor = document.createElement("fgcolor");
			baseElement.appendChild(fgcolor);
			Text fgcolorT = document.createTextNode(letter.get(2).toString());
			fgcolor.appendChild(fgcolorT);
			
			Element fontsize = document.createElement("fontsize");
			baseElement.appendChild(fontsize);
			Text fontsizeT = document.createTextNode(letter.get(3).toString());
			fontsize.appendChild(fontsizeT);			
			
			Element fontstyle = document.createElement("fontstyle");
			baseElement.appendChild(fontstyle);
			Text fontstyleT = document.createTextNode(letter.get(4).toString());
			fontstyle.appendChild(fontstyleT);	
			
			Element x = document.createElement("x");
			baseElement.appendChild(x);
			Text xT = document.createTextNode(letter.get(5).toString());
			x.appendChild(xT);
			
			Element y = document.createElement("y");
			baseElement.appendChild(y);
			Text yT = document.createTextNode(letter.get(6).toString());
			y.appendChild(yT);
			
			Element width = document.createElement("width");
			baseElement.appendChild(width);
			Text widthT = document.createTextNode(letter.get(7).toString());
			width.appendChild(widthT);
			
			Element height = document.createElement("height");
			baseElement.appendChild(height);
			Text heightT = document.createTextNode(letter.get(8).toString());
			height.appendChild(heightT);
			
			Element layername = document.createElement("layername");
			baseElement.appendChild(layername);
			Text layernameT = document.createTextNode(letter.get(9).toString());
			layername.appendChild(layernameT);
			
		} catch (Exception err){
			log.error("createNodesByLetter",err);
		}
	}
	
	private void createNodesByImage(Document document, Element baseElement, LinkedHashMap image){
		try {
			
			Element urlname = document.createElement("urlname");
			baseElement.appendChild(urlname);
			Text urlnameT = document.createTextNode(image.get(1).toString());
			urlname.appendChild(urlnameT);
			
			Element baseurl = document.createElement("baseurl");
			baseElement.appendChild(baseurl);
			Text baseurlT = document.createTextNode(image.get(2).toString());
			baseurl.appendChild(baseurlT);
			
			Element filename = document.createElement("filename");
			baseElement.appendChild(filename);
			Text filenameT = document.createTextNode(image.get(3).toString());
			filename.appendChild(filenameT);
			
			Element modulename = document.createElement("modulename");
			baseElement.appendChild(modulename);
			Text modulenameT = document.createTextNode(image.get(4).toString());
			modulename.appendChild(modulenameT);
			
			Element parentpath = document.createElement("parentpath");
			baseElement.appendChild(parentpath);
			Text parentpathT = document.createTextNode(image.get(5).toString());
			parentpath.appendChild(parentpathT);
			
			Element room = document.createElement("room");
			baseElement.appendChild(room);
			Text roomT = document.createTextNode(image.get(6).toString());
			room.appendChild(roomT);
			
			Element domain = document.createElement("domain");
			baseElement.appendChild(domain);
			Text domainT = document.createTextNode(image.get(7).toString());
			domain.appendChild(domainT);
			
			Element x = document.createElement("x");
			baseElement.appendChild(x);
			Text xT = document.createTextNode(image.get(8).toString());
			x.appendChild(xT);
			
			Element y = document.createElement("y");
			baseElement.appendChild(y);
			Text yT = document.createTextNode(image.get(9).toString());
			y.appendChild(yT);
			
			Element width = document.createElement("width");
			baseElement.appendChild(width);
			Text widthT = document.createTextNode(image.get(10).toString());
			width.appendChild(widthT);
			
			Element height = document.createElement("height");
			baseElement.appendChild(height);
			Text heightT = document.createTextNode(image.get(11).toString());
			height.appendChild(heightT);
			
			Element layername = document.createElement("layername");
			baseElement.appendChild(layername);
			Text layernameT = document.createTextNode(image.get(12).toString());
			layername.appendChild(layernameT);
			
		} catch (Exception err){
			log.error("createNodesByImage",err);
		}
	}
	
	
	private void createNodesByObject(Document document, Element baseElement, LinkedHashMap paintObject){
		try {
			
			Element fillstyle = document.createElement("fillstyle");
			baseElement.appendChild(fillstyle);
			Text fillstyleT = document.createTextNode(paintObject.get(1).toString());
			fillstyle.appendChild(fillstyleT);
			
			Element linewidth = document.createElement("linewidth");
			baseElement.appendChild(linewidth);
			Text linewidthT = document.createTextNode(paintObject.get(2).toString());
			linewidth.appendChild(linewidthT);
			
			Element strokestyle = document.createElement("strokestyle");
			baseElement.appendChild(strokestyle);
			Text strokestyleT = document.createTextNode(paintObject.get(3).toString());
			strokestyle.appendChild(strokestyleT);
			
			Element startx = document.createElement("startx");
			baseElement.appendChild(startx);
			Text startxT = document.createTextNode(paintObject.get(4).toString());
			startx.appendChild(startxT);
			
			Element starty = document.createElement("starty");
			baseElement.appendChild(starty);
			Text startyT = document.createTextNode(paintObject.get(5).toString());
			starty.appendChild(startyT);

			Element endx = document.createElement("endx");
			baseElement.appendChild(endx);
			Text endxT = document.createTextNode(paintObject.get(6).toString());
			endx.appendChild(endxT);

			Element endy = document.createElement("endy");
			baseElement.appendChild(endy);
			Text endyT = document.createTextNode(paintObject.get(7).toString());
			endy.appendChild(endyT);
			
			Element x = document.createElement("x");
			baseElement.appendChild(x);
			Text xT = document.createTextNode(paintObject.get(8).toString());
			x.appendChild(xT);
			
			Element y = document.createElement("y");
			baseElement.appendChild(y);
			Text yT = document.createTextNode(paintObject.get(9).toString());
			y.appendChild(yT);
			
			Element width = document.createElement("width");
			baseElement.appendChild(width);
			Text widthT = document.createTextNode(paintObject.get(10).toString());
			width.appendChild(widthT);
			
			Element height = document.createElement("height");
			baseElement.appendChild(height);
			Text heightT = document.createTextNode(paintObject.get(11).toString());
			height.appendChild(heightT);
			
			Element layername = document.createElement("layername");
			baseElement.appendChild(layername);
			Text layernameT = document.createTextNode(paintObject.get(12).toString());
			layername.appendChild(layernameT);
			
		} catch (Exception err){
			log.error("createNodesByObject",err);
		}
	}
	
	
	private void createNodesByRectAndEllipse(Document document, Element baseElement, LinkedHashMap paintObject){
		try {

			Element stroke = document.createElement("stroke");
			baseElement.appendChild(stroke);
			Text strokeT = document.createTextNode(paintObject.get(1).toString());
			stroke.appendChild(strokeT);

			Element line = document.createElement("line");
			baseElement.appendChild(line);
			Text lineT = document.createTextNode(paintObject.get(2).toString());
			line.appendChild(lineT);

			Element x = document.createElement("x");
			baseElement.appendChild(x);
			Text xT = document.createTextNode(paintObject.get(3).toString());
			x.appendChild(xT);
			
			Element y = document.createElement("y");
			baseElement.appendChild(y);
			Text yT = document.createTextNode(paintObject.get(4).toString());
			y.appendChild(yT);
			
			Element width = document.createElement("width");
			baseElement.appendChild(width);
			Text widthT = document.createTextNode(paintObject.get(5).toString());
			width.appendChild(widthT);
			
			Element height = document.createElement("height");
			baseElement.appendChild(height);
			Text heightT = document.createTextNode(paintObject.get(6).toString());
			height.appendChild(heightT);
			
			Element layername = document.createElement("layername");
			baseElement.appendChild(layername);
			Text layernameT = document.createTextNode(paintObject.get(7).toString());
			layername.appendChild(layernameT);
			
		} catch (Exception err){
			log.error("createNodesByRect",err);
		}
	}
	
}
