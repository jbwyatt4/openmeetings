package org.openmeetings.app.batik.serlvets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;

import javax.servlet.http.HttpServlet;
import javax.swing.JTextArea;

import org.apache.batik.svggen.SVGGraphics2D;
import org.openmeetings.app.data.basic.Configurationmanagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractBatikServlet extends HttpServlet {
	
	private static final Logger log = LoggerFactory.getLogger(AbstractBatikServlet.class);
	
	public void paintDiagramText(Graphics2D g2d, int x1, int y1, int x2, int y2, 
			String text, int style, int size, Color fontColor) throws Exception {

		int width = x2-x1;
		int height = y2-y1;
		 
		String default_export_font = Configurationmanagement.getInstance().getConfKey(3,"default_export_font").getConf_value();
		
		
		this.drawText(g2d, x1+1, y1+1, width-2, height-2, text, default_export_font, style, size, fontColor);

	}
	
//	//Border
//	this.paintRect(g2d, x1, y1, width, height, new Color(0,0,0));
//	
//	//Fill
//	this.paintRect(g2d, x1+1, y1+1, width-2, height-2, new Color(255,255,255));
	
	
	public void paintRect(Graphics2D g2d, int x, int y, int width, int height, Color col) throws Exception {
        g2d.setPaint(col);
        		//int x, int y, int width, int height
        g2d.fill(new Rectangle(x,y,width,height));
        
    }


	public void drawText(Graphics2D g2d, int x, int y, int width, int height, 
			String text, String default_export_font, int style, int size, Color fontColor) throws Exception {
		
//		g2d.setClip(x, y, width, height);
//		g2d.setColor(Color.black);
//		g2d.drawString(text, x, y+20);
		
		//Font font = new Font("Verdana", Font.PLAIN, 11);
		Font font = new Font(default_export_font, Font.PLAIN, size);

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
