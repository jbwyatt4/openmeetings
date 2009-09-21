package org.openmeetings.client.gui;

import java.util.List;
import java.util.LinkedList;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.zip.GZIPInputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.openmeetings.client.beans.ClientConnectionBean;
import org.openmeetings.client.beans.ClientFrameBean;
import org.openmeetings.client.beans.ClientViewerRegisterBean;
import org.openmeetings.client.transport.ClientTransportMinaPool;

/**
 * @author sebastianwagner
 *
 */
public class ClientViewerScreen {
	
	private static Logger log = Logger.getLogger(ClientViewerScreen.class);

	public static ClientViewerScreen instance = null;
	
	java.awt.Container contentPane;
	JFrame t;
	
	JButton exitButton;
	JLabel textWarningArea;
	JScrollPane scrollPane;
	JPanel scrollContent;
	
	private List<ImagePanel> imageScreens = new LinkedList<ImagePanel>();
	
	public ClientViewerScreen(String host, String port, String SID, String room, String domain, String publicSID, String record){
		log.debug("captureScreenStop START ");
		
		//JOptionPane.showMessageDialog(t, "publicSID: "+publicSID);
		
		ClientConnectionBean.isViewer = true;
		ClientConnectionBean.port = Integer.parseInt(port);
		ClientConnectionBean.host = host;
		ClientConnectionBean.SID = SID;
		ClientConnectionBean.room = room;
		ClientConnectionBean.domain = domain;	
		ClientConnectionBean.publicSID = publicSID;
		ClientConnectionBean.record = record;
		
		if (ClientConnectionBean.record == "yes") {
			ClientConnectionBean.mode = 1;
		}
		
		instance=this;
		
		//instance.showBandwidthWarning("StartScreen: "+SID+" "+room+" "+domain+" "+url);
		this.initMainFrame();
	}
	
	private void initMainFrame() {
		try {
			// TODO Auto-generated method stub
			UIManager.setLookAndFeel(new com.incors.plaf.kunststoff.KunststoffLookAndFeel());
	
	
	//		 make Web Start happy
	//		 see http://developer.java.sun.com/developer/bugParade/bugs/4155617.html
			UIManager.getLookAndFeelDefaults().put( "ClassLoader", getClass().getClassLoader()  );
			
			Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			
			t = new JFrame("Desktop Publisher");
			contentPane = t.getContentPane();
			contentPane.setBackground(Color.WHITE);
			contentPane.setLayout(null);
			
			textWarningArea = new JLabel();
			contentPane.add(textWarningArea);
			textWarningArea.setBounds(2, screenSize.height-150, 400, 30);
			
			exitButton = new JButton( "exit" );
			exitButton.addActionListener( new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					t.setVisible(false);
					System.exit(0);
				}
			});
			exitButton.setBounds(screenSize.width-300, screenSize.height-150, 190, 24);
			contentPane.add(exitButton);
			
			
			
			scrollContent = new JPanel();
			
			scrollContent.setPreferredSize(new Dimension(1600,1400));
			//scrollContent.setBounds(0, 0, 300, 1400);
			
			//, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	        //ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
			
			scrollPane = new JScrollPane(scrollContent);
			//scrollPane.setPreferredSize(new Dimension(300, 250));
			
			scrollPane.setBounds(0, 0, screenSize.width-100, screenSize.height-154);
			
			scrollPane.setViewportView(scrollContent);
			
			scrollPane.setViewportBorder(
	                BorderFactory.createLineBorder(Color.black));

			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			
			//scrollPane.setColumnHeaderView(columnView);
			//scrollPane.setRowHeaderView(rowView);

//			scrollPane.setCorner(JScrollPane.LOWER_LEFT_CORNER,
//			                    new ClientCorner());
//			scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER,
//			                    new ClientCorner());


			//scrollPane.set
			
			contentPane.add(scrollPane);
			
