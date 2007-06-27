package org.xmlcrm.webstart.gui;

import java.util.Date;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.quartz.impl.StdSchedulerFactory;
import org.quartz.SchedulerFactory;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.JobDetail;

import org.xmlcrm.webstart.screen.ScreenJob;
import org.xmlcrm.webstart.screen.ConnectionBean;

public class StartScreen {

	

	java.awt.Container contentPane;
	
	SchedulerFactory schedFact;
	
	Scheduler sched;

	JFrame t;
	JTextArea textArea;
	JTextArea textAreaQualy;
	JButton startButton;
	JButton stopButton;
	JButton exitButton;
	JSpinner jSpin;

	public void initMainFrame() {
		try {

			UIManager.setLookAndFeel(new com.incors.plaf.kunststoff.KunststoffLookAndFeel());


//			 make Web Start happy
//			 see http://developer.java.sun.com/developer/bugParade/bugs/4155617.html
			UIManager.getLookAndFeelDefaults().put( "ClassLoader", getClass().getClassLoader()  );
			
			
			schedFact = new StdSchedulerFactory();
			sched = schedFact.getScheduler();	
			sched.start();
			
			t = new JFrame("Desktop Publisher");
			contentPane = t.getContentPane();
			contentPane.setBackground(Color.LIGHT_GRAY);
			textArea = new JTextArea();
			textArea.setBackground(Color.LIGHT_GRAY);
			contentPane.setLayout(null);
			contentPane.add(textArea);
			textArea.setText("This application will publish your screen");
			textArea.setBounds(10, 0, 400,24);
			
			
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
			
			jSpin = new JSpinner(new SpinnerNumberModel(60, 30, 100, 5));
			jSpin.setBounds(140, 110, 50, 24);
			jSpin.addChangeListener( new ChangeListener(){
				public void stateChanged(ChangeEvent arg0) {
					// TODO Auto-generated method stub
					setNewStepperValues();
				}	
			});
			t.add(jSpin);	
			
			textAreaQualy = new JTextArea();
			contentPane.add(textAreaQualy);
			textAreaQualy.setText("Quality (%)");
			textAreaQualy.setBackground(Color.LIGHT_GRAY);
			textAreaQualy.setBounds(10, 110, 400, 24);	
			
			exitButton = new JButton( "exit" );
			exitButton.addActionListener( new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					t.setVisible(false);
					System.exit(0);
				}
			});
			exitButton.setBounds(190, 170, 200, 24);
			t.add(exitButton);				
			
			t.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					t.setVisible(false);
					System.exit(0);
				}
	
			});
			t.pack();
			t.setSize(400, 240);
			t.setVisible(true);
			
			System.err.println("initialized");
		} catch (Exception err) {
			System.out.println("randomFile Exception: ");
			System.err.println(err);
		}
	}
	
	void setNewStepperValues(){
		//System.out.println(jSpin.getValue());
		ConnectionBean.imgQuality=new Float(Double.valueOf(jSpin.getValue().toString())/100);
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
	
	public StartScreen(String url, String SID, String room, String domain){
		System.out.println("captureScreenStop Exception: ");
		System.err.println("captureScreenStop Exception: ");
		ConnectionBean.connectionURL = url;
		ConnectionBean.SID = SID;
		ConnectionBean.room = room;
		ConnectionBean.domain = domain;		
		this.initMainFrame();
	}
	
	public static void main(String[] args){
		String url = args[0];
		String SID = args[1]; 
		String room = args[2];
		String domain = args[3];
		new StartScreen(url,SID,room,domain);
	}

}
