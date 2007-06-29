package org.xmlcrm.webstart.screen;

import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;

import org.xmlcrm.webstart.beans.ConnectionBean;

import java.net.URLConnection;
import java.net.URL;
import java.awt.image.BufferedImage;

import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGCodec;

public class CaptureScreen {

	public static void main(String[] args) {
		new CaptureScreen("http://192.168.2.103:5080/xmlcrm/ScreenServlet","1010","1","public");
	}

	public CaptureScreen(String url, String SID, String room, String domain) {
		try {
			System.err.println("captureScreenStart");
			ConnectionBean.isloading = true;
			this.captureScreen(url+"?sid="+SID+"&room="+room+"&domain="+domain,"myscreenRemote.jpg");
		} catch (Exception io) {
			System.err.println(io);
			System.out.println(io);
		}

	}

	public void captureScreen(String url, String fileName) throws Exception {
		
		this.sendJpegToUrl(this.bufferImage(), url, fileName);

	}

	public byte[] bufferImage() {
		try {

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage imageScreen = robot.createScreenCapture(screenRectangle);
			
			

			//Scale the image ro reduce size

			int width = imageScreen.getWidth();
			int height = imageScreen.getHeight();

			int thumbWidth = 600;
			int div = width / thumbWidth;
			height = height / div;
			
			Image img = imageScreen.getScaledInstance(thumbWidth, height ,Image.SCALE_SMOOTH);

			BufferedImage image = new BufferedImage(thumbWidth, height,BufferedImage.TYPE_INT_RGB);
			Graphics2D biContext = image.createGraphics();
			biContext.drawImage(img, 0, 0, null);

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam encpar = encoder.getDefaultJPEGEncodeParam(image);
			encpar.setQuality(ConnectionBean.imgQuality, false);
			encoder.setJPEGEncodeParam(encpar);
			encoder.encode(image);

			return out.toByteArray();

		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException ioe) {
			System.out.println(ioe);
			ioe.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public void sendJpegToUrl(byte[] imageAsBytes, String url, String fileName) {
		try {
			
			System.out.println("sendJpegToUrl url  ");
			URL u = new URL(url);
			URLConnection c = u.openConnection();

			// post multipart data
			c.setDoOutput(true);
			c.setDoInput(true);
			c.setUseCaches(false);

			// set request headers
			c.setRequestProperty("Content-Type","multipart/form-data; boundary=AXi93A");

			// open a stream which can write to the url
			DataOutputStream dstream = new DataOutputStream(c.getOutputStream());

			// write content to the server, begin with the tag that says a content element is comming
			dstream.writeBytes("--AXi93A\r\n");

			// discribe the content
			dstream.writeBytes("Content-Disposition: form-data; name=\"Filedata\"; filename=\""+fileName+"\" \r\nContent-Type: image/jpeg\r\nContent-Transfer-Encoding: binary\r\n");
			dstream.write(imageAsBytes, 0, imageAsBytes.length);

			// close the multipart form request
			dstream.writeBytes("\r\n--AXi93A--\r\n\r\n");
			dstream.flush();
			dstream.close();
			
			System.out.println("sendJpegToUrl complete ");
			
		    // read the output from the URL
			try {
				DataInputStream in = new DataInputStream(
						new BufferedInputStream(c.getInputStream()));
				String sIn = in.readLine();
				while (sIn != null) {
					if (sIn != null) {
						System.out.println(sIn);
					}
					sIn = in.readLine();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
			ConnectionBean.isloading = false;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