			t.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					t.setVisible(false);
					System.exit(0);
				}
	
			});
			
			t.pack();
			t.setLocation(50, 50);
			t.setSize(screenSize.width-100, screenSize.height-100);
			t.setVisible(true);
			t.setResizable(false);
			
			log.debug("initialized");
			
			
			
			
			ClientTransportMinaPool.startConnections();
			
		
		} catch (Exception err) {
			
			log.error("[initMainFrame]",err);
			
		}
		
	}
	
	public void showBandwidthWarning(String warning){
		textWarningArea.setText(warning);
		//JOptionPane.showMessageDialog(t, warning);
	}
	
	/**
	 * 
	 */
	public void doInitMessage() {
		try {
			
			ClientViewerRegisterBean clientViewerRegisterBean = new ClientViewerRegisterBean();
			clientViewerRegisterBean.setMode(5);
			clientViewerRegisterBean.setPublicSID(ClientConnectionBean.publicSID);
			
			ClientTransportMinaPool.sendMessage(clientViewerRegisterBean);
			
		} catch (Exception err) {
			
			log.error("[doInitMessage]",err);
			
		}
	}
	
	public void addClientFrameBean(ClientFrameBean clientFrameBean) {
		try {
			
//			String imagePath_1 = "pic_"+clientFrameBean.getSequenceNumber()+".gzip";
//			FileOutputStream fos_1 = new FileOutputStream(imagePath_1);
//			fos_1.write(clientFrameBean.getImageBytes());
//			fos_1.close();
			
			ByteArrayInputStream byteGzipIn = new ByteArrayInputStream(clientFrameBean.getImageBytes());
    		GZIPInputStream gZipIn = new GZIPInputStream(byteGzipIn);

    		ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
    		
    		byte[] buffer = new byte[1024];
    		int count = 0;
    		while ((count = gZipIn.read(buffer)) > 0 ){
    			bytesOut.write(buffer,0,count);
			}
			bytesOut.close();
			gZipIn.close();
			
//			BufferedImage bufferedImage = new BufferedImage(bytesOut.toByteArray());
//			BufferedImage image = new BufferedImage(clientFrameBean.getWidth(), clientFrameBean.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
//			Graphics graphics = image.createGraphics();
//
//			graphics.
			
//			String imagePath_2 = "pic_"+clientFrameBean.getSequenceNumber()+".jpg";
//			FileOutputStream fos_2 = new FileOutputStream(imagePath_2);
//			fos_2.write(bytesOut.toByteArray());
//			fos_2.close();
			
			Image im_left = Toolkit.getDefaultToolkit().createImage(bytesOut.toByteArray());
			
			ImagePanel iPanel = this.getImagePanel(clientFrameBean.getXValue(), clientFrameBean.getYValue(), clientFrameBean.getWidth(), clientFrameBean.getHeight());
			
			log.debug("PANEL SET IMAGE "+bytesOut.toByteArray().length);
			
			iPanel.setImages(im_left);
			
			
			
			contentPane.repaint();
			//t.setVisible(true);
			
			
		} catch (Exception err) {
			log.error("[add]",err);
		}
	}
	
	public ImagePanel getImagePanel(int x, int y, int width, int height){
		try {
			
			for (ImagePanel iPanel : imageScreens) {
				
				if (iPanel.getXPosition() == x && iPanel.getYPosition() == y) {
					return iPanel;
				}
				
			}
			
			ImagePanel iPanel = new ImagePanel(x,y);
			iPanel.setBounds(x, y, width, height);
			log.debug("NEW PANEL "+iPanel.getXPosition()+" "+iPanel.getYPosition());
			
			//contentPane.add(iPanel);
			
			scrollContent.add(iPanel);
			
			imageScreens.add(iPanel);
			
			return iPanel;
			
		} catch (Exception err) {
			log.error("[getImagePanel]",err);
		}
		return null;
	}

	public static void main(String[] args){
		String host = args[0];
		String port = args[1];
		String SID = args[2]; 
		String room = args[3];
		String domain = args[4];
		String publicSID = args[5];
		String record = args[6];
		new ClientViewerScreen(host,port,SID,room,domain,publicSID,record);
	}

	

}
