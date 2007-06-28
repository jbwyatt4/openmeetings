package org.xmlcrm.webstart.gui;

import java.util.Date;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

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
import org.xmlcrm.webstart.screen.BlankArea;

public class StartScreen {

	public static StartScreen instance = null;

	java.awt.Container contentPane;
	
	SchedulerFactory schedFact;
	
	Scheduler sched;

	JFrame t;
	JLabel textArea;
	JLabel textAreaQualy;
	JButton startButton;
	JButton stopButton;
	JButton exitButton;
	JSpinner jSpin;
	JLabel tFieldScreenZoom;
	JLabel blankArea;
	BlankArea virtualScreen;
	JLabel vscreenXLabel;
	JLabel vscreenYLabel;
	JSpinner jVScreenXSpin;
	JSpinner jVScreenYSpin;
	JLabel vscreenWidthLabel;
	JLabel vscreenHeightLabel;
	JSpinner jVScreenWidthSpin;
	JSpinner jVScreenHeightSpin;	

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
			textArea = new JLabel();
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
			stopButton.setBounds(220, 50, 200, 24);
			stopButton.setEnabled(false);
			t.add(stopButton);	
			
			jSpin = new JSpinner(new SpinnerNumberModel(60, 30, 100, 5));
			jSpin.setBounds(140, 80, 50, 24);
			jSpin.addChangeListener( new ChangeListener(){
				public void stateChanged(ChangeEvent arg0) {
					// TODO Auto-generated method stub
					setNewStepperValues();
				}	
			});
			t.add(jSpin);	
			
			textAreaQualy = new JLabel();
			contentPane.add(textAreaQualy);
			textAreaQualy.setText("Quality (%)");
			textAreaQualy.setBackground(Color.LIGHT_GRAY);
			textAreaQualy.setBounds(10, 80, 100, 24);	
			
			addVirtualScreen();
			
			exitButton = new JButton( "exit" );
			exitButton.addActionListener( new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					t.setVisible(false);
					System.exit(0);
				}
			});
			exitButton.setBounds(190, 370, 200, 24);
			t.add(exitButton);
			
			t.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					t.setVisible(false);
					System.exit(0);
				}
	
			});
			t.pack();
			t.setSize(500, 440);
			t.setVisible(true);
			t.setResizable(false);
			
			System.err.println("initialized");
		} catch (Exception err) {
			System.out.println("randomFile Exception: ");
			err.printStackTrace();
		}
	}
	
	void setNewStepperValues(){
		//System.out.println(jSpin.getValue());
		ConnectionBean.imgQuality=new Float(Double.valueOf(jSpin.getValue().toString())/100);
	}
	
	public void showBandwidthWarning(){
		JOptionPane.showMessageDialog(t, "Your Bandwidth is bad. Frames have been droped. You can alter the Quality settings to reduce Bandwidth usage.");
	}
	
	void addVirtualScreen() throws Exception{
		tFieldScreenZoom = new JLabel();
		tFieldScreenZoom.setBounds(10, 140, 200, 20);
		tFieldScreenZoom.setText("Select your screen Area:");
		t.add(tFieldScreenZoom);
		
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double ratio = screenSize.getHeight()/screenSize.getWidth();

		int width_2 = ConnectionBean.vScreenWidth;
		int height_2 = Long.valueOf(Math.round(width_2*ratio)).intValue();
		
		int width = ConnectionBean.vScreenWidth;
		int height = Long.valueOf(Math.round(width*ratio)).intValue();

		virtualScreen = new BlankArea(new Color(255,255,255,100));
		virtualScreen.setOpaque(true);
		virtualScreen.setText("Screen: "+Double.valueOf(screenSize.getWidth()).intValue()+":"+Double.valueOf(screenSize.getHeight()).intValue());
		virtualScreen.setBounds(30, 170, width_2, height_2);
		t.add(virtualScreen);			
		
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot = new Robot();
		BufferedImage imageScreen = robot.createScreenCapture(screenRectangle);		
		
		Image img = imageScreen.getScaledInstance(width, height ,Image.SCALE_SMOOTH);
		//imageScreen.
		System.out.println("img"+img);
		ImageIcon image = new ImageIcon(img);
		
		blankArea = new JLabel(image);
		blankArea.setBounds(30, 170, width, height);
		t.add(blankArea);
		
		vscreenXLabel = new JLabel();
		vscreenXLabel.setText("SharingScreen X:");
		vscreenXLabel.setBounds(250, 170, 150, 24);
		t.add(vscreenXLabel);
		
		jVScreenXSpin = new JSpinner(new SpinnerNumberModel(0, 0, Double.valueOf(screenSize.getWidth()).intValue(), 1));
		jVScreenXSpin.setBounds(400, 170, 60, 24);
		jVScreenXSpin.addChangeListener( new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				calcNewValueXSpin();
			}	
		});	
		t.add(jVScreenXSpin);
		
		vscreenYLabel = new JLabel();
		vscreenYLabel.setText("SharingScreen Y:");
		vscreenYLabel.setBounds(250, 200, 150, 24);
		t.add(vscreenYLabel);
		
		jVScreenYSpin = new JSpinner(new SpinnerNumberModel(0, 0, Double.valueOf(screenSize.getHeight()).intValue(), 1));
		jVScreenYSpin.setBounds(400, 200, 60, 24);
		jVScreenYSpin.addChangeListener( new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				calcNewValueYSpin();
			}	
		});	
		t.add(jVScreenYSpin);
		
		vscreenWidthLabel = new JLabel();
		vscreenWidthLabel.setText("SharingScreen Width:");
		vscreenWidthLabel.setBounds(250, 240, 150, 24);
		t.add(vscreenWidthLabel);
		
		jVScreenWidthSpin = new JSpinner(new SpinnerNumberModel(Double.valueOf(screenSize.getWidth()).intValue(), 0, Double.valueOf(screenSize.getWidth()).intValue(), 1));
		jVScreenWidthSpin.setBounds(400, 240, 60, 24);
		jVScreenWidthSpin.addChangeListener( new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				//setNewStepperValues();
			}	
		});	
		t.add(jVScreenWidthSpin);		
		
		vscreenHeightLabel = new JLabel();
		vscreenHeightLabel.setText("SharingScreen Height:");
		vscreenHeightLabel.setBounds(250, 270, 150, 24);
		t.add(vscreenHeightLabel);
		
		jVScreenHeightSpin = new JSpinner(new SpinnerNumberModel(Double.valueOf(screenSize.getHeight()).intValue(), 0, Double.valueOf(screenSize.getHeight()).intValue(), 1));
		jVScreenHeightSpin.setBounds(400, 270, 60, 24);
		jVScreenHeightSpin.addChangeListener( new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				//setNewStepperValues();
			}	
		});	
		t.add(jVScreenHeightSpin);	
		
	}
	
	void calcNewValueXSpin(){
		
	}	
	
	void calcNewValueYSpin(){
		
	}
	
	void captureScreenStart(){
		try {
			
			System.err.println("captureScreenStart");
			
			JobDetail jobDetail = new JobDetail(ConnectionBean.quartzScreenJobName, Scheduler.DEFAULT_GROUP, ScreenJob.class); 
			
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
			sched.deleteJob(ConnectionBean.quartzScreenJobName, Scheduler.DEFAULT_GROUP);
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
		instance = new StartScreen(url,SID,room,domain);
	}

}
