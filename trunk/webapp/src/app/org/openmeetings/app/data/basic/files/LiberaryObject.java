package org.openmeetings.app.data.basic.files;
/**
 * Response of one Library-Files-Request
 * @author sebastianwagner
 *
 */
import java.util.LinkedList;

public class LiberaryObject {
	
	//These Objects here are into Sub-Items cause two LinkedList in one 
	//Object cannot be Serialized by Axis2!
	private LinkedList<FilesObject> filesList;
	private LinkedList<FoldersObject> foldersList;
	private PresentationObject presentationObject;
	private String error;
	public LinkedList<FilesObject> getFilesList() {
		return filesList;
	}
	public void setFilesList(LinkedList<FilesObject> filesList) {
		this.filesList = filesList;
	}
	public LinkedList<FoldersObject> getFoldersList() {
		return foldersList;
	}
	public void setFoldersList(LinkedList<FoldersObject> foldersList) {
		this.foldersList = foldersList;
	}
	public PresentationObject getPresentationObject() {
		return presentationObject;
	}
	public void setPresentationObject(PresentationObject presentationObject) {
		this.presentationObject = presentationObject;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

}
