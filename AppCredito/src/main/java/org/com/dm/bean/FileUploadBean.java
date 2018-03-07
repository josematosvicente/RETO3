package org.com.dm.bean;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class FileUploadBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -908299690627961768L;
	
	private CommonsMultipartFile file;

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
}
