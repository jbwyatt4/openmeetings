package org.xmlcrm.webstart.app;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.*;
import java.applet.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.jnlp.*;

public class StartScreen {

	JTextArea textArea;

	java.awt.Container contentPane;

	JFrame t;

	public void randomFile() {
		
		t = new JFrame("Desktop Publisher");
		contentPane = t.getContentPane();
		contentPane.setBackground(Color.white);
		textArea = new JTextArea();
		contentPane.setLayout(null);
		contentPane.add(textArea);
		textArea.setText("This application will publish your screen");
		textArea.setBounds(10, 0, 400, 50);
		
		Button button = new Button( "start" );
		button.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				captureScreen();
			}
		});
		button.setBounds(10, 50, 200, 24);
		t.add(button);
		
		t.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				t.setVisible(false);
				System.exit(0);
			}

		});
		t.pack();
		t.setSize(400, 200);
		t.setVisible(true);

	}
	
	void captureScreen(){
		try {
			// capture the whole screen
			BufferedImage screencapture = new Robot().createScreenCapture( new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) );
			
			// Save as JPEG
			File file = new File("screencapture.jpg");
			ImageIO.write(screencapture, "jpg", file);
			
			String filePath = file.getAbsolutePath();
			
			textArea.setText("PATH: "+filePath);
			
			// Save as PNG
			// File file = new File("screencapture.png");
			// ImageIO.write(screencapture, "png", file);	
		} catch (Exception err) {
			System.out.println("Exception: "+err);
			textArea.setText("Exception: "+err);
		}
	}
	
	public StartScreen(){
		this.randomFile();
	}


	public static void main(String args[]) {

		StartScreen startScreen = new StartScreen();

	}
}
