package org.xmlcrm.app.remote;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.red5.server.api.IScope;
import org.red5.server.api.Red5;

import org.apache.commons.lang.StringUtils;
import org.xmlcrm.app.data.basic.AuthLevelmanagement;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.documents.LibraryDocumentConverter;
import org.xmlcrm.app.documents.LibraryWmlLoader;
import org.xmlcrm.app.documents.LoadLibraryPresentation;
import org.xmlcrm.app.documents.CreateLibraryPresentation;

/**
 * 
 * @author swagner
 *
 */
public class ConferenceLibrary {

	private static final Log log = LogFactory.getLog(ConferenceLibrary.class);
	
	protected HashMap<String,String> fileExtensions = new HashMap<String,String>();
	
	public ConferenceLibrary(){
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
		fileExtensions.put("ext13", ".pdf");
		fileExtensions.put("ext14", ".swf");
	}
	
	public LinkedHashMap<String,Object> getListOfFiles(String SID, String moduleName,
			String parentFolder, String room, String domain ) {
		
		LinkedHashMap<String,Object> returnMap = new LinkedHashMap<String,Object>();
		
		try {
			
			LinkedList<LinkedList> filesMap = new LinkedList<LinkedList>();
			LinkedList<LinkedList> foldersMap = new LinkedList<LinkedList>();
			LinkedHashMap<String,LinkedHashMap> presentationObject = null;
						
	        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);  
	        
	        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)){
				
				String roomName = domain+"_"+room;
				//trim whitespaces cause it is a directory name
				roomName = StringUtils.deleteWhitespace(roomName);
				//System.out.println("roomname parentFolder"+roomName+" "+parentFolder);
				
				//Servlet.getServletRequest().getRealPath("/");
				IScope scope = Red5.getConnectionLocal().getScope().getParent();
				String current_dir = scope.getResource("upload/").getFile().getAbsolutePath();
				
				String working_dir = current_dir + File.separatorChar + roomName + parentFolder;
				log.error(working_dir);
				File dir = new File(working_dir);
				
				//First get all Directories of this Folder
				FilenameFilter ff = new FilenameFilter() {
				     public boolean accept(File b, String name) {
				    	  String absPath = b.getAbsolutePath()+File.separatorChar+name;
				    	  File f = new File (absPath);
				          return f.isDirectory();
				     }
				};			
				
				String[] allfolders = dir.list(ff);
				if(allfolders!=null){
					for(int i=0; i<allfolders.length; i++){
						File file = new File(working_dir+File.separatorChar+allfolders[i]);
						
						Date lastModifiedDate = new Date(file.lastModified());
						String lastModified = formatDate(lastModifiedDate);
						String fileName = allfolders[i];
						LinkedList<String> fileInfo = new LinkedList<String>();
						fileInfo.add("");
						fileInfo.add("");
						fileInfo.add(fileName);
						fileInfo.add(lastModified);
						foldersMap.add(fileInfo);
					}
				}
				
				
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
						if (allfiles[i].startsWith("_thumb_")){
							//log.error("Found Thumbs: "+allfiles[i]);
						} else {
							String lastModified = formatDate(new Date(file.lastModified()));
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
							
							if (fileName.equals(CreateLibraryPresentation.libraryFileName)){
								presentationObject = LoadLibraryPresentation.getInstance().parseLibraryFileToObject(file.getAbsolutePath());
							}
								
						}
					}
				}				
				
				returnMap.put("presentationObject",presentationObject);
				returnMap.put("folders",foldersMap);
				returnMap.put("files",filesMap);
				returnMap.put("error", "");
				
				return returnMap;
	        } else {
	        	returnMap.put("error", "not authenificated");
	        	return returnMap; 
	        }
	         
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("error", e.getMessage());
			return returnMap; 
		}        

	}
	
	private String formatDate(Date date) {
		SimpleDateFormat formatter;
		String pattern = "dd/MM/yy H:mm:ss";
		Locale locale= new Locale("en","US");
		formatter = new SimpleDateFormat(pattern, locale);
		return formatter.format(date);
	}
	
	public boolean deleteFile(String SID, String fileName, String moduleName, String parentFolder, String room, String domain){
		boolean returnVal = false;
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);  
        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)){		
			try {
				
				//Servlet.getServletRequest().getRealPath("/");
				IScope scope = Red5.getConnectionLocal().getScope().getParent();
				
				String current_dir = scope.getResource("upload/").getFile().getAbsolutePath();			
				
				String roomName = domain+"_"+room;
				
				//trim whitespaces cause it is a directory name
				roomName = StringUtils.deleteWhitespace(roomName);
				
				String working_dir = "";
				
				System.out.println("#### moduleName: "+moduleName);
	
				working_dir = current_dir+File.separatorChar+roomName+parentFolder;
				
				System.out.println("working_dir+fileName: "+working_dir+File.separatorChar+fileName);
				File dir = new File(working_dir+File.separatorChar+fileName);
				
				File thumb = new File(working_dir+File.separatorChar+"_thumb_"+fileName);
				if (thumb.exists()) thumb.delete();
				
				returnVal = dir.delete();
				
				System.out.println("delete file: "+working_dir+File.separatorChar+fileName);
			
				if (!returnVal && dir.isDirectory()){
					String[] listOfFiles = dir.list();
					
					for (int i=0;i<listOfFiles.length;i++){
						System.out.println("Deleting recursive: "+working_dir+File.separatorChar+fileName+File.separatorChar+listOfFiles[i]);
						File d2 = new File(working_dir+File.separatorChar+fileName+File.separatorChar+listOfFiles[i]);
						d2.delete();
						File thumb2 = new File(working_dir+File.separatorChar+fileName+File.separatorChar+"_thumb_"+listOfFiles[i]);
						if (thumb2.exists()) thumb2.delete();
					}
					dir.delete();
					
				}
				
			} catch (Exception e) {
				System.out.println("Exception: "+e);
				e.printStackTrace();
			}
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
			log.error(t);
		} catch (Exception err){
			log.error("[saveAsImage] "+err);
		}
		return null;
	}	
	
	public Long saveAsObject(String SID, String room, String domain, String fileName, Object t){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);  
        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)){		
			try {
				LinkedHashMap tObject = (LinkedHashMap)t;
				log.error(tObject.size());
				
				String roomName = domain+"_"+room;
				
//				trim whitespaces cause it is a directory name
				roomName = StringUtils.deleteWhitespace(roomName);
				
				IScope scope = Red5.getConnectionLocal().getScope().getParent();
				
				String current_dir = scope.getResource("upload/").getFile().getAbsolutePath()+File.separatorChar+roomName+File.separatorChar;

				log.error("### this is my working directory: "+current_dir);
				
				return LibraryDocumentConverter.getInstance().writeToLocalFolder(current_dir, fileName, tObject);
			} catch (Exception err){
				log.error("[saveAsImage] "+err);
			}
        }
		return null;
	}
	
	public LinkedHashMap loadWmlObject(String SID, String room, String domain, String fileName){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);  
        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)){		
			try {
				
				String roomName = domain+"_"+room;
				
//				trim whitespaces cause it is a directory name
				roomName = StringUtils.deleteWhitespace(roomName);
				
				IScope scope = Red5.getConnectionLocal().getScope().getParent();
				
				String current_dir = scope.getResource("upload/").getFile().getAbsolutePath()+File.separatorChar+roomName+File.separatorChar;

				log.error("### this is my working directory: "+current_dir);
				
				return LibraryWmlLoader.getInstance().loadWmlFile(current_dir, fileName);
			} catch (Exception err){
				log.error("[saveAsImage] "+err);
			}
        }
		return null;
	}
	
}