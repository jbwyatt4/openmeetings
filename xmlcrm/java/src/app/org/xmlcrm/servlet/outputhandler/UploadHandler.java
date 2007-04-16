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

	protected HashMap<String,String> fileExtensions = new HashMap<String,String>();
	
	public UploadHandler(){
		fileExtensions.put("ext1", ".ppt");
		fileExtensions.put("ext2", ".odp");
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void service(HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse) throws ServletException, IOException {
		try {
    		
			if (httpServletRequest.getContentLength() > 0) {
				
				String sid = httpServletRequest.getParameter("sid");
				if(sid == null){
					sid = "default";
				}
				System.out.println("sid: "+sid);
				
		        int users_id = Sessionmanagement.getInstance().checkSession(sid);
		        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
		        
		        if (User_LEVEL>0){
					String room = httpServletRequest.getParameter("room");
					if(room == null){
						room = "default";
					}
					String domain = httpServletRequest.getParameter("domain");
					if(domain == null){
						domain = "default";
					}				
					
					String moduleName = httpServletRequest.getParameter("moduleName");
					if (moduleName == null) {
						moduleName = "nomodule";
					}
					//make a complete name out of domain(organisation) + roomname
					String roomName = domain+"_"+room;
					//trim whitespaces cause it is a directory name
					roomName = StringUtils.deleteWhitespace(roomName);
	
					//Get the current User-Directory
					
					String current_dir = getServletContext().getRealPath("/");
					System.out.println("Current_dir: "+current_dir);
					
					String working_dir = "";
					String working_dirppt = "";
					System.out.println(MultipartRequest.MAX_READ_BYTES);
	
					working_dir = current_dir+File.separatorChar+"upload"+File.separatorChar+roomName+File.separatorChar;
					working_dirppt = current_dir+File.separatorChar+"uploadtemp"+File.separatorChar+roomName+File.separatorChar;
					
					//Add the Folder for the Room if it does not exist yet
					File localFolder = new File(working_dir);
					if (!localFolder.exists()){
						localFolder.mkdir();
					}
					File localFolderppt= new File(working_dirppt);
					if (!localFolderppt.exists()){
						localFolderppt.mkdir();
					}
	
					System.out.println("#### UploadHandler working_dir: "+working_dir);
					
					if (!moduleName.equals("nomodule")){
						//Check variable to see if this file is a presentation
	
						ServletMultipartRequest upload = new ServletMultipartRequest(httpServletRequest, 104857600); // max 100 mb
	
						InputStream is = upload.getFileContents("Filedata");
	
						//trim whitespace
						String fileSystemName= StringUtils.deleteWhitespace(upload.getFileSystemName("Filedata"));
						
	
						String newFileSystemName = StringComparer.getInstance().compareForRealPaths(fileSystemName.substring(0, fileSystemName.length()-4));
						String newFileSystemExtName = fileSystemName.substring(fileSystemName.length()-4,fileSystemName.length());
						
						//trim long names cause cannot output that
						if(newFileSystemName.length()>=17){
							newFileSystemName=newFileSystemName.substring(0,16);
						}
	
						//check if this is a presentation
						boolean isPresentation = checkForPresention(newFileSystemExtName);
						
						String completeName = "";
						//if it is a presenation it will be copied to another place
						if (isPresentation){
							completeName = working_dirppt + newFileSystemName;
						} else {
							completeName = working_dir + newFileSystemName;
						}
	
						log.debug("******** completeName: "+completeName+isPresentation);
						
						File f = new File(completeName+newFileSystemExtName);
						if (f.exists()){
							System.out.println("File exisitert bereits");
							int recursiveNumber=0;
							String tempd = completeName+ "_"+recursiveNumber;
							while (f.exists()){
								recursiveNumber++;
								tempd = completeName + "_"+recursiveNumber;
								f = new File(tempd+newFileSystemExtName);
								
							}
							completeName = tempd;
							System.out.println("Neuer Folder name "+completeName);
						}
	
						System.out.println("*****2 ***** completeName: "+completeName+newFileSystemExtName);
						log.debug("*****2 ******* completeName: "+completeName+newFileSystemExtName);
						FileOutputStream fos = new FileOutputStream(completeName+newFileSystemExtName);
						
						byte[] buffer = new byte[1024];
						int len = 0;
						
						while (len != (-1)) {
							len = is.read(buffer, 0, 1024);
							if (len != (-1)) fos.write(buffer, 0, len);
						}
						
						fos.close();
						is.close();
						
						if (isPresentation){
							//automatically convert to slides
							System.out.println("isPresentation Neuer Folder name "+completeName);
	
							HashMap<String,String> ll = createPptToPng(newFileSystemName+newFileSystemExtName, roomName);
							
							String outputfolder = ll.get("outputfolder");
							//now it should be completed so copy that file to the expected location
							System.out.println("Upload destinationFolder "+outputfolder);
							
							//FileHelper fileHelper = new FileHelper();
							
							File pptToBeMoved = new File(completeName+newFileSystemExtName);
							
							System.out.println("outputfolder "+outputfolder);
							System.out.println("newFileSystemName "+newFileSystemName);
							System.out.println("newFileSystemExtName "+newFileSystemExtName);
							
							File pptWhereToMove = new File(outputfolder+newFileSystemName+newFileSystemExtName);
							pptWhereToMove.createNewFile();
							
							FileHelper.moveRec(pptToBeMoved, pptWhereToMove);
							
						}
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("ee "+e);
			e.printStackTrace();
			throw new ServletException(e);
		}

	}

	
	private boolean checkForPresention (String fileExtension) throws Exception{
		
		log.debug("fileExtensions.size(): "+fileExtensions.size());
		System.out.println("sys fileExtensions.size(): "+fileExtensions.size());
		Iterator<String> extensionIt = fileExtensions.keySet().iterator();
		while (extensionIt.hasNext()) {
			String fileExt = fileExtensions.get(extensionIt.next());
			log.debug("key: "+fileExt);
			System.out.println("sys fileExt: "+fileExt);
			if(fileExtension.equals(fileExt)){
				return true;
			}
		}
		return false;
	}
	
	
	private HashMap<String,String> createPptToPng(String fileName, String roomName) throws Exception{
		//DocumentLocalConverter documentLocalConverter = new DocumentLocalConverter();
		
		//Get the current Directory
		String current_dir = getServletContext().getRealPath("/");
		System.out.println("Current_dir: "+current_dir);
		
		String prefix = "file://";
		
		String working_imgdir = "";
		String working_pptdir = "";
		System.out.println(MultipartRequest.MAX_READ_BYTES);
		
		working_imgdir = current_dir+File.separatorChar+"upload"+File.separatorChar+roomName+File.separatorChar;
		working_pptdir = current_dir+File.separatorChar+"uploadtemp"+File.separatorChar+roomName+File.separatorChar;
		
		String pptFullPath = working_pptdir+fileName;
		
		String newFolderName = fileName.substring(0, fileName.length()-4);
		System.out.println(newFolderName);
		String destinationFolder = working_imgdir+newFolderName;
		
		System.out.println(destinationFolder);
		File f = new File(destinationFolder+File.separatorChar);
		if (f.exists()){
			System.out.println("Folder exisitert bereits");
			recursiveNumber=0;
			String tempd = destinationFolder+ "_"+recursiveNumber;
			while (f.exists()){
				recursiveNumber++;
				tempd = destinationFolder + "_"+recursiveNumber;
				f = new File(tempd);
				
			}
			destinationFolder = tempd;
			System.out.println("Neuer Folder name "+destinationFolder);
		}
		System.out.println(destinationFolder+File.separatorChar+" ++++ "+f.exists());
		boolean b = f.mkdir();
		if (b){
			System.out.println("Folder wurde angelegt");
		} else {
			System.out.println("Folder konnte nicht angelegt werden");
		}
		String outputfolder = destinationFolder+File.separatorChar;
		destinationFolder = prefix+destinationFolder+File.separatorChar;
		pptFullPath = prefix+pptFullPath;
		HashMap<String,String> ll = new HashMap<String,String>();
		ll.put("destinationFolder",destinationFolder);
		ll.put("pptFullPath",pptFullPath);
		ll.put("outputfolder",outputfolder);
		
		System.out.println("destinationFolder: "+destinationFolder+"/"+"   ### pptFullPath "+pptFullPath);
		System.out.println("########### invoke NEW Converting");	
		
		
		doConvertExec(pptFullPath, destinationFolder);
		//documentLocalConverter.startConverting(pptFullPath, destinationFolder);
		
		return ll;
	}	
	
	
	//Start oo-service external cause it makes no sense here
    private void doConvertExec(String pptFullPath, String destinationFolder)
    {
        try
        {      
        	String current_dir = getServletContext().getRealPath("/");
        	String runtimeFile = "testoo.bat";
        	if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
        		runtimeFile = "testoo.sh";
        	}
            Runtime rt = Runtime.getRuntime();
            
            String command2 = current_dir+File.separatorChar+"ooservice"+File.separatorChar+runtimeFile+" java "+pptFullPath+" "+destinationFolder+" "+current_dir+File.separatorChar+"lps-latest"+File.separatorChar+"ooservice"+File.separatorChar;
           // String command2 = "java -cp .:"+pre+"ridl.jar:"+pre+"js.jar:"+pre+"juh.jar:"+pre+"jurt.jar:"+pre+"jut.jar:"+pre+"java_uno.jar:"+pre+"java_uno_accessbridge.jar:"+pre+"edtftpj-1.5.2.jar:"+pre+"unoil.jar:"+pre+"dokeosupload.jar org.dokeos.ooconverter.DocumentLocalConverterMain "+pptFullPath+" "+destinationFolder;
            System.out.println("command2: "+command2);
            Process proc = rt.exec(command2);
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            System.out.println("<ERROR>");
            while ( (line = br.readLine()) != null)
                System.out.println(line);
            System.out.println("</ERROR>");
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        } catch (Throwable t)
          {
            t.printStackTrace();
          }
    }
	
}
