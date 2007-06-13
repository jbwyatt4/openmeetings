package org.xmlcrm.servlet.outputhandler;

import http.utils.multipartrequest.MultipartRequest;
import http.utils.multipartrequest.ServletMultipartRequest;

import java.io.*;

import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.commons.transaction.util.FileHelper;
import org.apache.commons.lang.StringUtils;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.utils.stringhandlers.StringComparer;

public class UploadHandler extends HttpServlet {

	private static final Log log = LogFactory.getLog(UploadHandler.class);

	private int recursiveNumber = 0;

	protected HashMap<String, String> fileExtensions = new HashMap<String, String>();

	protected HashMap<String, String> pdfExtensions = new HashMap<String, String>();

	protected HashMap<String, String> imageExtensions = new HashMap<String, String>();

	protected HashMap<String, String> jpgExtensions = new HashMap<String, String>();

	public UploadHandler() {
		fileExtensions.put("ext1", ".ppt");
		fileExtensions.put("ext2", ".odp");
		fileExtensions.put("ext3", ".odt");
		fileExtensions.put("ext4", ".sxw");
		fileExtensions.put("ext5", ".wpd");
		fileExtensions.put("ext6", ".doc");
		fileExtensions.put("ext7", ".rtf");
		fileExtensions.put("ext8", ".txt");
		fileExtensions.put("ext9", ".ods");
		fileExtensions.put("ext10", ".sxc");
		fileExtensions.put("ext11", ".xls");
		fileExtensions.put("ext12", ".sxi");

		pdfExtensions.put("ext1", ".pdf");

		jpgExtensions.put("ext1", ".jpg");

		imageExtensions.put("ext1", ".png");
		imageExtensions.put("ext2", ".gif");
		imageExtensions.put("ext3", ".svg");
		imageExtensions.put("ext4", ".dpx"); //DPX
		imageExtensions.put("ext5", ".exr"); //EXR
		imageExtensions.put("ext6", ".pcd"); //PhotoCD
		imageExtensions.put("ext7", ".pcds"); //PhotoCD
		imageExtensions.put("ext8", ".ps"); //PostScript
		imageExtensions.put("ext9", ".psd"); //Adobe Photoshop bitmap file
		imageExtensions.put("ext10", ".tiff"); //Tagged Image File Format
		imageExtensions.put("ext11", ".ttf"); //TrueType font file
		imageExtensions.put("ext12", ".xcf"); //GIMP imag
		imageExtensions.put("ext13", ".wpg"); //Word Perfect Graphics File
		imageExtensions.put("ext14", ".txt"); //Raw text file
		imageExtensions.put("ext15", ".bmp");
		imageExtensions.put("ext16", ".ico"); //Microsoft Icon File
		imageExtensions.put("ext17", ".tga"); //Truevision Targa image

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void service(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException {
		try {

			if (httpServletRequest.getContentLength() > 0) {

				String sid = httpServletRequest.getParameter("sid");
				if (sid == null) {
					sid = "default";
				}
				System.out.println("sid: " + sid);

				Long users_id = Sessionmanagement.getInstance().checkSession(
						sid);
				long User_LEVEL = Usermanagement.getInstance()
						.getUserLevelByID(users_id);

				if (User_LEVEL > 0) {
					String room = httpServletRequest.getParameter("room");
					if (room == null) {
						room = "default";
					}
					String domain = httpServletRequest.getParameter("domain");
					if (domain == null) {
						domain = "default";
					}

					String moduleName = httpServletRequest
							.getParameter("moduleName");
					if (moduleName == null) {
						moduleName = "nomodule";
					}
					//make a complete name out of domain(organisation) + roomname
					String roomName = domain + "_" + room;
					//trim whitespaces cause it is a directory name
					roomName = StringUtils.deleteWhitespace(roomName);

					//Get the current User-Directory

					String current_dir = getServletContext().getRealPath("/");
					System.out.println("Current_dir: " + current_dir);

					String working_dir = "";
					String working_dirppt = "";
					System.out.println(MultipartRequest.MAX_READ_BYTES);

					working_dir = current_dir + File.separatorChar + "upload"
							+ File.separatorChar + roomName
							+ File.separatorChar;
					working_dirppt = current_dir + File.separatorChar
							+ "uploadtemp" + File.separatorChar + roomName
							+ File.separatorChar;

					//Add the Folder for the Room if it does not exist yet
					File localFolder = new File(working_dir);
					if (!localFolder.exists()) {
						localFolder.mkdir();
					}
					File localFolderppt = new File(working_dirppt);
					if (!localFolderppt.exists()) {
						localFolderppt.mkdir();
					}

					System.out.println("#### UploadHandler working_dir: "
							+ working_dir);

					if (!moduleName.equals("nomodule")) {
						//Check variable to see if this file is a presentation

						ServletMultipartRequest upload = new ServletMultipartRequest(httpServletRequest, 104857600); // max 100 mb

						InputStream is = upload.getFileContents("Filedata");

						//trim whitespace
						String fileSystemName = StringUtils
								.deleteWhitespace(upload.getFileSystemName("Filedata"));

						String newFileSystemName = StringComparer.getInstance()
								.compareForRealPaths(
										fileSystemName.substring(0,
												fileSystemName.length() - 4));
						String newFileSystemExtName = fileSystemName.substring(
								fileSystemName.length() - 4,
								fileSystemName.length()).toLowerCase();

						//trim long names cause cannot output that
						if (newFileSystemName.length() >= 17) {
							newFileSystemName = newFileSystemName.substring(0,
									16);
						}

						//check if this is a a file that can be converted by openoffice-service
						boolean canBeConverted = checkForConvertion(newFileSystemExtName);

						boolean isPDF = checkForPDF(newFileSystemExtName);

						boolean isImage = checkForImage(newFileSystemExtName);

						boolean isJpg = checkForJpg(newFileSystemExtName);

						String completeName = "";
						//if it is a presenation it will be copied to another place
						if (canBeConverted || isPDF || isImage) {
							completeName = working_dirppt + newFileSystemName;
						} else if (isJpg) {
							completeName = working_dir + newFileSystemName;
						} else {
							return;
						}

						log.debug("******** completeName: " + completeName
								+ canBeConverted);

						File f = new File(completeName + newFileSystemExtName);
						if (f.exists()) {
							System.out.println("File exisitert bereits");
							int recursiveNumber = 0;
							String tempd = completeName + "_" + recursiveNumber;
							while (f.exists()) {
								recursiveNumber++;
								tempd = completeName + "_" + recursiveNumber;
								f = new File(tempd + newFileSystemExtName);

							}
							completeName = tempd;
							System.out.println("Neuer Folder name "
									+ completeName);
						}

						System.out.println("*****2 ***** completeName: "
								+ completeName + newFileSystemExtName);
						log.debug("*****2 ******* completeName: "
								+ completeName + newFileSystemExtName);
						FileOutputStream fos = new FileOutputStream(
								completeName + newFileSystemExtName);

						byte[] buffer = new byte[1024];
						int len = 0;

						while (len != (-1)) {
							len = is.read(buffer, 0, 1024);
							if (len != (-1))
								fos.write(buffer, 0, len);
						}

						fos.close();
						is.close();

						if (canBeConverted) {
							//automatically convert to slides
							System.out
									.println("canBeConverted New Folder name "
											+ completeName);

							HashMap<String, String> ll = convertPDF(
									newFileSystemName + newFileSystemExtName,
									roomName, newFileSystemName, true);

							String outputfolder = ll.get("outputfolder");
							//now it should be completed so copy that file to the expected location
							System.out.println("Upload destinationFolder "
									+ outputfolder);

							//FileHelper fileHelper = new FileHelper();

							File pptToBeMoved = new File(completeName
									+ newFileSystemExtName);

//							System.out.println("outputfolder " + outputfolder);
//							System.out.println("newFileSystemName "+ newFileSystemName);
//							System.out.println("newFileSystemExtName "+ newFileSystemExtName);

							File pptWhereToMove = new File(outputfolder+ newFileSystemName + newFileSystemExtName);
							pptWhereToMove.createNewFile();

							FileHelper.moveRec(pptToBeMoved, pptWhereToMove);

						} else if (isPDF) {
							//automatically convert to slides
//							System.out.println("isPDF New Folder name "+ completeName);

							HashMap<String, String> ll = convertPDF(newFileSystemName + newFileSystemExtName,
									roomName, newFileSystemName, false);

							String outputfolder = ll.get("outputfolder");
							//now it should be completed so copy that file to the expected location
							System.out.println("Upload destinationFolder "+ outputfolder);

							//FileHelper fileHelper = new FileHelper();

							File pptToBeMoved = new File(completeName
									+ newFileSystemExtName);

							System.out.println("outputfolder " + outputfolder);
							System.out.println("newFileSystemName "
									+ newFileSystemName);
							System.out.println("newFileSystemExtName "
									+ newFileSystemExtName);

							File pptWhereToMove = new File(outputfolder
									+ newFileSystemName + newFileSystemExtName);
							pptWhereToMove.createNewFile();

							FileHelper.moveRec(pptToBeMoved, pptWhereToMove);

						} else if (isImage) {
							//convert it to JPG
							System.out.println("isImage New Folder name "
									+ completeName);
							convertImage(newFileSystemName
									+ newFileSystemExtName, roomName,
									newFileSystemName, false);

						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("ee " + e);
			e.printStackTrace();
			throw new ServletException(e);
		}

	}

	private boolean checkForConvertion(String fileExtension) throws Exception {
		Iterator<String> extensionIt = fileExtensions.keySet().iterator();
		while (extensionIt.hasNext()) {
			String fileExt = fileExtensions.get(extensionIt.next());
			if (fileExtension.equals(fileExt)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkForPDF(String fileExtension) throws Exception {
		Iterator<String> extensionIt = pdfExtensions.keySet().iterator();
		while (extensionIt.hasNext()) {
			String fileExt = pdfExtensions.get(extensionIt.next());
			if (fileExtension.equals(fileExt)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkForJpg(String fileExtension) throws Exception {
		Iterator<String> extensionIt = pdfExtensions.keySet().iterator();
		while (extensionIt.hasNext()) {
			String fileExt = jpgExtensions.get(extensionIt.next());
			if (fileExtension.equals(fileExt)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkForImage(String fileExtension) throws Exception {
		Iterator<String> extensionIt = pdfExtensions.keySet().iterator();
		while (extensionIt.hasNext()) {
			String fileExt = imageExtensions.get(extensionIt.next());
			if (fileExtension.equals(fileExt)) {
				return true;
			}
		}
		return false;
	}

	private HashMap<String, String> convertPDF(String fileNameExt,
			String roomName, String fileNameShort, boolean fullProcessing)
			throws Exception {
		//DocumentLocalConverter documentLocalConverter = new DocumentLocalConverter();

		//Get the current Directory
		String current_dir = getServletContext().getRealPath("/");
		//System.out.println("Current_dir: "+current_dir);

		String working_imgdir = "";
		String working_pptdir = "";
		//System.out.println(MultipartRequest.MAX_READ_BYTES);

		working_imgdir = current_dir + "upload" + File.separatorChar + roomName
				+ File.separatorChar;
		working_pptdir = current_dir + "uploadtemp" + File.separatorChar
				+ roomName + File.separatorChar;

		String fileFullPath = working_pptdir + fileNameExt;

		String newFolderName = fileNameExt.substring(0,
				fileNameExt.length() - 4);
		//System.out.println(newFolderName);
		String destinationFolder = working_imgdir + newFolderName;

		//System.out.println(destinationFolder);
		File f = new File(destinationFolder + File.separatorChar);
		if (f.exists()) {
			//System.out.println("Folder exisitert bereits");
			recursiveNumber = 0;
			String tempd = destinationFolder + "_" + recursiveNumber;
			while (f.exists()) {
				recursiveNumber++;
				tempd = destinationFolder + "_" + recursiveNumber;
				f = new File(tempd);

			}
			destinationFolder = tempd;
			//System.out.println("Neuer Folder name "+destinationFolder);
		}
		//System.out.println(destinationFolder+File.separatorChar+" ++++ "+f.exists());
		boolean b = f.mkdir();
		if (b) {
			//System.out.println("Folder wurde angelegt");
		} else {
			System.out.println("ERROR: Folder konnte nicht angelegt werden "
					+ f.getAbsolutePath());
		}
		String outputfolder = destinationFolder + File.separatorChar;
		destinationFolder = destinationFolder + File.separatorChar;

		HashMap<String, String> ll = new HashMap<String, String>();
		ll.put("destinationFolder", destinationFolder);
		ll.put("pptFullPath", fileFullPath);
		ll.put("outputfolder", outputfolder);

		//System.out.println("destinationFolder: "+destinationFolder+"/"+"   ### pptFullPath "+fileFullPath);
		//System.out.println("########### invoke NEW Converting");	

		String newPDF = null;
		if (fullProcessing) {
			newPDF = this.doConvertExec(fileFullPath, destinationFolder,
					fileNameShort);
		} else {
			newPDF = this.convertJpg(fileFullPath, destinationFolder);
		}
		if (!newPDF.equals(null)) {
			System.out.println("<ERROR2>");
			System.out.println(newPDF);
			System.out.println("<ERROR2>");
		}
		//documentLocalConverter.startConverting(pptFullPath, destinationFolder);

		return ll;
	}

	private String convertImage(String fileNameExt, String roomName,
			String fileNameShort, boolean fullProcessing) throws Exception {
		//DocumentLocalConverter documentLocalConverter = new DocumentLocalConverter();

		//Get the current Directory
		String current_dir = getServletContext().getRealPath("/");
		//System.out.println("Current_dir: "+current_dir);

		String working_imgdir = "";
		String working_pptdir = "";
		//System.out.println(MultipartRequest.MAX_READ_BYTES);

		working_imgdir = current_dir + "upload" + File.separatorChar + roomName
				+ File.separatorChar;
		working_pptdir = current_dir + "uploadtemp" + File.separatorChar
				+ roomName + File.separatorChar;

		String fileFullPath = working_pptdir + fileNameExt;

		String newFileName = fileNameExt.substring(0, fileNameExt.length() - 4);
		String newFileNameExtensionOnly = fileNameExt.substring(fileNameExt
				.length() - 4, fileNameExt.length());

		System.out.println("File to Check: " + working_imgdir + newFileName
				+ newFileNameExtensionOnly);

		File f = new File(working_imgdir + newFileName
				+ newFileNameExtensionOnly);
		if (f.exists()) {
			System.out.println("File exisitert bereits");
			int recursiveNumber = 0;
			String tempd = newFileName + "_" + recursiveNumber;
			while (f.exists()) {
				recursiveNumber++;
				tempd = newFileName + "_" + recursiveNumber;
				f = new File(working_imgdir + tempd + newFileNameExtensionOnly);

			}
			newFileName = tempd;
			System.out.println("Neuer File name " + newFileName);
		}

		String destinationFile = working_imgdir + newFileName;

		//System.out.println("destinationFolder: "+destinationFolder+"/"+"   ### pptFullPath "+fileFullPath);
		//System.out.println("########### invoke NEW Converting");	

		String newImage = null;
		newImage = this.convertSingleJpg(fileFullPath, destinationFile);

		if (!newImage.equals(null)) {
			System.out.println("<ERROR2>");
			System.out.println(newImage);
			System.out.println("<ERROR2>");
		}

		//Delete old one
		File fToDelete = new File(fileFullPath);
		fToDelete.delete();

		return newImage;
	}

	//Start oo-service external cause it makes no sense here
	private String doConvertExec(String fileFullPath, String destinationFolder,
			String outputfile) {
		try {
			String current_dir = getServletContext().getRealPath("/");
			String runtimeFile = "jodconverter.bat";
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				runtimeFile = "jodconverter.sh";
			}
			Runtime rt = Runtime.getRuntime();

			String command = current_dir + "jod" + File.separatorChar
					+ runtimeFile + " java " + fileFullPath + " "
					+ destinationFolder + outputfile + ".pdf " + current_dir
					+ "jod" + File.separatorChar;
			// String command2 = "java -cp .:"+pre+"ridl.jar:"+pre+"js.jar:"+pre+"juh.jar:"+pre+"jurt.jar:"+pre+"jut.jar:"+pre+"java_uno.jar:"+pre+"java_uno_accessbridge.jar:"+pre+"edtftpj-1.5.2.jar:"+pre+"unoil.jar:"+pre+"dokeosupload.jar org.dokeos.ooconverter.DocumentLocalConverterMain "+pptFullPath+" "+destinationFolder;
			System.out.println("command2: " + command);
			Process proc = rt.exec(command);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			System.out.println("<ERROR>");
			while ((line = br.readLine()) != null)
				System.out.println(line);
			System.out.println("</ERROR>");
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);

			this.convertJpg(destinationFolder + outputfile + ".pdf",
					destinationFolder);

			return destinationFolder + outputfile + ".pdf";
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	private String convertJpg(String inputFile, String outputpath) {
		try {
			String current_dir = getServletContext().getRealPath("/");
			String runtimeFile = "pngconverter.bat";
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				runtimeFile = "pngconverter.sh";
			}
			Runtime rt = Runtime.getRuntime();

			String command = current_dir + "jod" + File.separatorChar
					+ runtimeFile + " " + inputFile + " " + outputpath
					+ "pages-%03d.jpg";
			// String command2 = "java -cp .:"+pre+"ridl.jar:"+pre+"js.jar:"+pre+"juh.jar:"+pre+"jurt.jar:"+pre+"jut.jar:"+pre+"java_uno.jar:"+pre+"java_uno_accessbridge.jar:"+pre+"edtftpj-1.5.2.jar:"+pre+"unoil.jar:"+pre+"dokeosupload.jar org.dokeos.ooconverter.DocumentLocalConverterMain "+pptFullPath+" "+destinationFolder;
			System.out.println("command3: " + command);
			Process proc = rt.exec(command);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			System.out.println("<ERROR3>");
			while ((line = br.readLine()) != null)
				System.out.println(line);
			System.out.println("</ERROR3>");
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);
			
			this.generateBatchThumb(inputFile,outputpath);
			
			return inputFile;
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	private String convertSingleJpg(String inputFile, String outputfile) {
		try {
			String current_dir = getServletContext().getRealPath("/");
			String runtimeFile = "pngconverter.bat";

			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				runtimeFile = "pngconverter.sh";
			}
			Runtime rt = Runtime.getRuntime();

			String command = current_dir + "jod" + File.separatorChar
					+ runtimeFile + " " + inputFile + " " + outputfile + ".jpg";
			// String command2 = "java -cp .:"+pre+"ridl.jar:"+pre+"js.jar:"+pre+"juh.jar:"+pre+"jurt.jar:"+pre+"jut.jar:"+pre+"java_uno.jar:"+pre+"java_uno_accessbridge.jar:"+pre+"edtftpj-1.5.2.jar:"+pre+"unoil.jar:"+pre+"dokeosupload.jar org.dokeos.ooconverter.DocumentLocalConverterMain "+pptFullPath+" "+destinationFolder;
			System.out.println("command3: " + command);
			Process proc = rt.exec(command);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			System.out.println("<ERROR3>");
			while ((line = br.readLine()) != null)
				System.out.println(line);
			System.out.println("</ERROR3>");
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);
			this.generateThumb(outputfile);
			return inputFile;
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	private void generateThumb(String filepath) {
		try {
			String current_dir = getServletContext().getRealPath("/");
			String runtimeFile = "thumbnail.bat";

			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				runtimeFile = "thumbnail.sh";
			}
			Runtime rt = Runtime.getRuntime();
			
			File f = new File(filepath);
			String name = f.getName();
			String folder = f.getParentFile().getAbsolutePath()+File.separatorChar;
			
			String command = current_dir + "jod" + File.separatorChar
					+ runtimeFile + " " + filepath + ".jpg " + folder
					+ "_thumb_"+name+".jpg";
			System.out.println("command thumbSingle " + command);
			Process proc = rt.exec(command);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			System.out.println("<ERROR4>");
			while ((line = br.readLine()) != null)
				System.out.println(line);
			System.out.println("</ERROR4>");
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	private void generateBatchThumb(String inputfile, String outputpath) {
		try {
			String current_dir = getServletContext().getRealPath("/");
			String runtimeFile = "thumbnail.bat";

			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				runtimeFile = "thumbnail.sh";
			}
			Runtime rt = Runtime.getRuntime();

			String command = current_dir + "jod" + File.separatorChar
					+ runtimeFile + " " + inputfile + " " + outputpath
					+ "_thumb_pages-%03d.jpg";
			System.out.println("command3: " + command);
			Process proc = rt.exec(command);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			System.out.println("<ERROR4>");
			while ((line = br.readLine()) != null)
				System.out.println(line);
			System.out.println("</ERROR4>");
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}	

}
