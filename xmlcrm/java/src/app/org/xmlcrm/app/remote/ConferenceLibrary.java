package org.xmlcrm.app.remote;

import http.utils.multipartrequest.MultipartRequest;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.BitSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.red5.server.api.IScope;
import org.red5.server.api.Red5;
import org.mortbay.jetty.webapp.WebAppContext;

import org.apache.commons.lang.StringUtils;

public class ConferenceLibrary {

	private static final Log log = LogFactory.getLog(ConferenceLibrary.class);

	private int recursiveNumber = 0;
	
	protected HashMap<String,String> fileExtensions = new HashMap<String,String>();
	
	public ConferenceLibrary(){
		fileExtensions.put("ext1", ".ppt");
		fileExtensions.put("ext2", ".odp");
	}
	
	public List getListOfFiles(String moduleName,String parentFolder,String room, String domain ) {
		LinkedList<LinkedList> filesMap = new LinkedList<LinkedList>();
		LinkedList<LinkedList> foldersMap = new LinkedList<LinkedList>();
		LinkedList<LinkedList> returnMap = new LinkedList<LinkedList>();
		try {
			String roomName = domain+"_"+room;
			
			//trim whitespaces cause it is a directory name
			roomName = StringUtils.deleteWhitespace(roomName);
			
			System.out.println("roomname parentFolder"+roomName+" "+parentFolder);
			
			//Servlet.getServletRequest().getRealPath("/");
			IScope scope = Red5.getConnectionLocal().getScope().getParent();
			
			String current_dir = scope.getResource("upload/").getFile().getAbsolutePath();

			
			String working_dir = "";
			System.out.println(MultipartRequest.MAX_READ_BYTES);
			
			System.out.println("#### moduleName: "+moduleName);
			
			
			working_dir = current_dir+File.separatorChar+roomName+File.separatorChar;
			
			working_dir+=parentFolder;
			
			System.out.println("### this is my working directory: "+working_dir);
			
			File dir = new File(working_dir);

			/* 
			 * TODO: handle authentification
			 * We need to get a List of allowed files
			 * not all files in this directory can be allowed to be seen by the current
			 * uses
			 * 
			 */
			
			//First get all Directories of this Folder
			
			FilenameFilter ff = new FilenameFilter() {
			     public boolean accept(File b, String name) {
			    	  String absPath = b.getAbsolutePath()+File.separatorChar+name;
			    	  File f = new File (absPath);
			          return f.isDirectory();
			     }
			};			
			String[] allfolders = dir.list(ff);
			
			//Sortierung
			
			if(allfolders!=null){
				for(int i=0; i<allfolders.length; i++){
					File file = new File(working_dir+File.separatorChar+allfolders[i]);

					Date lastModifiedDate = new Date(file.lastModified());
					String lastModified = formatDate(lastModifiedDate);
					String fileName = allfolders[i];
					
					//String fileNamePure = fileName.substring(0, fileName.length()-4);
					//String fileNameExt = fileName.substring(fileName.length()-4,fileName.length());					
					
					LinkedList<String> fileInfo = new LinkedList<String>();
					fileInfo.add("");
					fileInfo.add("");
					fileInfo.add(fileName);
					fileInfo.add(lastModified);
					foldersMap.add(fileInfo);
				}
			}
			
			returnMap.add(foldersMap);
			
			//Secoond get all Files of this Folder
			
			FilenameFilter ff2 = new FilenameFilter() {
			     public boolean accept(File b, String name) {
			    	  String absPath = b.getAbsolutePath()+File.separatorChar+name;
			    	  File f = new File (absPath);
			          return f.isFile();
			     }
			};	
			
			String[] allfiles = dir.list(ff2);			
			if(allfiles!=null){
				for(int i=0; i<allfiles.length; i++){
					File file = new File(working_dir+File.separatorChar+allfiles[i]);
					
					System.out.println("working_dir+File.separatorChar+allfiles[i]: "+working_dir+File.separatorChar+allfiles[i]);

					Date lastModifiedDate = new Date(file.lastModified());
					String lastModified = formatDate(lastModifiedDate);
					String fileName = allfiles[i];
					String fileBytes = new Long(file.length()).toString();
					
					LinkedList<String> fileInfo = new LinkedList<String>();
					
					String fileNamePure = fileName.substring(0, fileName.length()-4);
					String fileNameExt = fileName.substring(fileName.length()-4,fileName.length());
					String isimage = "y";
					if(checkForPresention(fileNameExt.toLowerCase())) isimage = "n";
					fileInfo.add(fileName);
					fileInfo.add(fileNamePure);
					fileInfo.add(fileNameExt);
					fileInfo.add(lastModified);
					fileInfo.add(fileBytes);
					fileInfo.add(isimage);
					filesMap.add(fileInfo);
				}
			}
			
			returnMap.add(filesMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}
	
	private String formatDate(Date date) {
		SimpleDateFormat formatter;
		String pattern = "dd/MM/yy H:mm:ss";
		Locale locale= new Locale("en","US");
		formatter = new SimpleDateFormat(pattern, locale);
		return formatter.format(date);
	}
	
	public boolean deleteFile(String fileName, String moduleName, String parentFolder, String room, String domain){
		boolean returnVal = false;
		try {
			
			//Servlet.getServletRequest().getRealPath("/");
			IScope scope = Red5.getConnectionLocal().getScope().getParent();
			
			String current_dir = scope.getResource("upload/").getFile().getAbsolutePath();			
			
			String roomName = domain+"_"+room;
			
			//trim whitespaces cause it is a directory name
			roomName = StringUtils.deleteWhitespace(roomName);
			
			String working_dir = "";
			System.out.println(MultipartRequest.MAX_READ_BYTES);
			
			System.out.println("#### moduleName: "+moduleName);

			working_dir = current_dir+File.separatorChar+roomName+parentFolder;
			
			System.out.println("working_dir+fileName: "+working_dir+fileName);
			File dir = new File(working_dir+fileName);
			
			returnVal = dir.delete();
			
			System.out.println("delete file: "+working_dir+fileName);
		
			if (!returnVal && dir.isDirectory()){
				String[] listOfFiles = dir.list();
				
				for (int i=0;i<listOfFiles.length;i++){
					System.out.println("Deleting recursive: "+working_dir+fileName+File.separatorChar+listOfFiles[i]);
					File d2 = new File(working_dir+fileName+File.separatorChar+listOfFiles[i]);
					d2.delete();
				}
				dir.delete();
				
			}
			
		} catch (Exception e) {
			System.out.println("Exception: "+e);
			e.printStackTrace();
		}
		return returnVal;		
	}
	
	private boolean checkForPresention (String fileExtension) throws Exception{

		//System.out.println("sys fileExtensions.size(): "+fileExtensions.size());
		Iterator<String> extensionIt = fileExtensions.keySet().iterator();
		while (extensionIt.hasNext()) {
			String fileExt = fileExtensions.get(extensionIt.next());
			//System.out.println("sys fileExt: "+fileExt);
			if(fileExtension.equals(fileExt)){
				return true;
			}
		}
		return false;
	}	
	
	public String saveAsImage(Object t){
		try {
			BitSet tBitSet = (BitSet)t;
			log.error(tBitSet.size());
		} catch (Exception err){
			log.error("[saveAsImage] "+err);
		}
		return null;
	}
	
}
