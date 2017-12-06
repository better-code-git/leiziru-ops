package com.ureactor.jeesite.common.utils;

public class PicUrlObject {

    private String fileName;  
    private String fileKey;  //对应oss的存储空间
    private String fileType;  //文件类型r
    private String fileDir;//文件目录
	
    public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileDir() {
		return fileDir;
	}
	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}
	public String getFileKey() {
		return fileKey;
	}
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
    
	
}
