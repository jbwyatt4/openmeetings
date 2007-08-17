package org.xmlcrm.servlet.outputhandler;

import http.utils.multipartrequest.MultipartRequest;
import http.utils.multipartrequest.ServletMultipartRequest;

import java.io.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

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
import org.xmlcrm.app.documents.GenerateThumbs;
import org.xmlcrm.app.documents.GeneratePDF;
import org.xmlcrm.app.documents.GenerateImage;
import org.xmlcrm.app.remote.Application;

public class UploadHandler extends HttpServlet {

	private static final Log log = LogFactory.getLog(UploadHandler.class);

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
				
				HashMap<String,HashMap> returnError = new HashMap<String,HashMap>();

				String sid = httpServletRequest.getParameter("sid");
				if (sid == null) {
					sid = "default";
				}

				Long users_id = Sessionmanagement.getInstance().checkSession(sid);
				long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);

				if (user_level > 0) {
					String room = httpServletRequest.getParameter("room");
					if (room == null) {
						room = "default";
					}
					String domain = httpServletRequest.getParameter("domain");
					if (domain == null) {
						domain = "default";
					}

					String moduleName = httpServletRequest.getParameter("moduleName");
					if (moduleName == null) {
						moduleName = "nomodule";
					}
					//make a complete name out of domain(organisation) + roomname
					String roomName = domain + "_" + room;
					//trim whitespaces cause it is a directory name
					roomName = StringUtils.deleteWhitespace(roomName);

					//Get the current User-Directory

					String current_dir = getServletContext().getRealPath("/");
					String working_dir = current_dir + File.separatorChar + "upload"
							+ File.separatorChar + roomName
							+ File.separatorChar;
					String working_dirppt = current_dir + File.separatorChar
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

					System.out.println("#### UploadHandler working_dir: "+ working_dir);

					if (!moduleName.equals("nomodule")) {
						//Check variable to see if this file is a presentation

						ServletMultipartRequest upload = new ServletMultipartRequest(httpServletRequest, 104857600); // max 100 mb

						InputStream is = upload.getFileContents("Filedata");

						//trim whitespace
						String fileSystemName = StringUtils.deleteWhitespace(upload.getFileSystemName("Filedata"));

						String newFileSystemName = StringComparer.getInstance()
								.compareForRealPaths(
										fileSystemName.substring(0,
												fileSystemName.length() - 4));
						String newFileSystemExtName = fileSystemName.substring(
								fileSystemName.length() - 4,
								fileSystemName.length()).toLowerCase();

						//trim long names cause cannot output that
						if (newFileSystemName.length() >= 17) {
							newFileSystemName = newFileSystemName.substring(0,16);
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

						File f = new File(completeName + newFileSystemExtName);
						if (f.exists()) {
							int recursiveNumber = 0;
							String tempd = completeName + "_" + recursiveNumber;
							while (f.exists()) {
								recursiveNumber++;
								tempd = completeName + "_" + recursiveNumber;
								f = new File(tempd + newFileSystemExtName);

							}
							completeName = tempd;
						}

						FileOutputStream fos = new FileOutputStream(completeName + newFileSystemExtName);
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
							//convert to pdf, thumbs, swf and xml-description
							returnError = GeneratePDF.getInstance().convertPDF(current_dir,
									newFileSystemName + newFileSystemExtName, roomName, 
									newFileSystemName, true, completeName, newFileSystemExtName);
						} else if (isPDF) {
							//convert to thumbs, swf and xml-description
							returnError = GeneratePDF.getInstance().convertPDF(current_dir, 
									newFileSystemName + newFileSystemExtName, roomName, 
									newFileSystemName, false, completeName, newFileSystemExtName);						
						} else if (isImage) {
							//convert it to JPG
							returnError = GenerateImage.getInstance().convertImage(current_dir, 
									newFileSystemName+ newFileSystemExtName, 
									roomName,newFileSystemName, false);
						} else if (isJpg) {
							HashMap<String,Object> processThumb = GenerateThumbs.getInstance().generateThumb(current_dir, completeName, 50);
							returnError.put("processThumb", processThumb);
						}
						
						//Flash cannot read the response of an upload
						//httpServletResponse.getWriter().print(returnError);
						LinkedHashMap<String,Object> hs = new LinkedHashMap<String,Object>();
						hs.put("user", Usermanagement.getInstance().getUser(users_id));
						hs.put("message", "library");
						hs.put("action", "newFile");
						hs.put("error", returnError);
						hs.put("fileName", completeName);		
						
						Application.getInstance().sendMessageWithClientByUserId(hs,users_id.toString());
						
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



}
