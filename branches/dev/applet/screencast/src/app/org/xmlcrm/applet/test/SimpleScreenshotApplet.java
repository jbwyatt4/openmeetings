package org.xmlcrm.applet.test;

import java.applet.Applet;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class SimpleScreenshotApplet extends Applet {
	
	public void init(){
		System.out.println("init");
		captureScreen();
	}
	
	public void captureScreen(){
		try {
			// capture the whole screen
			BufferedImage screencapture = new Robot().createScreenCapture( new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) );
			
			// Save as JPEG
			File file = new File("screencapture.jpg");
			ImageIO.write(screencapture, "jpg", file);
			
			// Save as PNG
			// File file = new File("screencapture.png");
			// ImageIO.write(screencapture, "png", file);	
		} catch (Exception err) {
			System.out.println("Exception: "+err);
		}
	}

}
