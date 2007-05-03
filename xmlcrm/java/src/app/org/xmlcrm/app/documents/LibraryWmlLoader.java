package org.xmlcrm.app.documents;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.Element;

public class LibraryWmlLoader {
	
	private static final Log log = LogFactory.getLog(LibraryWmlLoader.class);
	
	private static final String fileExt = ".wml";
	
	private static final String wmlFolderName = "stored/";
	
	private static LibraryWmlLoader instance;

	private LibraryWmlLoader() {}

	public static synchronized LibraryWmlLoader getInstance() {
		if (instance == null) {
			instance = new LibraryWmlLoader();
		}
		return instance;
	}

	public LinkedHashMap loadWmlFile(String filePath, String fileName){
		try {
			LinkedHashMap<Integer,LinkedHashMap> lMap = new LinkedHashMap<Integer,LinkedHashMap>();
			String filepathComplete = filePath+wmlFolderName+fileName+fileExt;
			
			log.error("filepathComplete: "+filepathComplete);
			
	        SAXReader reader = new SAXReader();
	        Document document = reader.read(filepathComplete);
	        
	        Element root = document.getRootElement();
	        Integer k = 0;
	        
	        for ( Iterator i = root.elementIterator( "item" ); i.hasNext(); ) {
	            Element item = (Element) i.next();
	            log.error(item.getName());
	            
	            String nodeVal = item.element("itemname").getText();
	            
	            LinkedHashMap<Integer,Object> subMap = new LinkedHashMap<Integer,Object>();
	            subMap.put(0, nodeVal);
	            
				if (nodeVal.equals("paint")){
					this.createListObjectPaintByNode(item, subMap);
				} else if (nodeVal.equals("letter")){
					this.createListObjectLetterByNode(item, subMap);
				} else if (nodeVal.equals("image")){
					this.createListObjectImageByNode(item, subMap);
				} else if (nodeVal.equals("line") || nodeVal.equals("uline") || nodeVal.equals("drawarrow")){
					this.createListObjectObjecByNode(item, subMap);
				} else if (nodeVal.equals("rectangle") || nodeVal.equals("ellipse")){
					this.createListObjectRectAndEllipseByNode(item, subMap);
				}
	            
				log.error(subMap);
	            lMap.put(k, subMap);
	            
	            k++;

	        }
			
			
			return lMap;
		} catch (DocumentException domErr){
			log.error("DocumentException loadWmlFile",domErr);
		} catch (Exception err){
			log.error("loadWmlFile",err);
		}
		
		return null;
		
	}
	
	
	private void createListObjectPaintByNode(Element paintElement, LinkedHashMap<Integer,Object> subMap){
		try {
			
			LinkedHashMap<Integer,LinkedHashMap> pointMap = new LinkedHashMap<Integer,LinkedHashMap>();
			Element pointElements = paintElement.element("points");
			Integer k = 0;
			
			for ( Iterator i = pointElements.elementIterator( "point" ); i.hasNext(); ) {
				Element pointElement = (Element) i.next();
				LinkedHashMap<Integer,Object> singlePoint = new LinkedHashMap<Integer,Object>();
				singlePoint.put(0, pointElement.getName());
				singlePoint.put(1, Integer.valueOf(pointElement.attribute("val1").getText()).intValue());
				singlePoint.put(2, Integer.valueOf(pointElement.attribute("val2").getText()).intValue());
				singlePoint.put(3, Integer.valueOf(pointElement.attribute("val3").getText()).intValue());
				singlePoint.put(4, Integer.valueOf(pointElement.attribute("val4").getText()).intValue());
				pointMap.put(k, singlePoint);
				log.error(singlePoint);
				k++;
			}
			subMap.put(1, pointMap);

			subMap.put(2, paintElement.element("fillstyle").getText());
			subMap.put(3, Integer.valueOf(paintElement.element("linewidth").getText()).intValue());
			subMap.put(4, Integer.valueOf(paintElement.element("strokestyle").getText()).intValue());
			subMap.put(5, Float.valueOf(paintElement.element("x").getText()).floatValue());
			subMap.put(6, Float.valueOf(paintElement.element("y").getText()).floatValue());
			subMap.put(7, Float.valueOf(paintElement.element("width").getText()).floatValue());
			subMap.put(8, Float.valueOf(paintElement.element("height").getText()).floatValue());
			subMap.put(9, paintElement.element("layername").getText());		
			
		} catch (Exception err) {
			log.error("createListObjectPaintByNode",err);
		}
	}
	
