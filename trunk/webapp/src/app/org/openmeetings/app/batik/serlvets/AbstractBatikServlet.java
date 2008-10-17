package org.openmeetings.app.batik.serlvets;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.swing.JTextArea;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.openmeetings.app.data.basic.Configurationmanagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AbstractBatikServlet extends HttpServlet {
	
	private static final Logger log = LoggerFactory.getLogger(AbstractBatikServlet.class);

	public void paintTextByWidthHeight(SVGGraphics2D g2d, int x1, int y1, int width, int height,
			String text, int style, int size, Color fontColor) throws Exception {

		//String default_export_font = Configurationmanagement.getInstance().getConfKey(3,"default_export_font").getConf_value();
		
		this.drawTextByString(g2d, x1, y1, width, height, text, "Verdana", style, size, fontColor);

	}
	
	public void _paintTextByWidthHeight(SVGGraphics2D g2d, int x1, int y1, int width, int height,
			String text, int style, int size, Color fontColor) throws Exception {

		//String default_export_font = Configurationmanagement.getInstance().getConfKey(3,"default_export_font").getConf_value();
		
		this.drawTextByString(g2d, x1, y1, width, height, text, "Verdana", style, size, fontColor);

	}
	
	public void drawPointsObject(Graphics2D g2d, Map pointsList, Color lineColor, int size, 
			double xObj, double yObj, float alpha) throws Exception {
		
		for (Iterator iter = pointsList.keySet().iterator();iter.hasNext();) {
			Map<Integer,Object> point = (Map<Integer,Object>) pointsList.get(iter.next());
			
			this.drawThickLine2D(g2d, Double.valueOf(point.get(1).toString()).doubleValue(), 
					Double.valueOf(point.get(2).toString()).doubleValue(), 
					Double.valueOf(point.get(3).toString()).doubleValue(), 
					Double.valueOf(point.get(4).toString()).doubleValue(),  
					size, lineColor, xObj, yObj, alpha);
		}
		
		
	}
	
	public void paintEllipse2D(Graphics2D g2d, double x, double y, double width, double height, 
			Color linecoler, int thickness, Color fillColor, float alpha) throws Exception {
		
		g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		int[] rules = new int[8]; 
		
		//all possible Compositing Rules:
		rules[0] = AlphaComposite.SRC_OVER;
		rules[1] = AlphaComposite.DST_OVER;
		rules[2] = AlphaComposite.CLEAR;
		rules[3] = AlphaComposite.SRC;
		rules[4] = AlphaComposite.SRC_IN;
		rules[5] = AlphaComposite.DST_IN;
		rules[6] = AlphaComposite.SRC_OUT;
		rules[7] = AlphaComposite.DST_OUT;
		
		g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC,alpha));
		
        //int x, int y, int width, int height
        
        if (linecoler != null) {
	        g2d.setPaint(linecoler);
	        g2d.draw(new Ellipse2D.Double(x,y,width,height));
        }
        
        if (fillColor != null) {
        	g2d.setPaint(fillColor);
            g2d.fill(new Ellipse2D.Double(x,y,width,height));
        }
        
    }
	
	public void paintRect2D(Graphics2D g2d, double x, double y, double width, double height, 
			Color linecoler, int thickness, Color fillColor, float alpha) throws Exception {
		
		g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		int[] rules = new int[8]; 
		
		//all possible Compositing Rules:
		rules[0] = AlphaComposite.SRC_OVER;
		rules[1] = AlphaComposite.DST_OVER;
		rules[2] = AlphaComposite.CLEAR;
		rules[3] = AlphaComposite.SRC;
		rules[4] = AlphaComposite.SRC_IN;
		rules[5] = AlphaComposite.DST_IN;
		rules[6] = AlphaComposite.SRC_OUT;
		rules[7] = AlphaComposite.DST_OUT;
		
		g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC,alpha));
		
        //int x, int y, int width, int height
        
        if (linecoler != null) {
	        g2d.setPaint(linecoler);
	        g2d.draw(new Rectangle2D.Double(x,y,width,height));
        }
        
        if (fillColor != null) {
        	g2d.setPaint(fillColor);
            g2d.fill(new Rectangle2D.Double(x,y,width,height));
        }
        
    }
	
	public void paintRect(Graphics2D g2d, int x, int y, int width, int height, 
			Color linecoler, int thickness, Color fillColor) throws Exception {
		
		//int x, int y, int width, int height
        //g2d.fill(new Rectangle(x,y,width,height));
        
        g2d.setStroke(new BasicStroke(thickness));
        
        if (linecoler != null) {
	        g2d.setPaint(linecoler);
	        g2d.drawRect(x, y, width, height);
        }
        
        if (fillColor != null) {
        	g2d.setPaint(fillColor);
	        g2d.fillRect(x, y, width, height);
        }
        
    }
	
	public void paintLine(Graphics2D g2d, int x1, int y1, int x2, int y2, Color col) throws Exception {
		g2d.setPaint(col);
		g2d.drawLine(x1, y1, x2, y2);
	}
	
	public void drawThickLine2D(Graphics2D g2d, double x1, double y1, double x2, double y2, 
			int width, Color c, double xObj, double yObj, float alpha) throws Exception {
		g2d.setPaint(c);
		
		int[] rules = new int[8]; 
		
		//all possible Compositing Rules:
		rules[0] = AlphaComposite.SRC_OVER;
		rules[1] = AlphaComposite.DST_OVER;
		rules[2] = AlphaComposite.CLEAR;
		rules[3] = AlphaComposite.SRC;
		rules[4] = AlphaComposite.SRC_IN;
		rules[5] = AlphaComposite.DST_IN;
		rules[6] = AlphaComposite.SRC_OUT;
		rules[7] = AlphaComposite.DST_OUT;
		
		g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC,alpha));
		g2d.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		Line2D line = new Line2D.Double(x1+xObj, y1+yObj, x2+xObj, y2+yObj);
	    g2d.draw(line);
	}

	public void drawThickLine(Graphics2D g2d, int x1, int y1, int x2, int y2, int width, Color c) throws Exception {
		g2d.setPaint(c);
		g2d.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
	    g2d.drawLine(x1, y1, x2, y2);
	}	
	
	public void paintTextByWidthHeightAndDocument(SVGGraphics2D g2d, int x1, int y1, int width, int height,
			String text, int style, int size, Color fontColor, Document document) throws Exception {

		//String default_export_font = Configurationmanagement.getInstance().getConfKey(3,"default_export_font").getConf_value();
		
		this.drawText(g2d, x1, y1, width, height, text, "Verdana", style, size, fontColor, document);

	}

	public void drawText(SVGGraphics2D g2d, int x, int y, int width, int height, 
			String text, String default_export_font, int style, int size, Color fontColor,
			Document document) throws Exception {
		
//		g2d.setClip(x, y, width, height);
//		g2d.setColor(Color.black);
//		g2d.drawString(text, x, y+20);
		
		String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;

		// Get the root element (the 'svg' element).
		Element svgRoot = document.getDocumentElement();

		log.debug("svgNS DEBUG: "+svgNS);
		
		//Element textElement = g2d.getDOMFactory().createElementNS(svgNS,"text");
		Element rectangle = document.createElementNS(svgNS, "rect");
		rectangle.setAttributeNS(svgNS, "x", "10");
		rectangle.setAttributeNS(svgNS, "y", "20");
		rectangle.setAttributeNS(svgNS, "width", "100");
		rectangle.setAttributeNS(svgNS, "height", "50");
		rectangle.setAttributeNS(svgNS, "fill", "red");

		// Attach the rectangle to the root 'svg' element.
		svgRoot.appendChild(rectangle);


	}
	
	public void drawTextByString(SVGGraphics2D g2d, int x, int y, int width, int height, 
			String text, String default_export_font, int style, int size, Color fontColor) throws Exception {
		
		//g2d.setClip(x, y, width, height);
		g2d.setColor(Color.black);
		g2d.drawString(text, x, y);
		
		
	}

	public void _drawText(Graphics2D g2d, int x, int y, int width, int height, 
			String text, String default_export_font, int style, int size, Color fontColor) throws Exception {
		
//		g2d.setClip(x, y, width, height);
//		g2d.setColor(Color.black);
//		g2d.drawString(text, x, y+20);
		
		//Font font = new Font("Verdana", Font.PLAIN, 11);
		Font font = new Font(default_export_font, style, size);

		String[] stringsText = text.split("\r");
		log.debug("TEXT: "+stringsText);
		log.debug("TEXT: "+stringsText.length);
		
		String newText = "";
		
		for (int i=0;i<stringsText.length;i++) {
			newText += stringsText[i];
			if (i+1<stringsText.length) {
				newText += "\n";
			}
		}
		
		JTextArea n = new JTextArea( newText );
		n.setFont(font);
		n.setWrapStyleWord( true );
		n.setLineWrap( true );
		n.setForeground( fontColor );
		
		log.debug("Text at: "+x+" "+y);
			//int x, int y, int width, int height
		n.setBounds( x, y , width, height );
		n.setOpaque( false );
		
		//Text
		SVGGraphics2D svgGenerator2 = (SVGGraphics2D) g2d.create(x, y, width, height);
		svgGenerator2.setColor(fontColor);
		svgGenerator2.setPaint(fontColor);

		//svgGenerator2.create(x, y, width, height);
		//svgGenerator2.draw(.dra)
		n.paint( svgGenerator2 );
		
		//n.paintComponents(svgGenerator2);
		//n.setBounds( x, y , width, height );

		
	}
}
