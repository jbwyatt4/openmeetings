package org.xmlcrm.webstart.app;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.applet.*;
import javax.swing.*;
import javax.jnlp.*;

public class StartScreen {
	FileOpenService fos;

	JNLPRandomAccessFile raf;

	FileContents fc;

	static int count = 0;

	JTextArea textArea;

	java.awt.Container contentPane;

	JFrame t;

	public void randomFile() {
		t = new JFrame("JNLPRandomAccessFile API demo");
		contentPane = t.getContentPane();
		contentPane.setLayout(new FlowLayout());
		contentPane.setBackground(Color.white);
		textArea = new JTextArea();
		contentPane.add(textArea);
		textArea.setText("Demonstrating usage of random access file. \n");
		textArea.append("It behaves like a large array of bytes stored in the file system \n");
		textArea.append("Pick a file to store on the local system \n");
		t.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				t.setVisible(false);
				System.exit(0);
			}

		});
		t.pack();
		t.setSize(500, 400);
		t.setVisible(true);

	}
	
	public StartScreen(){
		this.randomFile();
	}


	public static void main(String args[]) {

		StartScreen startScreen = new StartScreen();

	}
}