	public void createListObjectLetterByNode(Element paintElement, LinkedHashMap<Integer,Object> subMap){
		try {

			subMap.put(1, paintElement.element("textforfield").getText());
			subMap.put(2, Integer.valueOf(paintElement.element("fgcolor").getText()).intValue());
			subMap.put(3, Integer.valueOf(paintElement.element("fontsize").getText()).intValue());
			subMap.put(4, paintElement.element("fontstyle").getText());
			subMap.put(5, Float.valueOf(paintElement.element("x").getText()).floatValue());
			subMap.put(6, Float.valueOf(paintElement.element("y").getText()).floatValue());			
			subMap.put(7, Float.valueOf(paintElement.element("width").getText()).floatValue());		
			subMap.put(8, Float.valueOf(paintElement.element("height").getText()).floatValue());		
			subMap.put(9, paintElement.element("layername").getText());
			
		} catch (Exception err) {
			log.error("createListObjectLetterByNode",err);
		}
	}	
	
	public void createListObjectImageByNode(Element paintElement, LinkedHashMap<Integer,Object> subMap){
		try {

			subMap.put(1, paintElement.element("urlname").getText());
			subMap.put(2, paintElement.element("baseurl").getText());
			subMap.put(3, paintElement.element("filename").getText());
			subMap.put(4, paintElement.element("modulename").getText());
			subMap.put(5, paintElement.element("parentpath").getText());
			subMap.put(6, paintElement.element("room").getText());
			subMap.put(7, paintElement.element("domain").getText());
			subMap.put(8, Float.valueOf(paintElement.element("x").getText()).floatValue());
			subMap.put(9, Float.valueOf(paintElement.element("y").getText()).floatValue());
			subMap.put(10, Float.valueOf(paintElement.element("width").getText()).floatValue());
			subMap.put(11, Float.valueOf(paintElement.element("height").getText()).floatValue());
			subMap.put(12, paintElement.element("layername").getText());		
			
		} catch (Exception err) {
			log.error("createListObjectImageByNode",err);
		}
	}	
	
	public void createListObjectObjecByNode(Element paintElement, LinkedHashMap<Integer,Object> subMap){
		try {	
			
			subMap.put(1, paintElement.element("fillstyle").getText());
			subMap.put(2, paintElement.element("linewidth").getText());
			subMap.put(3, Integer.valueOf(paintElement.element("strokestyle").getText()).intValue());
			subMap.put(4, Float.valueOf(paintElement.element("startx").getText()).floatValue());
			subMap.put(5, Float.valueOf(paintElement.element("starty").getText()).floatValue());
			subMap.put(6, Float.valueOf(paintElement.element("endx").getText()).floatValue());
			subMap.put(7, Float.valueOf(paintElement.element("endy").getText()).floatValue());
			subMap.put(8, Float.valueOf(paintElement.element("x").getText()).floatValue());
			subMap.put(9, Float.valueOf(paintElement.element("y").getText()).floatValue());
			subMap.put(10, Float.valueOf(paintElement.element("width").getText()).floatValue());
			subMap.put(11, Float.valueOf(paintElement.element("height").getText()).floatValue());
			subMap.put(12, paintElement.element("layername").getText());
			
		} catch (Exception err) {
			log.error("createListObjectObjecByNode",err);
		}
	}		
	
	public void createListObjectRectAndEllipseByNode(Element paintElement, LinkedHashMap<Integer,Object> subMap){
		try {	
			
			subMap.put(1, Integer.valueOf(paintElement.element("stroke").getText()).intValue());
			subMap.put(2, paintElement.element("line").getText());
			subMap.put(3, Float.valueOf(paintElement.element("x").getText()).floatValue());		
			subMap.put(4, Float.valueOf(paintElement.element("y").getText()).floatValue());
			subMap.put(5, Float.valueOf(paintElement.element("width").getText()).floatValue());
			subMap.put(6, Float.valueOf(paintElement.element("height").getText()).floatValue());
			subMap.put(7, paintElement.element("layername").getText());

		} catch (Exception err) {
			log.error("createListObjectObjecByNode",err);
		}
	}
	
}
