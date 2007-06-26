package org.xmlcrm.webstart.gui;

import java.util.Date;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.quartz.impl.StdSchedulerFactory;
import org.quartz.SchedulerFactory;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.JobDetail;

import org.xmlcrm.webstart.screen.ScreenJob;
import org.xmlcrm.webstart.screen.ConnectionBean;

public class StartScreen {

	JTextArea textArea;

	java.awt.Container contentPane;
	
	SchedulerFactory schedFact;
	
	Scheduler sched;

	JFrame t;
	JButton startButton;
	JButton stopButton;

	public void randomFile() {
		try {
			
			schedFact = new StdSchedulerFactory();
			sched = schedFact.getScheduler();	
			sched.start();
			
			t = new JFrame("Desktop Publisher");
			contentPane = t.getContentPane();
			contentPane.setBackground(Color.white);
			textArea = new JTextArea();
			contentPane.setLayout(null);
			contentPane.add(textArea);
			textArea.setText("This application will publish your screen");
			textArea.setBounds(10, 0, 400, 50);
			
			startButton = new JButton( "start Sharing" );
			startButton.addActionListener( new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					captureScreenStart();
				}
			});
			startButton.setBounds(10, 50, 200, 24);
			t.add(startButton);
			
			
			stopButton = new JButton( "stop Sharing" );
			stopButton.addActionListener( new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					captureScreenStop();
				}
			});
			stopButton.setBounds(10, 80, 200, 24);
			stopButton.setEnabled(false);
			t.add(stopButton);	
			
			t.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					t.setVisible(false);
					System.exit(0);
				}
	
			});
			t.pack();
			t.setSize(400, 200);
			t.setVisible(true);
			
			System.err.println("initialized");
		} catch (Exception err) {
			System.out.println("randomFile Exception: ");
			System.err.println(err);
		}
	}
	
	void captureScreenStart(){
		try {
			
			System.err.println("captureScreenStart");
			
			JobDetail jobDetail = new JobDetail(ConnectionBean.quartzScreenJobName, sched.DEFAULT_GROUP, ScreenJob.class); 
			
			Trigger trigger = TriggerUtils.makeSecondlyTrigger(ConnectionBean.intervallSeconds);
			trigger.setStartTime(new Date());
			trigger.setName("myTrigger");
			
			sched.scheduleJob(jobDetail, trigger);
			
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			
		} catch (Exception err) {
			System.out.println("captureScreenStart Exception: ");
			System.err.println(err);
			textArea.setText("Exception: "+err);
		}
	}
	
	void captureScreenStop(){
		try {
			sched.deleteJob(ConnectionBean.quartzScreenJobName, sched.DEFAULT_GROUP);
			startButton.setEnabled(true);
			stopButton.setEnabled(false);			
		} catch (Exception err) {
			System.out.println("captureScreenStop Exception: ");
			System.err.println(err);
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
