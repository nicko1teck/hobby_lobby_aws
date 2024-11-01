package com.o1teck.model.dto;

public class FileInfo {

	// the name of the file without the extension
	private String basename;

	private String extension;

	// the most immediate dir containing that file
	private String subDirectory;

	// the parent of that subDir
	private String baseDirectory;
	
	
	//CONSTRUCTOR
	public FileInfo(String basename, String extension, String subDirectory, String baseDirectory) {

		this.basename = basename;
		this.extension = extension;
		this.subDirectory = subDirectory;
		this.baseDirectory = baseDirectory;
	}
	
	
	//GETTERS SETTERS
	public String getBasename() {
		return basename;
	}

	public void setBaseName(String basename) {
		this.basename = basename;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getSubDirectory() {
		return subDirectory;
	}

	public void setSubDirectory(String subDirectory) {
		this.subDirectory = subDirectory;
	}

	public String getBaseDirectory() {
		return baseDirectory;
	}

	public void setBaseDirectory(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}

	@Override
	public String toString() {
		return "FileInfo [basename=" + basename + ", extension=" + extension + ", subDirectory=" + subDirectory
				+ ", baseDirectory=" + baseDirectory + "]";
	}
}
