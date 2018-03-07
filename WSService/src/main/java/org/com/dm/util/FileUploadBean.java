package org.com.dm.util;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FileUploadBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -908299690627961768L;
	
	@JsonIgnore
	private CommonsMultipartFile file;

	@JsonIgnore
	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
}
