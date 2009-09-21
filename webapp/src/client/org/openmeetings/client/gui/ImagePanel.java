package org.openmeetings.client.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * @author sebastianwagner
 *
 */
public class ImagePanel extends JPanel {
	
	private static Logger log = Logger.getLogger(ImagePanel.class);

	
	private int xPosition = 0;
	private int yPosition = 0;
	private Image image;
	
	/**
	 * @param position
	 * @param position2
	 * @param image
	 *
	 * 20.09.2009 17:10:36
	 * sebastianwagner
	 * 
	 * 
	 */
	public ImagePanel(int xPosition, int yPosition) {
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}


	@Override
    public void paintComponent(Graphics g) {
		try {
	        super.paintComponent(g);
	
	        if (image != null) {
	
	        	log.debug("DRAW IMAGE "+this.xPosition+" "+this.yPosition);
	        	
	            boolean drawResult = g.drawImage(image, 0, 0, null);
	
	            while(!drawResult) {
	            	drawResult = g.drawImage(image, 0, 0, null);
	            }
	            
	            if (!drawResult) {
	            	throw new Exception("Could not Draw Image");
	            }
	        }
		} catch (Exception err) {
			log.error("[paintComponent]",err);
		}
    }
	
    public void setImages(Image image) {

        this.image = image;
        
        repaint();

    }
}
